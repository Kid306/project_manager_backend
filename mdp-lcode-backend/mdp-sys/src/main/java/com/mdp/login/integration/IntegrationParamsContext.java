package com.mdp.login.integration;

/**
 * @author chenyc
 * @date 2018-3-30
 **/
public class IntegrationParamsContext {

    private static ThreadLocal<IntegrationParams> holder = new ThreadLocal<>();

    public static void set(IntegrationParams integrationParams){
        holder.set(integrationParams);
    }

    public static IntegrationParams get(){
        return holder.get();
    }

    public static void clear(){
        holder.remove();
    }
}
