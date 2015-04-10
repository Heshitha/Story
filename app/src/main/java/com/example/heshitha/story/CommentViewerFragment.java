package com.example.heshitha.story;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.heshitha.story.adapters.CommentAdapter;
import com.example.heshitha.story.beanclasses.Comment_Bean;
import com.example.heshitha.story.beanclasses.Story_Bean;
import com.example.heshitha.story.beanclasses.User_Bean;
import com.example.heshitha.story.common.CommonDataHolder;
import com.example.heshitha.story.common.JsonParser;
import com.example.heshitha.story.common.MessageBox;
import com.example.heshitha.story.common.MessageBoxType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommentViewerFragment extends Fragment {

    TextView txtReviewsTitle;

    ListView lstViewCommentList;

    Button btnShowMoreRecommends;

    public CommentViewerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ThisView = inflater.inflate(R.layout.fragment_comment_viewer, container, false);

        lstViewCommentList = (ListView)ThisView.findViewById(R.id.lstViewCommentList);
        btnShowMoreRecommends = (Button)ThisView.findViewById(R.id.btnShowMoreRecommends);
        txtReviewsTitle = (TextView)ThisView.findViewById(R.id.txtReviewsTitle);

        btnShowMoreRecommends.setTypeface(CommonDataHolder.RaleWay);

        try{

            new LoadCommentsAsyncTask(getActivity(), CommonDataHolder.selectedStoryBean.getStoryID()).execute();
            txtReviewsTitle.setText("Reviews for "+CommonDataHolder.selectedStoryBean.getTitle()+" ("+CommonDataHolder.selectedStoryBean.getTotalComments()+")");

        }catch (Exception ex){

            MessageBox.ShowMessageBox(getActivity(), "Error occurred while processing", MessageBoxType.ERROR, false, null);
        }

        return ThisView;
    }

    public class LoadCommentsAsyncTask extends AsyncTask<Void, Void, Void>{

        Context context;
        int storyID;
        String jsonUrl = "";
        List<Comment_Bean> lstComments;
        ProgressDialog pDialog;

        public LoadCommentsAsyncTask(Context context, int storyID){
            this.context = context;
            this.storyID = storyID;
            jsonUrl = "http://www.ezcim.com/story/GetStoryReviews?StoryId="+storyID;
            lstComments = new ArrayList<Comment_Bean>();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                JsonParser jParser = new JsonParser();

                JSONObject json = jParser.getJSONFromUrl(jsonUrl);

                JSONArray dataJsonMainArr = json.getJSONArray("Content");

                for(int i = 0; i < dataJsonMainArr.length(); i++){
                    JSONObject comment = dataJsonMainArr.getJSONObject(i);

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

                    String ackwardDate = comment.getString("CreatedDate");
                    Calendar calender = Calendar.getInstance();
                    String ackwardRipOff = ackwardDate.replace("/Date(", "").replace(")/", "");
                    Long timeInMillis = Long.valueOf(ackwardRipOff);
                    calender.setTimeInMillis(timeInMillis);

                    commentBean.setCreatedDate(calender.getTime());

                    ackwardDate = comment.getString("ModifiedDate");
                    calender = Calendar.getInstance();
                    ackwardRipOff = ackwardDate.replace("/Date(", "").replace(")/", "");
                    timeInMillis = Long.valueOf(ackwardRipOff);
                    calender.setTimeInMillis(timeInMillis);

                    commentBean.setModifiedDate(calender.getTime());

                    commentBean.setStatus(comment.getInt("Status"));

                    lstComments.add(commentBean);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
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
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pDialog.dismiss();

            CommentAdapter adapter = new CommentAdapter(context, R.layout.story_comment_item, lstComments);
            lstViewCommentList.setAdapter(adapter);
        }
    }

}
