package com.mdp.core.utils;

public class MapUtils {
	
	private static final double EARTH_RADIUS = 6378137;
	private static double rad(double d)
	{
	   return d * Math.PI / 180.0;
	}
	
	/**
	 * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
	 * @param longitude1 经度1
	 * @param latitude1 维度1
	 * @param longitude2 经度2
	 * @param latitude2 维度2
	 * @return
	 */
	public static double getDistance(double longitude1, double latitude1, double longitude2, double latitude2)
	{
	   double radlatitude1 = rad(latitude1);
	   double radlatitude2 = rad(latitude2);
	   double a = radlatitude1 - radlatitude2;
	   double b = rad(longitude1) - rad(longitude2);
	   double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + 
	    Math.cos(radlatitude1)*Math.cos(radlatitude2)*Math.pow(Math.sin(b/2),2)));
	   s = s * EARTH_RADIUS;
	   s = Math.round(s * 10000) / 10000;
	   return s;
	}
	
	   /**
     * @param args
     */
    public static void main(String[] args)
    {
     //自动生成方法存根
        double distance = getDistance(121.491909,31.233234,121.411994,31.206134);
        System.out.println("Distance is:"+distance);
    }

}
