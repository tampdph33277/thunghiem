package tampdph33277.fpoly.du_an_mau_ph33277.FRAGMENT;

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

import tampdph33277.fpoly.du_an_mau_ph33277.ADAPTER.ADAPTER_ThanhVien;
import tampdph33277.fpoly.du_an_mau_ph33277.DAO.DAO_ThanhVien;
import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_ThanhVien;
import tampdph33277.fpoly.du_an_mau_ph33277.R;


public class Frag_ThanhVien extends Fragment {
    RecyclerView rc_tv;
    DAO_ThanhVien dao_thanhVien;
    ADAPTER_ThanhVien adapter_thanhVien;
    List<DTO_ThanhVien> list_dto_thanhVien;
    FloatingActionButton fab_tv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_thanhvien,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab_tv = view.findViewById(R.id.fab_tv);
        dao_thanhVien = new DAO_ThanhVien(getContext());

        list_dto_thanhVien = dao_thanhVien.getAll();
        adapter_thanhVien = new ADAPTER_ThanhVien(getContext(),list_dto_thanhVien);
        rc_tv = view.findViewById(R.id.rc_tv);
        LinearLayoutManager llm = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rc_tv.setLayoutManager(llm);
        rc_tv.setAdapter(adapter_thanhVien);
        fab_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_TV();
            }
        });
    }
    void Add_TV(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View v  = inflater.inflate(R.layout.dialog_add_thanhvien,null);
        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
       // EditText ed_id_tv = v.findViewById(R.id.ed_id_tv);
        EditText ed_ten_tv = v.findViewById(R.id.ed_ten_tv);
        EditText ed_namsinh_tv = v.findViewById(R.id.ed_namsinh_tv);
        Button btn_huy_tv = v.findViewById(R.id.btn_huy_tv);
        Button btn_luu_tv = v.findViewById(R.id.btn_luu_tv);


        btn_luu_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tenTV = ed_ten_tv.getText().toString();
                String namSinh = ed_namsinh_tv.getText().toString();
                 dao_thanhVien = new DAO_ThanhVien(getContext());
                if(tenTV.isEmpty()||namSinh.isEmpty()){
                    Toast.makeText(getContext(), "Vui vòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;   }
                DTO_ThanhVien dto_thanhVien = new DTO_ThanhVien();
                dto_thanhVien.setTenTV(tenTV);
                dto_thanhVien.setNamSinh(namSinh);
                long id = dao_thanhVien.insert(dto_thanhVien);
                if(id>0){
                    list_dto_thanhVien.clear();
                    list_dto_thanhVien.addAll(dao_thanhVien.getAll());
                    adapter_thanhVien.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    ed_ten_tv.setText("");
                    ed_ten_tv.setText("");
                    dialog.dismiss();
                }else{
                    Toast.makeText(getContext(), "Không thêm được", Toast.LENGTH_SHORT).show();
               dialog.dismiss();
                }
            }
        });
        btn_huy_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
