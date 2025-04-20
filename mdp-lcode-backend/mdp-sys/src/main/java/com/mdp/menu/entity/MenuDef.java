package  com.mdp.menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织 com  顶级模块 mdp 大模块 menu  小模块 <br> 
 * 实体 MenuDef所有属性名: <br>
 *	"id","菜单编号","pid","上级菜单编号","accUrl","访问路径","msort","菜单顺序","mcate","菜单大类-指向menu_module.mcate","icon","菜单图标","mname","菜单名称","rpath","路由PATH","rid","路由编号","isShow","是否显示","menuType","菜单类型1菜单2按钮","operQxId","绑定的操作权限编号","supVers","支持版本0-免费版可用，1-企业版。假如企业属于企业版，则可以适用免费版和企业版的功能。假如企业为免费版，则只可以使用免费版功能","moduleId","模块编号-指向menu_module.id","arole","是否所有角色可用，0-否，1-不区分角色均可以用","qxType","一般按钮才需要区分权限类型c-新增，r-读，u-更新，d-删除","apiRules","对应后台的url地址，支持ant表达式匹配，用于后台接口调用时权限控制,多个逗号分隔,多个为or关系";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@TableName("menu_def")
@ApiModel(description="前端功能菜单表")
public class MenuDef  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="菜单编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
	
	@ApiModelProperty(notes="上级菜单编号",allowEmptyValue=true,example="",allowableValues="")
	String pid;
	
	@ApiModelProperty(notes="访问路径",allowEmptyValue=true,example="",allowableValues="")
	String accUrl;
	
	@ApiModelProperty(notes="菜单顺序",allowEmptyValue=true,example="",allowableValues="")
	String msort;
	
	@ApiModelProperty(notes="菜单大类-指向menu_module.mcate",allowEmptyValue=true,example="",allowableValues="")
	String mcate;
	
	@ApiModelProperty(notes="菜单图标",allowEmptyValue=true,example="",allowableValues="")
	String icon;
	
	@ApiModelProperty(notes="菜单名称",allowEmptyValue=true,example="",allowableValues="")
	String mname;
	
	@ApiModelProperty(notes="路由PATH",allowEmptyValue=true,example="",allowableValues="")
	String rpath;
	
	@ApiModelProperty(notes="路由编号",allowEmptyValue=true,example="",allowableValues="")
	String rid;
	
	@ApiModelProperty(notes="是否显示",allowEmptyValue=true,example="",allowableValues="")
	String isShow;
	
	@ApiModelProperty(notes="菜单类型1菜单2按钮",allowEmptyValue=true,example="",allowableValues="")
	String menuType;
	
	@ApiModelProperty(notes="绑定的操作权限编号",allowEmptyValue=true,example="",allowableValues="")
	String operQxId;
	
	@ApiModelProperty(notes="支持版本0-免费版可用，1-企业版。假如企业属于企业版，则可以适用免费版和企业版的功能。假如企业为免费版，则只可以使用免费版功能",allowEmptyValue=true,example="",allowableValues="")
	String supVers;
	
	@ApiModelProperty(notes="模块编号-指向menu_module.id",allowEmptyValue=true,example="",allowableValues="")
	String moduleId;
	
	@ApiModelProperty(notes="是否所有角色可用，0-否，1-不区分角色均可以用",allowEmptyValue=true,example="",allowableValues="")
	String arole;
	
	@ApiModelProperty(notes="一般按钮才需要区分权限类型c-新增，r-读，u-更新，d-删除",allowEmptyValue=true,example="",allowableValues="")
	String qxType;
	
	@ApiModelProperty(notes="对应后台的url地址，支持ant表达式匹配，用于后台接口调用时权限控制,多个逗号分隔,多个为or关系",allowEmptyValue=true,example="",allowableValues="")
	String apiRules;

	@ApiModelProperty(notes="菜单层级0-5级",allowEmptyValue=true,example="",allowableValues="")
	String lvl;

	/**
	 *菜单编号
	 **/
	public MenuDef(String id) {
		this.id = id;
	}
    
    /**
     * 前端功能菜单表
     **/
	public MenuDef() {
	}

}