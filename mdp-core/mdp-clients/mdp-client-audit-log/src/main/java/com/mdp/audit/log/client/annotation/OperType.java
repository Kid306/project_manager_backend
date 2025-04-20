package com.mdp.audit.log.client.annotation;

public enum OperType {
    /**
     * 操作类型
     */
    UNKOWN("--"),//未知
    MENUGO("00"),//访问
    SELECT("R"),//查询
    DOWN("DOWN"),//下载
    UPDATE("U"),//更新
    DELETE("D"),//删除
    ADD("C"),//添加
    IMPOR("IM"),//导入
	UPLOAD("UP");//上传


    private String value;

    public String getValue() { return value; }

    public void setValue(String value) { this.value = value; }

    OperType(String s){
        this.value = s;
    }
}
