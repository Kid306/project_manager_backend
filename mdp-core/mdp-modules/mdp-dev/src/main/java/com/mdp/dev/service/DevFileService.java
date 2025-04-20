package com.mdp.dev.service;

import com.mdp.core.service.SequenceService;
import com.mdp.dev.utils.ArcTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

/**
 * @author maimeng-mdp code-gen
 * @since 2023-9-1
 */
@Service
public class DevFileService {
	static Logger logger =LoggerFactory.getLogger(DevFileService.class);

	SequenceService sequenceService=new SequenceService();

	@Value(value="${mdp.arc.file-upload-root-path:}")
	private String arcFileUploadRootPath;

	@Value(value="${mdp.arc.file-download-base-uri:}")
	String arcFileDownloadBaseUri;

	public static String  urlPrefix="arc/file/";


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


	@PostConstruct
	public void checkPath(){
		if(StringUtils.isEmpty(arcFileUploadRootPath)){
			logger.error("请配置档案附件上传根目录 如 mdp.arc.file-upload-root-path=D:/arc/files 参数");
			//throw new RuntimeException("请配置档案附件上传根目录 如 mdp.arc.file-upload-root-path=D:/arc/files 参数");
		}
	}

	public String createKey() {
		return sequenceService.getCommonNo("codegen-{date:yyyyMMddHHmmss}-{rands:4}");
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

