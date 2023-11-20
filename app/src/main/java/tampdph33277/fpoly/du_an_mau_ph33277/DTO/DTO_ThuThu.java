package tampdph33277.fpoly.du_an_mau_ph33277.DTO;

public class DTO_ThuThu {
   public String id_TT;
    public String tenTT;
    public String matKhau;
    public String loaiTaiKhoan ;

    public DTO_ThuThu() {
    }

    public DTO_ThuThu(String id_TT, String tenTT, String matKhau, String loaiTaiKhoan) {
        this.id_TT = id_TT;
        this.tenTT = tenTT;
        this.matKhau = matKhau;
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public String getId_TT() {
        return id_TT;
    }

    public void setId_TT(String id_TT) {
        this.id_TT = id_TT;
    }

    public String getTenTT() {
        return tenTT;
    }

    public void setTenTT(String tenTT) {
        this.tenTT = tenTT;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(String loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }
}
