package thuc.dv.thucdvph17759_asg.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import thuc.dv.thucdvph17759_asg.DAO.LoaiThuDAO;
import thuc.dv.thucdvph17759_asg.DTO.LoaiThu;
import thuc.dv.thucdvph17759_asg.R;

public class LoaiThuAdapter extends RecyclerView.Adapter<LoaiThuAdapter.LoaiThuViewHoldel> {
    private Context context;
    private ArrayList<LoaiThu> arrayList;
    Toast toast;
    LoaiThuDAO loaiThuDAO;

    public LoaiThuAdapter(Context context, ArrayList<LoaiThu> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        toast = new Toast(context);
        loaiThuDAO = new LoaiThuDAO(context);
    }

    @NonNull
    //ham cha ve layout hien thi
    @Override
    public LoaiThuViewHoldel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.loai_thu_view_item, parent, false);
        return new LoaiThuViewHoldel(view);
    }

    //ham set du lieu de hien thi len
    @Override
    public void onBindViewHolder(@NonNull LoaiThuAdapter.LoaiThuViewHoldel holder, int position) {
        LoaiThu loaiThu = arrayList.get(position);
        holder.tvNoiDungTenLoaiThu.setText(loaiThu.getTenLoaiThu());

        //Sửa dữ liệu
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_loaithu,null);
                builder.setView(view);
                AlertDialog alertDialog= builder.create();
                //ẩn background của view-item
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
                EditText edSuaLoaiThu = view.findViewById(R.id.ed_sua_tenloaithu);
                edSuaLoaiThu.setText(loaiThu.getTenLoaiThu());
                Button btnSua = view.findViewById(R.id.btn_sua_loaithu);
                Button btnHuy = view.findViewById(R.id.btn_huy_loaithu);
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loaiThu.setTenLoaiThu(edSuaLoaiThu.getText().toString());
                        long result =  loaiThuDAO.update(loaiThu);
                        if(result>0){
                            //buoc cap nhap lai du lieu
                            arrayList.clear();
                            arrayList.addAll(loaiThuDAO.getAll());
                            notifyDataSetChanged();

                            Toast.makeText(v.getContext(), "Sửa Thành Công", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }else {
                            Toast.makeText(v.getContext(), "Sửa Thất Bại", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
            }
        });
        //Xóa Dữ Liệu
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_delete,null);
                builder.setView(view);
                AlertDialog alertDialog= builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
                Button btn_delete = view.findViewById(R.id.btn_ok_delete);
                Button btn_no_delete = view.findViewById(R.id.btn_no_delete);
                btn_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        long result=loaiThuDAO.delete(loaiThu.getIdTenLoaiThu());
                        if(result>0){
                            //buoc cap nhap lai du lieu
                            arrayList.clear();
                            arrayList.addAll(loaiThuDAO.getAll());
                            notifyDataSetChanged();

                            Toast.makeText(v.getContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }else {
                            Toast.makeText(v.getContext(), "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                    }
                });
                btn_no_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
            }
        });
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class LoaiThuViewHoldel extends RecyclerView.ViewHolder {
        ImageView ivEdit;
        ImageView ivDel;
        TextView tvNoiDungTenLoaiThu;

        public LoaiThuViewHoldel(@NonNull View itemView) {
            super(itemView);
            ivDel = itemView.findViewById(R.id.iv_delete);
            ivEdit = itemView.findViewById(R.id.iv_edit);
            tvNoiDungTenLoaiThu = itemView.findViewById(R.id.tv_noidung_tenloaithu);
        }
    }
}
