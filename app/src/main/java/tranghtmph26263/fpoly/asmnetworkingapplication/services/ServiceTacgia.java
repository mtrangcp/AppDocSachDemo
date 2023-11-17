package tranghtmph26263.fpoly.asmnetworkingapplication.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.ArrayTGChiTiet;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.ArrayTacgia;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.TacGia;

public interface ServiceTacgia {

    @GET("api/tg")
    Call<ArrayTacgia> getAllTacgia();

    @GET("api/tg/{id}")
    Call<ArrayTGChiTiet> getChiTietTG(@Path("id") String id);
}
