package tampdph33277.fpoly.du_an_mau_ph33277.DTO;

public class DTO_ThanhVien {
    public int id_TV;
    public String tenTV;
    public String namSinh;

    public DTO_ThanhVien() {
        this.id_TV = id_TV;
        this.tenTV = tenTV;
        this.namSinh = namSinh;
    }

    public int getId_TV() {
        return id_TV;
    }

    public void setId_TV(int id_TV) {
        this.id_TV = id_TV;
    }

    public String getTenTV() {
        return tenTV;
    }

    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }
}
