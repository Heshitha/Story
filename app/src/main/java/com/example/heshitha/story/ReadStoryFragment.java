package com.example.heshitha.story;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.heshitha.story.beanclasses.Comment_Bean;
import com.example.heshitha.story.common.CommonDataHolder;
import com.example.heshitha.story.common.RoundImage;

import static com.example.heshitha.story.common.CommonDataHolder.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadStoryFragment extends Fragment {

    ImageButton imgBtnSettings;

    TextView txtStoryTitle;
    TextView txtByText;
    TextView txtAuthorName;
    TextView txtAtText;
    TextView txtDateText;
    TextView txtStoryContent;
    TextView txtRecommendedFirstUser;
    TextView txtRecommendedAndText;
    TextView txtRecommendedNumberOfUsers;
    TextView txtRecommendedText;
    TextView txtHaveAThoughtText;

    Button btnAddToFavourite;
    Button btnReviewStory;
    Button btnBookmark;
    Button btnFlag;
    Button btnViewAllReviews;
    Button btnWriteYourResponse;

    LinearLayout llViewComments;

    public ReadStoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ThisView = inflater.inflate(R.layout.fragment_read_story, container, false);

        imgBtnSettings = (ImageButton)ThisView.findViewById(R.id.imgBtnSettings);

        txtStoryTitle = (TextView)ThisView.findViewById(R.id.txtStoryTitle);
        txtByText = (TextView)ThisView.findViewById(R.id.txtByText);
        txtAuthorName = (TextView)ThisView.findViewById(R.id.txtAuthorName);
        txtAtText = (TextView)ThisView.findViewById(R.id.txtAtText);
        txtDateText = (TextView)ThisView.findViewById(R.id.txtDateText);
        txtStoryContent = (TextView)ThisView.findViewById(R.id.txtStoryContent);
        txtRecommendedFirstUser = (TextView)ThisView.findViewById(R.id.txtRecommendedFirstUser);
        txtRecommendedAndText = (TextView)ThisView.findViewById(R.id.txtRecommendedAndText);
        txtRecommendedNumberOfUsers = (TextView)ThisView.findViewById(R.id.txtRecommendedNumberOfUsers);
        txtRecommendedText = (TextView)ThisView.findViewById(R.id.txtRecommendedText);
        txtHaveAThoughtText = (TextView)ThisView.findViewById(R.id.txtHaveAThoughtText);

        btnAddToFavourite = (Button)ThisView.findViewById(R.id.btnAddToFavourite);
        btnReviewStory = (Button)ThisView.findViewById(R.id.btnReviewStory);
        btnBookmark = (Button)ThisView.findViewById(R.id.btnBookmark);
        btnFlag = (Button)ThisView.findViewById(R.id.btnFlag);
        btnViewAllReviews = (Button)ThisView.findViewById(R.id.btnViewAllReviews);
        btnWriteYourResponse = (Button)ThisView.findViewById(R.id.btnWriteYourResponse);

        llViewComments = (LinearLayout)ThisView.findViewById(R.id.llViewComments);

        txtStoryTitle.setTypeface(timesNewRomenBold);
        txtByText.setTypeface(helviticaNeue);
        txtAuthorName.setTypeface(helviticaNeue);
        txtAtText.setTypeface(helviticaNeue);
        txtDateText.setTypeface(helviticaNeue);
        txtStoryContent.setTypeface(LucidaBright);
        txtRecommendedFirstUser.setTypeface(helviticaNeue);
        txtRecommendedAndText.setTypeface(helviticaNeue);
        txtRecommendedNumberOfUsers.setTypeface(helviticaNeue);
        txtRecommendedText.setTypeface(helviticaNeue);
        txtHaveAThoughtText.setTypeface(helviticaNeue);

        btnAddToFavourite.setTypeface(ChoplinMediumDemo);
        btnReviewStory.setTypeface(ChoplinMediumDemo);
        btnBookmark.setTypeface(ChoplinMediumDemo);
        btnFlag.setTypeface(ChoplinMediumDemo);
        btnViewAllReviews.setTypeface(RaleWay);
        btnWriteYourResponse.setTypeface(RaleWay);

        txtStoryTitle.setText(selectedStoryBean.getTitle());
        txtAuthorName.setText(selectedStoryBean.getAuthor().getName());
        txtDateText.setText(selectedStoryBean.getCreatedDate().toString());
        txtStoryContent.setText(selectedStoryBean.getFullStory());

        if(selectedStoryBean.getComments().size() > 0){
            Comment_Bean comment = selectedStoryBean.getComments().get(0);
            txtRecommendedFirstUser.setText(comment.getUser().getName());
            txtRecommendedNumberOfUsers.setText(String.valueOf(selectedStoryBean.getComments().size()-1) + " others");

            for(int i = 0; i < selectedStoryBean.getComments().size(); i++){
                View vi = inflater.inflate(R.layout.story_comment_item, null);
                llViewComments.addView(vi);

                ImageView imgCommenterImage = (ImageView)vi.findViewById(R.id.imgCommenterImage);
                TextView txtCommenterName = (TextView)vi.findViewById(R.id.txtCommenterName);
                TextView txtCommentDate = (TextView)vi.findViewById(R.id.txtCommentDate);
                TextView txtCommentText = (TextView)vi.findViewById(R.id.txtCommentText);

                txtCommenterName.setTypeface(HelveticaNeueBold);
                txtCommentDate.setTypeface(helviticaNeue);
                txtCommentText.setTypeface(helviticaNeueLight);

                comment = selectedStoryBean.getComments().get(i);

                imgCommenterImage.setImageDrawable(new RoundImage(comment.getUser().getImage()));
                txtCommenterName.setText(comment.getUser().getName());
                txtCommentText.setText(comment.getComment());
                txtCommentDate.setText(comment.getCreatedDate().toString());
            }
        }

        btnViewAllReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CommentViewerFragment();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.frame_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        txtAuthorName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(ReadStoryActivity.this, UserProfileActivity.class);
                //i.putExtra("MyProfile", false);
                //startActivity(i);
            }
        });

        btnWriteYourResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new WriteAReviewFragment();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.frame_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return ThisView;
    }


}
