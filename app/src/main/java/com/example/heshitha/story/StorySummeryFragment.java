package com.example.heshitha.story;


import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.heshitha.story.beanclasses.Comment_Bean;
import com.example.heshitha.story.beanclasses.Story_Bean;
import com.example.heshitha.story.common.CommonDataHolder;
import com.example.heshitha.story.common.RoundImage;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static com.example.heshitha.story.common.CommonDataHolder.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class StorySummeryFragment extends Fragment {

    ImageButton imgBtnSettings;

    ImageView imgStoryCoverImage;

    TextView txtStoryTitle;
    TextView txtAuthorName;
    TextView txtRatings;
    TextView txtSummeryTitle;
    TextView txtSummeryText;
    TextView txtPublicationTitle;
    TextView txtRecommendedFirstUser;
    TextView txtRecommendedAndText;
    TextView txtRecommendedNumberOfUsers;
    TextView txtRecommendedText;
    TextView txtHaveAThoughtText;

    Button btnRead;
    Button btnViewAllReviews;
    Button btnWriteYourResponse;

    LinearLayout llPublishedStories;
    LinearLayout llViewComments;

    RatingBar rbStoryRatingBar;

    public StorySummeryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ThisView = inflater.inflate(R.layout.fragment_story_summery, container, false);


        imgBtnSettings = (ImageButton)ThisView.findViewById(R.id.imgBtnSettings);

        imgStoryCoverImage = (ImageView)ThisView.findViewById(R.id.imgStoryCoverImage);

        txtStoryTitle = (TextView)ThisView.findViewById(R.id.txtStoryTitle);
        txtAuthorName = (TextView)ThisView.findViewById(R.id.txtAuthorName);
        txtRatings = (TextView)ThisView.findViewById(R.id.txtRatings);
        txtSummeryTitle = (TextView)ThisView.findViewById(R.id.txtSummeryTitle);
        txtSummeryText = (TextView)ThisView.findViewById(R.id.txtSummeryText);
        txtPublicationTitle = (TextView)ThisView.findViewById(R.id.txtPublicationTitle);
        txtRecommendedFirstUser = (TextView)ThisView.findViewById(R.id.txtRecommendedFirstUser);
        txtRecommendedAndText = (TextView)ThisView.findViewById(R.id.txtRecommendedAndText);
        txtRecommendedNumberOfUsers = (TextView)ThisView.findViewById(R.id.txtRecommendedNumberOfUsers);
        txtRecommendedText = (TextView)ThisView.findViewById(R.id.txtRecommendedText);
        txtHaveAThoughtText = (TextView)ThisView.findViewById(R.id.txtHaveAThoughtText);

        btnRead = (Button)ThisView.findViewById(R.id.btnRead);
        btnViewAllReviews = (Button)ThisView.findViewById(R.id.btnViewAllReviews);
        btnWriteYourResponse = (Button)ThisView.findViewById(R.id.btnWriteYourResponse);

        llPublishedStories = (LinearLayout)ThisView.findViewById(R.id.llPublishedStories);
        llViewComments = (LinearLayout)ThisView.findViewById(R.id.llViewComments);

        rbStoryRatingBar = (RatingBar)ThisView.findViewById(R.id.rbStoryRatingBar);

        txtStoryTitle.setTypeface(HelveticaNeueBold);
        txtAuthorName.setTypeface(raavi);
        txtRatings.setTypeface(LatoRegular);
        txtSummeryTitle.setTypeface(LatoBold);
        txtSummeryText.setTypeface(LucidaBright);
        btnRead.setTypeface(RaleWay);
        txtPublicationTitle.setTypeface(LatoBold);
        btnViewAllReviews.setTypeface(LatoRegular);
        txtRecommendedFirstUser.setTypeface(helviticaNeue);
        txtRecommendedAndText.setTypeface(helviticaNeue);
        txtRecommendedNumberOfUsers.setTypeface(helviticaNeue);
        txtRecommendedText.setTypeface(helviticaNeue);
        txtHaveAThoughtText.setTypeface(helviticaNeue);
        btnWriteYourResponse.setTypeface(RaleWay);

        imgStoryCoverImage.setImageBitmap(selectedStoryBean.getImage());
        txtStoryTitle.setText(selectedStoryBean.getTitle());
        txtAuthorName.setText("By "+selectedStoryBean.getAuthor().getName());
        NumberFormat formatter = new DecimalFormat("#0.00");
        txtRatings.setText("Rating: " + formatter.format(selectedStoryBean.getRate()));
        rbStoryRatingBar.setRating((float)(Math.floor(selectedStoryBean.getRate())+0.5));

        txtSummeryText.setText(selectedStoryBean.getFullStory());

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

        for(int i = 0; i < 5; i++){
            View vi = inflater.inflate(R.layout.story_profile_item, null);

            ImageView imgStoryImage = (ImageView)vi.findViewById(R.id.imgStoryImage);
            TextView txtStoryTitle = (TextView)vi.findViewById(R.id.txtStoryTitle);
            TextView txtStoryAuthor = (TextView)vi.findViewById(R.id.txtStoryAuthor);
            TextView txtStoryRating = (TextView)vi.findViewById(R.id.txtStoryRatings);

            txtStoryTitle.setTypeface(helviticaNeueLight);
            txtStoryAuthor.setTypeface(helviticaNeueLight);
            txtStoryRating.setTypeface(helviticaNeue);

            llPublishedStories.addView(vi);
        }

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ReadStoryFragment();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.frame_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

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
                //Intent i = new Intent(StorySummeryActivity.this, UserProfileActivity.class);
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

    public class LoadStoryDetails extends AsyncTask<Story_Bean, Story_Bean, Story_Bean>{

        @Override
        protected Story_Bean doInBackground(Story_Bean... params) {
            return null;
        }
    }
}
