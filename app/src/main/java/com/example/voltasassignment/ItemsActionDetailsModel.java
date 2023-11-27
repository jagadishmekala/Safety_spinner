package com.example.voltasassignment;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemsActionDetailsModel {
    @SerializedName("observationAttachmentModels")
    @Expose
    private List<ObservationAttachmentModel__2> observationAttachmentModels;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("observationItemId")
    @Expose
    private Integer observationItemId;
    @SerializedName("actionName")
    @Expose
    private String actionName;
    @SerializedName("targetDateOfClosure")
    @Expose
    private String targetDateOfClosure;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("reason")
    @Expose
    private List<Integer> reason;
    @SerializedName("adminRemarks")
    @Expose
    private String adminRemarks;
    @SerializedName("cpRemarks")
    @Expose
    private String cpRemarks;
    @SerializedName("modifiedBy")
    @Expose
    private String modifiedBy;

    public List<ObservationAttachmentModel__2> getObservationAttachmentModels() {
        return observationAttachmentModels;
    }

    public void setObservationAttachmentModels(List<ObservationAttachmentModel__2> observationAttachmentModels) {
        this.observationAttachmentModels = observationAttachmentModels;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getObservationItemId() {
        return observationItemId;
    }

    public void setObservationItemId(Integer observationItemId) {
        this.observationItemId = observationItemId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getTargetDateOfClosure() {
        return targetDateOfClosure;
    }

    public void setTargetDateOfClosure(String targetDateOfClosure) {
        this.targetDateOfClosure = targetDateOfClosure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Integer> getReason() {
        return reason;
    }

    public void setReason(List<Integer> reason) {
        this.reason = reason;
    }

    public String getAdminRemarks() {
        return adminRemarks;
    }

    public void setAdminRemarks(String adminRemarks) {
        this.adminRemarks = adminRemarks;
    }

    public String getCpRemarks() {
        return cpRemarks;
    }

    public void setCpRemarks(String cpRemarks) {
        this.cpRemarks = cpRemarks;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}



