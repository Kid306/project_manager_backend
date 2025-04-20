package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.api.CacheHKVService;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.GuardOrder;
import com.mdp.sys.service.GuardOrderService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.mdp.core.utils.BaseUtils.toMap;


/**
 * url编制采用rest风格,如对sys_guard_order 购买服务保障订单表的操作有增删改查,对应的url分别为:<br>
 * 组织 com  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 GuardOrder 表 sys_guard_order 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.sys.guardOrderController")
@RequestMapping(value="/*/sys/guardOrder")
@Api(tags={"购买服务保障订单表操作接口"})
public class GuardOrderController {
	
	static Logger logger =LoggerFactory.getLogger(GuardOrderController.class);
	
	@Autowired
	private GuardOrderService guardOrderService;

	@Autowired
	CacheHKVService cacheHKVService;
	 

	Map<String,Object> fieldsMap = toMap(new GuardOrder());
 
	
	@ApiOperation( value = "查询购买服务保障订单表信息列表",notes=" ")
	@ApiEntityParams( GuardOrder.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
 	})
	@ApiResponses({
		@ApiResponse(code = 200,response=GuardOrder.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listGuardOrder(@ApiIgnore @RequestParam Map<String,Object>  params){
		
		
		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<GuardOrder> qw= QueryTools.initQueryWrapper(GuardOrder.class,params);
		List<Map<String,Object>>	guardOrderList = guardOrderService.selectListMapByWhere(page,qw,params);
		
		return Result.ok().setData(guardOrderList).setTotal(page.getTotal());

	}
	
 
	
	/**
	@ApiOperation( value = "新增一条购买服务保障订单表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=GuardOrder.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addGuardOrder(@RequestBody GuardOrder guardOrder) {
		
		
		try{
		    boolean createPk=false;
			if(!StringUtils.hasText(guardOrder.getId())) {
			    createPk=true;
				guardOrder.setId(guardOrderService.createKey("id"));
			}
			if(createPk==false){
                 if(guardOrderService.selectOneObject(guardOrder) !=null ){
                    return Result.error("pk-exists","编号重复，请修改编号再提交");
                }
            }
			guardOrderService.insert(guardOrder);
			return Result.ok().setData(guardOrder);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}
	*/
	
	/**
	@ApiOperation( value = "删除一条购买服务保障订单表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delGuardOrder(@RequestBody GuardOrder guardOrder){
		
		
		try{
            if(!StringUtils.hasText(guardOrder.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            GuardOrder guardOrderDb = guardOrderService.selectOneObject(guardOrder);
            if( guardOrderDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			guardOrderService.deleteByPk(guardOrder);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}
	 */
	
	/**
	@ApiOperation( value = "根据主键修改一条购买服务保障订单表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=GuardOrder.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editGuardOrder(@RequestBody GuardOrder guardOrder) {
		
		
		try{
            if(!StringUtils.hasText(guardOrder.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            GuardOrder guardOrderDb = guardOrderService.selectOneObject(guardOrder);
            if( guardOrderDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			guardOrderService.updateSomeFieldByPk(guardOrder);
			return Result.ok().setData(guardOrder);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}
	*/

	/**
    @ApiOperation( value = "批量修改某些字段",notes="")
    @ApiEntityParams( value = GuardOrder.class, props={ }, remark = "购买服务保障订单表", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=GuardOrder.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> guardOrderMap) {
		
		
		try{
            List<String> ids= (List<String>) guardOrderMap.get("$pks");
			if(ids==null || ids.size()==0){
				return Result.error("ids-0","ids不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("id");
			for (String fieldName : guardOrderMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=guardOrderMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(guardOrderMap.get(i) )).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			GuardOrder guardOrder = fromMap(guardOrderMap,GuardOrder.class);
			List<GuardOrder> guardOrdersDb=guardOrderService.selectListByIds(ids);
			if(guardOrdersDb==null ||guardOrdersDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<GuardOrder> can=new ArrayList<>();
			List<GuardOrder> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (GuardOrder guardOrderDb : guardOrdersDb) {
				Tips tips2 = new Tips("检查通过"); 
				if(!tips2.isOk()){
				    no.add(guardOrderDb); 
				}else{
					can.add(guardOrderDb);
				}
			}
			if(can.size()>0){
                guardOrderMap.put("$pks",can.stream().map(i->i.getId()).collect(Collectors.toList()));
			    guardOrderService.editSomeFields(guardOrderMap); 
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
	*/

	/**
	@ApiOperation( value = "根据主键列表批量删除购买服务保障订单表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelGuardOrder(@RequestBody List<GuardOrder> guardOrders) {
		
         
        try{ 
            if(guardOrders.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<GuardOrder> datasDb=guardOrderService.selectListByIds(guardOrders.stream().map(i-> i.getId() ).collect(Collectors.toList()));

            List<GuardOrder> can=new ArrayList<>();
            List<GuardOrder> no=new ArrayList<>();
            for (GuardOrder data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                guardOrderService.batchDelete(can);
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
        
        return Result.ok();
	} 
	*/



	@ApiOperation( value = "通过Id获取订单",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/getOrderById",method=RequestMethod.GET)
	public Result getOrderById(String id) {
		
		
		if(!StringUtils.hasText(id)) {
			return Result.error("data-0","订单Id不能为空");
		}
		GuardOrder guardOrder = guardOrderService.selectOneById(id);
		
		return Result.ok().setData( guardOrder);
	}

	@ApiOperation( value = "通过Id获取订单",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/orderPaySuccess",method=RequestMethod.POST)
	public Result orderPaySuccess(@RequestBody GuardOrder order) {
		
		try {
			
			if(!StringUtils.hasText(order.getId())) {
				return Result.error("data-0","订单Id不能为空");
			}
			String flag= (String) this.cacheHKVService.get("pay-notify-success-"+order.getPayId());
			if(!StringUtils.hasText(flag)|| !"1".equals(flag)){
				return Result.error("pay-notify-success-flag-0","验证码错误");
			}
			guardOrderService.orderPaySuccess(order.getId(),order.getPayId(),order.getPrepayId(), order.getTranId(), order.getPayAt(), order.getRemark());
			
			return Result.ok();
		}catch (BizException e) {
			logger.error("",e);
			return Result.error("data-0",e.getMessage());
		} catch (Exception e) {
			logger.error("",e);
			return Result.error("data-0", "付款确认失败");
		}
	}

	@ApiOperation( value = "订单支付取消判断",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/payCancel",method=RequestMethod.POST)
	public Result payCancel(@RequestBody GuardOrder order) {
		
		try {
			
			if(!StringUtils.hasText(order.getId())) {
				return Result.error("data-0","订单Id不能为空");
			}
			String flag= (String) this.cacheHKVService.get("pay-notify-cancel-"+order.getPayId());
			if(!StringUtils.hasText(flag)|| !"1".equals(flag)){
				return Result.error("pay-notify-cancel-flag-0","验证码错误");
			}
			guardOrderService.payCancel(order.getId(),order.getPayId(), order.getRemark());
			
			return Result.ok();
		}catch (BizException e) {
			logger.error("",e);
			return Result.error("data-0",e.getMessage());
		} catch (Exception e) {
			logger.error("",e);
			return Result.error("data-0", "付款取消操作失败");
		}
	}
	@ApiOperation( value = "修改订单的第三方流水号",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/updatePrepayId",method=RequestMethod.POST)
	public Result updatePrepayId(@RequestBody GuardOrder order) {
		
		
		if(!StringUtils.hasText(order.getId())) {
			return Result.error("data-0","订单Id不能为空");
		}
		GuardOrder guardOrder = new GuardOrder();
		guardOrder.setId(order.getId());
		guardOrder.setPayId(order.getPayId());
		guardOrder.setPrepayId(order.getPrepayId());
		guardOrder.setPayTime(new Date());
		guardOrderService.updateSomeFieldByPk(guardOrder);
		
		return Result.ok().setData( guardOrder);
	}
}
