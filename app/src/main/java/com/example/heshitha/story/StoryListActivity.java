package com.example.heshitha.story;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.heshitha.story.adapters.StoryListAdapter;
import com.example.heshitha.story.asynctasks.LoadStoryList;
import com.example.heshitha.story.common.CommonDataHolder;
import com.example.heshitha.story.common.RoundImage;


public class StoryListActivity extends Activity {

    GridView gridView;
    TextView txtCategoryTitle;
    RelativeLayout rlEmptyCategory;

    TextView txtDescription;
    TextView btnWriteStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_list);

        Intent intent = getIntent();
        int categoryID = Integer.parseInt(intent.getStringExtra("storyCategoryId"));

        txtCategoryTitle = (TextView)findViewById(R.id.txtCategoryTitle);
        rlEmptyCategory = (RelativeLayout)findViewById(R.id.rlEmptyCategoryWarning);
        gridView = (GridView)findViewById(R.id.gridView);
        LoadStoryList loadStoryList = new LoadStoryList(categoryID, StoryListActivity.this, gridView, null, rlEmptyCategory);
        loadStoryList.execute();

        txtCategoryTitle.setTypeface(CommonDataHolder.LatoLight);

        txtDescription = (TextView)findViewById(R.id.txtDescription);
        btnWriteStory = (TextView)findViewById(R.id.btnWriteStory);

        txtDescription.setTypeface(CommonDataHolder.LatoLight);
        btnWriteStory.setTypeface(CommonDataHolder.RaleWay);

        btnWriteStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowSelectAuthorType();
            }
        });
    }

    public void ShowSelectAuthorType(){
        try{
            Dialog dialog = new Dialog(StoryListActivity.this);
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
                        Intent i = new Intent(StoryListActivity.this, SignInActivity.class);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(StoryListActivity.this, HomePageActivity.class);
                        i.putExtra("MyProfile", true);
                        startActivity(i);
                    }
                }
            };

            View.OnClickListener GoToAnonymousListener = new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent i = new Intent(StoryListActivity.this, MainWriteStoryActivity.class);
                    i.putExtra("AnonymousAccount", true);
                    startActivity(i);
                }
            };

            imgAnonymousAuthor.setOnClickListener(GoToAnonymousListener);
            btnAnonymousAuthor.setOnClickListener(GoToAnonymousListener);

            imgStoryAccount.setOnClickListener(GoToStoryAccountListener);
            btnStoryAccount.setOnClickListener(GoToStoryAccountListener);

            dialog.setCanceledOnTouchOutside(true);

            Activity creatingContextActivity = StoryListActivity.this;

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_story_list, menu);
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
