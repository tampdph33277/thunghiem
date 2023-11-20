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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tampdph33277.fpoly.du_an_mau_ph33277.DAO.DAO_LoaiSach;
import tampdph33277.fpoly.du_an_mau_ph33277.DAO.DAO_Sach;
import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_LoaiSach;
import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_Sach;
import tampdph33277.fpoly.du_an_mau_ph33277.R;
import tampdph33277.fpoly.du_an_mau_ph33277.SPINNER.Spiner_LoaiSach;



public class ADAPTER_Sach  extends RecyclerView.Adapter<ADAPTER_Sach.ViewHolder> {
    Context context;
    List<DTO_Sach> list_sach;
    DAO_Sach dao_sach;

    int giathue_edit = 0;
    public ADAPTER_Sach(Context context, List<DTO_Sach> list_sach) {
        this.context = context;
        this.list_sach = list_sach;

    }
public void setFilterList (List<DTO_Sach> filterList){
        this.list_sach= filterList;
        notifyDataSetChanged();
}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.row_sach,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         DTO_Sach dto_Sach = list_sach.get(position);
         holder.tv_id_sach.setText("Mã Sách:            "+dto_Sach.getId_Sach()+"");
         holder.tv_tensach.setText("Tên Sách:           "+dto_Sach.getTenSach()+"");
        holder.tv_tienthue.setText("Tiền Thuê:         "+dto_Sach.getGiaThue()+" VND");

        DAO_LoaiSach dao_loaiSach = new DAO_LoaiSach(context);
        DTO_LoaiSach dto_loaiSach = dao_loaiSach.getID(String.valueOf(dto_Sach.getId_LoaiSach()));
     holder.tv_id_loaisach.setText("Tên Loại Sách:  "+dto_loaiSach.getTenLoaiSach()+"");
        holder.img_xoa_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_sach(dto_Sach);
            }
        });
        holder.img_edit_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
update_sach(dto_Sach);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_sach.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id_sach,tv_tensach,tv_tienthue,tv_id_loaisach;
        ImageView img_xoa_sach,img_edit_sach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           tv_id_sach = itemView.findViewById(R.id.tv_id_sach);
            tv_tensach = itemView.findViewById(R.id.tv_tensach);
            tv_tienthue = itemView.findViewById(R.id.tv_tienthue);
            tv_id_loaisach = itemView.findViewById(R.id.tv_id_loaisach);
            img_xoa_sach = itemView.findViewById(R.id.img_xoa_sach);
            img_edit_sach = itemView.findViewById(R.id.img_edit_sach);

        }

    }
    public void update_sach(DTO_Sach dto_sach){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_edit_sach,null);
        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        EditText ed_edit_tensach = v.findViewById(R.id.ed_edit_tensach);
        EditText ed_edit_tienthue = v.findViewById(R.id.ed_edit_tienthue);
        Spinner sp_edit_loaisach = v.findViewById(R.id.sp_edit_loaisach);
        Button btn_edit_huy_sach = v.findViewById(R.id.btn_edit_huy_sach);
        Button btn_edit_luu_sach = v.findViewById(R.id.btn_edit_luu_sach);

        ed_edit_tensach.setText(dto_sach.getTenSach());
        ed_edit_tienthue.setText(dto_sach.getGiaThue()+"");
        DAO_LoaiSach dao_loaiSach = new DAO_LoaiSach(context);
        List<DTO_LoaiSach >list_Dto_loaiSach = dao_loaiSach.getAll();
        Spiner_LoaiSach spiner_loaiSach = new Spiner_LoaiSach(list_Dto_loaiSach,context);
        sp_edit_loaisach.setAdapter(spiner_loaiSach);
        for (int i = 0;i<list_Dto_loaiSach.size();i++){
            if(list_Dto_loaiSach.get(i).getId_LoaiSach() == dto_sach.getId_LoaiSach()){
                sp_edit_loaisach.setSelection(i);
            }
        }
        btn_edit_luu_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenSach = ed_edit_tensach.getText().toString();
             //   int giathue_edit = Integer.parseInt(ed_edit_tienthue.getText().toString());
                String giathueString = ed_edit_tienthue.getText().toString();
                int sp_edit_LoaiSach = (int) sp_edit_loaisach.getSelectedItemId();
                try {
                    giathue_edit = Integer.parseInt(giathueString);
                    if (giathue_edit <= 0) {
                        Toast.makeText(context, "Giá thuê phải là một số lớn hơn 0", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(context, "Vui lòng nhập một số hợp lệ cho giá thuê", Toast.LENGTH_SHORT).show();
                    return;
                }
                if( tenSach.isEmpty()||giathueString.isEmpty()){
                    Toast.makeText(context, "Vui vòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                dao_sach = new DAO_Sach(context);
                dto_sach.setTenSach(tenSach);
                dto_sach.setGiaThue(giathue_edit);
                dto_sach.setId_LoaiSach(sp_edit_LoaiSach);
                int id = dao_sach.update(dto_sach);
                if(id>0){
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    list_sach.clear();
                    list_sach.addAll(dao_sach.getAll());
                    notifyDataSetChanged();
                    ed_edit_tensach.setText("");
                    ed_edit_tienthue.setText("");
                    dialog.dismiss();
                }else {
                    Toast.makeText(context, "Không sửa được", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        btn_edit_huy_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void delete_sach(DTO_Sach dto_sach){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setTitle("Thông Báo");
        builder.setMessage("Bạn có muốn xóa không ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao_sach = new DAO_Sach(context);
                int id = dao_sach.delete(dto_sach);
                if (id > 0) {
                    Toast.makeText(context, "Xóa Thành công", Toast.LENGTH_SHORT).show();
                    list_sach.clear();
                    list_sach.addAll(dao_sach.getAll());
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
}
