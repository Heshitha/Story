package com.example.heshitha.story;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.heshitha.story.adapters.CommentAdapter;
import com.example.heshitha.story.beanclasses.Comment_Bean;
import com.example.heshitha.story.common.CommonDataHolder;
import com.example.heshitha.story.common.MessageBox;
import com.example.heshitha.story.common.MessageBoxType;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommentViewerFragment extends Fragment {

    TextView txtReviewsTitle;

    ListView lstViewCommentList;

    Button btnShowMoreRecommends;

    public CommentViewerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ThisView = inflater.inflate(R.layout.fragment_comment_viewer, container, false);

        lstViewCommentList = (ListView)ThisView.findViewById(R.id.lstViewCommentList);
        btnShowMoreRecommends = (Button)ThisView.findViewById(R.id.btnShowMoreRecommends);
        txtReviewsTitle = (TextView)ThisView.findViewById(R.id.txtReviewsTitle);

        btnShowMoreRecommends.setTypeface(CommonDataHolder.RaleWay);

        try{

            CommentAdapter adapter = new CommentAdapter(getActivity(), R.layout.story_comment_item, CommonDataHolder.selectedStoryBean.getComments());
            lstViewCommentList.setAdapter(adapter);
            txtReviewsTitle.setText("Reviews for "+CommonDataHolder.selectedStoryBean.getTitle()+" ("+CommonDataHolder.selectedStoryBean.getTotalComments()+")");

        }catch (Exception ex){

            MessageBox.ShowMessageBox(getActivity(), "Error occurred while processing", MessageBoxType.ERROR, false, null);
        }

        return ThisView;
    }


}
