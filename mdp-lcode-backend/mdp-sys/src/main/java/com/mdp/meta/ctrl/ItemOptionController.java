package com.mdp.meta.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.NumberUtil;
import com.mdp.core.utils.ObjectTools;
import com.mdp.meta.entity.Item;
import com.mdp.meta.entity.ItemOption;
import com.mdp.meta.service.ItemOptionService;
import com.mdp.meta.service.ItemService;
import com.mdp.swagger.ApiEntityParams;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * url编制采用rest风格,如对ADMIN.meta_item_option 数据项取值列表的操作有增删改查,对应的url分别为:<br>
 *  新增: meta/itemOption/add <br>
 *  查询: meta/itemOption/list<br>
 *  模糊查询: meta/itemOption/listKey<br>
 *  修改: meta/itemOption/edit <br>
 *  删除: meta/itemOption/del<br>
 *  批量删除: meta/itemOption/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 meta 小模块 <br>
 * 实体 ItemOption 表 ADMIN.meta_item_option 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.meta.itemOptionController")
@RequestMapping(value="/*/meta/itemOption")
@Api(tags={"数据项取值列表操作接口"})
public class ItemOptionController {
	
	static Log logger=LogFactory.getLog(ItemOptionController.class);
	
	@Autowired
	private ItemOptionService itemOptionService;

	@Autowired
	private ItemService itemService;
	 

	ObjectMapper objectMapper=new ObjectMapper();
	
	@ApiOperation( value = "查询数据项取值列表信息列表",notes="listItemOption,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiResponses({
		@ApiResponse(code = 200,response=ItemOption.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@Deprecated
	public Result listItemOption( @ApiIgnore @RequestParam Map<String,Object>  itemOption){
		 
		IPage page=QueryTools.initPage(itemOption);
		QueryWrapper<ItemOption> qw=QueryTools.initQueryWrapper(ItemOption.class,itemOption,"res.*","item.*","categoryId");
		qw.getCustomSqlSegment();
		List<Map<String,Object>>	itemOptionList = itemOptionService.selectListMapByWhere(page,qw,itemOption);
		return Result.ok().setData(itemOptionList).setTotal(page.getTotal());
		
		
	}
	
	@ApiOperation( value = "查询系统参数",notes="getSysParam,条件之间是 and关系")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="主键,主键",required=false), 
	})
	@ApiResponses({
		@ApiResponse(code = 200,response=ItemOption.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},data:{}}")
	})
	@RequestMapping(value="/sysParam",method=RequestMethod.GET)
	@Deprecated
	public Result getSysParam(@ApiIgnore @RequestParam Map<String,Object> itemOption){
		
		
		String id= (String) itemOption.get("id");
		String itemCode= (String) itemOption.get("itemCode");
		if(!StringUtils.hasText(id) && !StringUtils.hasText(itemCode) ){ 
			return Result.error("id-or-item-code-must","参数不足");
		}
		IPage page=new Page(1,1);
		QueryWrapper<Item> qw=QueryTools.initQueryWrapper(Item.class,itemOption);
		qw.getCustomSqlSegment();
		List<Map<String,Object>> datas = this.itemService.selectListMapByWhere(page,qw,itemOption);
		if(datas!=null && datas.size()>0){
			return Result.ok().setData(datas.get(0).get("dvalues"));
		}else{
			return Result.ok().setData(null);
		}
		
	}


	
	@ApiOperation( value = "查询数据项取值列表信息列表",notes="listItemOption,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiResponses({
		@ApiResponse(code = 200,response=ItemOption.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list/byItemCode",method=RequestMethod.POST)
	@Deprecated
	public Result listItemOptionByItemCode( @RequestBody List<Item> itemList){
		
		List<String> categoryIds=new ArrayList<>();
		List<String> itemCodes=new ArrayList<>();
		for (Item item : itemList) {
			categoryIds.add(item.getCategoryId());
			itemCodes.add(item.getItemCode());
		}
		IPage page=new Page<>(1,1000);
		QueryWrapper<ItemOption> qw=new QueryWrapper<>();
		qw.in("item.item_code",itemCodes);
		qw.in("item.category_id",categoryIds);
		List<Map<String,Object>> optionMaps=this.itemOptionService.selectListMapByWhere(page,qw,null);

		Map<String,List<Map<String,Object>>> data=new HashMap<>();
		for (Map<String, Object> option : optionMaps) {
			String itemCode= (String) option.get("itemCode");
			List<Map<String,Object>> options=new ArrayList<>();
			if(data.containsKey(itemCode)){
				options=data.get(itemCode);
			}else{
				options=new ArrayList<>();
				data.put(itemCode,options);
			}
			String itemId= (String) option.get("itemId");
			String itemType= (String) option.get("itemType");
			if("4".equals(itemType)||"5".equals(itemType)){
				option.put("optionValue",option.get("id"));
				option.put("optionName",option.get("name"));
				options.add(option);
			}else{
				option.put("optionValue",option.get("dvalues"));
				option.put("optionName",option.get("dnames"));
				options.add(option);
			}
			data.put((String) option.get("itemCode"),options);
		}

		return Result.ok().setData(data).setTotal(NumberUtil.getLong(data.size()));
	}

	@ApiOperation( value = "批量修改某些字段",notes="")
	@ApiEntityParams( value = ItemOption.class, props={ }, remark = "批量修改某些字段", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=ItemOption.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> params) {
		itemOptionService.editSomeFields(params);
 		return Result.ok();
	}

	@ApiOperation( value = "批量修改状态之间的转换关系",notes="")
	@RequestMapping(value="/unTargets",method=RequestMethod.POST)
	public Result unTargets( @ApiIgnore @RequestBody List<ItemOption> options) {
		for (ItemOption option : options) {
			this.itemOptionService.lambdaUpdate().set(ItemOption::getUnTargets,option.getUnTargets()).eq(ItemOption::getItemId,option.getItemId()).eq(ItemOption::getId,option.getId()).update();
		}
		return Result.ok();
	}

	@ApiOperation( value = "新增一条数据",notes="")
 	@ApiResponses({
			@ApiResponse(code = 200,response=ItemOption.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result add( @ApiIgnore @RequestBody ItemOption itemOption) {
		if(ObjectTools.isEmpty(itemOption.getItemId())){
			return Result.error("item-id-required","字典编号不能为空");
		}
		itemOptionService.save(itemOption);
		return Result.ok().setData(itemOption);
	}

	@ApiOperation( value = "删除一条数据",notes="")
	@ApiResponses({
			@ApiResponse(code = 200,response=ItemOption.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result del( @ApiIgnore @RequestBody ItemOption itemOption) {
		itemOptionService.removeById(itemOption);
		return Result.ok();
	}
	@ApiOperation( value = "批量删除一条数据",notes="")
	@ApiResponses({
			@ApiResponse(code = 200,response=ItemOption.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDel( @ApiIgnore @RequestBody List<ItemOption> itemOptions) {
		itemOptionService.removeByIds(itemOptions);
		return Result.ok();
	}
}
