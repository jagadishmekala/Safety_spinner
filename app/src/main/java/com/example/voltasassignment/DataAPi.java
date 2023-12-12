package com.example.voltasassignment;

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
    Call<MapResponse> getmapobservations
            (@Header("Authorization") String Response);

    @GET("safetyapi/api/Report/GetGuestZones/SHQ")
    Call<ZoneResponseCreate>getzones(@Header("Authorization")String Response);

    @GET("safetyapi/api/Report/GetGuestBranchs/{zoneId}")
    Call<ZoneId_Response>getbranch
            (@Header("Authorization")String Response, @Path("zoneId")String zoneId);

    @GET("safetyapi/api/User/SearchUsers/{searchKey}")
    Call<Autotext_Responsee>getsearchusers(
            @Header("Authorization") String Response,
            @Path("searchKey")String searchKey);

    @GET("safetyapi/api/Observation/GetAllClients")
    Call<EntityObservationResponse>getEntitynames(@Header("Authorization")String Response
//            ,@Path("entityName")String entityName
    );

}
