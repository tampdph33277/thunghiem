package tampdph33277.fpoly.du_an_mau_ph33277;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import tampdph33277.fpoly.du_an_mau_ph33277.DAO.DAO_ThuThu;


public class MainActivity_Login extends AppCompatActivity {
    TextInputEditText ed_UserName,ed_Password;
CheckBox chKRemeberPass;
Button btn_Login,btn_huy_Login;
String User;
String Pass;
DAO_ThuThu dao_thuThu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
         ed_UserName = findViewById(R.id.ed_UserName);
         ed_Password = findViewById(R.id.ed_Password);
         btn_Login = findViewById(R.id.btn_Login);
        btn_huy_Login = findViewById(R.id.btn_huy_Login);
        chKRemeberPass = findViewById(R.id.chKRemeberPass);
        dao_thuThu = new DAO_ThuThu(this);
    //    setTitle("DANG NHAP");
        // doc user , pass trong SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String user = sharedPreferences.getString("USERNAME","");
        String pass = sharedPreferences.getString("PASSWORD","");
        Boolean rem = sharedPreferences.getBoolean("REMEMBER",false);

        ed_UserName.setText(user);
        ed_Password.setText(pass);
        chKRemeberPass.setChecked(rem);
btn_huy_Login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ed_UserName.setText("");
        ed_Password.setText("");
        return;
    }
});
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
             }
        });
    }
    public void remeberUser(String u, String p, boolean status){
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
if(!status){
    // xoa du lieu
    edit.clear();
}else {
    // luu du lieu
    edit.putString("USERNAME",u);
    edit.putString("PASSWORD",p);
    edit.putBoolean("REMEMBER",status);
}
//luu lai toan bo
        edit.commit();
    }
    public  void checkLogin(){
        User = ed_UserName.getText().toString();
        Pass = ed_Password.getText().toString();
        if(User.isEmpty()||Pass.isEmpty() ){
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();

        }else {
            boolean loginSuccess = dao_thuThu.checkLogin(User, Pass);
            if (loginSuccess) {

                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
              remeberUser(User,Pass,chKRemeberPass.isChecked());
              Intent i = new Intent(getApplicationContext(),MainActivity.class);
              i.putExtra("user",User);
              startActivity(i);
              finish();
            } else {

                Toast.makeText(this, "Tên đăng nhập và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
//            if(dao_thuThu.checkLogin(User, Pass)){
//                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//              remeberUser(User,Pass,chKRemeberPass.isChecked());
//              Intent i = new Intent(getApplicationContext(),MainActivity.class);
//              i.putExtra("user",User);
//              startActivity(i);
//              finish();
//            }else {
//                Toast.makeText(this, "Tên đăng nhập và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
//            }
//        }
    }
}