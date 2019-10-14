package com.itheima.pojo;

import java.io.Serializable;
import java.util.Date;

/*
 * @author Jayvin
 * @date 2019/10/13 1:46
 */
public class HotPackage implements Serializable {
    private Integer id;
    private String name; //套餐名字
    private Integer count; //套餐预约数量
    private double proportion; //套餐预约数量占比
    private String remark; //套餐备注
    private Date reportDate; //查询日期

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public double getProportion() {
        return proportion;
    }

    public void setProportion(double proportion) {
        this.proportion = proportion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }
}
