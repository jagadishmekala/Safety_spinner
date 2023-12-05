package com.example.voltasassignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ZoneResultCreate {
    @SerializedName("zoneId")
    @Expose
    private String zoneId;
    @SerializedName("zoneName")
    @Expose
    private String zoneName;

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

}
