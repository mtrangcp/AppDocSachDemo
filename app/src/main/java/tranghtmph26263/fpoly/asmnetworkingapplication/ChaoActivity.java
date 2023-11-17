package tranghtmph26263.fpoly.asmnetworkingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import tranghtmph26263.fpoly.asmnetworkingapplication.login.LoginActivity;

public class ChaoActivity extends AppCompatActivity {
    ImageView img_chao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chao);

        img_chao = findViewById(R.id.img_logoChao);

        Glide.with(this).load(R.mipmap.hoctap).into(img_chao);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ChaoActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        }, 3000);



    }
}