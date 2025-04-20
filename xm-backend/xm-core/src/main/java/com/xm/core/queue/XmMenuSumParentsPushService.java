package com.xm.core.queue;

import com.mdp.mq.queue.Push;
import com.xm.core.entity.XmMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class XmMenuSumParentsPushService {

    @Autowired
    Push push;
    
    public static String queueName="xm-menu-sum-parents";
    
    public void pushXmMenu(XmMenu xmMenu){
        if(xmMenu==null || !StringUtils.hasText(xmMenu.getMenuId())){
            return;
        }
        push.leftPush(queueName,xmMenu);
    }

    public void pushXmMenus(List<XmMenu> xmMenus){
        if(xmMenus==null ||xmMenus.size()==0){
            return;
        }
        for (XmMenu xmMenu : xmMenus) {
            push.leftPush(queueName,xmMenu);
        }
        
    }
}
