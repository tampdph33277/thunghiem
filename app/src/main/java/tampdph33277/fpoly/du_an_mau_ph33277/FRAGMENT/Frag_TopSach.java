package tampdph33277.fpoly.du_an_mau_ph33277.FRAGMENT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tampdph33277.fpoly.du_an_mau_ph33277.ADAPTER.ADAPTER_TopSach;
import tampdph33277.fpoly.du_an_mau_ph33277.DAO.DAO_PhieuMuon;
import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_TopSach;
import tampdph33277.fpoly.du_an_mau_ph33277.R;

public class Frag_TopSach extends Fragment {

    List<DTO_TopSach> list_Dto_topSach;
    RecyclerView rc_topSach;
    DAO_PhieuMuon dao_phieuMuon;
    ADAPTER_TopSach adapter_topSach;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_topsach,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dao_phieuMuon = new DAO_PhieuMuon(getContext());
        list_Dto_topSach = dao_phieuMuon.getTopSach();
        adapter_topSach = new ADAPTER_TopSach(getContext(),list_Dto_topSach);
        rc_topSach = view.findViewById(R.id.rc_topSach);
        LinearLayoutManager llm = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rc_topSach.setLayoutManager(llm);
        rc_topSach.setAdapter(adapter_topSach);
    }

}
