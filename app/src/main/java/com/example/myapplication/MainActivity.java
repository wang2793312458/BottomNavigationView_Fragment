package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mNavigationView;
    private BookFragment wallpaperFragment;
    private ClothesFragment musicFragment;
    private FoodFragment myFragment;
    private MeFragment mMeFragment;
    private Fragment[] fragments;
    private int lastfragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setItemIconTintList(null);
        initView();
    }

    private void initView() {
        wallpaperFragment = new BookFragment();
        musicFragment = new ClothesFragment();
        myFragment = new FoodFragment();
        mMeFragment = new MeFragment();
        fragments = new Fragment[]{wallpaperFragment, musicFragment, myFragment, mMeFragment};
        //设置fragment到布局
        getSupportFragmentManager().beginTransaction().replace(R.id.frag, wallpaperFragment).show(wallpaperFragment).commit();
        //这里是bottomnavigationview的点击事件
        mNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //这里因为需要对3个fragment进行切换
                    //start
                    if (lastfragment != 0) {
                        switchFragment(lastfragment, 0);
                        lastfragment = 0;
                    }
                    //end
                    //如果只是想测试按钮点击，不管fragment的切换，可以把start到end里面的内容去掉
                    return true;
                case R.id.navigation_dashboard:
                    if (lastfragment != 1) {
                        switchFragment(lastfragment, 1);
                        lastfragment = 1;
                    }
                    return true;
                case R.id.navigation_notifications:
                    if (lastfragment != 2) {
                        switchFragment(lastfragment, 2);
                        lastfragment = 2;
                    }
                    return true;
                case R.id.navigation_notifications2:
                    if (lastfragment != 3) {
                        switchFragment(lastfragment, 3);
                        lastfragment = 3;
                    }
                    return true;
                default:
                    break;
            }
            return false;
        }
    };

    /**
     * 切换fragment
     */
    private void switchFragment(int lastfragment, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //隐藏上个Fragment
        transaction.hide(fragments[lastfragment]);
        if (fragments[index].isAdded() == false) {
            transaction.add(R.id.frag, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }
}
