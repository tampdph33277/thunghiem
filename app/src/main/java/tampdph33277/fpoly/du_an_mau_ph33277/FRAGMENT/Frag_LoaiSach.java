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

import java.util.ArrayList;
import java.util.List;

import tampdph33277.fpoly.du_an_mau_ph33277.ADAPTER.ADAPTER_LoaiSach;
import tampdph33277.fpoly.du_an_mau_ph33277.DAO.DAO_LoaiSach;
import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_LoaiSach;
import tampdph33277.fpoly.du_an_mau_ph33277.R;


public class Frag_LoaiSach extends Fragment {
    FloatingActionButton fab_loaisach;
    private EditText edSearchNhaCC;
    RecyclerView rc_loaisach;
    private Button btnSearch;
    DAO_LoaiSach dao_loaiSach;
    List<DTO_LoaiSach> list_LoaiSach ;
    ADAPTER_LoaiSach adapter_loaiSach ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_loaisach,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab_loaisach = view.findViewById(R.id.fab_loaisach);
        btnSearch = view.findViewById(R.id.btn_search);
        edSearchNhaCC = view.findViewById(R.id.ed_search_nhaCC);
        dao_loaiSach = new DAO_LoaiSach(getContext());

        list_LoaiSach = dao_loaiSach.getAll();
        adapter_loaiSach = new ADAPTER_LoaiSach(getContext(),list_LoaiSach);
        rc_loaisach = view.findViewById(R.id.rc_loaisach);
        LinearLayoutManager llm = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rc_loaisach.setLayoutManager(llm);
        rc_loaisach.setAdapter(adapter_loaiSach);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = edSearchNhaCC.getText().toString().trim();
                list_LoaiSach.clear();
                for (DTO_LoaiSach dto_loaiSach : dao_loaiSach.getAll()) {
                    if (dto_loaiSach.getNhaCungCap().toLowerCase().contains(searchQuery.toLowerCase())) {
                        list_LoaiSach.add(dto_loaiSach);
                    }
                }
                adapter_loaiSach.notifyDataSetChanged();
            }
        });
        fab_loaisach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Add_loaisach();
            }
        });
    }
    void Add_loaisach(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View v  = inflater.inflate(R.layout.dialog_add_loaisach,null);
        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
    //    EditText ed_id_loaisach = v.findViewById(R.id.ed_id_loaisach);
        EditText ed_ten_loaisach = v.findViewById(R.id.ed_ten_loaisach);
        EditText ed_nhaCC = v.findViewById(R.id.ed_nhaCC);
        Button btn_luu_loaisach = v.findViewById(R.id.btn_luu_loaisach);
        Button btn_huy_loaisach = v.findViewById(R.id.btn_huy_loaisach);

        btn_luu_loaisach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloaisach = ed_ten_loaisach.getText().toString();
                String nhacungcap = ed_nhaCC.getText().toString();

                if(tenloaisach.isEmpty()){
                    Toast.makeText(getContext(), "Vui vòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;   }

                DTO_LoaiSach dto_loaiSach = new DTO_LoaiSach();
                dto_loaiSach.setTenLoaiSach(tenloaisach);
                dto_loaiSach.setNhaCungCap(nhacungcap);
                long kq = dao_loaiSach.insert(dto_loaiSach);
                if(kq>0){
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    list_LoaiSach.clear();
                    list_LoaiSach.addAll(dao_loaiSach.getAll());
                    adapter_loaiSach.notifyDataSetChanged();
                    ed_ten_loaisach.setText("");
                    dialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "Không thêm được", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }

            }
        });
        btn_huy_loaisach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
