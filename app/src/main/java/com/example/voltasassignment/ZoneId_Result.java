package com.example.voltasassignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
    public class ZoneId_Result {

        @SerializedName("branchId")
        @Expose
        private String branchId;
        @SerializedName("branchName")
        @Expose
        private String branchName;

        public String getBranchId() {
            return branchId;
        }

        public void setBranchId(String branchId) {
            this.branchId = branchId;
        }

        public String getBranchName() {
            return branchName;
        }

        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

    }

