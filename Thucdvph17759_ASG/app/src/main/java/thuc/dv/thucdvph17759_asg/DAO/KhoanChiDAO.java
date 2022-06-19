package thuc.dv.thucdvph17759_asg.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import thuc.dv.thucdvph17759_asg.DTO.KhoanChi;
import thuc.dv.thucdvph17759_asg.Database.CreateDB;

public class KhoanChiDAO {
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    CreateDB createDB;

    public KhoanChiDAO(Context context) {
        createDB = new CreateDB(context);
    }
    //lay danh sach
    public ArrayList<KhoanChi> getAll()  {
        SQLiteDatabase db = createDB.getReadableDatabase();
        ArrayList<KhoanChi> khoanChiArrayList = new ArrayList<>();
        String SELECT = "SELECT * FROM " + KhoanChi.TB_NAME;
        Cursor cursor =db.rawQuery(SELECT,null);
        //dua con tro ve dau ket qua
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id1 = cursor.getInt(0);
            int id2 = cursor.getInt(1);
            String ten = cursor.getString(2);
            String noiDung = cursor.getString(3);
            float soTien = cursor.getFloat(4);
            Date ngay = null;
            try {
                ngay = format.parse(cursor.getString(5));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            KhoanChi khoanChi = new KhoanChi(id2,id1,ten,noiDung,soTien,ngay);
            khoanChiArrayList.add(khoanChi);
            cursor.moveToNext();
        }
        cursor.close();//dong con tro
        db.close();//dong csdl
        return khoanChiArrayList;
    }
    //Them
    public long insert(KhoanChi khoanChi){
        SQLiteDatabase db = createDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KhoanChi.COL_NAME_ID_FK,khoanChi.getIdTenLoaiChi());
        contentValues.put(KhoanChi.COL_NAME_TEN,khoanChi.getTenKhoanChi());
        contentValues.put(KhoanChi.COL_NAME_NOIDUNG,khoanChi.getNoiDung());
        contentValues.put(KhoanChi.COL_NAME_SOTIEN,khoanChi.getSoTien());
        contentValues.put(KhoanChi.COL_NAME_NGAYCHI,format.format(khoanChi.getNgayChi()));
        long result=db.insert(KhoanChi.TB_NAME,null,contentValues);
        return result;
    }
    //Sua
    public long update(KhoanChi khoanChi){
        SQLiteDatabase db = createDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KhoanChi.COL_NAME_ID_FK,khoanChi.getIdTenLoaiChi());
        contentValues.put(KhoanChi.COL_NAME_TEN,khoanChi.getTenKhoanChi());
        contentValues.put(KhoanChi.COL_NAME_NOIDUNG,khoanChi.getNoiDung());
        contentValues.put(KhoanChi.COL_NAME_SOTIEN,khoanChi.getSoTien());
        contentValues.put(KhoanChi.COL_NAME_NGAYCHI,format.format(khoanChi.getNgayChi()));
        long result=db.update(KhoanChi.TB_NAME,contentValues,"idKhoanChi = " + khoanChi.getIdKhoanChi(),null);
        return result;
    }
    //xoa
    public long delete(int id){
        SQLiteDatabase db = createDB.getWritableDatabase();
        long result = db.delete(KhoanChi.TB_NAME,"idKhoanChi = " + id,null);
        return result;
    }
}
