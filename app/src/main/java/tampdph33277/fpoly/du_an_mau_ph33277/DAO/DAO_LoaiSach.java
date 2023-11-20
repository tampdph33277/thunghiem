package tampdph33277.fpoly.du_an_mau_ph33277.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_LoaiSach;
import tampdph33277.fpoly.du_an_mau_ph33277.DpHelper.MyDpHelper;


public class DAO_LoaiSach {
    MyDpHelper myDpHelper;
    SQLiteDatabase db;
    public DAO_LoaiSach (Context context){
        MyDpHelper myDpHelper  = new MyDpHelper(context);
        db = myDpHelper.getWritableDatabase();
    }
    public long insert (DTO_LoaiSach objls){
        ContentValues values = new ContentValues();
      //  values.put("id_LoaiSach", objls.getId_LoaiSach());
        values.put("tenLoaiSach", objls.getTenLoaiSach());
        values.put("nhaCungCap", objls.getNhaCungCap());


        return db.insert("LoaiSach",null,values);
    }
    public int update(DTO_LoaiSach objls){
        ContentValues values = new ContentValues();
        values.put("tenLoaiSach", objls.getTenLoaiSach());
        values.put("nhaCungCap", objls.getNhaCungCap());
        String[] dieukien = new String[]
                {String.valueOf(objls.getId_LoaiSach())};
        return db.update("LoaiSach",values,"id_LoaiSach=?",dieukien);
    }
    public int delete (DTO_LoaiSach objls){
        String[] dieukien = new String[]
                {String.valueOf(objls.getId_LoaiSach())};
        return db.delete("LoaiSach","id_LoaiSach=?",dieukien);
    }
    //get data nhieu tham so
    @SuppressLint("Range")
    private List<DTO_LoaiSach> getData(String sql, String... selectionArgs){

        List<DTO_LoaiSach> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            DTO_LoaiSach objls = new DTO_LoaiSach();
            objls.setId_LoaiSach(Integer.parseInt(c.getString(c.getColumnIndex("id_LoaiSach"))));
             objls.setTenLoaiSach( c.getString(c.getColumnIndex("tenLoaiSach")));
            objls.setNhaCungCap( c.getString(c.getColumnIndex("nhaCungCap")));

            list.add(objls);
        }
        return list;
    }
    // get tat ca data
    public List<DTO_LoaiSach> getAll(){
        String sql = "SELECT * FROM LoaiSach";
        return getData(sql);
    }
    //get data teo id
    public DTO_LoaiSach getID(String id){
        String sql = "SELECT * FROM LoaiSach WHERE id_LoaiSach=?";
        List<DTO_LoaiSach> list = getData(sql,id);
        return list.get(0);
    }
}
