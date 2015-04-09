package com.example.heshitha.story;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.heshitha.story.common.CommonDataHolder;
import com.example.heshitha.story.common.RoundImage;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    String selectedImagePath;
    RoundImage roundImage;


    TextView txtAccountTitle;
    TextView txtPrimaryMailLabel;
    TextView txtPasswordLabel;
    TextView txtPasswordText;
    TextView txtMobileLabel;
    TextView txtProfileTitle;
    TextView txtChangeProfilePictureLabel;
    TextView txtNameLabel;
    TextView txtAddNewAuthorLabel;
    TextView txtAuthorLabel;
    TextView txtOtherTitle;
    TextView txtNotificationLabel;
    TextView txtNightModeLabel;
    TextView txtAppInfoTitle;
    TextView txtTermsOfServiceLabel;
    TextView txtViewTermsOfService;
    TextView txtPrivacyPolicyLabel;
    TextView txtViewPrivacyPolicy;
    TextView txtVersionLabel;
    TextView txtViewVersion;

    ImageView imgProfileImage;
    ImageView btnAddNewAuthor;

    EditText txtPrimaryMailText;
    EditText txtMobileText;
    EditText txtNameText;
    EditText txtAuthorText1;
    EditText txtAuthorText2;

    ToggleButton switchNotification;
    ToggleButton switchNightMode;

    Button btnSignOut;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ThisView = inflater.inflate(R.layout.fragment_settings, container, false);

        txtAccountTitle = (TextView)ThisView.findViewById(R.id.txtAccountTitle);
        txtPrimaryMailLabel = (TextView)ThisView.findViewById(R.id.txtPrimaryMailLabel);
        txtPasswordLabel = (TextView)ThisView.findViewById(R.id.txtPasswordLabel);
        txtPasswordText = (TextView)ThisView.findViewById(R.id.txtPasswordText);
        txtMobileLabel = (TextView)ThisView.findViewById(R.id.txtMobileLabel);
        txtProfileTitle = (TextView)ThisView.findViewById(R.id.txtProfileTitle);
        txtChangeProfilePictureLabel = (TextView)ThisView.findViewById(R.id.txtChangeProfilePictureLabel);
        txtNameLabel = (TextView)ThisView.findViewById(R.id.txtNameLabel);
        txtAddNewAuthorLabel = (TextView)ThisView.findViewById(R.id.txtAddNewAuthorLabel);
        txtAuthorLabel = (TextView)ThisView.findViewById(R.id.txtAuthorLabel);
        txtOtherTitle = (TextView)ThisView.findViewById(R.id.txtOtherTitle);
        txtNotificationLabel = (TextView)ThisView.findViewById(R.id.txtNotificationLabel);
        txtNightModeLabel = (TextView)ThisView.findViewById(R.id.txtNightModeLabel);
        txtAppInfoTitle = (TextView)ThisView.findViewById(R.id.txtAppInfoTitle);
        txtTermsOfServiceLabel = (TextView)ThisView.findViewById(R.id.txtTermsOfServiceLabel);
        txtViewTermsOfService = (TextView)ThisView.findViewById(R.id.txtViewTermsOfService);
        txtPrivacyPolicyLabel = (TextView)ThisView.findViewById(R.id.txtPrivacyPolicyLabel);
        txtViewPrivacyPolicy = (TextView)ThisView.findViewById(R.id.txtViewPrivacyPolicy);
        txtVersionLabel = (TextView)ThisView.findViewById(R.id.txtVersionLabel);
        txtViewVersion = (TextView)ThisView.findViewById(R.id.txtViewVersion);

        txtPrimaryMailText = (EditText)ThisView.findViewById(R.id.txtPrimaryMailText);
        txtMobileText = (EditText)ThisView.findViewById(R.id.txtMobileText);
        txtNameText = (EditText)ThisView.findViewById(R.id.txtNameText);
        txtAuthorText1 = (EditText)ThisView.findViewById(R.id.txtAuthorText1);
        txtAuthorText2 = (EditText)ThisView.findViewById(R.id.txtAuthorText2);

        switchNotification = (ToggleButton)ThisView.findViewById(R.id.switchNotification);
        switchNightMode = (ToggleButton)ThisView.findViewById(R.id.switchNightMode);

        btnSignOut = (Button)ThisView.findViewById(R.id.btnSignOut);

        imgProfileImage = (ImageView)ThisView.findViewById(R.id.imgProfileImage);
        btnAddNewAuthor = (ImageView)ThisView.findViewById(R.id.btnAddNewAuthor);


        txtAccountTitle.setTypeface(CommonDataHolder.RaleWayMedium);
        txtProfileTitle.setTypeface(CommonDataHolder.RaleWayMedium);
        txtOtherTitle.setTypeface(CommonDataHolder.RaleWayMedium);
        txtAppInfoTitle.setTypeface(CommonDataHolder.RaleWayMedium);

        txtPrimaryMailLabel.setTypeface(CommonDataHolder.RaleWayLight);
        txtPasswordLabel.setTypeface(CommonDataHolder.RaleWayLight);
        txtMobileLabel.setTypeface(CommonDataHolder.RaleWayLight);
        txtChangeProfilePictureLabel.setTypeface(CommonDataHolder.RaleWayLight);
        txtNameLabel.setTypeface(CommonDataHolder.RaleWayLight);
        txtAddNewAuthorLabel.setTypeface(CommonDataHolder.RaleWayLight);
        txtAuthorLabel.setTypeface(CommonDataHolder.RaleWayLight);
        txtNotificationLabel.setTypeface(CommonDataHolder.RaleWayLight);
        txtNightModeLabel.setTypeface(CommonDataHolder.RaleWayLight);
        txtTermsOfServiceLabel.setTypeface(CommonDataHolder.RaleWayLight);
        txtPrivacyPolicyLabel.setTypeface(CommonDataHolder.RaleWayLight);
        txtVersionLabel.setTypeface(CommonDataHolder.RaleWayLight);

        txtPasswordText.setTypeface(CommonDataHolder.LatoLight);

        txtViewTermsOfService.setTypeface(CommonDataHolder.RaleWayLight);
        txtViewPrivacyPolicy.setTypeface(CommonDataHolder.RaleWayLight);
        txtViewVersion.setTypeface(CommonDataHolder.RaleWayLight);

        switchNotification.setTextOff("");
        switchNotification.setTextOn("");
        switchNotification.setText("");


        switchNightMode.setTextOff("");
        switchNightMode.setTextOn("");
        switchNightMode.setText("");

        btnSignOut.setTypeface(CommonDataHolder.RaleWay);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences userDetails = getActivity().getSharedPreferences(CommonDataHolder.PREFS_NAME, getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = userDetails.edit();
                editor.clear();
                editor.commit();

                CommonDataHolder.LoggedUser = null;

                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        btnAddNewAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(SettingsPageActivity.this, AddNewAuthorActivity.class);
                //startActivity(i);
            }
        });

        imgProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), CommonDataHolder.SELECT_PICTURE);
            }
        });

        txtPrimaryMailText.setText(CommonDataHolder.LoggedUser.getEmail());
        txtMobileText.setText(CommonDataHolder.LoggedUser.getPhoneNumber());
        imgProfileImage.setImageDrawable(new RoundImage(CommonDataHolder.LoggedUser.getImage()));
        txtNameText.setText(CommonDataHolder.LoggedUser.getName());

        return ThisView;
    }


}
