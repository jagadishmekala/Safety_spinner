package com.example.voltasassignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ZoneId_Response {

    @SerializedName("result")
    @Expose

    private List<ZoneId_Result> result;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("errorCode")
    @Expose
    private Object errorCode;
    @SerializedName("errors")
    @Expose
    private Object errors;

    public List<ZoneId_Result> getResult() {
        return result;
    }

    public void setResult(List<ZoneId_Result> result) {
        this.result = result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Object errorCode) {
        this.errorCode = errorCode;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }
}
