package tranghtmph26263.fpoly.asmnetworkingapplication.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.ArrayUser;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.User;

public interface ServiceUser {

    @GET("api/user")
    Call<ArrayUser> getAllUser();

    @POST("api/user/dn")
    Call<User> checkUser(@Body User data);

    @POST("api/user/add")
    Call<User> addUser(@Body User data);



}
