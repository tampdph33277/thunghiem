package tampdph33277.fpoly.du_an_mau_ph33277.FRAGMENT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import tampdph33277.fpoly.du_an_mau_ph33277.ADAPTER.ADAPTER_Sach;
import tampdph33277.fpoly.du_an_mau_ph33277.DAO.DAO_LoaiSach;
import tampdph33277.fpoly.du_an_mau_ph33277.DAO.DAO_Sach;
import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_LoaiSach;
import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_Sach;
import tampdph33277.fpoly.du_an_mau_ph33277.R;
import tampdph33277.fpoly.du_an_mau_ph33277.SPINNER.Spiner_LoaiSach;


public class Frag_Sach extends Fragment {
    FloatingActionButton fab_sach;
    RecyclerView rc_sach;
    DAO_Sach dao_sach;
    SearchView seach_sach;
    List<DTO_Sach> list_sach ;
    ADAPTER_Sach adapter_sach ;
    int giathue = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.frag_sach,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab_sach = view.findViewById(R.id.fab_sach);
        rc_sach = view.findViewById(R.id.rc_sach);
        seach_sach = view.findViewById(R.id.seach_sach);
        dao_sach = new DAO_Sach(getContext());
        list_sach = dao_sach.getAll();
        adapter_sach = new ADAPTER_Sach(getContext(),list_sach);

        LinearLayoutManager llm = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rc_sach.setLayoutManager(llm);
        rc_sach.setAdapter(adapter_sach);
        seach_sach.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
        fab_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Add_sach();
            }
        });
    }
void Add_sach(){
    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
    LayoutInflater inflater = getLayoutInflater();
    View v  = inflater.inflate(R.layout.dialog_add_sach,null);
    builder.setView(v);
    builder.setCancelable(false);
    AlertDialog dialog = builder.create();

  //  EditText ed_id_sach = v.findViewById(R.id.ed_id_sach);
    EditText ed_tensach = v.findViewById(R.id.ed_tensach);
    EditText ed_tienthue = v.findViewById(R.id.ed_tienthue);
    Button btn_huy_sach = v.findViewById(R.id.btn_huy_sach);
    Button btn_luu_sach = v.findViewById(R.id.btn_luu_sach);
    Spinner sp_loaisach = v.findViewById(R.id.sp_loaisach);

    DAO_LoaiSach dao_loaiSach = new DAO_LoaiSach(getContext());
    List<DTO_LoaiSach> list_LoaiSach = dao_loaiSach.getAll();
    Spiner_LoaiSach spiner_loaiSach = new Spiner_LoaiSach(list_LoaiSach,getContext());
    sp_loaisach.setAdapter(spiner_loaiSach);
    btn_luu_sach.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String tenSach= ed_tensach.getText().toString();
            String giathueString = ed_tienthue.getText().toString();
            try {
                giathue = Integer.parseInt(giathueString);
                if (giathue <= 0) {
                    Toast.makeText(getContext(), "Giá thuê phải là một số lớn hơn 0", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Vui lòng nhập một số hợp lệ cho giá thuê", Toast.LENGTH_SHORT).show();
                return;
            }
      //       giathue = Integer.parseInt(ed_tienthue.getText().toString());
            int sp_LoaiSach = (int) sp_loaisach.getSelectedItemId();
            if( tenSach.isEmpty()||giathueString.isEmpty()){
                Toast.makeText(getContext(), "Vui vòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            dao_sach = new DAO_Sach(getContext());
            DTO_Sach dto_sach = new DTO_Sach();
            dto_sach.setTenSach(tenSach);
            dto_sach.setGiaThue(giathue);
            dto_sach.setId_LoaiSach(sp_LoaiSach);

            long id = dao_sach.insert(dto_sach);
            if(id>0){
                Toast.makeText(getContext(), "Thêm thành công ", Toast.LENGTH_SHORT).show();
                list_sach.clear();
                list_sach.addAll(dao_sach.getAll());
                adapter_sach.notifyDataSetChanged();
                ed_tensach.setText("");
                ed_tienthue.setText("");
                dialog.dismiss();
            }else {
                Toast.makeText(getContext(), "Không thêm được", Toast.LENGTH_SHORT).show();
            }
        }
    });
    btn_huy_sach.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.dismiss();
        }
    });
    dialog.show();
}
public  void filter(String text){
        List<DTO_Sach> filteredList = new ArrayList<>();
        for (DTO_Sach sach : list_sach){
            if(String.valueOf(sach.getGiaThue()).contains(text)){
                filteredList.add(sach);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(getContext(), "Vui vòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();

        }else {
            adapter_sach.setFilterList(filteredList);
        }

}
}
