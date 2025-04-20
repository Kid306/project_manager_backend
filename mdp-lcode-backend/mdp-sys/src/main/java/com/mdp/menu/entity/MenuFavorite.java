package  com.mdp.menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author code-gen
 * @since 2024-5-15
 */
@Data
@TableName("menu_favorite")
@ApiModel(description="菜单收藏夹")
public class MenuFavorite  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="菜单名称",allowEmptyValue=true,example="",allowableValues="")
	String name;

	
	@ApiModelProperty(notes="排序",allowEmptyValue=true,example="",allowableValues="")
	String sort;

	
	@ApiModelProperty(notes="云用户机构编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;

	
	@ApiModelProperty(notes="创建人",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;

	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date cdate;

	
	@ApiModelProperty(notes="访问路径,路由则填写路由的path",allowEmptyValue=true,example="",allowableValues="")
	String accUrl;

	
	@ApiModelProperty(notes="菜单图标",allowEmptyValue=true,example="",allowableValues="")
	String icon;

	
	@ApiModelProperty(notes="菜单类型1-路由，2-自定义",allowEmptyValue=true,example="",allowableValues="")
	String stype;

	
	@ApiModelProperty(notes="菜单类型为路由时，填写路由的name",allowEmptyValue=true,example="",allowableValues="")
	String rname;

	/**
	 *主键
	 **/
	public MenuFavorite(String id) {
		this.id = id;
	}
    
    /**
     * 菜单收藏夹
     **/
	public MenuFavorite() {
	}

}