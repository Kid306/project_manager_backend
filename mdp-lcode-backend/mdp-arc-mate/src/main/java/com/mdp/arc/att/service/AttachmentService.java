package com.mdp.arc.att.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.arc.ArcTools;
import com.mdp.arc.att.entity.Attachment;
import com.mdp.arc.att.mapper.AttachmentMapper;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.ObjectTools;
import com.mdp.meta.client.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @author maimeng-mdp code-gen
 * @since 2023-9-1
 */
@Service
@DS("arc-ds")
public class AttachmentService extends BaseService<AttachmentMapper,Attachment> {
	static Logger logger =LoggerFactory.getLogger(AttachmentService.class);

	/**
	 * 自定义查询，支持多表关联
	 * @param page 分页条件
	 * @param ew 一定要，并且必须加@Param("ew")注解
	 * @param ext 如果xml中需要根据某些值进行特殊处理，可以通过这个进行传递，非必须，注解也可以不加
	 * @return
	 */
	public List<Map<String,Object>> selectListMapByWhere(IPage page, QueryWrapper ew, Map<String,Object> ext){
		return baseMapper.selectListMapByWhere(page,ew,ext);
	}


	@Value(value="${mdp.arc.file-upload-root-path:}")
	private String arcFileUploadRootPath;

	@Value(value="${mdp.arc.file-download-base-uri:}")
	String arcFileDownloadBaseUri;

	public static String  urlPrefix="mdp/arc/att/attachment/";


	public static final boolean isWindows;
	public static final String splash;
	public static final String root;

	static {
		if (System.getProperty("os.name") != null && System.getProperty("os.name").toLowerCase().contains("windows")) {
			isWindows = true;
			splash = "\\";
			root="D:\\arc\\files\\";
		} else {
			isWindows = false;
			splash = "/";
			root="/arc/files/";
		}
	}

	@Autowired
	private ItemService itemService;

	@PostConstruct
	public void checkPath(){
		if(StringUtils.isEmpty(arcFileUploadRootPath)){
			throw new RuntimeException("请配置档案附件上传根目录 如 mdp.arc.file-upload-root-path=D:/arc/files 参数");
		}
	}
	/** **/
	public Attachment createAttachment(String requestUrl,String id,String branchId, String deptid, String categoryId, String archiveId, String bizId, String originalFilename, String remark,String fixedName){
		Attachment aa=new Attachment();
		if(ObjectTools.isNotEmpty(id)){
			aa.setId(id);
		}else{
			aa.setId(this.createKey("id"));
		}

		if(StringUtils.isEmpty(bizId)){
			bizId="";
		}
		if(StringUtils.isEmpty(branchId)||branchId.indexOf("undefined")>=0||branchId.indexOf("null")>=0){
			branchId="";
		}
		if(StringUtils.isEmpty(categoryId)||categoryId.indexOf("undefined")>=0||categoryId.indexOf("null")>=0){
			categoryId="";
		}
		if(StringUtils.isEmpty(archiveId)||archiveId.indexOf("undefined")>=0||archiveId.indexOf("null")>=0){
			archiveId="";
		}
		if(StringUtils.isEmpty(deptid)||deptid.indexOf("undefined")>=0||deptid.indexOf("null")>=0){
			deptid="";
		}
		String relativePath= ArcTools.pathJoin( true,branchId,categoryId,archiveId);
		aa.setRelativePath(relativePath);
		aa.setRootPath(arcFileUploadRootPath);
		aa.setName(originalFilename);
		aa.setArchiveId(archiveId);
		int nameIndex=originalFilename.lastIndexOf(".");
		String fileSuffix;
		if(nameIndex!=-1) {
			fileSuffix=originalFilename.substring(nameIndex);
		}else {
			fileSuffix="";
		}
		if(StringUtils.hasText(fixedName)){
			aa.setStoreName(fixedName);
		}else{
			aa.setStoreName(aa.getId());
		}
		aa.setFileSuffix(fileSuffix);
		String url2=ArcTools.pathJoin(true,urlPrefix,relativePath,aa.getStoreName()+aa.getFileSuffix());
		String preUrl=getPreUrlPathByUploadUrl(requestUrl);
		String url=(ArcTools.pathJoin(false,preUrl,url2));
		aa.setUrl(url);
		aa.setCdnUrl(url);

		aa.setCanDel("1");
		aa.setCanDown("1");
		aa.setCanRead("1");
		aa.setBizId(bizId);
		aa.setBranchId(branchId);
		aa.setDeptid(deptid);
		aa.setRemark(remark);
		aa.setCategoryId(categoryId);
		return aa;

	}
	public String getArcUploadRootPath() {
		return arcFileUploadRootPath;
	}

	public String getFullFilePath(String relativePath,String id,String fileSuffix){
		String path= ArcTools.pathJoin(true,this.arcFileUploadRootPath,relativePath,id+fileSuffix);
		return path;
	}

	public String getPreUrlPathByUploadUrl(String requestURL) {
		if(StringUtils.hasText(this.arcFileDownloadBaseUri)){
			return this.arcFileDownloadBaseUri;
		}else{
			int indexOf=requestURL.indexOf(urlPrefix);
			return  requestURL.substring(0,indexOf);
		}
	}
	public String getFullFilePathByUrl(String servletPath) {

		int indexOf=servletPath.indexOf(urlPrefix);
		return ArcTools.pathJoin(true,arcFileUploadRootPath,servletPath.substring(indexOf+urlPrefix.length()));
	}
}

