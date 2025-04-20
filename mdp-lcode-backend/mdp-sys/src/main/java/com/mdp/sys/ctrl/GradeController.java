package com.mdp.sys.ctrl;

import com.mdp.core.entity.Result;
import com.mdp.core.utils.NumberUtil;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.Grade;
import com.mdp.sys.service.GradeService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

import static com.mdp.core.utils.BaseUtils.toMap;

/**
 * url编制采用rest风格,如对sys_grade 会员等级定义表的操作有增删改查,对应的url分别为:<br>
 * 组织 com  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 Grade 表 sys_grade 当前主键(包括多主键): grade_id; 
 ***/
@RestController("mdp.sys.gradeController")
@RequestMapping(value="/*/sys/grade")
@Api(tags={"会员等级定义表操作接口"})
public class GradeController {
	
	static Logger logger =LoggerFactory.getLogger(GradeController.class);
	
	@Autowired
	private GradeService gradeService;
	 

	Map<String,Object> fieldsMap = toMap(new Grade());
 
	
	@ApiOperation( value = "查询会员等级定义表信息列表",notes=" ")
	@ApiEntityParams( Grade.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
 	})
	@ApiResponses({
		@ApiResponse(code = 200,response=Grade.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listGrade( @ApiIgnore @RequestParam Map<String,Object>  grade){
		
		
		
		String gtype= (String) grade.get("gtype");
		if(StringUtils.isEmpty(gtype)){
			gtype="2";
			grade.put("gtype",gtype);
		}
		List<Grade>	gradeList = gradeService.getGradesFromCacheFirst(gtype);	//列出Grade列表
		
		return Result.ok().setData(gradeList).setTotal(NumberUtil.getLong( gradeList.size()));

	}
	
 
	
	/**
	@ApiOperation( value = "新增一条会员等级定义表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=Grade.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addGrade(@RequestBody Grade grade) {
		
		
		try{
		    boolean createPk=false;
			if(!StringUtils.hasText(grade.getGradeId())) {
			    createPk=true;
				grade.setGradeId(gradeService.createKey("gradeId"));
			}
			if(createPk==false){
                 if(gradeService.selectOneObject(grade) !=null ){
                    return Result.error("pk-exists","编号重复，请修改编号再提交");
                }
            }
			gradeService.insert(grade);
			return Result.ok().setData(grade);
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
	@ApiOperation( value = "删除一条会员等级定义表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delGrade(@RequestBody Grade grade){
		
		
		try{
            if(!StringUtils.hasText(grade.getGradeId())) {
                 return Result.error("pk-not-exists","请上送主键参数gradeId");
            }
            Grade gradeDb = gradeService.selectOneObject(grade);
            if( gradeDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			gradeService.deleteByPk(grade);
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
	@ApiOperation( value = "根据主键修改一条会员等级定义表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=Grade.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editGrade(@RequestBody Grade grade) {
		
		
		try{
            if(!StringUtils.hasText(grade.getGradeId())) {
                 return Result.error("pk-not-exists","请上送主键参数gradeId");
            }
            Grade gradeDb = gradeService.selectOneObject(grade);
            if( gradeDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			gradeService.updateSomeFieldByPk(grade);
			return Result.ok().setData(grade);
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
    @ApiEntityParams( value = Grade.class, props={ }, remark = "会员等级定义表", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=Grade.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> gradeMap) {
		
		
		try{
            List<String> gradeIds= (List<String>) gradeMap.get("gradeIds");
			if(gradeIds==null || gradeIds.size()==0){
				return Result.error("gradeIds-0","gradeIds不能为空");
			}

			Set<String> fields=new HashSet<>();
            fields.add("gradeId");
			for (String fieldName : gradeMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=gradeMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(gradeMap.get(i) )).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			Grade grade = fromMap(gradeMap,Grade.class);
			List<Grade> gradesDb=gradeService.selectListByIds(gradeIds);
			if(gradesDb==null ||gradesDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<Grade> can=new ArrayList<>();
			List<Grade> no=new ArrayList<>();
			User user = LoginUtils.getCurrentUserInfo();
			for (Grade gradeDb : gradesDb) {
				Tips tips2 = new Tips("检查通过"); 
				if(!tips2.isOk()){
				    no.add(gradeDb); 
				}else{
					can.add(gradeDb);
				}
			}
			if(can.size()>0){
                gradeMap.put("gradeIds",can.stream().map(i->i.getGradeId()).collect(Collectors.toList()));
			    gradeService.editSomeFields(gradeMap); 
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
	@ApiOperation( value = "根据主键列表批量删除会员等级定义表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelGrade(@RequestBody List<Grade> grades) {
		
         
        try{ 
            if(grades.size()<=0){
                return Result.error("data-0","请上送待删除数据列表");
            }
             List<Grade> datasDb=gradeService.selectListByIds(grades.stream().map(i-> i.getGradeId() ).collect(Collectors.toList()));

            List<Grade> can=new ArrayList<>();
            List<Grade> no=new ArrayList<>();
            for (Grade data : datasDb) {
                if(true){
                    can.add(data);
                }else{
                    no.add(data);
                } 
            }
            List<String> msgs=new ArrayList<>();
            if(can.size()>0){
                gradeService.batchDelete(can);
                msgs.add(String.format("成功删除%s条数据.",can.size()));
            }
    
            if(no.size()>0){ 
                msgs.add(String.format("以下%s条数据不能删除.【%s】",no.size(),no.stream().map(i-> i.getGradeId() ).collect(Collectors.joining(","))));
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
}
