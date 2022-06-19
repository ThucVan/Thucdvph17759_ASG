package thuc.dv.thucdvph17759_asg;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import thuc.dv.thucdvph17759_asg.Fragment.Frg_Chi;
import thuc.dv.thucdvph17759_asg.Fragment.Frg_Gioi_Thieu;
import thuc.dv.thucdvph17759_asg.Fragment.Frg_Thong_ke;
import thuc.dv.thucdvph17759_asg.Fragment.Frg_Thu;


public class MainAppActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //khai bao bien de check fragment hien tai
    private  static final int FRAGMENT_THU=0;
    private  static final int FRAGMENT_CHI=1;
    private  static final int FRAGMENT_THONGKE=2;
    private  static final int FRAGMENT_GIOITHIEU=3;

    //TAO BIEN DE CHECK  FRAGMENT
    int current = FRAGMENT_THU;

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);

        toolbar =findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        frameLayout=findViewById(R.id.frame_layout);

        setSupportActionBar(toolbar);

        //Tao icon menu ba gach
        toggle = new ActionBarDrawerToggle(MainAppActivity.this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);//dua togle vao drawerlayout
        toggle.syncState();//dong bo hoa

        //navigationView lang nghe su kien
       navigationView.setNavigationItemSelectedListener(this);

        //set fragment hien thi luc dau tien
        replaceFragment(new Frg_Thu());
        navigationView.getMenu().findItem(R.id.khoanthu).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.khoanthu){
            setTitle("KHOẢN THU");
            if (current!=FRAGMENT_THU){
                replaceFragment(new Frg_Thu());
                current=FRAGMENT_THU;
                navigationView.getMenu().findItem(R.id.khoanthu).setChecked(true);
            }
        }else if (id==R.id.khoanchi){
            setTitle("KHOẢN CHI");
            if (current!=FRAGMENT_CHI){
                replaceFragment(new Frg_Chi());
                current=FRAGMENT_CHI;
                navigationView.getMenu().findItem(R.id.khoanchi).setChecked(true);
            }
        }
        else if (id==R.id.thongke){
            setTitle("THỐNG KÊ");
            if (current!=FRAGMENT_THONGKE){
                replaceFragment(new Frg_Thong_ke());
                current=FRAGMENT_THONGKE;
                navigationView.getMenu().findItem(R.id.thongke).setChecked(true);
            }
        }
        else if (id==R.id.gioithieu){
            setTitle("QUẢN LÝ THU CHI");
            if (current!=FRAGMENT_GIOITHIEU){
                replaceFragment(new Frg_Gioi_Thieu());
                current=FRAGMENT_GIOITHIEU;
                navigationView.getMenu().findItem(R.id.gioithieu).setChecked(true);
            }
        }
        else if (id==R.id.thoat) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainAppActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Thoát ?")
                        .setMessage("Bạn Chắc Chắn Muốn Thoát ??");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                        System.exit(0);
                    }
                });

                builder.setNegativeButton("CANCLE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment).commit();
    }
}