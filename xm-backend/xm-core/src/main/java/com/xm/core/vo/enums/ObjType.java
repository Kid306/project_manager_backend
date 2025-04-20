package com.xm.core.vo.enums;

import java.util.Arrays;

public enum ObjType {
    Project("1"),
    Task("2"),
    Product("3"),
    Menu("4"),
    Bug("5"),
    Iteration("6"),
    Group("7"),
    Branch("8"),
    Person("9"),
    Budget("B"),
    Cost("C"),
    Contract("H"),
    Customer("K"),
    Archive("W"),
    Case("TC"),
    Casedb("TB"),
    CasePlan("TP"),
    CaseExe("TE"),
    Kpi("KPI"),
    Risk("R");

    private String id;

    ObjType(String id) {
        this.id=id;
    }


    public String getId() {
        return this.id;
    }

    public ObjType parse(String id){
        return Arrays.stream(ObjType.values()).filter(k->k.id.equals(id)).findAny().get();
    }
}
