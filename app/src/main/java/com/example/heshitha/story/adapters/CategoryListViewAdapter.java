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
import com.example.heshitha.story.beanclasses.Story_Category_Bean;
import com.example.heshitha.story.common.CommonDataHolder;
import com.example.heshitha.story.common.ImageHelper;

import java.util.List;

/**
 * Created by Heshitha on 3/18/2015.
 */
public class CategoryListViewAdapter extends ArrayAdapter<Story_Category_Bean> {

    Context context;

    public CategoryListViewAdapter(Context context, int resourceId,
                                   List<Story_Category_Bean> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    private class ViewHolder {
        TextView txtCategoryName;
        TextView txtCategoryId;
        ImageView imgCategoryImage;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Story_Category_Bean rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.story_category_item, null);
            holder = new ViewHolder();
            holder.txtCategoryName = (TextView) convertView.findViewById(R.id.txtCategoryName);
            holder.txtCategoryId = (TextView)convertView.findViewById(R.id.txtCategoryId);
            holder.imgCategoryImage = (ImageView)convertView.findViewById(R.id.imgCategoryImage);

            holder.txtCategoryName.setTypeface(CommonDataHolder.centurygothic);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtCategoryName.setText(rowItem.getName());
        holder.txtCategoryId.setText(String.valueOf(rowItem.getId()));
        holder.imgCategoryImage.setImageBitmap(rowItem.getImage());
        return convertView;
    }
}
