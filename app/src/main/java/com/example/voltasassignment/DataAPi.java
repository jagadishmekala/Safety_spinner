package com.example.voltasassignment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DataAPi {
    @POST("safetyapi/api/User/SignIn")
    Call<LoginResponse> Signin(@Body LoginData loginData);

    @GET("safetyapi/api/Observation/GetUserObservations/{searchKey}/{type}/{subType}")
    Call<Example> getUserObservations(
            @Header("Authorization") String token, @Path("searchKey") String searchkey, @Path("type") String type,
            @Path("subType") String subType);

    @GET("safetyapi/api/Observation/GetUserObservations/{type}/{subType}")
    Call<Example> getAllObservations(
            @Header("Authorization") String token, @Path("type") String type,
            @Path("subType") String subType);

    @GET("safetyapi/api/Observation/GetById/{observationId}")
   Call<ListviewClickpojo> getObservationById(
           @Header("Authorization") String token,
           @Path("observationId") String observationId
    );
    @GET("safetyapi/api/Observation/GetUserObservations")
    Call<ListDetailsResult> getAllObservations
            (@Header("Authorization") String Response);

}
