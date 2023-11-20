package tampdph33277.fpoly.du_an_mau_ph33277.ADAPTER;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tampdph33277.fpoly.du_an_mau_ph33277.DAO.DAO_ThanhVien;
import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_ThanhVien;
import tampdph33277.fpoly.du_an_mau_ph33277.R;


public class ADAPTER_ThanhVien extends RecyclerView.Adapter<ADAPTER_ThanhVien.Viewholder> {
    Context context;
    List<DTO_ThanhVien> list_ThanhVien;
    DAO_ThanhVien dao_thanhVien;

    public ADAPTER_ThanhVien(Context context, List<DTO_ThanhVien> list_ThanhVien) {
        this.context = context;
        this.list_ThanhVien = list_ThanhVien;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.row_thanhvien,parent,false);
        Viewholder objViewHolder = new Viewholder(v);
        return objViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        DTO_ThanhVien dto_thanhVien = list_ThanhVien.get(position);
        holder.tv_id_TV.setText("Mã Thành Viên:   "+list_ThanhVien.get(position).getId_TV()+"");
        holder.tv_ten_TV.setText("Tên Thành Viên: "+list_ThanhVien.get(position).getTenTV()+"");
        holder.tv_NamSinh_TV.setText("Năm Sinh:         "+list_ThanhVien.get(position).getNamSinh()+"");
        holder.img_xoa_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
delete_TV(dto_thanhVien);
            }
        });
        holder.img_edit_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
update_TV(dto_thanhVien);
            }
        });
    }

    @Override
    public int getItemCount() {

        return list_ThanhVien.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView tv_id_TV,tv_ten_TV,tv_NamSinh_TV;
        ImageView img_edit_TV,img_xoa_TV;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tv_id_TV = itemView.findViewById(R.id.tv_id_TV);
            tv_ten_TV = itemView.findViewById(R.id.tv_ten_TV);
            tv_NamSinh_TV = itemView.findViewById(R.id.tv_NamSinh_TV);
            img_edit_TV = itemView.findViewById(R.id.img_edit_TV);
            img_xoa_TV = itemView.findViewById(R.id.img_xoa_TV);
        }
    }
    void delete_TV(DTO_ThanhVien dto_thanhVien){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa không ?");
        builder.setCancelable(false);

        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao_thanhVien = new DAO_ThanhVien(context);
                int id = dao_thanhVien.delete(dto_thanhVien);
                if (id>0){
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    list_ThanhVien.clear();
                    list_ThanhVien.addAll(dao_thanhVien.getAll());
                    notifyDataSetChanged();
                    dialog.dismiss();
                }else {
                    Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
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
    void update_TV( DTO_ThanhVien dto_thanhVien){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_edit_thanhvien,null);
        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        EditText ed_edit_ten_tv = v.findViewById(R.id.ed_edit_ten_tv);
        EditText ed_edit_namsinh_tv = v.findViewById(R.id.ed_edit_namsinh_tv);
     Button  btn_edit_huy_tv = v.findViewById(R.id.btn_edit_huy_tv);
     Button  btn_edit_luu_tv = v.findViewById(R.id.btn_edit_luu_tv);
     ed_edit_ten_tv.setText(dto_thanhVien.getTenTV());
        ed_edit_namsinh_tv.setText(dto_thanhVien.getNamSinh());
        btn_edit_luu_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTV = ed_edit_ten_tv.getText().toString();
                String namSinh = ed_edit_namsinh_tv.getText().toString();
                if(tenTV.isEmpty()||namSinh.isEmpty()){
                    Toast.makeText(context, "Vui vòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;   }
                dao_thanhVien = new DAO_ThanhVien(context);

                dto_thanhVien.setTenTV(tenTV);
                dto_thanhVien.setNamSinh(namSinh);
                int id = dao_thanhVien.update(dto_thanhVien);
                if(id>0){
                    list_ThanhVien.clear();
                    list_ThanhVien.addAll(dao_thanhVien.getAll());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    ed_edit_ten_tv.setText("");
                    ed_edit_namsinh_tv.setText("");
                    dialog.dismiss();
                }else{
                    Toast.makeText(context, "Không thêm được", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        btn_edit_huy_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
dialog.show();
    }
}
