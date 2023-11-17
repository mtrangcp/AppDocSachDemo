package tranghtmph26263.fpoly.asmnetworkingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tranghtmph26263.fpoly.asmnetworkingapplication.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    TextView tv_listsach, tv_listTG;
    ImageView imgLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_listsach = findViewById(R.id.tv_listSach);
        tv_listTG = findViewById(R.id.tv_listTacGia);
        imgLogout = findViewById(R.id.img_logout);

        tv_listsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, SachActivity.class);
                startActivity(intent1);
            }
        });
        tv_listTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, TacgiaActivity.class);
                startActivity(intent2);
            }
        });

        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

}