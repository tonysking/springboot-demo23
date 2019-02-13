package com.example.demo23.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@ApiModel(value = "学生对象", description = "这是学生对象")
public class Student {

    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue
    private Integer stuID;

    @Column(name = "stuname")
    @ApiModelProperty(value = "学生名", name = "stuName",example = "testStu",required = true)
    private String stuName;

    @Column(name = "stuphone")
    //@ApiModelProperty(value = "学生电话", name = "stuPhone",example = "testStu",required = true)
    private String stuPhone;

    @Column(name = "stuaddress")
    private String stuAddress;

    public Integer getStuID() {
        return stuID;
    }

    public void setStuID(Integer stuID) {
        this.stuID = stuID;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    public String getStuAddress() {
        return stuAddress;
    }

    public void setStuAddress(String stuAddress) {
        this.stuAddress = stuAddress;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuID=" + stuID +
                ", stuName='" + stuName + '\'' +
                ", stuPhone='" + stuPhone + '\'' +
                ", stuAddress='" + stuAddress + '\'' +
                '}';
    }
}
