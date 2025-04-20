package com.mdp;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DsSubService extends DsAllService{

    public void selectDs2(Map parameter) {
        super.selectDs1(parameter);
    }
}
