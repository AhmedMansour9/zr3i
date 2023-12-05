
package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.schemecode.zr3i.data.models.show_lands.ListDataByTime;

import java.util.List;


public class Forcasting {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private Integer message;
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private List<ListDataByTime> list;
//    @SerializedName("city")
//    @Expose
//    private City city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<ListDataByTime> getList() {
        return list;
    }

    public void setList(List<ListDataByTime> list) {
        this.list = list;
    }

//    public City getCity() {
//        return city;
//    }
//
//    public void setCity(City city) {
//        this.city = city;
//    }


//    @SerializedName("by_day_times")
//    @Expose
//    private Object byDayTimes;
//    @SerializedName("by_days")
//    @Expose
//    private Object byDays;
//
//    /**
//     * No args constructor for use in serialization
//     *
//     */
//    public Forcasting() {
//    }
//
//    /**
//     *
//     * @param byDays
//     * @param byDayTimes
//     */
//    public Forcasting(ByDayTimes byDayTimes, ByDays byDays) {
//        super();
//        this.byDayTimes = byDayTimes;
//        this.byDays = byDays;
//    }
//
//    public Object getByDayTimes() {
//        return byDayTimes;
//    }
//
//    public void setByDayTimes(ByDayTimes byDayTimes) {
//        this.byDayTimes = byDayTimes;
//    }
//
//    public Object getByDays() {
//        return byDays;
//    }
//
//    public void setByDays(ByDays byDays) {
//        this.byDays = byDays;
//    }

}
