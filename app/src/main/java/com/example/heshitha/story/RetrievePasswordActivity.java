package com.example.heshitha.story;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
import java.util.concurrent.Callable;


public class RetrievePasswordActivity extends Activity {

    TextView txtWelcomeText;
    EditText txtLoginEmail;
    Button btnRetrievePassword;
    TextView txtTermsAndPrivacy;
    TextView txtCopyright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_password);

        txtWelcomeText = (TextView)findViewById(R.id.txtWelcomeText);
        txtLoginEmail = (EditText)findViewById(R.id.txtLoginEmail);
        btnRetrievePassword = (Button)findViewById(R.id.btnRetrievePassword);
        txtTermsAndPrivacy = (TextView)findViewById(R.id.txtTermsAndPrivacy);
        txtCopyright = (TextView)findViewById(R.id.txtCopyright);

        txtWelcomeText.setTypeface(CommonDataHolder.LatoLight);
        txtLoginEmail.setTypeface(CommonDataHolder.LatoLight);
        btnRetrievePassword.setTypeface(CommonDataHolder.RaleWay);
        txtTermsAndPrivacy.setTypeface(CommonDataHolder.LatoLight);
        txtCopyright.setTypeface(CommonDataHolder.LatoLight);

        btnRetrievePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new retrievePasswordAsyncTask(txtLoginEmail.getText().toString()).execute();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_retrieve_password, menu);
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

    public class retrievePasswordAsyncTask extends AsyncTask<String, Void, String>{

        private String phoneOrEmail;
        private ProgressDialog pDialog;
        private String requestResponse;

        public retrievePasswordAsyncTask(String phoneOrEmail){
            this.phoneOrEmail = phoneOrEmail;
        }

        @Override
        protected String doInBackground(String... params) {

            try{
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(CommonDataHolder.WebURLAddress+"ForgotPassword");
                JSONObject json = new JSONObject();

                json.put("PhoneOrEmail", phoneOrEmail);

                StringEntity stringEntity = new StringEntity(json.toString());
                stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                httpPost.setEntity(stringEntity);
                HttpResponse response = httpClient.execute(httpPost);

                HttpEntity httpEntity = response.getEntity();

                requestResponse = EntityUtils.toString(httpEntity);

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

            pDialog = new ProgressDialog(RetrievePasswordActivity.this);
            pDialog.setMessage("Please Wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            pDialog.dismiss();

            try{
                JSONObject responseObject = new JSONObject(requestResponse).getJSONObject("Content");

                boolean isActivationCodeCreated = responseObject.getBoolean("isActivationCodeCreated");
                boolean isEmailSent = responseObject.getBoolean("isEmailSent");

                if(isActivationCodeCreated && isEmailSent){
                    MessageBox.ShowMessageBox(RetrievePasswordActivity.this, "Verify code for change password sent to your email. Please enter it and reset password", MessageBoxType.SUCCESS, true, new Callable<Void>() {
                        @Override
                        public Void call() throws Exception {
                            Intent i = new Intent(RetrievePasswordActivity.this, ResetPasswordActivity.class);
                            i.putExtra("userEmail", phoneOrEmail);
                            startActivity(i);
                            finish();

                            return null;
                        }
                    });

                }else {
                    MessageBox.ShowMessageBox(RetrievePasswordActivity.this, "Please check your email and try again.", MessageBoxType.ERROR, true, new Callable<Void>() {
                        @Override
                        public Void call() throws Exception {

                            txtLoginEmail.setText("");

                            return null;
                        }
                    });
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
