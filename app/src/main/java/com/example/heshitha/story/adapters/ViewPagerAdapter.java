package com.example.heshitha.story.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.heshitha.story.DemoFirstPage;
import com.example.heshitha.story.DemoSecondPage;
import com.example.heshitha.story.DemoThirdPage;

/**
 * Created by Heshitha on 2/26/2015.
 */


public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public ViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new Fragment();
        switch (position){
            case 0:
                fragment = DemoFirstPage.newInstance(context);
                break;
            case 1:
                fragment = DemoSecondPage.newInstance(context);
                break;
            case 2:
                fragment = DemoThirdPage.newInstance(context);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
