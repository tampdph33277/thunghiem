package tampdph33277.fpoly.du_an_mau_ph33277.FRAGMENT;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import tampdph33277.fpoly.du_an_mau_ph33277.ADAPTER.ADAPTER_ThuThu;
import tampdph33277.fpoly.du_an_mau_ph33277.DAO.DAO_ThuThu;
import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_ThuThu;
import tampdph33277.fpoly.du_an_mau_ph33277.R;

public class Frag_ThuThu extends Fragment {
    FloatingActionButton fab_tt;
    RecyclerView rc_tt;
    DAO_ThuThu dao_thuThu;
    ADAPTER_ThuThu adapter_thuThu;
    List<DTO_ThuThu> list_dto_thuthu;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_thuthu,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab_tt = view.findViewById(R.id.fab_tt);
        dao_thuThu = new DAO_ThuThu(getContext());
        list_dto_thuthu = dao_thuThu.getAll();
        adapter_thuThu = new ADAPTER_ThuThu(getContext(),list_dto_thuthu);
        rc_tt = view.findViewById(R.id.rc_tt);
        LinearLayoutManager llm = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rc_tt.setLayoutManager(llm);
        rc_tt.setAdapter(adapter_thuThu);
        fab_tt
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_TT();
            }
        });
    }
    void Add_TT(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View v  = inflater.inflate(R.layout.dialog_add_thuthu,null);
        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        EditText ed_id_tt = v.findViewById(R.id.ed_id_TT);
        EditText ed_ten_tt = v.findViewById(R.id.ed_ten_tt);
        EditText ed_matkhau_tt = v.findViewById(R.id.ed_matkhau_tt);
        Button btn_huy_tt = v.findViewById(R.id.btn_huy_tt);
        Button btn_luu_tt = v.findViewById(R.id.btn_luu_tt);
        EditText ed_loaiTK = v.findViewById(R.id.ed_loaiTK);
        ed_loaiTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] quyen = {"Admin","ThuThu"};
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setTitle("Chọn quyền tài khoản");
                builder1.setItems(quyen, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ed_loaiTK.setText(quyen[which]);
                    }
                });
                AlertDialog dialog1 = builder1.create();
                dialog1.show();
            }
        });

        btn_luu_tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_TT = ed_id_tt.getText().toString();
                String tenTT = ed_ten_tt.getText().toString();
                String matKhau = ed_matkhau_tt.getText().toString();
                String loaiTK = ed_loaiTK.getText().toString();

                if(id_TT.isEmpty()|| tenTT.isEmpty()||matKhau.isEmpty()||loaiTK.isEmpty()){
                    Toast.makeText(getContext(), "Vui vòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
             return;   }
                if (loaiTK.equals("Admin")||loaiTK.equals("ThuThu")) {
                    DTO_ThuThu dto_thuThu = new DTO_ThuThu();
                    dto_thuThu.setId_TT(id_TT);
                    dto_thuThu.setTenTT(tenTT);
                    dto_thuThu.setMatKhau(matKhau);
                    dto_thuThu.setLoaiTaiKhoan(loaiTK);
                    long id = dao_thuThu.insert(dto_thuThu);
                    if (id > 0) {
                        list_dto_thuthu.clear();
                        list_dto_thuthu.addAll(dao_thuThu.getAll());
                        adapter_thuThu.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        ed_id_tt.setText("");
                        ed_ten_tt.setText("");
                        ed_matkhau_tt.setText("");
                        ed_loaiTK.setText("");
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Không thêm được", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }else {
                    Toast.makeText(getContext(), "Vui lòng click vào để chọn quyền", Toast.LENGTH_SHORT).show();
return;
                }
            }
        });
        btn_huy_tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
