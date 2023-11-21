
package com.schemecode.zr3i.data.models.show_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vari {

    @SerializedName("20210406")
    @Expose
    private String _20210406;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Vari() {
    }

    /**
     * 
     * @param _20210406
     */
    public Vari(String _20210406) {
        super();
        this._20210406 = _20210406;
    }

    public String get20210406() {
        return _20210406;
    }

    public void set20210406(String _20210406) {
        this._20210406 = _20210406;
    }

}
