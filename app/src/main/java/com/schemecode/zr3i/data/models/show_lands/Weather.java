
package com.schemecode.zr3i.data.models.show_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("20210407")
    @Expose
    private com.schemecode.zr3i.data.models.show_lands._20210407 _20210407;
    @SerializedName("20210408")
    @Expose
    private com.schemecode.zr3i.data.models.show_lands._20210408 _20210408;
    @SerializedName("20210410")
    @Expose
    private com.schemecode.zr3i.data.models.show_lands._20210410 _20210410;
    @SerializedName("20210411")
    @Expose
    private com.schemecode.zr3i.data.models.show_lands._20210411 _20210411;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Weather() {
    }

    /**
     * 
     * @param _20210407
     * @param _20210411
     * @param _20210410
     * @param _20210408
     */
    public Weather(com.schemecode.zr3i.data.models.show_lands._20210407 _20210407, com.schemecode.zr3i.data.models.show_lands._20210408 _20210408, com.schemecode.zr3i.data.models.show_lands._20210410 _20210410, com.schemecode.zr3i.data.models.show_lands._20210411 _20210411) {
        super();
        this._20210407 = _20210407;
        this._20210408 = _20210408;
        this._20210410 = _20210410;
        this._20210411 = _20210411;
    }

    public com.schemecode.zr3i.data.models.show_lands._20210407 get20210407() {
        return _20210407;
    }

    public void set20210407(com.schemecode.zr3i.data.models.show_lands._20210407 _20210407) {
        this._20210407 = _20210407;
    }

    public com.schemecode.zr3i.data.models.show_lands._20210408 get20210408() {
        return _20210408;
    }

    public void set20210408(com.schemecode.zr3i.data.models.show_lands._20210408 _20210408) {
        this._20210408 = _20210408;
    }

    public com.schemecode.zr3i.data.models.show_lands._20210410 get20210410() {
        return _20210410;
    }

    public void set20210410(com.schemecode.zr3i.data.models.show_lands._20210410 _20210410) {
        this._20210410 = _20210410;
    }

    public com.schemecode.zr3i.data.models.show_lands._20210411 get20210411() {
        return _20210411;
    }

    public void set20210411(com.schemecode.zr3i.data.models.show_lands._20210411 _20210411) {
        this._20210411 = _20210411;
    }

}
