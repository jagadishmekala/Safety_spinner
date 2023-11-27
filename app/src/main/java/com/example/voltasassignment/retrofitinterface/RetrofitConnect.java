package com.example.voltasassignment.retrofitinterface;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnect {
   Retrofit retrofit;
    String URL =("https://vserveq.voltasworld.com/");
   public  Retrofit getLink() {
      if (retrofit == null) {
         HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
         httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
         OkHttpClient okHttpClient = new OkHttpClient.Builder()
                 .addInterceptor(httpLoggingInterceptor)
                 .build();

         retrofit = new Retrofit.Builder()
                 .baseUrl(URL)
                 .client(okHttpClient)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
                              }
      return retrofit;
                                     }

}
