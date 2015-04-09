package com.example.heshitha.story.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heshitha.story.R;
import com.example.heshitha.story.beanclasses.Comment_Bean;
import com.example.heshitha.story.common.CommonDataHolder;
import com.example.heshitha.story.common.RoundImage;

import java.util.List;

/**
 * Created by Heshitha on 3/19/2015.
 */
public class CommentAdapter extends ArrayAdapter<Comment_Bean> {

    Context context;

    public CommentAdapter(Context context, int resource, List<Comment_Bean> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    private class ViewHolder{
        ImageView imgCommenterImage;
        TextView txtCommenterName;
        TextView txtCommentDate;
        TextView txtCommentText;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        Comment_Bean comment = getItem(position);

        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.story_comment_item, null);
            holder = new ViewHolder();
            holder.imgCommenterImage = (ImageView)convertView.findViewById(R.id.imgCommenterImage);
            holder.txtCommenterName = (TextView)convertView.findViewById(R.id.txtCommenterName);
            holder.txtCommentDate = (TextView)convertView.findViewById(R.id.txtCommentDate);
            holder.txtCommentText = (TextView)convertView.findViewById(R.id.txtCommentText);

            holder.txtCommenterName.setTypeface(CommonDataHolder.HelveticaNeueBold);
            holder.txtCommentDate.setTypeface(CommonDataHolder.helviticaNeue);
            holder.txtCommentText.setTypeface(CommonDataHolder.helviticaNeueLight);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.txtCommenterName.setText(comment.getUser().getName());
        holder.txtCommentDate.setText(comment.getCreatedDate().toString());
        holder.txtCommentText.setText(comment.getComment());
        holder.imgCommenterImage.setImageDrawable(new RoundImage(comment.getUser().getImage()));

        return convertView;
    }
}
