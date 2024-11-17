package com.example.proj1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.elevation.SurfaceColors;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabs;
    private ViewPager viewPager1;
    private MyPageAdapter myPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int color = SurfaceColors.getColorForElevation(getApplicationContext(), 0);
        getWindow().setStatusBarColor(color);
        getWindow().setNavigationBarColor(color);


        setContentView(R.layout.activity_main);

        tabs = findViewById(R.id.tabs);
        viewPager1 = findViewById(R.id.viewPager1);

        tabs.addTab(tabs.newTab().setText("홈"));
        tabs.addTab(tabs.newTab().setText("질병카드"));
        tabs.addTab(tabs.newTab().setText("소식지"));
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);

        myPageAdapter = new MyPageAdapter(getSupportFragmentManager(), 3);
        viewPager1.setAdapter(myPageAdapter);

        // 탭 메뉴를 클릭하면 해당 프래그먼트로 변경(싱크화)
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager1));

        // 뷰페이저를 밀면 해당 탭으로 이동
        viewPager1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
    }
}
