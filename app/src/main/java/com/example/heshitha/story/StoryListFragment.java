package com.example.heshitha.story;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.heshitha.story.asynctasks.LoadStoryList;
import com.example.heshitha.story.common.CommonDataHolder;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoryListFragment extends Fragment {

    GridView gridView;
    RelativeLayout rlEmptyCategory;

    TextView txtDescription;
    TextView btnWriteStory;

    public StoryListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ThisView = inflater.inflate(R.layout.fragment_story_list, container, false);

        int categoryID = getArguments().getInt("CategoryID");

        gridView = (GridView)ThisView.findViewById(R.id.gridView);
        rlEmptyCategory = (RelativeLayout)ThisView.findViewById(R.id.rlEmptyCategoryWarning);
        LoadStoryList loadStoryList = new LoadStoryList(categoryID, getActivity(), gridView, this, rlEmptyCategory);
        loadStoryList.execute();

        txtDescription = (TextView)ThisView.findViewById(R.id.txtDescription);
        btnWriteStory = (TextView)ThisView.findViewById(R.id.btnWriteStory);

        txtDescription.setTypeface(CommonDataHolder.LatoLight);
        btnWriteStory.setTypeface(CommonDataHolder.RaleWay);

        btnWriteStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new WriteStoryFragment();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.frame_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return ThisView;
    }



}
