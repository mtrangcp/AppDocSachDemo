package tranghtmph26263.fpoly.asmnetworkingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tranghtmph26263.fpoly.asmnetworkingapplication.adapter.SachAdapter2;
import tranghtmph26263.fpoly.asmnetworkingapplication.adapter.TacgiaAdapter;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.ArrayTGChiTiet;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.ArrayTacgia;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.Sach;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.TacGia;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.TacGia2;
import tranghtmph26263.fpoly.asmnetworkingapplication.services.ServiceTacgia;

public class TacgiaChitietActivity extends AppCompatActivity {
    static final String TAG = "zzzz";
    TextView tv;
    ListView lv_listsach;
    List<Sach> listSach = new ArrayList<Sach>();
    SachAdapter2 adapter2;

    Retrofit retrofit;
    ServiceTacgia serviceTacgia;
    public static final String API = "http://192.168.1.79:3000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tacgia_chitiet);

        tv = findViewById(R.id.tv_tenTGChiTiet);
        lv_listsach = findViewById(R.id.lv_listSachCuaTG);

        retrofit = new Retrofit.Builder().baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create()).build();
        serviceTacgia = retrofit.create(ServiceTacgia.class);

        String idTacGia = getIntent().getStringExtra("idTacGia");
        String ten = getIntent().getStringExtra("tenTG");
        int namSinh = getIntent().getIntExtra("namSinhTG",0 );

        tv.setText(ten+ " - "+ namSinh);

        Call<ArrayTGChiTiet> call = serviceTacgia.getChiTietTG(idTacGia);
        call.enqueue(new Callback<ArrayTGChiTiet>() {
            @Override
            public void onResponse(Call<ArrayTGChiTiet> call, Response<ArrayTGChiTiet> response) {
                if ( response.isSuccessful()){
                    ArrayTGChiTiet arrayTacgia = response.body();
                    if ( arrayTacgia == null){
                        Toast.makeText(TacgiaChitietActivity.this, "Đối tượng rỗng!", Toast.LENGTH_SHORT).show();

                    }else {
                        TacGia2 tacGia2 = arrayTacgia.getData();
                        listSach = tacGia2.getSachs();
                        Log.d("zzzz", " list sach cua tac gia: "+listSach+ "listSize: "+ listSach.size());

                        adapter2 = new SachAdapter2(listSach);
                        lv_listsach.setAdapter(adapter2);
                    }
                }else{
                    Log.d(TAG, "có lỗi ");
                }
            }
            @Override
            public void onFailure(Call<ArrayTGChiTiet> call, Throwable t) {
                Toast.makeText(TacgiaChitietActivity.this, "co loi: "+ t, Toast.LENGTH_SHORT).show();
                Log.d("zzzzz", "co loi: "+ t);
            }
        });


    }
}