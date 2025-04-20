package com.mdp.core.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Date;

public class MdpDateDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {


        String date = jsonParser.getText();
        if(date==null || date.trim().length()==0){
            return null;
        }
        // 处理数据在这里
        try {
             return DateFormatUtil.pareDate(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
