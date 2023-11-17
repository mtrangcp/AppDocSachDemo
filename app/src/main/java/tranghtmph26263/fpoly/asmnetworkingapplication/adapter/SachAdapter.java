package tranghtmph26263.fpoly.asmnetworkingapplication.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tranghtmph26263.fpoly.asmnetworkingapplication.R;
import tranghtmph26263.fpoly.asmnetworkingapplication.SachActivity;
import tranghtmph26263.fpoly.asmnetworkingapplication.TacgiaActivity;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.ArrayTacgia;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.Sach;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.TacGia;
import tranghtmph26263.fpoly.asmnetworkingapplication.services.ServiceSach;
import tranghtmph26263.fpoly.asmnetworkingapplication.services.ServiceTacgia;

public class SachAdapter extends BaseAdapter {
    List<Sach> listSach;
    List<TacGia> listTacGia = new ArrayList<TacGia>();
    Context context;

    Retrofit retrofit;
    ServiceTacgia serviceTacgia;
    ServiceSach serviceSach;
    public static final String API = "http://192.168.1.79:3000/";

    public SachAdapter(Context context, List<Sach> listSach) {
        this.listSach = listSach;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listSach.size();
    }

    @Override
    public Object getItem(int i) {
        Sach obj = listSach.get(i);
        return obj;
    }

    @Override
    public long getItemId(int i) {
        Sach obj = listSach.get(i);
        return obj.get__v();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        String tenTG;
        View itemView;
        if ( view == null){
            itemView = View.inflate(viewGroup.getContext(), R.layout.item_sach, null);
         }else {
            itemView = view;
        }

        retrofit = new Retrofit.Builder().baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create()).build();
        serviceTacgia = retrofit.create(ServiceTacgia.class);
        serviceSach = retrofit.create(ServiceSach.class);

        Sach objSach = listSach.get(i);
        ImageView imgAnhBia = itemView.findViewById(R.id.img_anhBia);
        TextView tv_tenSach = itemView.findViewById(R.id.tv_tenSach);
        TextView tv_namSX = itemView.findViewById(R.id.tv_namSX);
        TextView tv_tacGia = itemView.findViewById(R.id.tv_tacgia);
        TextView tv_slBluan = itemView.findViewById(R.id.tv_slBluan);
        ImageView imgEdit = itemView.findViewById(R.id.img_editSach);
        ImageView imgDel = itemView.findViewById(R.id.img_delSach);

        Call<ArrayTacgia> call = serviceTacgia.getAllTacgia();
        call.enqueue(new Callback<ArrayTacgia>() {
            @Override
            public void onResponse(Call<ArrayTacgia> call, Response<ArrayTacgia> response) {
                ArrayTacgia tacgia = response.body();
                if ( tacgia == null){
                    Toast.makeText(viewGroup.getContext(), "Danh sách rỗng!", Toast.LENGTH_SHORT).show();
                }else {
                    listTacGia = Arrays.asList(tacgia.getData());
                    for ( TacGia tg: listTacGia ){
                        if ( tg.get_id().equalsIgnoreCase(objSach.getTacGia()) ){
                            tv_tacGia.setText("Tác giả: "+tg.getTen());
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayTacgia> call, Throwable t) {
                Toast.makeText(viewGroup.getContext(), "co loi: "+ t, Toast.LENGTH_SHORT).show();
                Log.d("zzzzz", "co loi: "+ t);
            }
        });

        tv_tenSach.setText(objSach.getTenSach());
        tv_namSX.setText("Năm sx: "+objSach.getNamsx());
        tv_slBluan.setText("Số lượng bình luận: "+objSach.getBinhLuan().size());
        Glide.with(viewGroup.getContext()).load(objSach.getAnhBia()).into(imgAnhBia);

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("USER_LOGIN", MODE_PRIVATE);
                String usernameLogin = sharedPreferences.getString("Username", null);
                Log.d("zzzz", "onClick: "+ usernameLogin);

                if (usernameLogin.equalsIgnoreCase("nanh")){
                    final Dialog dialog = new Dialog(viewGroup.getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert );
                    dialog.setContentView(R.layout.layout_edit_sach);

                    TextInputEditText ed_tenSach, ed_mota, ed_idTacGia, ed_namSx, ed_linkAnh;
                    Button btnSaveEdit = dialog.findViewById(R.id.btn_saveEditSach);
                    ed_tenSach = dialog.findViewById(R.id.ed_edit_tenSach);
                    ed_mota = dialog.findViewById(R.id.ed_edit_mota);
                    ed_idTacGia = dialog.findViewById(R.id.ed_edit_idtacGia);
                    ed_namSx = dialog.findViewById(R.id.ed_edit_namSX);
                    ed_linkAnh = dialog.findViewById(R.id.ed_edit_linkAnh);

                    Sach sach = listSach.get(i);
                    ed_tenSach.setText(sach.getTenSach());
                    ed_mota.setText(sach.getMoTa());
                    ed_idTacGia.setText(sach.getTacGia());
                    ed_namSx.setText(sach.getNamsx() +"");
                    ed_linkAnh.setText(sach.getAnhBia());

                    btnSaveEdit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (ed_tenSach.getText().toString().trim().isEmpty() ){
                                Toast.makeText(viewGroup.getContext(), "Vui lòng nhập ten sach", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (ed_mota.getText().toString().trim().isEmpty() ){
                                Toast.makeText(viewGroup.getContext(), "Vui lòng nhập mo ta", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (ed_idTacGia.getText().toString().trim().isEmpty() ){
                                Toast.makeText(viewGroup.getContext(), "Vui lòng nhập id tac gia", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (ed_namSx.getText().toString().trim().isEmpty() ){
                                Toast.makeText(viewGroup.getContext(), "Vui lòng nhập nam suat ban", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (ed_linkAnh.getText().toString().trim().isEmpty() ){
                                Toast.makeText(viewGroup.getContext(), "Vui lòng nhập link anh", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            sach.setTenSach(ed_tenSach.getText().toString());
                            sach.setMoTa(ed_mota.getText().toString());
                            sach.setTacGia(ed_idTacGia.getText().toString());
                            sach.setNamsx(Integer.parseInt(ed_namSx.getText().toString()));
                            sach.setAnhBia(ed_linkAnh.getText().toString());

                            listSach.set(i, sach);
                            notifyDataSetChanged();

                            Call<Sach> call = serviceSach.editSachById( sach.get_id(),sach);
                            call.enqueue(new Callback<Sach>() {
                                @Override
                                public void onResponse(Call<Sach> call, Response<Sach> response) {
                                    Toast.makeText(viewGroup.getContext(),  "Sửa thành công ", Toast.LENGTH_SHORT).show();
                                    Log.d("zzzzz", "Ket qua: "+response);
                                    dialog.dismiss();
                                }

                                @Override
                                public void onFailure(Call<Sach> call, Throwable t) {
                                    Toast.makeText(viewGroup.getContext(),  "Lỗi: "+ t, Toast.LENGTH_SHORT).show();
                                    Log.d("zzzzz", "Lỗi: "+t);
                                    dialog.dismiss();
                                }
                            });
                        }
                    });
                    dialog.show();

                }else{
                    Toast.makeText(viewGroup.getContext(), "Chỉ admin mới có thể dùng chức năng này!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("USER_LOGIN", MODE_PRIVATE);
                String usernameLogin = sharedPreferences.getString("Username", null);
                Log.d("zzzz", "onClick: "+ usernameLogin);

                if (usernameLogin.equalsIgnoreCase("nanh")){
                    String idSach = listSach.get(i).get_id();

                    AlertDialog.Builder builder = new AlertDialog.Builder(viewGroup.getContext());
                    builder.setTitle("Xóa Sách?");
                    builder.setIcon(android.R.drawable.ic_delete);
                    builder.setMessage("Có chắc chắn xóa?" );

                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Call<Sach> call1 = serviceSach.deleteSachById(idSach);
                            call1.enqueue(new Callback<Sach>() {
                                @Override
                                public void onResponse(Call<Sach> call, Response<Sach> response) {
                                    notifyDataSetChanged();
                                    Toast.makeText(viewGroup.getContext(), " Xoa thanh cong", Toast.LENGTH_SHORT).show();
                                    dialogInterface.dismiss();
                                }
                                @Override
                                public void onFailure(Call<Sach> call, Throwable t) {
                                    Toast.makeText(viewGroup.getContext(), " Xoa khong thanh cong", Toast.LENGTH_SHORT).show();
                                    dialogInterface.dismiss();
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
                    Toast.makeText(viewGroup.getContext(), "Chỉ admin mới có thể dùng chức năng này!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return itemView;
    }
}
