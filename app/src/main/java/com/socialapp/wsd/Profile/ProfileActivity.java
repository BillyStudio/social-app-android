package com.socialapp.wsd.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.socialapp.wsd.R;
import com.socialapp.wsd.Utils.BottomNavigationViewHelper;
import com.socialapp.wsd.Utils.GridImageAdapter;
import com.socialapp.wsd.Utils.UniversalImageLoader;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_NUM = 4;
    private static final int NUM_GRID_COLUMNS = 3;
    private Context mContext = ProfileActivity.this;
    private ProgressBar mProgressBar;
    private ImageView profilePhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: Started.");
        setupActivityWidgets();
        setupBottomNavigationView();
        setupToolbar();
        setProfileImage();

        tempGridSetup();
    }

    private void tempGridSetup() {
        ArrayList<String> imgURLs = new ArrayList<>();
        imgURLs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529928492&di=eb5d6922f106c7755c8e9cc8cae6b057&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.taopic.com%2Fuploads%2Fallimg%2F140814%2F240410-140Q406392254.jpg");
        imgURLs.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1051910104,2515083462&fm=27&gp=0.jpg");
        imgURLs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529928371&di=40b890e13e159431691608cce0350f74&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3Df72e5c441a38534398c28f62fb7ada0b%2Ffaf2b2119313b07e0884ff2006d7912397dd8cd9.jpg");
        imgURLs.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3114133109,1557575257&fm=27&gp=0.jpg");
        imgURLs.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3741917178,2789063179&fm=27&gp=0.jpg");
        imgURLs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529928569&di=a246648ab4ccc31a783fae609a1dd292&imgtype=jpg&er=1&src=http%3A%2F%2Fimagecdn.lecake.com%2Fpostsystem%2Fdocroot%2Fimages%2Fgoods%2F201212%2F10871%2Fdetail_10871_1.jpg");
        imgURLs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529928601&di=68bed4555d03ee3a370c6fa6651501a1&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.sootuu.com%2Fvector%2F200801%2F097%2F386.jpg");
        imgURLs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529928641&di=0fa756a2d4d5b690139c92fb6c8adec6&imgtype=jpg&er=1&src=http%3A%2F%2Fwenwen.soso.com%2Fp%2F20100418%2F20100418105254-1692267634.jpg");
        imgURLs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529928661&di=d249835c8fa5123894c409fe06ffb594&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.taopic.com%2Fuploads%2Fallimg%2F140306%2F235030-140306150U849.jpg");
        imgURLs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529928686&di=46098c99f4462aa43c2aeb6922658e2a&imgtype=jpg&er=1&src=http%3A%2F%2Fimg3.redocn.com%2F20120405%2FRedocn_2012040500434646.jpg");

        setupImageGrid(imgURLs);
    }

    private void setupImageGrid(ArrayList<String> imgURLs) {
        GridView gridView = (GridView) findViewById(R.id.gridView);
        int gridWidth = getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth / NUM_GRID_COLUMNS;
        gridView.setColumnWidth(imageWidth);

        GridImageAdapter adapter = new GridImageAdapter(mContext, R.layout.layout_grid_imageview, "", imgURLs);
        gridView.setAdapter(adapter);
    }

    private void setProfileImage() {
        Log.d(TAG, "setProfileImage: Setting profile photo.");
        String imageURL = "http://e.hiphotos.baidu.com/zhidao/pic/item/f7246b600c3387440f9940625d0fd9f9d72aa054.jpg";
        UniversalImageLoader.setImage(imageURL, profilePhoto, mProgressBar, "");
    }

    private void setupActivityWidgets() {
        mProgressBar = (ProgressBar) findViewById(R.id.profileProgressBar);
        mProgressBar.setVisibility(View.GONE);
        profilePhoto = (ImageView) findViewById(R.id.profile_photo);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);

        ImageView profileMenu = (ImageView) findViewById(R.id.profileMenu);

        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Navigating to Account Settings...");
                Intent intent = new Intent(mContext, AccountSettingsActivity.class);
                startActivity(intent);
            }
        });

    }
    /*
     * Bottom navigation view setup
     */
    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: settting up BottomNavigationViewEx");
        BottomNavigationViewEx navigationEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(navigationEx);
        BottomNavigationViewHelper.enableNavigation(mContext, navigationEx);
        Menu menu = navigationEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

}
