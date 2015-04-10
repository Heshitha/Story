package com.example.heshitha.story;


import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.heshitha.story.beanclasses.Author_Bean;
import com.example.heshitha.story.beanclasses.Comment_Bean;
import com.example.heshitha.story.beanclasses.Story_Bean;
import com.example.heshitha.story.beanclasses.Story_Category_Bean;
import com.example.heshitha.story.beanclasses.User_Bean;
import com.example.heshitha.story.common.CommonDataHolder;
import com.example.heshitha.story.common.JsonParser;
import com.example.heshitha.story.common.RoundImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.heshitha.story.common.CommonDataHolder.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class StorySummeryFragment extends Fragment {

    ImageButton imgBtnSettings;

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

    RatingBar rbStoryRatingBar;

    ScrollView scrStoryContent;

    public StorySummeryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ThisView = inflater.inflate(R.layout.fragment_story_summery, container, false);


        imgBtnSettings = (ImageButton)ThisView.findViewById(R.id.imgBtnSettings);

        imgStoryCoverImage = (ImageView)ThisView.findViewById(R.id.imgStoryCoverImage);

        txtStoryTitle = (TextView)ThisView.findViewById(R.id.txtStoryTitle);
        txtAuthorName = (TextView)ThisView.findViewById(R.id.txtAuthorName);
        txtRatings = (TextView)ThisView.findViewById(R.id.txtRatings);
        txtSummeryTitle = (TextView)ThisView.findViewById(R.id.txtSummeryTitle);
        txtSummeryText = (TextView)ThisView.findViewById(R.id.txtSummeryText);
        txtPublicationTitle = (TextView)ThisView.findViewById(R.id.txtPublicationTitle);
        txtRecommendedFirstUser = (TextView)ThisView.findViewById(R.id.txtRecommendedFirstUser);
        txtRecommendedAndText = (TextView)ThisView.findViewById(R.id.txtRecommendedAndText);
        txtRecommendedNumberOfUsers = (TextView)ThisView.findViewById(R.id.txtRecommendedNumberOfUsers);
        txtRecommendedText = (TextView)ThisView.findViewById(R.id.txtRecommendedText);
        txtHaveAThoughtText = (TextView)ThisView.findViewById(R.id.txtHaveAThoughtText);

        btnRead = (Button)ThisView.findViewById(R.id.btnRead);
        btnViewAllReviews = (Button)ThisView.findViewById(R.id.btnViewAllReviews);
        btnWriteYourResponse = (Button)ThisView.findViewById(R.id.btnWriteYourResponse);

        llPublishedStories = (LinearLayout)ThisView.findViewById(R.id.llPublishedStories);
        llViewComments = (LinearLayout)ThisView.findViewById(R.id.llViewComments);

        rbStoryRatingBar = (RatingBar)ThisView.findViewById(R.id.rbStoryRatingBar);
        scrStoryContent = (ScrollView)ThisView.findViewById(R.id.scrStoryContent);

        txtStoryTitle.setTypeface(HelveticaNeueBold);
        txtAuthorName.setTypeface(raavi);
        txtRatings.setTypeface(LatoRegular);
        txtSummeryTitle.setTypeface(LatoBold);
        txtSummeryText.setTypeface(LucidaBright);
        btnRead.setTypeface(RaleWay);
        txtPublicationTitle.setTypeface(LatoBold);
        btnViewAllReviews.setTypeface(LatoRegular);
        txtRecommendedFirstUser.setTypeface(helviticaNeue);
        txtRecommendedAndText.setTypeface(helviticaNeue);
        txtRecommendedNumberOfUsers.setTypeface(helviticaNeue);
        txtRecommendedText.setTypeface(helviticaNeue);
        txtHaveAThoughtText.setTypeface(helviticaNeue);
        btnWriteYourResponse.setTypeface(RaleWay);

        new LoadStoryDetails(getActivity(), selectedStoryBean, inflater).execute();

        return ThisView;
    }

    public void fillStorySummery(LayoutInflater inflater, List<Story_Bean> lstAuthorStories) {
        imgStoryCoverImage.setImageBitmap(selectedStoryBean.getImage());
        txtStoryTitle.setText(selectedStoryBean.getTitle());
        txtAuthorName.setText("By "+selectedStoryBean.getAuthor().getName());
        NumberFormat formatter = new DecimalFormat("#0.00");
        txtRatings.setText("Rating: " + formatter.format(selectedStoryBean.getRate()));
        rbStoryRatingBar.setRating((float)(Math.floor(selectedStoryBean.getRate())+0.5));

        txtSummeryText.setText(selectedStoryBean.getFullStory());

        if(selectedStoryBean.getComments().size() > 0){
            Comment_Bean comment = selectedStoryBean.getComments().get(0);
            txtRecommendedFirstUser.setText(comment.getUser().getName());
            txtRecommendedNumberOfUsers.setText(String.valueOf(selectedStoryBean.getTotalComments()) + " others");

            for(int i = 0; i < selectedStoryBean.getComments().size(); i++){
                View vi = inflater.inflate(R.layout.story_comment_item, null);
                llViewComments.addView(vi);

                ImageView imgCommenterImage = (ImageView)vi.findViewById(R.id.imgCommenterImage);
                TextView txtCommenterName = (TextView)vi.findViewById(R.id.txtCommenterName);
                TextView txtCommentDate = (TextView)vi.findViewById(R.id.txtCommentDate);
                TextView txtCommentText = (TextView)vi.findViewById(R.id.txtCommentText);

                txtCommenterName.setTypeface(HelveticaNeueBold);
                txtCommentDate.setTypeface(helviticaNeue);
                txtCommentText.setTypeface(helviticaNeueLight);

                comment = selectedStoryBean.getComments().get(i);

                imgCommenterImage.setImageDrawable(new RoundImage(comment.getUser().getImage()));
                txtCommenterName.setText(comment.getUser().getName());
                txtCommentText.setText(comment.getComment());
                txtCommentDate.setText(comment.getCreatedDate().toString());
            }
        }

        for(int i = 0; i < lstAuthorStories.size(); i++){
            View vi = inflater.inflate(R.layout.story_profile_item, null);

            ImageView imgStoryImage = (ImageView)vi.findViewById(R.id.imgStoryImage);
            TextView txtStoryTitle = (TextView)vi.findViewById(R.id.txtStoryTitle);
            TextView txtStoryAuthor = (TextView)vi.findViewById(R.id.txtStoryAuthor);
            TextView txtStoryRating = (TextView)vi.findViewById(R.id.txtStoryRatings);

            txtStoryTitle.setTypeface(helviticaNeueLight);
            txtStoryAuthor.setTypeface(helviticaNeueLight);
            txtStoryRating.setTypeface(helviticaNeue);

            imgStoryImage.setImageBitmap(lstAuthorStories.get(i).getImage());
            txtStoryTitle.setText(lstAuthorStories.get(i).getTitle());
            txtStoryAuthor.setText(lstAuthorStories.get(i).getAuthor().getName());
            txtStoryRating.setText(String.valueOf(lstAuthorStories.get(i).getRate()));

            llPublishedStories.addView(vi);
        }

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ReadStoryFragment();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.frame_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnViewAllReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CommentViewerFragment();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.frame_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
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
                Fragment fragment = new WriteAReviewFragment();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.frame_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        scrStoryContent.setVisibility(View.VISIBLE);
    }

    public class LoadStoryDetails extends AsyncTask<Story_Bean, Story_Bean, Story_Bean>{

        Context context;
        String jsonUrl = "";
        ProgressDialog pDialog;
        Story_Bean storyBean;
        LayoutInflater inflater;
        List<Story_Bean> lstStoriesByAuthor;

        public LoadStoryDetails(Context context, Story_Bean story_bean, LayoutInflater inflater) {
            this.context = context;
            this.storyBean = story_bean;
            jsonUrl = "http://www.ezcim.com/story/GetStory?StoryId="+story_bean.getStoryID();
            this.inflater = inflater;
            lstStoriesByAuthor = new ArrayList<Story_Bean>();
        }

        @Override
        protected Story_Bean doInBackground(Story_Bean... params) {

            try{
                JsonParser jParser = new JsonParser();

                JSONObject json = jParser.getJSONFromUrl(jsonUrl);

                JSONObject storyObject = json.getJSONObject("Content");

                JSONObject categoryObject = storyObject.getJSONObject("Category");
                Story_Category_Bean storyCategoryBean = new Story_Category_Bean();
                storyCategoryBean.setId(categoryObject.getInt("Id"));
                storyCategoryBean.setName(categoryObject.getString("Name"));
                storyCategoryBean.setImageName(categoryObject.getString("ImageName"));
                storyCategoryBean.setLanguage(categoryObject.getString("Language"));
                storyCategoryBean.setCount(categoryObject.getInt("Count"));

                JSONObject authorObject = storyObject.getJSONObject("Author");
                Author_Bean authorBean = new Author_Bean();
                authorBean.setId(authorObject.getInt("Id"));
                authorBean.setName(authorObject.getString("Name"));

                LoadStoriesByAuthor(authorBean.getId());

                storyBean = new Story_Bean();
                storyBean.setStoryID(storyObject.getInt("Id"));
                storyBean.setCategory(storyCategoryBean);
                storyBean.setAuthor(authorBean);
                storyBean.setTitle(storyObject.getString("Title"));
                storyBean.setSummery(storyObject.getString("Summery"));
                storyBean.setFullStory(storyObject.getString("FullStory"));
                storyBean.setRate(storyObject.getDouble("Rate"));
                storyBean.setImageName(storyObject.getString("ImageName"));
                storyBean.setLanguage(storyObject.getString("Language"));

                String ackwardDate = storyObject.getString("CreatedDate");
                Calendar calender = Calendar.getInstance();
                String ackwardRipOff = ackwardDate.replace("/Date(", "").replace(")/", "");
                Long timeInMillis = Long.valueOf(ackwardRipOff);
                calender.setTimeInMillis(timeInMillis);

                Date createdDate = calender.getTime();

                ackwardDate = storyObject.getString("ModifiedDate");
                calender = Calendar.getInstance();
                ackwardRipOff = ackwardDate.replace("/Date(", "").replace(")/", "");
                timeInMillis = Long.valueOf(ackwardRipOff);
                calender.setTimeInMillis(timeInMillis);

                Date modifiedDate = calender.getTime();

                storyBean.setCreatedDate(createdDate);
                storyBean.setModifiedDate(modifiedDate);

                storyBean.setTotalComments(storyObject.getInt("TotalComments"));

                storyBean.setComments(new ArrayList<Comment_Bean>());

                if(storyBean.getTotalComments() > 0){

                    JSONArray comments_array = storyObject.getJSONArray("Comments");

                    for(int j = 0; j < comments_array.length(); j++){
                        JSONObject comment = comments_array.getJSONObject(j);

                        JSONObject userObject = comment.getJSONObject("User");
                        User_Bean user = new User_Bean();
                        user.setId(userObject.getInt("Id"));
                        user.setName(userObject.getString("Name"));
                        user.setEmail(userObject.getString("Email"));
                        user.setImageName(userObject.getString("ImageName"));

                        try{
                            URL userImageUrl = new URL("http://www.assets.ezcim.com/story/userpics/profilepic/"+user.getImageName());
                            Bitmap userImageBmp = BitmapFactory.decodeStream(userImageUrl.openConnection().getInputStream());
                            user.setImage(userImageBmp);
                        }catch (Exception ex){
                            user.setImage(CommonDataHolder.DefaultUserImage);
                        }

                        Comment_Bean commentBean = new Comment_Bean();
                        commentBean.setId(comment.getInt("Id"));
                        commentBean.setUser(user);
                        commentBean.setStory(new Story_Bean());
                        commentBean.setComment(comment.getString("Comment"));
                        commentBean.setRate(comment.getDouble("Rate"));

                        ackwardDate = comment.getString("CreatedDate");
                        calender = Calendar.getInstance();
                        ackwardRipOff = ackwardDate.replace("/Date(", "").replace(")/", "");
                        timeInMillis = Long.valueOf(ackwardRipOff);
                        calender.setTimeInMillis(timeInMillis);

                        commentBean.setCreatedDate(calender.getTime());

                        ackwardDate = comment.getString("ModifiedDate");
                        calender = Calendar.getInstance();
                        ackwardRipOff = ackwardDate.replace("/Date(", "").replace(")/", "");
                        timeInMillis = Long.valueOf(ackwardRipOff);
                        calender.setTimeInMillis(timeInMillis);

                        commentBean.setModifiedDate(calender.getTime());

                        commentBean.setStatus(comment.getInt("Status"));

                        storyBean.getComments().add(commentBean);
                    }

                }

                //URL url = new URL("http://www.assets.ezcim.com/story/storypics/storypic/story.jpg");
                //Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                try{
                    URL storyImageUrl = new URL("http://www.assets.ezcim.com/story/storypics/storypic/"+storyBean.getImageName());
                    Bitmap storyImageBmp = BitmapFactory.decodeStream(storyImageUrl.openConnection().getInputStream());

                    storyBean.setImage(storyImageBmp);
                }catch (Exception ex){
                    storyBean.setImage(CommonDataHolder.DefaultStoryImage);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return storyBean;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Loading! Please Wait.");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(Story_Bean story_bean) {
            super.onPostExecute(story_bean);

            pDialog.dismiss();

            selectedStoryBean = storyBean;

            fillStorySummery(inflater, lstStoriesByAuthor);
        }

        private void LoadStoriesByAuthor(int authorID){

            try{
                String url = "http://www.ezcim.com/Story/SearchStories?AuthorId="+authorID;
                JsonParser jsonParser = new JsonParser();
                JSONObject json = jsonParser.getJSONFromUrl(url);
                JSONArray jsonArray = json.getJSONArray("Content");

                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject storyObject = jsonArray.getJSONObject(i);

                    JSONObject categoryObject = storyObject.getJSONObject("Category");
                    Story_Category_Bean storyCategoryBean = new Story_Category_Bean();
                    storyCategoryBean.setId(categoryObject.getInt("Id"));
                    storyCategoryBean.setName(categoryObject.getString("Name"));
                    storyCategoryBean.setImageName(categoryObject.getString("ImageName"));
                    storyCategoryBean.setLanguage(categoryObject.getString("Language"));
                    storyCategoryBean.setCount(categoryObject.getInt("Count"));

                    JSONObject authorObject = storyObject.getJSONObject("Author");
                    Author_Bean authorBean = new Author_Bean();
                    authorBean.setId(authorObject.getInt("Id"));
                    authorBean.setName(authorObject.getString("Name"));

                    Story_Bean storyBean = new Story_Bean();
                    storyBean.setStoryID(storyObject.getInt("Id"));
                    storyBean.setCategory(storyCategoryBean);
                    storyBean.setAuthor(authorBean);
                    storyBean.setTitle(storyObject.getString("Title"));
                    storyBean.setSummery(storyObject.getString("Summery"));
                    storyBean.setFullStory(storyObject.getString("FullStory"));
                    storyBean.setRate(storyObject.getDouble("Rate"));
                    storyBean.setImageName(storyObject.getString("ImageName"));
                    storyBean.setLanguage(storyObject.getString("Language"));

                    String ackwardDate = storyObject.getString("CreatedDate");
                    Calendar calender = Calendar.getInstance();
                    String ackwardRipOff = ackwardDate.replace("/Date(", "").replace(")/", "");
                    Long timeInMillis = Long.valueOf(ackwardRipOff);
                    calender.setTimeInMillis(timeInMillis);

                    Date createdDate = calender.getTime();

                    ackwardDate = storyObject.getString("ModifiedDate");
                    calender = Calendar.getInstance();
                    ackwardRipOff = ackwardDate.replace("/Date(", "").replace(")/", "");
                    timeInMillis = Long.valueOf(ackwardRipOff);
                    calender.setTimeInMillis(timeInMillis);

                    Date modifiedDate = calender.getTime();

                    storyBean.setCreatedDate(createdDate);
                    storyBean.setModifiedDate(modifiedDate);

                    storyBean.setTotalComments(storyObject.getInt("TotalComments"));

                    storyBean.setComments(new ArrayList<Comment_Bean>());

                    if(storyBean.getTotalComments() > 0){

                        JSONArray comments_array = storyObject.getJSONArray("Comments");

                        for(int j = 0; j < comments_array.length(); j++){
                            JSONObject comment = comments_array.getJSONObject(j);

                            JSONObject userObject = comment.getJSONObject("User");
                            User_Bean user = new User_Bean();
                            user.setId(userObject.getInt("Id"));
                            user.setName(userObject.getString("Name"));
                            user.setEmail(userObject.getString("Email"));
                            user.setImageName(userObject.getString("ImageName"));

                            try{
                                URL userImageUrl = new URL("http://www.assets.ezcim.com/story/userpics/profilepic/"+user.getImageName());
                                Bitmap userImageBmp = BitmapFactory.decodeStream(userImageUrl.openConnection().getInputStream());
                                user.setImage(userImageBmp);
                            }catch (Exception ex){
                                user.setImage(CommonDataHolder.DefaultUserImage);
                            }

                            Comment_Bean commentBean = new Comment_Bean();
                            commentBean.setId(comment.getInt("Id"));
                            commentBean.setUser(user);
                            commentBean.setStory(new Story_Bean());
                            commentBean.setComment(comment.getString("Comment"));
                            commentBean.setRate(comment.getDouble("Rate"));

                            ackwardDate = comment.getString("CreatedDate");
                            calender = Calendar.getInstance();
                            ackwardRipOff = ackwardDate.replace("/Date(", "").replace(")/", "");
                            timeInMillis = Long.valueOf(ackwardRipOff);
                            calender.setTimeInMillis(timeInMillis);

                            commentBean.setCreatedDate(calender.getTime());

                            ackwardDate = comment.getString("ModifiedDate");
                            calender = Calendar.getInstance();
                            ackwardRipOff = ackwardDate.replace("/Date(", "").replace(")/", "");
                            timeInMillis = Long.valueOf(ackwardRipOff);
                            calender.setTimeInMillis(timeInMillis);

                            commentBean.setModifiedDate(calender.getTime());

                            commentBean.setStatus(comment.getInt("Status"));

                            storyBean.getComments().add(commentBean);
                        }

                    }

                    //URL url = new URL("http://www.assets.ezcim.com/story/storypics/storypic/story.jpg");
                    //Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                    try{
                        URL storyImageUrl = new URL("http://www.assets.ezcim.com/story/storypics/storypic/"+storyBean.getImageName());
                        Bitmap storyImageBmp = BitmapFactory.decodeStream(storyImageUrl.openConnection().getInputStream());

                        storyBean.setImage(storyImageBmp);
                    }catch (Exception ex){
                        storyBean.setImage(CommonDataHolder.DefaultStoryImage);
                    }

                    lstStoriesByAuthor.add(storyBean);
                }

            }catch (Exception ex){

            }

        }

    }



}
