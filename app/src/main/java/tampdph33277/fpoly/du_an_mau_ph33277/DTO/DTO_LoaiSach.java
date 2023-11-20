package tampdph33277.fpoly.du_an_mau_ph33277.DTO;

public class DTO_LoaiSach {
    public int id_LoaiSach;
    public String tenLoaiSach;
    public String nhaCungCap;

    public DTO_LoaiSach() {
    }

    public DTO_LoaiSach(int id_LoaiSach, String tenLoaiSach, String nhaCungCap) {
        this.id_LoaiSach = id_LoaiSach;
        this.tenLoaiSach = tenLoaiSach;
        this.nhaCungCap = nhaCungCap;
    }

    public int getId_LoaiSach() {
        return id_LoaiSach;
    }

    public void setId_LoaiSach(int id_LoaiSach) {
        this.id_LoaiSach = id_LoaiSach;
    }

    public String getTenLoaiSach() {
        return tenLoaiSach;
    }

    public void setTenLoaiSach(String tenLoaiSach) {
        this.tenLoaiSach = tenLoaiSach;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }
}

