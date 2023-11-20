package tampdph33277.fpoly.du_an_mau_ph33277.ADAPTER;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import tampdph33277.fpoly.du_an_mau_ph33277.DAO.DAO_PhieuMuon;
import tampdph33277.fpoly.du_an_mau_ph33277.DAO.DAO_Sach;
import tampdph33277.fpoly.du_an_mau_ph33277.DAO.DAO_ThanhVien;
import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_PhieuMuon;
import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_Sach;
import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_ThanhVien;
import tampdph33277.fpoly.du_an_mau_ph33277.R;
import tampdph33277.fpoly.du_an_mau_ph33277.SPINNER.Spiner_Sach;
import tampdph33277.fpoly.du_an_mau_ph33277.SPINNER.Spiner_ThanhVien;


public class ADAPTER_PhieuMuon extends RecyclerView.Adapter<ADAPTER_PhieuMuon.ViewHolder> {
    Context context;
    List<DTO_PhieuMuon> list_phieuMuon;
    DAO_PhieuMuon dao_phieuMuon;
 public    int tienthue_edit;
    public    int thueVAT_edit=0;
  public   SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    public ADAPTER_PhieuMuon(Context context, List<DTO_PhieuMuon> list_phieuMuon) {
        this.context = context;
        this.list_phieuMuon = list_phieuMuon;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.row_pm,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
DTO_PhieuMuon dto_phieuMuon = list_phieuMuon.get(position);
holder.tv_id_pm.setText("Mã Phiếu Mượn:  "+dto_phieuMuon.getId_PM());
        DAO_Sach dao_sach = new DAO_Sach(context);
        DTO_Sach dto_sach = dao_sach.getID(String.valueOf(dto_phieuMuon.getId_Sach()));
        holder.tv_tensach.setText("Tên Sách:              "+dto_sach.getTenSach());
        DAO_ThanhVien dao_thanhVien = new DAO_ThanhVien(context);
        DTO_ThanhVien dto_thanhVien = dao_thanhVien.getID(String.valueOf(dto_phieuMuon.getId_TV()));
        holder.tv_tenTV.setText("Tên Thành Viên:   "+dto_thanhVien.getTenTV()+"");
        holder.tv_gia_thue.setText("Giá Thuê:               "+dto_phieuMuon.getTienThue()+" VND");
        holder.tv_thueVAT.setText("Giá Thuế VAT:               "+dto_phieuMuon.getThueVAT()+" VND");

        holder.tv_ngayMuon.setText("Ngày Thuê:            "+sdf.format(dto_phieuMuon.getNgayMuon()));
        holder.tv_ngayTra.setText(dto_phieuMuon.getTraSach()+"");
        if(dto_phieuMuon.getTraSach()==1){
            holder.tv_ngayTra.setText("Đã trả sách");
            holder.tv_ngayTra.setTextColor(Color.BLUE);

        }else {
            holder.tv_ngayTra.setTextColor(Color.RED);
            holder.tv_ngayTra.setText("Chưa trả sách");

        }
        holder.img_edit_pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_PhieuMuon(dto_phieuMuon);
            }
        });
holder.img_xoa_pm.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        delete_phieumuon(dto_phieuMuon);
    }
});
    }
    @Override
    public int getItemCount() {
        return list_phieuMuon.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id_pm,tv_tenTV,tv_tensach,tv_gia_thue,tv_ngayMuon,tv_ngayTra,tv_thueVAT;
        ImageView img_edit_pm,img_xoa_pm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id_pm = itemView.findViewById(R.id.tv_id_pm);
            tv_tenTV = itemView.findViewById(R.id.tv_tenTV);
            tv_tensach = itemView.findViewById(R.id.tv_tensach);
            tv_gia_thue = itemView.findViewById(R.id.tv_gia_thue);
            tv_thueVAT = itemView.findViewById(R.id.tv_thueVAT);
            tv_ngayMuon = itemView.findViewById(R.id.tv_ngayMuon);
            tv_ngayTra = itemView.findViewById(R.id.tv_ngayTra);
          //  tv_status = itemView.findViewById(R.id.tv_status);
            img_edit_pm = itemView.findViewById(R.id.img_edit_pm);
            img_xoa_pm = itemView.findViewById(R.id.img_xoa_pm);
        }
    }

    void delete_phieumuon(DTO_PhieuMuon dto_phieuMuon){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setIcon(android.R.drawable.ic_delete);
    builder.setTitle("Thông báo");
    builder.setMessage("Bạn có muốn xóa không?");
    builder.setCancelable(false);
    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dao_phieuMuon = new DAO_PhieuMuon(context);
            int id = dao_phieuMuon.delete(dto_phieuMuon);
            if (id > 0) {
                Toast.makeText(context, "Xóa Thành công", Toast.LENGTH_SHORT).show();
                list_phieuMuon.clear();
                list_phieuMuon.addAll(dao_phieuMuon.getAll());
                notifyDataSetChanged();
                dialog.dismiss();
            } else {
                Toast.makeText(context, "Không xóa được", Toast.LENGTH_SHORT).show();
            }
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
 public  void update_PhieuMuon(DTO_PhieuMuon dto_phieuMuon){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_edit_phieumuon,null);
        builder.setView(v);
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();

        Spinner sp_edit_TV = v.findViewById(R.id.sp_edit_TV);
        TextView tv_edit_ngayMuon = v.findViewById(R.id.tv_edit_ngayMuon);
        TextView tv_edit_gia_thue = v.findViewById(R.id.tv_edit_gia_thue);
     EditText ed_edit_thueVAT = v.findViewById(R.id.ed_edit_thueVAT);
        Spinner sp_edit_Sach = v.findViewById(R.id.sp_edit_Sach);
        CheckBox chk_edit_ngaytra = v.findViewById(R.id.chk_edit_ngaytra);
        Button btn_edit_luu_phieumuon = v.findViewById(R.id.btn_edit_luu_phieumuon);
        Button btn_edit_huy_phieumuon = v.findViewById(R.id.btn_edit_huy_phieumuon);

     if(dto_phieuMuon.getTraSach()==1){
         chk_edit_ngaytra.setChecked(true);
     }else {
         chk_edit_ngaytra.setChecked(false);
     }
     tv_edit_ngayMuon.setText("Ngày Thuê: "+sdf.format(dto_phieuMuon.getNgayMuon()));


     DAO_ThanhVien dao_thanhVien = new DAO_ThanhVien(context);
        List<DTO_ThanhVien> list_Dto_thanhVien = dao_thanhVien.getAll();
        Spiner_ThanhVien spiner_thanhVien = new Spiner_ThanhVien(list_Dto_thanhVien,context);
     sp_edit_TV.setAdapter(spiner_thanhVien);
        for (int i = 0;i<list_Dto_thanhVien.size();i++){
            if(list_Dto_thanhVien.get(i).getId_TV() == dto_phieuMuon.getId_TV()){
                sp_edit_TV.setSelection(i);
            }
        }

        DAO_Sach dao_sach = new DAO_Sach(context);
        List<DTO_Sach> list_Dto_sach = dao_sach.getAll();
        Spiner_Sach spiner_sach = new Spiner_Sach(list_Dto_sach,context);
     sp_edit_Sach.setAdapter(spiner_sach);
     for (int i = 0;i<list_Dto_sach.size();i++){
         if(list_Dto_sach.get(i).getId_Sach() == dto_phieuMuon.getId_Sach()){
             sp_edit_Sach.setSelection(i);
         }
     }
     ed_edit_thueVAT.setText(dto_phieuMuon.getThueVAT()+"");
     sp_edit_Sach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             tienthue_edit= list_Dto_sach.get(position).getGiaThue();
             tv_edit_gia_thue.setText("Tiền Thuê: "+tienthue_edit);
         //    Toast.makeText(context, "Chọn "+list_Dto_sach.get(position).getTenSach(), Toast.LENGTH_SHORT).show();
         }
         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
     });


        btn_edit_luu_phieumuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_edit_ngayMuon.setText("Ngày Thuê: "+sdf.format(dto_phieuMuon.getNgayMuon()));
                tv_edit_gia_thue.setText("Tiền thuê: "+dto_phieuMuon.getTienThue());
                int tentv = (int) sp_edit_TV.getSelectedItemId();
                int tensach = (int) sp_edit_Sach.getSelectedItemId();
                String thueVATString =  ed_edit_thueVAT.getText().toString();
                if(thueVATString.isEmpty()){
                    Toast.makeText(context, "Vui vòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    thueVAT_edit = Integer.parseInt(thueVATString);
                    if (thueVAT_edit <= 0) {
                        Toast.makeText(context, "Thuế VAT phải là một số lớn hơn 0", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }catch (NumberFormatException e){
                    Toast.makeText(context, " Vui lòng nhập một số hợp lệ cho thuế VAT", Toast.LENGTH_SHORT).show();
                }
                dao_phieuMuon = new DAO_PhieuMuon(context);
            //    tv_edit_ngayMuon.setText("Ngày Thuê: "+sdf.format(new Date()));
                dto_phieuMuon.setId_Sach(tensach);
                dto_phieuMuon.setId_TV(tentv);
       //         dto_phieuMuon.setNgayMuon(new Date());
               dto_phieuMuon.setTienThue(tienthue_edit);
                if(chk_edit_ngaytra.isChecked()){
                    dto_phieuMuon.setTraSach(1);
                }else {
                    dto_phieuMuon.setTraSach(0);
                }
                dto_phieuMuon.setThueVAT(thueVAT_edit);
                long id = dao_phieuMuon.update(dto_phieuMuon);
                if(id>0){
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    list_phieuMuon.clear();
                    list_phieuMuon.addAll(dao_phieuMuon.getAll());
                    notifyDataSetChanged();
                    dialog.dismiss();
                }else {
                    Toast.makeText(context, "Không sửa được", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        btn_edit_huy_phieumuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
dialog.show();
    }
}
