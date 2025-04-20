package com.mdp.form;

import com.alibaba.fastjson.JSON;
import com.mdp.core.entity.DmField;
import com.mdp.core.utils.ObjectTools;
import com.mdp.form.entity.FormField;

import java.util.List;
import java.util.stream.Collectors;

public class FieldUtil {

    public static DmField toDmField(FormField formField){
        DmField dmField=new DmField() ;
        dmField.setColumnName(ObjectTools.camelToUnderline(formField.getField()));
        dmField.setRemarks(formField.getTitle());
        dmField.setColumnSize(50);
        return dmField;
    }

    public static List<DmField> toDmFields(List<FormField> formFields){
        return formFields.stream().map(k->toDmField(k)).collect(Collectors.toList());
    }
    public static List<FormField> toFormFields(String rules) {
        List<FormField> formFields= JSON.parseArray(rules,FormField.class);
        return formFields;
    }
}
