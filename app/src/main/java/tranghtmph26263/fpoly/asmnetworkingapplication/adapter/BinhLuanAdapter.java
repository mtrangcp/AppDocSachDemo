package tranghtmph26263.fpoly.asmnetworkingapplication.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tranghtmph26263.fpoly.asmnetworkingapplication.MainActivity;
import tranghtmph26263.fpoly.asmnetworkingapplication.R;
import tranghtmph26263.fpoly.asmnetworkingapplication.SachChiTietActivity;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.ArraySachChiTiet;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.ArrayUser;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.BinhLuan;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.Sach2;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.User;
import tranghtmph26263.fpoly.asmnetworkingapplication.login.LoginActivity;
import tranghtmph26263.fpoly.asmnetworkingapplication.services.ServiceBluan;
import tranghtmph26263.fpoly.asmnetworkingapplication.services.ServiceTacgia;
import tranghtmph26263.fpoly.asmnetworkingapplication.services.ServiceUser;

public class BinhLuanAdapter extends BaseAdapter {
    final String TAG = "zzzz";
    Context context;
    List<BinhLuan> listBLuan;
    List<User> listUser = new ArrayList<User>();

    Retrofit retrofit;
    ServiceBluan serviceBluan;
    ServiceUser serviceUser;
    public static final String API = "http://192.168.1.79:3000/";

    String name;

    public BinhLuanAdapter(Context context, List<BinhLuan> listBLuan) {
        this.context = context;
        this.listBLuan = listBLuan;
    }

    @Override
    public int getCount() {
        return listBLuan.size();
    }

    @Override
    public Object getItem(int i) {
        BinhLuan obj = listBLuan.get(i);
        return obj;
    }

    @Override
    public long getItemId(int i) {
        BinhLuan obj = listBLuan.get(i);
        return obj.get__v();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView;
        if ( view == null){
            itemView = View.inflate(viewGroup.getContext(), R.layout.item_bluan, null);
        }else{
            itemView  = view;
        }
        retrofit = new Retrofit.Builder().baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create()).build();
        serviceBluan = retrofit.create(ServiceBluan.class);
        serviceUser = retrofit.create(ServiceUser.class);

        BinhLuan  binhLuan = listBLuan.get(i);
        TextView tv_tenUser = itemView.findViewById(R.id.tv_tenUser);
        TextView tv_ngayBL = itemView.findViewById(R.id.tv_ngayBL);
        TextView tv_ndung = itemView.findViewById(R.id.tv_ndungBL);
        ImageView imgEdit = itemView.findViewById(R.id.img_editBluan);
        ImageView imgDel = itemView.findViewById(R.id.img_delBluan);

        tv_ngayBL.setText(binhLuan.getDate());
        tv_ndung.setText(binhLuan.getNoiDung());

        Call<ArrayUser> call = serviceUser.getAllUser( );
        call.enqueue(new Callback<ArrayUser>() {
            @Override
            public void onResponse(Call<ArrayUser> call, Response<ArrayUser> response) {
                if ( response.isSuccessful()){
                    ArrayUser arrayUser = response.body();
                    listUser = Arrays.asList(arrayUser.getData());
                    for ( User u: listUser){
                        if ( u.get_id().equalsIgnoreCase(binhLuan.getIdUser()) ){
                            tv_tenUser.setText(u.getUsername()+ "( "+ u.getFullname()+" )");
                        }
                    }
                }else{
                    Log.d("zzzzz", "co loi "+response.body());
                    Toast.makeText(viewGroup.getContext(), "co loi", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayUser> call, Throwable t) {
                Toast.makeText(viewGroup.getContext(), "Lỗi: "+ t, Toast.LENGTH_SHORT).show();
                Log.d("zzzz", "Lỗi: "+ t);
            }
        });

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("USER_LOGIN", Context.MODE_PRIVATE);
                String usernameLogin = sharedPreferences.getString("Username", null);
                Log.d(TAG, "onClick: "+ usernameLogin);

                Call<ArrayUser> call = serviceUser.getAllUser( );
                call.enqueue(new Callback<ArrayUser>() {
                    @Override
                    public void onResponse(Call<ArrayUser> call, Response<ArrayUser> response) {
                        if ( response.isSuccessful()){
                            ArrayUser arrayUser = response.body();
                            listUser = Arrays.asList(arrayUser.getData());
                            for ( User u: listUser){
                                if ( u.get_id().equalsIgnoreCase(listBLuan.get(i).getIdUser())){
                                    name = u.getUsername();
                                }
                            }
                            if ( name.equalsIgnoreCase(usernameLogin) ){
                                final Dialog dialog = new Dialog(viewGroup.getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert );
                                dialog.setContentView(R.layout.layout_edit_bluan);

                                TextInputEditText ed_ndBluan = dialog.findViewById(R.id.ed_edit_noiDungBL);
                                Button btnSaveEdit = dialog.findViewById(R.id.btn_saveEditBL);
                                BinhLuan binhLuan1 = listBLuan.get(i);
                                ed_ndBluan.setText(binhLuan1.getNoiDung());

                                btnSaveEdit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (ed_ndBluan.getText().toString().trim().isEmpty() ){
                                            Toast.makeText(viewGroup.getContext(), "Vui lòng nhập nội dung", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        binhLuan1.setNoiDung(ed_ndBluan.getText().toString());
                                        Log.d(TAG, "onClick: "+binhLuan1);

                                        Call<BinhLuan> binhLuanCall  = serviceBluan.editBluanById(binhLuan1.get_id(), binhLuan1);
                                        binhLuanCall.enqueue(new Callback<BinhLuan>() {
                                            @Override
                                            public void onResponse(Call<BinhLuan> call, Response<BinhLuan> response) {
                                                if (response.isSuccessful()){
                                                    listBLuan.set(i, binhLuan1);
                                                    notifyDataSetChanged();
                                                    Toast.makeText(viewGroup.getContext(), "Sửa thành công!", Toast.LENGTH_SHORT).show();
                                                    dialog.dismiss();
                                                }
                                            }
                                            @Override
                                            public void onFailure(Call<BinhLuan> call, Throwable t) {
                                                Toast.makeText(viewGroup.getContext(), "Sửa không thành công!", Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                            }
                                        });
                                    }
                                });
                                dialog.show();

                            }else{
                                Toast.makeText(viewGroup.getContext(), "Bạn chỉ được xem!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Log.d("zzzzz", "co loi "+response.body());
                            Toast.makeText(viewGroup.getContext(), "co loi", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ArrayUser> call, Throwable t) {
                        Toast.makeText(viewGroup.getContext(), "co loi: "+ t, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Co loi: "+ t);
                    }
                });
            }
        });

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("USER_LOGIN", Context.MODE_PRIVATE);
                String usernameLogin = sharedPreferences.getString("Username", null);
                Log.d(TAG, "onClick: "+ usernameLogin);

                Call<ArrayUser> call = serviceUser.getAllUser( );
                call.enqueue(new Callback<ArrayUser>() {
                    @Override
                    public void onResponse(Call<ArrayUser> call, Response<ArrayUser> response) {
                        if ( response.isSuccessful()){
                            ArrayUser arrayUser = response.body();
                            listUser = Arrays.asList(arrayUser.getData());
                            for ( User u: listUser){
                                if ( u.get_id().equalsIgnoreCase(listBLuan.get(i).getIdUser())){
                                    name = u.getUsername();
                                }
                            }
                            if ( name.equalsIgnoreCase(usernameLogin) || usernameLogin.equalsIgnoreCase("nanh")){
                                AlertDialog.Builder builder = new AlertDialog.Builder(viewGroup.getContext());
                                builder.setTitle("Xóa bình luận?");
                                builder.setIcon(android.R.drawable.ic_delete);
                                builder.setMessage("Có chắc chắn xóa?");

                                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        listBLuan.remove(binhLuan.get_id());
                                        notifyDataSetChanged();
                                        Call<BinhLuan> call = serviceBluan.deleteBluanById(binhLuan.get_id());
                                        call.enqueue(new Callback<BinhLuan>() {
                                            @Override
                                            public void onResponse(Call<BinhLuan> call, Response<BinhLuan> response) {
                                                notifyDataSetChanged();
                                                Toast.makeText(viewGroup.getContext(),  "Xóa thành công ", Toast.LENGTH_SHORT).show();
                                                Log.d("zzzzz", "Xóa thành công, Ket qua: "+response);
                                            }
                                            @Override
                                            public void onFailure(Call<BinhLuan> call, Throwable t) {
                                                Toast.makeText(viewGroup.getContext(),  "Lỗi: "+ t, Toast.LENGTH_SHORT).show();
                                                Log.d("zzzzz", "Lỗi: "+ t);
                                            }
                                        });
                                    }
                                });

                                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }else{
                                Toast.makeText(viewGroup.getContext(), "Bạn chỉ được xem!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Log.d("zzzzz", "co loi "+response.body());
                            Toast.makeText(viewGroup.getContext(), "co loi", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayUser> call, Throwable t) {
                        Log.d("zzzz", "Lỗi: "+ t);
                    }
                });
            }
        });

        return itemView;
    }
}
