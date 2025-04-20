package com.mdp.core.api;

import java.util.Map;

public interface ContextEnvService {

    Map<String,Object> getEnv();
    Map<String,Object> getUser();

    Map<String,Object> getAll();

    void clearThreadLock();
}
