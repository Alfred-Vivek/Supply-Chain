package com.example.supplychain.Rest;
import com.example.supplychain.Response.LoginResponse;
import com.example.supplychain.Response.LogisticPackageResponse;
import com.example.supplychain.Response.LogisticPackageAcceptResponse;
import com.example.supplychain.Response.TpwPackageAcceptResponse;
import com.example.supplychain.Response.TpwPackageResponse;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("auth/login/")
    Call<LoginResponse> sendLoginDetails(@FieldMap Map<String, String> map);

    @GET("logistic/listofawbs/")
    Call<LogisticPackageResponse> getPackage(@Query("orgName") String dcorg);

    @PUT("logistic/updateASN/")
    Call<LogisticPackageAcceptResponse> sendLogisticAcceptance(@Body Map<String,String>  map);

    @GET("tpw/listofawbs/")
    Call<TpwPackageResponse> getTpwPackage(@Query("orgName") String dcorg, @Query("flag") Boolean flag);

    @POST("tpw/updateStatus/")
    Call<TpwPackageAcceptResponse> sendTpwAcceptance(@Body Map<String, String> request);
}
