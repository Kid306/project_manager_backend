package com.mdp.core.config;

import com.mdp.core.utils.DateFormatUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;

/**
 * 定义全局Instant日期类型转换器
* @author chenyc
* @date 2021-03-20
* @since 1.0
*/
@Component
public class InstantConverter implements Converter<String,Instant> {

    @Override
    public Instant convert(String source) {
        String dateString = source.trim();
        if(StringUtils.isNotBlank(dateString)){
            Date pareDate;
            try {
                pareDate = DateFormatUtil.pareDate(dateString);
                if(null != pareDate){
                    return pareDate.toInstant();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}