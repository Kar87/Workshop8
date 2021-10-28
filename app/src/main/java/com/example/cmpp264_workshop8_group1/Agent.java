package com.example.cmpp264_workshop8_group1;

public class Agent {
    private Integer agentid;
    private String agtfirstname;
    private String agtmiddleinitial;
    private String agtlastname;
    private String agtbusphone;
    private String agtemail;
    private String agtposition;
    private Integer agencyid;

    public Agent(Integer agentid, String agtfirstname, String agtmiddleinitial, String agtlastname, String agtbusphone, String agtemail, String agtposition, Integer agencyid) {
        this.agentid = agentid;
        this.agtfirstname = agtfirstname;
        this.agtmiddleinitial = agtmiddleinitial;
        this.agtlastname = agtlastname;
        this.agtbusphone = agtbusphone;
        this.agtemail = agtemail;
        this.agtposition = agtposition;
        this.agencyid = agencyid;
    }

    public Integer getAgentid() {
        return agentid;
    }

    public void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }

    public String getAgtfirstname() {
        return agtfirstname;
    }

    public void setAgtfirstname(String agtfirstname) {
        this.agtfirstname = agtfirstname;
    }

    public String getAgtmiddleinitial() {
        return agtmiddleinitial;
    }

    public void setAgtmiddleinitial(String agtmiddleinitial) {
        this.agtmiddleinitial = agtmiddleinitial;
    }

    public String getAgtlastname() {
        return agtlastname;
    }

    public void setAgtlastname(String agtlastname) {
        this.agtlastname = agtlastname;
    }

    public String getAgtbusphone() {
        return agtbusphone;
    }

    public void setAgtbusphone(String agtbusphone) {
        this.agtbusphone = agtbusphone;
    }

    public String getAgtemail() {
        return agtemail;
    }

    public void setAgtemail(String agtemail) {
        this.agtemail = agtemail;
    }

    public String getAgtposition() {
        return agtposition;
    }

    public void setAgtposition(String agtposition) {
        this.agtposition = agtposition;
    }

    public Integer getAgencyid() {
        return agencyid;
    }

    public void setAgencyid(Integer agencyid) {
        this.agencyid = agencyid;
    }

    @Override
    public String toString() {
        return agentid + "." + agtfirstname + " " +agtmiddleinitial +"" + agtlastname + "|"
                + agtbusphone + " | " + agtemail + '|' + agtposition + '|' + agencyid ;
    }
}
