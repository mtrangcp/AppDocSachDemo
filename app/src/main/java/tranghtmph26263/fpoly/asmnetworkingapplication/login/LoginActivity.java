package tranghtmph26263.fpoly.asmnetworkingapplication.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tranghtmph26263.fpoly.asmnetworkingapplication.MainActivity;
import tranghtmph26263.fpoly.asmnetworkingapplication.R;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.User;
import tranghtmph26263.fpoly.asmnetworkingapplication.services.ServiceUser;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText ed_username, ed_pass;
    Button btnDN, btnHuy;
    TextView tv_dangky;

    Retrofit retrofit;
    ServiceUser serviceUser;
    public static final String API = "http://192.168.1.79:3000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_username = findViewById(R.id.ed_tenDN);
        ed_pass = findViewById(R.id.ed_passDN);
        btnDN = findViewById(R.id.btn_dn);
        btnHuy = findViewById(R.id.btn_huy);
        tv_dangky = findViewById(R.id.tv_dangky);

        retrofit = new Retrofit.Builder().baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create()).build();
        serviceUser = retrofit.create(ServiceUser.class);

        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( ed_username.getText().toString().trim().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ( ed_pass.getText().toString().trim().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập pass", Toast.LENGTH_SHORT).show();
                    return;
                }

                String userName = ed_username.getText().toString();
                String pass = ed_pass.getText().toString();
                User user = new User(pass, userName);

                Call<User> call = serviceUser.checkUser(user );
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if ( response.isSuccessful()){
                            User user1 = response.body();
                            Log.d("zzzz", "onResponse: "+ user1);
                            Log.d("zzzz", "Thanh cong: 11111111111");
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);

                        }else{
                            Log.d("zzzzz", "k thanh cong: "+response.body());
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công! Vui lòng kiểm tra lại username hoặc pass!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Lỗi: "+ t, Toast.LENGTH_SHORT).show();
                        Log.d("zzzz", "Lỗi: "+ t);
                    }
                });

                Remember(userName);
            }


        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_username.setText("");
                ed_pass.setText("");
            }
        });

        tv_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(LoginActivity.this, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setContentView(R.layout.layout_add_user);

                TextInputEditText ed_username, ed_pass, ed_email, ed_fullname;
                Button btnSaveAddUser = dialog.findViewById(R.id.btn_saveAddUser);
                ed_username = dialog.findViewById(R.id.ed_add_username);
                ed_pass = dialog.findViewById(R.id.ed_add_pass);
                ed_email = dialog.findViewById(R.id.ed_add_email);
                ed_fullname = dialog.findViewById(R.id.ed_add_fullname);

                btnSaveAddUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (ed_username.getText().toString().trim().isEmpty() ){
                            Toast.makeText(LoginActivity.this, "Vui lòng nhập tiêu đề", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (ed_pass.getText().toString().trim().isEmpty() ){
                            Toast.makeText(LoginActivity.this, "Vui lòng nhập tiêu đề", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (ed_email.getText().toString().trim().isEmpty() ){
                            Toast.makeText(LoginActivity.this, "Vui lòng nhập tiêu đề", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (ed_fullname.getText().toString().trim().isEmpty() ){
                            Toast.makeText(LoginActivity.this, "Vui lòng nhập tiêu đề", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        User newUser = new User();
                        newUser.setUsername(ed_username.getText().toString());
                        newUser.setPasswork(ed_pass.getText().toString());
                        newUser.setEmail(ed_email.getText().toString());
                        newUser.setFullname(ed_fullname.getText().toString());

                        Call<User> call = serviceUser.addUser(newUser);
                        call.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                Toast.makeText(LoginActivity.this, "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Toast.makeText(LoginActivity.this, "Lỗi: "+ t, Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });


                    }
                });

                dialog.show();

            }
        });

    }

    public void Remember(String username){
        SharedPreferences sharedPreferences = getSharedPreferences("USER_LOGIN", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("Username", username);
        editor.commit();


    }
}