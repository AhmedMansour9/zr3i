
package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Media {

    @SerializedName("field_image")
    @Expose
    private FieldImage fieldImage;
    @SerializedName("field_index_area_image")
    @Expose
    private FieldIndexAreaImage fieldIndexAreaImage;
    @SerializedName("field_report")
    @Expose
    private FieldReport fieldReport;
    @SerializedName("map_details")
    @Expose
    private MapDetails mapDetails;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Media() {
    }

    /**
     * 
     * @param fieldReport
     * @param fieldImage
     * @param fieldIndexAreaImage
     * @param mapDetails
     */
    public Media(FieldImage fieldImage, FieldIndexAreaImage fieldIndexAreaImage, FieldReport fieldReport, MapDetails mapDetails) {
        super();
        this.fieldImage = fieldImage;
        this.fieldIndexAreaImage = fieldIndexAreaImage;
        this.fieldReport = fieldReport;
        this.mapDetails = mapDetails;
    }

    public FieldImage getFieldImage() {
        return fieldImage;
    }

    public void setFieldImage(FieldImage fieldImage) {
        this.fieldImage = fieldImage;
    }

    public FieldIndexAreaImage getFieldIndexAreaImage() {
        return fieldIndexAreaImage;
    }

    public void setFieldIndexAreaImage(FieldIndexAreaImage fieldIndexAreaImage) {
        this.fieldIndexAreaImage = fieldIndexAreaImage;
    }

    public FieldReport getFieldReport() {
        return fieldReport;
    }

    public void setFieldReport(FieldReport fieldReport) {
        this.fieldReport = fieldReport;
    }

    public MapDetails getMapDetails() {
        return mapDetails;
    }

    public void setMapDetails(MapDetails mapDetails) {
        this.mapDetails = mapDetails;
    }

}
