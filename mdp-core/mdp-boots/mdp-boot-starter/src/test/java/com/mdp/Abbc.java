package com.mdp;

import java.time.Instant;
import java.util.Date;

public class Abbc {
    public Date getStartdate() {
        return startdate;
    }
    public Instant getEnddate() {
        return enddate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }
    public void setEnddate(Instant enddate) {
        this.enddate = enddate;
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    Date startdate;
    Instant enddate;
        String userid;
}