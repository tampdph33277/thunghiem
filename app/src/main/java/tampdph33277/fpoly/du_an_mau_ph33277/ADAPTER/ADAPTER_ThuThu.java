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

import tampdph33277.fpoly.du_an_mau_ph33277.DAO.DAO_ThuThu;
import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_ThuThu;
import tampdph33277.fpoly.du_an_mau_ph33277.R;


public class ADAPTER_ThuThu extends RecyclerView.Adapter<ADAPTER_ThuThu.Viewholder>{
    Context context;
    List<DTO_ThuThu> list_ThuThu;
      DAO_ThuThu dao_thuThu;

    public ADAPTER_ThuThu(Context context, List<DTO_ThuThu> list_ThuThu) {
        this.context = context;
        this.list_ThuThu = list_ThuThu;
    }




    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.row_thuthu, parent,false);
        Viewholder objViewHolder = new Viewholder(v);
        return objViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        DTO_ThuThu dto_thuThu = list_ThuThu.get(position);
          holder.tv_id_tt.setText("Mã Thủ Thư:  "+list_ThuThu.get(position).getId_TT()+"");
          holder.tv_tentt.setText("Tên Thủ Thư: "+list_ThuThu.get(position).getTenTT()+"");
        holder.tv_matKhau.setText("Mật Khẩu:       "+list_ThuThu.get(position).getMatKhau()+"");
         holder.tv_loaiTK.setText("Tài Khoản:      "+list_ThuThu.get(position).getLoaiTaiKhoan()+"");
  holder.img_xoa_tt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
delete_TT(dto_thuThu);
      }
  });
  holder.img_edit_tt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
update_TT(dto_thuThu);
      }
  });
    }


    @Override
    public int getItemCount() {
        return list_ThuThu.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView tv_id_tt, tv_tentt, tv_matKhau, tv_loaiTK;
        ImageView img_xoa_tt, img_edit_tt;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tv_id_tt = itemView.findViewById(R.id.tv_id_tt);
            tv_tentt = itemView.findViewById(R.id.tv_tentt);
            tv_matKhau = itemView.findViewById(R.id.tv_matKhau);
            tv_loaiTK = itemView.findViewById(R.id.tv_loaiTK);
            img_xoa_tt = itemView.findViewById(R.id.img_xoa_tt);
            img_edit_tt = itemView.findViewById(R.id.img_edit_tt);


        }
    }
    public void update_TT(DTO_ThuThu dto_thuThu){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_edit_thuthu,null);
        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
  //      EditText ed_edit_id_TT = v.findViewById(R.id.ed_edit_id_TT);
        EditText ed_edit_ten_tt = v.findViewById(R.id.ed_edit_ten_tt);
        EditText ed_edit_matkhau_tt = v.findViewById(R.id.ed_edit_matkhau_tt);
        EditText ed_edit_loaiTK = v.findViewById(R.id.ed_edit_loaiTK);

        Button btn_edit_huy_tt = v.findViewById(R.id.btn_edit_huy_tt);
        Button  btn_edit_luu_tt = v.findViewById(R.id.btn_edit_luu_tt);

    //    ed_edit_id_TT.setText(dto_thuThu.getId_TT());
        ed_edit_ten_tt.setText(dto_thuThu.getTenTT());
        ed_edit_matkhau_tt.setText(dto_thuThu.getMatKhau());
        ed_edit_loaiTK.setText(dto_thuThu.getLoaiTaiKhoan());
        ed_edit_loaiTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] quyen = {"Admin","ThuThu"};
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setTitle("Chọn quyền tài khoản");
                builder1.setItems(quyen, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ed_edit_loaiTK.setText(quyen[which]);
                    }
                });
             AlertDialog dialog1 = builder1.create();
             dialog1.show();
            }
        });
        btn_edit_huy_tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_edit_luu_tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String id_tt = ed_edit_id_TT.getText().toString();
                String tentt = ed_edit_ten_tt.getText().toString();
                String matKhau = ed_edit_matkhau_tt.getText().toString();
                String loaiTK = ed_edit_loaiTK.getText().toString();
                if( tentt.isEmpty()||matKhau.isEmpty()||loaiTK.isEmpty()){
                    Toast.makeText(context, "Vui vòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (loaiTK.equals("Admin")||loaiTK.equals("ThuThu")) {
                    dao_thuThu = new DAO_ThuThu(context);
                    //     dto_thuThu.setId_TT(id_tt);
                    dto_thuThu.setTenTT(tentt);
                    dto_thuThu.setMatKhau(matKhau);
                    dto_thuThu.setLoaiTaiKhoan(loaiTK);
                    int id = dao_thuThu.update(dto_thuThu);
                    if(id>0){
                        list_ThuThu.clear();
                        list_ThuThu.addAll(dao_thuThu.getAll());
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                        //     ed_edit_id_TT.setText("");
                        ed_edit_ten_tt.setText("");
                        ed_edit_matkhau_tt.setText("");
                        ed_edit_loaiTK.setText("");
                        dialog.dismiss();
                    }else{
                        Toast.makeText(context, "Không sửa được", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }else {
                    Toast.makeText(context, "Vui lòng click vào ô để chọn quyền", Toast.LENGTH_SHORT).show();
              return;  }



            }
        });
        dialog.show();
    }
    public void delete_TT(DTO_ThuThu dto_thuThu){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa không ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Dồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao_thuThu = new DAO_ThuThu(context);
                int id = dao_thuThu.delete(dto_thuThu);
                if (id>0){
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    list_ThuThu.clear();
                    list_ThuThu.addAll(dao_thuThu.getAll());
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
}

