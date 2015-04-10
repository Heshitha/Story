package com.example.heshitha.story;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heshitha.story.asynctasks.SaveReview;
import com.example.heshitha.story.beanclasses.Comment_Bean;
import com.example.heshitha.story.beanclasses.User_Bean;
import com.example.heshitha.story.common.CommonDataHolder;
import com.example.heshitha.story.common.HomePageMenuItem;
import com.example.heshitha.story.common.MessageBox;
import com.example.heshitha.story.common.MessageBoxType;
import com.example.heshitha.story.common.RoundImage;


/**
 * A simple {@link Fragment} subclass.
 */
public class WriteAReviewFragment extends Fragment {

    TextView txtWrittenInResponseToText;
    TextView txtStoryTitle;
    TextView txtRatingLabel;
    RatingBar rbStoryRatingBar;
    EditText txtYourResponse;
    Button btnSave;

    public WriteAReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ThisView = inflater.inflate(R.layout.fragment_write_areview, container, false);

        txtWrittenInResponseToText = (TextView)ThisView.findViewById(R.id.txtWrittenInResponseToText);
        txtStoryTitle = (TextView)ThisView.findViewById(R.id.txtStoryTitle);
        txtRatingLabel = (TextView)ThisView.findViewById(R.id.txtRatingLabel);

        rbStoryRatingBar = (RatingBar)ThisView.findViewById(R.id.rbStoryRatingBar);

        txtYourResponse = (EditText)ThisView.findViewById(R.id.txtYourResponse);

        btnSave = (Button)ThisView.findViewById(R.id.btnSave);

        try{
            txtStoryTitle.setText(CommonDataHolder.selectedStoryBean.getTitle());

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String response = txtYourResponse.getText().toString();
                    float rating = rbStoryRatingBar.getRating();



                    Comment_Bean comment_bean =  new Comment_Bean();
                    comment_bean.setUser(CommonDataHolder.LoggedUser);
                    comment_bean.setStory(CommonDataHolder.selectedStoryBean);
                    comment_bean.setComment(response);
                    comment_bean.setRate(rating);

                    ShowSelectAuthorType(comment_bean);

                    //SaveReview review = new SaveReview(comment_bean, getActivity());
                    //review.execute();
                }
            });

        }catch (Exception ex){
            MessageBox.ShowMessageBox(getActivity(), "Error occurred while processing.", MessageBoxType.ERROR, false, null);
        }

        return ThisView;
    }

    public void ShowSelectAuthorType(Comment_Bean comment_bean){

        final Comment_Bean storyCommentBean = comment_bean;

        try{
            final Dialog dialog = new Dialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.story_write_author_selector);

            ImageView imgStoryAccount = (ImageView)dialog.findViewById(R.id.imgUserImage);
            ImageView imgAnonymousAuthor = (ImageView)dialog.findViewById(R.id.imgAnonymousImage);

            Button btnStoryAccount = (Button)dialog.findViewById(R.id.btnStoryAccount);
            Button btnAnonymousAuthor = (Button)dialog.findViewById(R.id.btnAnonymousAuthor);

            btnAnonymousAuthor.setTypeface(CommonDataHolder.LatoBold);
            btnStoryAccount.setTypeface(CommonDataHolder.LatoBold);

            try{
                if(CommonDataHolder.LoggedUser != null){
                    imgStoryAccount.setImageDrawable(new RoundImage(CommonDataHolder.LoggedUser.getImage()));
                }
            }catch (Exception ex){

            }


            View.OnClickListener GoToStoryAccountListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (CommonDataHolder.LoggedUser == null) {
                        Intent i = new Intent(getActivity(), SignInActivity.class);
                        startActivity(i);
                    } else {
                        SaveReview review = new SaveReview(storyCommentBean, getActivity(), WriteAReviewFragment.this);
                        review.execute();
                        dialog.dismiss();
                    }
                }
            };

            View.OnClickListener GoToAnonymousListener = new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    User_Bean anonymousUser = new User_Bean();
                    anonymousUser.setId(0);

                    storyCommentBean.setUser(anonymousUser);

                    SaveReview review = new SaveReview(storyCommentBean, getActivity(), WriteAReviewFragment.this);
                    review.execute();
                    dialog.dismiss();
                }
            };

            imgAnonymousAuthor.setOnClickListener(GoToAnonymousListener);
            btnAnonymousAuthor.setOnClickListener(GoToAnonymousListener);

            imgStoryAccount.setOnClickListener(GoToStoryAccountListener);
            btnStoryAccount.setOnClickListener(GoToStoryAccountListener);

            dialog.setCanceledOnTouchOutside(true);

            Activity creatingContextActivity = getActivity();

            Display display = creatingContextActivity.getWindowManager().getDefaultDisplay();

            WindowManager.LayoutParams lp;

            lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = (int) (display.getWidth() * 1);

            dialog.getWindow().setAttributes(lp);

            dialog.show();

        }catch (Exception ex){

        }
    }

}
