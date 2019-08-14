package com.eshore.entity;

import java.util.Date;

public class Staff {
   /* `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `schoolName` varchar(255) DEFAULT NULL,
  `schoolNature` varchar(255) DEFAULT NULL,
  `emergencyContact` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `entryTime` date DEFAULT NULL,
            `leaveTime` date DEFAULT NULL,
            `jobNum` int(11) DEFAULT NULL,
  `providentFundStartTime` date DEFAULT NULL,
            `providentFundEndTime` date DEFAULT NULL,
            `contractStartTime` date DEFAULT NULL,
            `contractEndTime` date DEFAULT NULL,
            `socialSecurityStartTime` date DEFAULT NULL,
            `socialSecurityEndTime` date DEFAULT NULL,
            `examination` varchar(255) DEFAULT NULL,
  `oaNumber` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  departmentId*/
   private  int id;
   private  String name;
   private  int age;
   private String schoolName;
   private String schoolNature;
   private String emergencyContact;
   private String tel;
   private Date entryTime;
   private  Date leaveTime;
   private  int jobNum;
   private  Date providentFundStartTime;
   private Date providentFundEndTime;
   private Date contractStartTime;
   private Date contractEndTime;
   private  Date socialSecurityStartTime,socialSecurityEndTime;
   private   int examination,oaNumber,departmentId;
   private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolNature() {
        return schoolNature;
    }

    public void setSchoolNature(String schoolNature) {
        this.schoolNature = schoolNature;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public int getJobNum() {
        return jobNum;
    }

    public void setJobNum(int jobNum) {
        this.jobNum = jobNum;
    }

    public Date getProvidentFundStartTime() {
        return providentFundStartTime;
    }

    public void setProvidentFundStartTime(Date providentFundStartTime) {
        this.providentFundStartTime = providentFundStartTime;
    }

    public Date getProvidentFundEndTime() {
        return providentFundEndTime;
    }

    public void setProvidentFundEndTime(Date providentFundEndTime) {
        this.providentFundEndTime = providentFundEndTime;
    }

    public Date getContractStartTime() {
        return contractStartTime;
    }

    public void setContractStartTime(Date contractStartTime) {
        this.contractStartTime = contractStartTime;
    }

    public Date getContractEndTime() {
        return contractEndTime;
    }

    public void setContractEndTime(Date contractEndTime) {
        this.contractEndTime = contractEndTime;
    }

    public Date getSocialSecurityStartTime() {
        return socialSecurityStartTime;
    }

    public void setSocialSecurityStartTime(Date socialSecurityStartTime) {
        this.socialSecurityStartTime = socialSecurityStartTime;
    }

    public Date getSocialSecurityEndTime() {
        return socialSecurityEndTime;
    }

    public void setSocialSecurityEndTime(Date socialSecurityEndTime) {
        this.socialSecurityEndTime = socialSecurityEndTime;
    }

    public int getExamination() {
        return examination;
    }

    public void setExamination(int examination) {
        this.examination = examination;
    }

    public int getOaNumber() {
        return oaNumber;
    }

    public void setOaNumber(int oaNumber) {
        this.oaNumber = oaNumber;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
