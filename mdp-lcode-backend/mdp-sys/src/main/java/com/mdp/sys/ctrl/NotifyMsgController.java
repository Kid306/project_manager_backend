package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.ObjectTools;
import com.mdp.msg.client.PushNotifyMsgService;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.NotifyMsg;
import com.mdp.sys.service.NotifyMsgService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.fromMap;
import static com.mdp.core.utils.BaseUtils.toMap;


/**
 * url编制采用rest风格,如对sys_notify_msg 个人消息通知的操作有增删改查,对应的url分别为:<br>
 * 组织 com  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 NotifyMsg 表 sys_notify_msg 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.sys.notifyMsgController")
@RequestMapping(value="/*/sys/notifyMsg")
@Api(tags={"个人消息通知操作接口"})
public class NotifyMsgController {
	
	static Logger logger =LoggerFactory.getLogger(NotifyMsgController.class);
	
	@Autowired
	private NotifyMsgService notifyMsgService;

	@Autowired
	private PushNotifyMsgService push;

	Map<String,Object> fieldsMap = toMap(new NotifyMsg());
 
	
	@ApiOperation( value = "查询个人消息通知信息列表",notes=" ")
	@ApiEntityParams( NotifyMsg.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="objTypes",value="对象类型数组，比如8,9,10",required=false),
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
 	})
	@ApiResponses({
		@ApiResponse(code = 200,response=NotifyMsg.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listNotifyMsg( @ApiIgnore @RequestParam Map<String,Object>  params){
		
		
		
		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<NotifyMsg> qw= QueryTools.initQueryWrapper(NotifyMsg.class,params);

		User user=LoginUtils.getCurrentUserInfo();
		qw.eq("to_userid",user.getUserid());
		List<Map<String,Object>>	notifyMsgList = notifyMsgService.selectListMapByWhere(page,qw,params);
		
		return Result.ok().setData(notifyMsgList).setTotal(page.getTotal());

	}

	@ApiOperation( value = "统计个人未读消息总数",notes=" ")
	@ApiImplicitParams({
			@ApiImplicitParam(name="objTypes",value="对象类型数组，比如8,9,10",required=false),
	})
	@ApiResponses({
			@ApiResponse(code = 200,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/myUnReadMsgNums",method=RequestMethod.GET)
	public Result myUnReadMsgNums( @ApiIgnore Map<String,Object> notifyMsg){
		
		
		
		User user=LoginUtils.getCurrentUserInfo();
		notifyMsg.put("toUserid",user.getUserid());
		Long count=this.notifyMsgService.myUnReadMsgNums(notifyMsg);
		return Result.ok().setData(count);

	}
 

	@ApiOperation( value = "新增一条个人消息通知信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=NotifyMsg.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addNotifyMsg(@RequestBody NotifyMsg notifyMsg) {
		try{
			User user=LoginUtils.getCurrentUserInfo();
			notifyMsg.setBranchId(user.getBranchId());
			notifyMsg.setSendUserid(user.getUserid());
			notifyMsg.setSendUsername(user.getUsername());
			if(ObjectTools.isEmpty(notifyMsg.getToUserid())){
				return Result.error("toUserid-required","接收用户不能为空");
			}
			if(ObjectTools.isEmpty(notifyMsg.getMsg())){
				return Result.error("msg-required","消息不能为空");
			}
			push.pushMsg(BaseUtils.toMap(notifyMsg));
			return Result.ok().setData(notifyMsg);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}

	@ApiOperation( value = "",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=NotifyMsg.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/batchAdd",method=RequestMethod.POST)
	public Result batchAddNotifyMsg(@RequestBody List<NotifyMsg> datas) {
		try{
			User user=LoginUtils.getCurrentUserInfo();
			for (NotifyMsg data : datas) {
				data.setBranchId(user.getBranchId());
				data.setSendUserid(user.getUserid());
				data.setSendUsername(user.getUsername());
				if(ObjectTools.isEmpty(data.getToUserid())){
					return Result.error("toUserid-required","接收用户不能为空");
				}
				if(ObjectTools.isEmpty(data.getMsg())){
					return Result.error("msg-required","消息不能为空");
				}
				push.pushMsg(BaseUtils.toMap(data));
			}
			return Result.ok();

		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}
	}
	@ApiOperation( value = "删除一条个人消息通知信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delNotifyMsg(@RequestBody NotifyMsg notifyMsg){
		
		
		try{
            if(!StringUtils.hasText(notifyMsg.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            NotifyMsg notifyMsgDb = notifyMsgService.selectOneObject(notifyMsg);
            if( notifyMsgDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			notifyMsgService.deleteByPk(notifyMsg);
            return Result.ok();
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}

	@ApiOperation( value = "根据主键修改一条个人消息通知信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=NotifyMsg.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editNotifyMsg(@RequestBody NotifyMsg notifyMsg) {
		
		
		try{
            if(!StringUtils.hasText(notifyMsg.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            NotifyMsg notifyMsgDb = notifyMsgService.selectOneObject(notifyMsg);
            if( notifyMsgDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			notifyMsgService.updateSomeFieldByPk(notifyMsg);
			return Result.ok().setData(notifyMsg);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}

    @ApiOperation( value = "批量修改某些字段",notes="")
    @ApiEntityParams( value = NotifyMsg.class, props={ }, remark = "个人消息通知", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=NotifyMsg.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> notifyMsgMap) {
		
		
		try{
            List<String> ids= (List<String>) notifyMsgMap.get("$pks");
			if(ids==null || ids.size()==0){
				return Result.error("ids-0","ids不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("id");
			for (String fieldName : notifyMsgMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=notifyMsgMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(notifyMsgMap.get(i) )).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			NotifyMsg notifyMsg = fromMap(notifyMsgMap,NotifyMsg.class);
			List<NotifyMsg> notifyMsgsDb=notifyMsgService.selectListByIds(ids);
			if(notifyMsgsDb==null ||notifyMsgsDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<NotifyMsg> can=new ArrayList<>();
			List<NotifyMsg> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (NotifyMsg notifyMsgDb : notifyMsgsDb) {
				Tips tips2 = new Tips("检查通过"); 
				if(!tips2.isOk()){
				    no.add(notifyMsgDb); 
				}else{
					can.add(notifyMsgDb);
				}
			}
			if(can.size()>0){
                notifyMsgMap.put("$pks",can.stream().map(i->i.getId()).collect(Collectors.toList()));
			    notifyMsgService.editSomeFields(notifyMsgMap); 
			}
			List<String> msgs=new ArrayList<>();
			if(can.size()>0){
				msgs.add(String.format("成功更新以下%s条数据",can.size()));
			}
			if(no.size()>0){
				msgs.add(String.format("以下%s个数据无权限更新",no.size()));
			}
			if(can.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining()));
			}else {
				return Result.error(msgs.stream().collect(Collectors.joining()));
			}
			//return Result.ok().setData(xmMenu);
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}
	}

	@ApiOperation( value = "根据主键列表批量删除个人消息通知信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelNotifyMsg(@RequestBody List<NotifyMsg> notifyMsgs) {
		
         
        try{ 
            if(notifyMsgs.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<NotifyMsg> datasDb=notifyMsgService.selectListByIds(notifyMsgs.stream().map(i-> i.getId() ).collect(Collectors.toList()));

            List<NotifyMsg> can=new ArrayList<>();
            List<NotifyMsg> no=new ArrayList<>();
            for (NotifyMsg data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                notifyMsgService.batchDelete(can);
                msgs.add(String.format("成功删除%s条数据.",can.size()));
            }
    
            if(no.size()>0){ 
                msgs.add(String.format("以下%s条数据不能删除.【%s】",no.size(),no.stream().map(i-> i.getId() ).collect(Collectors.joining(","))));
            }
            if(can.size()>0){
                 return Result.ok(msgs.stream().collect(Collectors.joining()));
            }else {
                return Result.error(msgs.stream().collect(Collectors.joining()));
            }
        }catch (BizException e) { 
            logger.error("",e);
            return Result.error(e);
        }catch (Exception e) {
            logger.error("",e);
            return Result.error(e);
        }
	} 

}
