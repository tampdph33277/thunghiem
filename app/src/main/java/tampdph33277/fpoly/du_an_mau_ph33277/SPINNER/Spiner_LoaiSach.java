package tampdph33277.fpoly.du_an_mau_ph33277.SPINNER;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_LoaiSach;
import tampdph33277.fpoly.du_an_mau_ph33277.R;


public class Spiner_LoaiSach extends BaseAdapter {
    List<DTO_LoaiSach> list;
    Context context;

    public Spiner_LoaiSach(List<DTO_LoaiSach> list, Context context) {
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
        return list.get(position).getId_LoaiSach();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        if(convertView==null){      row=View.inflate(context, R.layout.spin_loaisach,null);
    }else {
        row=convertView;
    }
    DTO_LoaiSach loaisach_dto = list.get(position);
    TextView spin_loai = row.findViewById(R.id.spin_loaisach);
        spin_loai.setText(loaisach_dto.getTenLoaiSach());
        return row;
    }
}
