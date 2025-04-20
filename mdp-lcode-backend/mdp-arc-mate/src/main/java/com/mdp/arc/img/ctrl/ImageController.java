package com.mdp.arc.img.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.arc.ArcTools;
import com.mdp.arc.img.entity.Base64ImageVo;
import com.mdp.arc.img.entity.Image;
import com.mdp.arc.img.service.ImageCompressService;
import com.mdp.arc.img.service.ImageService;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.ObjectTools;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.mdp.core.utils.BaseUtils.map;

/**
 * url编制采用rest风格,如对ARC.arc_image 图片素材库的操作有增删改查,对应的url分别为:<br>
 *  新增: arc/image/add <br>
 *  查询: arc/image/list<br>
 *  模糊查询: arc/image/listKey<br>
 *  修改: arc/image/edit <br>
 *  删除: arc/image/del<br>
 *  批量删除: arc/image/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 arc 小模块 <br>
 * 实体 Image 表 ARC.arc_image 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.arc.imageController")
@RequestMapping(value="/*/arc/img/image")
@Api(tags={"图片素材库操作接口"})
public class ImageController {
	
	static Log logger=LogFactory.getLog(ImageController.class);
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	 ResourceLoader resourceLoader;
	
	@Autowired
	private ImageCompressService imageCompressService;
	  
	
	@ApiOperation( value = "查询图片素材库信息列表",notes="listImage,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(Image.class)
	@ApiResponses({
		@ApiResponse(code = 200,response= Image.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listImage(@RequestParam Map<String,Object> params ){
		User user= LoginUtils.getCurrentUserInfo();
		IPage page= QueryTools.initPage(params);
		QueryWrapper<Image> qw=QueryTools.initQueryWrapper(Image.class,params);
		qw.eq("branch_id",user.getBranchId());
		List<Map<String,Object>> datas=this.imageService.selectListMapByWhere(page,qw,params);
		
		return Result.ok().setData(datas).setTotal(page.getTotal());
	}
	
	
	@ApiOperation( value = "获取图片分类名",notes="listTags,条件之间是 and关系")
	@ApiImplicitParams({
		@ApiImplicitParam(name="tag",value="标签",required=false),
		@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderFields",value="排序列 如性别、学生编号排序 ['sex','studentId']",required=false),
		@ApiImplicitParam(name="orderDirs",value="排序方式,与orderFields对应，升序 asc,降序desc 如 性别 升序、学生编号降序 ['asc','desc']",required=false) 
	})
	@ApiResponses({
		@ApiResponse(code = 200,response=Image.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/listTags",method=RequestMethod.GET)
	public Result listTags( @RequestParam Map<String,Object> params ){
		System.out.println("------------------");
		List<Map<String,Object>>	imageList = imageService.selectTagsListMap(params);
		return Result.ok().setData(imageList);
	}


	/**
	 * 上传固定名称图片 并压缩保存到硬盘
	 * @param categoryId 分类 非必输
	 * @param fixedName 固定文件名称，如用户编号等
	 * @param file 文件流对象，form表单自动生成 必输
	 * @return
	 */
	@RequestMapping(value="/upload/fixed")
	public Result filesUpload(HttpServletRequest request,  @RequestParam(value="categoryId",required=false) String categoryId,  @RequestParam("file") MultipartFile file,  @RequestParam(value="fixedName",required=false) String fixedName) {
		
		Map<String,Object> map=new HashMap<>();
		Float scale=1f;
		Float outputQuality=0f;
		if(!StringUtils.hasText(fixedName)){
			return Result.error("fixedName","请指定固定文件名称");
		}
		//判断file数组不能为空并且长度大于0
		if(outputQuality==null || outputQuality==0) {
			long fileSize= file.getSize();
			if(fileSize>=1024*2048) {
				outputQuality=0.2f;
			}else if(fileSize>=1024*1024) {
				outputQuality=0.5f;
			}else {
				outputQuality=1f;
			}
		}
		Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("JPEG");
		while (readers.hasNext()) {
			System.out.println("reader: " + readers.next());
			logger.info("reader: " +readers.next());
		}
		if(scale<0.1f ||scale>1f) {
			return Result.error("scale-err", "scale", "缩放比例必须在0.1~1之间");
		}else if(outputQuality<0.1f||outputQuality>1f) {
			return Result.error("outputQuality-err", "outputQuality", "压缩质量必须在0.1~1之间");
		}else {
			//保存文件
			try {
				String originalFilename=file.getOriginalFilename();
				String fileSuffix=originalFilename.substring(originalFilename.lastIndexOf("."));
				String requestURL=request.getRequestURL().toString();
				String rootPath= imageService.arcImageUploadRootPath;
				String dirPath= ArcTools.pathJoin(true,rootPath,categoryId);
				String fullFilePath=ArcTools.pathJoin(true,dirPath,fixedName+fileSuffix);
				File file2=new File(dirPath);
				if(!file2.exists()){
					file2.mkdirs();
				}
				Tips tips=imageCompressService.compressAndSaveImage(file.getInputStream(),scale,outputQuality,fullFilePath);
				Result.assertIsFalse(tips);
 				String fileSuffix2=originalFilename.substring(originalFilename.lastIndexOf(".")+1);
 				String fileSuffixNew="jpg";
				String fullFilePathNew=ArcTools.pathJoin(true,dirPath,fixedName+"."+fileSuffixNew);
				if(StringUtils.hasText(fileSuffix2)){
					if(!fileSuffix.equalsIgnoreCase(fileSuffixNew)){
						BufferedImage originalImage = ImageIO.read(new File(fullFilePath));


						BufferedImage newImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

						for (int x = 0; x < originalImage.getWidth(); x++) {

							for (int y = 0; y < originalImage.getHeight(); y++) {

								newImage.setRGB(x, y, originalImage.getRGB(x, y));

							}
						}

						ImageIO.write(newImage, fileSuffixNew, new File(fullFilePathNew));
					}
				}
 					String url2=ArcTools.pathJoin(true,ImageService.urlPrefix,categoryId,fixedName+"."+fileSuffixNew);
					String prePath=imageService.getPreUrlPathByUploadUrl(requestURL);
					String url=ArcTools.pathJoin(false,prePath,url2);
					map.put("data", map("url",url));
					return Result.ok().setData(map("url",url));
			} catch (BizException e) {
				logger.error("上传图像失败",e);
				return Result.error(e);
			}catch (Exception e) {
				logger.error("上传图像失败",e);
				return Result.error("文件保存出错");

			}
		}
	}
 
	/**
	 * 图片上传并压缩保存到硬盘，同时记录到数据库
	 * @param fileName 文件名 非必输
	 * @param tag 标签 非必输
	 * @param storedb 是否创建数据库记录 非必输,true|false默认不存
	 * @param categoryId 分类 非必输
	 * @param remark 备注 非必输
	 * @param deptid 部门 非必输
	 * @param branchId 归属机构 非必输
	 * @param file 文件流对象，form表单自动生成 必输
	 * @param scale 压缩比例 0.1~1之间
	 * @param outputQuality 压缩质量0.1~1之间
	 * @return
	 */
	 @RequestMapping(value="/upload")
	    public Result filesUpload(HttpServletRequest request,
								              @RequestParam(value="id",required=false) String id,
								              @RequestParam(value="fileName",required=false) String fileName,
											  @RequestParam(value="tag",required=false) String tag,
											  @RequestParam(value="storedb",required=false) boolean storedb,
											  @RequestParam(value="categoryId",required=false) String categoryId,
											  @RequestParam(value="remark",required=false) String remark,
											  @RequestParam(value="deptid",required=false) String deptid,
											  @RequestParam(value="branchId",required=false) String branchId,
											  @RequestParam("file") MultipartFile file,
											  @RequestParam(value="scale",required=false) Float scale,
											  @RequestParam(value="outputQuality",required=false) Float outputQuality,
											  @RequestParam(value="fixedName",required=false) String fixedName) {
		   
 		   Image image = null;
		   String fileSuffix=null;
	        //判断file数组不能为空并且长度大于0  
		   if(scale==null || scale==0) { 
			   scale=1f;
		   }
		   if(outputQuality==null || outputQuality==0) {
			   long fileSize= file.getSize();
			   if(fileSize>=1024*2048) {
				   outputQuality=0.2f;
			   }else if(fileSize>=1024*1024) {
				   outputQuality=0.5f;
			   }else {
				   outputQuality=0.5f;
			   }
			   
		   }
       	if(scale<0.1f ||scale>1f) {
       		return Result.error("scale-err", "scale", "缩放比例必须在0.1~1之间");
       	}else if(outputQuality<0.1f||outputQuality>1f) {
       		return Result.error("outputQuality-err", "outputQuality", "压缩质量必须在0.1~1之间");
       	}else {
       	  //保存文件  
            try {

				String requestURL=request.getRequestURL().toString();
            	image=saveImage(requestURL,id,fileName,tag,storedb,categoryId,remark, deptid,branchId,file,scale,outputQuality,fixedName);
             	return Result.ok().setData(image);
			} catch (BizException e) {
				return Result.error(e);
			}catch (Exception e) {
            	logger.error("文件保存出错",e);
				return Result.error("文件保存出错");
			}  
       	}
	 }

	@RequestMapping(value="/uploadMovie")
	public Result movieUpload(HttpServletRequest request,@RequestParam(value="id",required=false) String id,@RequestParam(value="fileName",required=false) String fileName,@RequestParam(value="tag",required=false) String tag,@RequestParam(value="categoryId",required=false) String categoryId,@RequestParam(value="remark",required=false) String remark,@RequestParam(value="deptid",required=false) String deptid,@RequestParam(value="branchId",required=false) String branchId,@RequestParam("file") MultipartFile file,@RequestParam(value="scale",required=false) Float scale,@RequestParam(value="outputQuality",required=false) Float outputQuality,@RequestParam(value="fixedName",required=false) String fixedName) {
		
		Map<String,Object> map=new HashMap<>();
		Image image = null;
		String fileSuffix=null;
		System.out.println(file);
		outputQuality=1f;
		Long duration=null;
		String requestURL=request.getRequestURL().toString();

		scale=1f;
			//保存文件
			try {

//				image=saveMovie(fileName,tag,categoryId,remark, deptid,branchId,file,scale,outputQuality);
				Map<String,Object> MovieAndDuration=(Map<String,Object>)saveMovie(request,id,fileName,tag,categoryId,remark, deptid,branchId,file,scale,outputQuality,fixedName);
				image=(Image) MovieAndDuration.get("movie");
				duration=(Long) MovieAndDuration.get("duration");
				map.put("data", image);
				map.put("duration", duration);
				return Result.ok().setData(image).put("duration",duration);
			} catch (BizException e) {
				return Result.error(e);
			}catch (Exception e) {
				return Result.error("文件保存出错");
			}

	}

	/**
	 * 图片上传保存到硬盘，同时记录到数据库
	 */
	public Result saveMovie(HttpServletRequest request,String id,String fileName,String tag,String categoryId,String remark,String deptid,String branchId,MultipartFile file,Float scale,Float outputQuality,String fixedName){
		Image i=null;
		String dirPath="";
		String fullFilePath="";
		Map<String,Object> mmp=new HashMap<String,Object>();//返回短视频对象,和时长 
		String requestURL=request.getRequestURL().toString();
		if(StringUtils.isEmpty(categoryId)) {
			throw new BizException("categoryId","分类不能为空");
		}
		if(!file.isEmpty()){
			File file2=null;
			try {
				String originalFilename = file.getOriginalFilename();
				long size = file.getSize();
				i = this.imageService.genImageInfo(requestURL,id,originalFilename,fileName, tag,categoryId,remark,deptid,branchId,size,fixedName);
				dirPath=(i.getRootPath()+"/"+i.getRelativePath()).replaceAll("//", "/");
				file2=new File(dirPath);
				if(!file2.exists()){
					file2.mkdirs();
				}
				// 转存文件
				fullFilePath=(dirPath+"/"+i.getStorageName()+i.getFileSuffix()).replaceAll("//", "/");
				// Get the file and save it somewhere
				byte[] bytes = file.getBytes();
				Path path = Paths.get(fullFilePath);
				Files.write(path, bytes);

				//计算视频长度
				File f = null;
				f = File.createTempFile("tmp", i.getFileSuffix());
				file.transferTo(f);
				f.deleteOnExit();
				//获取视频时长
				MultimediaObject instance = new MultimediaObject(f);
				MultimediaInfo result = instance.getInfo();
				long ls = (result.getDuration() / 1000);

				this.imageService.insert(i);
				mmp.put("movie",i);
				mmp.put("duration",ls);
				return Result.ok().put("movie",i).put("duration",ls);
			} catch (BizException e) {
				throw e;
			}catch (Exception e) {
				logger.error("保存图片出错到"+fullFilePath+"出错",e);
				throw new BizException("saveFileError01","保存图片出错");
			}finally {
				if(file2!=null) {
					file2=null;
				}
			}

		}else{
			throw new BizException("saveFileError02","图片为空");
		}
	}

	@RequestMapping("/upload/base64")
	    public  Map<String,Object> uplodBase64(@RequestBody Base64ImageVo imageVo,HttpServletRequest request){
		   
		   Map<String,Object> map=new HashMap<>();
		   
        	Float scale=imageVo.getScale();
        	Float outputQuality=imageVo.getOutputQuality();
 		   if(scale==null||scale==0) {
			   scale=1f;
		   }
		   if(outputQuality==null || outputQuality==0) {
			   long fileSize= imageVo.getFileData().length();
			   if(fileSize>=1024*2048) {
				   outputQuality=0.2f;
			   }else if(fileSize>=1024*1024) {
				   outputQuality=0.5f;
			   }else {
				   outputQuality=0.5f;
			   }
		   }
		   imageVo.setScale(scale);
		   imageVo.setOutputQuality(outputQuality);
	       	if(scale<0.1f ||scale>1f) {
	       		return Result.error("scale-err", "scale", "缩放比例必须在0.1~1之间");
	       	}else if(outputQuality<0.1f||outputQuality>1f) {
	       		return Result.error("outputQuality-err", "outputQuality", "压缩质量必须在0.1~1之间");
	       	}else {
	       		Image image=new Image();
		        //判断file数组不能为空并且长度大于0  
		                //保存文件  
		                try {
		                	image=this.saveBase64File( imageVo ,request.getRequestURL().toString());
							return Result.ok().setData(image);
						} catch (BizException e) {
							return Result.error(e);
						}catch (Exception e) {
							return Result.error("文件保存出错");
						}  
		        
	       	}
		   
	    }  
	 @RequestMapping("/**")
     public ResponseEntity download(HttpServletRequest request,HttpServletResponse response, @RequestParam(value="name",required=false) String name, @RequestParam(value="preview",required=false) String preview  ) {
    	 
        //下载文件路径  
		 String requestURL=request.getRequestURL().toString();
    	String fullPath=this.imageService.getFullFilePathByUrl(requestURL); 
		if(StringUtils.isEmpty(name)){
			name=requestURL.substring(requestURL.lastIndexOf("/")+1);
			
		}
		 // * 表示允许任何域名跨域访问
		 response.setHeader("Access-Control-Allow-Origin", "*");
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
	
	 
	
	/***/
	@ApiOperation( value = "删除一条图片素材库信息",notes="delImage,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delImage(@RequestBody Image image){
		
		
		try{
			imageService.deleteByPk(image);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e.getMessage());
		}  
		return Result.ok();
	}
	 
	 
	

	
	/***/
	@ApiOperation( value = "根据主键列表批量删除图片素材库信息",notes="batchDelImage,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelImage(@RequestBody List<Image> images) {
		
		
		try{ 
			imageService.batchDelete(images);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e.getMessage());
		}  
		return Result.ok();
	} 
	
	/**
	 * 图片上传并压缩保存到硬盘，同时记录到数据库
	 * @param fileName 文件名 非必输
	 * @param tag 标签 非必输
	 * @param categoryId 分类 非必输
	 * @param remark 备注 非必输
	 * @param deptid 部门 非必输
	 * @param branchId 归属机构 非必输
	 * @param file 文件流对象，form表单自动生成 必输
	 * @param scale 非必输，默认为1， 压缩比例 0.1~1之间
	 * @param outputQuality 非必输，默认为1，压缩质量0.1~1之间
	 * @return
	 */
	public Image saveImage(String requestURL,String id,String fileName,String tag,boolean storedb,String categoryId,String remark,String deptid,String branchId,MultipartFile file,Float scale,Float outputQuality,String fixedName){
		Image i=null;
		String dirPath="";
		String fullFilePath="";
		boolean existsDb=false;
		if(StringUtils.isEmpty(categoryId)) {
			throw new BizException("categoryId","分类不能为空");
		}
		if(!file.isEmpty()){
			File file2=null;
			try {
				String originalFilename = file.getOriginalFilename();
				long size = file.getSize();
				if(ObjectTools.isNotEmpty(id)){
					i=this.imageService.getById(id);
					if(i==null){
						existsDb=false;
						i = this.imageService.genImageInfo(requestURL,id,originalFilename,fileName, tag,categoryId,remark,deptid,branchId,size,fixedName);
					}else {
						if(!originalFilename.endsWith(i.getFileSuffix())){
							throw new BizException(LangTips.errMsg("file-suffix-no-match","文件类型不匹配，要求%s文件",i.getFileSuffix()));
						}
						existsDb=true;
					}
				}else{
					i = this.imageService.genImageInfo(requestURL,id,originalFilename,fileName, tag,categoryId,remark,deptid,branchId,size,fixedName);
				}
				dirPath= ArcTools.pathJoin(true,i.getRootPath(),i.getRelativePath());
				file2=new File(dirPath);
				if(!file2.exists()){
					file2.mkdirs();
				}
				// 转存文件
				fullFilePath=ArcTools.pathJoin(true,dirPath,i.getStorageName()+i.getFileSuffix());
				Tips tips=imageCompressService.compressAndSaveImage(file.getInputStream(),scale,outputQuality,fullFilePath);
				if(tips.isOk()) {
					if(storedb && !existsDb){
						this.imageService.insert(i);
					}
				}else {
					throw new BizException(tips);
				}
			} catch (BizException e) {
				throw e;
			}catch (Exception e) {
				logger.error("保存图片出错,保存路径【"+fullFilePath+"】",e);
				throw new BizException("saveFileError01","保存图片出错");
			}finally {
				if(file2!=null) {
					file2=null;
				}
			}

		}else{
			throw new BizException("saveFileError02","图片为空");
		}
		return i;
	}
	@ApiOperation( value = "根据主键修改一条图片素材库信息",notes="editImage")
	@ApiResponses({
		@ApiResponse(code = 200,response=Image.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editImage(@RequestBody Image image) {
		
		
		try{
			imageService.updateByPk(image);
			return Result.ok().setData(image);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e.getMessage());
		}
	}
	
	   /*** 
  * 保存文件 
  * @param
  * @return 
  */  
 private Image saveBase64File( Base64ImageVo imageVo,String requestURL) {
 	Image i=null;
 	String dirPath="";
 	String fullFilePath="";  
	if(StringUtils.isEmpty(imageVo.getCategoryId())) {
		throw new BizException("categoryId","分类不能为空");
	} 
     // 判断文件是否为空  
     if (!StringUtils.isEmpty(imageVo.getFileData())) {  
         try {  
             // 文件保存路径  
         	String originalFilename=imageVo.getName();
         	if(StringUtils.isEmpty(originalFilename)) {
         		throw new BizException("name","文件名不能为空，如  name = xxx.png");
         	}
         	i=this.imageService.genImageInfo(requestURL,null,originalFilename,imageVo.getName(),imageVo.getTag(), imageVo.getCategoryId(),imageVo.getRemark(),imageVo.getDeptid(),imageVo.getBranchId(),(long) imageVo.getFileData().length(),"");
         	 dirPath=(i.getRootPath()+"/"+i.getRelativePath()).replaceAll("//", "/");
          	File file2=new File(dirPath);
          	if(!file2.exists()){
          		file2.mkdirs();
          	}
          	// 转存文件  
         	fullFilePath=(dirPath+"/"+i.getStorageName()+i.getFileSuffix()).replaceAll("//", "/"); 
        	Tips tips=imageCompressService.compressAndSaveImage(Base64Utils.decode(imageVo.getFileData().substring("data:image/png;base64,".length()).getBytes()),imageVo.getScale(),imageVo.getOutputQuality(),fullFilePath);
        	if(tips.isOk()) {
        		if("1".equals(imageVo.getStoredb())) {
             		this.imageService.insert(i);
             	 } 
        	}else {
        		throw new BizException(tips);
        	}
         	 
             return i;  
         } catch (Exception e) {  
         	logger.error("保存文件出错到"+fullFilePath+"出错");
            throw new BizException("保存文件出错");
         }  
     }else{
     	throw new BizException("文件为空");
     }
 }  
}
