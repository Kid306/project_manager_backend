package com.mdp;

import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.DateUtils;

import java.util.Date;
import java.util.Map;

public class TestBaseUtils {

    public static void main(String[] args) {

        Date now=DateUtils.getNowDate();
        System.out.println(now.getTime());

        Abbc abb=BaseUtils.fromMap(BaseUtils.map("userid","cycsir","startdate","2013-08-21","enddate","2013-08-21 14:20:20"), Abbc.class);
        Map<String,Object> mdate1=BaseUtils.toMap(abb);
        Abbc abb2=BaseUtils.fromMap(BaseUtils.map("userid","cycsir","startdate","2013-08-21 18:33:33","enddate",now.getTime()), Abbc.class);
        Map<String,Object> mdate2=BaseUtils.toMap(abb2);
        Abbc abb3=BaseUtils.fromMap(BaseUtils.map("userid","cycsir","startdate",null,"enddate",now.getTime()+""), Abbc.class);
        Map<String,Object> mdate3=BaseUtils.toMap(abb3);
        Abbc abb4=BaseUtils.fromMap(BaseUtils.map("userid","cycsir","startdate",now.getTime(),"enddate","2013-08-21"), Abbc.class);
        Abbc abb5=BaseUtils.fromMap(BaseUtils.map("userid","cycsir","startdate", now,"enddate","2013-08-21"), Abbc.class);

        Map<String,Object> mdate5=BaseUtils.toMap(abb5);
        System.out.println(abb.toString());
    }


}
