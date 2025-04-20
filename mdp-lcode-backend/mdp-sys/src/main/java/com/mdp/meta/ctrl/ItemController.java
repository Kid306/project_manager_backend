package com.mdp.meta.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.NumberUtil;
import com.mdp.core.utils.RequestUtils;
import com.mdp.meta.client.entity.ItemVo;
import com.mdp.meta.client.entity.Option;
import com.mdp.meta.entity.Item;
import com.mdp.meta.entity.ItemOption;
import com.mdp.meta.service.ItemOptionService;
import com.mdp.meta.service.ItemService;
import com.mdp.qx.HasRole;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;


/**
 * url编制采用rest风格,如对ADMIN.meta_item 数据项定义的操作有增删改查,对应的url分别为:<br>
 *  新增: meta/item/add <br>
 *  查询: meta/item/list<br>
 *  模糊查询: meta/item/listKey<br>
 *  修改: meta/item/edit <br>
 *  删除: meta/item/del<br>
 *  批量删除: meta/item/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 meta 小模块 <br>
 * 实体 Item 表 ADMIN.meta_item 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.meta.itemController")
@RequestMapping(value="/*/meta/item")
@Api(tags={"数据项定义操作接口"})
public class ItemController {
	
	static Log logger=LogFactory.getLog(ItemController.class);
	
	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemOptionService itemOptionService;

	@Autowired
	com.mdp.meta.client.service.ItemService itemClientService;
	  
	
	@ApiOperation( value = "查询数据项定义信息列表",notes="listItem,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiResponses({
		@ApiResponse(code = 200,response= Item.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listItem( @ApiIgnore @RequestParam Map<String,Object>  item){
		
		IPage page=QueryTools.initPage(item);
		QueryWrapper<Item> qw= QueryTools.initQueryWrapper(Item.class,item);
		List<Map<String,Object>>	itemList = itemService.selectListMapByWhere(page,qw,item);
		return Result.ok().setData(itemList).setTotal(page.getTotal());
		
		
	}

	@ApiOperation( value = "查询数据项定义信息列表，免登录接口",notes="listItem,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiResponses({
			@ApiResponse(code = 200,response= Item.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/dicts",method=RequestMethod.GET)
	public Result dicts( @ApiIgnore  @RequestParam Map<String,Object> params){
		IPage page=new Page(1,5000);
		RequestUtils.transformArray(params,"categoryIds","itemCodes");
		QueryWrapper<ItemOption> qw=QueryTools.initQueryWrapper(ItemOption.class,params,"itemCode","categoryId");

		List<Map<String,Object>>	optionMaps = itemOptionService.selectListMapByWhere(page,qw,params);
		Map<String,ItemVo> data=new HashMap<>();
		for (Map<String, Object> option : optionMaps) {
			String itemCode= (String) option.get("itemCode");
			ItemVo item=null;
			if(data.containsKey(itemCode)){
				item=data.get(itemCode);
			}else{
				item= BaseUtils.fromMap(option,ItemVo.class);
				data.put(itemCode,item);
			}
			item.setId((String) option.get("itemId"));
			ItemVo finalItem = item;
			if(Arrays.asList("4","5","14","15").stream().filter(k->k.equals(finalItem.getItemType())).findAny().isPresent()){
				List<Option> options=item.getOptions();
				if(options==null){
					options=new ArrayList<>();
				}
				options.add(BaseUtils.fromMap(option,Option.class));
				item.setOptions(options);
			}

			data.put(item.getItemCode(),item);
		}
		return Result.ok().setData(data.values()).setTotal(NumberUtil.getLong(data.size()));
	}


	@ApiOperation( value = "查询系统参数",notes="getSysParam,条件之间是 and关系")
	@ApiImplicitParams({
			@ApiImplicitParam(name="id",value="主键,主键",required=false),
	})
	@ApiResponses({
			@ApiResponse(code = 200,response=Item.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},data:{}}")
	})
	@RequestMapping(value="/sysParam",method=RequestMethod.GET)
	@Deprecated
	public Result getSysParam(@ApiIgnore @RequestParam Map<String,Object> params){


		String id= (String) params.get("id");
		String itemCode= (String) params.get("itemCode");
		if(!StringUtils.hasText(id) && !StringUtils.hasText(itemCode) ){
			return Result.error("id-or-item-code-must","参数不足");
		}
		IPage page=new Page(1,1);
		QueryWrapper<Item> qw=QueryTools.initQueryWrapper(Item.class,params);
		qw.getCustomSqlSegment();
		List<Map<String,Object>> datas = this.itemService.selectListMapByWhere(page,qw,params);
		if(datas!=null && datas.size()>0){
			return Result.ok().setData(datas.get(0).get("dvalues"));
		}else{
			return Result.ok().setData(null);
		}

	}

	/***/
	@ApiOperation( value = "新增或者修改一条数据项定义信息",notes="addItem,主键如果为空，后台自动生成")
	@ApiResponses({
			@ApiResponse(code = 200,response=Item.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole(roles = "superAdmin")
	@RequestMapping(value="/insertOrUpdate",method=RequestMethod.POST)
	public Result insertOrUpdate(@RequestBody Item item) {
		
		
		try{
			if(StringUtils.isEmpty(item.getId())) {
				item.setId(itemService.createKey("id"));
			}
			if(StringUtils.isEmpty(item.getItemCode())) { 
				return Result.error("item-code-not-allow-empty","字典代码不能为空");
			}
			if(StringUtils.isEmpty(item.getCategoryId())) { 
				return Result.error("category-id-not-allow-empty","分类编号不能为空");

			}
			Item itemQuery=new Item();
			itemQuery.setItemCode(item.getItemCode());
			List<Item>	listDb=this.itemService.selectListByWhere(itemQuery);
			Item itemDb=null;
			if(listDb!=null && listDb.size()>0){
				itemDb=listDb.get(0);
				item.setId(itemDb.getId());
				this.itemService.updateSomeFieldByPk(item);
			}else{
				this.itemService.insert(item);
			}
			return Result.ok().setData(item);
		}catch (BizException e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		} 
	}

	/***/
	@ApiOperation( value = "新增一条数据项定义信息",notes="addItem,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=Item.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole(roles = "superAdmin")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addItem(@RequestBody Item item) {
		
		
		try{
			if(StringUtils.isEmpty(item.getId())) {
				item.setId(itemService.createKey("id"));
			}
			if(StringUtils.isEmpty(item.getItemCode())) {
				return Result.error("item-code-not-allow-empty","字典代码不能为空");
			}
			if(StringUtils.isEmpty(item.getCategoryId())) {
				return Result.error("category-id-not-allow-empty","分类编号不能为空");

			}
			Item itemQuery=new Item();
 			itemQuery.setItemCode(item.getItemCode());
			if(this.itemService.countByWhere(itemQuery)>0){ 
				return Result.error("item-code-had-exists","该字典代码已存在");
			}
			itemService.insert(item);
			return Result.ok().setData(item);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}   
	}


	/***/
	@ApiOperation( value = "新增一条数据项定义信息",notes="addItem,主键如果为空，后台自动生成")
	@ApiResponses({
			@ApiResponse(code = 200,response=Item.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole(roles = "superAdmin")
	@RequestMapping(value="/copyToNewCategory",method=RequestMethod.POST)
	public Result copyToNewCategory(@RequestBody Item item) {
		
		
		try{
			if(StringUtils.isEmpty(item.getId())) {
				return Result.error("id-is-null","数据项编号参数不能为空");
			}
			if(StringUtils.isEmpty(item.getCategoryId())) {
				return Result.error("categoryId-is-null","分类代码不能为空");
			}
			Item itemQuery=new Item();
			itemQuery.setId(item.getId());
			Item itemDb=this.itemService.selectOneObject(itemQuery);
			itemDb.setId(this.itemService.createKey("id"));
			itemDb.setCategoryId(item.getCategoryId());

			Item itemQuery2=new Item();
			itemQuery2.setCategoryId(item.getCategoryId());
			itemQuery2.setItemCode(itemDb.getItemCode());
			Long lcount=this.itemService.countByWhere(itemQuery2);
			if(lcount>0){
				return Result.error("itemCode-is-exists","字典代码已在同一个分类下存在");
			}
			itemService.insert(itemDb);
			return Result.ok().setData(itemDb);
		}catch (BizException e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}
	}

	
	/***/
	@ApiOperation( value = "删除一条数据项定义信息",notes="delItem,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	@HasRole(roles = "superAdmin")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delItem(@RequestBody Item item){
		
		
		try{
			itemService.deleteByPk(item);
			try {
				itemClientService.initItemVoMap();
				itemClientService.initSysParam();
			}catch (Exception e){
				logger.error("执行清缓存异常，不影响交易继续",e);
			}

			return Result.ok();
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	}
	 
	
	/***/
	@ApiOperation( value = "根据主键修改一条数据项定义信息",notes="editItem")
	@ApiResponses({
		@ApiResponse(code = 200,response=Item.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole(roles = "superAdmin")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editItem(@RequestBody Item item) {
		
		
		try{
			itemService.updateByPk(item);
			try {
				itemClientService.initItemVoMap();
				itemClientService.initSysParam();
			}catch (Exception e){
				logger.error("执行清缓存异常，不影响交易继续",e);
			}
			return Result.ok().setData(item);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}   
	}
	
	

	
	/***/
	@ApiOperation( value = "根据主键列表批量删除数据项定义信息",notes="batchDelItem,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@HasRole(roles = "superAdmin")
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelItem(@RequestBody List<Item> items) {
		
		 
		try{ 
			itemService.batchDelete(items);
			try {
				itemClientService.initItemVoMap();
				itemClientService.initSysParam();
			}catch (Exception e){
				logger.error("执行清缓存异常，不影响交易继续",e);
			}
			return Result.ok();
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	} 
	
}
