package com.mdp.mo.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.api.CacheHKVService;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.NumberUtil;
import com.mdp.mo.entity.MoOrder;
import com.mdp.mo.service.MoOrderService;
import com.mdp.qx.HasRole;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.toMap;


/**
 * url编制采用rest风格,如对mo_order mo_order的操作有增删改查,对应的url分别为:<br>
 *  新增: mo/moOrder/add <br>
 *  查询: mo/moOrder/list<br>
 *  模糊查询: mo/moOrder/listKey<br>
 *  修改: mo/moOrder/edit <br>
 *  删除: mo/moOrder/del<br>
 *  批量删除: mo/moOrder/batchDel<br>
 * 组织 com  顶级模块 mdp 大模块 mo 小模块 <br>
 * 实体 MoOrder 表 mo_order 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.mo.moOrderController")
@RequestMapping(value="/*/mo/moOrder")
@Api(tags={"mo_order操作接口"})
public class MoOrderController {
	
	static Logger logger =LoggerFactory.getLogger(MoOrderController.class);
	
	@Autowired
	private MoOrderService moOrderService;

	@Autowired
	CacheHKVService cacheHKVService;
	 

	Map<String,Object> fieldsMap = toMap(new MoOrder());
 
	
	@ApiOperation( value = "查询mo_order信息列表",notes=" ") 
	@ApiResponses({
		@ApiResponse(code = 200,response=MoOrder.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listMoOrder( @ApiIgnore @RequestParam Map<String,Object>  moOrder){
		
		
		
		IPage page=QueryTools.initPage(moOrder);
		QueryWrapper<MoOrder> qw= QueryTools.initQueryWrapper(MoOrder.class,moOrder);
		User user=LoginUtils.getCurrentUserInfo();
		if(!LoginUtils.isSuperAdmin()  && !LoginUtils.hasAnyRoles("platformAdmin")){
			qw.eq("obranch_id",user.getBranchId());
		}
		List<Map<String,Object>>	moOrderList = moOrderService.selectListMapByWhere(page,qw,moOrder);
		return Result.ok().setData(moOrderList).setTotal(page.getTotal());
	}
	
 

	@ApiOperation( value = "新增一条mo_order信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=MoOrder.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addMoOrder(@RequestBody MoOrder moOrder) {
		
		
		try{
		    boolean createPk=false;
			if(!StringUtils.hasText(moOrder.getId())) {
			    createPk=true;
				moOrder.setId(moOrderService.createKey("id"));
			}
			if(createPk==false){
                 if(moOrderService.selectOneObject(moOrder) !=null ){
                    return Result.error("pk-exists","编号重复，请修改编号再提交");
                }
            }
			moOrder.setCtime(new Date());
			moOrderService.insert(moOrder);
			return Result.ok().setData(moOrder);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}   
	}

	@ApiOperation( value = "删除一条mo_order信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delMoOrder(@RequestBody MoOrder moOrder){
		
		
		try{
            if(!StringUtils.hasText(moOrder.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            MoOrder moOrderDb = moOrderService.getById(moOrder.getId());
            if( moOrderDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
            User user=LoginUtils.getCurrentUserInfo();
            if(moOrderDb.getObranchId().equals(user.getBranchId())){
				return Result.error("no-qx-del","无权限删除");
			}
            String[] status={"0","1","5","6"};
			if(Arrays.stream(status).filter(k->k.equals(moOrderDb.getStatus())).findAny().isPresent()){
				return Result.error("no-qx-del-curr-status","当前状态的订单不允许删除");
			}
			moOrderService.deleteByPk(moOrder);
            return Result.ok();
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}

	@ApiOperation( value = "根据主键修改一条mo_order信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=MoOrder.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole(roles={"superAdmin","platformAdmin"})
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editMoOrder(@RequestBody MoOrder moOrder) {
		
		
		try{
            if(!StringUtils.hasText(moOrder.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            MoOrder moOrderDb = moOrderService.selectOneObject(moOrder);
            if( moOrderDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			moOrderService.updateSomeFieldByPk(moOrder);
			return Result.ok().setData(moOrder);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}   
	}

    @ApiOperation( value = "批量修改某些字段",notes="")
	@ApiResponses({
			@ApiResponse(code = 200,response=MoOrder.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(@RequestBody Map<String,Object> moOrderMap) {
		
		
		try{

            List<String> ids= (List<String>) moOrderMap.get("$pks");
			if(ids==null || ids.size()==0){
				return Result.error("ids-0","ids不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("id");
			for (String fieldName : moOrderMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=moOrderMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(moOrderMap.get(i) )).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			List<MoOrder> moOrdersDb=moOrderService.selectListByIds(ids);
			if(moOrdersDb==null ||moOrdersDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<MoOrder> can=new ArrayList<>();
			List<MoOrder> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			boolean isSuperAdmin=LoginUtils.isSuperAdmin();
			for (MoOrder moOrderDb : moOrdersDb) {
				Tips tips2 = new Tips("检查通过");
				if(!isSuperAdmin && moOrderDb.getObranchId().equals(user.getBranchId())){
					tips2.setErrMsg("no-qx-edit","无权限修改");
				}
				String[] status={"0","1","5","6"};
				if(Arrays.stream(status).filter(k->k.equals(moOrderDb.getStatus())).findAny().isPresent()){
					tips2.setErrMsg("no-qx-edit","无权限修改");
				}
				if(!tips2.isOk()){
				    no.add(moOrderDb); 
				}else{
					can.add(moOrderDb);
				}
			}
			if(can.size()>0){
				if(moOrderMap.containsKey("odisRate")){
					if(can.size()>1){
						return Result.error("odisRate-count-big-1","修改折扣率只能一次修改一条订单");
					}
					MoOrder moOrderDb=can.get(0);
					BigDecimal orate=NumberUtil.getBigDecimal(moOrderMap.get("odisRate"),BigDecimal.valueOf(100));
					if(orate.compareTo(BigDecimal.ZERO)==0){
						orate=BigDecimal.valueOf(100);
					}
					BigDecimal othFee=NumberUtil.getBigDecimal(moOrderDb.getOthFee(),BigDecimal.ZERO);
					moOrderMap.put("ofinalFee",moOrderDb.getMoFinalFee().multiply(orate.divide(BigDecimal.valueOf(100))).add(othFee));
				}
                moOrderMap.put("$pks",can.stream().map(i->i.getId()).collect(Collectors.toList()));
			    moOrderService.editSomeFields(moOrderMap); 
			}
			List<String> msgs=new ArrayList<>();
			if(can.size()>0){
				msgs.add(String.format("成功更新以下%s条数据",can.size()));
			}
			if(no.size()>0){
				msgs.add(String.format("以下%s个数据无权限更新",no.size()));
			}
			if(can.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining())).setData(moOrderMap);
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

	@ApiOperation( value = "根据主键列表批量删除mo_order信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelMoOrder(@RequestBody List<MoOrder> moOrders) {
		
         
        try{ 
            if(moOrders.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<MoOrder> datasDb=moOrderService.listByIds(moOrders.stream().map(i-> i.getId() ).collect(Collectors.toList()));
			User user=LoginUtils.getCurrentUserInfo();
            List<MoOrder> can=new ArrayList<>();
            List<MoOrder> no=new ArrayList<>();
            for (MoOrder data : datasDb) {
            	boolean ok=true;
				if(data.getObranchId().equals(user.getBranchId())){
					ok=false;
				}
				String[] status={"0","1","5","6"};
				if(Arrays.stream(status).filter(k->k.equals(data.getStatus())).findAny().isPresent()){
					ok=false;
 				}
                if(ok){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                moOrderService.removeByIds(moOrders);
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

	@ApiOperation( value = "通过Id获取订单",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/getOrderById",method=RequestMethod.GET)
	public Result getOrderById(String orderId) {
		
		
		if(!StringUtils.hasText(orderId)) {
			return Result.error("data-0","订单Id不能为空");
		}
		MoOrder moOrder = moOrderService.selectOneById(orderId);
		
		return Result.ok().setData( moOrder); 
	}


	@ApiOperation( value = "0元购付款确认",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/orderPayZero",method=RequestMethod.POST)
	public Result orderPayZero(@RequestBody MoOrder order) {

		try {

			if(!StringUtils.hasText(order.getId())) {
				return Result.error("data-0","订单Id不能为空");
			}
			if(!LoginUtils.isSuperAdmin()){
				return Result.error("superAdmin-required","只有超级管理员可以进行零元购付款确认");
			}
			moOrderService.orderPaySuccess(true,order.getId(),order.getPayId(),order.getPrepayId(), order.getTranId(), order.getPayAt(), order.getRemark());

			return Result.ok();
		}catch (BizException e) {
			logger.error("",e);
			return Result.error("data-0",e.getMessage());
		} catch (Exception e) {
			logger.error("",e);
			return Result.error("data-0", "开通模块失败");
		}
	}

	@ApiOperation( value = "通过Id获取订单",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/orderPaySuccess",method=RequestMethod.POST)
	public Result orderPaySuccess(@RequestBody MoOrder order) {
		
		try {
			
			if(!StringUtils.hasText(order.getId())) {
				return Result.error("data-0","订单Id不能为空");
			}
			String flag= (String) this.cacheHKVService.getValue("pay-notify-success-"+order.getPayId());
			if(!StringUtils.hasText(flag)|| !"1".equals(flag)){
				return Result.error("pay-notify-success-flag-0","验证码错误");
			}
			moOrderService.orderPaySuccess(false,order.getId(),order.getPayId(),order.getPrepayId(), order.getTranId(), order.getPayAt(), order.getRemark());
			
			return Result.ok();
		}catch (BizException e) {
			logger.error("",e);
			return Result.error("data-0",e.getMessage());
		} catch (Exception e) {
			logger.error("",e);
			return Result.error("data-0", "开通模块失败");
		}
	}

	@ApiOperation( value = "订单支付取消判断",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/payCancel",method=RequestMethod.POST)
	public Result payCancel(@RequestBody MoOrder order) {
		
		try {
			
			if(!StringUtils.hasText(order.getId())) {
				return Result.error("data-0","订单Id不能为空");
			}
			String flag= (String) this.cacheHKVService.getValue("pay-notify-cancel-"+order.getPayId());
			if(!StringUtils.hasText(flag)|| !"1".equals(flag)){
				return Result.error("pay-notify-cancel-flag-0","验证码错误");
			}
			moOrderService.payCancel(order.getId(),order.getPayId(), order.getRemark());
			
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
	public Result updatePrepayId(@RequestBody  MoOrder order) {
		
		
		if(!StringUtils.hasText(order.getId())) {
			return Result.error("data-0","订单Id不能为空");
		}
		MoOrder moOrder = new MoOrder();
		moOrder.setId(order.getId());
		moOrder.setPayId(order.getPayId());
		moOrder.setPrepayId(order.getPrepayId());
		moOrder.setPayTime(new Date());
		moOrderService.updateSomeFieldByPk(moOrder);
		
		return Result.ok().setData( moOrder); 
	}






}
