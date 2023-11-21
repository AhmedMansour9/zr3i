
package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Charts {

    @SerializedName("first")
    @Expose
    private First first;
    @SerializedName("second")
    @Expose
    private Second second;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Charts() {
    }

    /**
     * 
     * @param first
     * @param second
     */
    public Charts(First first, Second second) {
        super();
        this.first = first;
        this.second = second;
    }

    public First getFirst() {
        return first;
    }

    public void setFirst(First first) {
        this.first = first;
    }

    public Second getSecond() {
        return second;
    }

    public void setSecond(Second second) {
        this.second = second;
    }

}
