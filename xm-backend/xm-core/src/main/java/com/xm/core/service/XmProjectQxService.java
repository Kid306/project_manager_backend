package com.xm.core.service;

import com.mdp.core.entity.Tips;
import com.mdp.core.utils.ObjectTools;
import com.mdp.safe.client.entity.User;
import com.xm.core.QxTool;
import com.xm.core.entity.XmProject;
import com.xm.core.service.client.SysClient;
import com.xm.core.vo.XmGroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 权限码0,1,2,3,4,5,67,8,9，逗号分割
 共10位,不定长，暂时只启用前6个位
 第0位代表团队建立及成员管理及crud权限：
 0-代表不限制,1-同组织，2-同项目组（默认），3-同小组
 第1位代表团队建立及成员管理及crud权限是否检查上下级关系：0-否（默认），1是
 第2位代表测试指派及crud时权限，同第0位
 第3位代表测试指派及crud时是否检查上下级关系，同第1位
 第4位代表任务指派及crud时权限，同第0位
 第5位代表任务指派及crud时是否检查上下级关系，同第1位
 */
@Service
public class XmProjectQxService {


    @Autowired
    XmGroupService xmGroupService;


    @Autowired
    SysClient sysClient;


    /**
     *
     * @param xmProject
     * @param teamType 0-团队 1-测试相关,2-任务相关
     * @return
     */
    public Tips checkProjectQx(XmProject xmProject, int teamType, String userid,String username,String branchId){
        return this.checkProjectScopeQx( xmProject,teamType,userid,username,branchId);
    }
    /**
     *
     * @param xmProject
     * @param teamType 0-团队 1-测试相关,2-任务相关
     * @param head
     * @return
     */
    public Tips checkProjectQx(XmProject xmProject,int teamType,User head){
        Tips tips=this.checkProjectScopeQx(xmProject,teamType,head.getUserid(),head.getUsername(),head.getBranchId());
        return tips;
    }
    /**
     *
     * @param xmProject
     * @param teamType 0-团队 1-测试相关,2-任务相关
     * @param head
     * @return
     */
    public Tips checkProjectQx(XmProject xmProject,int teamType,User head,String memUserid,String memUsername,String memBranchId){
        Tips tips=new Tips("成功");
        tips=this.checkProjectScopeQx(xmProject,teamType,head,memUserid,memUsername,memBranchId);
        if(!tips.isOk()){
            return tips;
        }
        return this.checkProjectTransmitQx(xmProject,teamType,head,memUserid,memUsername);
    }



    public Tips checkProjectScopeQx(XmProject xmProject,int teamType,User head,String memUserid,String memUsername,String memBranchId){
        Tips tips=new Tips("成功");

        if(!StringUtils.hasText(memUserid)|| memUserid.equals(head.getUserid())){
            return this.checkProjectScopeQx(xmProject,teamType,head.getUserid(),head.getUsername(),head.getBranchId());
        }
        String headUsername=StringUtils.hasText(head.getUsername())?head.getUsername():head.getUserid();
        String scopeQx= QxTool.getProjectScopeQx(xmProject.getQxCode(),teamType);
        memUsername=StringUtils.hasText(memUsername)?memUsername:memUserid;

        boolean headIsPm=xmGroupService.checkUserIsProjectAdm(xmProject,head.getUserid());
        if("1".equals(scopeQx)){//同组织
            if( !headIsPm && !head.getBranchId().equals(xmProject.getBranchId())){
                tips.setErrMsg("pjqx-scope-branch-0",String.format("项目【%s】只开放给同企业人员,【%s】不在企业【%s】中。",xmProject.getId(),headUsername,xmProject.getBranchId()));
                return tips;
            }
            if(xmGroupService.checkUserIsProjectAdm(xmProject,memUserid)){
                return tips;
            }
            if(!StringUtils.hasText(memBranchId)){
                User sysU=sysClient.getUserByUserid(memUserid);
                if(sysU==null || StringUtils.isEmpty(sysU.getUserid())){
                    return tips;
                }
                if(sysU.getBranchId().equals(xmProject.getBranchId())){
                    return tips;
                }
            }else if (memBranchId.equals(xmProject.getBranchId())){
                return tips;
            }
            tips.setErrMsg("pjqx-scope-branch-1",String.format("项目【%s】只开放给同企业人员,【%s】不在企业【%s】中。",xmProject.getId(),memUsername,xmProject.getBranchId()));
            return tips;


        }else if("2".equals(scopeQx)){//同项目
            List<XmGroupVo> groups=this.getProjectGroupsFromLocalCache(xmProject.getId());
            if( !headIsPm && !xmGroupService.checkUserExistsGroup(groups, head.getUserid())){
                tips.setErrMsg("pjqx-scope-project-0",String.format("项目【%s】只开放给同一个项目团队人员,【%s】不在项目团队中。",xmProject.getId(),headUsername));
                return tips;
            };
            if(xmGroupService.checkUserIsProjectAdm(xmProject,memUserid)){
                return tips;
            }
            if( xmGroupService.checkUserExistsGroup(groups,memUserid) ){
                return tips;
            };
            tips.setErrMsg("pjqx-scope-project-1",String.format("项目【%s】只开放给同一个项目团队人员,【%s】不在项目团队中。",xmProject.getId(),memUsername));
            return tips;
        }else if("3".equals(scopeQx)){//同小组
            List<XmGroupVo> groups=this.getProjectGroupsFromLocalCache(xmProject.getId());
            List<XmGroupVo> headGroups=groups;

            if( !headIsPm ){
                headGroups=xmGroupService.getUserGroups(groups,head.getUserid());
                if( headGroups==null || headGroups.size()==0 ){
                    tips.setErrMsg("pjqx-scope-team-1",String.format("项目【%s】只开放给同一个项目团队下同一个小组人员,【%s】不在项目团队中。",xmProject.getId(),headUsername));
                    return tips;
                }
            }

            if(xmGroupService.checkUserIsProjectAdm(xmProject,memUserid)){
                return tips;
            }
            List<XmGroupVo> memGroups=xmGroupService.getUserGroups(headGroups,memUserid);
            if( memGroups!=null && memGroups.size()>0 ){
                return tips;
            }
            tips.setErrMsg("pjqx-scope-team-2",String.format("项目【%s】只开放给同一个项目团队下同一个小组人员,【%s】与【%s】不在同一个小组内。",xmProject.getId(),memUsername,head.getUsername()));
            return tips;
        }
        return tips;
    }

    /**
     * 检查用户是否满足项目权限控制要求
     * @param xmProject
     * @param teamType
     * @param memUserid
     * @param memUsername 只用于显示
     * @param memBranchId
     * @return
     */
    public Tips checkProjectScopeQx(XmProject xmProject,int teamType,String memUserid,String memUsername,String memBranchId){
        Tips tips=new Tips("成功");
        String scopeQx= QxTool.getProjectScopeQx(xmProject.getQxCode(),teamType);
        boolean isPm=xmGroupService.checkUserIsProjectAdm(xmProject,memUserid);
        if(isPm){
            return tips;
        }
        memUsername=StringUtils.hasText(memUsername)?memUsername:memUserid;
        if("1".equals(scopeQx)){//同组织
            if(!StringUtils.hasText(memBranchId)){
                User sysUser=sysClient.getUserByUserid(memUserid);
                if(sysUser==null || StringUtils.isEmpty(sysUser.getUserid())){
                    //如果账户不存在，可能已注销，再判断没有意义，会导致企业无法操作遗留数据问题
                    //tips.setErrMsg("no-qx-branchId","项目【"+xmProject.getId()+"】只开放给同企业人员。");
                    return tips;
                }
                memBranchId=sysUser.getBranchId();
                if(ObjectTools.isEmpty(memBranchId) || !memBranchId.equals(xmProject.getBranchId())){
                    tips.setErrMsg("pjqx-scope-branch-0",String.format("项目【%s】只开放给同企业人员,【%s】不在企业【%s】中。",xmProject.getName(),memUsername,xmProject.getBranchId()));
                    return tips;
                }
            }else{
                if(!memBranchId.equals(xmProject.getBranchId())){
                    tips.setErrMsg("pjqx-scope-branch-0",String.format("项目【%s】只开放给同企业人员,【%s】不在企业【%s】中。",xmProject.getName(),memUsername,xmProject.getBranchId()));
                    return tips;
                }
            }

        }else if("2".equals(scopeQx)){//同项目
            if(!xmGroupService.checkUserExistsGroup(getProjectGroupsFromLocalCache(xmProject.getId()), memUserid)){
                tips.setErrMsg("pjqx-scope-project-0",String.format("项目【%s】只开放给同一个项目团队人员,【%s】不在项目团队中。",xmProject.getName(),memUsername));
                return tips;
            };
        }else if("3".equals(scopeQx)){//同小组

            if(!xmGroupService.checkUserExistsGroup(getProjectGroupsFromLocalCache(xmProject.getId()), memUserid)){
                tips.setErrMsg("pjqx-scope-team-0",String.format("项目【%s】只开放给同一个项目团队下同一个小组人员,【%s】不在项目团队中。",xmProject.getName(),memUsername));
                return tips;
            }
        }
        return tips;
    }


    public Tips checkProjectTransmitQx(XmProject xmProject,int teamType,User head){
        return checkProjectTransmitQx(xmProject,teamType,head,null,null);
    }
    public Tips checkProjectTransmitQx(XmProject xmProject,int teamType,User head,String memUserid,String memUsername){
        Tips tips=new Tips("成功");
        if(!StringUtils.hasText(memUserid) || memUserid.equals(head.getUserid())){
            return tips;
        }
        String transmitQx= QxTool.getProjectTransmitQx(xmProject.getQxCode(),teamType);
        if("0".equals(transmitQx)){//不检查上下级关系
            return tips;
        }else if("1".equals(transmitQx)){//检查上下级关系
            if(StringUtils.isEmpty(memUserid)||head.getUserid().equals(memUserid)){
                return tips;
            }

            List<XmGroupVo> groups=getProjectGroupsFromLocalCache(xmProject.getId());
            if(xmGroupService.checkUserIsOtherUserTeamHeadOrAss(groups, head.getUserid(), memUserid)){
                return tips;
            }
            tips.setErrMsg("pjqx-transmit-0",
                    String.format("项目【%s】开启了上下级关系检查，您当前账户【%s】不属于账户【%s】的上级，无权操作。",xmProject.getId(),head.getUsername(),memUsername));


        }
        return tips;
    }


    public Tips checkProjectQxBatch(XmProject xmProject,int teamType,User head,String ...memUserids){
        Tips tips=new Tips("成功");
        if(xmGroupService.checkUserIsProjectAdm(xmProject,head.getUserid())){
            return tips;
        }

        tips=this.checkProjectScopeQxBatch(xmProject,teamType,head,memUserids);
        if(!tips.isOk()){
            return tips;
        }
        return this.checkProjectTransmitQxBatch(xmProject,teamType,head.getUserid(),memUserids);
    }

    public Tips checkProjectScopeQxBatch(XmProject xmProject,int teamType,User head,String ...memUserids){
        Tips tips=new Tips("成功");

        if(memUserids==null || memUserids.length==0){
            return this.checkProjectScopeQx(xmProject,teamType,head.getUserid(),head.getUsername(),head.getBranchId());
        }
        Set<String> memUseridSet= Arrays.stream(memUserids).filter(k->StringUtils.hasText(k)&&!k.equals(head.getUserid())).collect(Collectors.toSet());
        if(memUseridSet.size()==0){
            return this.checkProjectScopeQx(xmProject,teamType,head.getUserid(),head.getUsername(),head.getBranchId());
        }
        String username=head.getUsername();
        String scopeQx= QxTool.getProjectScopeQx(xmProject.getQxCode(),teamType);
        boolean headIsPm=xmGroupService.checkUserIsProjectAdm(xmProject,head.getUserid());
        if("1".equals(scopeQx)){//同组织
            if( !headIsPm && !head.getBranchId().equals(xmProject.getBranchId())){
                tips.setErrMsg("pjqx-scope-branch-0",String.format("项目【%s】只开放给同企业人员,【%s】不在企业【%s】中。",xmProject.getId(),username,xmProject.getBranchId()));
                return tips;
            }
            boolean isAllNull=true;
            for (String memUserid : memUseridSet) {//只要有一个满足条件即可
                if(xmGroupService.checkUserIsProjectAdm(xmProject,memUserid)){
                    return tips;
                }
                User sysU=sysClient.getUserByUserid(memUserid);
                if(sysU==null || StringUtils.isEmpty(sysU.getUserid())){
                    continue;
                }
                isAllNull=false;
                if(sysU.getBranchId().equals(xmProject.getBranchId())){
                    return tips;
                }
            }
            if(isAllNull){
                return tips;
            }else{
                tips.setErrMsg("pjqx-scope-branch-1",String.format("项目【%s】只开放给同企业人员,【%s】不在企业【%s】中。",xmProject.getId(),memUseridSet.stream().collect(Collectors.joining(",")),xmProject.getBranchId()));
                return tips;
            }


        }else if("2".equals(scopeQx)){//同项目
            List<XmGroupVo> groups=this.getProjectGroupsFromLocalCache(xmProject.getId());
            if( !headIsPm && !xmGroupService.checkUserExistsGroup(groups, head.getUserid())){
                tips.setErrMsg("pjqx-scope-project-0",String.format("项目【%s】只开放给同一个项目团队人员,【%s】不在项目团队中。",xmProject.getId(),username));
                return tips;
            };
            Set<String> not = new HashSet<>();
            for (String memUserid : memUseridSet) {
                if( !xmGroupService.checkUserIsProjectAdm(xmProject,memUserid) && !xmGroupService.checkUserExistsGroup(groups,memUserid) ){
                    not.add(memUserid);
                };
            }
            if(not.size()<=0){
                return tips;
            }
            tips.setErrMsg("pjqx-scope-project-1",String.format("项目【%s】只开放给同一个项目团队人员,【%s】不在项目团队中。",xmProject.getId(),not.stream().collect(Collectors.joining(","))));
            return tips;
        }else if("3".equals(scopeQx)){//同小组
            List<XmGroupVo> groups=this.getProjectGroupsFromLocalCache(xmProject.getId());
            List<XmGroupVo> headGroups=groups;

            if( !headIsPm ){
                headGroups=xmGroupService.getUserGroups(groups,head.getUserid());
                if( headGroups==null || headGroups.size()==0 ){
                    tips.setErrMsg("pjqx-scope-team-1",String.format("项目【%s】只开放给同一个项目团队下同一个小组人员,【%s】不在项目团队中。",xmProject.getId(),username));
                    return tips;
                }
            }
            Set<String> not=new HashSet<>();
            for (String memUserid : memUseridSet) {
                if(xmGroupService.checkUserIsProjectAdm(xmProject,memUserid)){
                    continue;
                }
                List<XmGroupVo> memGroups=xmGroupService.getUserGroups(headGroups,memUserid);
                if( memGroups!=null && memGroups.size()>0 ){
                    not.add(memUserid);
                }
            }
            if(not.size()==0){
                return tips;
            }
            tips.setErrMsg("pjqx-scope-team-2",String.format("项目【%s】只开放给同一个项目团队下同一个小组人员,【%s】与【%s】不在同一个小组内。",xmProject.getId(),not.stream().collect(Collectors.joining(",")),head.getUsername()));
            return tips;
        }
        return tips;
    }


    List<XmGroupVo> getProjectGroupsFromLocalCache(String projectId){
        List<XmGroupVo> groupVoList= xmGroupService.getProjectGroupVoList(projectId);

        return groupVoList;
    }


    public Tips checkProjectTransmitQxBatch(XmProject xmProject,int teamType,String headUserid,String ...memUserids){
        Tips tips=new Tips("成功");
        String transmitQx= QxTool.getProjectTransmitQx(xmProject.getQxCode(),teamType);
        if("0".equals(transmitQx)){//不检查上下级关系
            return tips;
        }else if("1".equals(transmitQx)){//检查上下级关系
            if(memUserids==null || memUserids.length==0){
                return tips;
            }
            Set<String> memUseridSet= Arrays.stream(memUserids).filter(k->StringUtils.hasText(k) && !k.equals(headUserid)).collect(Collectors.toSet());
            if(memUseridSet.size()==0){
                return tips;
            }
            List<XmGroupVo> groups=getProjectGroupsFromLocalCache(xmProject.getId());
            Set<String> not= new HashSet<>();
            for (String memUserid : memUseridSet) {
                if(!xmGroupService.checkUserIsOtherUserTeamHeadOrAss(groups,headUserid,memUserid)){
                    not.add(memUserid);
                }
            }
            if(not.size()==0){
                return tips;
            }
            tips.setErrMsg("pjqx-transmit-0",
                    String.format("项目【%s】开启了上下级关系检查，您当前账户不属于【%s】的上级，无权操作。",xmProject.getId(),not.stream().collect(Collectors.joining(","))));


        }
        return tips;
    }
}
