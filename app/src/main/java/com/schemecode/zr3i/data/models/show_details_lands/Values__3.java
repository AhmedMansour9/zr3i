
package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Values__3 {

    @SerializedName("2021-04-06")
    @Expose
    private String _20210406;
    @SerializedName("2021-04-11")
    @Expose
    private String _20210411;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Values__3() {
    }

    /**
     * 
     * @param _20210406
     * @param _20210411
     */
    public Values__3(String _20210406, String _20210411) {
        super();
        this._20210406 = _20210406;
        this._20210411 = _20210411;
    }

    public String get20210406() {
        return _20210406;
    }

    public void set20210406(String _20210406) {
        this._20210406 = _20210406;
    }

    public String get20210411() {
        return _20210411;
    }

    public void set20210411(String _20210411) {
        this._20210411 = _20210411;
    }

}
