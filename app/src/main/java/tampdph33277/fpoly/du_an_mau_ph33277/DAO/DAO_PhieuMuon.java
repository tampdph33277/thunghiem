package tampdph33277.fpoly.du_an_mau_ph33277.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_PhieuMuon;
import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_Sach;
import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_TopSach;
import tampdph33277.fpoly.du_an_mau_ph33277.DpHelper.MyDpHelper;


public class DAO_PhieuMuon {
    MyDpHelper myDpHelper;
    SQLiteDatabase db;
    private Context context;
SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    public DAO_PhieuMuon (Context context){
this.context = context;
        MyDpHelper myDpHelper  = new MyDpHelper(context);
        db = myDpHelper.getWritableDatabase();
    }
    public long inser(DTO_PhieuMuon objpm){
        ContentValues values = new ContentValues();
       // values.put("id_PM",objpm.getId_PM());
        values.put("id_TT",objpm.getId_TT());
        values.put("id_TV",objpm.getId_TV());
        values.put("id_Sach",objpm.getId_Sach());
        values.put("ngayMuon",sdf.format(objpm.getNgayMuon()));
        values.put("traSach",objpm.getTraSach());
        values.put("tienThue",objpm.getTienThue());
        values.put("thueVAT",objpm.getThueVAT());
        return db.insert("PhieuMuon",null,values);
    }
    public long update(DTO_PhieuMuon objpm){
        ContentValues values = new ContentValues();
        // values.put("id_PM",objpm.getId_PM());
        values.put("id_TT",objpm.getId_TT());
        values.put("id_TV",objpm.getId_TV());
        values.put("id_Sach",objpm.getId_Sach());
        values.put("ngayMuon",sdf.format(objpm.getNgayMuon()));
        values.put("traSach",objpm.getTraSach());
        values.put("tienThue",objpm.getTienThue());
        values.put("thueVAT",objpm.getThueVAT());
        String[] dieukien = new String[]
                {String.valueOf(objpm.getId_PM())};
        return db.update("PhieuMuon",values,"id_PM=?",dieukien);
    }
    public int delete (DTO_PhieuMuon objpm){
        String[] dieukien = new String[]
                {String.valueOf(objpm.getId_PM())};
        return db.delete("PhieuMuon","id_PM=?",dieukien);
    }
    //get data nhieu tham so
    @SuppressLint("Range")
    private List<DTO_PhieuMuon> getData(String sql, String... selectionArgs){

        List<DTO_PhieuMuon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            DTO_PhieuMuon objpm = new DTO_PhieuMuon();
            objpm.setId_PM(Integer.parseInt(c.getString(c.getColumnIndex("id_PM"))));
            objpm.setId_TT( c.getString(c.getColumnIndex("id_TT")));
            objpm.setId_TV(Integer.parseInt(c.getString(c.getColumnIndex("id_TV"))));
            objpm.setId_Sach(Integer.parseInt(c.getString(c.getColumnIndex("id_Sach"))));
            objpm.setTienThue(Integer.parseInt(c.getString(c.getColumnIndex("tienThue"))));
            objpm.setThueVAT(Integer.parseInt(c.getString(c.getColumnIndex("thueVAT"))));
            try{
                objpm.setNgayMuon((sdf.parse(c.getString(c.getColumnIndex("ngayMuon")))));
            }catch (ParseException e){
                e.printStackTrace();
            }
            objpm.setTraSach(Integer.parseInt(c.getString(c.getColumnIndex("traSach"))));
            list.add(objpm);
        }
        return list;
    }
    // get tat ca data
    public List<DTO_PhieuMuon> getAll(){
        String sql = "SELECT * FROM PhieuMuon";
        return getData(sql);
    }
    //get data teo id
    public DTO_PhieuMuon getID(String id){
        String sql = "SELECT * FROM PhieuMuon WHERE id_PM=?";
        List<DTO_PhieuMuon> list = getData(sql,id);
        return list.get(0);
    }
    // thong kê top 10
    @SuppressLint("Range")
    public List<DTO_TopSach> getTopSach(){
        String sqlTopSach = "SELECT id_Sach, count(id_Sach) as soLuong FROM PhieuMuon GROUP BY id_Sach ORDER BY soLuong DESC LIMIT 10";
        List<DTO_TopSach> list = new ArrayList<DTO_TopSach>();
        DAO_Sach dao_sach =  new DAO_Sach(context);
        Cursor c = db.rawQuery(sqlTopSach,null);
        while (c.moveToNext()){
            DTO_TopSach dto_topSach = new DTO_TopSach();
            @SuppressLint("Range")
            DTO_Sach dto_sach = dao_sach.getID(c.getString(c.getColumnIndex("id_Sach")));
            dto_topSach.setTenSach(dto_sach.getTenSach());
            dto_topSach.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soLuong"))));
      list.add(dto_topSach);
        }
        return list;
    }
@SuppressLint("Range")
public int getDoanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu = "SELECT SUM(tienThue) as doanhThu FROM PhieuMuon WHERE ngayMuon BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<Integer>();
        Cursor c = db.rawQuery(sqlDoanhThu,new String[]{tuNgay,denNgay});
        while (c.moveToNext()){
            try {
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));

            }catch (Exception e){
                list.add(0);
            }
        }
return list.get(0);
}
}
