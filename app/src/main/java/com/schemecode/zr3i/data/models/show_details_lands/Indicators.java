
package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Indicators {

    @SerializedName("si")
    @Expose
    private Si si;
    @SerializedName("avi")
    @Expose
    private Avi avi;
    @SerializedName("bsi")
    @Expose
    private Bsi bsi;
    @SerializedName("evi")
    @Expose
    private Evi evi;
    @SerializedName("soc")
    @Expose
    private Soc soc;
    @SerializedName("ndre")
    @Expose
    private Ndre ndre;
    @SerializedName("ndvi")
    @Expose
    private Ndvi ndvi;
    @SerializedName("ndwi")
    @Expose
    private Ndwi ndwi;
    @SerializedName("savi")
    @Expose
    private Savi savi;
    @SerializedName("vari")
    @Expose
    private Vari vari;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Indicators() {
    }

    /**
     * 
     * @param savi
     * @param avi
     * @param vari
     * @param si
     * @param soc
     * @param bsi
     * @param evi
     * @param ndre
     * @param ndvi
     * @param ndwi
     */
    public Indicators(Si si, Avi avi, Bsi bsi, Evi evi, Soc soc, Ndre ndre, Ndvi ndvi, Ndwi ndwi, Savi savi, Vari vari) {
        super();
        this.si = si;
        this.avi = avi;
        this.bsi = bsi;
        this.evi = evi;
        this.soc = soc;
        this.ndre = ndre;
        this.ndvi = ndvi;
        this.ndwi = ndwi;
        this.savi = savi;
        this.vari = vari;
    }

    public Si getSi() {
        return si;
    }

    public void setSi(Si si) {
        this.si = si;
    }

    public Avi getAvi() {
        return avi;
    }

    public void setAvi(Avi avi) {
        this.avi = avi;
    }

    public Bsi getBsi() {
        return bsi;
    }

    public void setBsi(Bsi bsi) {
        this.bsi = bsi;
    }

    public Evi getEvi() {
        return evi;
    }

    public void setEvi(Evi evi) {
        this.evi = evi;
    }

    public Soc getSoc() {
        return soc;
    }

    public void setSoc(Soc soc) {
        this.soc = soc;
    }

    public Ndre getNdre() {
        return ndre;
    }

    public void setNdre(Ndre ndre) {
        this.ndre = ndre;
    }

    public Ndvi getNdvi() {
        return ndvi;
    }

    public void setNdvi(Ndvi ndvi) {
        this.ndvi = ndvi;
    }

    public Ndwi getNdwi() {
        return ndwi;
    }

    public void setNdwi(Ndwi ndwi) {
        this.ndwi = ndwi;
    }

    public Savi getSavi() {
        return savi;
    }

    public void setSavi(Savi savi) {
        this.savi = savi;
    }

    public Vari getVari() {
        return vari;
    }

    public void setVari(Vari vari) {
        this.vari = vari;
    }

}
