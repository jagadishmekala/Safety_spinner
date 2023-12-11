package com.example.voltasassignment;
import java.util.List;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class EntityObservationResult {

    @SerializedName("entityName")
    @Expose
    private String entityName;
    @SerializedName("lookUpName")
    @Expose
    private String lookUpName;
    @SerializedName("lookUpValue")
    @Expose
    private String lookUpValue;
    @SerializedName("lookUpId")
    @Expose
    private String lookUpId;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getLookUpName() {
        return lookUpName;
    }

    public void setLookUpName(String lookUpName) {
        this.lookUpName = lookUpName;
    }

    public String getLookUpValue() {
        return lookUpValue;
    }

    public void setLookUpValue(String lookUpValue) {
        this.lookUpValue = lookUpValue;
    }

    public String getLookUpId() {
        return lookUpId;
    }

    public void setLookUpId(String lookUpId) {
        this.lookUpId = lookUpId;
    }

}
