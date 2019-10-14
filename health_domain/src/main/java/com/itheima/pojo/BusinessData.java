package com.itheima.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
 * @author Jayvin
 * @date 2019/10/13 1:55
 */
public class BusinessData implements Serializable {
    private Integer id;
    private Integer todayNewMember;
    private Integer totalMember;
    private Integer thisWeekNewMember;
    private Integer thisMonthNewMember;
    private Integer todayOrderNumber;
    private Integer todayVisitsNumber;
    private Integer thisWeekOrderNumber;
    private Integer thisWeekVisitsNumber;
    private Integer thisMonthOrderNumber;
    private Integer thisMonthVisitsNumber;
    private Date reportDate;
    private List<HotPackage> hotPackages;

    public BusinessData() {
    }

    public BusinessData(Integer todayNewMember, Integer totalMember,
                        Integer thisWeekNewMember, Integer thisMonthNewMember,
                        Integer todayOrderNumber, Integer todayVisitsNumber,
                        Integer thisWeekOrderNumber, Integer thisWeekVisitsNumber,
                        Integer thisMonthOrderNumber, Integer thisMonthVisitsNumber,
                        Date reportDate) {
        this.todayNewMember = todayNewMember;
        this.totalMember = totalMember;
        this.thisWeekNewMember = thisWeekNewMember;
        this.thisMonthNewMember = thisMonthNewMember;
        this.todayOrderNumber = todayOrderNumber;
        this.todayVisitsNumber = todayVisitsNumber;
        this.thisWeekOrderNumber = thisWeekOrderNumber;
        this.thisWeekVisitsNumber = thisWeekVisitsNumber;
        this.thisMonthOrderNumber = thisMonthOrderNumber;
        this.thisMonthVisitsNumber = thisMonthVisitsNumber;
        this.reportDate = reportDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTodayNewMember() {
        return todayNewMember;
    }

    public void setTodayNewMember(Integer todayNewMember) {
        this.todayNewMember = todayNewMember;
    }

    public Integer getTotalMember() {
        return totalMember;
    }

    public void setTotalMember(Integer totalMember) {
        this.totalMember = totalMember;
    }

    public Integer getThisWeekNewMember() {
        return thisWeekNewMember;
    }

    public void setThisWeekNewMember(Integer thisWeekNewMember) {
        this.thisWeekNewMember = thisWeekNewMember;
    }

    public Integer getThisMonthNewMember() {
        return thisMonthNewMember;
    }

    public void setThisMonthNewMember(Integer thisMonthNewMember) {
        this.thisMonthNewMember = thisMonthNewMember;
    }

    public Integer getTodayOrderNumber() {
        return todayOrderNumber;
    }

    public void setTodayOrderNumber(Integer todayOrderNumber) {
        this.todayOrderNumber = todayOrderNumber;
    }

    public Integer getTodayVisitsNumber() {
        return todayVisitsNumber;
    }

    public void setTodayVisitsNumber(Integer todayVisitsNumber) {
        this.todayVisitsNumber = todayVisitsNumber;
    }

    public Integer getThisWeekOrderNumber() {
        return thisWeekOrderNumber;
    }

    public void setThisWeekOrderNumber(Integer thisWeekOrderNumber) {
        this.thisWeekOrderNumber = thisWeekOrderNumber;
    }

    public Integer getThisWeekVisitsNumber() {
        return thisWeekVisitsNumber;
    }

    public void setThisWeekVisitsNumber(Integer thisWeekVisitsNumber) {
        this.thisWeekVisitsNumber = thisWeekVisitsNumber;
    }

    public Integer getThisMonthOrderNumber() {
        return thisMonthOrderNumber;
    }

    public void setThisMonthOrderNumber(Integer thisMonthOrderNumber) {
        this.thisMonthOrderNumber = thisMonthOrderNumber;
    }

    public Integer getThisMonthVisitsNumber() {
        return thisMonthVisitsNumber;
    }

    public void setThisMonthVisitsNumber(Integer thisMonthVisitsNumber) {
        this.thisMonthVisitsNumber = thisMonthVisitsNumber;
    }

    public List<HotPackage> getHotPackages() {
        return hotPackages;
    }

    public void setHotPackages(List<HotPackage> hotPackages) {
        this.hotPackages = hotPackages;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }
}
