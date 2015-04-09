package com.example.heshitha.story;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.example.heshitha.story.common.CommonDataHolder;


public class SplashActivity extends Activity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        CommonDataHolder.raavi                  = Typeface.createFromAsset(getAssets(), "raavi.ttf");
        CommonDataHolder.RaleWay                = Typeface.createFromAsset(getAssets(), "Raleway-Regular.ttf");
        CommonDataHolder.LatoLight              = Typeface.createFromAsset(getAssets(), "Lato-Light.ttf");
        CommonDataHolder.timesNewRomen          = Typeface.createFromAsset(getAssets(), "Times-New-Roman.ttf");
        CommonDataHolder.timesNewRomenBold      = Typeface.createFromAsset(getAssets(), "Times-New-Roman-Bold.ttf");
        CommonDataHolder.helviticaNeue          = Typeface.createFromAsset(getAssets(), "HelveticaNeue.ttf");
        CommonDataHolder.centurygothic          = Typeface.createFromAsset(getAssets(), "Century-Gothic.ttf");
        CommonDataHolder.LatoBold               = Typeface.createFromAsset(getAssets(), "Lato-Bold.ttf");
        CommonDataHolder.helviticaNeueLight     = Typeface.createFromAsset(getAssets(), "HELVETICANEUELIGHT.TTF");
        CommonDataHolder.RaleWayLight           = Typeface.createFromAsset(getAssets(), "Raleway-Light.ttf");
        CommonDataHolder.RaleWayMedium          = Typeface.createFromAsset(getAssets(), "Raleway-Medium.ttf");
        CommonDataHolder.HelveticaNeueLTSTDMD   = Typeface.createFromAsset(getAssets(), "HelveticaNeueLTSTDMD.OTF");
        CommonDataHolder.LatoRegular            = Typeface.createFromAsset(getAssets(), "Lato-Regular.ttf");
        CommonDataHolder.LucidaBright           = Typeface.createFromAsset(getAssets(), "Lucida-Bright.ttf");
        CommonDataHolder.HelveticaNeueBold      = Typeface.createFromAsset(getAssets(), "HelveticaNeueBold.ttf");
        CommonDataHolder.ChoplinMediumDemo      = Typeface.createFromAsset(getAssets(), "CHOPLIN-MEDIUM-DEMO.OTF");

        CommonDataHolder.SampleDefaultImage = BitmapFactory.decodeResource(getResources(), R.drawable.defaultimagefortestingpurpose);
        CommonDataHolder.DefaultUserImage = BitmapFactory.decodeResource(getResources(), R.drawable.userimageicon);
        CommonDataHolder.DefaultStoryImage = BitmapFactory.decodeResource(getResources(), R.drawable.story);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
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
