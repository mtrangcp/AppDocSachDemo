package tranghtmph26263.fpoly.asmnetworkingapplication;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tranghtmph26263.fpoly.asmnetworkingapplication.adapter.BinhLuanAdapter;
import tranghtmph26263.fpoly.asmnetworkingapplication.adapter.SachAdapter2;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.ArraySachChiTiet;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.ArrayTGChiTiet;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.ArrayTacgia;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.ArrayUser;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.BinhLuan;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.Sach;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.Sach2;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.TacGia;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.TacGia2;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.User;
import tranghtmph26263.fpoly.asmnetworkingapplication.services.ServiceBluan;
import tranghtmph26263.fpoly.asmnetworkingapplication.services.ServiceSach;
import tranghtmph26263.fpoly.asmnetworkingapplication.services.ServiceTacgia;
import tranghtmph26263.fpoly.asmnetworkingapplication.services.ServiceUser;

public class SachChiTietActivity extends AppCompatActivity {
    static final String TAG = "zzzz";
    Retrofit retrofit;
    ServiceSach serviceSach;
    ServiceBluan serviceBluan;
    ServiceUser serviceUser;
    public static final String API = "http://192.168.1.79:3000/";

    List<BinhLuan> listBLuan = new ArrayList<BinhLuan>();
    List<User> listUser = new ArrayList<User>();

    TextView tv_tenSach, tv_namSX, tv_tacGia, tv_mota, txt_addBl;
    ImageView imgAnhBia;
    ListView lv_binhLuan;
    BinhLuanAdapter adapter;

    String idUser;
    String usernameSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach_chi_tiet);

        String tensach = getIntent().getStringExtra("tenSach");
        String idS = getIntent().getStringExtra("idS");
        String linkAnh = getIntent().getStringExtra("linkAnh");
        int namSX = getIntent().getIntExtra("namSx", 0);

        retrofit = new Retrofit.Builder().baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create()).build();
        serviceSach = retrofit.create(ServiceSach.class);
        serviceBluan = retrofit.create(ServiceBluan.class);
        serviceUser = retrofit.create(ServiceUser.class);

        tv_mota = findViewById(R.id.tv_mota_sachchitiet);
        tv_tenSach = findViewById(R.id.tv_tenSach_chitiet);
        tv_namSX = findViewById(R.id.tv_namSX_chitiet);
        tv_tacGia = findViewById(R.id.tv_tacgia_chitiet);
        lv_binhLuan = findViewById(R.id.lv_listBLuan);
        imgAnhBia = findViewById(R.id.img_anhBia_chitiet);
        txt_addBl= findViewById(R.id.txt_addBL);

        tv_tenSach.setText(tensach);
        tv_namSX.setText("Năm sx: "+namSX);
        Glide.with(SachChiTietActivity.this).load(linkAnh).into(imgAnhBia);

        Call<ArraySachChiTiet> call1 = serviceSach.getChiTietSach(idS);
        call1.enqueue(new Callback<ArraySachChiTiet>() {
            @Override
            public void onResponse(Call<ArraySachChiTiet> call, Response<ArraySachChiTiet> response) {
                if ( response.isSuccessful()){
                    ArraySachChiTiet arraySach = response.body();
                    if ( arraySach == null){
                        Toast.makeText(SachChiTietActivity.this, "Đối tượng rỗng!", Toast.LENGTH_SHORT).show();

                    }else {
                        Sach2 sach2 = arraySach.getData();
                        TacGia tacGia = sach2.getTacGia();
                        tv_tacGia.setText("Tác giả: "+tacGia.getTen()+ " ( "+ tacGia.getNamSinh()+ " ) ");

                        listBLuan = sach2.getBinhLuan();
                        tv_mota.setText(sach2.getMoTa());
                        Log.d("zzzz", " list bluan cua sach: "+listBLuan+ "listSize: "+ listBLuan.size());

                        adapter = new BinhLuanAdapter( getApplicationContext() ,listBLuan);
                        lv_binhLuan.setAdapter(adapter);

                    }
                }else{
                    Log.d(TAG, "có lỗi ");
                }
            }
            @Override
            public void onFailure(Call<ArraySachChiTiet> call, Throwable t) {
                Toast.makeText(SachChiTietActivity.this, "co loi: "+ t, Toast.LENGTH_SHORT).show();
                Log.d("zzzzz", "co loi: "+ t);
            }
        });

        txt_addBl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(SachChiTietActivity.this, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setContentView(R.layout.layout_add_bluan);

                TextInputEditText ed_nd = dialog.findViewById(R.id.ed_add_noiDungBL);
                Button btnSaveAdd = dialog.findViewById(R.id.btn_saveAddBL);

                SharedPreferences sharedPreferences = getSharedPreferences("USER_LOGIN", MODE_PRIVATE);
                String username = sharedPreferences.getString("Username", null);
                Log.d(TAG, "onClick: "+ username);

                Call<ArrayUser> call = serviceUser.getAllUser( );
                call.enqueue(new Callback<ArrayUser>() {
                    @Override
                    public void onResponse(Call<ArrayUser> call, Response<ArrayUser> response) {
                        if ( response.isSuccessful()){
                            ArrayUser arrayUser = response.body();
                            listUser = Arrays.asList(arrayUser.getData());
                            for ( User u: listUser){
                                if ( u.getUsername().equalsIgnoreCase(username)){
                                    idUser = u.get_id();
                                }
                            }
                        }else{
                            Log.d("zzzzz", "co loi "+response.body());
                            Toast.makeText(getApplicationContext(), "co loi", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ArrayUser> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Lỗi: "+ t, Toast.LENGTH_SHORT).show();
                        Log.d("zzzz", "Lỗi: "+ t);
                    }
                });

                btnSaveAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (ed_nd.getText().toString().trim().isEmpty()) {
                            Toast.makeText(SachChiTietActivity.this, "Vui lòng nhập nội dung bình luận của bạn!", Toast.LENGTH_SHORT).show();
                        }
                        Date currentDate = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                        String date = dateFormat.format(currentDate);

                        BinhLuan binhLuan = new BinhLuan();
                        binhLuan.setNoiDung(ed_nd.getText().toString());
                        binhLuan.setIdSach(idS);
                        binhLuan.setDate(date);
                        binhLuan.setIdUser(idUser);

                        listBLuan.add(binhLuan);
                        adapter.notifyDataSetChanged();

                        Call<BinhLuan> call = serviceBluan.AddBL(binhLuan);
                        call.enqueue(new Callback<BinhLuan>() {
                            @Override
                            public void onResponse(Call<BinhLuan> call, Response<BinhLuan> response) {
                                if (response.isSuccessful() ){
                                    Toast.makeText(SachChiTietActivity.this, "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();

                                    adapter = new BinhLuanAdapter(getApplicationContext(), listBLuan);
                                    lv_binhLuan.setAdapter(adapter);
                                }else{
                                    Toast.makeText(SachChiTietActivity.this, "Loi: "+ response, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<BinhLuan> call, Throwable t) {
                                Toast.makeText(SachChiTietActivity.this, "Lỗi: " + t, Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                    }
                });
                dialog.show();

                Call<ArraySachChiTiet> call1 = serviceSach.getChiTietSach(idS);
                call1.enqueue(new Callback<ArraySachChiTiet>() {
                    @Override
                    public void onResponse(Call<ArraySachChiTiet> call, Response<ArraySachChiTiet> response) {
                        if ( response.isSuccessful()){
                            ArraySachChiTiet arraySach = response.body();
                            if ( arraySach == null){
                                Toast.makeText(SachChiTietActivity.this, "Đối tượng rỗng!", Toast.LENGTH_SHORT).show();

                            }else {
                                Sach2 sach2 = arraySach.getData();

                                listBLuan = sach2.getBinhLuan();
                                adapter = new BinhLuanAdapter( getApplicationContext() ,listBLuan);
                                lv_binhLuan.setAdapter(adapter);
                            }
                        }else{
                            Log.d(TAG, "có lỗi ");
                        }
                    }
                    @Override
                    public void onFailure(Call<ArraySachChiTiet> call, Throwable t) {
                        Toast.makeText(SachChiTietActivity.this, "co loi: "+ t, Toast.LENGTH_SHORT).show();
                        Log.d("zzzzz", "co loi: "+ t);
                    }
                });

            }
        });

    }

    @Override
    protected void onResume() {

        super.onResume();

        String tensach = getIntent().getStringExtra("tenSach");
        String idS = getIntent().getStringExtra("idS");
        String linkAnh = getIntent().getStringExtra("linkAnh");
        int namSX = getIntent().getIntExtra("namSx", 0);

        retrofit = new Retrofit.Builder().baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create()).build();
        serviceSach = retrofit.create(ServiceSach.class);
        serviceBluan = retrofit.create(ServiceBluan.class);
        serviceUser = retrofit.create(ServiceUser.class);

        tv_mota = findViewById(R.id.tv_mota_sachchitiet);
        tv_tenSach = findViewById(R.id.tv_tenSach_chitiet);
        tv_namSX = findViewById(R.id.tv_namSX_chitiet);
        tv_tacGia = findViewById(R.id.tv_tacgia_chitiet);
        lv_binhLuan = findViewById(R.id.lv_listBLuan);
        imgAnhBia = findViewById(R.id.img_anhBia_chitiet);
        txt_addBl= findViewById(R.id.txt_addBL);

        tv_tenSach.setText(tensach);
        tv_namSX.setText("Năm sx: "+namSX);
        Glide.with(SachChiTietActivity.this).load(linkAnh).into(imgAnhBia);

        Call<ArraySachChiTiet> call1 = serviceSach.getChiTietSach(idS);
        call1.enqueue(new Callback<ArraySachChiTiet>() {
            @Override
            public void onResponse(Call<ArraySachChiTiet> call, Response<ArraySachChiTiet> response) {
                if ( response.isSuccessful()){
                    ArraySachChiTiet arraySach = response.body();
                    if ( arraySach == null){
                        Toast.makeText(SachChiTietActivity.this, "Đối tượng rỗng!", Toast.LENGTH_SHORT).show();

                    }else {
                        Sach2 sach2 = arraySach.getData();
                        TacGia tacGia = sach2.getTacGia();
                        tv_tacGia.setText("Tác giả: "+tacGia.getTen()+ " ( "+ tacGia.getNamSinh()+ " ) ");

                        listBLuan = sach2.getBinhLuan();
                        tv_mota.setText(sach2.getMoTa());
                        Log.d("zzzz", " list bluan cua sach: "+listBLuan+ "listSize: "+ listBLuan.size());

                        adapter = new BinhLuanAdapter( getApplicationContext() ,listBLuan);
                        lv_binhLuan.setAdapter(adapter);

                    }
                }else{
                    Log.d(TAG, "có lỗi ");
                }
            }
            @Override
            public void onFailure(Call<ArraySachChiTiet> call, Throwable t) {
                Toast.makeText(SachChiTietActivity.this, "co loi: "+ t, Toast.LENGTH_SHORT).show();
                Log.d("zzzzz", "co loi: "+ t);
            }
        });



    }
}