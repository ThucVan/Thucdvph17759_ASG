package thuc.dv.thucdvph17759_asg.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import thuc.dv.thucdvph17759_asg.DAO.KhoanChiDAO;
import thuc.dv.thucdvph17759_asg.DAO.KhoanThuDAO;
import thuc.dv.thucdvph17759_asg.DTO.KhoanChi;
import thuc.dv.thucdvph17759_asg.DTO.KhoanThu;
import thuc.dv.thucdvph17759_asg.R;

public class Frg_Thong_ke extends Fragment {
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    KhoanChiDAO khoanChiDAO;
    KhoanThuDAO khoanThuDAO;
    float tongthu=0,tongchi=0;

    public Frg_Thong_ke() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);
        khoanChiDAO = new KhoanChiDAO(getContext());
        khoanThuDAO = new KhoanThuDAO(getContext());

        ArrayList<KhoanChi> listchi =khoanChiDAO.getAll();
        for (int i= 0;i<listchi.size();i++){
            tongchi = tongchi + listchi.get(i).getSoTien();
        }

        ArrayList<KhoanThu> listthu =khoanThuDAO.getAll();
        for (int i= 0;i<listthu.size();i++){
            tongthu = tongthu + listthu.get(i).getSoTien();
        }

        barChart = view.findViewById(R.id.bar_chart);
        ArrayList<BarEntry> visitors = new ArrayList<>();

        visitors.add(new BarEntry(2020, tongchi));
        visitors.add(new BarEntry(2021, tongthu));

        barDataSet = new BarDataSet(visitors, "Với Màu Xanh Là Tổng Thu Và Màu Vàng Là Tổng Chi");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(15f);

        barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Bảng Thống Kê Thu Chi");
        barChart.animateY(2000);

        return view;
    }
}
