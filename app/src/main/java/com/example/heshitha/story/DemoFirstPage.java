package com.example.heshitha.story;


import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heshitha.story.common.CommonDataHolder;
import com.example.heshitha.story.common.RoundImage;


public class DemoFirstPage extends Fragment {

    ImageView imgProfileImage;
    TextView txtName;
    TextView txtWelcomeMessage;



    public static Fragment newInstance(Context context){
        DemoFirstPage demoPage = new DemoFirstPage();
        return demoPage;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_demo_first_page, null);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgProfileImage = (ImageView)getView().findViewById(R.id.imgProfileImage);
        txtName = (TextView)getView().findViewById(R.id.txtName);
        txtWelcomeMessage = (TextView)getView().findViewById(R.id.txtWelcomeMessage);

        try{
            imgProfileImage.setImageDrawable(new RoundImage(CommonDataHolder.LoggedUser.getImage()));
            txtName.setText(CommonDataHolder.LoggedUser.getName());
        }catch (Exception ex){
            imgProfileImage.setImageDrawable(new RoundImage(CommonDataHolder.SampleDefaultImage));
            txtName.setText("Sunny Leone");
        }
        txtName.setTypeface(CommonDataHolder.LatoLight);
        txtWelcomeMessage.setTypeface(CommonDataHolder.LatoLight);
    }
}
