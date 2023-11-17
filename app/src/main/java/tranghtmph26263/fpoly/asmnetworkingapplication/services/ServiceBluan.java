package tranghtmph26263.fpoly.asmnetworkingapplication.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.BinhLuan;

public interface ServiceBluan {
    @POST("api/bluan/add")
    Call<BinhLuan> AddBL(@Body BinhLuan data);

    @DELETE("api/bluan/delete/{id}")
    Call<BinhLuan> deleteBluanById(@Path("id") String id);

    @PUT("api/bluan/update/{id}")
    Call<BinhLuan> editBluanById(@Path("id") String id, @Body BinhLuan data);

}
