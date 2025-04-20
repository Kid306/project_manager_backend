package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sys.cache.InterestsCacheService;
import com.mdp.sys.entity.Interests;
import com.mdp.sys.service.InterestsService;
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

import java.util.*;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.fromMap;
import static com.mdp.core.utils.BaseUtils.toMap;


/**
 * url编制采用rest风格,如对mem_interests 会员权益表的操作有增删改查,对应的url分别为:<br>
 *  新增: mem/interests/add <br>
 *  查询: mem/interests/list<br>
 *  模糊查询: mem/interests/listKey<br>
 *  修改: mem/interests/edit <br>
 *  删除: mem/interests/del<br>
 *  批量删除: mem/interests/batchDel<br>
 * 组织 com  顶级模块 mk 大模块 mem 小模块 <br>
 * 实体 Interests 表 mem_interests 当前主键(包括多主键): ilvl_id; 
 ***/
@RestController("sys.interestsController")
@RequestMapping(value="/*/sys/interests")
@Api(tags={"会员权益表操作接口"})
public class InterestsController {
	
	static Logger logger =LoggerFactory.getLogger(InterestsController.class);
	
	@Autowired
	private InterestsService interestsService;

	@Autowired
	private InterestsCacheService interestsCacheService;

	Map<String,Object> fieldsMap = toMap(new Interests());
 
	
	@ApiOperation( value = "查询会员权益表信息列表",notes=" ") 
	@ApiResponses({
		@ApiResponse(code = 200,response=Interests.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listInterests( @ApiIgnore @RequestParam Map<String,Object>  params){
		
		
		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<Interests> qw= QueryTools.initQueryWrapper(Interests.class,params);
		List<Interests>	interestsList = interestsService.getInterestssFromCacheFirst("1");	//列出Interests列表
		
		return Result.ok().setData(interestsList).setTotal(page.getTotal());

	}
	
 

	@ApiOperation( value = "新增一条会员权益表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=Interests.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addInterests(@RequestBody Interests interests) {
		
		
		try{
		    boolean createPk=false;
			if(!StringUtils.hasText(interests.getIlvlId())) {
			    createPk=true;
				interests.setIlvlId(interestsService.createKey("ilvlId"));
			}
			if(createPk==false){
                 if(interestsService.selectOneObject(interests) !=null ){
                    return Result.error("pk-exists","编号重复，请修改编号再提交");
                }
            }
			interestsService.insert(interests);
			interestsCacheService.remove(interests.getItype());
			return Result.ok().setData(interests);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}

	@ApiOperation( value = "删除一条会员权益表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delInterests(@RequestBody Interests interests){
		
		
		try{
            if(!StringUtils.hasText(interests.getIlvlId())) {
                 return Result.error("pk-not-exists","请上送主键参数ilvlId");
            }
            Interests interestsDb = interestsService.selectOneObject(interests);
            if( interestsDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			interestsService.deleteByPk(interests);
			interestsCacheService.remove(interests.getItype());
			return Result.ok();
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}

	@ApiOperation( value = "根据主键修改一条会员权益表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=Interests.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editInterests(@RequestBody Interests interests) {
		
		
		try{
            if(!StringUtils.hasText(interests.getIlvlId())) {
                 return Result.error("pk-not-exists","请上送主键参数ilvlId");
            }
            Interests interestsDb = interestsService.selectOneObject(interests);
            if( interestsDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			interestsService.updateSomeFieldByPk(interests);
			interestsCacheService.remove(interests.getItype());
			return Result.ok().setData(interests);
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
			@ApiResponse(code = 200,response=Interests.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(@RequestBody Map<String,Object> interestsMap) {
		
		
		try{
            List<String> ilvlIds= (List<String>) interestsMap.get("ilvlIds");
			if(ilvlIds==null || ilvlIds.size()==0){
				return Result.error("ilvlIds-0","ilvlIds不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("ilvlId");
			for (String fieldName : interestsMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=interestsMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(interestsMap.get(i) )).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			Interests interests = fromMap(interestsMap,Interests.class);
			List<Interests> interestssDb=interestsService.selectListByIds(ilvlIds);
			if(interestssDb==null ||interestssDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<Interests> can=new ArrayList<>();
			List<Interests> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (Interests interestsDb : interestssDb) {
				Tips tips2 = new Tips("检查通过"); 
				if(!tips2.isOk()){
				    no.add(interestsDb); 
				}else{
					can.add(interestsDb);
				}
			}

			if(can.size()>0){
                interestsMap.put("ilvlIds",can.stream().map(i->i.getIlvlId()).collect(Collectors.toList()));
			    interestsService.editSomeFields(interestsMap); 
			}
			interestsCacheService.remove(interestssDb.get(0).getItype());
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
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}
	}

	@ApiOperation( value = "根据主键列表批量删除会员权益表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelInterests(@RequestBody List<Interests> interestss) {
		
         
        try{ 
            if(interestss.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<Interests> datasDb=interestsService.selectListByIds(interestss.stream().map(i-> i.getIlvlId() ).collect(Collectors.toList()));

            List<Interests> can=new ArrayList<>();
            List<Interests> no=new ArrayList<>();
            for (Interests data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                interestsService.batchDelete(interestss);
				interestsCacheService.remove(can.get(0).getItype());
                msgs.add(String.format("成功删除%s条数据.",can.size()));
            }
    
            if(no.size()>0){ 
                msgs.add(String.format("以下%s条数据不能删除.【%s】",no.size(),no.stream().map(i-> i.getIlvlId() ).collect(Collectors.joining(","))));
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
