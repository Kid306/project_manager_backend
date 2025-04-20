package com.xm.core.ctrl;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdp.core.entity.Tips;
import com.mdp.core.utils.NumberUtil;
import com.mdp.core.utils.ObjectTools;
import com.mdp.msg.client.PushNotifyMsgService;
import com.xm.core.PubTool;
import com.xm.core.entity.*;
import com.xm.core.entity.XmKpi;
import com.xm.core.service.*;
import com.xm.core.service.push.XmPushMsgService;
import com.xm.core.vo.BatchChangeParentKpiVo;
import com.xm.core.vo.ImportKpiByDictVo;
import com.xm.core.vo.ImportKpiByTplVo;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.entity.LangTips;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;

import com.xm.core.entity.XmKpi;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author 唛盟开源 code-gen
 * @since 2025-4-4
 */
@RestController
@RequestMapping(value="/xm/core/xmKpi")
@Api(tags={"项目或指标关键指标考核-操作接口"})
public class XmKpiController {
	
	static Logger logger =LoggerFactory.getLogger(XmKpiController.class);

    @Autowired
    private XmRecordService xmRecordService;

    @Autowired
    private XmPushMsgService xmPushMsgService;
    @Autowired
    private XmProjectService xmProjectService;
    @Autowired
    private XmProjectQxService projectQxService;

    @Autowired
    PushNotifyMsgService notifyMsgService;
	
	@Autowired
	private XmKpiService xmKpiService;

	@Operation( summary =  "项目或指标关键指标考核-查询列表")
	@ApiEntityParams(XmKpi.class)
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmKpi(  @RequestParam Map<String,Object> params){
			User user=LoginUtils.getCurrentUserInfo();
        params.put("branchId",user.getBranchId());
			QueryWrapper<XmKpi> qw = QueryTools.initQueryWrapper(XmKpi.class , params);
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = xmKpiService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
	}
	

	@Operation( summary =  "项目或指标关键指标考核-新增")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmKpi(@RequestBody XmKpi xmKpi) {
        User user=LoginUtils.getCurrentUserInfo();
        if(ObjectTools.isNotEmpty(xmKpi.getParentId()) && !"0".equals(xmKpi.getParentId())) ;
        List<XmKpi> xmKpis=this.xmKpiService.listHasQxKpisByIds(Arrays.asList(xmKpi.getParentId()),user.getUserid());
        if(xmKpis==null || xmKpis.size()==0){
            return Result.error("no-qx-insert-to-parent","无权限新增指标到上级【%s】中",xmKpi.getParentId());
        }
        if(ObjectTools.isEmpty(xmKpi.getStartTime()) || ObjectTools.isEmpty(xmKpi.getEndTime())){
            List<Date> dates= PubTool.calcStartEndDate(new Date(),xmKpi.getCalcType());
            xmKpi.setStartTime(dates.get(0));
            xmKpi.setEndTime(dates.get(1));
        }
        xmKpi.setId(this.xmKpiService.createKey("id"));
        xmKpiService.parentIdPathsCalcBeforeSave(xmKpi);
        xmKpiService.save(xmKpi);
         xmKpiService.sumParents(xmKpi,xmKpi.getId());
         return Result.ok("add-ok","添加成功！").setData(xmKpi);
	}

	@Operation( summary =  "项目或指标关键指标考核-删除")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmKpi(@RequestBody XmKpi xmKpi){
        User user=LoginUtils.getCurrentUserInfo();
        List<XmKpi> xmKpis=this.xmKpiService.listHasQxKpisByIds(Arrays.asList(xmKpi.getId()),user.getUserid());
		if(xmKpis==null || xmKpis.size()==0){
            return Result.error("no-qx-del","无权限删除");
        }
        xmKpiService.removeById(xmKpi.getId());
        xmKpiService.sumParents(xmKpis.get(0),xmKpi.getId());
        return Result.ok("del-ok","删除成功！");
	}

	@Operation( summary =  "项目或指标关键指标考核-修改")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmKpi(@RequestBody XmKpi xmKpi) {
		xmKpiService.updateById(xmKpi);
        return Result.ok("edit-ok","修改成功！");
	}

    @Operation( summary =  "项目或指标关键指标考核-批量修改某些字段" )
    @ApiEntityParams( value = XmKpi.class, props={ }, remark = "项目或指标关键指标考核", paramType = "body" )
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(   @RequestBody Map<String,Object> params) {
            User user= LoginUtils.getCurrentUserInfo();
            List<String> pks= (List<String>) params.get("$pks");
            if(pks==null || pks.size()==0){
                return Result.error("pks-0","请上送主键");
            }
            Set<String> fields=new HashSet<>();
            fields.add("childrenCnt");
            fields.add("pidPaths");
            fields.add("parentId");
            //fields.add("ptype");
            for (String fieldName : params.keySet()) {
                if(fields.contains(fieldName)){
                    return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
                }
            }
            // 判断我有没有权限改，业务逻辑：我是需要修改的数据上的任意上级或者自身的负责人，才能改下面的子节点数据
            List<XmKpi> kpis=xmKpiService.listHasQxKpisByIds(pks,user.getUserid());
            if(kpis==null || kpis.size()==0){
                return Result.error("no-qx-change","没有权限修改，只有负责人、上级负责人可以修改数据");
            }
            List<XmKpi> can=kpis;
            if(params.containsKey("score")){
                Integer score=NumberUtil.getInteger(params.get("score"));
                if(score<0||score>100){
                    return Result.error("score-err","评分必须在0-100之间");
                }
                params.put("kselfTime",new Date());
                can=kpis.stream().filter(k->k.getChildrenCnt()==null || k.getChildrenCnt()==0).collect(Collectors.toList());
                if(can.size()==0){
                    return Result.error("childrenCnt-not-zero","以下指标具有子指标，分数由下级汇总，无须手动修改。【%s】",kpis.stream().map(k->k.getKpiName()).collect(Collectors.joining(",")));
                }
            }
            if(params.containsKey("kadmStatus")){
                params.put("kadmTime",new Date());
            }
            if(params.containsKey("weight")){
                Integer weight=NumberUtil.getInteger(params.get("weight"));
                if(weight<0||weight>100){
                    return Result.error("weight-err","权重必须在0-100之间，并且在同一个父类下，所有儿子节点的权重和必须<=100");
                }
            }
            List<String> ids=can.stream().map(k->k.getId()).collect(Collectors.toList());
            params.put("$pks",ids);
            xmKpiService.editSomeFields(params);
            // 更新上级数据
            if(params.containsKey("score")||params.containsKey("weight")){
                xmKpiService.batchSumParents(kpis);
            }
            //审核通过存储指标数据到历史库
            if(params.containsKey("kadmStatus") && "3".equals(params.get("kadmStatus"))){
                xmKpiService.loadToHis(ids);
            }
            for (XmKpi kpi : can) {
                String remark= ChangeLogService.getChangeLogsString(params,kpi);
                xmRecordService.addXmKpiRecord(kpi.getDeptid(),kpi.getProjectId(),kpi.getId(),"指标-批量修改","修改指标："+remark);
            }
            return Result.ok("edit-ok","更新成功");
	}

	@Operation( summary =  "项目或指标关键指标考核-批量删除")
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmKpi(@RequestBody List<XmKpi> xmKpis) {
	    User user= LoginUtils.getCurrentUserInfo();
        if(xmKpis.size()<=0){
            return Result.error("batchDel-data-err-0","请上送待删除数据列表");
        }
        List<XmKpi> datasDb= xmKpiService.listHasQxKpisByIds(xmKpis.stream().map(i-> i.getId() ).collect(Collectors.toList()),user.getUserid());
        if(datasDb==null || datasDb.size()<=0){
            return Result.error("no-qx-del-err-0","无权限删除");
        }
        List<XmKpi> can=new ArrayList<>();
        List<XmKpi> no=xmKpis.stream().filter(k-> !datasDb.stream().filter(d->d.getId().equals(k.getId())).findAny().isPresent()).collect(Collectors.toList());
        can=datasDb;
        List<String> msgs=new ArrayList<>();
        if(can.size()>0){
            xmKpiService.removeByIds(can);
            msgs.add(LangTips.transMsg("del-ok-num","成功删除%s条数据.",can.size()));
            xmRecordService.addXmKpiRecord(can,"kpi-批量删除","删除指标");
            xmKpiService.batchSumParents(can);
        }

        if(no.size()>0){
            msgs.add(LangTips.transMsg("not-allow-del-num","以下%s条数据不能删除:【%s】",no.size(),no.stream().map(i-> i.getId() ).collect(Collectors.joining(","))));
        }
        if(can.size()>0){
             return Result.ok(msgs.stream().collect(Collectors.joining()));
        }else {
            return Result.error(msgs.stream().collect(Collectors.joining()));
        }
	}


    @Operation( summary =  "项目或指标关键指标考核-批量从指标库导入")
    @RequestMapping(value="/batchImportFromDict",method=RequestMethod.POST)
    public Result batchImportFromDict(@RequestBody ImportKpiByDictVo vo) {
        User user= LoginUtils.getCurrentUserInfo();
        List<XmKpi> xmKpis=vo.getXmKpis();
        if(xmKpis==null || xmKpis.size()<=0){
            return Result.error("xm-kpis-data-err-0","请上送待导入的指标列表");
        }
        for (XmKpi xmKpi : xmKpis) {
            this.initForAdd(xmKpi);
            if(ObjectTools.isEmpty(xmKpi.getKtype()) && ObjectTools.isNotEmpty(vo.getProjectId())){
                xmKpi.setKtype("3");
            }else if(ObjectTools.isEmpty(xmKpi.getKtype()) && ObjectTools.isEmpty(vo.getProjectId())){
                if(ObjectTools.isNotEmpty(vo.getParentId()) && !"0".equals(vo.getParentId())){
                    xmKpi.setKtype("1");
                }else{
                    xmKpi.setKtype("2");
                } 
            } 
        }
        ImportKpiByTplVo batchImportVo=new ImportKpiByTplVo();
        batchImportVo.setXmKpis(xmKpis);
        batchImportVo.setProjectId(vo.getProjectId());
        batchImportVo.setParentId(vo.getParentId());
        batchImportVo.setSourceType("some"); 
        return this.batchImportFromTemplate(batchImportVo); 

    }
    
    public void initForAdd(XmKpi xmKpi){
        User user= LoginUtils.getCurrentUserInfo();
        if(ObjectTools.isEmpty(xmKpi.getKstatus())){
            xmKpi.setKstatus("0");
        }
        if(ObjectTools.isEmpty(xmKpi.getCalcType())){
            xmKpi.setCalcType("Q");
        }

        if(ObjectTools.isEmpty(xmKpi.getStartTime())||ObjectTools.isEmpty(xmKpi.getEndTime())){
             List<Date> dates=PubTool.calcStartEndDate(new Date(),xmKpi.getCalcType());
            xmKpi.setStartTime(dates.get(0));
            xmKpi.setEndTime(dates.get(1));
            xmKpi.setNextCalcDate(xmKpi.getEndTime());
        }
        xmKpi.setKadmTime(null);
        xmKpi.setScoreDate(null);
        xmKpi.setKadmUserid(null);
        xmKpi.setKadmUsername(null);
        xmKpi.setKadmStatus("0");
        if(ObjectTools.isEmpty(xmKpi.getKselfUserid())){
            xmKpi.setKselfUserid(user.getUserid());
            xmKpi.setKselfUsername(user.getUsername());
            xmKpi.setDeptid(user.getDeptid());
            xmKpi.setBranchId(user.getBranchId());
        }
    }

    @ApiOperation( value = "批量导入指标-从模板导入",notes="batchDelXmKpi,仅需要上传主键字段")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
    })
    //@HasQx(value = "xm_core_xmKpi_batchImportFromTemplate",name = "从模板导入指标",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
    @RequestMapping(value="/batchImportFromTemplate",method=RequestMethod.POST)
    public Result batchImportFromTemplate(@RequestBody ImportKpiByTplVo batchImportVo) {

        List<XmKpi> xmKpis=batchImportVo.getXmKpis();
        User user=LoginUtils.getCurrentUserInfo();
        if("some".equals(batchImportVo.getSourceType()) && (xmKpis==null || xmKpis.size()==0)){
            return Result.error("指标列表不能为空");
        }
        if("prpjectAll".equals(batchImportVo.getSourceType()) && ObjectTools.isEmpty(batchImportVo.getSourceProjectId())){
            return Result.error("sourceProjectId-0","请上送源项目编号");
        }
        String projectId=batchImportVo.getProjectId();
        if(ObjectTools.isNotEmpty(projectId)){
            XmProject xmProject=xmProjectService.getProjectFromCache(projectId);
            Tips tips1=projectQxService.checkProjectQx(xmProject,2,user);
            Result.assertIsFalse(tips1);
        }

        if("projectAll".equals(batchImportVo.getSourceType())){
            xmKpis=this.xmKpiService.list(new LambdaQueryWrapper<XmKpi>().eq(XmKpi::getProjectId,batchImportVo.getSourceProjectId()));
            if(xmKpis.size()==0){
                return Result.error("kpis-data-0","项目【%s】没有指标可导入",batchImportVo.getSourceProjectId());
            }
        }


        XmKpi parent;
        if(ObjectTools.isNotEmpty(batchImportVo.getParentId()) && !"0".equals(batchImportVo.getParentId())){
            List<XmKpi> xmKpis2=this.xmKpiService.listHasQxKpisByIds(Arrays.asList(batchImportVo.getParentId()),user.getUserid());
            if(xmKpis2==null || xmKpis2.size()==0){
                return Result.error("no-qx-import-to-parent","无权限导入到上级【%s】中",batchImportVo.getParentId());
            }
            parent=xmKpis2.get(0);
        } else {
            parent = null;
        }
        Map<String,String> newIdMap=new HashMap<>();
        if(StringUtils.hasText(batchImportVo.getParentId())){
            newIdMap.put(batchImportVo.getParentId(),batchImportVo.getParentId());
        }else{
            batchImportVo.setParentId("0");

            newIdMap.put(batchImportVo.getParentId(),batchImportVo.getParentId());
        }
        for (XmKpi xmKpi : xmKpis) {
            newIdMap.put(xmKpi.getId(),this.xmKpiService.createKey("id"));
        }
        for (XmKpi g : xmKpis) {
            g.setId(newIdMap.get(g.getId()));
            if (StringUtils.hasText(g.getParentId())) {
                if (newIdMap.containsKey(g.getParentId())) {
                    g.setParentId(newIdMap.get(g.getParentId()));
                } else {
                    g.setParentId(batchImportVo.getParentId());
                }
            } else {
                g.setParentId(batchImportVo.getParentId());
            }
            g.setBranchId(user.getBranchId());
            g.setProjectId(projectId);
            g.setDeptid(user.getDeptid());
        }
        /**
         * 计算子节点数量
         */
        Date base=new Date();
        if(parent!=null && ObjectTools.isNotEmpty(parent.getStartTime())){
            base=parent.getStartTime();
        }
        for (XmKpi xmKpi : xmKpis) {
            List<XmKpi> childs=xmKpis.stream().filter(k->xmKpi.getId().equals(k.getParentId())).collect(Collectors.toList());
            xmKpi.setChildrenCnt(childs.size());

            //处理时间
            List<Date> dates=PubTool.calcStartEndDate(base,xmKpi.getCalcType());
            xmKpi.setStartTime(dates.get(0));
            xmKpi.setEndTime(dates.get(1));
        }
        xmKpiService.parentIdPathsCalcBeforeSave(xmKpis);

        //排序号生成 按##.##.##格式生成
        PubTool.initKpisSeqNo(parent,xmKpis);


        xmKpiService.batchImportFromTemplate(xmKpis);
        if(parent!=null){
            // 设置默认值，防止空指针异常
            parent.setChildrenCnt(NumberUtil.getInteger(parent.getChildrenCnt(),0));
            List<XmKpi> childs=xmKpis.stream().filter(k->parent.getId().equals(k.getParentId())).collect(Collectors.toList());
            if(childs.size()>0){
                parent.setChildrenCnt(childs.size()+NumberUtil.getInteger(parent.getChildrenCnt(),0));
            }
        }
        if(parent!=null){
            this.xmKpiService.lambdaUpdate().set(XmKpi::getChildrenCnt,parent.getChildrenCnt()).eq(XmKpi::getId,parent.getId()).update();
        }
        for (XmKpi t : xmKpis) {

            if(!user.getUserid().equals(t.getKselfUserid()) && !"0".equals(t.getKstatus())) {
                notifyMsgService.pushMsg(user.getUserid(), t.getKselfUserid(),  "您成为kpi指标【" + t.getKpiName() + "】的负责人，请注意跟进。","");
            }
            xmRecordService.addXmKpiRecord(t.getDeptid(),t.getProjectId(), t.getId(),"kpi-批量新增", "新增kpi"+t.getKpiName());

        }
        return Result.ok();
    }


    /***/
    @ApiOperation( value = "批量修改指标的上级",notes="batchChangeParentKpi,仅需要上传主键字段")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
    })
    //@HasQx(value = "xm_core_xmKpi_batchChangeParentKpi",name = "批量修改指标的上级",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
    @RequestMapping(value="/batchChangeParentKpi",method=RequestMethod.POST)
    public Result batchChangeParentKpi(@RequestBody List<BatchChangeParentKpiVo> xmKpisVo) {

        User user=LoginUtils.getCurrentUserInfo();

        if(xmKpisVo==null || xmKpisVo.size()==0){
            return Result.error("指标列表不能为空");

        }
        Set<String> childIds=new HashSet<>();
        Set<String> parentKpiids=new HashSet<>();
        Map<String,String> newIdLinks=new HashMap<>();
        for (BatchChangeParentKpiVo link : xmKpisVo) {
            if(ObjectTools.isEmpty(link.getParentId())||"0".equals(link.getParentId())){
                link.setParentId("0");
            }else{
                parentKpiids.add(link.getParentId());
            }

            if(ObjectTools.isEmpty(link.getId())){
                return Result.error("指标编号不能为空");
            }
            childIds.add(link.getId());
            newIdLinks.put(link.getId(),link.getParentId());
        }

        List<String> allIds=childIds.stream().collect(Collectors.toList());
        allIds.addAll(parentKpiids);
        List<XmKpi> allXmKpis=this.xmKpiService.listHasQxKpisByIds(allIds,user.getUserid());
        if(allXmKpis==null || allXmKpis.size()<=0){
            return Result.error("no-qx-change-parent","无权限修改上级");
        }
        List<XmKpi> childKpis=allXmKpis.stream().filter(k->childIds.contains(k.getId())).collect(Collectors.toList());
        List<XmKpi> parentKpis=allXmKpis.stream().filter(k->parentKpiids.contains(k.getId())).collect(Collectors.toList());

        if(childKpis==null || childKpis.size()==0){
            return Result.error("no-qx-to-change-parent-from","无权限修改下面指标的上级。【%s】",childIds.stream().collect(Collectors.joining(",")));
        }
        if((parentKpis==null || parentKpis.size()==0) && parentKpiids.size()>0){//有可能上级是0，没有放进parentKpiids
            return Result.error("no-qx-to-change-parent-to","无权限将指标挂到以下上级指标中。【%s】",parentKpiids.stream().collect(Collectors.joining(",")));
        }

        // 过滤掉新旧上级一致的指标
        childKpis=childKpis.stream().filter(k->!k.getParentId().equals(newIdLinks.get(k.getId()))).collect(Collectors.toList());
        if(childKpis==null || childKpis.size()==0){
            return Result.error("id-link-same-0","新旧上级一致，无须保存");
        }

        Map<String,XmKpi> xmKpiMap=new HashMap<>();
        for (XmKpi xmKpi : childKpis) {
            xmKpiMap.put(xmKpi.getId(),xmKpi);
        }
        for (XmKpi xmKpi : parentKpis) {
            xmKpiMap.put(xmKpi.getId(),xmKpi);
        }
      


        /**
         * 按上级进行分组
         */
        Map<String,List<XmKpi>> xmMaps=new HashMap<>();
        for (XmKpi xmKpi : childKpis) {
            String newPid=newIdLinks.get(xmKpi.getId());
            XmKpi parent=null;
            String pidPaths=null;
            if(ObjectTools.isEmpty(newPid)||"0".equals(newPid)){//向根迁移
                pidPaths="0,";
            }else{
                parent=xmKpiMap.get(newPid);
                pidPaths=parent.getPidPaths();
            }

            List<XmKpi> kpis=xmMaps.get(pidPaths);
            if(kpis==null){
                kpis=new ArrayList<>();
                kpis.add(xmKpi);
            }else{
                kpis.add(xmKpi);
            }
            xmMaps.put(pidPaths,kpis);
        }

        // 分组后要进行降序排序，从底层往上更新
        List<String> pidPathsList = xmMaps.keySet().stream().sorted((x,y)->{
            return y.split(",").length-x.split(",").length;
        }).collect(Collectors.toList());
        for (String pidPaths : pidPathsList) {
            List<XmKpi> kpis=xmMaps.get(pidPaths);
            //上级为顶级的情况处理
            if("0".equals(pidPaths)||"0,".equals(pidPaths)||ObjectTools.isEmpty(pidPaths)){
                this.xmKpiService.batchChangeParent(kpis,null);
                for (XmKpi kpi : kpis) {
                    this.xmRecordService.addXmKpiRecord(kpi.getDeptid(),kpi.getProjectId(),kpi.getId(), "指标-批量更新上级",String.format("批量更新上级:新值【%s】【%s】，旧值【%s】","0","顶级",kpi.getParentId()));
                }
            }else{
                String[] parentKpiIds=pidPaths.split(",");
                String parentKpiId=parentKpiIds[parentKpiIds.length-1];
                //必须重新查询,因为可能上一次循环已经更新了数据库中的pidPaths
                XmKpi parentKpi=this.xmKpiService.getById(parentKpiId);
                this.xmKpiService.batchChangeParent(kpis,parentKpi);
                for (XmKpi kpi : kpis) {
                    this.xmRecordService.addXmKpiRecord(kpi.getDeptid(),kpi.getProjectId(),kpi.getId(), "指标-批量更新上级",String.format("批量更新上级:新值【%s】【%s】，旧值【%s】",parentKpi.getId(),parentKpi.getKpiName(),kpi.getParentId()));
                }
            }

        }
        return Result.ok();

    }

    @ApiOperation("统计所有上级的进度情况")
    @RequestMapping(value="/calcProgress",method=RequestMethod.POST)
    public Result calcProgress( @ApiIgnore @RequestBody List<String> ids){
        User user=LoginUtils.getCurrentUserInfo();
        List<XmKpi> kpis=this.xmKpiService.listByIds(ids);
        this.xmKpiService.batchSumParents(kpis);
        return Result.ok("成功");
    }

    public void paramsInit(Map<String, Object> params) {
        User user = LoginUtils.getCurrentUserInfo();
        params.put("branchId", user.getBranchId());
    }
    @RequestMapping(value = "/getXmKpiAttDist", method = RequestMethod.GET)
    public Result getXmMenuAttDist(@ApiIgnore @RequestParam Map<String, Object> params) {
        this.paramsInit(params);
        IPage page = QueryTools.initPage(params);
        QueryWrapper<XmKpi> qw = QueryTools.initQueryWrapper(XmKpi.class, params);
        List<Map<String, Object>> datas = this.xmKpiService.getXmKpiAttDist(page, qw, params);
        return Result.ok("ok", "成功").setData(datas).setTotal(page.getTotal());
    }

    @RequestMapping(value = "/getXmKpiHis", method = RequestMethod.GET)
    public Result getXmKpiHis(@ApiIgnore @RequestParam String id) {
        List<Map<String, Object>> datas = this.xmKpiService.getXmKpiHis(id);
        return Result.ok("ok", "成功").setData(datas).setTotal(datas.size());
    }
    
    @Operation( summary =  "项目或指标关键指标考核-根据主键查询一条数据")
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(XmKpi xmKpi) {
        XmKpi data = (XmKpi) xmKpiService.getById(xmKpi);
        return Result.ok().setData(data);
    }

}
