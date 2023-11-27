package com.example.voltasassignment;
import java.util.List;
import javax.xml.transform.Result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {
        @SerializedName("result")
        @Expose
        private List<HazardResult> result;
        @SerializedName("success")
        @Expose
        private Boolean success;
        @SerializedName("errorCode")
        @Expose
        private String errorCode;
        @SerializedName("errors")
        @Expose
        private String errors;
        public List<HazardResult> getHazardResult() {

            return result;
        }

        public void setHazardResult(List<HazardResult> result) {

            this.result = result;
        }

        public Boolean getSuccess() {

            return success;
        }

        public void setSuccess(Boolean success) {

            this.success = success;
        }

        public String getErrorCode()
        {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {

            this.errorCode = errorCode;
        }

        public String getErrors() {

            return errors;
        }

        public void setErrors(String errors) {

            this.errors = errors;
        }

    }

