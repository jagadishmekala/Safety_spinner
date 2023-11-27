package com.example.voltasassignment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListviewClickpojo {

        @SerializedName("result")
        @Expose
        private ListDetailsResult result;
        @SerializedName("success")
        @Expose
        private Boolean success;
        @SerializedName("errorCode")
        @Expose
        private String errorCode;
        @SerializedName("errors")
        @Expose
        private String errors;

        public ListDetailsResult getResult() {

            return result;
        }

        public void setResult(ListDetailsResult result) {

            this.result = result;
        }

        public Boolean getSuccess() {

            return success;
        }

        public void setSuccess(Boolean success) {

            this.success = success;
        }

        public String getErrorCode() {

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
