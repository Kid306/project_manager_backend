package com.mdp.arc.img.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.arc.ArcTools;
import com.mdp.arc.img.entity.Image;
import com.mdp.arc.img.mapper.ImageMapper;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.ObjectTools;
import com.mdp.meta.client.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.mdp  顶级模块 arc 大模块 img 小模块 <br>
 * 实体 Image 表 arc_image 当前主键(包括多主键): id; 
 ***/
@Service
@DS("arc-ds")
public class ImageService extends BaseService<ImageMapper,Image> {
	static Logger logger =LoggerFactory.getLogger(ImageService.class);



	@Autowired
	public ItemService itemService;

	@Value(value="${mdp.arc.image-upload-root-path:}")
	public String arcImageUploadRootPath;

	@Value(value="${mdp.arc.image-download-base-uri:}")
	String arcImageDownloadBaseUri;

	public static String  urlPrefix="mdp/arc/img/image/";



	public static final boolean isWindows;
	public static final String splash;
	public static final String root;

	static {
		if (System.getProperty("os.name") != null && System.getProperty("os.name").toLowerCase().contains("windows")) {
			isWindows = true;
			splash = "\\";
			root="D:\\arc\\images\\";
		} else {
			isWindows = false;
			splash = "/";
			root="/arc/images/";
		}
	}

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
	public List<Map<String, Object>> selectTagsListMap(Map<String, Object> map) {
		return baseMapper.selectTagsListMap(map);
	}

	public void insertImageInfoBytag(Image image){
		this.insert( image);
	}



	/**
	 *
	 * @param originalFilename
	 * @param fileName
	 * @param categoryId
	 * @param remark
	 * @param deptid
	 * @param size
	 * @return
	 */
	public Image genImageInfo(String requestURL,String id,String originalFilename,String fileName,String tag,String categoryId,String remark,String deptid,String branchId,Long size,String fixdName){
		Image image = new Image();
		if(ObjectTools.isNotEmpty(id)){
			image.setId(id);
		}else {
			image.setId(this.createKey("id"));
		}

		if(StringUtils.isEmpty(branchId)||branchId.indexOf("undefined")>=0||branchId.indexOf("null")>=0) {
			branchId="";
		}
		if(StringUtils.isEmpty(categoryId)||categoryId.indexOf("undefined")>=0||categoryId.indexOf("null")>=0) {
			categoryId="";
		}
		String relativePath= ArcTools.pathJoin(true,branchId,categoryId);

		image.setRelativePath(relativePath);
		image.setRootPath(arcImageUploadRootPath);	//先保存image，待会打印查看

		if(StringUtils.isEmpty(fileName)) {
			if(originalFilename==null || "".equals(originalFilename)) {
				image.setName(image.getId());
			} else if(originalFilename.lastIndexOf(".")>0) {
				image.setName(originalFilename.substring(0,originalFilename.lastIndexOf(".")));
			}else {
				image.setName(originalFilename);
			}
		}else {
			if(fileName.lastIndexOf(".")>0) {
				image.setName(fileName.substring(0,fileName.lastIndexOf(".")));
			}else {
				image.setName(fileName);
			}
		}
		if(StringUtils.hasText(fixdName)){
			image.setStorageName(fixdName);
		}else{
			image.setStorageName(image.getId());
		}
		String fileSuffix="";
		if(ObjectTools.isNotEmpty(originalFilename) && originalFilename.contains(".")){
			fileSuffix=originalFilename.substring(originalFilename.lastIndexOf("."));
		}
		image.setFileSuffix(fileSuffix);
		String url2=ArcTools.pathJoin(true,urlPrefix,relativePath,image.getStorageName()+fileSuffix);


		String prePath=this.getPreUrlPathByUploadUrl(requestURL);
		image.setUrl(ArcTools.pathJoin(false,prePath,url2));
		image.setFileSize(BigDecimal.valueOf(size));
		image.setCreateDate(new Date());
		image.setRemark(remark);
		image.setUrlPrefix(urlPrefix);
		image.setCategoryId(categoryId);
		image.setDeptid(deptid);
		image.setBranchId(branchId);
		image.setTag(tag);
		return image;
	}

	public String getPreUrlPathByUploadUrl(String requestURL) {
		if(StringUtils.hasText(this.arcImageDownloadBaseUri)){
			return this.arcImageDownloadBaseUri;
		}else{
			int indexOf=requestURL.indexOf(urlPrefix);
			return  requestURL.substring(0,indexOf);
		}
	}

	public String getFullFilePathByUrl(String requestURL) {

		int indexOf=requestURL.indexOf(urlPrefix);
		return ArcTools.pathJoin(true,arcImageUploadRootPath,requestURL.substring(indexOf+urlPrefix.length()));
	}

	public static void main(String[] args) {
		String filePath="/aaaa/bbb/ccc/////ddd/xx/ddd///xxx/fff";
		String filePath2=filePath.replaceAll("[/]{2,}+", "/");
		System.out.println(filePath2);
	}
}

