package com.example.heshitha.story;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.heshitha.story.common.CommonDataHolder;
import com.example.heshitha.story.common.RoundImage;


public class StorySummeryActivity extends Activity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_summery);

        imgStoryCoverImage = (ImageView)findViewById(R.id.imgStoryCoverImage);

        txtStoryTitle = (TextView)findViewById(R.id.txtStoryTitle);
        txtAuthorName = (TextView)findViewById(R.id.txtAuthorName);
        txtRatings = (TextView)findViewById(R.id.txtRatings);
        txtSummeryTitle = (TextView)findViewById(R.id.txtSummeryTitle);
        txtSummeryText = (TextView)findViewById(R.id.txtSummeryText);
        txtPublicationTitle = (TextView)findViewById(R.id.txtPublicationTitle);
        txtRecommendedFirstUser = (TextView)findViewById(R.id.txtRecommendedFirstUser);
        txtRecommendedAndText = (TextView)findViewById(R.id.txtRecommendedAndText);
        txtRecommendedNumberOfUsers = (TextView)findViewById(R.id.txtRecommendedNumberOfUsers);
        txtRecommendedText = (TextView)findViewById(R.id.txtRecommendedText);
        txtHaveAThoughtText = (TextView)findViewById(R.id.txtHaveAThoughtText);

        btnRead = (Button)findViewById(R.id.btnRead);
        btnViewAllReviews = (Button)findViewById(R.id.btnViewAllReviews);
        btnWriteYourResponse = (Button)findViewById(R.id.btnWriteYourResponse);

        llPublishedStories = (LinearLayout)findViewById(R.id.llPublishedStories);
        llViewComments = (LinearLayout)findViewById(R.id.llViewComments);

        txtStoryTitle.setTypeface(CommonDataHolder.HelveticaNeueBold);
        txtAuthorName.setTypeface(CommonDataHolder.raavi);
        txtRatings.setTypeface(CommonDataHolder.LatoRegular);
        txtSummeryTitle.setTypeface(CommonDataHolder.LatoBold);
        txtSummeryText.setTypeface(CommonDataHolder.LucidaBright);
        btnRead.setTypeface(CommonDataHolder.RaleWay);
        txtPublicationTitle.setTypeface(CommonDataHolder.LatoBold);
        btnViewAllReviews.setTypeface(CommonDataHolder.LatoRegular);
        txtRecommendedFirstUser.setTypeface(CommonDataHolder.helviticaNeue);
        txtRecommendedAndText.setTypeface(CommonDataHolder.helviticaNeue);
        txtRecommendedNumberOfUsers.setTypeface(CommonDataHolder.helviticaNeue);
        txtRecommendedText.setTypeface(CommonDataHolder.helviticaNeue);
        txtHaveAThoughtText.setTypeface(CommonDataHolder.helviticaNeue);
        btnWriteYourResponse.setTypeface(CommonDataHolder.RaleWay);

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for(int i = 0; i < 5; i++){
            View vi = inflater.inflate(R.layout.story_profile_item, null);

            ImageView imgStoryImage = (ImageView)vi.findViewById(R.id.imgStoryImage);
            TextView txtStoryTitle = (TextView)vi.findViewById(R.id.txtStoryTitle);
            TextView txtStoryAuthor = (TextView)vi.findViewById(R.id.txtStoryAuthor);
            TextView txtStoryRating = (TextView)vi.findViewById(R.id.txtStoryRatings);

            txtStoryTitle.setTypeface(CommonDataHolder.helviticaNeueLight);
            txtStoryAuthor.setTypeface(CommonDataHolder.helviticaNeueLight);
            txtStoryRating.setTypeface(CommonDataHolder.helviticaNeue);

            llPublishedStories.addView(vi);
        }

        for(int i = 0; i < 3; i++){
            View vi = inflater.inflate(R.layout.story_comment_item, null);
            llViewComments.addView(vi);

            ImageView imgCommenterImage = (ImageView)vi.findViewById(R.id.imgCommenterImage);
            TextView txtCommenterName = (TextView)vi.findViewById(R.id.txtCommenterName);
            TextView txtCommentDate = (TextView)vi.findViewById(R.id.txtCommentDate);
            TextView txtCommentText = (TextView)vi.findViewById(R.id.txtCommentText);

            txtCommenterName.setTypeface(CommonDataHolder.HelveticaNeueBold);
            txtCommentDate.setTypeface(CommonDataHolder.helviticaNeue);
            txtCommentText.setTypeface(CommonDataHolder.helviticaNeueLight);

            imgCommenterImage.setImageDrawable(new RoundImage(CommonDataHolder.SampleDefaultImage));
        }

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StorySummeryActivity.this, ReadStoryActivity.class);
                startActivity(i);
            }
        });

        btnViewAllReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StorySummeryActivity.this, CommentViewerActivity.class);
                startActivity(i);
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
                Intent i = new Intent(StorySummeryActivity.this, WriteAReviewActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_story_summery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
