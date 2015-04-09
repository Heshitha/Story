package com.example.heshitha.story.common;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heshitha.story.R;
import com.example.heshitha.story.SignInActivity;

import java.util.concurrent.Callable;

/**
 * Created by Heshitha on 2/18/2015.
 */

public class MessageBox {

    public static void ShowMessageBoxWithRedirectAndFinishAfterCancel(final Context creatingContext, final Class redirectClass, String message, MessageBoxType messageBoxType) {
        try {

            final Dialog dialog = new Dialog(creatingContext);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.success_message);


            TextView txtMessageContent = (TextView)dialog.findViewById(R.id.txtMessageContent);
            TextView txtMessageText = (TextView)dialog.findViewById(R.id.txtMessageText);
            ImageView imgSuccessfullImage = (ImageView)dialog.findViewById(R.id.imgMessageGreenTick);

            txtMessageContent.setTypeface(CommonDataHolder.LatoBold);
            txtMessageText.setTypeface(CommonDataHolder.LatoBold);

            txtMessageText.setText(message);


            switch (messageBoxType){
                case ERROR:{
                    txtMessageText.setTextColor(Color.parseColor("#f12727"));
                    txtMessageContent.setText("OOPS!");
                    imgSuccessfullImage.setImageResource(R.drawable.messageboxredcross);
                    break;
                }
                case SUCCESS:{
                    txtMessageText.setTextColor(Color.parseColor("#3fb21c"));
                    txtMessageContent.setText("SUCCESS!");
                    imgSuccessfullImage.setImageResource(R.drawable.messagebocgreentick);
                    break;
                }
                case WARNING:{
                    txtMessageText.setTextColor(Color.parseColor("#d85a23"));
                    txtMessageContent.setText("WARNING!");
                    imgSuccessfullImage.setImageResource(R.drawable.messageboxwarningicon);
                    break;
                }
                case QUESTION:{
                    txtMessageText.setTextColor(Color.parseColor("#f1bf27"));
                    txtMessageContent.setText("UNKNOWN ERROR!");
                    imgSuccessfullImage.setImageResource(R.drawable.messagequestionmarkicon);
                    break;
                }
            }




            //dialog.setTitle(Html.fromHtml("<font color='#FFFFFF'>Phone Number Verification</font>"));

            //dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_header);
            dialog.setCanceledOnTouchOutside(true);

            //TextView txtWhat = (TextView) dialog.findViewById(R.id.txtWhat);
            //txtWhat.setTypeface(lato_regular);

            //Button btnEdit = (Button) dialog.findViewById(R.id.btnNo);
            //btnEdit.setTypeface(lato_regular);

            //btnEdit.setText("Edit");

            //Button btnOk = (Button) dialog.findViewById(R.id.btnYes);
            //btnOk.setTypeface(lato_regular);

            //btnOk.setText("Ok");

            //txtWhat.setText(" Is this your correct number ? \n\n "
            // + edtPhoneNumber.getText().toString()
            // + "\n\n"
            // + "A Negomee message with an access code will be sent to your device");

            //btnOk.setOnClickListener(new OnClickListener() {
//
            //    @Override
            //    public void onClick(View v) {
            //        // TODO Auto-generated method stub
//
            //        if (!isNetworkConnected()) {
            //            Toast.makeText(getApplicationContext(),
            //                    "Internet is not available", Toast.LENGTH_LONG)
            //                    .show();
            //        }
//
            //        else {
            //            new Async_Phone_Device().execute();
            //        }
//
            //        dialog.dismiss();
            //    }
            //});
//
            //btnEdit.setOnClickListener(new OnClickListener() {
//
            //    @Override
            //    public void onClick(View v) {
            //        // TODO Auto-generated method stub
//
            //        dialog.dismiss();
            //    }
            //});

            final Activity creatingContextActivity = (Activity)creatingContext;

            Display display = creatingContextActivity.getWindowManager().getDefaultDisplay();

            WindowManager.LayoutParams lp;

            lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = (int) (display.getWidth() * 1);

            //
            dialog.getWindow().setAttributes(lp);

            dialog.show();

            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    Intent i = new Intent(creatingContext, redirectClass);
                    creatingContextActivity.startActivity(i);
                    creatingContextActivity.finish();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ShowMessageBox(final Context creatingContext, String message, MessageBoxType messageBoxType, final boolean callFunction, final Callable<Void> OnCancelMethod) {
        try {

            final Dialog dialog = new Dialog(creatingContext);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.success_message);


            TextView txtMessageContent = (TextView)dialog.findViewById(R.id.txtMessageContent);
            TextView txtMessageText = (TextView)dialog.findViewById(R.id.txtMessageText);
            ImageView imgSuccessfullImage = (ImageView)dialog.findViewById(R.id.imgMessageGreenTick);

            txtMessageContent.setTypeface(CommonDataHolder.LatoBold);
            txtMessageText.setTypeface(CommonDataHolder.LatoBold);

            txtMessageText.setText(message);


            switch (messageBoxType){
                case ERROR:{
                    txtMessageText.setTextColor(Color.parseColor("#f12727"));
                    txtMessageContent.setText("OOPS!");
                    imgSuccessfullImage.setImageResource(R.drawable.messageboxredcross);
                    break;
                }
                case SUCCESS:{
                    txtMessageText.setTextColor(Color.parseColor("#3fb21c"));
                    txtMessageContent.setText("SUCCESS!");
                    imgSuccessfullImage.setImageResource(R.drawable.messagebocgreentick);
                    break;
                }
                case WARNING:{
                    txtMessageText.setTextColor(Color.parseColor("#d85a23"));
                    txtMessageContent.setText("WARNING!");
                    imgSuccessfullImage.setImageResource(R.drawable.messageboxwarningicon);
                    break;
                }
                case QUESTION:{
                    txtMessageText.setTextColor(Color.parseColor("#f1bf27"));
                    txtMessageContent.setText("UNKNOWN ERROR!");
                    imgSuccessfullImage.setImageResource(R.drawable.messagequestionmarkicon);
                    break;
                }
            }




            //dialog.setTitle(Html.fromHtml("<font color='#FFFFFF'>Phone Number Verification</font>"));

            //dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_header);
            dialog.setCanceledOnTouchOutside(true);

            //TextView txtWhat = (TextView) dialog.findViewById(R.id.txtWhat);
            //txtWhat.setTypeface(lato_regular);

            //Button btnEdit = (Button) dialog.findViewById(R.id.btnNo);
            //btnEdit.setTypeface(lato_regular);

            //btnEdit.setText("Edit");

            //Button btnOk = (Button) dialog.findViewById(R.id.btnYes);
            //btnOk.setTypeface(lato_regular);

            //btnOk.setText("Ok");

            //txtWhat.setText(" Is this your correct number ? \n\n "
            // + edtPhoneNumber.getText().toString()
            // + "\n\n"
            // + "A Negomee message with an access code will be sent to your device");

            //btnOk.setOnClickListener(new OnClickListener() {
//
            //    @Override
            //    public void onClick(View v) {
            //        // TODO Auto-generated method stub
//
            //        if (!isNetworkConnected()) {
            //            Toast.makeText(getApplicationContext(),
            //                    "Internet is not available", Toast.LENGTH_LONG)
            //                    .show();
            //        }
//
            //        else {
            //            new Async_Phone_Device().execute();
            //        }
//
            //        dialog.dismiss();
            //    }
            //});
//
            //btnEdit.setOnClickListener(new OnClickListener() {
//
            //    @Override
            //    public void onClick(View v) {
            //        // TODO Auto-generated method stub
//
            //        dialog.dismiss();
            //    }
            //});

            final Activity creatingContextActivity = (Activity)creatingContext;

            Display display = creatingContextActivity.getWindowManager().getDefaultDisplay();

            WindowManager.LayoutParams lp;

            lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = (int) (display.getWidth() * 1);

            //
            dialog.getWindow().setAttributes(lp);

            dialog.show();

            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {

                    if(callFunction){
                        try {
                            OnCancelMethod.call();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ShowLoginBox(final Context creatingContext) {
        try {

            final Dialog dialog = new Dialog(creatingContext);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.messagebox_signin);


            TextView txtMessageContent = (TextView)dialog.findViewById(R.id.txtMessageContent);
            TextView txtMessageText = (TextView)dialog.findViewById(R.id.txtMessageText);
            TextView txtThankYouText = (TextView)dialog.findViewById(R.id.txtThankYouText);
            Button btnSignIn = (Button)dialog.findViewById(R.id.btnSignIn);

            txtMessageContent.setTypeface(CommonDataHolder.LatoBold);
            txtMessageText.setTypeface(CommonDataHolder.LatoBold);
            txtThankYouText.setTypeface(CommonDataHolder.LatoBold);
            btnSignIn.setTypeface(CommonDataHolder.timesNewRomenBold);

            btnSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(creatingContext, SignInActivity.class);
                    creatingContext.startActivity(i);
                }
            });

            //dialog.setTitle(Html.fromHtml("<font color='#FFFFFF'>Phone Number Verification</font>"));

            //dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_header);
            dialog.setCanceledOnTouchOutside(true);

            //TextView txtWhat = (TextView) dialog.findViewById(R.id.txtWhat);
            //txtWhat.setTypeface(lato_regular);

            //Button btnEdit = (Button) dialog.findViewById(R.id.btnNo);
            //btnEdit.setTypeface(lato_regular);

            //btnEdit.setText("Edit");

            //Button btnOk = (Button) dialog.findViewById(R.id.btnYes);
            //btnOk.setTypeface(lato_regular);

            //btnOk.setText("Ok");

            //txtWhat.setText(" Is this your correct number ? \n\n "
            // + edtPhoneNumber.getText().toString()
            // + "\n\n"
            // + "A Negomee message with an access code will be sent to your device");

            //btnOk.setOnClickListener(new OnClickListener() {
//
            //    @Override
            //    public void onClick(View v) {
            //        // TODO Auto-generated method stub
//
            //        if (!isNetworkConnected()) {
            //            Toast.makeText(getApplicationContext(),
            //                    "Internet is not available", Toast.LENGTH_LONG)
            //                    .show();
            //        }
//
            //        else {
            //            new Async_Phone_Device().execute();
            //        }
//
            //        dialog.dismiss();
            //    }
            //});
//
            //btnEdit.setOnClickListener(new OnClickListener() {
//
            //    @Override
            //    public void onClick(View v) {
            //        // TODO Auto-generated method stub
//
            //        dialog.dismiss();
            //    }
            //});

            final Activity creatingContextActivity = (Activity)creatingContext;

            Display display = creatingContextActivity.getWindowManager().getDefaultDisplay();

            WindowManager.LayoutParams lp;

            lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = (int) (display.getWidth() * 1);

            //
            dialog.getWindow().setAttributes(lp);

            dialog.show();

            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {



                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

