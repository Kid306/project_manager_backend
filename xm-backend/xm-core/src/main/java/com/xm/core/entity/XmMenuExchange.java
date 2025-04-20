package  com.xm.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author code-gen
 * @since 2023-10-3
 */
@Data
@TableName("xm_menu_exchange")
@ApiModel(description="功能表")
public class XmMenuExchange  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="评论编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="功能编号",allowEmptyValue=true,example="",allowableValues="")
	String menuId;

	
	@ApiModelProperty(notes="功能名称",allowEmptyValue=true,example="",allowableValues="")
	String menuName;

	
	@ApiModelProperty(notes="归属产品编号",allowEmptyValue=true,example="",allowableValues="")
	String productId;

	
	@ApiModelProperty(notes="备注",allowEmptyValue=true,example="",allowableValues="")
	String remark;

	
	@ApiModelProperty(notes="上级评论编号",allowEmptyValue=true,example="",allowableValues="")
	String pid;

	
	@ApiModelProperty(notes="评论人编号",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;

	
	@ApiModelProperty(notes="评论人名称",allowEmptyValue=true,example="",allowableValues="")
	String cusername;

	
	@ApiModelProperty(notes="评论时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;

	
	@ApiModelProperty(notes="评论人所属机构",allowEmptyValue=true,example="",allowableValues="")
	String cbranchId;

	
	@ApiModelProperty(notes="是否采纳0否1采纳",allowEmptyValue=true,example="",allowableValues="")
	String adopt;

	
	@ApiModelProperty(notes="采纳人编号",allowEmptyValue=true,example="",allowableValues="")
	String adoptUserid;

	
	@ApiModelProperty(notes="采纳人名称",allowEmptyValue=true,example="",allowableValues="")
	String adoptUsername;

	
	@ApiModelProperty(notes="采纳时间",allowEmptyValue=true,example="",allowableValues="")
	Date adoptTime;

	
	@ApiModelProperty(notes="关闭该评论0否1是",allowEmptyValue=true,example="",allowableValues="")
	String closed;

	
	@ApiModelProperty(notes="上级用户编号",allowEmptyValue=true,example="",allowableValues="")
	String puserid;

	
	@ApiModelProperty(notes="上级姓名",allowEmptyValue=true,example="",allowableValues="")
	String pusername;

	
	@ApiModelProperty(notes="上级备注",allowEmptyValue=true,example="",allowableValues="")
	String premark;

	
	@ApiModelProperty(notes="本评论需要同步给的人列表,逗号分隔",allowEmptyValue=true,example="",allowableValues="")
	String notifyUserids;

	
	@ApiModelProperty(notes="发送通知渠道inner-email/wxpub/sms/im/out-email等逗号分割",allowEmptyValue=true,example="",allowableValues="")
	String notifyChannels;

	
	@ApiModelProperty(notes="通知用户姓名逗号分隔",allowEmptyValue=true,example="",allowableValues="")
	String notifyUsernames;

	
	@ApiModelProperty(notes="发言人头像地址",allowEmptyValue=true,example="",allowableValues="")
	String cuserHeadImg;

	
	@ApiModelProperty(notes="回复方式1引用2回复",allowEmptyValue=true,example="",allowableValues="")
	String replyType;

	/**
	 *评论编号
	 **/
	public XmMenuExchange(String id) {
		this.id = id;
	}
    
    /**
     * 功能表
     **/
	public XmMenuExchange() {
	}

}