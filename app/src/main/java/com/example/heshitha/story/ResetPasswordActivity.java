package com.example.heshitha.story;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.heshitha.story.beanclasses.User_Bean;
import com.example.heshitha.story.common.CommonDataHolder;
import com.example.heshitha.story.common.MessageBox;
import com.example.heshitha.story.common.MessageBoxType;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.concurrent.Callable;


public class ResetPasswordActivity extends Activity {


    TextView txtWelcomeText;
    EditText txtVerifyCode;
    EditText txtNewPassword;
    EditText txtConfirmPassword;
    Button btnSubmit;
    TextView txtCopyRightText;

    String phoneOrEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        txtWelcomeText = (TextView)findViewById(R.id.txtWelcomeText);
        txtVerifyCode = (EditText)findViewById(R.id.txtResetPasswordCode);
        txtNewPassword = (EditText)findViewById(R.id.txtResetNewPassword);
        txtConfirmPassword = (EditText)findViewById(R.id.txtResetConfirmPassword);
        btnSubmit = (Button)findViewById(R.id.btnResetPassword);
        txtCopyRightText = (TextView)findViewById(R.id.txtCopyright);

        txtWelcomeText.setTypeface(CommonDataHolder.LatoLight);
        txtVerifyCode.setTypeface(CommonDataHolder.LatoLight);
        txtNewPassword.setTypeface(CommonDataHolder.LatoLight);
        txtConfirmPassword.setTypeface(CommonDataHolder.LatoLight);
        btnSubmit.setTypeface(CommonDataHolder.RaleWay);
        txtCopyRightText.setTypeface(CommonDataHolder.LatoLight);

        Intent myIntent = getIntent();
        phoneOrEmail = myIntent.getStringExtra("userEmail");



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User_Bean userBean = new User_Bean();
                userBean.setEmail(phoneOrEmail);
                userBean.setPassword(txtNewPassword.getText().toString());
                String verifyCode = txtVerifyCode.getText().toString();

                new ResetPasswordAsyncTask(userBean, verifyCode).execute();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reset_password, menu);
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

    public class ResetPasswordAsyncTask extends AsyncTask<String, Void, String>{
        private User_Bean userBean;
        private String code;
        private HttpEntity httpEntity;
        ProgressDialog pDialog;
        String PasswordChangeResponse;

        public ResetPasswordAsyncTask(User_Bean userBean, String code) {
            this.userBean = userBean;
            this.code = code;
        }

        @Override
        protected String doInBackground(String... params) {

            try{
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(CommonDataHolder.WebURLAddress+"VerifyForgotPassword");
                JSONObject json = new JSONObject();

                json.put("PhoneOrEmail", userBean.getEmail());
                json.put("ActivationCode", code);
                json.put("Password", userBean.getPassword());

                StringEntity stringEntity = new StringEntity(json.toString());
                stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                httpPost.setEntity(stringEntity);
                HttpResponse response = httpClient.execute(httpPost);

                httpEntity = response.getEntity();

                PasswordChangeResponse = EntityUtils.toString(httpEntity);
                JSONObject responseObject = new JSONObject(PasswordChangeResponse);
                int responseCode = responseObject.getInt("Code");

                if(responseCode == 1){

                    httpClient = new DefaultHttpClient();
                    httpPost = new HttpPost(CommonDataHolder.WebURLAddress+"Login");
                    json = new JSONObject();

                    json.put("PhoneOrEmail", userBean.getEmail());
                    json.put("Password", userBean.getPassword());

                    stringEntity = new StringEntity(json.toString());
                    stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                    httpPost.setEntity(stringEntity);
                    HttpResponse loginResponse = httpClient.execute(httpPost);

                    HttpEntity loginHttpEntity = loginResponse.getEntity();

                    String content = EntityUtils.toString(loginHttpEntity);

                    JSONObject loginResponseObject = new JSONObject(content);

                    int logineRsponseCode = loginResponseObject.getInt("Code");

                    if(logineRsponseCode == 1){
                        //successfully logged in

                        JSONObject userJsonObject = loginResponseObject.getJSONObject("Content");

                        String ImageUrl = "http://www.assets.ezcim.com/story/userpics/profilepic/" + userJsonObject.getString("ImageName");
                        URL url = new URL(ImageUrl);
                        Bitmap userProfileImage = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                        User_Bean usrBean = new User_Bean();
                        usrBean.setId(userJsonObject.getInt("Id"));
                        usrBean.setName(userJsonObject.getString("Name"));
                        usrBean.setEmail(userJsonObject.getString("Email"));
                        usrBean.setPhoneNumber(userJsonObject.getString("PhoneNumber"));
                        usrBean.setPassword(userJsonObject.getString("Password"));
                        usrBean.setImageName(userJsonObject.getString("ImageName"));
                        usrBean.setStatus(userJsonObject.getInt("Status"));
                        usrBean.setImage(userProfileImage);

                        SharedPreferences userDetails = getSharedPreferences(CommonDataHolder.PREFS_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = userDetails.edit();
                        editor.putInt("Id", usrBean.getId());
                        editor.putString("Name", usrBean.getName());
                        editor.putString("Email", usrBean.getEmail());
                        editor.putString("PhoneNumber", usrBean.getPhoneNumber());
                        editor.putString("Password", usrBean.getPassword());
                        editor.putString("ImageName", usrBean.getImageName());
                        editor.putInt("Status", usrBean.getStatus());

                        editor.commit();

                        CommonDataHolder.LoggedUser = usrBean;
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(ResetPasswordActivity.this);
            pDialog.setMessage("Please Wait ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject responseObject = new JSONObject(PasswordChangeResponse);
                int responseCode = responseObject.getInt("Code");

                if(responseCode == 1){

                    MessageBox.ShowMessageBox(ResetPasswordActivity.this, "You have successfully changed the password and you will redirect to your home page", MessageBoxType.SUCCESS, true, new Callable<Void>() {
                        @Override
                        public Void call() throws Exception {
                            Intent i = new Intent(ResetPasswordActivity.this, HomePageActivity.class);
                            i.putExtra("WelcomeBackText", false);
                            startActivity(i);
                            finish();
                            return null;
                        }
                    });
                }else{
                    MessageBox.ShowMessageBox(ResetPasswordActivity.this, "Please verify code you entered and try again.", MessageBoxType.ERROR, false, null);
                }

            }  catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
