package com.example.heshitha.story;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.heshitha.story.common.CommonDataHolder;
import com.example.heshitha.story.common.RoundImage;


public class ReadStoryActivity extends Activity {

    ImageButton imgBtnCaregoryList;
    ImageButton imgBtnWriteStory;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_story);

        imgBtnCaregoryList = (ImageButton)findViewById(R.id.imgBtnCategoryList);
        imgBtnWriteStory = (ImageButton)findViewById(R.id.imgBtnWriteStory);

        txtStoryTitle = (TextView)findViewById(R.id.txtStoryTitle);
        txtByText = (TextView)findViewById(R.id.txtByText);
        txtAuthorName = (TextView)findViewById(R.id.txtAuthorName);
        txtAtText = (TextView)findViewById(R.id.txtAtText);
        txtDateText = (TextView)findViewById(R.id.txtDateText);
        txtStoryContent = (TextView)findViewById(R.id.txtStoryContent);
        txtRecommendedFirstUser = (TextView)findViewById(R.id.txtRecommendedFirstUser);
        txtRecommendedAndText = (TextView)findViewById(R.id.txtRecommendedAndText);
        txtRecommendedNumberOfUsers = (TextView)findViewById(R.id.txtRecommendedNumberOfUsers);
        txtRecommendedText = (TextView)findViewById(R.id.txtRecommendedText);
        txtHaveAThoughtText = (TextView)findViewById(R.id.txtHaveAThoughtText);

        btnAddToFavourite = (Button)findViewById(R.id.btnAddToFavourite);
        btnReviewStory = (Button)findViewById(R.id.btnReviewStory);
        btnBookmark = (Button)findViewById(R.id.btnBookmark);
        btnFlag = (Button)findViewById(R.id.btnFlag);
        btnViewAllReviews = (Button)findViewById(R.id.btnViewAllReviews);
        btnWriteYourResponse = (Button)findViewById(R.id.btnWriteYourResponse);

        llViewComments = (LinearLayout)findViewById(R.id.llViewComments);

        txtStoryTitle.setTypeface(CommonDataHolder.timesNewRomenBold);
        txtByText.setTypeface(CommonDataHolder.helviticaNeue);
        txtAuthorName.setTypeface(CommonDataHolder.helviticaNeue);
        txtAtText.setTypeface(CommonDataHolder.helviticaNeue);
        txtDateText.setTypeface(CommonDataHolder.helviticaNeue);
        txtStoryContent.setTypeface(CommonDataHolder.LucidaBright);
        txtRecommendedFirstUser.setTypeface(CommonDataHolder.helviticaNeue);
        txtRecommendedAndText.setTypeface(CommonDataHolder.helviticaNeue);
        txtRecommendedNumberOfUsers.setTypeface(CommonDataHolder.helviticaNeue);
        txtRecommendedText.setTypeface(CommonDataHolder.helviticaNeue);
        txtHaveAThoughtText.setTypeface(CommonDataHolder.helviticaNeue);

        btnAddToFavourite.setTypeface(CommonDataHolder.ChoplinMediumDemo);
        btnReviewStory.setTypeface(CommonDataHolder.ChoplinMediumDemo);
        btnBookmark.setTypeface(CommonDataHolder.ChoplinMediumDemo);
        btnFlag.setTypeface(CommonDataHolder.ChoplinMediumDemo);
        btnViewAllReviews.setTypeface(CommonDataHolder.RaleWay);
        btnWriteYourResponse.setTypeface(CommonDataHolder.RaleWay);

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        btnViewAllReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReadStoryActivity.this, CommentViewerActivity.class);
                startActivity(i);
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
                Intent i = new Intent(ReadStoryActivity.this, WriteAReviewActivity.class);
                startActivity(i);
            }
        });

        imgBtnCaregoryList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReadStoryActivity.this, StoryCategoryListActivity.class);
                startActivity(i);
                finish();
            }
        });

        imgBtnWriteStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowSelectAuthorType();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_read_story, menu);
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

    public void ShowSelectAuthorType(){
        try{
            Dialog dialog = new Dialog(ReadStoryActivity.this);
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
                        Intent i = new Intent(ReadStoryActivity.this, SignInActivity.class);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(ReadStoryActivity.this, HomePageActivity.class);
                        i.putExtra("MyProfile", true);
                        startActivity(i);
                    }
                }
            };

            View.OnClickListener GoToAnonymousListener = new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ReadStoryActivity.this, MainWriteStoryActivity.class);
                    i.putExtra("AnonymousAccount", true);
                    startActivity(i);
                }
            };

            imgAnonymousAuthor.setOnClickListener(GoToAnonymousListener);
            btnAnonymousAuthor.setOnClickListener(GoToAnonymousListener);

            imgStoryAccount.setOnClickListener(GoToStoryAccountListener);
            btnStoryAccount.setOnClickListener(GoToStoryAccountListener);

            dialog.setCanceledOnTouchOutside(true);

            Activity creatingContextActivity = ReadStoryActivity.this;

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
