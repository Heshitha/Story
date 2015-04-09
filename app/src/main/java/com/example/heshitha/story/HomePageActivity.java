package com.example.heshitha.story;

import java.util.ArrayList;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.heshitha.story.adapters.NavDrawerListAdapter;
import com.example.heshitha.story.beanclasses.NavDrawerItem;
import com.example.heshitha.story.common.CommonDataHolder;
import com.example.heshitha.story.common.HomePageMenuItem;
import com.example.heshitha.story.common.MessageBox;
import com.example.heshitha.story.common.RoundImage;


public class HomePageActivity extends Activity {
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView lstSliderMenu;
    RelativeLayout rlContentHolder;

    ImageButton imgBtnMenu;

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;

    private float lastTranslate = 0.0f;

    ImageView imgMenuProfileImage;
    TextView txtProfileName;
    TextView txtUserEmail;
    Button btnSignOut;
    Button btnSignIn;
    FrameLayout flSignOutHolder;
    FrameLayout flSignInHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mTitle = mDrawerTitle = getTitle();

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (RelativeLayout)findViewById(R.id.rlMenuHolder);
        lstSliderMenu = (ListView) findViewById(R.id.list_slidermenu);
        rlContentHolder = (RelativeLayout)findViewById(R.id.rlContentHolder);

        imgBtnMenu = (ImageButton)findViewById(R.id.imgBtnMenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Find People
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Photos
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Communities, Will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        // Pages
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));


        // Recycle the typed array
        navMenuIcons.recycle();

        lstSliderMenu.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        lstSliderMenu.setAdapter(adapter);

        // enabling action bar app icon and behaving it as toggle button
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);



        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                //getSupportActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                //invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                //getSupportActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                //invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);

                float moveFactor = (mDrawerList.getWidth() * slideOffset);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
                    rlContentHolder.setTranslationX(moveFactor);
                }
                else{
                    TranslateAnimation anim = new TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f);
                    anim.setDuration(0);
                    anim.setFillAfter(true);
                    rlContentHolder.startAnimation(anim);

                    lastTranslate = moveFactor;
                }

            }
        };
        //mDrawerLayout.setDrawerListener(mDrawerToggle);

        loadFirstView(savedInstanceState);


        imgBtnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        });

        imgMenuProfileImage = (ImageView)findViewById(R.id.imgMenuProfileImage);
        txtProfileName = (TextView)findViewById(R.id.txtProfileName);
        txtUserEmail = (TextView)findViewById(R.id.txtUserEmail);
        btnSignOut = (Button)findViewById(R.id.btnSignOut);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        flSignOutHolder = (FrameLayout)findViewById(R.id.flSignOutHolder);
        flSignInHolder = (FrameLayout)findViewById(R.id.flSignInHolder);

        if(CommonDataHolder.LoggedUser != null){
            imgMenuProfileImage.setImageDrawable(new RoundImage(CommonDataHolder.LoggedUser.getImage()));
            txtProfileName.setText(CommonDataHolder.LoggedUser.getName());
            txtUserEmail.setText(CommonDataHolder.LoggedUser.getEmail());
            flSignInHolder.setVisibility(View.GONE);
            flSignOutHolder.setVisibility(View.VISIBLE);
        }else{
            imgMenuProfileImage.setImageResource(R.drawable.anonymousicon);
            txtProfileName.setText("Anonymous Account");
            txtUserEmail.setText("");
            flSignInHolder.setVisibility(View.VISIBLE);
            flSignOutHolder.setVisibility(View.GONE);
        }
        txtProfileName.setTypeface(CommonDataHolder.RaleWayLight);
        txtUserEmail.setTypeface(CommonDataHolder.LatoRegular);
        btnSignIn.setTypeface(CommonDataHolder.RaleWay);
        btnSignOut.setTypeface(CommonDataHolder.RaleWay);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePageActivity.this, SignInActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences userDetails = getSharedPreferences(CommonDataHolder.PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = userDetails.edit();
                editor.clear();
                editor.commit();

                CommonDataHolder.LoggedUser = null;

                Intent i = new Intent(HomePageActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void loadFirstView(Bundle savedInstanceState) {
        try{
            Intent myIntent = getIntent();
            HomePageMenuItem result = (HomePageMenuItem)myIntent.getSerializableExtra(CommonDataHolder.HomePageMenuItem);

            if(result == HomePageMenuItem.Reviews){
                displayView(0);
            } else if(result == HomePageMenuItem.Category){
                displayView(1);
            } else if(result == HomePageMenuItem.Profile){
                displayView(2);
            } else if(result == HomePageMenuItem.WriteStory){
                displayView(3);
            } else if(result == HomePageMenuItem.Settings){
                displayView(4);
            }

        }catch(Exception ex){
            if (savedInstanceState == null) {
                // on first time display view for first nav item
                displayView(0);
            }
        }
    }

    /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* *
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     * */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                if(checkIfLoggedIn())
                    fragment = new ReviewsFragment();
                break;
            case 1:
                fragment = new CategoryFragment();
                break;
            case 2:
                if(checkIfLoggedIn())
                    fragment = new HomeFragment();
                break;
            case 3:
                fragment = new WriteStoryFragment();
                break;
            case 4:
                if(checkIfLoggedIn())
                    fragment = new SettingsFragment();
                break;
            case 5:
                if(checkIfLoggedIn())
                    fragment = new ProfileFragment();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            lstSliderMenu.setItemChecked(position, true);
            lstSliderMenu.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    public boolean checkIfLoggedIn(){
        if(CommonDataHolder.LoggedUser != null){
            return true;
        }
        else{
            mDrawerLayout.closeDrawer(mDrawerList);
            MessageBox.ShowLoginBox(HomePageActivity.this);
            //loadFirstView(null);
            return false;
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        //getSupportActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

}
