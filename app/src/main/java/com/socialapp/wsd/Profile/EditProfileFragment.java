package com.socialapp.wsd.Profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.socialapp.wsd.R;
import com.socialapp.wsd.Utils.UniversalImageLoader;

public class EditProfileFragment extends Fragment {
    private static final String TAG = "EditProfileFragment";
    private ImageView mprofilePhoto;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editprofile, container, false);
        mprofilePhoto = (ImageView) view.findViewById(R.id.profile_photo);


        setProfileImage();

        //back arrow for navigating back to "Profile Acitivity"
        ImageView backArrow = (ImageView) view.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Navigating back to Prifile Activity");
                getActivity().finish();
            }
        });

        return view;
    }



    private void setProfileImage() {
        Log.d(TAG, "setProfileImage: setting profile image.");
        String imageURL = "http://e.hiphotos.baidu.com/zhidao/pic/item/f7246b600c3387440f9940625d0fd9f9d72aa054.jpg";
        UniversalImageLoader.setImage(imageURL, mprofilePhoto, null, "");
    }
}
