package com.schemecode.zr3i.adapters;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.schemecode.zr3i.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SoilDetailsAdapter extends RecyclerView.Adapter<SoilDetailsAdapter.SoilDtailsViewHolder> {

    private Context context;
    List<String> values;


    public SoilDetailsAdapter(Context context , List<String> values) {
        this.context = context;
        this.values = values;
    }

    public class SoilDtailsViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDate, tvPoints;

        public SoilDtailsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.date_text_view);
            tvPoints = itemView.findViewById(R.id.points_text_view);
        }
    }

    @NonNull
    @Override
    public SoilDtailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.soil_dtails_layout, parent, false);
        SoilDtailsViewHolder accountsTypesViewHolder = new SoilDtailsViewHolder(view);
        this.context = parent.getContext();
        return accountsTypesViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull SoilDtailsViewHolder holder, int position) {
        String value = values.get(position);
        String arrValues[] = value.split("--");
        String date = arrValues[0];
        String points = arrValues[1];
        String valueOnly[] = points.split(" ");
        String val = valueOnly[0];
        holder.tvDate.setText(date);
        String pointsColored = "<font color='#72c02c'>"+val+"</font>";
        holder.tvPoints.setText(Html.fromHtml(pointsColored + " " + valueOnly[1]));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
