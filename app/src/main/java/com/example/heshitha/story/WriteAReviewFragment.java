package com.example.heshitha.story;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heshitha.story.asynctasks.SaveReview;
import com.example.heshitha.story.beanclasses.Comment_Bean;
import com.example.heshitha.story.common.CommonDataHolder;
import com.example.heshitha.story.common.MessageBox;
import com.example.heshitha.story.common.MessageBoxType;


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

                    Toast.makeText(getActivity(), response + " with " + rating +" ratings", Toast.LENGTH_LONG).show();

                    Comment_Bean comment_bean =  new Comment_Bean();
                    comment_bean.setUser(CommonDataHolder.LoggedUser);
                    comment_bean.setStory(CommonDataHolder.selectedStoryBean);
                    comment_bean.setComment(response);
                    comment_bean.setRate(rating);

                    SaveReview review = new SaveReview(comment_bean, getActivity());
                    review.execute();
                }
            });

        }catch (Exception ex){
            MessageBox.ShowMessageBox(getActivity(), "Error occurred while processing.", MessageBoxType.ERROR, false, null);
        }

        return ThisView;
    }



}
