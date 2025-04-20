package com.mdp.mq.sp;

public class SubjectNameTools {
		
	public static String getSubjectName(String prefix,String orginName,int maxSubject) {
		return prefix+ Math.abs(orginName.hashCode())%maxSubject+"" ;
		
	}
}
