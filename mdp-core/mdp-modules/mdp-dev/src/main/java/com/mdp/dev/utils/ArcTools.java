package com.mdp.dev.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class ArcTools {

    public static String filterPath(String path){
        if(path==null){
            return null;
        }else {
            return path.replaceAll("//", "/");
        }
    }

    /**
     * 讲路径加入第一个中
     * @param filter 是否进行替换 //--> /
     * @return
     */
    public static String pathJoin(boolean filter,String ...paths){
        if(paths==null||paths.length==0){
            return "";
        }
        String src0=paths[0];
        for (int i = 1; i < paths.length; i++) {
            String src2=paths[i];
                if(src0.endsWith("/")){
                    if(src2.startsWith("/")){
                        src0=src0+src2.substring(1);
                    }else {
                        src0=src0+src2;
                    }
                }else{
                    if(src2.startsWith("/")){
                        src0= src0+src2;
                    }else {
                        src0= src0+"/"+src2;
                    }
                }
        }


        if(filter){
            return filterPath(src0);
        }else {
            return src0;
        }
    }


    public static Boolean delete(String path) {
        Boolean result = false;
        try {
            File remoteFile = new File(path);
            if (!remoteFile.exists()) {
                return false;
            }
            if (remoteFile.isFile()) {
                result = remoteFile.delete();
            } else {
                FileUtils.deleteDirectory(remoteFile);
                result = true;
            }
        } catch (Exception var4) {
            var4.printStackTrace();
            result = false;
        }

        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(pathJoin(false));
        System.out.println(pathJoin(true,"https://xxx.com","/xxxxxxxx/ffffffffff"));
        System.out.println(pathJoin(true,"https://xxx.com//","xxxxxxxx/ffffffffff"));
        System.out.println(pathJoin(true,"https://xxx.com//","xxxxxxxx/ffffffffff"));
        System.out.println(pathJoin(true,"https://xxx.com//","xxxxxxxx/ffffffffff"));

    }
}
