package tampdph33277.fpoly.du_an_mau_ph33277;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import tampdph33277.fpoly.du_an_mau_ph33277.DAO.DAO_ThuThu;
import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_ThuThu;
import tampdph33277.fpoly.du_an_mau_ph33277.DpHelper.MyDpHelper;
import tampdph33277.fpoly.du_an_mau_ph33277.FRAGMENT.Frag_DoanhThu;
import tampdph33277.fpoly.du_an_mau_ph33277.FRAGMENT.Frag_DoiMatKhau;
import tampdph33277.fpoly.du_an_mau_ph33277.FRAGMENT.Frag_LoaiSach;
import tampdph33277.fpoly.du_an_mau_ph33277.FRAGMENT.Frag_Sach;
import tampdph33277.fpoly.du_an_mau_ph33277.FRAGMENT.Frag_ThanhVien;
import tampdph33277.fpoly.du_an_mau_ph33277.FRAGMENT.Frag_ThuThu;
import tampdph33277.fpoly.du_an_mau_ph33277.FRAGMENT.Frag_TopSach;
import tampdph33277.fpoly.du_an_mau_ph33277.FRAGMENT.Frag_phieumuon;

public class MainActivity extends AppCompatActivity {
    DrawerLayout layout_chinh_drawer001;
    Toolbar mToobar;
    NavigationView main_navigation_view001;
    FragmentManager fm;
    View mHeaderview ;
    TextView tv_user_nav1;
    DAO_ThuThu dao_thuThu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this , "Chào mừng đến với trang chủ", Toast.LENGTH_SHORT).show();
        MyDpHelper myDpHelper = new MyDpHelper(getApplicationContext());
        layout_chinh_drawer001 = findViewById(R.id.layout_chinh_drawer001);
        mToobar = findViewById(R.id.mToobar);
        main_navigation_view001 = findViewById(R.id.main_navigation_view001);
        mHeaderview = main_navigation_view001.getHeaderView(0);
        tv_user_nav1 = mHeaderview.findViewById(R.id.tv_user_nav);
        Intent i = getIntent();
        String id_TT = i.getStringExtra("user");

        dao_thuThu = new DAO_ThuThu(this);
        DTO_ThuThu dto_thuThu = dao_thuThu.getID(id_TT);
        String name = dto_thuThu.getTenTT();
        tv_user_nav1.setText("Welcom "+name+"!");
        String loaiTaiKhoan = dto_thuThu.getLoaiTaiKhoan();
        if (loaiTaiKhoan.equals("Admin")) {
            // Hiển thị mục "Doanh thu", "Tốp 10" và "Thủ thư" cho tài khoản admin
            Menu navMenu = main_navigation_view001.getMenu();
            MenuItem doanhThuMenuItem = navMenu.findItem(R.id.nav_DoanhThu);
            MenuItem top10MenuItem = navMenu.findItem(R.id.nav_Top);
            MenuItem thuThuMenuItem = navMenu.findItem(R.id.nav_ThuThu);
            doanhThuMenuItem.setVisible(true);
            top10MenuItem.setVisible(true);
            thuThuMenuItem.setVisible(true);
        } else if (loaiTaiKhoan.equals("ThuThu")) {
            Menu navMenu = main_navigation_view001.getMenu();
//            MenuItem doanhThuMenuItem = navMenu.findItem(R.id.nav_DoanhThu);
//            MenuItem top10MenuItem = navMenu.findItem(R.id.nav_Top);
            MenuItem thuThuMenuItem = navMenu.findItem(R.id.nav_ThuThu);
//            doanhThuMenuItem.setVisible(false);
//            top10MenuItem.setVisible(false);
            thuThuMenuItem.setVisible(false);
        }
        fm = getSupportFragmentManager();
        Frag_phieumuon frag_phieumuon = new Frag_phieumuon();
        Frag_Sach frag_sach = new Frag_Sach();
        Frag_LoaiSach frag_loaiSach = new Frag_LoaiSach();
        Frag_DoiMatKhau frag_doiMatKhau = new Frag_DoiMatKhau();
        Frag_ThanhVien frag_thanhVien = new Frag_ThanhVien();
        Frag_ThuThu frag_thuThu = new Frag_ThuThu();
        Frag_TopSach frag_topSach = new Frag_TopSach();
        Frag_DoanhThu frag_doanhThu = new Frag_DoanhThu();

        setSupportActionBar(mToobar);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, layout_chinh_drawer001, mToobar, R.string.open, R.string.close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        layout_chinh_drawer001.addDrawerListener(drawerToggle);
        fm.beginTransaction().replace(R.id.frag_container001, frag_phieumuon).commit();

        main_navigation_view001.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_PhieuMuon) {
                    fm.beginTransaction().replace(R.id.frag_container001, frag_phieumuon).commit();
                } else if (item.getItemId() == R.id.nav_LoaiSach) {
                    fm.beginTransaction().replace(R.id.frag_container001, frag_loaiSach).commit();
                } else if (item.getItemId() == R.id.nav_Sach) {
                    fm.beginTransaction().replace(R.id.frag_container001, frag_sach).commit();
                } else if (item.getItemId() == R.id.nav_ThanhVien) {
                    fm.beginTransaction().replace(R.id.frag_container001, frag_thanhVien).commit();
                } else if (item.getItemId() == R.id.nav_ThuThu) {
                    fm.beginTransaction().replace(R.id.frag_container001, frag_thuThu).commit();
                } else if (item.getItemId() == R.id.nav_Top) {
                    fm.beginTransaction().replace(R.id.frag_container001, frag_topSach).commit();
                } else if (item.getItemId() == R.id.nav_DoanhThu) {
                    fm.beginTransaction().replace(R.id.frag_container001, frag_doanhThu).commit();
                } else if (item.getItemId() == R.id.nav_DMK) {
                    fm.beginTransaction().replace(R.id.frag_container001, frag_doiMatKhau).commit();
                } else if (item.getItemId() == R.id.nav_Logout) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Thông báo");
                  //  tamp8785@
                    // builder.setMessage("Bạn có muốn đăng xuất tài khoảnnnnn không ?");
                    builder.setMessage("Bạn có muốn đăng xuất tài khoản không ?");
// builder.setMessage("Bạn có muốn đăng xuất tài khoản không ?");
                    // builder.setMessage("Bạn có muốn đăng xuất tài khoản không ?"); builder.setMessage("Bạn có muốn đăng xuất tài khoản không ?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(MainActivity.this, MainActivity_Login.class));
                        }
                    });
                    builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                getSupportActionBar().setTitle(item.getTitle());

                return false;
            }
        });
    }
    }
