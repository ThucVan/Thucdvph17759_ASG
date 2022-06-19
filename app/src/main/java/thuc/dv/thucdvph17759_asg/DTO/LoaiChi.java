package thuc.dv.thucdvph17759_asg.DTO;

public class LoaiChi {
    public int idTenLoaiChi;
    public String tenLoaiChi;

    public static final String TB_NAME = "tb_loaichi";
    public static final String COL_NAME_ID = "idTenLoaiChi";
    public static final String COL_NAME_TEN = "tenLoaiChi";

    public LoaiChi(int idTenLoaiChi, String tenLoaiChi) {
        this.idTenLoaiChi = idTenLoaiChi;
        this.tenLoaiChi = tenLoaiChi;
    }

    public LoaiChi() {
    }

    public int getIdTenLoaiChi() {
        return idTenLoaiChi;
    }

    public void setIdTenLoaiChi(int idTenLoaiChi) {
        this.idTenLoaiChi = idTenLoaiChi;
    }

    public String getTenLoaiChi() {
        return tenLoaiChi;
    }

    public void setTenLoaiChi(String tenLoaiChi) {
        this.tenLoaiChi = tenLoaiChi;
    }

    public String toString() {
        return getTenLoaiChi();
    }

}
