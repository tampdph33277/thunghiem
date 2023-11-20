package tampdph33277.fpoly.du_an_mau_ph33277.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_ThuThu;
import tampdph33277.fpoly.du_an_mau_ph33277.DpHelper.MyDpHelper;


public class DAO_ThuThu {
    MyDpHelper myDpHelper;
    private SQLiteDatabase db ;
    public DAO_ThuThu (Context context){
        MyDpHelper myDpHelper  = new MyDpHelper(context);
        db = myDpHelper.getWritableDatabase();
    }
    public long insert (DTO_ThuThu objtt){
        ContentValues values = new ContentValues();
        values.put("id_TT", objtt.getId_TT());
        values.put("tenTT", objtt.getTenTT());
        values.put("matKhau", objtt.getMatKhau());
        values.put("loaiTaiKhoan", objtt.getLoaiTaiKhoan());
        return db.insert("ThuThu",null,values);
    }
    public int update (DTO_ThuThu objtt){
        ContentValues values = new ContentValues();
      //  values.put("id_TT", objtt.getId_TT());
        values.put("tenTT", objtt.getTenTT());
        values.put("matKhau", objtt.getMatKhau());
        values.put("loaiTaiKhoan", objtt.getLoaiTaiKhoan());
        String[] dieukien = new String[]
                {String.valueOf(objtt.getId_TT())};
        return db.update("ThuThu",values,"id_TT=?",dieukien);
    }
    public int delete (DTO_ThuThu objtt){
        String[] dieukien = new String[]
                {String.valueOf(objtt.getId_TT())};
        return db.delete("ThuThu","id_TT=?",dieukien);
    }
    public List<DTO_ThuThu> getAll(){
        String sql = "SELECT * FROM ThuThu";
        return getData(sql);
    }
    public DTO_ThuThu getID(String id){
        String sql = "SELECT * FROM ThuThu WHERE id_TT=?";
        List<DTO_ThuThu> list = getData(sql,id);
        return list.get(0);
    }



    @SuppressLint("Range")
    private List<DTO_ThuThu> getData(String sql, String... selectionArgs){

        List<DTO_ThuThu> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            DTO_ThuThu objtt = new DTO_ThuThu();
          objtt.setId_TT(c.getString(c.getColumnIndex("id_TT")));
            objtt.setTenTT(c.getString(c.getColumnIndex("tenTT")));
            objtt.setMatKhau(c.getString(c.getColumnIndex("matKhau")));
            objtt.setLoaiTaiKhoan(c.getString(c.getColumnIndex("loaiTaiKhoan")));
            list.add(objtt);
        }
        return list;
    }
    // check login
    public boolean checkLogin(String id, String password) {
        String sql = "SELECT * FROM ThuThu WHERE id_TT=? AND matKhau=?";
        List<DTO_ThuThu> list = getData(sql, id, password);
        return list.size() > 0;
    }
//    public int checkLogin (String id, String password){
//        String spl = "SELECT * FROM ThuThu WHERE id_TT=? AND matKhau=?";
//        List<DTO_ThuThu> list = getData(spl,id,password);
//        if (list.size()==0)
//            return -1;
//        return 1;
//    }
}
