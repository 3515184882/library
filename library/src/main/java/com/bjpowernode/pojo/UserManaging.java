package com.bjpowernode.pojo;

public class UserManaging {
    private Integer uId;
    private String uName;
    private String uEmail;
    private String uHiredate;
    private String uPassword;
    private String uRole;
    private String uStatus;

    public UserManaging() {
    }

    public UserManaging(Integer uId, String uName, String uEmail, String uHiredate, String uPassword, String uRole, String uStatus) {
        this.uId = uId;
        this.uName = uName;
        this.uEmail = uEmail;
        this.uHiredate = uHiredate;
        this.uPassword = uPassword;
        this.uRole = uRole;
        this.uStatus = uStatus;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuHiredate() {
        return uHiredate;
    }

    public void setuHiredate(String uHiredate) {
        this.uHiredate = uHiredate;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuRole() {
        return uRole;
    }

    public void setuRole(String uRole) {
        this.uRole = uRole;
    }

    public String getuStatus() {
        return uStatus;
    }

    public void setuStatus(String uStatus) {
        this.uStatus = uStatus;
    }

    @Override
    public String toString() {
        return "UserManaging{" +
                "uId=" + uId +
                ", uName='" + uName + '\'' +
                ", uEmail='" + uEmail + '\'' +
                ", uHiredate='" + uHiredate + '\'' +
                ", uPassword='" + uPassword + '\'' +
                ", uRole='" + uRole + '\'' +
                ", uStatus='" + uStatus + '\'' +
                '}';
    }
}
