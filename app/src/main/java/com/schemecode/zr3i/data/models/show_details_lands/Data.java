
package com.schemecode.zr3i.data.models.show_details_lands;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("info")
    @Expose
    private Info info;
    @SerializedName("farmers")
    @Expose
    private List<Farmer> farmers = null;
    @SerializedName("media")
    @Expose
    private Media media;
    @SerializedName("weather")
    @Expose
    private Weather weather;
    @SerializedName("soil_data")
    @Expose
    private SoilData soilData;
    @SerializedName("is_data_built")
    @Expose
    private Boolean isDataBuilt;
    @SerializedName("historical_ndvi")
    @Expose
    private HistoricalNdvi historicalNdvi;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param soilData
     * @param isDataBuilt
     * @param weather
     * @param farmers
     * @param media
     * @param historicalNdvi
     * @param info
     */
    public Data(Info info, List<Farmer> farmers, Media media, Weather weather, SoilData soilData, Boolean isDataBuilt, HistoricalNdvi historicalNdvi) {
        super();
        this.info = info;
        this.farmers = farmers;
        this.media = media;
        this.weather = weather;
        this.soilData = soilData;
        this.isDataBuilt = isDataBuilt;
        this.historicalNdvi = historicalNdvi;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Farmer> getFarmers() {
        return farmers;
    }

    public void setFarmers(List<Farmer> farmers) {
        this.farmers = farmers;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public SoilData getSoilData() {
        return soilData;
    }

    public void setSoilData(SoilData soilData) {
        this.soilData = soilData;
    }

    public Boolean getIsDataBuilt() {
        return isDataBuilt;
    }

    public void setIsDataBuilt(Boolean isDataBuilt) {
        this.isDataBuilt = isDataBuilt;
    }

    public HistoricalNdvi getHistoricalNdvi() {
        return historicalNdvi;
    }

    public void setHistoricalNdvi(HistoricalNdvi historicalNdvi) {
        this.historicalNdvi = historicalNdvi;
    }

}
