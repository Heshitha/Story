package com.example.heshitha.story;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.heshitha.story.common.CommonDataHolder;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemoThirdPage extends Fragment {

    TextView txtTitle;
    TextView txtContent;

    public static Fragment newInstance(Context context){
        DemoThirdPage demoPage = new DemoThirdPage();
        return demoPage;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_demo_third_page, null);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtTitle = (TextView)getView().findViewById(R.id.txtTitle);
        txtContent = (TextView)getView().findViewById(R.id.txtContent);

        txtTitle.setTypeface(CommonDataHolder.LatoLight);
        txtContent.setTypeface(CommonDataHolder.LatoLight);
    }
}
