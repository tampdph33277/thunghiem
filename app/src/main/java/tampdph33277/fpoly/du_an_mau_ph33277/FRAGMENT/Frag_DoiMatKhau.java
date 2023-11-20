package tampdph33277.fpoly.du_an_mau_ph33277.FRAGMENT;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import tampdph33277.fpoly.du_an_mau_ph33277.DAO.DAO_ThuThu;
import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_ThuThu;
import tampdph33277.fpoly.du_an_mau_ph33277.R;


public class Frag_DoiMatKhau extends Fragment {
    EditText ed_PassOld,ed_PassNew,ed_RePassNew;
    Button btn_huy_dmk,btn_luu_dmk;
    DAO_ThuThu dao_thuThu;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.frag_doimatkhau,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dao_thuThu = new DAO_ThuThu(getActivity());
        ed_PassOld = view.findViewById(R.id.ed_PassOld);
        ed_PassNew = view.findViewById(R.id.ed_PassNew);
        ed_RePassNew = view.findViewById(R.id.ed_RePassNew);
        btn_huy_dmk = view.findViewById(R.id.btn_huy_dmk);
        btn_luu_dmk = view.findViewById(R.id.btn_luu_dmk);
        btn_huy_dmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_PassOld.setText("");
                    ed_PassNew.setText("");
                ed_RePassNew.setText("");
            }
        });
        btn_luu_dmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = sharedPreferences.getString("USERNAME","");
                if(validate()>0){
                     DTO_ThuThu dto_thuThu= dao_thuThu.getID(user);
                     dto_thuThu.setMatKhau(ed_PassNew.getText().toString());
                     if(dao_thuThu.update(dto_thuThu)>0){
                         Toast.makeText(getContext(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                         ed_PassOld.setText("");
                         ed_PassNew.setText("");
                         ed_RePassNew.setText("");

                     }else {
                         Toast.makeText(getContext(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();

                     }
                }
            }
        });

    }
    public  int validate(){
        int check = 1;
        if(ed_PassOld.getText().length()==0||ed_PassNew.getText().length()==0||ed_RePassNew.getText().length()==0){
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check =-1;

        }else {
                // đọc user,pass trong SharedPreferences
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld = sharedPreferences.getString("PASSWORD","");
            String passNew = ed_PassNew.getText().toString();
            String rePassNew = ed_RePassNew.getText().toString();
            if(!passOld.equals(ed_PassOld.getText().toString())){
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
         check= -1;
            }
            if(!passNew.equals(rePassNew)){
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;

            }
        }
        return check;
    }
}
