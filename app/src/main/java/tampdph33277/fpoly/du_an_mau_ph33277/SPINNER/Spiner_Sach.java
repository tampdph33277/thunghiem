package tampdph33277.fpoly.du_an_mau_ph33277.SPINNER;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_Sach;
import tampdph33277.fpoly.du_an_mau_ph33277.R;


public class Spiner_Sach extends BaseAdapter {
    List<DTO_Sach> list;
    Context context;

    public Spiner_Sach(List<DTO_Sach> list, Context context) {
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
        return list.get(position).getId_Sach();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        if(convertView==null){
            row=View.inflate(context, R.layout.spin_sach,null);
    }else {
        row=convertView;
    }
    DTO_Sach dto_sach = list.get(position);
    TextView spin_sach = row.findViewById(R.id.spin_Sach);
        spin_sach.setText(dto_sach.getTenSach());
        return row;
    }
}
