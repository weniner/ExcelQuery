package com.example.query.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.query.R;
import com.example.query.model.ViewModel;

import java.util.List;

/**
 * @Time: 2019/1/2
 **/
public class ViewAdapter extends ArrayAdapter<ViewModel> {
    private int resource;

    public ViewAdapter(@NonNull Context context, int resource, @NonNull List<ViewModel> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view =LayoutInflater.from(getContext()).inflate(R.layout.item_view,parent,false);
        ViewModel model = getItem(position);
        TextView tv_info = view.findViewById(R.id.tv_info);
        tv_info.setText(model.getInfo());
        return view;
    }
}
