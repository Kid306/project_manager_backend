package com.mdp.dev.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CodeGenVo {
    @Schema(description="数据库拥有者")
    String dbOwner;
    @Schema(description="java工程目录")
    String serviceProjectPath;
    @Schema(description="页面工程目录")
    String viewProjectPath;
    @Schema(description="代码生成的存放包(java package,页面目录也会根据该包进行转换成目录)")
    String javaPackage;
    @Schema(description="需要过滤掉的一些字母，被过滤掉的字母将不会出现在目录及文件名中")
    String pathFilter;
    @Schema(description="数据库表名需要过滤掉的字母串，被过滤的字母串将不会出现在文件名中")
    String ignoePrefixs;
    @Schema(description="如果同目录下存在相同文件，是否覆盖")
    Boolean forceOveride;
    @Schema(description="是否打印@TableField标记到 实体类的属性上，一般不需要，只有数据库字段命名及其不规范时需要")
    Boolean printTableField;
    @Schema(description="需要生成代码的表名列表,逗号分割多个")
    String tableNames;

}
