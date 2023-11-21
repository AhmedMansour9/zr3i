package com.schemecode.zr3i.adapters;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.schemecode.zr3i.R;
import com.schemecode.zr3i.data.models.show_details_lands.WeatherExpectationObject;
import com.schemecode.zr3i.ui.activities.land_activity.LandActivity;

import java.util.List;

public class WeatherDetailsAdapter extends RecyclerView.Adapter<WeatherDetailsAdapter.WeatherDetailsViewHolder> {

    private Context context;
    List<WeatherExpectationObject> weatherExpectationObjects;


    public WeatherDetailsAdapter(Context context , List<WeatherExpectationObject> weatherExpectationObjects) {
        this.context = context;
        this.weatherExpectationObjects = weatherExpectationObjects;
    }

    public class WeatherDetailsViewHolder extends RecyclerView.ViewHolder {

        private TextView tvWeatherState, tvTemp , tvHumandity , tvPressure , tvWindSpeed;
        private ImageView imgArrow , imgWeatherState;
        public WeatherDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWeatherState = itemView.findViewById(R.id.weather_state_text_view);
            tvTemp = itemView.findViewById(R.id.temp_text_view);
            tvHumandity = itemView.findViewById(R.id.humandity_text_view);
            tvPressure = itemView.findViewById(R.id.pressure_text_view);
            tvWindSpeed = itemView.findViewById(R.id.wind_text_view);
            imgWeatherState = itemView.findViewById(R.id.weather_state_image_view);
        }
    }

    @NonNull
    @Override
    public WeatherDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_details_layout, parent, false);
        WeatherDetailsViewHolder weatherDetailsViewHolder = new WeatherDetailsViewHolder(view);
        return weatherDetailsViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull WeatherDetailsViewHolder holder, int position) {
        WeatherExpectationObject weatherExpectationObject = weatherExpectationObjects.get(position);
        String temp = "<font color='#72c02c'>"+weatherExpectationObject.getMain().getTemp() +"</font>";
        String humandity = "<font color='#72c02c'>"+weatherExpectationObject.getMain().getHumidity() +"</font>";
        String pressure = "<font color='#72c02c'>"+weatherExpectationObject.getMain().getPressure()+"</font>";
        String weatherState = "<font color='#72c02c'>"+weatherExpectationObject.getWeather().get(0).getDescription() +"</font>";
        String windSpeed = "<font color='#72c02c'>"+weatherExpectationObject.getWind().getDeg() + "," + weatherExpectationObject.getWind().getSpeed() +"</font>";
        String imageFile = "https://openweathermap.org/img/w/" + weatherExpectationObject.getWeather().get(0).getIcon() +".png";
        Glide.with(context).load(imageFile).into(holder.imgWeatherState);
        holder.tvTemp.setText(Html.fromHtml(temp +" "+ "\u2103"));
        holder.tvHumandity.setText(Html.fromHtml(humandity +" "+ "%"));
        holder.tvPressure.setText(Html.fromHtml(pressure +" "+ "hPa"));
        holder.tvWeatherState.setText(weatherExpectationObject.getWeather().get(0).getDescription());
        holder.tvWindSpeed.setText(Html.fromHtml(windSpeed + " "+"m/s (deg)"));
    }

    @Override
    public int getItemCount() {
        return weatherExpectationObjects.size();
    }
}
