package tranghtmph26263.fpoly.asmnetworkingapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tranghtmph26263.fpoly.asmnetworkingapplication.adapter.SachAdapter;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.ArraySach;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.ArrayUser;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.BinhLuan;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.Sach;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.User;
import tranghtmph26263.fpoly.asmnetworkingapplication.services.ServiceSach;
import tranghtmph26263.fpoly.asmnetworkingapplication.services.ServiceUser;

public class SachActivity extends AppCompatActivity {
    ListView lv_sach;
    Retrofit retrofit;
    ServiceSach serviceSach;
    ServiceUser serviceUser;
    public static final String API = "http://192.168.1.79:3000/";

    List<Sach> listSach = new ArrayList<Sach>();
    List<User> listUser = new ArrayList<User>();
    SachAdapter adapter;
    Button btn_addSach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);

        lv_sach = findViewById(R.id.lv_listSach);
        btn_addSach = findViewById(R.id.btn_addSach);

        retrofit = new Retrofit.Builder().baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create()).build();
        serviceSach = retrofit.create(ServiceSach.class);

        Call<ArraySach> call =serviceSach.getAllSach();
        call.enqueue(new Callback<ArraySach>() {
            @Override
            public void onResponse(Call<ArraySach> call, Response<ArraySach> response) {
                ArraySach sach = response.body();
                if ( sach == null){
                    Toast.makeText(SachActivity.this, "Danh sách rỗng!", Toast.LENGTH_SHORT).show();
                }else{
                    listSach = Arrays.asList(sach.getData());
                    adapter = new SachAdapter(getApplicationContext(), listSach);
                    lv_sach.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<ArraySach> call, Throwable t) {
                Toast.makeText(SachActivity.this, "co loi: "+ t, Toast.LENGTH_SHORT).show();
                Log.d("zzzzz", "co loi: "+ t);
            }
        });

        lv_sach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String linkAnh = listSach.get(i).getAnhBia();
                String idS = listSach.get(i).get_id();
                String tenSach = listSach.get(i).getTenSach();
                int namSx = listSach.get(i).getNamsx();

                Intent intent = new Intent( SachActivity.this, SachChiTietActivity.class);
                intent.putExtra("linkAnh", linkAnh);
                intent.putExtra("tenSach", tenSach);
                intent.putExtra("idS", idS);
                intent.putExtra("namSx", namSx);
                startActivity(intent);
            }
        });

        btn_addSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("USER_LOGIN", MODE_PRIVATE);
                String usernameLogin = sharedPreferences.getString("Username", null);
                Log.d("zzzz", "onClick: "+ usernameLogin);

                if (usernameLogin.equalsIgnoreCase("nanh")){
                    final Dialog dialog = new Dialog(SachActivity.this, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
                    dialog.setContentView(R.layout.layout_add_sach);

                    TextInputEditText ed_tenSach, ed_mota, ed_idTacGia, ed_namSx, ed_linkAnh;
                    Button btnSaveAdd = dialog.findViewById(R.id.btn_saveAddSach);
                    ed_tenSach = dialog.findViewById(R.id.ed_add_tenSach);
                    ed_mota = dialog.findViewById(R.id.ed_add_mota);
                    ed_idTacGia = dialog.findViewById(R.id.ed_add_idtacGia);
                    ed_namSx = dialog.findViewById(R.id.ed_add_namSX);
                    ed_linkAnh = dialog.findViewById(R.id.ed_add_linkAnh);

                    btnSaveAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (ed_tenSach.getText().toString().trim().isEmpty() ){
                                Toast.makeText(SachActivity.this, "Vui lòng nhập ten sach", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (ed_mota.getText().toString().trim().isEmpty() ){
                                Toast.makeText(SachActivity.this, "Vui lòng nhập mo ta", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (ed_idTacGia.getText().toString().trim().isEmpty() ){
                                Toast.makeText(SachActivity.this, "Vui lòng nhập id tac gia", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (ed_namSx.getText().toString().trim().isEmpty() ){
                                Toast.makeText(SachActivity.this, "Vui lòng nhập nam suat ban", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (ed_linkAnh.getText().toString().trim().isEmpty() ){
                                Toast.makeText(SachActivity.this, "Vui lòng nhập link anh", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            Sach newSach = new Sach();
                            newSach.setTenSach(ed_tenSach.getText().toString());
                            newSach.setMoTa(ed_mota.getText().toString());
                            newSach.setNamsx(Integer.parseInt(ed_namSx.getText().toString()));
                            newSach.setTacGia(ed_idTacGia.getText().toString());
                            newSach.setAnhBia(ed_linkAnh.getText().toString());

                            adapter.notifyDataSetChanged();

                            Call<Sach> call1 = serviceSach.addSach(newSach);
                            call1.enqueue(new Callback<Sach>() {
                                @Override
                                public void onResponse(Call<Sach> call, Response<Sach> response) {

                                    Toast.makeText(SachActivity.this, "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }

                                @Override
                                public void onFailure(Call<Sach> call, Throwable t) {
                                    Toast.makeText(SachActivity.this, "Lỗi: "+ t, Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            });
                        }
                    });
                    dialog.show();

                }else{
                    Toast.makeText(SachActivity.this, "Chỉ admin mới có thể dùng chức năng này!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}