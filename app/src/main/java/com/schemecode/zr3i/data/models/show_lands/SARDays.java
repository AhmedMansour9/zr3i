
package com.schemecode.zr3i.data.models.show_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SARDays {

    @SerializedName("20210406")
    @Expose
    private String _20210406;
    @SerializedName("20210410")
    @Expose
    private String _20210410;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SARDays() {
    }

    /**
     * 
     * @param _20210406
     * @param _20210410
     */
    public SARDays(String _20210406, String _20210410) {
        super();
        this._20210406 = _20210406;
        this._20210410 = _20210410;
    }

    public String get20210406() {
        return _20210406;
    }

    public void set20210406(String _20210406) {
        this._20210406 = _20210406;
    }

    public String get20210410() {
        return _20210410;
    }

    public void set20210410(String _20210410) {
        this._20210410 = _20210410;
    }

}
