package com.mdp.meta.client.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 组织 com  顶级模块 mdp 大模块 meta  小模块 <br> 
 * 实体 ItemOption所有属性名: <br>
 *	"id","编号","name","名称","relyType","关联类型逗号分隔多个","relyId","关联类型的编号，逗号分隔多个","relyStype","关联子类型逗号分隔多个","relySid","关联子类型对应的编号，逗号分隔多个","color","显示颜色，参考https://element-plus.gitee.io/zh-CN/component/color.html","unTargets","不能跳转到哪些状态,逗号分隔多个","disabled","前台是否可选1-不可选，0或者空可选","itemId","指向meta_item.id";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@TableName("meta_item_option")
@Schema(description="字典选项值")
public class Option implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableIds
	@TableId
	@Schema(description="编号,主键")
	String id;
	
	@Schema(description="名称")
	String name;
	
	@Schema(description="关联类型逗号分隔多个")
	String relyType;
	
	@Schema(description="关联类型的编号，逗号分隔多个")
	String relyId;
	
	@Schema(description="关联子类型逗号分隔多个")
	String relyStype;
	
	@Schema(description="关联子类型对应的编号，逗号分隔多个")
	String relySid;
	
	@Schema(description="显示颜色，参考https://element-plus.gitee.io/zh-CN/component/color.html")
	String color;
	
	@Schema(description="不能跳转到哪些状态,逗号分隔多个")
	String unTargets;

	@Schema(description="能跳转到哪些状态,逗号分隔多个")
	String targets;

	@Schema(description="前台是否可选1-不可选，0或者空可选")
	String disabled;

	@TableIds
	@Schema(description="指向meta_item.id")
	String itemId;

	@Schema(description="排序0-999")
	Integer seqOrder;


	@Schema(description="自定义变量及值")
	String extVals;

	@Schema(description="图标")
	String icon;

	/**
	 *编号
	 **/
	public Option(String id) {
		this.id = id;
	}
    
    /**
     * 字典选项值
     **/
	public Option() {
	}

}