package tampdph33277.fpoly.du_an_mau_ph33277.DTO;

import java.util.Date;

public class DTO_PhieuMuon {
    int id_PM;
    String id_TT;
    int id_TV;
    int id_Sach;
    Date ngayMuon;
    int traSach;
    int tienThue;
    int thueVAT;
    public DTO_PhieuMuon() {
    }

    public DTO_PhieuMuon(int id_PM, String id_TT, int id_TV, int id_Sach, Date ngayMuon, int traSach, int tienThue, int thueVAT) {
        this.id_PM = id_PM;
        this.id_TT = id_TT;
        this.id_TV = id_TV;
        this.id_Sach = id_Sach;
        this.ngayMuon = ngayMuon;
        this.traSach = traSach;
        this.tienThue = tienThue;
        this.thueVAT = thueVAT;
    }

    public int getThueVAT() {
        return thueVAT;
    }

    public void setThueVAT(int thueVAT) {
        this.thueVAT = thueVAT;
    }

    public int getId_PM() {
        return id_PM;
    }

    public void setId_PM(int id_PM) {
        this.id_PM = id_PM;
    }

    public String getId_TT() {
        return id_TT;
    }

    public void setId_TT(String id_TT) {
        this.id_TT = id_TT;
    }

    public int getId_TV() {
        return id_TV;
    }

    public void setId_TV(int id_TV) {
        this.id_TV = id_TV;
    }

    public int getId_Sach() {
        return id_Sach;
    }

    public void setId_Sach(int id_Sach) {
        this.id_Sach = id_Sach;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public int getTraSach() {
        return traSach;
    }

    public void setTraSach(int traSach) {
        this.traSach = traSach;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }
}
