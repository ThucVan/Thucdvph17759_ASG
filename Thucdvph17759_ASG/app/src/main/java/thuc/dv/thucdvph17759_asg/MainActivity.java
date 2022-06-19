package thuc.dv.thucdvph17759_asg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PaperOnboardingFragment paperOnboardingFragment = PaperOnboardingFragment.newInstance(getPaperOnboardingPageData());

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content_farme, paperOnboardingFragment);
        fragmentTransaction.commit();

        paperOnboardingFragment.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private ArrayList<PaperOnboardingPage> getPaperOnboardingPageData(){
        PaperOnboardingPage scr1 = new PaperOnboardingPage("",
                "Dễ dàng truy cập dữ liệu trên nhiều thiết bị",
                Color.parseColor("#29b497"), R.drawable.step1, R.drawable.iconsearch);
        PaperOnboardingPage scr2 = new PaperOnboardingPage("",
                "Kiểm soát từng khoản thu chi với hệ thống báo cáo chi tiết",
                Color.parseColor("#2796ce"), R.drawable.step2, R.drawable.iconwallet);
        PaperOnboardingPage scr3 = new PaperOnboardingPage("",
                "Lên kế hoạch tài chính thông minh và từng bước tiết kiệm để thực hiện hóa ước mơ",
                Color.parseColor("#e25704"), R.drawable.step3, R.drawable.icnext);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);

        return elements;
    }
}