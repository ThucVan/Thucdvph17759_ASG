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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import thuc.dv.thucdvph17759_asg.Adapter.LoaiChiAdapter;
import thuc.dv.thucdvph17759_asg.DAO.LoaiChiDAO;
import thuc.dv.thucdvph17759_asg.DTO.LoaiChi;
import thuc.dv.thucdvph17759_asg.R;

public class Fragment_Loai_Chi extends Fragment {
    LoaiChiAdapter chiAdapter;
    RecyclerView rvLoaiChi;
    FloatingActionButton fabLoaiChi;
    ArrayList<LoaiChi> arrayListLoaiChi;
    LoaiChiDAO loaiChiDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_loai_chi, container, false);

        rvLoaiChi = view.findViewById(R.id.recyclerView_loaichi);
        fabLoaiChi =view.findViewById(R.id.fab_addloaichii);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvLoaiChi.setLayoutManager(layoutManager);
        arrayListLoaiChi = new ArrayList<>();
        loaiChiDAO = new LoaiChiDAO(getContext());
        arrayListLoaiChi = loaiChiDAO.getAll();
        chiAdapter = new LoaiChiAdapter(getContext(),arrayListLoaiChi);
        rvLoaiChi.setAdapter(chiAdapter);

        fabLoaiChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaLogAddLoaiChi();
            }
        });

        return view;
    }
    public void diaLogAddLoaiChi(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_loaichi,null);
        builder.setView(view);
        AlertDialog alertDialog =builder.create();
        alertDialog.show();
        EditText edThemLoaiChi = view.findViewById(R.id.ed_them_tenloaichi);
        Button btnThem = view.findViewById(R.id.btn_them_loaichi);
        Button btnHuy = view.findViewById(R.id.btn_huy_loaichi);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiChi loaiChi = new LoaiChi();
                loaiChi.setTenLoaiChi(edThemLoaiChi.getText().toString());
                if (edThemLoaiChi.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Không được để trống",Toast.LENGTH_SHORT).show();
                    return;
                }
                long result =  loaiChiDAO.insert(loaiChi);
                if(result>0){
                    //buoc cap nhap lai du lieu
                    arrayListLoaiChi.clear();
                    arrayListLoaiChi.addAll(loaiChiDAO.getAll());
                    chiAdapter.notifyDataSetChanged();

                    Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_LONG).show();
                    alertDialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_LONG).show();
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
