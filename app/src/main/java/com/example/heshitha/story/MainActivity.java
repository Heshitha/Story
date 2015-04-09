package com.example.heshitha.story;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heshitha.story.beanclasses.Story_Category_Bean;
import com.example.heshitha.story.beanclasses.User_Bean;
import com.example.heshitha.story.common.CommonDataHolder;
import com.example.heshitha.story.common.HomePageMenuItem;
import com.example.heshitha.story.common.ImageHelper;
import com.example.heshitha.story.common.JsonParser;
import com.example.heshitha.story.common.RoundImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


public class MainActivity extends Activity {


    TextView txtReadStory;
    TextView txtWriteStory;
    ImageButton imgBtnWriteStory;
    ImageButton imgBtnReadStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgBtnReadStory = (ImageButton)findViewById(R.id.imgButtonTapToRead);
        imgBtnWriteStory = (ImageButton)findViewById(R.id.imgButtonTapToWrite);
        txtReadStory = (TextView)findViewById(R.id.txtReadStory);
        txtWriteStory = (TextView)findViewById(R.id.txtWriteStory);

        txtReadStory.setTypeface(CommonDataHolder.raavi);
        txtWriteStory.setTypeface(CommonDataHolder.raavi);

        imgBtnReadStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HomePageActivity.class);
                i.putExtra(CommonDataHolder.GoToCategory, true);
                i.putExtra(CommonDataHolder.HomePageMenuItem, HomePageMenuItem.Category);
                startActivity(i);
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Dialog dialog = new Dialog(MainActivity.this);
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
                        Intent i = new Intent(MainActivity.this, SignInActivity.class);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(MainActivity.this, HomePageActivity.class);
                        i.putExtra(CommonDataHolder.StoryMyProfile, true);
                        i.putExtra(CommonDataHolder.HomePageMenuItem, HomePageMenuItem.WriteStory);
                        startActivity(i);
                    }
                }
            };

            View.OnClickListener GoToAnonymousListener = new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, HomePageActivity.class);
                    i.putExtra(CommonDataHolder.AnonymousAccountWriteStory, true);
                    i.putExtra(CommonDataHolder.HomePageMenuItem, HomePageMenuItem.WriteStory);
                    startActivity(i);
                }
            };

            imgAnonymousAuthor.setOnClickListener(GoToAnonymousListener);
            btnAnonymousAuthor.setOnClickListener(GoToAnonymousListener);

            imgStoryAccount.setOnClickListener(GoToStoryAccountListener);
            btnStoryAccount.setOnClickListener(GoToStoryAccountListener);

            dialog.setCanceledOnTouchOutside(true);

            Activity creatingContextActivity = MainActivity.this;

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
