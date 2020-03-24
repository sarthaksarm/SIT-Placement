package com.sark.android.placement_sit1;

public class User {

    private String name;
    private String date;
    private String key;
    private String key2;
    private String pack;
    private String branch;
    private String datedead;
    private String timedead;
    private String regislink;
    private String emailid;
    private String mailid;

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public User(){

    }

    public String getTimedead() {
        return timedead;
    }

    public void setTimedead(String timedead) {
        this.timedead = timedead;
    }

    public User(String name, String date, String key, String pack, String branch, String datedead,
                String timedead,String regislink, String emailid,String mailid, String key2) {
        this.name = name;
        this.date = date;
        this.key=key;
        this.pack=pack;
        this.branch=branch;
        this.datedead=datedead;
        this.timedead=timedead;
        this.regislink=regislink;
        this.emailid=emailid;
        this.key2=key2;
        this.mailid=mailid;
    }

    public String getMailid() {
        return mailid;
    }

    public void setMailid(String mailid) {
        this.mailid = mailid;
    }

    public String getKey2() {
        return key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public String getRegislink() {
        return regislink;
    }

    public void setRegislink(String regislink) {
        this.regislink = regislink;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDatedead() {
        return datedead;
    }

    public void setDatedead(String datedead) {
        this.datedead = datedead;
    }
    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}