package tampdph33277.fpoly.du_an_mau_ph33277.ADAPTER;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_TopSach;
import tampdph33277.fpoly.du_an_mau_ph33277.R;

public class ADAPTER_TopSach extends RecyclerView.Adapter<ADAPTER_TopSach.Viewholder>{
    Context context;
    List<DTO_TopSach> list_topSach ;

    public ADAPTER_TopSach(Context context, List<DTO_TopSach> list_topSach) {
        this.context = context;
        this.list_topSach = list_topSach;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.row_top10,parent,false);
        Viewholder objViewHolder = new Viewholder(v);
        return objViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.tv_tenSach_top.setText("Tên Sách: "+list_topSach.get(position).getTenSach()+"");
        holder.tv_soLuong_sach.setText("Số Lượng: "+list_topSach.get(position).getSoLuong()+"");
    }

    @Override
    public int getItemCount() {
        return list_topSach.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView tv_tenSach_top,tv_soLuong_sach;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tv_tenSach_top = itemView.findViewById(R.id.tv_tenSach_top);
            tv_soLuong_sach = itemView.findViewById(R.id.tv_soLuong_sach);
        }
    }
}
