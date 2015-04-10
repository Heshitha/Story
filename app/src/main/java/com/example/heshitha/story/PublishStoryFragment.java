package com.example.heshitha.story;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.heshitha.story.common.CommonDataHolder;


/**
 * A simple {@link Fragment} subclass.
 */
public class PublishStoryFragment extends Fragment {

    TextView txtBookCoverLabel;

    ImageView imgStoryCoverImage;

    Spinner spnStoryCategory;
    Spinner spnStoryAuthor;

    EditText txtSummeryText;

    Button btnPublish;


    public PublishStoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ThisView = inflater.inflate(R.layout.fragment_publish_story, container, false);

        txtBookCoverLabel = (TextView)ThisView.findViewById(R.id.txtBookCoverLabel);

        imgStoryCoverImage = (ImageView)ThisView.findViewById(R.id.imgStoryCoverImage);

        spnStoryCategory = (Spinner)ThisView.findViewById(R.id.spnStoryCategory);
        spnStoryAuthor = (Spinner)ThisView.findViewById(R.id.spnStoryAuthor);

        txtSummeryText = (EditText)ThisView.findViewById(R.id.txtSummeryText);

        btnPublish = (Button)ThisView.findViewById(R.id.btnPublish);

        txtBookCoverLabel.setTypeface(CommonDataHolder.LatoLight);

        txtSummeryText.setTypeface(CommonDataHolder.LatoLight);

        btnPublish.setTypeface(CommonDataHolder.RaleWay);

        for(int i = 0; i < spnStoryAuthor.getChildCount(); i++){
            TextView vi = (TextView)spnStoryAuthor.getChildAt(i);
            vi.setTypeface(CommonDataHolder.LatoLight);
            vi.setTextColor(Color.parseColor("#434343"));
        }

        for(int i = 0; i < spnStoryCategory.getChildCount(); i++){
            TextView vi = (TextView)spnStoryCategory.getChildAt(i);
            vi.setTypeface(CommonDataHolder.LatoLight);
            vi.setTextColor(Color.parseColor("#434343"));
        }

        imgStoryCoverImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent();
                //intent.setType("image/*");
                //intent.setAction(Intent.ACTION_GET_CONTENT);
                //startActivityForResult(Intent.createChooser(intent, "Select Picture"), CommonDataHolder.SELECT_PICTURE);
            }
        });

        

        return ThisView;
    }


}
