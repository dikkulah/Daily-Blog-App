package com.dikkulah.retrofit.request;

import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface BlogServiceRequest {

    @POST("/api/v1/blog")
    Call<JsonElement> blogSave(@Body JsonElement jsonElement, @Header("GatewayToken") String token);

    //LIST
    @GET("/api/v1/blog")
    Call<List<JsonElement>> blogList(@Header("GatewayToken") String token);

    //FIND
    @GET("/api/v1/blog/{id}")
    Call<JsonElement> blogFind(@Path("id") String id, @Header("GatewayToken") String token);


    //DELETE
    @DELETE("/api/v1/blog/{id}")
    Call<JsonElement> blogDelete(@Path("id") String id, @Header("GatewayToken") String token);


    //UPDATE
    @PUT("/api/v1/blog/{id}")
    Call<JsonElement> blogUpdate(@Path("id") String id, @Body JsonElement jsonElement, @Header("GatewayToken") String token);

}
