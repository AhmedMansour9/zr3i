package com.schemecode.zr3i.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.schemecode.zr3i.R;

public class WeatherViewHolder extends RecyclerView.ViewHolder {
    TextView dateTextView;
    TextView temperatureTextView;
    TextView summaryTextView;

    public WeatherViewHolder(View itemView) {
        super(itemView);
        dateTextView = itemView.findViewById(R.id.dateTextView);
        temperatureTextView = itemView.findViewById(R.id.temperatureTextView);
        summaryTextView = itemView.findViewById(R.id.summaryTextView);
    }
}
