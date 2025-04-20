package com.mdp.core.err;

import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Tips;

/**
 * 统一的业务异常类<br>
 * 在进行任何业务判断时,如果需要通知客户端相关的信息,异常或者提示信息都可以通过抛出此类达到目的<br>
 * 实例如下:<br>
 * 
 * 例如：<br>
 * 
 * public Map querySysAccount(Map p){         <br>
 * 		if(null==p.get("password")){        <br>
 * 			throw new BizException("userDetailsService270","您输入的密码不正确,请重新输入,您还可以再试 %s 次",3); <br>
 * 		}else{<br>
 * 			.......<br>
 * 		}<br>
 * }<br>
 * @author cyc 20150122
 * @since 1.0.0
 * 
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	Tips tips;
	
	Object[] args;
	

	/**
	 * 统一的业务异常类<br>
	 * 在进行任何业务判断时,如果需要通知客户端相关的信息,异常或者提示信息都可以通过抛出此类达到目的<br>
	 * 实例如下:<br>
	 * 
	 * 例如：<br>
	 * 
	 * public Map querySysAccount(Map p){         <br>
	 * 		if(null==p.get("password")){        <br>
	 * 			throw new BizException("您输入的密码不正确,请重新输入,您还可以再试3次"); <br>
	 * 		}else{<br>
	 * 			.......<br>
	 * 		}<br>
	 * }<br>
	 * @param msg 消息 如 您输入的密码不正确,请重新输入,您还可以再试1次.	 
	 */
	public BizException(String msg) {
		super(msg);
		tips = LangTips.errMsg(null,msg);
	}

	public BizException(Tips tips) {
		super(tips.getMsg());
		this.tips=tips;
	}
	
	/**
	 * 统一的业务异常类<br>
	 * 在进行任何业务判断时,如果需要通知客户端相关的信息,异常或者提示信息都可以通过抛出此类达到目的<br>
	 * 实例如下:<br>
	 * 
	 * 例如：<br>
	 * 
	 * public Map querySysAccount(Map p){         <br>
	 * 		if(null==p.get("password")){        <br>
	 * 			throw new BizException("userDetailsService270","您输入的密码不正确,请重新输入,您还可以再试3次"); <br>
	 * 		}else{<br>
	 * 			.......<br>
	 * 		}<br>
	 * }<br>
	 * @param tipscode 技术错误码在编码阶段由开发人员自行定义,一般由类名+代码行数组成.首字母小写,此错误码不会展现给用户
	 * @param msg 消息 如 您输入的密码不正确,请重新输入,您还可以再试1次.	 
	 */
	public BizException(String tipscode,String msg) {
		super(msg);
		tips = LangTips.errMsg(tipscode,msg);
	}

	/**
	 * 统一的业务异常类<br>
	 * 在进行任何业务判断时,如果需要通知客户端相关的信息,异常或者提示信息都可以通过抛出此类达到目的<br>
	 * 实例如下:<br>
	 *
	 * 例如：<br>
	 *
	 * public Map querySysAccount(Map p){         <br>
	 * 		if(null==p.get("password")){        <br>
	 * 			throw new BizException("userDetailsService270","您输入的密码不正确,请重新输入,您还可以再试3次"); <br>
	 * 		}else{<br>
	 * 			.......<br>
	 * 		}<br>
	 * }<br>
	 * @param tipscode 技术错误码在编码阶段由开发人员自行定义,一般由类名+代码行数组成.首字母小写,此错误码不会展现给用户
	 * @param msgTpl String,消息模板 如 您输入的密码不正确,请重新输入,您还可以再试 %s 次.
	 * @param vars Object[] 参数列表
	 */
	public BizException(String tipscode,String msgTpl,Object...vars) {
		super(String.format(msgTpl,vars));
		tips = LangTips.errMsg(tipscode,msgTpl,vars);
		this.args=vars;

	}
	public Tips getTips() {
		return tips;
	}
	public BizException setTips(Tips tips) {
		this.tips = tips;
		return this;
	}

	public Object[] getArgs() {
		return args;
	}

	public BizException setArgs(Object[] args) {
		this.args = args;
		return this;
	}

	@Override
	public String toString() {
		
		return tips.toString();
	}
}
