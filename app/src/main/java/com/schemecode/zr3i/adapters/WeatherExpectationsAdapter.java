package com.schemecode.zr3i.adapters;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.schemecode.zr3i.R;
import com.schemecode.zr3i.data.models.show_details_lands.WeatherExpectationObject;

import java.util.List;

public class WeatherExpectationsAdapter extends RecyclerView.Adapter<WeatherExpectationsAdapter.WeatherExpectationsViewHolder> {

    private Context context;
    List<List<WeatherExpectationObject>> weatherList;
    int pos = -1;

    public WeatherExpectationsAdapter(Context context, List<List<WeatherExpectationObject>> weatherList) {
        this.context = context;
        this.weatherList = weatherList;
    }

    public class WeatherExpectationsViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDate;
        private RecyclerView rvExcpectation;
        private WeatherDetailsAdapter weatherDetailsAdapter;
        private LinearLayoutManager linearLayoutManager;
        private ImageView imgArrow;
        private RelativeLayout rlExpectedDate;

        public WeatherExpectationsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.date_text_view);
            rvExcpectation = itemView.findViewById(R.id.weather_expectation_item_recycler_view);
            imgArrow = itemView.findViewById(R.id.arrow_image_view);
            rlExpectedDate = itemView.findViewById(R.id.date_layout);

            rlExpectedDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (rvExcpectation.getVisibility() == View.VISIBLE){
                        rvExcpectation.setVisibility(View.GONE);
                    }
                    else {
                        rvExcpectation.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public WeatherExpectationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_excpectations_layout, parent, false);
        WeatherExpectationsViewHolder weatherExpectationsViewHolder = new WeatherExpectationsViewHolder(view);
        this.context = parent.getContext();
        return weatherExpectationsViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull WeatherExpectationsViewHolder holder, int position) {
        List<WeatherExpectationObject> weatherExpectationObjectList = weatherList.get(position);
        Log.e("BindingPos", pos + "");
        if (pos == position) {
            holder.rvExcpectation.setVisibility(View.VISIBLE);
            holder.rlExpectedDate.setBackground(context.getResources().getDrawable(R.drawable.weather_excpectations_selected));
            holder.imgArrow.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
            holder.imgArrow.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            holder.rvExcpectation.setVisibility(View.GONE);
            holder.rlExpectedDate.setBackground(context.getResources().getDrawable(R.drawable.weather_expectations_shape));
            holder.imgArrow.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
            holder.imgArrow.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        holder.tvDate.setText(weatherExpectationObjectList.get(weatherExpectationObjectList.size() - 1).getDate());
        holder.weatherDetailsAdapter = new WeatherDetailsAdapter(context, weatherExpectationObjectList);
        holder.linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.rvExcpectation.setAdapter(holder.weatherDetailsAdapter);
        holder.rvExcpectation.setNestedScrollingEnabled(false);
        holder.rvExcpectation.setLayoutManager(holder.linearLayoutManager);
        holder.rvExcpectation.setHasFixedSize(true);
        holder.rvExcpectation.setNestedScrollingEnabled(false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(holder.rvExcpectation.getContext(),
                holder.linearLayoutManager.getOrientation());
        holder.rvExcpectation.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }
}
