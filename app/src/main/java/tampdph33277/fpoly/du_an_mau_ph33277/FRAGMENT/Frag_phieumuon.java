package tampdph33277.fpoly.du_an_mau_ph33277.FRAGMENT;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import tampdph33277.fpoly.du_an_mau_ph33277.ADAPTER.ADAPTER_PhieuMuon;
import tampdph33277.fpoly.du_an_mau_ph33277.DAO.DAO_PhieuMuon;
import tampdph33277.fpoly.du_an_mau_ph33277.DAO.DAO_Sach;
import tampdph33277.fpoly.du_an_mau_ph33277.DAO.DAO_ThanhVien;
import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_PhieuMuon;
import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_Sach;
import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_ThanhVien;
import tampdph33277.fpoly.du_an_mau_ph33277.R;
import tampdph33277.fpoly.du_an_mau_ph33277.SPINNER.Spiner_Sach;
import tampdph33277.fpoly.du_an_mau_ph33277.SPINNER.Spiner_ThanhVien;


public class Frag_phieumuon extends Fragment {
    FloatingActionButton fab_phieumuon;
    List<DTO_PhieuMuon> list_PhieuMuon;
    DAO_PhieuMuon dao_phieuMuon;
    RecyclerView rc_pm;
    ADAPTER_PhieuMuon adapter_phieuMuon;
    int tien_Thue;
    int thueVAT = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_phieumuon,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab_phieumuon = view.findViewById(R.id.fab_phieumuon);
        rc_pm = view.findViewById(R.id.rc_pm);
        dao_phieuMuon = new DAO_PhieuMuon(getContext());
        list_PhieuMuon = dao_phieuMuon.getAll();
        adapter_phieuMuon = new ADAPTER_PhieuMuon(getContext(),list_PhieuMuon);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,false);
        rc_pm.setLayoutManager(linearLayoutManager);
        rc_pm.setAdapter(adapter_phieuMuon);
        fab_phieumuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
ADD_PhieuMuon();
            }
        });
    }
    void ADD_PhieuMuon(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View v  = inflater.inflate(R.layout.dialog_add_phieumuon,null);
        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();

        Spinner sp_TV = v.findViewById(R.id.sp_TV);
        TextView tv_add_ngayMuon = v.findViewById(R.id.tv_add_ngayMuon);
        TextView tv_gia_thue = v.findViewById(R.id.tv_gia_thue);
        EditText ed_thueVAT = v.findViewById(R.id.ed_thueVAT);
        Spinner sp_Sach = v.findViewById(R.id.sp_Sach);
        CheckBox chk_ngaytra = v.findViewById(R.id.chk_ngaytra);
        Button btn_luu_phieumuon = v.findViewById(R.id.btn_luu_phieumuon);
        Button btn_huy_phieumuon = v.findViewById(R.id.btn_huy_phieumuon);
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        tv_add_ngayMuon.setText("Ngày Thuê: "+sdf.format(new Date()));
        DAO_ThanhVien dao_thanhVien = new DAO_ThanhVien(getContext());
        List<DTO_ThanhVien> list_Dto_thanhVien = dao_thanhVien.getAll();
        Spiner_ThanhVien spiner_thanhVien = new Spiner_ThanhVien(list_Dto_thanhVien,getContext());
        sp_TV.setAdapter(spiner_thanhVien);

        DAO_Sach dao_sach = new DAO_Sach(getContext());
        List<DTO_Sach> list_Dto_sach = dao_sach.getAll();
        Spiner_Sach spiner_sach = new Spiner_Sach(list_Dto_sach,getContext());
        sp_Sach.setAdapter(spiner_sach);

        btn_luu_phieumuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tentv = (int) sp_TV.getSelectedItemId();
                int tensach = (int) sp_Sach.getSelectedItemId();
                String thueVATString =  ed_thueVAT.getText().toString();
                if(thueVATString.isEmpty()){
                    Toast.makeText(getContext(), "Vui vòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    thueVAT = Integer.parseInt(thueVATString);
                    if (thueVAT <= 0) {
                        Toast.makeText(getContext(), "Thuế VAT phải là một số lớn hơn 0", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }catch (NumberFormatException e){
                    Toast.makeText(getContext(), " Vui lòng nhập một số hợp lệ cho thuế VAT", Toast.LENGTH_SHORT).show();
                    return;
                }
                dao_phieuMuon = new DAO_PhieuMuon(getContext());
                DTO_PhieuMuon dto_phieuMuon = new DTO_PhieuMuon();
                dto_phieuMuon.setId_TV(tentv);
                dto_phieuMuon.setId_Sach(tensach);
              dto_phieuMuon.setNgayMuon(new Date());
                dto_phieuMuon.setTienThue(tien_Thue);
                dto_phieuMuon.setThueVAT(thueVAT);
              if(chk_ngaytra.isChecked()){
                  dto_phieuMuon.setTraSach(1);

              }else {
                  dto_phieuMuon.setTraSach(0);
              }
                long id = dao_phieuMuon.inser(dto_phieuMuon);
                if(id>0){
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    list_PhieuMuon.clear();
                    list_PhieuMuon.addAll(dao_phieuMuon.getAll());
                    adapter_phieuMuon.notifyDataSetChanged();
                    dialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "không thêm được", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        btn_huy_phieumuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        sp_TV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Chọn "+list_Dto_thanhVien.get(position).getTenTV(), Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
            sp_Sach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  tien_Thue= list_Dto_sach.get(position).getGiaThue();
                    tv_gia_thue.setText("Tiền Thuê "+tien_Thue);
                    Toast.makeText(getContext(), "Chọn "+list_Dto_sach.get(position).getTenSach(), Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

dialog.show();
    }

}
