package thuc.dv.thucdvph17759_asg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    Button btnDangKi, btnDangNhap, btnFB, btnGG, btnApple;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnApple = findViewById(R.id.buttonloginApple);
        btnFB = findViewById(R.id.buttonloginFb);
        btnGG = findViewById(R.id.buttonloginGG);
        btnDangKi = findViewById(R.id.buttonDangKi);
        btnDangNhap = findViewById(R.id.buttonDangNhap);

        btnApple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainAppActivity.class);
                startActivity(intent);
            }
        });

        btnFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainAppActivity.class);
                startActivity(intent);
            }
        });

        btnGG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainAppActivity.class);
                startActivity(intent);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainAppActivity.class);
                startActivity(intent);
            }
        });

        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"Đăng Kí Thành Công !!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}