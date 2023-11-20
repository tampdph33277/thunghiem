package tampdph33277.fpoly.du_an_mau_ph33277.SPINNER;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_ThanhVien;
import tampdph33277.fpoly.du_an_mau_ph33277.R;


public class Spiner_ThanhVien extends BaseAdapter {
    List<DTO_ThanhVien> list;
    Context context;

    public Spiner_ThanhVien(List<DTO_ThanhVien> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId_TV();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        if(convertView==null){
            row=View.inflate(context,R.layout.spin_thanhvien,null);
    }else {
        row=convertView;
    }
    DTO_ThanhVien dto_thanhVien = list.get(position);
    TextView spin_thanhvien = row.findViewById(R.id.spin_thanhvien);
        spin_thanhvien.setText(dto_thanhVien.getTenTV());
        return row;
    }
}
