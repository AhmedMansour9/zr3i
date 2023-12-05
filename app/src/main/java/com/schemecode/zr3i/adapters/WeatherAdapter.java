package com.schemecode.zr3i.adapters;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.schemecode.zr3i.R;
import com.schemecode.zr3i.data.models.show_lands.ListDataByTime;
import com.schemecode.zr3i.data.models.weather.ByDayTimeResponse;
import com.schemecode.zr3i.data.models.weather.WeatherResponse;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> {
    private final List<ListDataByTime> weatherDataList;

    public WeatherAdapter(List<ListDataByTime> weatherDataList) {
        this.weatherDataList = weatherDataList;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        ListDataByTime weatherData = weatherDataList.get(position);

        Pair<String,String> formattedDate = convertTimestampToDateString(weatherData.getDt());

        holder.dateTextView.setText( formattedDate.second + " "+formattedDate.first);
        double kelvinTemperature = weatherData.getMain().getTempMin();
        double celsiusTemperature = kelvinTemperature - 273.15;
        String formattedCelsiusTemperature = String.format("%.2f°C", celsiusTemperature);
        holder.summaryTextView.setText(formattedCelsiusTemperature);

        double kelvinTemperatureMax = weatherData.getMain().getTempMax();
        double celsiusTemperatureMax = kelvinTemperatureMax - 273.15;
        String formattedCelsiusTemperatureMax = String.format("%.2f°C", celsiusTemperatureMax);
        holder.temperatureTextView.setText(formattedCelsiusTemperatureMax);

    }

    public Pair<String,String> convertTimestampToDateString(long timestamp) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(timestamp * 1000);
        String date = DateFormat.format("dd", cal).toString();
        String dateMonth = DateFormat.format("MM", cal).toString();
        String time = DateFormat.format("hh:mm a", cal).toString();

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        // Convert the integer to a day name
        String[] daysOfWeek = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String dayName = daysOfWeek[dayOfWeek - 1];
        int position = -1;

        // Loop through the array to find the string
        for (int i = 0; i < daysOfWeek.length; i++) {
            if (daysOfWeek[i].equals(dayName)) {
                position = i;
                break;  // Exit the loop when the string is found
            }
        }
        String newDate = date;
        if(dayOfMonth < 10 ){
             newDate =   date.replace("0","");
        }

        Log.d("TAG", "convertTimestampToDateString: "+dayOfMonth);


       String dayArabic = getArabicDayName(position);

        return Pair.create(newDate+"/"+dateMonth+" "+time, dayArabic);
    }
    public String getArabicDayName(int dayIndex) {
        String[] arabicDaysOfWeek = new String[]{"الأحد", "الاثنين", "الثلاثاء", "الأربعاء", "الخميس", "الجمعة", "السبت"};
        if (dayIndex >= 0 && dayIndex < arabicDaysOfWeek.length) {
            return arabicDaysOfWeek[dayIndex];
        } else {
            return "Invalid Day";
        }
    }



    @Override
    public int getItemCount() {
        return weatherDataList.size();
    }
}
