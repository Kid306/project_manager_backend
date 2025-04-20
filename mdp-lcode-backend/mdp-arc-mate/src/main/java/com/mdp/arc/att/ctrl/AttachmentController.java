package com.mdp.arc.att.ctrl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.arc.ArcTools;
import com.mdp.arc.att.entity.Attachment;
import com.mdp.arc.att.service.AttachmentService;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.ObjectTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * url编制采用rest风格,如对ARC.arc_attachment的操作有增删改查,对应的url分别为:<br>
 *  新增: arc/archiveAttachment/add <br>
 *  查询: arc/archiveAttachment/list<br>
 *  模糊查询: arc/archiveAttachment/listKey<br>
 *  修改: arc/archiveAttachment/edit <br>
 *  删除: arc/archiveAttachment/del<br>
 *  批量删除: arc/archiveAttachment/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 arc 小模块 <br>
 * 实体 ArchiveAttachment 表 ARC.arc_attachment 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.arc.archiveAttachmentController")
@RequestMapping(value="/*/arc/att/attachment")
public class AttachmentController {
	
	static Log logger=LogFactory.getLog(AttachmentController.class);
	
	@Autowired
	private AttachmentService attachmentService;
	
    @Autowired  
    private HttpServletRequest request;  
	
	
	

	
	@Autowired
	 ResourceLoader resourceLoader;
	
	 
	
	/**
	 * 请求,如list
	 * 分页参数 {pageNum:1,pageSize:10,total:0}
	 * 根据条件查询数据对象列表,如果不上传分页参数，将不会分页。后台自动分页，无需编程
	 */
	@RequestMapping(value="/list")
	public Result listArchiveAttachment( @RequestParam Map<String,Object> params ){
		 
		IPage page= QueryTools.initPage(params);
		User user=LoginUtils.getCurrentUserInfo();
		params.put("qxUserid",user.getUserid());
		params.put("qxBranchId",user.getBranchId());
		params.put("qxRoleids",LoginUtils.getMyRoleids());
		params.put("qxDeptids",user.getDeptids());

		RequestUtils.transformArray(params,"relyIdList");
		RequestUtils.transformArray(params,"relySidList");
		List<Map<String,Object>>	datas = attachmentService.selectListMapByWhere(page,QueryTools.initQueryWrapper(Attachment.class,params),params);	//列出ArchiveAttachment列表
		return Result.ok().setData(datas).setTotal(page.getTotal());
	}
   @RequestMapping(value="/upload" )
	public Result filesUpload(Attachment att,HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		User user= LoginUtils.getCurrentUserInfo();
	   Map<String,Object> map=new HashMap<>();
		//判断file数组不能为空并且长度大于0
		//保存文件
		try {
			if(ObjectTools.isNotEmpty(att.getStoreName())){
				att.setStoreName(URLDecoder.decode(att.getStoreName(), StandardCharsets.UTF_8.name()));
			}
			if(ObjectTools.isNotEmpty(att.getRemark())){
				att.setRemark(URLDecoder.decode(att.getRemark(),"UTF-8"));
			}
			Attachment attachment=null;
			String requestUrl=request.getRequestURL().toString();
			if(ObjectTools.isEmpty(att.getId())){
				attachment=saveFile(requestUrl,null,user.getBranchId(),user.getDeptid(),att.getCategoryId(),att.getArchiveId(),att.getBizId(),att.getRemark(),file,att.getStoreName());
				attachment.setArchiveType(att.getArchiveType());
				attachment.setRelyType(att.getRelyType());
				attachment.setRelyId(att.getRelyId());
				attachment.setRelyStype(att.getRelyStype());
				attachment.setRelySid(att.getRelySid());
				attachment.setCuserid(user.getUserid());
				attachment.setCusername(user.getUsername());
				attachment.setCdate(new Date());
				attachmentService.insert(attachment);
			}else {
				attachment=this.attachmentService.getById(att.getId());
				if(attachment==null){
					attachment=saveFile(requestUrl,null,user.getBranchId(),user.getDeptid(),att.getCategoryId(),att.getArchiveId(),att.getBizId(),att.getRemark(),file,att.getStoreName());
					attachment.setArchiveType(att.getArchiveType());
					attachment.setCuserid(user.getUserid());
					attachment.setCusername(user.getUsername());
					attachment.setCdate(new Date());
					attachmentService.insert(attachment);
				}else{
					String originalFilename=file.getOriginalFilename();

					if(originalFilename.endsWith(attachment.getFileSuffix())){
						this.coverFile(file,attachment);
					}else {
						return Result.error("file-suffix-no-match","文件类型不匹配，要求%s文件",attachment.getFileSuffix());
					}

				}
			}

			return Result.ok().setData(attachment);
		} catch (BizException e) {
			return Result.error(e);
		}catch (Exception e) {
			return Result.error("文件保存出错");
		}
	}

	/**
	 *
	 * 覆盖原有文件
	 *
	 */
	private Attachment coverFile( MultipartFile file,Attachment attachment) {
 		String dirPath="";
		String fullFilePath="";
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// 文件保存路径
				String originalFilename=file.getOriginalFilename();
 				dirPath=ArcTools.pathJoin(true,attachment.getRootPath(),attachment.getRelativePath());
				File file2=new File(dirPath);
				if(!file2.exists()){
					file2.mkdirs();
				}
				// 转存文件
				fullFilePath=ArcTools.pathJoin(true,dirPath,attachment.getStoreName()+attachment.getFileSuffix());
				file.transferTo(new File(fullFilePath));
				return attachment;
			} catch (Exception e) {
				logger.error("保存文件出错到"+fullFilePath+"出错");
				throw new BizException("保存文件出错");
			}
		}else{
			throw new BizException("文件为空");
		}
	}
	/***
	 * 保存文件
	 * @param file
	 * @return
	 */
	private Attachment saveFile(String requestUrl,String id,String branchId, String deptid, String categoryId, String archiveId, String bizId, String remark, MultipartFile file,String fixedName ) {
		Attachment attachment=new Attachment();
		String dirPath="";
		String fullFilePath="";
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// 文件保存路径
				String originalFilename=file.getOriginalFilename();
				attachment= attachmentService.createAttachment(requestUrl,id,branchId,deptid,categoryId,archiveId,bizId,originalFilename,remark,fixedName);
				dirPath=ArcTools.pathJoin(true,attachment.getRootPath(),attachment.getRelativePath());
				File file2=new File(dirPath);
				if(!file2.exists()){
					file2.mkdirs();
				}
				// 转存文件
				fullFilePath=ArcTools.pathJoin(true,dirPath,attachment.getStoreName()+attachment.getFileSuffix());
				file.transferTo(new File(fullFilePath));
				return attachment;
			} catch (Exception e) {
				logger.error("保存文件出错到"+fullFilePath+"出错");
			   throw new BizException("保存文件出错");
			}
		}else{
			throw new BizException("文件为空");
		}
	}
	    
	     
	    @RequestMapping("/**")
	     public ResponseEntity download(HttpServletRequest request,HttpServletResponse response, @RequestParam(value="name",required=false) String name, @RequestParam(value="preview",required=false) String preview ) {
	    	 
	        //下载文件路径 
	    	String servletPath=request.getServletPath();
	    	String fullPath=this.attachmentService.getFullFilePathByUrl(servletPath);
			if(StringUtils.isEmpty(name)){
				name=servletPath.substring(servletPath.lastIndexOf("/")+1);
				
			}  
			Resource resource=resourceLoader.getResource("file:"+fullPath);
			String contentType = null;
	        try {
	            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	        } catch (IOException e) {
	            logger.error("无法获取文件类型", e);
	        }
	        if (contentType == null) {
	            contentType = "application/octet-stream";
	        }
			if("1".equals(preview)){
				return ResponseEntity.ok()
						.contentType(MediaType.parseMediaType(contentType))
 						.body(resource);
			}else {
				return ResponseEntity.ok()
						.contentType(MediaType.parseMediaType(contentType))
						.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + name + "\"")
						.body(resource);
			}

	     }
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/batchDel")
	public Result batchDel(@RequestBody List<Attachment> atts){

 		this.attachmentService.removeByIds(atts);
		return Result.ok();
	}
	/**
	 * 根据主键删除1条数据
	*/
	@RequestMapping(value="/del")
	public Result delArchiveAttachment(@RequestBody Attachment archiveAttachment){
		
		
		try{
			attachmentService.deleteByPk(archiveAttachment);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e.getMessage());
		}  
		return Result.ok();
	}
	 
	 
}
