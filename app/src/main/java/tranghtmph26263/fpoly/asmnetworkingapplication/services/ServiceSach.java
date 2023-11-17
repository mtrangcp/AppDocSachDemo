package tranghtmph26263.fpoly.asmnetworkingapplication.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.ArraySach;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.ArraySachChiTiet;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.BinhLuan;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.Sach;

public interface ServiceSach {

    @GET("api/sach")
    Call<ArraySach>  getAllSach();

    @GET("api/sach/{id}")
    Call<ArraySachChiTiet> getChiTietSach(@Path("id") String id);

    @POST("api/sach/add")
    Call<Sach> addSach(@Body Sach data);

    @DELETE("api/sach/delete/{id}")
    Call<Sach> deleteSachById(@Path("id") String id);

    @PUT("api/sach/update/{id}")
    Call<Sach> editSachById(@Path("id") String id, @Body Sach data);

}
