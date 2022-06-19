package thuc.dv.thucdvph17759_asg.Adapter;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import thuc.dv.thucdvph17759_asg.Fragment.Fragment_Khoan_Thu;
import thuc.dv.thucdvph17759_asg.Fragment.Fragment_Loai_Thu;

public class ThuViewAdapter extends FragmentStatePagerAdapter {
    private int soLuongTab = 2;
    public ThuViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new Fragment_Loai_Thu();
            case 1:
                return new Fragment_Khoan_Thu();
            default:
                return new Fragment_Loai_Thu();
        }
    }

    @Override
    public int getCount() {
        return soLuongTab;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position) {
            case 0:
                title = "Loại Thu";
                break;
            case 1:
                title = "Khoản Thu";
                break;
        }
        return title;
    }
}
