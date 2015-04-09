package com.example.heshitha.story;


import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.heshitha.story.adapters.CategoryListViewAdapter;
import com.example.heshitha.story.beanclasses.Story_Category_Bean;
import com.example.heshitha.story.beanclasses.User_Bean;
import com.example.heshitha.story.common.CommonDataHolder;
import com.example.heshitha.story.common.ImageHelper;
import com.example.heshitha.story.common.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    ListView lstViewStoryCategories;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ThisView = inflater.inflate(R.layout.fragment_category, container, false);
        lstViewStoryCategories = (ListView)ThisView.findViewById(R.id.lstViewStoryCategories);



        return ThisView;
    }

    public class FillStoryCategoryList extends AsyncTask<String, String, String> {

        String jsonUrl = "http://www.ezcim.com/story/getcategories";

        JSONArray dataJsonArr = null;

        List<Story_Category_Bean> categoryList = new ArrayList<Story_Category_Bean>();

        ProgressDialog pDialog;

        @Override
        protected String doInBackground(String... params) {
            try{
                JsonParser jParser = new JsonParser();

                JSONObject json = jParser.getJSONFromUrl(jsonUrl);

                dataJsonArr = json.getJSONArray("Content");

                categoryList = new ArrayList<Story_Category_Bean>();

                for(int i = 0; i < dataJsonArr.length(); i++){
                    JSONObject c = dataJsonArr.getJSONObject(i);

                    int id = c.getInt("Id");
                    String name = c.getString("Name");
                    int count = c.getInt("Count");
                    String imageName = c.getString("ImageName");
                    String language = c.getString("Language");

                    Story_Category_Bean category_bean = new Story_Category_Bean();
                    category_bean.setId(id);
                    category_bean.setName(name);
                    category_bean.setCount(count);
                    category_bean.setImageName(imageName);
                    category_bean.setLanguage(language);

                    try {
                        String ImageUrl = "http://www.assets.ezcim.com/story/categorypics/categorypic105/" + imageName;
                        URL url = new URL(ImageUrl);
                        Bitmap categoryImage = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        category_bean.setImage(categoryImage);
                    }catch(Exception ex){
                        category_bean.setImage(ImageHelper.getRoundedCornerBitmap(CommonDataHolder.SampleDefaultImage, 100));
                    }

                    categoryList.add(category_bean);
                }

                SharedPreferences userDetails = getActivity().getSharedPreferences(CommonDataHolder.PREFS_NAME, getActivity().MODE_PRIVATE);
                int userID = userDetails.getInt("Id", 0);
                Log.d("UserID", String.valueOf(userID));

                if(userID != 0){
                    User_Bean usrBean = new User_Bean();
                    usrBean.setId(userID);
                    usrBean.setName(userDetails.getString("Name", ""));
                    usrBean.setEmail(userDetails.getString("Email", ""));
                    usrBean.setPhoneNumber(userDetails.getString("PhoneNumber", ""));
                    usrBean.setPassword(userDetails.getString("Password", ""));
                    usrBean.setImageName(userDetails.getString("ImageName", ""));
                    usrBean.setStatus(userDetails.getInt("Status", 0));

                    String ImageUrl = "http://www.assets.ezcim.com/story/userpics/profilepic/" + usrBean.getImageName();
                    URL url = new URL(ImageUrl);
                    Bitmap userProfileImage = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    usrBean.setImage(userProfileImage);

                    CommonDataHolder.LoggedUser = usrBean;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();

        }

        @Override
        protected void onPostExecute(String strFromDoInBg){
            try {
                Thread.sleep(1000);

                pDialog.dismiss();

                if(categoryList.size() > 0){
                    CommonDataHolder.lstStoryCategories = categoryList;

                    CategoryListViewAdapter adap = new CategoryListViewAdapter(getActivity(), R.layout.story_category_item, CommonDataHolder.lstStoryCategories);
                    lstViewStoryCategories.setAdapter(adap);

                    lstViewStoryCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            int storyCategoryID = Integer.parseInt((String)((TextView)view.findViewById(R.id.txtCategoryId)).getText());

                            //Intent intent = new Intent(StoryCatagoryListActivity.this, StoryListActivity.class);
                            //intent.putExtra("storyCategoryId", storyCategoryID);
                            //startActivity(intent);

                            Bundle bundle = new Bundle();
                            bundle.putInt("CategoryID", storyCategoryID);

                            Fragment storyListFragment = new StoryListFragment();
                            storyListFragment.setArguments(bundle);

                            FragmentTransaction transaction = getFragmentManager().beginTransaction();

                            transaction.replace(R.id.frame_container, storyListFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();

                        }
                    });

                }
                else{
                    CommonDataHolder.lstStoryCategories = new ArrayList<Story_Category_Bean>();

                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                    dialogBuilder.setTitle("Warning!");
                    dialogBuilder.setMessage("Oops! Something went wrong. Please check your data connection.");
                    dialogBuilder.setPositiveButton("OK", null);
                    AlertDialog dialog = dialogBuilder.create();
                    dialog.show();
                }



            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new FillStoryCategoryList().execute();
    }
}
