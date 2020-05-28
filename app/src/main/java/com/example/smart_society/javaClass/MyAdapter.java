package com.example.smart_society.javaClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.smart_society.R;

public class MyAdapter extends ArrayAdapter<String>
{
    Context context;
    String myTitles[];
    String mydescription[];
    int[] imgs;

    public MyAdapter(Context c, String[] titles, int[] imgs, String[] description){
        super(c, R.layout.user_directory_list,R.id.text1,titles);
        this.context=c;
        this.imgs=imgs;
        this.myTitles=titles;
        this.mydescription=description;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row= layoutInflater.inflate(R.layout.user_directory_list,parent,false);
        ImageView images=row.findViewById(R.id.logo1);
        TextView myTitle=row.findViewById(R.id.text1);
        TextView myDescription=row.findViewById(R.id.text2);
        images.setImageResource(imgs[position]);
        myTitle.setText(myTitles[position]);
        myDescription.setText(mydescription[position]);
        return row;
    }
}
