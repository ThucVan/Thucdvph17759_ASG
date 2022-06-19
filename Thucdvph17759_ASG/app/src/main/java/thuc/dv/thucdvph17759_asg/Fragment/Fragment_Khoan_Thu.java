package thuc.dv.thucdvph17759_asg.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;

import thuc.dv.thucdvph17759_asg.Adapter.KhoanThuAdapter;
import thuc.dv.thucdvph17759_asg.DAO.KhoanThuDAO;
import thuc.dv.thucdvph17759_asg.DAO.LoaiThuDAO;
import thuc.dv.thucdvph17759_asg.DTO.KhoanThu;
import thuc.dv.thucdvph17759_asg.DTO.LoaiThu;
import thuc.dv.thucdvph17759_asg.R;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Fragment_Khoan_Thu extends Fragment {
    RecyclerView rvKhoanThu;
    KhoanThuAdapter adapter;
    KhoanThuDAO khoanThuDAO;
    ArrayList<KhoanThu> arrayListKhoanThus;
    FloatingActionButton fabKhoanThu;
    ArrayList<LoaiThu> list = new ArrayList<>();
    LoaiThuDAO loaiThuDAO;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    DatePickerDialog datePickerDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_khoan_thu, container, false);

        rvKhoanThu =view.findViewById(R.id.recyclerView_khoanthu);
        fabKhoanThu = view.findViewById(R.id.fab_addkhoanthu);

        khoanThuDAO = new KhoanThuDAO(getContext());
        loaiThuDAO = new LoaiThuDAO(getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvKhoanThu.setLayoutManager(layoutManager);
        arrayListKhoanThus = new ArrayList<>();
        arrayListKhoanThus = khoanThuDAO.getAll();
        adapter= new KhoanThuAdapter(getContext(),arrayListKhoanThus);
        rvKhoanThu.setAdapter(adapter);

        fabKhoanThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaLogAddKhoanThu();
            }
        });

        return view;
    }

    public void diaLogAddKhoanThu(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_khoanthu,null);
        builder.setView(view);
        AlertDialog alertDialog =builder.create();
        alertDialog.show();
        EditText edThemTenKhoanThu = view.findViewById(R.id.ed_them_tenkhoanthu);
        EditText edThemNoiDungKhoanThu = view.findViewById(R.id.ed_them_noidungkhoanthu);
        EditText edThemNgayThuKhoanThu = view.findViewById(R.id.ed_them_ngaythukhoanthu);
        EditText edThemSoTienThuKhoanThu = view.findViewById(R.id.ed_them_sotienkhoanthu);
        Spinner spthemkhoanthu = view.findViewById(R.id.sp_them_loaithu);
        //đổ dữu liệu vào cho spinner
        list = loaiThuDAO.getAll();
        ArrayAdapter adapter_sp = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,list);
        spthemkhoanthu.setAdapter(adapter_sp);
        //chon ngay
        edThemNgayThuKhoanThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edThemNgayThuKhoanThu.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });


        Button btnThem = view.findViewById(R.id.btn_them_khoanthu);
        Button btnHuy = view.findViewById(R.id.btn_huy_khoanthu);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edThemTenKhoanThu.getText().toString().isEmpty() ||
                        edThemNoiDungKhoanThu.getText().toString().isEmpty() ||
                        edThemNgayThuKhoanThu.getText().toString().isEmpty() ||
                        edThemSoTienThuKhoanThu.getText().toString().isEmpty()){
                    Toast.makeText(getContext()," Các Trường Không được để trống",Toast.LENGTH_SHORT).show();
                    return;
                }
                KhoanThu khoanThu = new KhoanThu();
                khoanThu.setTenKhoanThu(edThemTenKhoanThu.getText().toString());
                khoanThu.setNoiDung(edThemNoiDungKhoanThu.getText().toString());
                try {
                    khoanThu.setNgayThu(simpleDateFormat.parse(edThemNgayThuKhoanThu.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                khoanThu.setSoTien(Float.parseFloat(edThemSoTienThuKhoanThu.getText().toString()));
                LoaiThu loaiThu = (LoaiThu) spthemkhoanthu.getSelectedItem();
                khoanThu.setIdTenLoaiThu(loaiThu.getIdTenLoaiThu());

                long result =  khoanThuDAO.insert(khoanThu);
                if(result>0){
                    //buoc cap nhap lai du lieu
                    arrayListKhoanThus.clear();
                    arrayListKhoanThus.addAll(khoanThuDAO.getAll());
                    adapter.notifyDataSetChanged();

                    Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
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
