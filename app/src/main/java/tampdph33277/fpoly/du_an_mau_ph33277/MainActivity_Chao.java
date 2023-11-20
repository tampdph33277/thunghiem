package tampdph33277.fpoly.du_an_mau_ph33277;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_Chao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chao);
        EditText ed_msv = findViewById(R.id.ed_msv);
        Button btn_Submit = findViewById(R.id.btn_Submit);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(MainActivity_Chao.this, MainActivity_Login.class));
//         }
//        }, 1000);
btn_Submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String msv = ed_msv.getText().toString();
        if(msv.isEmpty()){
            Toast.makeText(MainActivity_Chao.this, "Vui vòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
finish();
    return;    }
        if(msv.equals("PH33277")||msv.equals("ph33277")||msv.equals("Ph33277")||msv.equals("pH33277")){
            startActivity(new Intent(MainActivity_Chao.this, MainActivity_Login.class));
            Toast.makeText(MainActivity_Chao.this, "OK", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity_Chao.this, "Ma Sinh vien khong dung", Toast.LENGTH_SHORT).show();
        }
    }
});
    }
    }
