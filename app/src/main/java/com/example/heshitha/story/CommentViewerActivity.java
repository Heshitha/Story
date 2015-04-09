package com.example.heshitha.story;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.heshitha.story.adapters.CommentAdapter;
import com.example.heshitha.story.beanclasses.Comment_Bean;
import com.example.heshitha.story.common.CommonDataHolder;

import java.util.ArrayList;
import java.util.List;


public class CommentViewerActivity extends Activity {


    TextView txtReviewsTitle;

    ListView lstViewCommentList;

    Button btnShowMoreRecommends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_viewer);

        txtReviewsTitle = (TextView)findViewById(R.id.txtReviewsTitle);
        lstViewCommentList = (ListView)findViewById(R.id.lstViewCommentList);
        btnShowMoreRecommends = (Button)findViewById(R.id.btnShowMoreRecommends);

        txtReviewsTitle.setTypeface(CommonDataHolder.HelveticaNeueBold);
        btnShowMoreRecommends.setTypeface(CommonDataHolder.RaleWay);

        List<Comment_Bean> lstComments = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            lstComments.add(new Comment_Bean());
        }

        CommentAdapter adapter = new CommentAdapter(CommentViewerActivity.this, R.layout.story_comment_item, lstComments);
        lstViewCommentList.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_comment_viewer, menu);
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
