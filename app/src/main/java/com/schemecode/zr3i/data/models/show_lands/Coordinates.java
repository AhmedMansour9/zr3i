
package com.schemecode.zr3i.data.models.show_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coordinates {

    @SerializedName("a")
    @Expose
    private A a;
    @SerializedName("P_1")
    @Expose
    private P1 p1;
    @SerializedName("P_2")
    @Expose
    private P2 p2;
    @SerializedName("P_3")
    @Expose
    private P3 p3;
    @SerializedName("P_4")
    @Expose
    private P4 p4;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Coordinates() {
    }

    /**
     * 
     * @param a
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     */
    public Coordinates(A a, P1 p1, P2 p2, P3 p3, P4 p4) {
        super();
        this.a = a;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public P1 getP1() {
        return p1;
    }

    public void setP1(P1 p1) {
        this.p1 = p1;
    }

    public P2 getP2() {
        return p2;
    }

    public void setP2(P2 p2) {
        this.p2 = p2;
    }

    public P3 getP3() {
        return p3;
    }

    public void setP3(P3 p3) {
        this.p3 = p3;
    }

    public P4 getP4() {
        return p4;
    }

    public void setP4(P4 p4) {
        this.p4 = p4;
    }

}
