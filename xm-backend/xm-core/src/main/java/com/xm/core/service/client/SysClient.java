package com.xm.core.service.client;

import com.mdp.core.entity.Tips;
import com.mdp.core.utils.BaseUtils;
import com.mdp.micro.client.CallBizService;
import com.mdp.mq.queue.Push;
import com.mdp.safe.client.entity.Dept;
import com.mdp.safe.client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.mdp.core.utils.BaseUtils.map;

/**
 * 对sys接口调用
 */
@Service
public class SysClient {


    @Autowired
    CallBizService callBizService;

    @Autowired
    Push push;


    /**
     * 查询用户详细资料
     * @param userid
     * @return
     */
    public User getUserInterestsByUserid(String userid){
        String url="/lcode/mdp/sys/user/noauth/detail?userid={userid}";
        Map<String,Object> re=callBizService.getForMap(url,map("userid",userid));
        Map<String,Object> data= (Map<String, Object>) re.get("data");
        if(data==null || data.isEmpty()){
            return null;
        }
        User user=BaseUtils.fromMap(data,User.class);
        return user;

    }

    /**
     * 查询用户详细资料
     * @param userid
     * @return
     */
    public User getUserByUserid(String userid){
        String url="/lcode/mdp/sys/user/detail?userid={userid}";
        Map<String,Object> re=callBizService.getForMap(url,map("userid",userid));
        Map<String,Object> data= (Map<String, Object>) re.get("data");
        if(data==null || data.isEmpty()){
            return null;
        }
        User user=BaseUtils.fromMap(data,User.class);
        return user;

    }

    /**
     * 查询用户服务明细及用户基本信息
     * @param serviceId
     * @return
     */
    public Map<String,Object> getUserSvrByServiceId(String serviceId){
        String url="/lcode/mdp/sys/userSvr/detail?id={serviceId}";
        Map<String,Object> re=callBizService.getForMap(url,map("serviceId",serviceId));
        Map<String,Object> data= (Map<String, Object>) re.get("data");
        if(data==null || data.isEmpty()){
            return null;
        }

        return data;

    }

    /**
     * 检查用户归属企业是否可以投标等等
     * @param branchId
     * @param at
     * @param exp
     * @param bids
     * @return {tipscode:bids-not-enough,msg:投标次数超限},{tipscode:smaxExp-not-enough,msg:投标工作量超限},{tipscode:smaxAt-not-enough,msg:投标金额超限},
     */
    public Map<String,Object> checkBranchInterests(String branchId,BigDecimal at,BigDecimal exp,Integer bids){
        String url="/lcode/mdp/sys/branchInterests/checkBranchInterests?branchId={branchId}&at={at}&exp={exp}&bids={bids}";
        Map<String,Object> re=callBizService.getForMap(url,map("branchId",branchId  ,"at",at,"exp",exp,"bids",bids));
        return re;
    }


    /**
     * 检查用户归属企业是否可以投标等等
     * @param userid
     * @param at
     * @param exp
     * @param bids
     * @return {tipscode:bids-not-enough,msg:投标次数超限},{tipscode:smaxExp-not-enough,msg:投标工作量超限},{tipscode:smaxAt-not-enough,msg:投标金额超限},
     */
    public Map<String,Object> checkUserInterests(String userid,BigDecimal at,BigDecimal exp,Integer bids){
        String url="/lcode/mdp/sys/userInterests/checkUserInterests?userid={userid}&at={at}&exp={exp}&bids={bids}";
        Map<String,Object> re=callBizService.getForMap(url,map("userid",userid  ,"at",at,"exp",exp,"bids",bids));
        return re;
    }

    /**
     * 投标成功后登记投标次数-登记企业的投标次数
     * @return
     */
    public Tips pushBidsAfterBidSuccess(String userid,String bizId,BigDecimal at,BigDecimal exp,Integer bids){
        Tips tips = new Tips("推送订单成功");
        push.leftPush("xm_task_bid_for_person",map("userid",userid  ,"bizId",bizId,"at",at,"exp",exp,"bids",bids));
        // strRedisTemplate.convertAndSend("xm_task_settle", JSON.toJSONString(params));
        return tips;
    }

    /**
     * 任务验收后，推送任务金额到sys
     * @return
     */
    public Tips pushPayAtAfterTaskAcceptanceSuccess(String userid,String bizId,BigDecimal at,BigDecimal exp){
        Tips tips = new Tips("推送订单成功");
        push.leftPush("xm_task_acceptance_success_for_person",map("userid",userid  ,"bizId",bizId,"at",at,"exp",exp,"bids",1));
        // strRedisTemplate.convertAndSend("xm_task_settle", JSON.toJSONString(params));
        return tips;
    }

    public List<Dept> listSubDept(String pdeptid) {
        return null;
    }

    public User createUserIfNotExists(User exeUser,String deptid,String branchId) {
        String url="/lcode/mdp/user/createIfNotExists";
        exeUser.setBranchId(branchId);
        Map<String,Object> re=callBizService.postForMap(url,map("user",exeUser  ,"userDept",map("deptid",deptid)));
        User user=BaseUtils.fromMap((Map<String, Object>) re.get("data"),User.class);
        return user;
    }
}
