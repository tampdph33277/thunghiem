package tampdph33277.fpoly.du_an_mau_ph33277.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_Sach;
import tampdph33277.fpoly.du_an_mau_ph33277.DpHelper.MyDpHelper;


public class DAO_Sach {
    MyDpHelper myDpHelper;
    SQLiteDatabase db;
    public DAO_Sach (Context context){
        MyDpHelper myDpHelper  = new MyDpHelper(context);
        db = myDpHelper.getWritableDatabase();
    }
    public long insert (DTO_Sach objsach){
        ContentValues values = new ContentValues();
       // values.put("id_Sach", objsach.getId_Sach());
        values.put("tenSach", objsach.getTenSach());
        values.put("giaThue", objsach.getGiaThue());
        values.put("id_LoaiSach", objsach.getId_LoaiSach());
        return db.insert("Sach",null,values);
    }
    public int update (DTO_Sach objsach ){
        ContentValues values = new ContentValues();

        values.put("tenSach", objsach.getTenSach());
        values.put("giaThue", objsach.getGiaThue());
        values.put("id_LoaiSach", objsach.getId_LoaiSach());
        String[] dieukien = new String[]
                {String.valueOf(objsach.getId_Sach())};
        return db.update("Sach",values,"id_Sach=?",dieukien);
    }
    public int delete (DTO_Sach objsach){
        String[] dieukien = new String[]
                {String.valueOf(objsach.getId_Sach())};
        return db.delete("Sach","id_Sach=?",dieukien);
    }
    //get data nhieu tham so
    @SuppressLint("Range")
    private List<DTO_Sach> getData(String sql, String... selectionArgs){

        List<DTO_Sach> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            DTO_Sach objsach = new DTO_Sach();
            objsach.setId_Sach(Integer.parseInt(c.getString(c.getColumnIndex("id_Sach"))));
            objsach.setTenSach( c.getString(c.getColumnIndex("tenSach")));
            objsach.setGiaThue(Integer.parseInt(c.getString(c.getColumnIndex("giaThue"))));
            objsach.setId_LoaiSach(Integer.parseInt(c.getString(c.getColumnIndex("id_LoaiSach"))));
            list.add(objsach);
        }
        return list;
    }
    // get tat ca data
    public List<DTO_Sach> getAll(){
        String sql = "SELECT * FROM Sach";
        return getData(sql);
    }
    //get data teo id
    public DTO_Sach getID(String id){
        String sql = "SELECT * FROM Sach WHERE id_Sach=?";
        List<DTO_Sach> list = getData(sql,id);
        return list.get(0);
    }
}
