package tampdph33277.fpoly.du_an_mau_ph33277.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tampdph33277.fpoly.du_an_mau_ph33277.DTO.DTO_ThanhVien;
import tampdph33277.fpoly.du_an_mau_ph33277.DpHelper.MyDpHelper;


public class DAO_ThanhVien {

    MyDpHelper myDpHelper;
    private SQLiteDatabase db ;
    public DAO_ThanhVien (Context context){
        MyDpHelper myDpHelper  = new MyDpHelper(context);
        db = myDpHelper.getWritableDatabase();
    }
    public long insert (DTO_ThanhVien objtv){
        ContentValues values = new ContentValues();
        values.put("tenTV", objtv.getTenTV());
        values.put("namSinh", objtv.getNamSinh());
        return db.insert("ThanhVien",null,values);
    }
    public int update (DTO_ThanhVien objtv){
        ContentValues values = new ContentValues();

        values.put("tenTV", objtv.getTenTV());
        values.put("namSinh", objtv.getNamSinh());
        String[] dieukien = new String[]
                {String.valueOf(objtv.getId_TV())};
        return db.update("ThanhVien",values,"id_TV=?",dieukien);
    }
    public int delete (DTO_ThanhVien objtv){
               String[] dieukien = new String[]
                {String.valueOf(objtv.getId_TV())};
        return db.delete("ThanhVien","id_TV=?",dieukien);
    }
    public List<DTO_ThanhVien> getAll(){
        String sql = "SELECT * FROM ThanhVien";
        return getData(sql);
    }
    public DTO_ThanhVien getID(String id){
        String sql = "SELECT * FROM ThanhVien WHERE id_TV=?";
        List<DTO_ThanhVien> list = getData(sql,id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<DTO_ThanhVien> getData(String sql, String... selectionArgs){

        List<DTO_ThanhVien> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            DTO_ThanhVien objtv = new DTO_ThanhVien();
            objtv.setId_TV(Integer.parseInt(c.getString(c.getColumnIndex("id_TV"))));
            objtv.setTenTV(c.getString(c.getColumnIndex("tenTV")));
            objtv.setNamSinh( c.getString(c.getColumnIndex("namSinh")));
            list.add(objtv);
        }
        return list;
    }
    }

//    public List<DTO_ThanhVien> getAll(){
//
//
//    }}
//    public List<DTO_ThanhVien> getAll(){
//        List<DTO_ThanhVien> listDTO_TV = new ArrayList<>();
//        Cursor c = db.rawQuery("SELECT * FROM ThanhVien",null);
//        if(c != null && c.getCount()>0){
//            c.moveToFirst();
//            do {
//                int id_TV = c.getInt(0);
//                String tenTV = c.getString(1);
//                String NamSinh = c.getString(2);
//
//
//
//                DTO_ThanhVien objtv_DTO = new DTO_ThanhVien();
//                objtv_DTO.setId_TV(id_TV);
//                objtv_DTO.setTenTV(tenTV);
//                objtv_DTO.setNamSinh(NamSinh);
//
//                listDTO_TV.add(objtv_DTO);
//
//            }while (c.moveToNext());
//        }return listDTO_TV;
//    }

