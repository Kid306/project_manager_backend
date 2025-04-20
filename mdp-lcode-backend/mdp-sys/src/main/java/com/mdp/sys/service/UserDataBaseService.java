package com.mdp.sys.service;

import com.mdp.core.api.Sequence;
import com.mdp.core.entity.LangTips;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.ObjectTools;
import com.mdp.mq.queue.Push;
import com.mdp.safe.client.pwd.SafePasswordEncoder;
import com.mdp.sys.entity.*;
import com.mdp.sys.pub.service.InviteCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDataBaseService {

    Logger logger= LoggerFactory.getLogger(UserDataBaseService.class);

    @Autowired
    UserTpaService tpaService;

    @Autowired
    UserTpaInviteLinkService inviteLinkService;

    @Autowired
    UserService userService;


    @Autowired
    UserTpaLinkService tpaLinkService;

    @Autowired
    UserTpaInviteService inviteService;


    @Autowired
    InviteCacheService inviteCacheService;


    @Autowired
    BranchService branchService;


    PasswordEncoder passwordEncoder=new SafePasswordEncoder();

    @Autowired
    Sequence sequence;

    @Autowired
    Push push;


    public User converUserTpaToUser(UserTpa userTpa) {
        User user = new User();
        user.setHeadimgurl(userTpa.getHeadimgurl());
        user.setUsername(userTpa.getUsername());
        user.setNickname(userTpa.getNickname());
        user.setCity(userTpa.getCity());
        user.setSex(userTpa.getGender());
        user.setPhoneno(userTpa.getPhoneno());
        user.setUnionid("M_"+sequence.getUserid());
        user.setUserid(user.getUnionid());
        user.setDisplayUserid(user.getUserid());
        user.setAtype("1");
        user.setBranchId(user.getUserid());
        user.setOpen("1");
        user.setGradeId("mg_001");
        user.setGradeName("一星学徒");
        user.setIlvlId("1");
        user.setGuardId("0");
        user.setProvince(userTpa.getProvince());
        user.setCpaType("0");
        user.setCpaOrg("0");
        user.setMemType("0");
        return user;
    }

    /**
     * 将userTpa 注册到数据库
     * @param tpa
     * @return
     */
    public User registerUserByTpa(String inviteId, UserTpa tpa, boolean tpadbExists){
         if(tpadbExists){
            if(ObjectTools.isNotEmpty(tpa.getUsername())){
                tpaService.updateById(tpa,true);
            }
        }else{
            tpaService.save(tpa);
        }

        List<User> userList= userService.selectListByTpaOpenidOrTpaUnionidOrPhoneno(tpa);
        User bestUser=getBestUser(userList);
        if(bestUser!=null ){
                User sysUser=bestUser;
                try {
                    UserTpaLink link = new UserTpaLink();
                    link.setBindBranchId(sysUser.getBranchId());
                    link.setTpaId(tpa.getOpenid());
                    link.setSysUserid(sysUser.getUserid());
                    link.setBindTime(new Date());
                    tpaLinkService.save(link);
                    if (ObjectTools.isNotEmpty(tpa.getPhoneno())) {
                        sysUser.setPhoneno(tpa.getPhoneno());
                    }
                }catch (Exception e){

                }
                if(inviteId.startsWith("login")){
                    String rawPassword=createRawPassword();
                    sysUser.setPwdStrong("3");
                    sysUser.setPassword(this.initRandPassword(rawPassword));
                    userService.updateById(sysUser,true);
                    sysUser.setPassword(rawPassword);
                }
                return sysUser;
        }else{
            User sysUser=converUserTpaToUser(tpa);
            UserTpaLink link=new UserTpaLink();
            link.setBindBranchId(sysUser.getBranchId());
            link.setTpaId(tpa.getOpenid());
            link.setSysUserid(sysUser.getUserid());
            link.setBindTime(new Date());
            tpaLinkService.save(link);
            if(inviteId.startsWith("login")){
                String rawPassword=createRawPassword();
                sysUser.setPassword(this.initRandPassword(rawPassword));
                sysUser.setPwdStrong("3");
                sysUser.setPassword(rawPassword);
            }
            userService.save(sysUser);
            return sysUser;
        }
    }

    User getBestUser(List<User> userList){
        User bestUser=null;
        User bestUser1=null;
        User bestUser2=null;
        User bestUser3=null;
        if(userList!=null && userList.size()>0){
            for (User user : userList) {
                if(ObjectTools.isNotEmpty(user.getUsername())){
                    bestUser3=user;
                }
                if(!"1".equals(user.getAtype()) && ObjectTools.isNotEmpty(user.getUsername())){
                    bestUser2=user;
                }
                if(!"1".equals(user.getAtype()) && ObjectTools.isNotEmpty(user.getUsername()) && ObjectTools.isNotEmpty(user.getPhoneno())){
                    bestUser1=user;
                }
                if(!"1".equals(user.getAtype()) && ObjectTools.isNotEmpty(user.getUsername()) && ObjectTools.isNotEmpty(user.getPhoneno()) && !user.getUserid().equals(user.getBranchId())){
                    bestUser=user;
                }
            }
            if(bestUser==null){
                bestUser=bestUser1;
            }
            if(bestUser==null){
                bestUser=bestUser2;
            }
            if(bestUser==null){
                bestUser=bestUser3;
            }
            if(bestUser==null){
                bestUser=userList.get(0);
            }

        }
        return bestUser;
    }
    public User invite(String state, UserTpa tpa,boolean tpadbExists) {
        UserTpaInvite invite=inviteCacheService.getInvite(state);
        if(invite==null){
            throw new BizException("state-not-exists","邀请已不存在");
        }
        List<User> userList= userService.selectListByTpaOpenidOrTpaUnionidOrPhoneno(tpa);

        User bestUser=getBestUser(userList.stream().filter(k->invite.getSendBranchId().equals(k.getBranchId())).collect(Collectors.toList()));
       if(bestUser!=null){
           return bestUser;
       }
        String unionid=getUnionid(userList);
        User user=this.converUserTpaToUser(tpa);
        user.setAtype("0");
        user.setMemType("2");
        user.setCpaType("0");
        user.setCpaOrg("0");
        user.setBranchId(invite.getSendBranchId());
        user.setUnionid(ObjectTools.isNotEmpty(unionid)?unionid:user.getUnionid());
        userService.save(user);

        if(tpadbExists){
            if(ObjectTools.isNotEmpty(tpa.getUsername())){
                tpaService.updateById(tpa,true);
            }
        }else{
            tpaService.save(tpa);
        }
        try{
            UserTpaLink link=new UserTpaLink(user.getUserid(),tpa.getOpenid());
            link.setBindTime(new Date());
            link.setBindBranchId(user.getBranchId());
            tpaLinkService.save(link);
        }catch (Exception e){
            logger.debug("保存出错，但是可以接受的，出错代表数据已存在，无须做其它处理，属于正常业务流程",e);
        }
        try{
            UserTpaInviteLink inviteLink=new UserTpaInviteLink();
            inviteLink.setInviteId(state);
            inviteLink.setJoinTpaId(tpa.getOpenid());
            inviteLink.setJoinUserid(user.getUserid());
            inviteLink.setJoinTime(new Date());
            inviteLink.setJoinType("1");
            inviteLink.setJoinBranchId(invite.getSendBranchId());
            inviteLinkService.save(inviteLink);
        }catch (Exception e){
            logger.debug("保存出错，但是可以接受的，出错代表数据已存在，无须做其它处理，属于正常业务流程",e);
        }
        return user;
    }

    String getUnionid(List<User> userList){
        if(userList==null || userList.size()==0){
            return null;
        }else{
            User mainUser=getBestUser(userList.stream().filter(k->"1".equals(k.getAtype())).collect(Collectors.toList()));
            User unionUser=getBestUser(userList.stream().filter(k->ObjectTools.isNotEmpty(k.getUnionid())).collect(Collectors.toList()));
            String unionid=null;
            if(mainUser!=null && ObjectTools.isNotEmpty(mainUser.getUnionid())){
                unionid=mainUser.getUnionid();
            }
            if(ObjectTools.isEmpty(unionid)){
                if(unionUser!=null){
                    unionid= unionUser.getUnionid();
                }
            }
            return unionid;
        }
    }

    public User bind(String state, UserTpa tpa, boolean tpadbExists) {
        UserTpaInvite invite= inviteCacheService.getInvite(state);
        if(invite==null){
            throw new BizException("state-not-exists","邀请已不存在");
        }
        UserTpaLink linkDb=tpaLinkService.getById(BaseService.map("tpaId",tpa.getOpenid(),"sysUserid",invite.getJoinUserid()));
        if(linkDb!=null){
            throw new BizException("user-had-bind-ok","该账户已绑定,无需再绑定");
        }
        List<User> userList=userService.selectListByTpaOpenidOrTpaUnionidOrPhoneno(tpa);
        Optional<User> userOptional=userList.stream().filter(k->k.getUserid().equals(invite.getJoinUserid())).findAny();
        User userdb=null;
        if(userOptional.isPresent()){
            userdb=userOptional.get();
        }else{
            userdb=userService.getById(invite.getJoinUserid());
            if(userdb==null){
                throw new BizException(LangTips.errMsg("user-not-exists","用户%s已不存在",invite.getJoinUsername()));
            }
        }
        User bestUser=getBestUser(userList);
        if(bestUser!=null && ObjectTools.isNotEmpty(bestUser.getUnionid()) && !bestUser.getUnionid().equals(userdb.getUnionid())){
            User up=new User();
            up.setUnionid(bestUser.getUnionid());
            up.setUserid(userdb.getUserid());
            up.setPhoneno(bestUser.getPhoneno());
            up.setEmail(bestUser.getEmail());
            String ramPwd=createRawPassword();
            up.setPassword(initRandPassword(ramPwd));
            up.setPwdStrong("3");
            if(ObjectTools.isEmpty(userdb.getUsername())){
                up.setUsername(bestUser.getUsername());
                userdb.setUsername(up.getUsername());
            }
            if(ObjectTools.isEmpty(userdb.getNickname())){
                up.setNickname(bestUser.getNickname());
                userdb.setNickname(up.getNickname());
            }
            userService.updateById(up,true);
            userdb.setUnionid(up.getUnionid());
            userdb.setEmail(up.getEmail());
            userdb.setPhoneno(up.getPhoneno());
            userdb.setPassword(ramPwd);
            userdb.setPwdStrong("3");
        }
        if(tpadbExists){
            if(ObjectTools.isNotEmpty(tpa.getUsername())){
                tpaService.updateById(tpa,true);
            }
        }else{
            tpaService.save(tpa);
        }

        UserTpaLink link=new UserTpaLink(userdb.getUserid(),tpa.getOpenid());
        link.setBindBranchId(invite.getSendBranchId());
        link.setBindTime(new Date());
        tpaLinkService.save(link);
        UserTpaInviteLink inviteLink=new UserTpaInviteLink();
        inviteLink.setInviteId(state);
        inviteLink.setJoinTpaId(tpa.getOpenid());
        inviteLink.setJoinUserid(userdb.getUserid());
        inviteLink.setJoinTime(new Date());
        inviteLink.setJoinType("1");
        inviteLink.setJoinBranchId(invite.getSendBranchId());
        inviteLinkService.save(inviteLink);

        return userdb;
    }

    public String initPassword(){
        String passwordJsMd5= DigestUtils.md5DigestAsHex(("888888").getBytes());
        String passwordJavaMd5=passwordEncoder.encode(passwordJsMd5);
        return passwordJavaMd5;
    }
    public String initRandPassword(String rawPassword){
        String passwordJsMd5= DigestUtils.md5DigestAsHex(rawPassword.getBytes());
        String passwordJavaMd5=passwordEncoder.encode(passwordJsMd5);
        return passwordJavaMd5;
    }
    public String createRawPassword(){
        return sequence.getCommonNo("{rands:6}");
    }

    public User claim(String inviteId, UserTpa tpa, boolean tpadbExists) {
        UserTpaInvite invite=inviteCacheService.getInvite(inviteId);
        if(invite==null){
            throw new BizException("state-not-exists","申领流水号已不存在");
        }
        Branch branch=branchService.getById(invite.getSendBranchId());
        if(branch==null){
            throw new BizException("branch-not-exists","机构已不存在");
        }
        if(!"1".equals(branch.getEnabled())){
            throw new BizException("branch-enabled-0","机构已禁用");
        }
        if("1".equals(branch.getClaimStatus())){
            throw new BizException("branch-had-claim","机构已申领完毕");
        }
        if(tpadbExists){
            if(ObjectTools.isNotEmpty(tpa.getUsername())){
                tpaService.updateById(tpa,true);
            }
        }else{
            tpaService.save(tpa);
        }
        List<User> userList=this.userService.selectListByTpaOpenidOrTpaUnionidOrPhoneno(tpa);
        String unionid=this.getUnionid(userList);
         String rawPassword=createRawPassword();
        User mainUser=this.userService.getById(invite.getSendBranchId());
        if(mainUser==null){
            mainUser=new User();
            mainUser.setUnionid(ObjectTools.isNotEmpty(unionid)?unionid:branch.getId());
            mainUser.setUserid(branch.getId());
            mainUser.setBranchId(branch.getId());
            mainUser.setUsername(branch.getBranchName());
            mainUser.setMemType("1");
            mainUser.setAtype("0");
            mainUser.setIstatus("0");
            mainUser.setDisplayUserid(branch.getId());
            mainUser.setOpen("0");
            mainUser.setUstatus("1");
            mainUser.setStartdate(new Date());
            mainUser.setLocked("0");
            mainUser.setBizFlowState("0");
            mainUser.setLtime(new Date());
            mainUser.setPassword(initRandPassword(rawPassword));
            mainUser.setPwdStrong("3");
            userService.save(mainUser);
        }else{
            mainUser.setUnionid(ObjectTools.isNotEmpty(unionid)?unionid:branch.getId());
            mainUser.setLtime(new Date());
            mainUser.setPassword(initRandPassword(rawPassword));
            mainUser.setPwdStrong("3");
            userService.updateById(mainUser,true);
        }

        Branch branch1=new Branch();
        branch1.setId(invite.getSendBranchId());
        branch1.setAdmUserid(mainUser.getUserid());
        branch1.setAdmUsername(mainUser.getUsername());
        branch1.setClaimStatus("1");
        this.branchService.updateById(branch1,true);
        try{
            UserTpaLink link=new UserTpaLink(mainUser.getUserid(),tpa.getOpenid());
            link.setBindBranchId(mainUser.getBranchId());
            link.setBindTime(new Date());
            tpaLinkService.save(link);
        }catch (Exception e){
            logger.debug("保存出错，但是可以接受的，出错代表数据已存在，无须做其它处理，属于正常业务流程",e);
        }
        try{
            UserTpaInviteLink inviteLink=new UserTpaInviteLink();
            inviteLink.setInviteId(inviteId);
            inviteLink.setJoinTpaId(tpa.getOpenid());
            inviteLink.setJoinUserid(mainUser.getUserid());
            inviteLink.setJoinTime(new Date());
            inviteLink.setJoinType("1");
            inviteLink.setJoinBranchId(invite.getSendBranchId());
            inviteLinkService.save(inviteLink);
        }catch (Exception e){
            logger.debug("保存出错，但是可以接受的，出错代表数据已存在，无须做其它处理，属于正常业务流程",e);
        }
        mainUser.setPassword(rawPassword);
        return mainUser;

    }
}
