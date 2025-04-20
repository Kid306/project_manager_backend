package com.mdp.dev.dao;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class TestCreateDao {
	
	/**
	1. DebitNoteAction.class.getResource("")

	得到的是当前类FileTest.class文件的URI目录。不包括自己!

	如：file:/D:/eclipse/springTest/WebRoot/WEB-INF/classes/

	atacarnet/src/com/evi/modules/atacarnet/action/

	2. DebitNoteAction.class.getResource("/")

	得到的是当前的classpath的绝对URI路径。

	如：file:/D:/eclipse/springTest/WebRoot/WEB-INF/classes/

	3. Thread.currentThread().getContextClassLoader().getResource("")

	得到的也是当前ClassPath的绝对URI路径

	如：file:/D:/eclipse/springTest/WebRoot/WEB-INF/classes/

	4. DebitNoteAction.class.getClassLoader().getResource("") 或ClassLoader.getSystemResource("")

	得到的也是当前ClassPath的绝对URI路径。

	如：file:/D:/eclipse/springTest/WebRoot/WEB-INF/classes/

	5. 取得服务器相对路径

	System.getProperty("user.dir") 
	例如:E:\apache-tomcat-5.5.16\apache-tomcat-5.5.16\bin

	我推荐使用Thread.currentThread().getContextClassLoader().getResource("")来得到当前的classpath的绝对路径的URI表示法

	6. 取得项目中的绝对路径

	一般用request.getRealPath("/")或request.getRealPath("/config/")

	但现在不提倡使用request.getRealPath("/")了，大家可试用ServletContext.getRealPath("/")方法得到Web应用程序的根目录的绝对路径

	要取得src的文件非常容易，因为src是默认的相对目录，比如您说要取得src下com目录的test.java文件，您只需要这样就够了

	File f = new File(com/test.java); 
	但如果我要取得不在src目录或者WebRoot目录下的文件呢，而是要从src或者WebRoot同级的目录中取呢，比如说doc吧

	我的硬方法是这样实现的：

	String path = this.getServletContext().getRealPath("/");  

	Properties p = new Properties();  

	p.load(new FileInputStream(new File(path.substring(0,(path.lastIndexOf("\\WebRoot") + 1)) + "doc/db.properties")));  

	System.out.println(p.getProperty("driverName"));
	
	**/
	@Test
	public void getProjectDir(){
		try {
		// Users/cyc/Documents/workspace2/mdp/target/test-classes/
		String projectDir=Thread.currentThread().getContextClassLoader().getResource("").getPath();
		System.out.println(projectDir);
		
		//获取得到类全名包括包名 com.qqkj.mdp.mdp.codegen.dao.TestCreateDao
		projectDir=this.getClass().getCanonicalName();
		System.out.println(projectDir);
		
		// 获取得到项目当前根目录 Users/cyc/Documents/workspace2/mdp
		File f=new File("");
		System.out.println(f.getCanonicalPath());
		
		// /Users/cyc/Documents/workspace2/mdp/com/qq
		File f1=new File("com/qq");
		System.out.println(f1.getCanonicalPath());
		
		//获取得到服务器相对目录 /Users/cyc/Documents/workspace2/mdp
		System.out.println(System.getProperty("user.dir") );
		
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
	}
}
