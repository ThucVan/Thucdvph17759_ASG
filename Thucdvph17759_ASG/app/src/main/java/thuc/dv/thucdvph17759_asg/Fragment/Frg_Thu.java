package thuc.dv.thucdvph17759_asg.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import thuc.dv.thucdvph17759_asg.Adapter.ThuViewAdapter;
import thuc.dv.thucdvph17759_asg.R;

public class Frg_Thu extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    ThuViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thu, container, false);
        tabLayout = view.findViewById(R.id.tablayout_thu);
        viewPager = view.findViewById(R.id.viewpager_thu);
        adapter = new ThuViewAdapter(getActivity().getSupportFragmentManager());
        //set adapter cho viewpager
        viewPager.setAdapter(adapter);
        //set viewpager vao tablayout
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
