package com.example.heshitha.story;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.heshitha.story.common.CommonDataHolder;


public class MainWriteStoryActivity extends Activity {

    EditText txtStoryTitle;
    EditText txtStoryContent;
    ImageButton imgBtnPublishStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_write_story);

        txtStoryTitle = (EditText)findViewById(R.id.txtStoryTitle);
        txtStoryContent = (EditText)findViewById(R.id.txtStoryContent);

        imgBtnPublishStory = (ImageButton)findViewById(R.id.imgBtnPublishStory);

        txtStoryTitle.setTypeface(CommonDataHolder.timesNewRomenBold);
        txtStoryContent.setTypeface(CommonDataHolder.timesNewRomen);

        imgBtnPublishStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(MainWriteStoryActivity.this, PublishStoryActivity.class);
                //startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_write_story, menu);
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