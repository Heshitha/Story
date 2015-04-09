package com.example.heshitha.story;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.heshitha.story.adapters.CategoryListViewAdapter;
import com.example.heshitha.story.adapters.StoryListAdapter;
import com.example.heshitha.story.common.CommonDataHolder;


public class StoryCategoryListActivity extends Activity {

    TextView txtCategoryTitle;
    ListView lstViewStoryCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_category_list);

        lstViewStoryCategories = (ListView)findViewById(R.id.lstViewStoryCategories);

        txtCategoryTitle = (TextView)findViewById(R.id.txtCategoryTitle);


        CategoryListViewAdapter adap = new CategoryListViewAdapter(StoryCategoryListActivity.this, R.layout.story_category_item, CommonDataHolder.lstStoryCategories);
        lstViewStoryCategories.setAdapter(adap);

        lstViewStoryCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String storyCategoryID = (String) ((TextView)view.findViewById(R.id.txtCategoryId)).getText();

                Intent intent = new Intent(StoryCategoryListActivity.this, StoryListActivity.class);
                intent.putExtra("storyCategoryId", storyCategoryID);
                startActivity(intent);

            }
        });

        txtCategoryTitle.setTypeface(CommonDataHolder.LatoLight);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_story_category_list, menu);
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
