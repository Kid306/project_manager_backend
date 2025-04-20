package com.mdp.meta.client.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 组织 com  顶级模块 mdp 大模块 meta  小模块 <br> 
 * 实体 Item所有属性名: <br>
 *	"id","主键","itemCode","代码，小写,下横线分割，请不要用驼峰命名","itemName","名称","remark","备注","categoryId","分类编号","itemSize","长度","itemType","1-普通文本，2-数字，3-日期，4-单选列表，5-多选列表，6-单文件，7-多文件，8-富文本，9-单图文，10多图文,11-单视频，12-多视频，13单选radio,14多选checkbox","branchId","机构编号","deptid","部门编号","cmenu","是否创建菜单","dvalues","默认值,如果是列表，则存储列表编号，多个逗号分割","dnames","默认名称，如果是列表，则存储列表名称，多个则逗号分割","optionList","item_type=4,5时的选项列表-指向item_option表关联的列表，该字段作废","inputFormat","输入提示","required","是否必须0否1是","seq","排序顺序","tableName","表名","isShow","是否显示0否1是","qx","权限，是否可以0-新增，1-删除，2-编辑，3-查询，多个以逗号分割","extInfos","扩展字段";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@TableName("meta_item")
@Schema(description="数据项定义")
public class Item  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@Schema(description="主键,主键")
	String id;
	
	@Schema(description="代码，小写,下横线分割，请不要用驼峰命名")
	String itemCode;
	
	@Schema(description="名称")
	String itemName;
	
	@Schema(description="备注")
	String remark;
	
	@Schema(description="分类编号")
	String categoryId;
	
	@Schema(description="长度")
	Integer itemSize;
	
	@Schema(description="1-普通文本，2-数字，3-日期，4-单选列表，5-多选列表，6-单文件，7-多文件，8-富文本，9-单图文，10多图文,11-单视频，12-多视频，13单选radio,14多选checkbox")
	String itemType;
	
	@Schema(description="机构编号")
	String branchId;
	
	@Schema(description="部门编号")
	String deptid;
	
	@Schema(description="是否创建菜单")
	String cmenu;
	
	@Schema(description="默认值,如果是列表，则存储列表编号，多个逗号分割")
	String dvalues;
	
	@Schema(description="默认名称，如果是列表，则存储列表名称，多个则逗号分割")
	String dnames;
	
	@Schema(description="item_type=4,5时的选项列表-指向item_option表关联的列表，该字段作废")
	String optionList;
	
	@Schema(description="输入提示")
	String inputFormat;
	
	@Schema(description="是否必须0否1是")
	String required;
	
	@Schema(description="排序顺序")
	Integer seq;
	
	@Schema(description="表名")
	String tableName;
	
	@Schema(description="是否显示0否1是")
	String isShow;
	
	@Schema(description="权限，是否可以0-新增，1-删除，2-编辑，3-查询，多个以逗号分割")
	String qx;
	
	@Schema(description="扩展字段")
	String extInfos;

	/**
	 *主键
	 **/
	public Item(String id) {
		this.id = id;
	}
    
    /**
     * 数据项定义
     **/
	public Item() {
	}

}