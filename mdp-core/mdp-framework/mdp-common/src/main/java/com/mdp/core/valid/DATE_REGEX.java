package com.mdp.core.valid;
public enum DATE_REGEX {


    SYMBOL_DOT( "\\."),
    YYYYMM("^\\d{4}-\\d{1,2}$"),//日期正则yyyy-MM
    YYYYMMDD("^\\d{4}-\\d{1,2}-\\d{1,2}$"),//日期正则yyyy-MM-dd
    YYYYMMDDHHMM("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$"),//日期正则yyyy-MM-dd hh:mm
    YYYYMMDDHHMMSS("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$"),//日期正则yyyy-MM-dd hh:mm:ss
    SECOND_DOT_NANOSECOND("^[0-9]{1,}\\.[0-9]{1,9}$"),//Instant日期秒+纳秒
    LONGTIME("^[0-9]{1,}$"),//longtime 毫秒
    YYYYMMDD_T_HHMMSS_Z("^\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}Z$"),//日期正则yyyy-MM-dd'T'HH:mm:ssZ
    YYYYMMDD_T_HHMMSS_SSS_Z("^\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}Z$");//日期正则yyyy-MM-dd'T'HH:mm:ss.SSSZ





    private final String regex;
    DATE_REGEX(String regex){
        this.regex=regex;
    }

    public String getRegex() {
        return regex;
    }
}