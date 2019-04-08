package com.example.supplychain.Rest;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiClient {
   public static final String Base_URL1 = "http://35.154.67.134:3000/";
    public static final String Base_URL2 = "http://192.168.0.160:3000/";
    private static Retrofit retrofit1 = null;
    private static Retrofit retrofit2 = null;
    public static Retrofit getLoginClient(){
        if(retrofit1 == null){
            retrofit1                =       new Retrofit.Builder()
                    .baseUrl(Base_URL1)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit1;
    }
    public static Retrofit getPackageClient(){
        if(retrofit2 == null){
            retrofit2                =       new Retrofit.Builder()
                    .baseUrl(Base_URL2)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit2;
    }
}