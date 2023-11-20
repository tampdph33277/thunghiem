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

import tampdph33277.fpoly.du_an_mau_ph33277.DAO.DAO_LoaiSach;
import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_LoaiSach;
import tampdph33277.fpoly.du_an_mau_ph33277.R;


public class ADAPTER_LoaiSach extends RecyclerView.Adapter<ADAPTER_LoaiSach.Viewholder>{
    Context context;

    List<DTO_LoaiSach> list_LoaiSach;
    DAO_LoaiSach dao_loaiSach;

    public ADAPTER_LoaiSach(Context context, List<DTO_LoaiSach> list_LoaiSach) {
        this.context = context;
        this.list_LoaiSach = list_LoaiSach;

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.row_loaisach,parent,false);
        Viewholder viewHolder = new Viewholder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        DTO_LoaiSach dto_loaiSach = list_LoaiSach.get(position);
        holder.tv_id_loaisach.setText("Mã Loại Sách: "+dto_loaiSach.getId_LoaiSach()+"");
        holder.tv_ten_loaisach.setText("Tên Loại Sách: "+dto_loaiSach.getTenLoaiSach());
        holder.tv_nhaCC.setText("Nhà Cung Cấp: "+dto_loaiSach.getNhaCungCap());

        holder.img_xoa_LoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_LS(dto_loaiSach);
            }
        });
        holder.img_edit_LoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
update_LS(dto_loaiSach);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_LoaiSach.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView tv_id_loaisach, tv_ten_loaisach,tv_nhaCC;
        ImageView img_edit_LoaiSach, img_xoa_LoaiSach;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tv_id_loaisach = itemView.findViewById(R.id.tv_id_loaisach);
            tv_ten_loaisach = itemView.findViewById(R.id.tv_ten_loaisach);
            tv_nhaCC = itemView.findViewById(R.id.tv_nhaCC);
            img_edit_LoaiSach = itemView.findViewById(R.id.img_edit_LoaiSach);
            img_xoa_LoaiSach = itemView.findViewById(R.id.img_xoa_LoaiSach);

        }
    }
        void delete_LS(DTO_LoaiSach dto_loaiSach) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setIcon(android.R.drawable.ic_delete);
            builder.setTitle("Thông Báo");
            builder.setMessage("Bạn có muốn xóa không ?");
            builder.setCancelable(false);
            builder.setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dao_loaiSach = new DAO_LoaiSach(context);
                    int id = dao_loaiSach.delete(dto_loaiSach);
                    if (id > 0) {
                        Toast.makeText(context, "Xóa Thành công", Toast.LENGTH_SHORT).show();
                        list_LoaiSach.clear();
                        list_LoaiSach.addAll(dao_loaiSach.getAll());
                        notifyDataSetChanged();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "không xóa được", Toast.LENGTH_SHORT).show();
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

    void update_LS(DTO_LoaiSach dto_loaiSach){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_edit_loaisach,null);
        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        EditText ed_edit_nhaCC = v.findViewById(R.id.ed_edit_nhaCC);
        EditText ed_edit_ten_loaisach = v.findViewById(R.id.ed_edit_ten_loaisach);
        Button btn_edit_luu_loaisach = v.findViewById(R.id.btn_edit_luu_loaisach);
        Button btn_edit_huy_loaisach = v.findViewById(R.id.btn_edit_huy_loaisach);

        ed_edit_ten_loaisach.setText(dto_loaiSach.getTenLoaiSach());
        ed_edit_nhaCC.setText(dto_loaiSach.getNhaCungCap());
        btn_edit_luu_loaisach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenLoaiSach = ed_edit_ten_loaisach.getText().toString();
                String nhacungcap = ed_edit_nhaCC.getText().toString();
                if(tenLoaiSach.isEmpty()){
                    Toast.makeText(context, "Vui vòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;   }
                dao_loaiSach = new DAO_LoaiSach(context);
                dto_loaiSach.setTenLoaiSach(tenLoaiSach);
                dto_loaiSach.setNhaCungCap(nhacungcap);
                if(tenLoaiSach.isEmpty()){
                    Toast.makeText(context, "Vui vòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;   }
                int id = dao_loaiSach.update(dto_loaiSach);
                if(id>0){
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    list_LoaiSach.clear();
                    list_LoaiSach.addAll(dao_loaiSach.getAll());
                    notifyDataSetChanged();
                    ed_edit_ten_loaisach.setText("");
                    dialog.dismiss();
                }else {
                    Toast.makeText(context, "Không sửa được", Toast.LENGTH_SHORT).show();
                     dialog.dismiss();
                }
            }
        });
        btn_edit_huy_loaisach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
