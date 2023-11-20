package tampdph33277.fpoly.du_an_mau_ph33277.DpHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDpHelper extends SQLiteOpenHelper {
    static  final String DB_NAME = "PNLIB";
    static  final int DB_VERSION = 1;
    public MyDpHelper(Context context){ super(context,DB_NAME,null,DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tạo bảng Thủ Thư
         String createTableThuThu =
                "CREATE TABLE ThuThu (\n" +
                        "    id_TT        TEXT PRIMARY KEY,\n" +
                        "    tenTT        TEXT NOT NULL,\n" +
                        "    matKhau      TEXT NOT NULL,\n" +
                        "    loaiTaiKhoan TEXT NOT NULL\n" +
                        ");";
        db.execSQL(createTableThuThu);
        String sql_insert_ThuThu = "INSERT INTO ThuThu(id_TT,tenTT,matKhau,loaiTaiKhoan)"+" values('Admin','Phùng Đức Tâm','1','Admin'),('ThuThu_Tam','Phùng Đức Tâm','1','ThuThu');";
        db.execSQL(sql_insert_ThuThu);
        //Tạo bảng Thành viên
        String createTableThanhVien =
                        "CREATE TABLE ThanhVien (\n" +
                        "    id_TV   INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        "    tenTV   TEXT    NOT NULL,\n" +
                        "    namSinh TEXT    NOT NULL\n" +
                        ");";
        db.execSQL(createTableThanhVien);
        String sql_insert_ThanhVien = "INSERT INTO ThanhVien(id_TV,tenTV,namSinh)"+" values('1','Phùng Đức Tâm','2004'),('2','Nguyễn Văn B','2004');";
        db.execSQL(sql_insert_ThanhVien);
        //Tạo bảng loại Sách
        String createTableLoaiSach =
                "CREATE TABLE LoaiSach (\n" +
                        "    id_LoaiSach INTEGER PRIMARY KEY,\n" +
                        "    tenLoaiSach TEXT    NOT NULL,\n" +
                        "    nhaCungCap  TEXT    NOT NULL\n" +
                        ");\n";
        db.execSQL(createTableLoaiSach);
        //Tạo bảng  Sách
        String createTableSach =
                "CREATE TABLE Sach (\n" +
                        "    id_Sach     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        "    tenSach     TEXT    NOT NULL,\n" +
                        "    giaThue     INTEGER NOT NULL,\n" +
                        "    id_LoaiSach INTEGER REFERENCES LoaiSach (ID_LoaiSach) \n" +

                        ");\n";
        db.execSQL(createTableSach);
        //Tạo bảng phiếu mượn
        String createTablePhieuMuon =
                " CREATE TABLE PhieuMuon (\n" +
                        "                id_PM    INTEGER PRIMARY KEY,\n" +
                        "                id_TV    INTEGER REFERENCES ThanhVien (ID_TV),\n" +
                        "                id_Sach  INTEGER REFERENCES Sach (ID_Sach),\n" +
                        "                id_TT    TEXT    REFERENCES ThuThu (ID_TT),\n" +
                        "                ngayMuon DATE    NOT NULL,\n" +
                        "                traSach  INTEGER    NOT NULL,\n" +
                        "                tienThue  INTEGER    NOT NULL,\n" +
                        "                thueVAT INTEGER NOT NULL);";
        db.execSQL(createTablePhieuMuon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableThuThu = " drop table if exists ThuThu";
        db.execSQL(dropTableThuThu);
        String dropTableThanhVien = " drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiSach = " drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach = " drop table if exists Sach";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon = " drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);
        onCreate(db);
    }
}
