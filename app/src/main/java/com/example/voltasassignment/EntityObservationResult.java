package com.example.voltasassignment;
import java.util.List;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class EntityObservationResult {
    @SerializedName("clientId")
    @Expose
    private String clientId;
    @SerializedName("clientName")
    @Expose
    private String clientName;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }


}
