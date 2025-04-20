package com.xm.core.service;

import com.mdp.core.entity.Tips;
import com.mdp.core.utils.CollectionUtils;
import com.mdp.safe.client.entity.User;
import com.xm.core.QxTool;
import com.xm.core.entity.XmProduct;
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
 共10位,不定长，暂时只启用前8个位
 第0位代表团队建立及成员管理及crud权限：
 0-代表不限制,1-同组织，2-同项目组（默认），3-同小组
 第1位代表团队建立及成员管理及crud权限是否检查上下级关系：0-否（默认），1是
 第2位代表测试相关(包括测试用例、测试库、测试计划、测试报告)指派及crud权限同第0位，
 第3位代表测试相关(包括测试用例、测试库、测试计划、测试报告)指派及crud时是否检查上下级关系，同第1位
 第4位代表需求指派及crud时权限，同第0位
 第5位代表需求指派及crud时是否检查上下级关系，同第1位
 第6位代表迭代指派及crud时权限，同第0位
 第7位代表迭代指派及crud时是否检查上下级关系，同第1位
 */
@Service
public class XmProductQxService {


    @Autowired
    XmGroupService xmGroupService;


    @Autowired
    SysClient sysClient;


    /**
     *
     * @param xmProduct
     * @param teamType 0-团队 1-测试人员,2-需求人员 3-迭代
     * @param head
     * @return
     */
    public Tips checkProductQx(XmProduct xmProduct, int teamType, User head){
        Tips tips= this.checkProductScopeQx(xmProduct,teamType,head.getUserid(),head.getUsername(),head.getBranchId());
        return tips;
    }
    /**
     *
     * @param xmProduct
     * @param teamType 0-需求人员 1-测试人员,2-迭代人员
     * @param head
     * @return
     */
    public Tips checkProductQx(XmProduct xmProduct,int teamType,User head,String memUserid,String memUsername,String memBranchId){
        Tips tips=new Tips("成功");
        tips=this.checkProductScopeQx(xmProduct,teamType,head,memUserid,memUsername,memBranchId);
        if(!tips.isOk()){
            return tips;
        }
        return this.checkProductTransmitQx(xmProduct,teamType,head,memUserid,memUsername);
    }



    public Tips checkProductScopeQx(XmProduct xmProduct,int teamType,User head,String memUserid,String memUsername,String memBranchId){
        Tips tips=new Tips("成功");

        if(!StringUtils.hasText(memUserid)|| memUserid.equals(head.getUserid())){
            return this.checkProductScopeQx(xmProduct,teamType,head.getUserid(),head.getUsername(),head.getBranchId());
        }
        String headUsername=StringUtils.hasText(head.getUsername())?head.getUsername():head.getUserid();
        String scopeQx= QxTool.getProductScopeQx(xmProduct.getQxCode(),teamType);
        memUsername=StringUtils.hasText(memUsername)?memUsername:memUserid;

        boolean headIsPm=xmGroupService.checkUserIsProductAdm(xmProduct,head.getUserid());
        if("1".equals(scopeQx)){//同组织
            if( !headIsPm && !head.getBranchId().equals(xmProduct.getBranchId())){
                tips.setErrMsg("pdqx-scope-branch-0",String.format("产品【%s】只开放给同企业人员,【%s】不在企业【%s】中。",xmProduct.getId(),headUsername,xmProduct.getBranchId()));
                return tips;
            }
            if(xmGroupService.checkUserIsProductAdm(xmProduct,memUserid)){
                return tips;
            }
            if(!StringUtils.hasText(memBranchId)){
                User sysU=sysClient.getUserByUserid(memUserid);
                if(sysU==null || StringUtils.isEmpty(sysU.getUserid())){
                    return tips;
                }
                if(sysU.getBranchId().equals(xmProduct.getBranchId())){
                    return tips;
                }
            }else if (memBranchId.equals(xmProduct.getBranchId())){
                return tips;
            }
            tips.setErrMsg("pdqx-scope-branch-1",String.format("产品【%s】只开放给同企业人员,【%s】不在企业【%s】中。",xmProduct.getId(),memUsername,xmProduct.getBranchId()));
            return tips;


        }else if("2".equals(scopeQx)){//同产品
            List<XmGroupVo> groups=this.getProductGroupsFromLocalCache(xmProduct.getId());
            if( !headIsPm && !xmGroupService.checkUserExistsGroup(groups, head.getUserid())){
                tips.setErrMsg("pdqx-scope-product-0",String.format("产品【%s】只开放给同一个产品团队人员,【%s】不在产品团队中。",xmProduct.getId(),headUsername));
                return tips;
            };
            if(xmGroupService.checkUserIsProductAdm(xmProduct,memUserid)){
                return tips;
            }
            if( xmGroupService.checkUserExistsGroup(groups,memUserid) ){
                return tips;
            };
            tips.setErrMsg("pdqx-scope-product-1",String.format("产品【%s】只开放给同一个产品团队人员,【%s】不在产品团队中。",xmProduct.getId(),memUsername));
            return tips;
        }else if("3".equals(scopeQx)){//同小组
            List<XmGroupVo> groups=this.getProductGroupsFromLocalCache(xmProduct.getId());
            List<XmGroupVo> headGroups=groups;

            if( !headIsPm ){
                headGroups=xmGroupService.getUserGroups(groups,head.getUserid());
                if( headGroups==null || headGroups.size()==0 ){
                    tips.setErrMsg("pdqx-scope-team-1",String.format("产品【%s】只开放给同一个产品团队下同一个小组人员,【%s】不在产品团队中。",xmProduct.getId(),headUsername));
                    return tips;
                }
            }

            if(xmGroupService.checkUserIsProductAdm(xmProduct,memUserid)){
                return tips;
            }
            List<XmGroupVo> memGroups=xmGroupService.getUserGroups(headGroups,memUserid);
            if( memGroups!=null && memGroups.size()>0 ){
                return tips;
            }
            tips.setErrMsg("pdqx-scope-team-2",String.format("产品【%s】只开放给同一个产品团队下同一个小组人员,【%s】与【%s】不在同一个小组内。",xmProduct.getId(),memUsername,headUsername));
            return tips;
        }
        return tips;
    }

    /**
     * 检查用户是否满足产品权限控制要求
     * @param xmProduct
     * @param teamType
     * @param memUserid
     * @param memUsername 只用于显示
     * @param memBranchId
     * @return
     */
    public Tips checkProductScopeQx(XmProduct xmProduct,int teamType,String memUserid,String memUsername,String memBranchId){
        Tips tips=new Tips("成功");
        String scopeQx= QxTool.getProductScopeQx(xmProduct.getQxCode(),teamType);
        boolean isPm=xmGroupService.checkUserIsProductAdm(xmProduct,memUserid);
        if(isPm){
            return tips;
        }
        memUsername=StringUtils.hasText(memUsername)?memUsername:memUserid;
        if("1".equals(scopeQx)){//同组织
            if(!StringUtils.hasText(memBranchId)){
                User sysUser=sysClient.getUserByUserid(memUserid);
                if(sysUser==null || StringUtils.isEmpty(sysUser.getUserid())){
                    //如果账户不存在，可能已注销，再判断没有意义，会导致企业无法操作遗留数据问题
                    //tips.setErrMsg("no-qx-branchId","产品【"+xmProduct.getId()+"】只开放给同企业人员。");
                    return tips;
                }
                memBranchId=sysUser.getBranchId();
                if(!memBranchId.equals(xmProduct.getBranchId())){
                    tips.setErrMsg("pdqx-scope-branch-0",String.format("产品【%s】只开放给同企业人员,【%s】不在企业【%s】中。",xmProduct.getId(),memUsername,xmProduct.getBranchId()));
                    return tips;
                }
            }else{
                if(!memBranchId.equals(xmProduct.getBranchId())){
                    tips.setErrMsg("pdqx-scope-branch-0",String.format("产品【%s】只开放给同企业人员,【%s】不在企业【%s】中。",xmProduct.getId(),memUsername,xmProduct.getBranchId()));
                    return tips;
                }
            }

        }else if("2".equals(scopeQx)){//同产品
            if(!xmGroupService.checkUserExistsGroup(getProductGroupsFromLocalCache(xmProduct.getId()), memUserid)){
                tips.setErrMsg("pdqx-scope-product-0",String.format("产品【%s】只开放给同一个产品团队人员,【%s】不在产品团队中。",xmProduct.getId(),memUsername));
                return tips;
            };
        }else if("3".equals(scopeQx)){//同小组

            if(!xmGroupService.checkUserExistsGroup(getProductGroupsFromLocalCache(xmProduct.getId()), memUserid)){
                tips.setErrMsg("pdqx-scope-team-0",String.format("产品【%s】只开放给同一个产品团队下同一个小组人员,【%s】不在产品团队中。",xmProduct.getId(),memUsername));
                return tips;
            }
        }
        return tips;
    }


    public Tips checkProductTransmitQx(XmProduct xmProduct,int teamType,User head){
        return checkProductTransmitQx(xmProduct,teamType,head,null,null);
    }
    public Tips checkProductTransmitQx(XmProduct xmProduct,int teamType,User head,String memUserid,String memUsername){
        Tips tips=new Tips("成功");
        if(!StringUtils.hasText(memUserid) || memUserid.equals(head.getUserid())){
            return tips;
        }
        String transmitQx= QxTool.getProductTransmitQx(xmProduct.getQxCode(),teamType);
        if("0".equals(transmitQx)){//不检查上下级关系
            return tips;
        }else if("1".equals(transmitQx)){//检查上下级关系
            if(StringUtils.isEmpty(memUserid)||head.getUserid().equals(memUserid)){
                return tips;
            }

            List<XmGroupVo> groups=getProductGroupsFromLocalCache(xmProduct.getId());
            if(xmGroupService.checkUserIsOtherUserTeamHeadOrAss(groups, head.getUserid(), memUserid)){
                return tips;
            }
            tips.setErrMsg("pdqx-transmit-0",
                    String.format("产品【%s】开启了上下级关系检查，您当前账户【%s】不属于账户【%s】的上级，无权操作。",xmProduct.getId(),head.getUsername(),memUsername));


        }
        return tips;
    }

    public Tips checkProductQxBatch(XmProduct xmProduct,int teamType,User head,String ...memUserids){
        Tips tips=new Tips("成功");
        tips=this.checkProductScopeQxBatch(xmProduct,teamType,head,memUserids);
        if(!tips.isOk()){
            return tips;
        }
        return this.checkProductTransmitQxBatch(xmProduct,teamType,head.getUserid(),memUserids);
    }

    public Tips checkProductScopeQxBatch(XmProduct xmProduct,int teamType,User head,String ...memUserids){
        Tips tips=new Tips("成功");

        if(memUserids==null || memUserids.length==0){
            return this.checkProductScopeQx(xmProduct,teamType,head.getUserid(),head.getUsername(),head.getBranchId());
        }
        Set<String> memUseridSet= Arrays.stream(memUserids).filter(k->StringUtils.hasText(k)&&!k.equals(head.getUserid())).collect(Collectors.toSet());
        if(memUseridSet.size()==0){
            return this.checkProductScopeQx(xmProduct,teamType,head.getUserid(),head.getUsername(),head.getBranchId());
        }
        String username=head.getUsername();
        String scopeQx= QxTool.getProductScopeQx(xmProduct.getQxCode(),teamType);
        boolean headIsPm=xmGroupService.checkUserIsProductAdm(xmProduct,head.getUserid());
        if("1".equals(scopeQx)){//同组织
            if( !headIsPm && !head.getBranchId().equals(xmProduct.getBranchId())){
                tips.setErrMsg("pdqx-scope-branch-0",String.format("产品【%s】只开放给同企业人员,【%s】不在企业【%s】中。",xmProduct.getId(),username,xmProduct.getBranchId()));
                tips.put("noQxUseridSet", CollectionUtils.convertSet(Arrays.asList(head.getUserid())));
                return tips;
            }
            boolean isAllNull=true;
            for (String memUserid : memUseridSet) {//为了减少对sys_user的查询，只要有一个满足条件就放行，一般只要有一个是对的，其它都是同一个机构的。
                if(xmGroupService.checkUserIsProductAdm(xmProduct,memUserid)){
                    return tips;
                }
                User sysU=sysClient.getUserByUserid(memUserid);
                if(sysU==null || StringUtils.isEmpty(sysU.getUserid())){
                    continue;
                }
                isAllNull=false;
                if(sysU.getBranchId().equals(xmProduct.getBranchId())){
                    return tips;
                }
            }
            if(isAllNull){
                return tips;
            }else{
                tips.setErrMsg("pdqx-scope-branch-1",String.format("产品【%s】只开放给同企业人员,【%s】不在企业【%s】中。",xmProduct.getId(),memUseridSet.stream().collect(Collectors.joining(",")),xmProduct.getBranchId()));
                tips.put("noQxUseridSet",memUseridSet);
                return tips;
            }


        }else if("2".equals(scopeQx)){//同产品
            List<XmGroupVo> groups=this.getProductGroupsFromLocalCache(xmProduct.getId());
            if( !headIsPm && !xmGroupService.checkUserExistsGroup(groups, head.getUserid())){
                tips.setErrMsg("pdqx-scope-product-0",String.format("产品【%s】只开放给同一个产品团队人员,【%s】不在产品团队中。",xmProduct.getId(),username));
                tips.put("noQxUseridSet", CollectionUtils.convertSet(Arrays.asList(head.getUserid())));
                return tips;
            };
            Set<String> not=new HashSet<>();
            for (String memUserid : memUseridSet) {
                if( !xmGroupService.checkUserIsProductAdm(xmProduct,memUserid) && !xmGroupService.checkUserExistsGroup(groups,memUserid) ){
                    not.add(memUserid);
                };
            }
            if(not.size()>0){
                tips.setErrMsg("pdqx-scope-product-1",String.format("产品【%s】只开放给同一个产品团队人员,【%s】与【%s】不在同一个团队中。",xmProduct.getId(),not.stream().collect(Collectors.joining(",")),head.getUsername()));
                tips.put("noQxUseridSet",not);
            }
            return tips;
        }else if("3".equals(scopeQx)){//同小组
            List<XmGroupVo> groups=this.getProductGroupsFromLocalCache(xmProduct.getId());
            List<XmGroupVo> headGroups=groups;

            if( !headIsPm ){
                headGroups=xmGroupService.getUserGroups(groups,head.getUserid());
                if( headGroups==null || headGroups.size()==0 ){
                    tips.setErrMsg("pdqx-scope-team-1",String.format("产品【%s】只开放给同一个产品团队下同一个小组人员,【%s】不在产品团队中。",xmProduct.getId(),username));
                    tips.put("noQxUseridSet", CollectionUtils.convertSet(Arrays.asList(head.getUserid())));
                    return tips;
                }
            }
            Set<String> not=new HashSet<>();
            for (String memUserid : memUseridSet) {
                List<XmGroupVo> memGroups=xmGroupService.getUserGroups(headGroups,memUserid);
                if( memGroups==null || memGroups.size()==0 ){
                    not.add(memUserid);
                }
            }
            if(not.size()>0){
                tips.setErrMsg("pdqx-scope-team-2",String.format("产品【%s】只开放给同一个产品团队下同一个小组人员,【%s】与【%s】不在同一个小组内。",xmProduct.getId(),not.stream().collect(Collectors.joining(",")),head.getUsername()));
                tips.put("noQxUseridSet",not);
            }
            return tips;
        }
        return tips;
    }


    List<XmGroupVo> getProductGroupsFromLocalCache(String productId){
        List<XmGroupVo> groupVoList  =xmGroupService.getProductGroupVoList(productId);
        return groupVoList;
    }


    public Tips checkProductTransmitQxBatch(XmProduct xmProduct,int teamType,String headUserid,String ...memUserids){
        Tips tips=new Tips("成功");
        String transmitQx= QxTool.getProductTransmitQx(xmProduct.getQxCode(),teamType);
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
            List<XmGroupVo> groups=getProductGroupsFromLocalCache(xmProduct.getId());
            Set<String> not=new HashSet<>();
            for (String memUserid : memUseridSet) {
                if(!xmGroupService.checkUserIsOtherUserTeamHeadOrAss(groups,headUserid,memUserid)){
                    not.add(memUserid);
                }
            }
            if(not.size()>0){
                tips.setErrMsg("pdqx-transmit-0",
                        String.format("产品【%s】开启了上下级关系检查，您当前账户不属于【%s】中任意账户的上级，无权操作。",xmProduct.getId(),not.stream().collect(Collectors.joining(","))));
                tips.put("noQxUseridSet",not);
            }

        }
        return tips;
    }
}
