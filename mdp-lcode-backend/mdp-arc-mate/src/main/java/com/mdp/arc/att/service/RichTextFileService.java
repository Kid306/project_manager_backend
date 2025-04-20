package com.mdp.arc.att.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
@Slf4j
@Service
public class RichTextFileService   {
	
	
    public void writeStringToFile(String filePath,String context) {
        FileWriter fw=null;
        BufferedWriter bw=null;
        try {
        	File file=new File(filePath);
            
            File fileParent = file.getParentFile(); 
            if(!fileParent.exists()){ 
             fileParent.mkdirs(); 
            } 
            if(!file.exists())  file.createNewFile();
            fw = new FileWriter(filePath, false);
            bw = new BufferedWriter(fw);
            bw.write(context);// 往已有的文件上添加字符串 

        } catch (Exception e) {
             log.error("",e);
        }finally {
            try {
               if(bw!=null) bw.close();
               if(fw!=null) fw.close();
            }catch (Exception e){

            }

        }
    }
	
    public void delFile(String filePath) {
        try {
        	File file=new File(filePath);
        	if(file.exists() && file.isFile()) {
        		file.delete();
        	}
        	return;
             
        } catch (Exception e) {
             
        }
    } 
	

	
	 	
}