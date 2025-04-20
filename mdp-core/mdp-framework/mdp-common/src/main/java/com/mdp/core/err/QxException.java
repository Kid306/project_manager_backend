package com.mdp.core.err;

/**
 * 统一的权限业务异常类<br>
 * 在进行任何业务判断时,如果需要通知客户端相关的信息,异常或者提示信息都可以通过抛出此类达到目的<br>
 * @author cyc 20150122
 * @since 1.0.0
 * 
 */
public class QxException extends BizException {

	public QxException(String msg) {
		super(msg); 
	}  
	/**
	 * 统一的权限业务异常类<br>
	 * 在进行任何业务判断时,如果需要通知客户端相关的信息,异常或者提示信息都可以通过抛出此类达到目的<br> 
	 * @param qxCode 权限编码、角色编码
	 * @param msg 消息 如 您输入的密码不正确,请重新输入,您还可以再试1次.	 
	 */
	public QxException(String qxCode,String msg) {
		super(qxCode,msg); 
	}
	/**
	 * 统一的权限业务异常类<br>
	 * 在进行任何业务判断时,如果需要通知客户端相关的信息,异常或者提示信息都可以通过抛出此类达到目的<br> 
	 * @param qxCode  权限编码、角色编码
	 * @param resourceType 错误字段. 告诉客户端发生错误的具体字段信息,方便客户端进行友好提示
	 * @param msg 消息 如 您输入的密码不正确,请重新输入,您还可以再试1次.	 
	 */
	public QxException(String qxCode,String resourceType,String msg) {
		super(qxCode,msg);
		this.tips.put("field",resourceType);
	}
	
}
