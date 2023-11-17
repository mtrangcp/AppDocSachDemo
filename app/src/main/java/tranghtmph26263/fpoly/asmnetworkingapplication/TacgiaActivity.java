package tranghtmph26263.fpoly.asmnetworkingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tranghtmph26263.fpoly.asmnetworkingapplication.adapter.TacgiaAdapter;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.ArrayTacgia;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.TacGia;
import tranghtmph26263.fpoly.asmnetworkingapplication.services.ServiceTacgia;

public class TacgiaActivity extends AppCompatActivity {
    ListView lv_listTG;
    Retrofit retrofit;
    ServiceTacgia serviceTacgia;
    public static final String API = "http://192.168.1.79:3000/";

    List<TacGia> listTacGia = new ArrayList<TacGia>();
    TacgiaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tacgia);

        lv_listTG = findViewById(R.id.lv_listTacgia);
        retrofit = new Retrofit.Builder().baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create()).build();
        serviceTacgia = retrofit.create(ServiceTacgia.class);

        Call<ArrayTacgia> call = serviceTacgia.getAllTacgia();
        call.enqueue(new Callback<ArrayTacgia>() {
            @Override
            public void onResponse(Call<ArrayTacgia> call, Response<ArrayTacgia> response) {
                ArrayTacgia tacgia = response.body();
                if ( tacgia == null){
                    Toast.makeText(TacgiaActivity.this, "Danh sách rỗng!", Toast.LENGTH_SHORT).show();

                }else {
                    listTacGia = Arrays.asList(tacgia.getData());
                    adapter = new TacgiaAdapter(listTacGia);
                    lv_listTG.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<ArrayTacgia> call, Throwable t) {
                Toast.makeText(TacgiaActivity.this, "co loi: "+ t, Toast.LENGTH_SHORT).show();
                Log.d("zzzzz", "co loi: "+ t);
            }
        });

        lv_listTG.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String idTacGia = listTacGia.get(i).get_id();
                String tenTG = listTacGia.get(i).getTen();
                int namSinhTG = listTacGia.get(i).getNamSinh();

                Intent intent = new Intent(TacgiaActivity.this, TacgiaChitietActivity.class);
                intent.putExtra("idTacGia", idTacGia);
                intent.putExtra("tenTG", tenTG);
                intent.putExtra("namSinhTG", namSinhTG);
                startActivity(intent);

            }
        });

    }
}