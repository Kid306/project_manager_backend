package com.mdp.core.utils;

import java.util.HashSet;
import java.util.Random;  
  
public class TentoN {  
    private static final String[] l = {   
        "2", "2", "2", "3", "4", "5", "6", "7", "8", "9",
        "A", "B", "C", "D", "E", "F", "G", "H", "H", "J",
        "K", "K", "K", "N", "P", "P", "Q", "R", "S", "T",
        "U", "V", "V", "X", "Y", "Z"};
      
    private static int count = 100;  
  
    private static int getCount(){  
        if(count>999)count = 100;  
        return count++;   
    }  
      
    //TentoN(这里是您想转换的数 ,这里是您想转换为多少进制 2-62之间）
    public static String tentoN(long value, int number) {  
        if (number < 10 ) {
            return tentoN(value,10);
        } else if(number > l.length){
            return tentoN(value, l.length);
        }
        //负数处理  
        if (value < 0) {  
            return "-" + tentoN(0 - value, number);  
        }  
        if (value < number) {  
            return l[(int)value];  
        } else {  
            long n = value % (long)number;  
            return (tentoN(value / number, number) + l[(int)n]);  
        }  
    }  
  
    /** 
     * 返回4位随机数 
     * @return 
     */  
    public static Integer getRandom2(){  
        Integer i = new Random().nextInt(9999);  
        while(i<1000)    i=i<<1;  
        return i;  
    }  
      
    public static void main(String[] args) throws InterruptedException {  
        long a = System.currentTimeMillis();  
        HashSet<String> hs = new HashSet<String>();  
//        for(int i=0;i<1000;i++){
//            String s = tentoN((System.currentTimeMillis()-1723866585037L), 62)+tentoN((long)getCount(),62);
//            hs.add(s);
//            System.out.println(System.currentTimeMillis());
//            System.out.println(System.currentTimeMillis()-1723866585037L);
//            System.out.println(s);
//        }
        System.out.println(hs.size());  
         String aa= tentoN(20241230183060300L,62);
         System.out.println("aa->" +aa);
        long b = System.currentTimeMillis();  
        System.out.println("毫秒："+(b-a));  
    }  
} 