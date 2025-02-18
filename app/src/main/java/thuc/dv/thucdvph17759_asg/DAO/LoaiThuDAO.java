package thuc.dv.thucdvph17759_asg.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import thuc.dv.thucdvph17759_asg.DTO.LoaiThu;
import thuc.dv.thucdvph17759_asg.Database.CreateDB;

public class LoaiThuDAO {

    CreateDB createDB;

    public LoaiThuDAO(Context context) {
        createDB = new CreateDB(context);
    }
    //lay danh sach
    public ArrayList<LoaiThu> getAll2(){
        SQLiteDatabase db = createDB.getReadableDatabase();
        ArrayList<LoaiThu> loaiThuArrayList = new ArrayList<>();
        String SELECT = "SELECT * FROM " + LoaiThu.TB_NAME;
        Cursor cursor =db.rawQuery(SELECT,null);
        //dua con tro ve dau ket qua
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            LoaiThu loaiThu = new LoaiThu(id,ten);
            loaiThuArrayList.add(loaiThu);
            cursor.moveToNext();
        }
        cursor.close();//dong con tro
        db.close();//dong csdl
        return loaiThuArrayList;
    }

    //Them
    public long insert(LoaiThu loaiThu){
        SQLiteDatabase db = createDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LoaiThu.COL_NAME_TEN,loaiThu.getTenLoaiThu());
        long result =db.insert(LoaiThu.TB_NAME,null,contentValues);
        return result;
    }

    //sua
    public long update(LoaiThu loaiThu){
        SQLiteDatabase db = createDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LoaiThu.COL_NAME_TEN,loaiThu.getTenLoaiThu());
        long result =db.update(LoaiThu.TB_NAME,contentValues,"idTenLoaiThu = " + loaiThu.getIdTenLoaiThu(),null);
        return result;
    }

    //xoa
    public long delete(int id){
        SQLiteDatabase db = createDB.getWritableDatabase();
        long result = db.delete(LoaiThu.TB_NAME,"idTenLoaiThu = " + id,null);
        return result;
    }


    //lay danh sach theo dieu kien
    public ArrayList<LoaiThu> getLoaiThuTheoDK(String sql, String... a) {
        SQLiteDatabase db = createDB.getWritableDatabase();
        ArrayList<LoaiThu> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, a);
        cursor.moveToFirst();
        //dua con tro ve dau ket qua
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            LoaiThu loaiThu = new LoaiThu(id,ten);
            list.add(loaiThu);
            cursor.moveToNext();
        }
        cursor.close();//dong con tro
        db.close();//dong csdl
        return list;
    }

    //lấy toàn bộ ds thu chi
    public ArrayList<LoaiThu> getAll() {
        String sql = "SELECT * FROM " + LoaiThu.TB_NAME;
        return getLoaiThuTheoDK(sql);
    }
    //Lấy tên loai thu theo id loai thu
    public String getTenLoaiThu(int id) {
        String sql = "SELECT * FROM tb_loaithu WHERE idTenLoaiThu=?";
        ArrayList<LoaiThu> list = getLoaiThuTheoDK(sql, String.valueOf(id));
        return list.get(0).getTenLoaiThu();
    }

    public ArrayList<LoaiThu> checkGetIDLoaiThu(int id){
        String sql = "SELECT * FROM tb_loaithu as lt INNER JOIN tb_khoanthu as kt ON lt.idTenLoaiThu = kt.idTenLoaiThu WHERE lt.idTenLoaiThu=?";
        ArrayList<LoaiThu> list = getLoaiThuTheoDK(sql, String.valueOf(id));
        return list;
    }
}
