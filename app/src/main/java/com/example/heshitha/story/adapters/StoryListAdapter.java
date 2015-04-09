package com.example.heshitha.story.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.heshitha.story.R;
import com.example.heshitha.story.beanclasses.Story_Bean;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Heshitha on 3/18/2015.
 */
public class StoryListAdapter extends ArrayAdapter {

    private Context mContext;
    private List<Story_Bean> storyList;

    public StoryListAdapter(Context context, List<Story_Bean> storyList) {
        super(context, R.layout.fragment_story_list, storyList);
        this.storyList = storyList;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        RecordHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.story_item, parent, false);

            holder = new RecordHolder();

            holder.storyImage = (ImageView) row.findViewById(R.id.imgStoryImage);
            holder.txtStoryName = (TextView) row.findViewById(R.id.txtStoryName);
            holder.txtStoryAuthor = (TextView) row.findViewById(R.id.txtStoryAuthor);
            holder.txtStorySummery = (TextView) row.findViewById(R.id.txtStorySummery);
            holder.txtPublishedDate = (TextView) row.findViewById(R.id.txtPublishedDate);
            holder.txtNoofReviews = (TextView) row.findViewById(R.id.txtNoofReviews);
            holder.txtStoryID = (TextView)row.findViewById(R.id.txtStoryID);
            holder.txtFullStory = (TextView)row.findViewById(R.id.txtFullStory);
            holder.rbStoryRatingBar = (RatingBar)row.findViewById(R.id.rbStoryRatingBar);
            row.setTag(holder);
        }else {
            holder = (RecordHolder)row.getTag();
        }

        Story_Bean item = storyList.get(position);

        holder.txtStoryName.setText(item.getTitle());



        String authorName = item.getAuthor().getName();

        if(authorName != "null"){
            holder.txtStoryAuthor.setText("By " + item.getAuthor().getName());
        }
        else{
            holder.txtStoryAuthor.setText("By Anonymous");
        }

        String storyContent = item.getSummery();

        if(storyContent.length() > 100){
            storyContent = storyContent.substring(0, 100) + "... ";
        }

        holder.txtStorySummery.setText(storyContent);
        holder.txtPublishedDate.setText(new SimpleDateFormat("MMM dd, yyyy").format(item.getCreatedDate()));

        NumberFormat formatter = new DecimalFormat("#0.00");


        holder.txtNoofReviews.setText(formatter.format(item.getRate()) + " ratings");
        holder.storyImage.setImageBitmap(item.getImage());
        holder.txtStoryID.setText(String.valueOf(item.getStoryID()));
        holder.txtFullStory.setText(item.getFullStory());
        holder.rbStoryRatingBar.setRating((float)(Math.floor(item.getRate())+0.5));

        return row;
    }

    public class RecordHolder{
        ImageView storyImage;
        TextView txtStoryName;
        TextView txtStoryAuthor;
        TextView txtStorySummery;
        TextView txtPublishedDate;
        TextView txtNoofReviews;
        TextView txtStoryID;
        TextView txtFullStory;
        RatingBar rbStoryRatingBar;
    }
}

