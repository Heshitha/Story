package com.example.heshitha.story;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.heshitha.story.adapters.CommentAdapter;
import com.example.heshitha.story.beanclasses.Comment_Bean;
import com.example.heshitha.story.common.CommonDataHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewsFragment extends Fragment {

    ListView lstViewCommentList;
    Button btnShowMoreRecommends;

    public ReviewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ThisView = inflater.inflate(R.layout.fragment_reviews, container, false);

        lstViewCommentList = (ListView)ThisView.findViewById(R.id.lstViewCommentList);
        btnShowMoreRecommends = (Button)ThisView.findViewById(R.id.btnShowMoreRecommends);

        btnShowMoreRecommends.setTypeface(CommonDataHolder.RaleWay);

        //List<Comment_Bean> lstComments = new ArrayList<>();
        //for(int i = 0; i < 10; i++){
        //    lstComments.add(new Comment_Bean());
        //}
//
        //CommentAdapter adapter = new CommentAdapter(getActivity(), R.layout.story_comment_item, lstComments);
        //lstViewCommentList.setAdapter(adapter);

        return  ThisView;
    }


}
