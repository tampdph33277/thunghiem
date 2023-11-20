package tampdph33277.fpoly.du_an_mau_ph33277.FRAGMENT;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import tampdph33277.fpoly.du_an_mau_ph33277.DAO.DAO_PhieuMuon;
import tampdph33277.fpoly.du_an_mau_ph33277.R;


public class Frag_DoanhThu extends Fragment {
    Button btn_doanhthu,btn_ngaybd,btn_ngaykt;
    EditText ed_ngaybd,ed_ngaykt;
    TextView tv_doanhthu;
    SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd");
    int mDay,mMonth,mYear;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.frag_doanhthu,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_doanhthu = view.findViewById(R.id.btn_doanhthu);
        btn_ngaybd = view.findViewById(R.id.btn_ngaybd);
        btn_ngaykt = view.findViewById(R.id.btn_ngaykt);
        ed_ngaybd = view.findViewById(R.id.ed_ngaybd);
        ed_ngaykt = view.findViewById(R.id.ed_ngaykt);
        tv_doanhthu = view.findViewById(R.id.tv_doanhthu);
        btn_ngaybd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear= c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
DatePickerDialog d = new DatePickerDialog(getActivity(),
        0,mDate_bd,mYear,mMonth,mDay);
        d.show();

            }
        });
      btn_ngaykt.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Calendar c = Calendar.getInstance();
              mYear= c.get(Calendar.YEAR);
              mMonth = c.get(Calendar.MONTH);
              mDay = c.get(Calendar.DAY_OF_MONTH);
              DatePickerDialog d = new DatePickerDialog(getActivity(),
                      0,mDate_kt,mYear,mMonth,mDay);
              d.show();
          }
      });
        btn_doanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String batdau = ed_ngaybd.getText().toString();
                String ketthuc = ed_ngaykt.getText().toString();
                DAO_PhieuMuon dao_phieuMuon = new DAO_PhieuMuon(getContext());
                tv_doanhthu.setText("Doanh Thu: "+dao_phieuMuon.getDoanhThu(batdau,ketthuc)+" VND");

            }
        });
    }
   DatePickerDialog.OnDateSetListener mDate_bd = new DatePickerDialog.OnDateSetListener() {
       @Override
       public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

           mYear= year;
           mMonth = month;
           mDay = dayOfMonth;
           GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
           ed_ngaybd.setText(sdf.format(c.getTime()));
       }
   };
    DatePickerDialog.OnDateSetListener mDate_kt = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear= year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
            ed_ngaykt.setText(sdf.format(c.getTime()));
        }
    };
}
