package com.socialapp.wsd.Desktop;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.socialapp.wsd.R;
import com.socialapp.wsd.Utils.BottomNavigationViewHelper;
import com.socialapp.wsd.Utils.SectionsPagerAdapter;
import com.socialapp.wsd.Utils.UniversalImageLoader;

public class DesktopActivity extends AppCompatActivity {

    private static final String TAG = "DesktopActivity";
    private static final int ACTIVITY_NUM = 0;
    private Context mContext = DesktopActivity.this;

    // Beego API
    private boolean mAuth;
    private boolean mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: starting.");

        initImageLoader();
        setupBottomNavigationView();
        setupViewPager();


    }

    /**
     * Setup the Beego API auth object
     */
    private void setupBeegoAuth() {
        Log.d(TAG, "setupBeegoAuth: setting up Beego auth.");

        mAuth = false;


    }

    /**
     * Responsible for adding three tabs: Home, Camera and Messages
     */
    private void setupViewPager() {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CameraFragment());  // index 0
        adapter.addFragment(new HomeFragment());    // index 1
        adapter.addFragment(new MessagesFragment());// index 2
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_arrow);
    }
    /*
     * Bottom navigation view setup
     */
    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: settting up BottomNavigationViewEx");
        BottomNavigationViewEx navigationEx = findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(navigationEx);
        BottomNavigationViewHelper.enableNavigation(mContext, navigationEx);
        Menu menu = navigationEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    private void initImageLoader() {
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }

}
