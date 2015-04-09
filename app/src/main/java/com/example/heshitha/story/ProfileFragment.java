package com.example.heshitha.story;


import android.app.ActivityGroup;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.heshitha.story.adapters.ProfileStoriesAdapter;
import com.example.heshitha.story.beanclasses.Author_Bean;
import com.example.heshitha.story.beanclasses.Story_Bean;
import com.example.heshitha.story.common.CommonDataHolder;
import com.example.heshitha.story.common.RoundImage;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment{

    ImageView imgProfileImage;
    TextView txtUserName;

    ListView lstViewPopularStories;
    ListView lstViewPublicationStories;
    ListView lstViewDraftsStories;
    ListView lstViewMyListStories;

    Button btnTabPopular;
    Button btnTabPublication;
    Button btnTabDrafts;
    Button btnTabMyList;

    RelativeLayout rlPopularStoriesHolder;
    RelativeLayout rlPublicationStoriesHolder;
    RelativeLayout rlDraftsStoriesHolder;
    RelativeLayout rlMyListStoriesHolder;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ThisView = inflater.inflate(R.layout.fragment_profile, container, false);

        List<Story_Bean> lstStories = new ArrayList<Story_Bean>();

        for(int i = 0; i < 5; i++){

            Author_Bean author_bean = new Author_Bean();
            author_bean.setName("J K Rolling");

            Story_Bean story_bean = new Story_Bean();
            story_bean.setTitle("Harry Potter");
            story_bean.setRate(4.5);
            story_bean.setAuthor(author_bean);

            lstStories.add(story_bean);
        }

        lstViewPopularStories = (ListView)ThisView.findViewById(R.id.lstViewPopularStories);
        lstViewPublicationStories = (ListView)ThisView.findViewById(R.id.lstViewPublicationStories);
        lstViewDraftsStories = (ListView)ThisView.findViewById(R.id.lstViewDraftsStories);
        lstViewMyListStories = (ListView)ThisView.findViewById(R.id.lstViewMyListStories);

        btnTabPopular = (Button)ThisView.findViewById(R.id.btnTabPopular);
        btnTabPublication = (Button)ThisView.findViewById(R.id.btnTabPublication);
        btnTabDrafts = (Button)ThisView.findViewById(R.id.btnTabDrafts);
        btnTabMyList = (Button)ThisView.findViewById(R.id.btnTabMyList);

        rlPopularStoriesHolder = (RelativeLayout)ThisView.findViewById(R.id.rlPopularStoriesHolder);
        rlPublicationStoriesHolder = (RelativeLayout)ThisView.findViewById(R.id.rlPublicationStoriesHolder);
        rlDraftsStoriesHolder = (RelativeLayout)ThisView.findViewById(R.id.rlDraftsStoriesHolder);
        rlMyListStoriesHolder = (RelativeLayout)ThisView.findViewById(R.id.rlMyListStoriesHolder);

        imgProfileImage = (ImageView)ThisView.findViewById(R.id.imgProfileImage);

        txtUserName = (TextView)ThisView.findViewById(R.id.txtUserName);

        txtUserName.setTypeface(CommonDataHolder.RaleWayLight);

        RoundImage roundImage = new RoundImage(CommonDataHolder.LoggedUser.getImage());
        imgProfileImage.setImageDrawable(roundImage);
        txtUserName.setText(CommonDataHolder.LoggedUser.getName());

        ProfileStoriesAdapter adap = new ProfileStoriesAdapter(getActivity(), R.layout.story_profile_item, lstStories);

        lstViewPopularStories.setAdapter(adap);
        lstViewPublicationStories.setAdapter(adap);
        lstViewDraftsStories.setAdapter(adap);
        lstViewMyListStories.setAdapter(adap);

        btnTabPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlPopularStoriesHolder.setVisibility(View.VISIBLE);
                rlPublicationStoriesHolder.setVisibility(View.GONE);
                rlDraftsStoriesHolder.setVisibility(View.GONE);
                rlMyListStoriesHolder.setVisibility(View.GONE);

                btnTabPopular.setTextColor(Color.parseColor("#3f9dd9"));
                btnTabPublication.setTextColor(Color.parseColor("#8b8e90"));
                btnTabDrafts.setTextColor(Color.parseColor("#8b8e90"));
                btnTabMyList.setTextColor(Color.parseColor("#8b8e90"));
            }
        });

        btnTabPublication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlPopularStoriesHolder.setVisibility(View.GONE);
                rlPublicationStoriesHolder.setVisibility(View.VISIBLE);
                rlDraftsStoriesHolder.setVisibility(View.GONE);
                rlMyListStoriesHolder.setVisibility(View.GONE);

                btnTabPopular.setTextColor(Color.parseColor("#8b8e90"));
                btnTabPublication.setTextColor(Color.parseColor("#3f9dd9"));
                btnTabDrafts.setTextColor(Color.parseColor("#8b8e90"));
                btnTabMyList.setTextColor(Color.parseColor("#8b8e90"));
            }
        });

        btnTabDrafts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlPopularStoriesHolder.setVisibility(View.GONE);
                rlPublicationStoriesHolder.setVisibility(View.GONE);
                rlDraftsStoriesHolder.setVisibility(View.VISIBLE);
                rlMyListStoriesHolder.setVisibility(View.GONE);

                btnTabPopular.setTextColor(Color.parseColor("#8b8e90"));
                btnTabPublication.setTextColor(Color.parseColor("#8b8e90"));
                btnTabDrafts.setTextColor(Color.parseColor("#3f9dd9"));
                btnTabMyList.setTextColor(Color.parseColor("#8b8e90"));
            }
        });

        btnTabMyList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlPopularStoriesHolder.setVisibility(View.GONE);
                rlPublicationStoriesHolder.setVisibility(View.GONE);
                rlDraftsStoriesHolder.setVisibility(View.GONE);
                rlMyListStoriesHolder.setVisibility(View.VISIBLE);

                btnTabPopular.setTextColor(Color.parseColor("#8b8e90"));
                btnTabPublication.setTextColor(Color.parseColor("#8b8e90"));
                btnTabDrafts.setTextColor(Color.parseColor("#8b8e90"));
                btnTabMyList.setTextColor(Color.parseColor("#3f9dd9"));
            }
        });

        return ThisView;
    }
}
