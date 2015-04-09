package com.example.heshitha.story.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.heshitha.story.R;
import com.example.heshitha.story.beanclasses.Story_Bean;
import com.example.heshitha.story.common.CommonDataHolder;

import java.util.List;

/**
 * Created by Heshitha on 2/24/2015.
 */

public class ProfileStoriesAdapter extends ArrayAdapter<Story_Bean> {

    Context context;

    public ProfileStoriesAdapter(Context context, int resource, List<Story_Bean> items) {
        super(context, resource, items);
        this.context = context;
    }

    private class ViewHolder{
        TextView txtStoryTitle;
        TextView txtStoryAuthor;
        TextView txtStoryRatings;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Story_Bean rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.story_profile_item, null);
            holder = new ViewHolder();
            holder.txtStoryTitle = (TextView)convertView.findViewById(R.id.txtStoryTitle);
            holder.txtStoryTitle.setTypeface(CommonDataHolder.helviticaNeueLight);
            holder.txtStoryAuthor = (TextView)convertView.findViewById(R.id.txtStoryAuthor);
            holder.txtStoryAuthor.setTypeface(CommonDataHolder.helviticaNeueLight);
            holder.txtStoryRatings = (TextView)convertView.findViewById(R.id.txtStoryRatings);
            holder.txtStoryRatings.setTypeface(CommonDataHolder.helviticaNeue);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtStoryTitle.setText(rowItem.getTitle());
        holder.txtStoryAuthor.setText("By "+rowItem.getAuthor().getName());
        holder.txtStoryRatings.setText(String.valueOf(rowItem.getRate()));

        return convertView;
    }

}
