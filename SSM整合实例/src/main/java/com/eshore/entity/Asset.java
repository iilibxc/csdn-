package com.eshore.entity;

import java.util.Date;

public class Asset {
 /*   `id` int(11) NOT NULL AUTO_INCREMENT,
  `staffId` int(11) DEFAULT NULL,
  `computeId` varchar(11) DEFAULT NULL,
  `cpu` double(255,0) DEFAULT NULL,
  `machineryDisk` double(255,0) DEFAULT NULL,
  `solidDisk` double(255,0) DEFAULT NULL,
  `lendTime` datetime DEFAULT NULL,
            `returnBackTime` datetime DEFAULT NULL,
            `ram` double(255,0) DEFAULT NULL,*/
 private  int id;
 private int staffId;
 private String computeId;
 private  double cpu;
 private double machineryDisk;
 private double solidDisk;
 private Date lendTime;
 private  Date returnBackTime;
 private double ram;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getComputeId() {
        return computeId;
    }

    public void setComputeId(String computeId) {
        this.computeId = computeId;
    }

    public double getCpu() {
        return cpu;
    }

    public void setCpu(double cpu) {
        this.cpu = cpu;
    }

    public double getMachineryDisk() {
        return machineryDisk;
    }

    public void setMachineryDisk(double machineryDisk) {
        this.machineryDisk = machineryDisk;
    }

    public double getSolidDisk() {
        return solidDisk;
    }

    public void setSolidDisk(double solidDisk) {
        this.solidDisk = solidDisk;
    }

    public Date getLendTime() {
        return lendTime;
    }

    public void setLendTime(Date lendTime) {
        this.lendTime = lendTime;
    }

    public Date getReturnBackTime() {
        return returnBackTime;
    }

    public void setReturnBackTime(Date returnBackTime) {
        this.returnBackTime = returnBackTime;
    }

    public double getRam() {
        return ram;
    }

    public void setRam(double ram) {
        this.ram = ram;
    }
}
