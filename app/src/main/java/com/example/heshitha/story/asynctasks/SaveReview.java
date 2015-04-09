package com.example.heshitha.story.asynctasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.heshitha.story.beanclasses.Comment_Bean;
import com.example.heshitha.story.common.CommonDataHolder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 * Created by Heshitha on 3/26/2015.
 */
public class SaveReview extends AsyncTask<Comment_Bean, String, String>{

    private Comment_Bean comment;
    private Context context;
    private ProgressDialog pDialog;

    public SaveReview(Comment_Bean comment, Context context) {
        this.comment = comment;
        this.context = context;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();

        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Saving...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

    }

    @Override
    protected void onPostExecute(String strFromDoInBg){
        pDialog.dismiss();

        Log.d("Response",strFromDoInBg);
    }

    @Override
    protected String doInBackground(Comment_Bean... params) {

        try{

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(CommonDataHolder.WebURLAddress+"SaveStoryReview");
            JSONObject json = new JSONObject();

            json.put("User.Id", comment.getUser().getId());
            json.put("Story.Id", comment.getStory().getStoryID());
            json.put("Comment", comment.getComment());
            json.put("Rate", comment.getRate());

            StringEntity stringEntity = new StringEntity(json.toString());
            stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            httpPost.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity httpEntity = response.getEntity();

            return EntityUtils.toString(httpEntity);

        }catch (Exception ex){

        }

        return "";
    }
}
