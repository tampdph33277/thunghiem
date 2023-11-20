package tampdph33277.fpoly.du_an_mau_ph33277.DTO;

public class DTO_Sach {
  public   int id_Sach;
  public   String tenSach;
  public   int giaThue;
    int id_LoaiSach;

    public DTO_Sach() {
        this.id_Sach = id_Sach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.id_LoaiSach = id_LoaiSach;
    }
    public int getId_Sach() {
        return id_Sach;
    }

    public void setId_Sach(int id_Sach) {
        this.id_Sach = id_Sach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public int getId_LoaiSach() {
        return id_LoaiSach;
    }

    public void setId_LoaiSach(int id_LoaiSach) {
        this.id_LoaiSach = id_LoaiSach;
    }
}
