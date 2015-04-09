package com.example.heshitha.story;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.heshitha.story.common.CommonDataHolder;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemoSecondPage extends Fragment {

    TextView txtDemoText;

    public static Fragment newInstance(Context context) {
        DemoSecondPage demoPage = new DemoSecondPage();
        return demoPage;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_demo_second_page, null);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtDemoText = (TextView)getView().findViewById(R.id.txtDemoText);
        txtDemoText.setTypeface(CommonDataHolder.LatoLight);
    }
}
