package com.example.heshitha.story;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.heshitha.story.common.CommonDataHolder;


/**
 * A simple {@link Fragment} subclass.
 */
public class WriteStoryFragment extends Fragment {

    TextView txtStoryTitle;
    TextView txtStoryContent;
    ImageButton imgBtnSaveStory;
    ImageButton imgBtnPublishStory;

    public WriteStoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ThisView = inflater.inflate(R.layout.fragment_write_story, container, false);

        txtStoryTitle = (TextView)ThisView.findViewById(R.id.txtStoryTitle);
        txtStoryContent = (TextView)ThisView.findViewById(R.id.txtStoryContent);

        imgBtnSaveStory = (ImageButton)ThisView.findViewById(R.id.imgBtnSaveStory);
        imgBtnPublishStory = (ImageButton)ThisView.findViewById(R.id.imgBtnPublishStory);

        txtStoryTitle.setTypeface(CommonDataHolder.timesNewRomenBold);
        txtStoryContent.setTypeface(CommonDataHolder.timesNewRomen);

        if(CommonDataHolder.LoggedUser == null){
            imgBtnSaveStory.setVisibility(View.GONE);
        }

        imgBtnPublishStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonDataHolder.writtenStoryBean.setFullStory(txtStoryContent.getText().toString());
                CommonDataHolder.writtenStoryBean.setTitle(txtStoryTitle.getText().toString());

                Fragment fragment = new PublishStoryFragment();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.frame_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return ThisView;
    }


}
