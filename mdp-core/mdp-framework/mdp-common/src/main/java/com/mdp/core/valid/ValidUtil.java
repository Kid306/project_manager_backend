package com.mdp.core.valid;

import com.mdp.core.entity.LangTips;
import com.mdp.core.err.BizException;
import com.mdp.core.utils.NumberUtil;
import com.mdp.core.utils.ObjectTools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidUtil {

    /**
     * 必填检测
     * @param fieldId
     * @param fieldTitle
     * @param val
     */
    public static void isRequired(String fieldId,String fieldTitle,Object val){
        if(ObjectTools.isEmpty(val)){
            throw new BizException(LangTips.errMsg("required","【%s】不能为空",fieldTitle).setFieldName(fieldId));
        }
    }

    /**
     * 最大长度检测
     * @param fieldId
     * @param fieldTitle
     * @param val
     * @param maxLength
     */
    public static void maxLength(String fieldId,String fieldTitle,Object val,int maxLength){
        if(!ObjectTools.isEmpty(val)){
            if(val.toString().length()>maxLength){
                throw new BizException(LangTips.errMsg("gt-max-length","【%s】长度不能超过%s",fieldTitle,maxLength).setFieldName(fieldId));
            }
        }
    }

    /**
     * 最小长度检测
     * @param fieldId
     * @param fieldTitle
     * @param val
     * @param minLength
     */
    public static void minLength(String fieldId,String fieldTitle,Object val,int minLength){
        if(!ObjectTools.isEmpty(val)){
            if(val.toString().length()<minLength){
                throw new BizException(LangTips.errMsg("lt-min-length","【%s】长度不能小于%s",fieldTitle,minLength).setFieldName(fieldId));
            }
        }
    }

    /**
     * 最小数字检测
     * @param fieldId
     * @param fieldTitle
     * @param val
     * @param min
     */
    public static void min(String fieldId,String fieldTitle,Object val,long min){
        if(!ObjectTools.isEmpty(val)){
            if(NumberUtil.getLong(val) <min){
                throw new BizException(LangTips.errMsg("lt-min-num","【%s】不能小于%s",fieldTitle,min).setFieldName(fieldId));
            }
        }
    }

    /**
     * 最大数字检测
     * @param fieldId
     * @param fieldTitle
     * @param val
     * @param max
     */
    public static void max(String fieldId,String fieldTitle,Object val,long max){
        if(!ObjectTools.isEmpty(val)){
            if(NumberUtil.getLong(val) >max){
                throw new BizException(LangTips.errMsg("gt-max-num","【%s】不能大于%s",fieldTitle,max).setFieldName(fieldId));
            }
        }
    }


    /**
     * 邮箱账号检测
     * @param fieldId
     * @param fieldTitle
     * @param email
     */
    public static void isEmail(String fieldId,String fieldTitle,String email){
        if(!ObjectTools.isEmpty(email)){
            // ^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$
            String regex="^[a-zA-Z0-9_]+@[a-zA-Z0-9]+\\.[a-z]{2,3}$";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher=pattern.matcher(email);
            if(!matcher.matches()){
                throw new BizException(LangTips.errMsg("email-format-err","【%s】邮件格式不正确",fieldTitle).setFieldName(fieldId));
            }
        }
    }

    /**
     * 汉字检测
     * @param fieldId
     * @param fieldTitle
     * @param val
     */
    public static void isChinese(String fieldId,String fieldTitle,String val){
        if(!ObjectTools.isEmpty(val)){
             String regex="^[\\u4e00-\\u9fa5]{0,}$";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher=pattern.matcher(val);
            if(!matcher.matches()){
                throw new BizException(LangTips.errMsg("chinese-err","【%s】必须为汉字",fieldTitle).setFieldName(fieldId));
            }
        }
    }

    /**
     * 英文和数字检测
     * @param fieldId
     * @param fieldTitle
     * @param val
     */
    public static void isEnglishAndNumber(String fieldId,String fieldTitle,String val){
        if(!ObjectTools.isEmpty(val)){
            String regex="^[A-Za-z0-9]+$";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher=pattern.matcher(val);
            if(!matcher.matches()){
                throw new BizException(LangTips.errMsg("en-and-num-err","【%s】必须为英文和汉字组合",fieldTitle).setFieldName(fieldId));
            }
        }
    }
    /**
     * 域名检测
     * @param fieldId
     * @param fieldTitle
     * @param val
     */
    public static void isWww(String fieldId,String fieldTitle,String val){
        if(!ObjectTools.isEmpty(val)){
            String regex="^[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(/.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+/.?";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher=pattern.matcher(val);
            if(!matcher.matches()){
                throw new BizException(LangTips.errMsg("www-err","【%s】域名格式不正确",fieldTitle).setFieldName(fieldId));
            }
        }
    }

    /**
     * url检测
     * @param fieldId
     * @param fieldTitle
     * @param val
     */
    public static void isUrl(String fieldId,String fieldTitle,String val){
        if(!ObjectTools.isEmpty(val)){
            String regex="[a-zA-z]+://";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher=pattern.matcher(val);
            if(!matcher.matches()){
                throw new BizException(LangTips.errMsg("www-err","【%s】url格式不正确",fieldTitle).setFieldName(fieldId));
            }
        }
    }

    /**
     * 手机号检测
     * @param fieldId
     * @param fieldTitle
     * @param val
     */
    public static void isPhoneno(String fieldId,String fieldTitle,String val){
        if(!ObjectTools.isEmpty(val)){
            String regex="^1[3-9]\\d{9}$";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher=pattern.matcher(val);
            if(!matcher.matches()){
                throw new BizException(LangTips.errMsg("www-err","【%s】手机号码格式不正确",fieldTitle).setFieldName(fieldId));
            }
        }
    }
    /**
     * 帐号是否合法(允许5-16字节，允许字母数字下划线)
     * @param fieldId
     * @param fieldTitle
     * @param val
     */
    public static void isAccount(String fieldId,String fieldTitle,String val,int min,int max){
        if(!ObjectTools.isEmpty(val)){
            String regex="^[a-zA-Z][a-zA-Z0-9_]{"+min+","+max+"}$";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher=pattern.matcher(val);
            if(!matcher.matches()){
                throw new BizException(LangTips.errMsg("account-format-err","【%s】账号格式不正确，长度在%s~%s之间，只能包含字母、数字和下划线",fieldTitle,min,max).setFieldName(fieldId));
            }
        }
    }
    /**
     * 密码是否合法(允许5-16字节，允许字母数字下划线)
     * @param fieldId
     * @param fieldTitle
     * @param val
     */
    public static void isPassword(String fieldId,String fieldTitle,String val,int min,int max){
        if(!ObjectTools.isEmpty(val)){
            String regex="^[a-zA-Z][a-zA-Z0-9_]{“+min+”,"+max+"}$";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher=pattern.matcher(val);
            if(!matcher.matches()){
                throw new BizException(LangTips.errMsg("password-format-err","【%s】密码格式不正确，长度在%s~%s之间，只能包含字母、数字和下划线",fieldTitle,min,max).setFieldName(fieldId));
            }
        }
    }
    /**
     * 强密码(必须包含大小写字母和数字的组合，不能使用特殊字符，长度在8-10之间)
     * @param fieldId
     * @param fieldTitle
     * @param val
     */
    public static void isPasswordStrong(String fieldId,String fieldTitle,String val,int min,int max){
        if(!ObjectTools.isEmpty(val)){
            String regex="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{“+min+”,"+max+"}$";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher=pattern.matcher(val);
            if(!matcher.matches()){
                throw new BizException(LangTips.errMsg("password-format-err","【%s】密码格式不正确，长度在%s~%s之间，必须包含大小写字母和数字的组合，不能使用特殊字符",fieldTitle,min,max).setFieldName(fieldId));
            }
        }
    }
    /**
     * 日期检验
     * @param fieldId
     * @param fieldTitle
     * @param val
     * @param regex 表达式
     */
    public static void isDate(String fieldId,String fieldTitle,String val,DATE_REGEX regex){
        if(!ObjectTools.isEmpty(val)){
            Pattern pattern=Pattern.compile(regex.getRegex());
            Matcher matcher=pattern.matcher(val);
            if(!matcher.matches()){
                throw new BizException(LangTips.errMsg("date-format-err","【%s】日期/时间格式不正确，格式必须为【%s】",fieldTitle,regex.getRegex()).setFieldName(fieldId));
            }
        }
    }
    /**
     * 金额检验
     * @param fieldId
     * @param fieldTitle
     * @param val
     */
    public static void isMoney(String fieldId,String fieldTitle,String val){
        if(!ObjectTools.isEmpty(val)){
            Pattern pattern=Pattern.compile("^(\\-)?(\\d+)(,\\d{3})*(\\.\\d{1,2})?$");
            Matcher matcher=pattern.matcher(val);
            if(!matcher.matches()){
                throw new BizException(LangTips.errMsg("money-err","【%s】金额格式不正确",fieldTitle).setFieldName(fieldId).setFieldName(fieldId));
            }
        }
    }
    /**
     * ip检验
     * @param fieldId
     * @param fieldTitle
     * @param val
     */
    public static void isIp(String fieldId,String fieldTitle,String val){
        if(!ObjectTools.isEmpty(val)){
            Pattern pattern=Pattern.compile("\\d+\\.\\d+\\.\\d+\\.\\d+");
            Matcher matcher=pattern.matcher(val);
            if(!matcher.matches()){
                throw new BizException(LangTips.errMsg("ip-err","【%s】ip格式不正确",fieldTitle).setFieldName(fieldId));
            }
        }
    }
    /**
     * 首尾空白检验
     * @param fieldId
     * @param fieldTitle
     * @param val
     */
    public static void isWrapBlank(String fieldId,String fieldTitle,String val){
        if(!ObjectTools.isEmpty(val)){
            Pattern pattern=Pattern.compile("^\\s*|\\s*$");
            Matcher matcher=pattern.matcher(val);
            if(!matcher.matches()){
                throw new BizException(LangTips.errMsg("blank-err","【%s】首尾不能包含空白",fieldTitle).setFieldName(fieldId));
            }
        }
    }
    /**
     * 空白行检测
     * @param fieldId
     * @param fieldTitle
     * @param val
     */
    public static void isBlankRow(String fieldId,String fieldTitle,String val){
        if(!ObjectTools.isEmpty(val)){
            Pattern pattern=Pattern.compile("\\n\\s*\\r");
            Matcher matcher=pattern.matcher(val);
            if(!matcher.matches()){
                throw new BizException(LangTips.errMsg("blank-err","【%s】不能包含空白行",fieldTitle).setFieldName(fieldId));
            }
        }
    }
    /**
     * 正数、负数、和小数
     * @param fieldId
     * @param fieldTitle
     * @param val
     */
    public static void isNumber(String fieldId,String fieldTitle,String val){
        if(!ObjectTools.isEmpty(val)){
            Pattern pattern=Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$");
            Matcher matcher=pattern.matcher(val);
            if(!matcher.matches()){
                throw new BizException(LangTips.errMsg("must-number","【%s】只能是正数、负数、和小数",fieldTitle).setFieldName(fieldId));
            }
        }
    }

    /**
     * 有n位小数的正实数
     * @param fieldId
     * @param fieldTitle
     * @param val
     * @param length 几位小数
     */
    public static void isNumberLength(String fieldId,String fieldTitle,String val,int length){
        if(!ObjectTools.isEmpty(val)){
            Pattern pattern=Pattern.compile("^[0-9]+(.[0-9]{"+length+"})?$");
            Matcher matcher=pattern.matcher(val);
            if(!matcher.matches()){
                throw new BizException(LangTips.errMsg("must-number","【%s】只能是有%s位小数的正实数",fieldTitle,length).setFieldName(fieldId));
            }
        }
    }

    /**
     * n位整数
     * @param fieldId
     * @param fieldTitle
     * @param val
     * @param length 几位整数
     */
    public static void isInteger(String fieldId,String fieldTitle,String val,int length){
        if(!ObjectTools.isEmpty(val)){
            Pattern pattern=Pattern.compile("^\\d{"+length+"}$");
            Matcher matcher=pattern.matcher(val);
            if(!matcher.matches()){
                throw new BizException(LangTips.errMsg("must-integer","【%s】只能是有%s位的正整数数",fieldTitle,length).setFieldName(fieldId));
            }
        }
    }

    /**
     * 是否为字母开头，数字字母下横线组合,适合用于表命等
     * @param fieldId
     * @param fieldTitle
     * @param val
     */
    public static void  isLetterNumberLine(String fieldId,String fieldTitle,String val){
        if(!ObjectTools.isEmpty(val)){
            Pattern pattern=Pattern.compile("^[A-Za-z][A-Za-z0-9_]{0,}$");
            Matcher matcher=pattern.matcher(val);
            if(!matcher.matches()){
                throw new BizException(LangTips.errMsg("letter-number-line","【%s】必须为字母开头，字母数字下横线任意组合",fieldTitle).setFieldName(fieldId));
            }
        }
    }

    /**
     * 是否全部为字母
     * @param fieldId
     * @param fieldTitle
     * @param val
     */
    public static void  isLetter(String fieldId,String fieldTitle,String val){
        if(!ObjectTools.isEmpty(val)){
            Pattern pattern=Pattern.compile("^[A-Za-z]{1,}$");
            Matcher matcher=pattern.matcher(val);
            if(!matcher.matches()){
                throw new BizException(LangTips.errMsg("letter","【%s】必须全部为字母",fieldTitle).setFieldName(fieldId));
            }
        }
    }

    public static void main(String[] args) {
        //isAccount("x","xxxx","dddddddddxxxx",1,9);
        //isChinese("x","xxx","dddddddddxxxx");
        //isIp("x","xxx","127.0.0.1");
        //isNumber("x","xxx","123.999");
        //isRequired("x","xxx","");
        //isInteger("x","xx","99999",5);
        //isEmail("x","xx","yyyy@111.com");
        //isPhoneno("x","xx","13810337178");
        //isDate("x","xx","2028-01-19",DATE_REGEX.YYYYMMDD);
        //isMoney("x","xx","13810x337178");

        //isLetterNumberLine("x","xx","123456");

        //isLetterNumberLine("x","A_123456","A123456");

        //isLetterNumberLine("x","a123456","a123456");

        //isLetterNumberLine("x","$123456","$123456");

        //isLetterNumberLine("x","123b3-456","123b3-456");


        //isLetterNumberLine("x","1#23b3-456","1#23b3-456");
        isLetterNumberLine("x","sys_user","sys_user");
        isLetterNumberLine("x","T_sys_user","T_sys_user");
        //isLetterNumberLine("x","T-sys_user","T-sys_user");
        //isLetterNumberLine("x","T0sys#{}_user","T0sys#{}_user");
        isLetter("x","Tsysuser","Tsysuser");
        isLetter("x","T0sysuser","T0sysuser");
    }
}
