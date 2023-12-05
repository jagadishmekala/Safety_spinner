package com.example.voltasassignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MapResult implements Serializable{

    @SerializedName("observationId")
    @Expose
    private String observationId;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("siteName")
    @Expose
    private String siteName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("dateOfIncidence")
    @Expose
    private String dateOfIncidence;
    @SerializedName("projectManagerName")
    @Expose
    private String projectManagerName;
    @SerializedName("typeOfObservation")
    @Expose
    private String typeOfObservation;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("subType")
    @Expose
    private String subType;
    @SerializedName("targetDateOfClosure")
    @Expose
    private String targetDateOfClosure;
    @SerializedName("createdOn")
    @Expose
    private String createdOn;
    @SerializedName("employeeCode")
    @Expose
    private String employeeCode;
    @SerializedName("employeeName")
    @Expose
    private String employeeName;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("guestName")
    @Expose
    private String guestName;
    @SerializedName("guestPhone")
    @Expose
    private String guestPhone;
    @SerializedName("guestEmail")
    @Expose
    private String guestEmail;
    @SerializedName("guestPfno")
    @Expose
    private String guestPfno;
    @SerializedName("field1")
    @Expose
    private String field1;
    @SerializedName("field2")
    @Expose
    private String field2;
    @SerializedName("guestZoneId")
    @Expose
    private String guestZoneId;
    @SerializedName("guestZone")
    @Expose
    private String guestZone;
    @SerializedName("guestBranchId")
    @Expose
    private String guestBranchId;
    @SerializedName("guestBranch")
    @Expose
    private String guestBranch;
    @SerializedName("concernEngineerOrSupervisor")
    @Expose
    private String concernEngineerOrSupervisor;
    @SerializedName("concernEngineerOrSupervisorEmail")
    @Expose
    private String concernEngineerOrSupervisorEmail;
    @SerializedName("risk")
    @Expose
    private String risk;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("modifiedBy")
    @Expose
    private String modifiedBy;
    @SerializedName("dailyReminderCount")
    @Expose
    private Integer dailyReminderCount;
    @SerializedName("actionclosed")
    @Expose
    private Integer actionclosed;
    @SerializedName("observationclosed")
    @Expose
    private Integer observationclosed;

    public String getObservationId() {
        return observationId;
    }

    public void setObservationId(String observationId) {
        this.observationId = observationId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfIncidence() {
        return dateOfIncidence;
    }

    public void setDateOfIncidence(String dateOfIncidence) {
        this.dateOfIncidence = dateOfIncidence;
    }

    public String getProjectManagerName() {
        return projectManagerName;
    }

    public void setProjectManagerName(String projectManagerName) {
        this.projectManagerName = projectManagerName;
    }

    public String getTypeOfObservation() {
        return typeOfObservation;
    }

    public void setTypeOfObservation(String typeOfObservation) {
        this.typeOfObservation = typeOfObservation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getTargetDateOfClosure() {
        return targetDateOfClosure;
    }

    public void setTargetDateOfClosure(String targetDateOfClosure) {
        this.targetDateOfClosure = targetDateOfClosure;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public String getGuestPfno() {
        return guestPfno;
    }

    public void setGuestPfno(String guestPfno) {
        this.guestPfno = guestPfno;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getGuestZoneId() {
        return guestZoneId;
    }

    public void setGuestZoneId(String guestZoneId) {
        this.guestZoneId = guestZoneId;
    }

    public String getGuestZone() {
        return guestZone;
    }

    public void setGuestZone(String guestZone) {
        this.guestZone = guestZone;
    }

    public String getGuestBranchId() {
        return guestBranchId;
    }

    public void setGuestBranchId(String guestBranchId) {
        this.guestBranchId = guestBranchId;
    }

    public String getGuestBranch() {
        return guestBranch;
    }

    public void setGuestBranch(String guestBranch) {
        this.guestBranch = guestBranch;
    }

    public String getConcernEngineerOrSupervisor() {
        return concernEngineerOrSupervisor;
    }

    public void setConcernEngineerOrSupervisor(String concernEngineerOrSupervisor) {
        this.concernEngineerOrSupervisor = concernEngineerOrSupervisor;
    }

    public String getConcernEngineerOrSupervisorEmail() {
        return concernEngineerOrSupervisorEmail;
    }

    public void setConcernEngineerOrSupervisorEmail(String concernEngineerOrSupervisorEmail) {
        this.concernEngineerOrSupervisorEmail = concernEngineerOrSupervisorEmail;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Integer getDailyReminderCount() {
        return dailyReminderCount;
    }

    public void setDailyReminderCount(Integer dailyReminderCount) {
        this.dailyReminderCount = dailyReminderCount;
    }

    public Integer getActionclosed() {
        return actionclosed;
    }

    public void setActionclosed(Integer actionclosed) {
        this.actionclosed = actionclosed;
    }

    public Integer getObservationclosed() {
        return observationclosed;
    }

    public void setObservationclosed(Integer observationclosed) {
        this.observationclosed = observationclosed;
    }

}
