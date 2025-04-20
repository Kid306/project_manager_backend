package com.mdp.sys.ctrl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.api.CacheHKVService;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.DateUtils;
import com.mdp.sys.entity.*;
import com.mdp.sys.service.InterestsOrdersService;
import com.mdp.sys.service.InterestsService;
import com.mdp.sys.service.UserInterestsService;
import com.mdp.sys.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.mdp.core.utils.BaseUtils.map;
import static com.mdp.core.utils.BaseUtils.toMap;


/**
 * url编制采用rest风格,如对mem_interests_orders 会员权益关联表的操作有增删改查,对应的url分别为:<br>
 *  新增: mem/interestsOrders/add <br>
 *  查询: mem/interestsOrders/list<br>
 *  模糊查询: mem/interestsOrders/listKey<br>
 *  修改: mem/interestsOrders/edit <br>
 *  删除: mem/interestsOrders/del<br>
 *  批量删除: mem/interestsOrders/batchDel<br>
 * 组织 com  顶级模块 mk 大模块 mem 小模块 <br>
 * 实体 InterestsOrders 表 mem_interests_orders 当前主键(包括多主键): id; 
 ***/
@RestController("sys.interestsOrdersController")
@RequestMapping(value="/*/sys/interestsOrders")
@Api(tags={"会员权益关联表操作接口"})
public class InterestsOrdersController {
	
	static Logger logger =LoggerFactory.getLogger(InterestsOrdersController.class);
	
	@Autowired
	private InterestsOrdersService interestsOrdersService;
	@Autowired
	CacheHKVService cacheHKVService;

	Map<String,Object> fieldsMap = toMap(new InterestsOrders());
	@Autowired
	private UserService userService;

	@Autowired
	private InterestsService interestsService;

	@Autowired
	UserInterestsService userInterestsService;

	@ApiOperation( value = "查询一个订单明细",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=InterestsOrders.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public Result detail( InterestsOrders interestsOrders){
		
		
		if(!StringUtils.hasText(interestsOrders.getId())){
			return Result.error("id-0","订单编号不能为空");
		}
		InterestsOrders	data = interestsOrdersService.selectOneById(interestsOrders.getId());	//列出InterestsOrders列表
 		return Result.ok().setData(data!=null?data:map());
	}
	@ApiOperation( value = "查询会员权益关联表信息列表",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=InterestsOrders.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listInterestsOrders( @ApiIgnore @RequestParam Map<String,Object>  params){
		
		
		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<InterestsOrders> qw= QueryTools.initQueryWrapper(InterestsOrders.class,params);
		List<Map<String,Object>>	interestsOrdersList = interestsOrdersService.selectListMapByWhere(page,qw,params);
		
		return Result.ok().setData(interestsOrdersList).setTotal(page.getTotal());

	}
	@ApiOperation( value = "创建一个订单，仅用于计算数据，不存入数据库",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=InterestsOrders.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/calcOrders",method=RequestMethod.GET)
	public Result calcOrders( CreateInterestsOrdersVo createInterestsOrders) {
		createInterestsOrders.setCalc(true);
		return createOrders(createInterestsOrders);
	}

		@ApiOperation( value = "创建一个订单",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=InterestsOrders.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/createOrders",method=RequestMethod.POST)
	public Result createOrders(@RequestBody CreateInterestsOrdersVo createInterestsOrders) {
		
		
		try{
		    boolean createPk=false;
			if(!StringUtils.hasText(createInterestsOrders.getId())) {
				createPk=true;
				createInterestsOrders.setId(interestsOrdersService.createKey("id"));
			}
			if(createPk==false){
				if(interestsOrdersService.selectOneById(createInterestsOrders.getId()) !=null ){
					return Result.error("pk-exists","编号重复，请修改编号再提交");
				}
			}
			if(!StringUtils.hasText(createInterestsOrders.getIlvlId())){
				return Result.error("ilvlId-0","会员等级编号不能为空");
			}
			if(!StringUtils.hasText(createInterestsOrders.getUserid())){
				return Result.error("userid-0","会员编号不能为空");
			}
			if(  createInterestsOrders.getMonths()<=0){
				return Result.error("months-0","购买月数不能为空");
			}
			User user= userService.selectOneById(createInterestsOrders.getUserid());
			if(user==null){
				return Result.error("userid-0","用户不存在");
			}
 			Interests interests=this.interestsService.selectOneById(createInterestsOrders.getIlvlId());
			if(interests==null){
				return Result.error("interests-0","会员等级不存在");
			}
			if(StringUtils.isEmpty(createInterestsOrders.getOoper())){
				return Result.error("ooper-0","操作类型不正确");
			}

			//下单前需要检查是否存在待执行订单，如存在不允许下单
			InterestsOrders countParams=new InterestsOrders();
			countParams.setUserid(user.getUserid());
			countParams.setCalcState("2");
			countParams.setPayStatus("1");
			Long countExecOrders=this.interestsOrdersService.countByWhere(countParams);
			if(countExecOrders>0){
				return Result.error("orders-exists","已有待执行订单，无须再购买");
			}
			long oneDayMillsec=(1000*3600*24);
 			UserInterests userInterests=this.userInterestsService.selectOneById(createInterestsOrders.getUserid());
			if("1".equals(createInterestsOrders.getOoper())){//新购
				if(userInterests.getIlevel()!=null && userInterests.getIlevel()>1 && userInterests.getRtime()!=null){
					Date oldRtime=DateUtils.parse(userInterests.getRtime(),"yyyy-MM-dd");
					Date now=new Date();
					int days = (int) ((oldRtime.getTime() - now.getTime()) / oneDayMillsec );
					if(days>0) {
						return Result.error("rtime-err","您已是会员，无须再新增购买，如需要调整，请升级或者续费");
					}
				}

			}else if("2".equals(createInterestsOrders.getOoper())){//续费
				if(userInterests==null||userInterests.getIlevel()==null||userInterests.getIlevel()<=1||userInterests.getRtime()==null){
					return Result.error("data-err2","您没有需要续费的订单，请新增购买");
				}
			}else if("3".equals(createInterestsOrders.getOoper())){//升级
				if(userInterests==null||userInterests.getIlevel()==null||userInterests.getIlevel()<=1||userInterests.getRtime()==null){
					return Result.error("data-err3","您没有需要升级的订单，请新增购买");
				}
				if(interests.getIlevel()<userInterests.getIlevel()){
					return Result.error("ilevel-err","不能升级为更低级别的会员");
				}
				//升级购买月份不能少于当前日期到旧的等级到期日，否则无法计算差价
				Date oldRtime=DateUtils.parse(userInterests.getRtime(),"yyyy-MM-dd");
				Date now=new Date();
				int days = (int) ((oldRtime.getTime() - now.getTime()) / oneDayMillsec);
				if(days<0) {
					return Result.error("rtime-err4","会员已过期，不能进行会员再升级，请续费购买");
				}
				if(days<30){
					return Result.error("rtime-err5","旧会员剩余不到30天到期，建议不做升级处理，请续费");
				}
			}else{
				return Result.error("ooper-err","操作类型错误");
			}



			InterestsOrders interestsOrders=new InterestsOrders();

			if("1".equals(createInterestsOrders.getOoper())){//新购
				interestsOrders.setCalcState("1");
				interestsOrders.setCalcStatus("1");
				interestsOrders.setCalcExecTime(new Date());
				interestsOrders.setOldRtime(userInterests.getRtime());
			}else if("2".equals(createInterestsOrders.getOoper())){//续费 如果过期后续费，立即生效，如果未过期续费，需要引入定时任务到期再计算
				Date oldRtime=DateUtils.parse(userInterests.getRtime(),"yyyy-MM-dd");
				Date now=new Date();
				int days = (int) ((oldRtime.getTime() - now.getTime()) / oneDayMillsec );
				if(days<=0) {//过期后续费立即生效
					interestsOrders.setCalcState("1");
					interestsOrders.setCalcStatus("1");
					interestsOrders.setCalcExecTime(new Date());
					interestsOrders.setOldRtime(userInterests.getRtime());
				}else{//未过期 需要引入定时任务到期再计算
					interestsOrders.setCalcState("2");
					interestsOrders.setCalcStatus("2");
					interestsOrders.setOldRtime(userInterests.getRtime());
				}

			}else if("3".equals(createInterestsOrders.getOoper())){//升级
				interestsOrders.setCalcState("1");
				interestsOrders.setCalcStatus("1");
				interestsOrders.setCalcExecTime(new Date());
				interestsOrders.setOldRtime(userInterests.getRtime());
			}else{
				return Result.error("ooper-err","操作类型错误");
			}


			BeanUtils.copyProperties(interests,interestsOrders);
			interestsOrders.setIlvlId(createInterestsOrders.getIlvlId());
			interestsOrders.setUserid(createInterestsOrders.getUserid());
			interestsOrders.setMonths(createInterestsOrders.getMonths());
			interestsOrders.setOoper(createInterestsOrders.getOoper());
			interestsOrders.setId(createInterestsOrders.getId());
			if(!StringUtils.hasText(interestsOrders.getVer())){
				interestsOrders.setVer("1");
			}
			interestsOrders.setOtye("2");
			Integer discountRate=100;
			if(StringUtils.hasText(interests.getDiscount()) && interests.getDiscount().startsWith("{")){
				JSONObject jsonObject= JSON.parseObject(interests.getDiscount());
				String discount=jsonObject.getString("months");
				if(StringUtils.hasText(discount)){
					String[] disList=discount.split("\n");
					if(disList.length>0){
 						for (int i = 0; i < disList.length; i++) {
							String month=disList[i];
							String[] mrate=month.split(":");
							if(Integer.valueOf(mrate[0])>=interestsOrders.getMonths()){
								discountRate=Integer.valueOf(mrate[1]);
 								break;
							}
						}
					}
				}
			}



			BigDecimal originFee=BigDecimal.ZERO;
			BigDecimal finalFee=BigDecimal.ZERO;
			if(!"1".equals(interests.getIsFree())){
				originFee= interests.getMfee().multiply(BigDecimal.valueOf(interestsOrders.getMonths()));
				finalFee=originFee.multiply(BigDecimal.valueOf(discountRate)).divide(BigDecimal.valueOf(100));
				interestsOrders.setPayStatus("0");
			}else {
				interestsOrders.setPayStatus("1");
			}

			//如果是升级--需要扣减原来的差额费用
			BigDecimal upgradeDiscountFee=BigDecimal.ZERO;
			if("3".equals(createInterestsOrders.getOoper())){
				//查询原来的订单
				InterestsOrders oldOrders=this.interestsOrdersService.selectLastOrders(createInterestsOrders.getUserid());
				if(oldOrders==null||oldOrders.getIlevel()<=1||oldOrders.getPayAt()==null || oldOrders.getPayAt().compareTo(BigDecimal.ZERO)==0||oldOrders.getMonths()==null||oldOrders.getMonths()<1){//如果是免费的则忽略
					//免费的或者原来没有购买记录，不需要处理
				}else{
					//升级购买月份不能少于当前日期到旧的等级到期日，否则无法计算差价
					Date oldRtime=DateUtils.parse(oldOrders.getRtime(),"yyyy-MM-dd");
					Date now=new Date();
					long months=DateUtil.betweenMonth(now, oldRtime, false);
					upgradeDiscountFee=oldOrders.getPayAt().divide(BigDecimal.valueOf(oldOrders.getMonths())).multiply(BigDecimal.valueOf(months));
				}
			}
			finalFee=finalFee.subtract(upgradeDiscountFee);
			interestsOrders.setOriginFee( originFee );
			interestsOrders.setFinalFee(finalFee);
			interestsOrders.setDiscount(discountRate);
			interestsOrders.setOthFee(upgradeDiscountFee);
			interestsOrders.setCtime(new Date());
			interestsOrders.setLtime(new Date());
			if("1".equals(interestsOrders.getOoper())){
				interestsOrders.setName("新购会员等级【"+interests.getIlvlName()+"】");
			}else if("2".equals(interestsOrders.getOoper())){
				interestsOrders.setName("续期购买会员等级【"+interests.getIlvlName()+"】");
			}else if("3".equals(interestsOrders.getOoper())){
				interestsOrders.setName("升级购买会员等级【"+interests.getIlvlName()+"】");
			}
			interestsOrders.setRemark(interestsOrders.getName());
			if(!createInterestsOrders.isCalc()){
				interestsOrdersService.createOrders(interestsOrders);
			}
			return Result.ok().setData(interestsOrders);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}

	@ApiOperation( value = "付款确认",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=InterestsOrders.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/payConfirm",method=RequestMethod.POST)
	public Result payConfirm(@RequestBody InterestsOrders interestsOrders) {
		
		
		try{
			if(!StringUtils.hasText(interestsOrders.getId())) {
				return Result.error("pk-not-exists","请上送主键参数id");
			}
			String flag= (String) cacheHKVService.get("pay-notify-success-"+interestsOrders.getPayId());
			if(!StringUtils.hasText(flag)|| !"1".equals(flag)){
				return Result.error("pay-notify-success-flag-0","验证码错误");
			}
			InterestsOrders interestsOrdersDb = interestsOrdersService.selectOneObject(interestsOrders);
			if( interestsOrdersDb == null ){
				return Result.error("data-not-exists","数据不存在，无法修改");
			}
			if("1".equals(interestsOrdersDb.getPayStatus())){
				return Result.error("had-pay","已付款");
			}

			if( StringUtils.hasText(interestsOrdersDb.getPayId()) && !interestsOrdersDb.getPayId().equals(interestsOrders.getPayId()) ){
				return Result.error("payId-err0","付款流水编号不正确");
			}
			if("1".equals(interestsOrdersDb.getVer())||!StringUtils.hasText(interestsOrdersDb.getVer())){
				interestsOrdersService.payConfirmForPerson(interestsOrders,interestsOrdersDb);
			}else {
				interestsOrdersService.payConfirmForBranch(interestsOrders,interestsOrdersDb);
			}

			//付款成功直接开通会员等级

			return Result.ok().setData(interestsOrders);
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}
	}


	@ApiOperation( value = "订单支付取消判断",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/payCancel",method=RequestMethod.POST)
	public Result payCancel(@RequestBody InterestsOrders order) {
		
		try {
			
			if(!StringUtils.hasText(order.getId())) {
				return Result.error("data-0","订单Id不能为空");
			}
			String flag= (String)cacheHKVService.get("pay-notify-cancel-"+order.getPayId());
			if(!StringUtils.hasText(flag)|| !"1".equals(flag)){
				return Result.error("pay-notify-cancel-flag-0","验证码错误");
			}
			this.interestsOrdersService.payCancel(order.getId(),order.getPayId(), order.getRemark());
			
			return Result.ok();
		}catch (BizException e) {
			logger.error("",e);
			return Result.error("data-0",e.getMessage());
		} catch (Exception e) {
			logger.error("",e);
			return Result.error("data-0", "订单付款取消操作失败");
		}
	}
	@ApiOperation( value = "付款确认",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=InterestsOrders.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/updatePrepayId",method=RequestMethod.POST)
	public Result updatePrepayId(@RequestBody InterestsOrders interestsOrders) {
		
		
		try{
			if(!StringUtils.hasText(interestsOrders.getId())) {
				return Result.error("pk-not-exists","请上送主键参数id");
			}

			if(!StringUtils.hasText(interestsOrders.getPrepayId())){
				return Result.error("prepayId-0","第三方订单号不能为空");
			}

			if(!StringUtils.hasText(interestsOrders.getPayId())){
				return Result.error("payId-0","付款流水单号不能为空");
			}
			InterestsOrders interestsOrdersDb = interestsOrdersService.selectOneObject(interestsOrders);
			if( interestsOrdersDb == null ){
				return Result.error("data-not-exists","数据不存在，无法修改");
			}
			if("1".equals(interestsOrdersDb.getPayStatus())){
				return Result.error("had-pay","已付款");
			}

			InterestsOrders interestsOrdersUpdate=new InterestsOrders();
			interestsOrdersUpdate.setId(interestsOrders.getId());
			interestsOrdersUpdate.setPrepayId(interestsOrders.getPrepayId());
			interestsOrdersUpdate.setPayId(interestsOrders.getPayId());
			interestsOrdersService.updateSomeFieldByPk(interestsOrdersUpdate);
			//付款成功直接开通会员等级

			return Result.ok().setData(interestsOrders);
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}
	}

	@ApiOperation( value = "删除一条会员权益关联表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delInterestsOrders(@RequestBody InterestsOrders interestsOrders){
		
		
		try{
            if(!StringUtils.hasText(interestsOrders.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            InterestsOrders interestsOrdersDb = interestsOrdersService.selectOneObject(interestsOrders);
            if( interestsOrdersDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
            if("1".equals(interestsOrdersDb.getPayStatus())){
				return Result.error("payStatus-1","订单已支付，不允许删除");
			}
			interestsOrdersService.deleteByPk(interestsOrders);
            return Result.ok();
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}

	/**
    @ApiOperation( value = "批量修改某些字段",notes="")
	@ApiResponses({
			@ApiResponse(code = 200,response=InterestsOrders.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(@RequestBody Map<String,Object> interestsOrdersMap) {
		
		
		try{
            List<String> ids= (List<String>) interestsOrdersMap.get("$pks");
			if(ids==null || ids.size()==0){
				return Result.error("ids-0","ids不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("id");
			for (String fieldName : interestsOrdersMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=interestsOrdersMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(interestsOrdersMap.get(i) )).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			InterestsOrders interestsOrders = fromMap(interestsOrdersMap,InterestsOrders.class);
			List<InterestsOrders> interestsOrderssDb=interestsOrdersService.selectListByIds(ids);
			if(interestsOrderssDb==null ||interestsOrderssDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<InterestsOrders> can=new ArrayList<>();
			List<InterestsOrders> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (InterestsOrders interestsOrdersDb : interestsOrderssDb) {
				Tips tips2 = new Tips("检查通过"); 
				if(!tips2.isOk()){
				    no.add(interestsOrdersDb); 
				}else{
					can.add(interestsOrdersDb);
				}
			}
			if(can.size()>0){
                interestsOrdersMap.put("$pks",can.stream().map(i->i.getId()).collect(Collectors.toList()));
			    interestsOrdersService.editSomeFields(interestsOrdersMap); 
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
	 **/
	/**
	@ApiOperation( value = "根据主键列表批量删除会员权益关联表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelInterestsOrders(@RequestBody List<InterestsOrders> interestsOrderss) {
		
         
        try{ 
            if(interestsOrderss.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<InterestsOrders> datasDb=interestsOrdersService.selectListByIds(interestsOrderss.stream().map(i-> i.getId() ).collect(Collectors.toList()));

            List<InterestsOrders> can=new ArrayList<>();
            List<InterestsOrders> no=new ArrayList<>();
            for (InterestsOrders data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                interestsOrdersService.batchDelete(interestsOrderss);
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
	**/

	public static void main(String[] args) {
		Date startDate=DateUtils.parse("2022-2-1","yyyy-MM-dd");
		Date endDate=DateUtils.parse("2022-2-5","yyyy-MM-dd");
		Long months=DateUtil.betweenMonth(startDate, endDate, true);
		System.out.println(months);

		startDate=DateUtils.parse("2021-09-11","yyyy-MM-dd");
		endDate=new Date();
		months=DateUtil.betweenMonth(startDate, endDate, true);
		System.out.println(months);
	}

}
