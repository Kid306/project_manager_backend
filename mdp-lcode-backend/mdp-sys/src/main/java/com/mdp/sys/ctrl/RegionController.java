package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.service.SequenceService;
import com.mdp.sys.entity.Region;
import com.mdp.sys.service.RegionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;
/**
 * url编制采用rest风格,如对ADMIN.sys_region的操作有增删改查,对应的url分别为:<br>
 *  新增: sys/region/add <br>
 *  查询: sys/region/list<br>
 *  模糊查询: sys/region/listKey<br>
 *  修改: sys/region/edit <br>
 *  删除: sys/region/del<br>
 *  批量删除: sys/region/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 Region 表 ADMIN.sys_region 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.sys.regionController")
@RequestMapping(value="/*/sys/region")
public class RegionController {
	
	static Log logger=LogFactory.getLog(RegionController.class);
	
	@Autowired
	private RegionService regionService;
	
	@Autowired
	private SequenceService seqService;
		
	 
	
	/**
	 * 请求,如list
	 * 分页参数 {pageNum:1,pageSize:10,total:0}
	 * 根据条件查询数据对象列表,如果不上传分页参数，将不会分页。后台自动分页，无需编程
	 */
	@RequestMapping(value="/list")
	public Result listRegion(@ApiIgnore @RequestParam Map<String,Object>  params){
		 
		IPage page=QueryTools.initPage(params);
		QueryWrapper<Region> qw= QueryTools.initQueryWrapper(Region.class,params);
		List<Map<String,Object>>	regionList = regionService.selectListMapByWhere(page,qw,params);
		return Result.ok().setData(regionList).setTotal(page.getTotal());
		
		
	}
	
	
	/**
	 * 请求,如list/tree 
	 * 根据条件查询数据对象列表,如果不上传分页参数，将不会分页。后台自动分页，无需编程
	 */
	@RequestMapping(value="/list/tree")
	public Result listRegionTree( Map<String,Object> params){
		IPage page=new Page(1,10000);
		QueryWrapper<Region> qw= QueryTools.initQueryWrapper(Region.class,params);
		List<Map<String,Object>>	regionList = regionService.selectListMapByWhere(page,qw,params);
		return Result.ok().setData(regionList).setTotal(page.getTotal());
	}
	/**
	 * 请求模糊查询、条件字段由上传的参数决定 ,如{字段1:v1,字段2:v1},代表字段1，字段2都以v1作为参数值进行模糊查询，两个条件是or的关系。需要驼峰命名字段名
	 * 根据条件查询数据对象列表
	 
	@RequestMapping(value="/listKey")
	public Result listRegionKey( params region){
		 
		List<Map<String,Object>>	regionList = regionService.selectListMapByKey(region);	//列出Region列表
		return Result.ok().setData(regionList);
		
		
	}
	*/
	
	/**
	 * 新增一条数据
	 
	@RequestMapping(value="/add")
	public Result addRegion(@RequestBody Region region) {
		
		
		try{
			region.setId(seqService.getTablePK("region", "id"));
			regionService.insert(region);
			return Result.ok().setData(region);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	}
	*/
	
	/**
	 * 根据主键删除1条数据
	
	@RequestMapping(value="/del")
	public Result delRegion(@RequestBody Region region){
		
		
		try{
			regionService.deleteByPk(region);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	}
	 */
	
	/**
	 * 根据主键修改一条数据
	 
	@RequestMapping(value="/edit")
	public Result editRegion(@RequestBody Region region) {
		
		
		try{
			regionService.updateByPk(region);
			return Result.ok().setData(region);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	}
	*/
	

	
	/**
	 * 批量删除
	 
	@RequestMapping(value="/batchDel")
	public Result batchDelRegion(@RequestBody String[] ids) {
		
		
		List<Region> list=new ArrayList<Region>();
		try{
			for(int i=0;i<ids.length;i++){
				Region region=new Region();
				region.setId(ids[i]);
				list.add(region);
			  }
			regionService.batchDelete(list);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	} 
	*/
}
