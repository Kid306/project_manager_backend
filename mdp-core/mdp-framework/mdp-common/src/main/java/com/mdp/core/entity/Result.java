package com.mdp.core.entity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.err.BizException;
import com.mdp.core.err.QxException;
import com.mdp.core.utils.LogUtils;
import com.mdp.core.utils.NumberUtil;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;

/**
 *   接口返回数据格式
 * @author cyc
 * @date  2019年10月18日
 */
public class Result extends HashMap implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *总条数，如果分页查询会返回总记录数
	 */
	//Long total;

	/**
	 * 交易状态
	 */
	//LangTips tips;

	/**
	 * 返回数据对象 data
	 */
	//private Object data;
	
	/**
	 * 时间戳
	 */
	//private long timestamp = System.currentTimeMillis();

	public Result() {
	}

	public Result setTips(LangTips tips) {
		super.put("tips",tips);
		return this;
	}
	public Result setTips(boolean isOk,String tipscode,String msg,Object...msgVars){
		setTips(LangTips.msg(isOk,tipscode,msg,msgVars));
		return this;
	}
	public Result setTips(String tipscode,String msg,Object...msgVars){
		if(super.get("tips")==null){
			setTips(LangTips.msg(true,tipscode,msg,msgVars));
			return this;
		}else {
			Tips tips0= (Tips) super.get("tips");
			LangTips tips=LangTips.msg(tips0.isOk(),tipscode,msg,msgVars);
			String reqNo= (String) tips0.get(LogUtils.REQ_NO_NAME);
			String gloNo= (String) tips0.get(LogUtils.GLO_NO_NAME);
			if(!StringUtils.isEmpty(reqNo)) {
				tips.put(LogUtils.REQ_NO_NAME, reqNo);
			}
			if(!StringUtils.isEmpty(gloNo)) {
				tips.put(LogUtils.GLO_NO_NAME, gloNo);
			}
			setTips(tips);
		}
		return this;
	}
	public <T> Result  setData(T data) {
		if(data instanceof IPage){
			IPage page=(IPage) data;
			super.put("total",page.getTotal());
			super.put("data",page.getRecords());
		}else{
			super.put("data",data);
		}
		return this;
	}

	public Result setTotal(Integer total){
		super.put("total",NumberUtil.getLong(total));
		return this;
	}

	public Result setTotal(Long total){
		super.put("total",total);
		return this;
	}

	public static Result build(LangTips tips){
		 return Result.build(null,tips);
	}
	public static <T> Result build(T data,LangTips tips){
		Result result=new Result();
		result.setTips(tips);
		result.setData(data);
		return result;
	}

	public static Result ok() {
		Result r = new Result();
		LangTips tips=LangTips.okMsg("ok","成功");
		r.setTips(tips);
		return r;
	}

	public static Result ok(String msg) {
		Result r = new Result();
		LangTips tips=LangTips.okMsg(null,msg);
		r.setTips(tips);
		return r;
	}
	public static <T> Result ok(String tipscode,String msg,Object...msgVars) {
		Result r = new Result();
		LangTips tips=LangTips.okMsg(tipscode,msg,msgVars);
		r.setTips(tips);
		return r;
	}
	public static  Result error(String msg,Object...msgVars) {
		return error(null,msg,msgVars);
	}
	public static Result error(String tipscode,String msg,Object...msgVars) {
		Result r = new Result();
		LangTips tips=LangTips.errMsg(tipscode,msg,msgVars);
		r.setTips(tips);
		return r;
	}
	public static Result error() {
		Result r = new Result();
		LangTips tips=LangTips.errMsg("error","失败");
		r.setTips(tips);
		return r;
	}

	public static Result error(BizException bizException) {
		Result r = new Result();
		r.setTips(LangTips.fromBizException(bizException));
		return r;
	}
	public static Result error(QxException qxException) {
		Result r = new Result();
		r.setTips(LangTips.fromTips(qxException.getTips()));
		return r;
	}
	public static Result error(Exception exception) {
		return Result.error(exception.getMessage());
	}
	public static Result error(LangTips tips) {
		Result r = new Result();
		r.setTips(tips);
		return r;
	}

	public static void assertIsFalse(Tips tips){
		if(tips.isOk()==false){
			throw new BizException(tips);
		}
	}
	public static void assertIsTrue(Boolean express,String msg){
		if(express){
			throw new BizException(msg);
		}
	}
	public Result put(Object key,Object value){
		super.put(key,value);
		return this;
	}

	public Long getTotal(){
		return NumberUtil.getLong(super.get("total"));
	}

	public <T> T getData(){
		return (T) super.get("data");
	}

	public LangTips getTips(){
		LangTips tips0= (LangTips) super.get("tips");
		if(tips0==null){
			LangTips tips= new LangTips();
			this.setTips(tips);
			return tips;
		}
		return tips0;
	}

}