package thuc.dv.thucdvph17759_asg.Fragment;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import thuc.dv.thucdvph17759_asg.Adapter.LoaiThuAdapter;
import thuc.dv.thucdvph17759_asg.DAO.LoaiThuDAO;
import thuc.dv.thucdvph17759_asg.DTO.LoaiThu;
import thuc.dv.thucdvph17759_asg.R;

public class Fragment_Loai_Thu extends Fragment {
    RecyclerView rvLoaiThu;
    LoaiThuAdapter adapter;
    LoaiThuDAO loaiThuDAO;
    ArrayList<LoaiThu> arrayListLoaiThu;
    FloatingActionButton fabLoaiThu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_loai_thu, container, false);
        rvLoaiThu = view.findViewById(R.id.recyclerView_loaithu);
        fabLoaiThu = view.findViewById(R.id.fab_addloaithu);
        loaiThuDAO = new LoaiThuDAO(getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvLoaiThu.setLayoutManager(layoutManager);
        arrayListLoaiThu = new ArrayList<>();
        arrayListLoaiThu = loaiThuDAO.getAll();
        adapter = new LoaiThuAdapter(getContext(), arrayListLoaiThu);
        rvLoaiThu.setAdapter(adapter);

        fabLoaiThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaLogAddLoaiThu();
            }
        });
        return view;
    }
    public void diaLogAddLoaiThu(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_loaithu,null);
        builder.setView(view);
        AlertDialog alertDialog =builder.create();
        alertDialog.show();
        EditText edThemLoaiThu = view.findViewById(R.id.ed_them_tenloaithu);
        Button btnThem = view.findViewById(R.id.btn_them_loaithu);
        Button btnHuy = view.findViewById(R.id.btn_huy_loaithu);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiThu loaiThu = new LoaiThu();
                loaiThu.setTenLoaiThu(edThemLoaiThu.getText().toString());
                if (edThemLoaiThu.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Không được để trống",Toast.LENGTH_SHORT).show();
                    return;
                }
                long result =  loaiThuDAO.insert(loaiThu);
                if(result>0){
                    //buoc cap nhap lai du lieu
                    arrayListLoaiThu.clear();
                    arrayListLoaiThu.addAll(loaiThuDAO.getAll());
                    adapter.notifyDataSetChanged();

                    Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();;
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
}
