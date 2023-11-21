package com.schemecode.zr3i.ui.activities.land_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;

import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.bumptech.glide.Glide;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarEntry;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.schemecode.zr3i.R;
import com.schemecode.zr3i.adapters.ShowDetailsSoilAdapter;
import com.schemecode.zr3i.adapters.WeatherExpectationsAdapter;
import com.schemecode.zr3i.data.classes.PrefLand;
import com.schemecode.zr3i.data.models.show_details_lands.Data;
import com.schemecode.zr3i.data.models.show_details_lands.ForecastingByDays;
import com.schemecode.zr3i.data.models.show_details_lands.HistoricalNdvi;
import com.schemecode.zr3i.data.models.show_details_lands.Indicators;
import com.schemecode.zr3i.data.models.show_details_lands.Weather;
import com.schemecode.zr3i.data.models.show_details_lands.WeatherExpectation;
import com.schemecode.zr3i.data.models.show_details_lands.WeatherExpectationObject;
import com.schemecode.zr3i.helpers.FileDownloader;
import com.schemecode.zr3i.ui.activities.edit_land_details.EditLandActivity;
import com.schemecode.zr3i.ui.activities.land_map_activity.LandMapActivity;
import com.schemecode.zr3i.ui.activities.list_farmers.ListFarmersActivity;
import com.schemecode.zr3i.ui.activities.login_activity.LoginActivity;
import com.schemecode.zr3i.ui.dialogs.error_dialog.ErrorDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LandActivity extends AppCompatActivity implements LandContract.view {
    private TextView tvOwnerName, tvCreationDate, tvUpdatedDate, tvCropType, tvContractSpace, tvActualSpace, tvNumberOfCycles, tvCity;
    private LandPresenter landPresenter;
    private SharedPreferences sharedPreferences;
    private String token, secret;
    int landId;
    private String cropType;
    private RelativeLayout rlLand, rlDownloadReport , rlChart , rlChart2 ;
    private ImageView imgFieldImage, imgFieldArea;
    private AppCompatButton btnDownloadPdf , btnEditLand , btnFarmers , btnLandMap ;
    private Map<String, Map<String, String>> outterSoilMap;
    private Map<String, String> innerSoilMap, copyInnerSoildMap;
    private RecyclerView rvSoilIndicatiors, rvWeatherExpectations;
    private ShowDetailsSoilAdapter soilAdapter;
    private LinearLayoutManager linearLayoutManager, weatherExpectationLayoutManage;
    private SmartMaterialSpinner spinnerForecastingDays;
    private List<String> keyList;
    private List<List<WeatherExpectationObject>> weathersListOfLists;
    private WeatherExpectationsAdapter weatherExpectationsAdapter;
    private ImageView imgWeatherState , imgRefresh;
    int weatherForecastingDayPosition = 0;
    private TextView tvWeatherState, tvTemp, tvHumandity, tvPressure, tvWindSpeed,
            tvMaxTemp, tvMinTemp, tvSunset, tvSunrise, tvForecastHumandity, tvForecastPressure, tvWindForecastSpeed;
    private AnyChartView barChartIndicatorVegetation , barChartIndicatorVegetationValuesOftheVegetationIndex;
    private String cityArea , country , desc;
    private Integer cycles , contractSpace ;
    private Double actualSpace ;
    private TextView tvViolet , tv10CmTemp , tvCurrentTemp , tvHumandityInfo ;
    private String city;
    private ProgressBar progressBar;
    private RelativeLayout rlDate , rlRefresh , rlLandProgress ;
    private LinearLayout llCurrentWeather , llWeatherByDate;
    private TextView tvWaitingCurrentWeather , tvWaitingSoildIndicatiors , tvWaitingWeatherExpectations , TvCreationDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land);
        sharedPreferences = getSharedPreferences("MahmoudPref", MODE_PRIVATE);
        token = sharedPreferences.getString("token", "token");
        secret = sharedPreferences.getString("secret", "secret");
        Intent intent = getIntent();
        landId = intent.getIntExtra("land_id", 0);
        cropType = intent.getStringExtra("crop_type");
        landPresenter = new LandPresenter(this);
        landPresenter.start();
        landPresenter.showLand(landId, token, secret);
    }

    @Override
    public void initUi() {
        innerSoilMap = new HashMap<>();
        copyInnerSoildMap = new HashMap<>();
        outterSoilMap = new HashMap<>();
        rlDate = findViewById(R.id.date_relative);
        tv10CmTemp = findViewById(R.id.temp_10cmp_text_view);
        tvCurrentTemp = findViewById(R.id.top_temp_text_view);
        tvViolet = findViewById(R.id.violet_text_view);
        tvHumandityInfo = findViewById(R.id.humidity_info_text_view);
        imgWeatherState = findViewById(R.id.weather_state_image_view);
        barChartIndicatorVegetation = findViewById(R.id.indicator_vegetation_bar);
        barChartIndicatorVegetationValuesOftheVegetationIndex = findViewById(R.id.values_of_the_vegetation_index_chart_view);
        imgFieldImage = findViewById(R.id.field_image_view);
        imgFieldArea = findViewById(R.id.field_area_image_view);
        rlLand = findViewById(R.id.land_relative_layout);
        rlDownloadReport = findViewById(R.id.report_download_relative);
        tvCreationDate = findViewById(R.id.creation_date_dynamic_text_view);
        tvUpdatedDate = findViewById(R.id.last_update_static_text_view);
        tvActualSpace = findViewById(R.id.actual_space_text_view);
        tvContractSpace = findViewById(R.id.contract_space_text_view);
        tvCropType = findViewById(R.id.crops_dynamic_text_view);
        tvOwnerName = findViewById(R.id.owner_text_view);
        tvActualSpace = findViewById(R.id.actual_space_text_view);
        tvNumberOfCycles = findViewById(R.id.number_of_cycles_text_view);
        tvCity = findViewById(R.id.city_text_view);
        btnDownloadPdf = findViewById(R.id.download_report_button);
        tvWeatherState = findViewById(R.id.weather_state_text_view);
        tvTemp = findViewById(R.id.temp_text_view);
        tvHumandity = findViewById(R.id.humandity_text_view);
        tvPressure = findViewById(R.id.pressure_text_view);
        tvWindSpeed = findViewById(R.id.wind_text_view);
        tvMaxTemp = findViewById(R.id.weather_max_temp_text_view);
        tvMinTemp = findViewById(R.id.weather_min_temp_text_view);
        tvForecastHumandity = findViewById(R.id.humandity_forecast_text_view);
        tvForecastPressure = findViewById(R.id.pressure_forecast_text_view);
        tvWindForecastSpeed = findViewById(R.id.wind_forecast_text_view);
        btnEditLand = findViewById(R.id.edit_land_button);
        btnFarmers = findViewById(R.id.farmers_button);
        btnLandMap = findViewById(R.id.map_location_button);
        imgRefresh = findViewById(R.id.refresh_image_view);
        rlRefresh = findViewById(R.id.refresh_relative);
        progressBar = findViewById(R.id.land_progress_bar);
        tvWaitingCurrentWeather = findViewById(R.id.current_weather_waiting_text_view);
        tvWaitingSoildIndicatiors = findViewById(R.id.soil_indicators_waiting_text_view);
        tvWaitingWeatherExpectations = findViewById(R.id.weather_expectations_waiting_text_view);
        rlLandProgress = findViewById(R.id.progress_land_relative);
        rlChart = findViewById(R.id.any_chart_relative_1);
        rlChart2 = findViewById(R.id.any_chart_relative_2);
        llCurrentWeather = findViewById(R.id.current_weather_relative);
        llWeatherByDate = findViewById(R.id.weather_by_date_linear);
    }

    @Override
    public void initSpinner(List<ForecastingByDays> forecastingByDays, List<String> days) {
        spinnerForecastingDays = findViewById(R.id.weather_forecasting_by_days_spinner);
        spinnerForecastingDays.setVisibility(View.VISIBLE);
        llCurrentWeather.setVisibility(View.VISIBLE);
        llWeatherByDate.setVisibility(View.VISIBLE);
        spinnerForecastingDays.setItem(days);
        String maxTemp = "<font color='#72c02c'>" + forecastingByDays.get(0).getMaxTemp() + "</font>";
        String minTemp = "<font color='#72c02c'>" + forecastingByDays.get(0).getMinTemp() + "</font>";
        String humandity = "<font color='#72c02c'>" + forecastingByDays.get(0).getHumidity() + "</font>";
        String pressure = "<font color='#72c02c'>" + forecastingByDays.get(0).getPressure() + "</font>";
        String speed = "<font color='#72c02c'>" + forecastingByDays.get(0).getWindDeg() + "," + forecastingByDays.get(0).getWindSpeed() + "</font>";
        tvMaxTemp.setText(Html.fromHtml(maxTemp + " " + "\u2103"));
        tvMinTemp.setText(Html.fromHtml(minTemp + " " + "\u2103"));
        tvForecastHumandity.setText(Html.fromHtml(humandity + " " + "%"));
        tvForecastPressure.setText(Html.fromHtml(pressure + " " + "hPa"));
        tvWindForecastSpeed.setText(Html.fromHtml(speed + " " + "m/s (deg)"));
        spinnerForecastingDays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                weatherForecastingDayPosition = position;
                String maxTemp = "<font color='#72c02c'>" + forecastingByDays.get(position).getMaxTemp() + "</font>";
                String minTemp = "<font color='#72c02c'>" + forecastingByDays.get(position).getMinTemp() + "</font>";
                String humandity = "<font color='#72c02c'>" + forecastingByDays.get(position).getHumidity() + "</font>";
                String pressure = "<font color='#72c02c'>" + forecastingByDays.get(position).getPressure() + "</font>";
                String speed = "<font color='#72c02c'>" + forecastingByDays.get(position).getWindDeg() + "," + forecastingByDays.get(position).getWindSpeed() + "</font>";
                tvMaxTemp.setText(Html.fromHtml(maxTemp + " " + "\u2103"));
                tvMinTemp.setText(Html.fromHtml(minTemp + " " + "\u2103"));
                tvForecastHumandity.setText(Html.fromHtml(humandity + " " + "%"));
                tvForecastPressure.setText(Html.fromHtml(pressure + " " + "hPa"));
                tvWindForecastSpeed.setText(Html.fromHtml(speed + " " + "m/s (deg)"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void initSoilAdapter(Indicators indicators) {
        rvSoilIndicatiors = findViewById(R.id.soil_recycler_view);
        linearLayoutManager = new LinearLayoutManager(LandActivity.this, LinearLayoutManager.VERTICAL, false);
        soilAdapter = new ShowDetailsSoilAdapter(indicators);
        rvSoilIndicatiors.setLayoutManager(linearLayoutManager);
        rvSoilIndicatiors.setAdapter(soilAdapter);
        rvSoilIndicatiors.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvSoilIndicatiors.getContext(),
                linearLayoutManager.getOrientation());
        rvSoilIndicatiors.addItemDecoration(dividerItemDecoration);
        rvSoilIndicatiors.setVisibility(View.VISIBLE);
    }

    @Override
    public void initWeatherExcpectationsAdapter(List<List<WeatherExpectationObject>> weatherList) {
        rvWeatherExpectations = findViewById(R.id.weather_expectations_recycler_view);
        weatherExpectationsAdapter = new WeatherExpectationsAdapter(LandActivity.this, weatherList);
        weatherExpectationLayoutManage = new LinearLayoutManager(LandActivity.this, LinearLayoutManager.VERTICAL, false);
        rvWeatherExpectations.setAdapter(weatherExpectationsAdapter);
        rvWeatherExpectations.setLayoutManager(weatherExpectationLayoutManage);
        rvWeatherExpectations.setHasFixedSize(true);
        rvWeatherExpectations.setVisibility(View.VISIBLE);
    }

    @Override
    public void handleClicks() {
        btnEditLand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cycles != null && contractSpace != null && actualSpace != null){
                    PrefLand prefLand = new PrefLand(landId , tvOwnerName.getText().toString() , tvCropType.getText().toString(),
                            country ,
                            city ,  cityArea ,
                            cycles, contractSpace,
                            actualSpace , desc);
                    Intent editLandIntent = new Intent(LandActivity.this , EditLandActivity.class);
                    editLandIntent.putExtra("land_obj" ,prefLand);
                    startActivity(editLandIntent);
                }

            }
        });
        btnLandMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent landMapIntent = new Intent(LandActivity.this , LandMapActivity.class);
                landMapIntent.putExtra("land_id" ,landId);
                startActivity(landMapIntent);
            }
        });

        btnFarmers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent famersIntent = new Intent(LandActivity.this , ListFarmersActivity.class);
                famersIntent.putExtra("land_id" , landId);
                startActivity(famersIntent);
            }
        });
        rlRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                landPresenter.showLand(landId , token , secret);
            }
        });
    }

    @Override
    public void setData(Data data , boolean is_land_details_available , String message) throws JSONException {
        if (is_land_details_available == true){
            if (data.getIsDataBuilt() == true) {
                if (data.getWeather().getForcasting() != null) {
                    tvWaitingWeatherExpectations.setVisibility(View.GONE);
                    tvWaitingSoildIndicatiors.setVisibility(View.GONE);
                    tvWaitingCurrentWeather.setVisibility(View.GONE);
                    rlChart.setVisibility(View.VISIBLE);
                    rlChart2.setVisibility(View.VISIBLE);
                    country = data.getInfo().getCountryVal();
                    desc = data.getInfo().getDesc();
                    city = data.getInfo().getCity();
                    cityArea = data.getInfo().getCityArea();
                    cycles = data.getInfo().getCropPlantingCycles();
                    actualSpace = data.getInfo().getActualSpace();
                    contractSpace = Integer.valueOf(data.getInfo().getContract_space_clear());
                    data.getInfo().getContractSpace();
                    initBarChart(data.getHistoricalNdvi());
                    Gson gson;
                    JSONObject forecastingJsonObject;
                    List<String> forecastingByDaysStringList = new ArrayList<>();
                    List<ForecastingByDays> forecastingByDays = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject((Map) data.getWeather().getForcasting().getByDays());
                    Iterator<String> daysIterator = jsonObject.keys();
                    while (daysIterator.hasNext()) {
                        String key = daysIterator.next();
                        gson = new Gson();
                        forecastingJsonObject = new JSONObject(String.valueOf(jsonObject.getJSONObject(key)));
                        ForecastingByDays forecastingByDaysObj = gson.fromJson(String.valueOf(forecastingJsonObject), ForecastingByDays.class);
                        forecastingByDays.add(forecastingByDaysObj);
                        forecastingByDaysStringList.add(forecastingByDaysObj.getDate());
                    }
                    initSpinner(forecastingByDays, forecastingByDaysStringList);
                }
                if (data.getWeather().getForcasting().getByDayTimes() != null) {
                    weathersListOfLists = new ArrayList<>();
                    Gson gson = new Gson();
                    List<WeatherExpectationObject> weatherExpectationObjectList = new ArrayList<>();
                    JSONObject forecastingJsonObject;
                    List<String> weatherExpectations = new ArrayList<>();
                    JSONObject weatherExpectationsJson = new JSONObject((Map) data.getWeather().getForcasting().getByDayTimes());
                    Iterator<String> daysIterator = weatherExpectationsJson.keys();
                    while (daysIterator.hasNext()) {
                        weatherExpectationObjectList = new ArrayList<>();
                        weatherExpectationObjectList.clear();
                        String key = daysIterator.next();
                        JSONArray weatherExpectationJsonArray = new JSONArray(String.valueOf(weatherExpectationsJson.getJSONArray(key)));
                        for (int i = 0; i < weatherExpectationJsonArray.length(); i++) {
                            WeatherExpectationObject weatherExpectationObject = gson.fromJson(String.valueOf(weatherExpectationJsonArray.get(i)), WeatherExpectationObject.class);
                            weatherExpectationObjectList.add(weatherExpectationObject);
                        }
                        weathersListOfLists.add(weatherExpectationObjectList);
//                    WeatherExpectation weatherExpectationObject = gson.fromJson(String.valueOf(weatherExpectationJsonArray), WeatherExpectation.class);
////                    weatherExpectationObjectList.add(weatherExpectationObject);
//                    weatherExpectations.add(key);
                    }
                    Log.e("size", weathersListOfLists.size() + "");
                    initWeatherExcpectationsAdapter(weathersListOfLists);
                }
                if( data.getSoilData().getInfo().getT0() != null){
                    String tempt02f =   String.format("%.2f", data.getSoilData().getInfo().getT0() - 273.15);;
                    String tempt102f =   String.format("%.2f", data.getSoilData().getInfo().getT10() - 273.15);;
                    String temp10cm = "<font color='#72c02c'>"
                            + tempt102f + "</font>";
                    String tempInfo = "<font color='#72c02c'>"
                            + tempt02f + "</font>";
                    String moisture = "<font color='#72c02c'>"
                            + data.getSoilData().getInfo().getMoisture() + "</font>";
                    String uvi = "<font color='#72c02c'>"
                            + data.getSoilData().getUvi().getUvi() + " " + data.getSoilData().getUvi().getRisk() + "</font>";
                    tv10CmTemp.setText(Html.fromHtml(temp10cm + " " + "\u2103"));
                    tvCurrentTemp.setText(Html.fromHtml(tempInfo + " " + "\u2103"));
                    tvHumandityInfo.setText((Html.fromHtml(moisture) + " m3/m3"));
                    tvViolet.setText((Html.fromHtml(uvi)));

                }
                if (data.getInfo().getPlanting_date() != null){
                    tvCreationDate.setText("تاريخ الزراعة: " + data.getInfo().getPlanting_date());
                }
                else {
                    tvCreationDate.setText("تاريخ الزراعة: ");
                }
                tvUpdatedDate.setText("اخر تحديث #" + data.getInfo().getLastUpdate());
//                tvCreationDate.setText("تاريخ الإنشاء #" + data.getInfo().getcr());
                tvCity.setText(Html.fromHtml(data.getInfo().getCountryVal()));
                tvNumberOfCycles.setText("عدد دورات زراعة المحصول: " + data.getInfo().getCropPlantingCycles() + "");
                tvOwnerName.setText(data.getInfo().getOwner());
                tvContractSpace.setText("مساحة العقد: " + data.getInfo().getContractSpace());
                tvActualSpace.setText("المساحة الفعلية: " + data.getInfo().getAcre().getTitle());
                tvCropType.setText(" " + cropType);
                rlLand.setVisibility(View.VISIBLE);
                if (data.getWeather().getCurrent().getDetails().getWeather().get(0) != null) {
                    String imageFile = "https://openweathermap.org/img/w/" + data.getWeather().getCurrent().getDetails().getWeather().get(0).getIcon() + ".png";
                    tvWeatherState.setText(data.getWeather().getCurrent().getDetails().getWeather().get(0).getDescription());
                    Glide.with(LandActivity.this).load(imageFile).into(imgWeatherState);
                }
                String temp = "<font color='#72c02c'>" + data.getWeather().getCurrent().getDetails().getMain().getTemp() + "</font>";
                String humandity = "<font color='#72c02c'>" + data.getWeather().getCurrent().getDetails().getMain().getHumidity() + "</font>";
                String pressure = "<font color='#72c02c'>" + data.getWeather().getCurrent().getDetails().getMain().getPressure() + "</font>";
                String windSpeed = "<font color='#72c02c'>" + data.getWeather().getCurrent().getDetails().getWind().getDeg() + "," + data.getWeather().getCurrent().getDetails().getWind().getSpeed() + " m/s (deg) " + "</font>";
                tvTemp.setText(Html.fromHtml(temp + " " + "\u2103"));
                tvHumandity.setText(Html.fromHtml(humandity + " " + "%"));
                tvPressure.setText(Html.fromHtml(pressure + " " + "hPa"));
                tvWindSpeed.setText(Html.fromHtml(windSpeed));
                Glide.with(LandActivity.this).load(data.getMedia().getFieldImage().getUrl()).into(imgFieldImage);
                Glide.with(LandActivity.this).load(data.getMedia().getFieldIndexAreaImage().getUrl()).into(imgFieldArea);
                initSoilAdapter(data.getSoilData().getIndicators());
                if (data.getMedia().getFieldReport().getExists() == true) {
                    rlDownloadReport.setVisibility(View.VISIBLE);
                    btnDownloadPdf.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getMedia().getFieldReport().getUrl()));
                            startActivity(browserIntent);
                        }
                    });
                }
            }
            else {
                rlLandProgress.setVisibility(View.VISIBLE);
                if (data.getSoilData().getInfo().getT0() != null){
                    String tempt02f =   String.format("%.2f", data.getSoilData().getInfo().getT0() - 273.15);;
                    String tempt102f =   String.format("%.2f", data.getSoilData().getInfo().getT10() - 273.15);;
                    String temp10cm = "<font color='#72c02c'>"
                            + tempt102f + "</font>";
                    String tempInfo = "<font color='#72c02c'>"
                            + tempt02f + "</font>";
                    String moisture = "<font color='#72c02c'>"
                            + data.getSoilData().getInfo().getMoisture() + "</font>";
                    String uvi = "<font color='#72c02c'>"
                            + data.getSoilData().getUvi().getUvi() + " " + data.getSoilData().getUvi().getRisk() + "</font>";
                    tv10CmTemp.setText(Html.fromHtml(temp10cm + " " + "\u2103"));
                    tvCurrentTemp.setText(Html.fromHtml(tempInfo + " " + "\u2103"));
                    tvHumandityInfo.setText((Html.fromHtml(moisture) + " m3/m3"));
                    tvViolet.setText((Html.fromHtml(uvi)));
                }

                if (data.getInfo().getPlanting_date() == null){
                    tvCreationDate.setText("تاريخ الزراعة: " );
                }
                else {
                    tvCreationDate.setText("تاريخ الزراعة: " + data.getInfo().getPlanting_date());
                }

                tvUpdatedDate.setText("اخر تحديث #" + data.getInfo().getLastUpdate());
                tvCity.setText(Html.fromHtml(data.getInfo().getCountryVal()));
                tvNumberOfCycles.setText("عدد دورات زراعة المحصول: " + data.getInfo().getCropPlantingCycles() + "");
                tvOwnerName.setText(data.getInfo().getOwner());
                tvContractSpace.setText("مساحة العقد: " + data.getInfo().getContractSpace());
                tvActualSpace.setText("المساحة الفعلية: " + data.getInfo().getAcre().getTitle());
                tvCropType.setText(" " + cropType);
                rlLand.setVisibility(View.VISIBLE);
                Glide.with(LandActivity.this).load(data.getInfo().getMap_image_url()).into(imgFieldArea);
                if (data.getMedia().getFieldReport().getExists() == true) {
                    rlDownloadReport.setVisibility(View.VISIBLE);
                    btnDownloadPdf.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getMedia().getFieldReport().getUrl()));
                            startActivity(browserIntent);
                        }
                    });
                }
            }
        }
        else {
            ErrorDialog errorDialog = new ErrorDialog(LandActivity.this, message);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            errorDialog.show();
        }
    }

    @Override
    public void setDataOld(com.schemecode.zr3i.data.models.show_lands.Data data) throws JSONException {
        if (data.getIsDataBuilt() == true) {
            JSONObject jsonObject = new JSONObject((Map) data.getField().getHealth());
            Iterator<String> keys = jsonObject.keys();
            Iterator<String> subKeys;

            while (keys.hasNext()) {
                copyInnerSoildMap = new HashMap<>();
                innerSoilMap = new HashMap<>();
                copyInnerSoildMap.clear();
                innerSoilMap.clear();
                String key = keys.next();
                if (jsonObject.get(key) instanceof JSONObject) {
                    JSONObject subJsonObject = new JSONObject(String.valueOf(jsonObject.getJSONObject(key)));
                    subKeys = subJsonObject.keys();
                    while (subKeys.hasNext()) {
                        String subkey = subKeys.next();
                        innerSoilMap.put(subkey, subJsonObject.getString(subkey));
                    }
                    copyInnerSoildMap.putAll(innerSoilMap);
                    outterSoilMap.put(key, copyInnerSoildMap);
                }
            }
//            tvCreationDate.setText(data.getCreatedAtFormated());
            tvUpdatedDate.setText("اخر تحديث #" + data.getUpdatedAtFormated());
            tvCity.setText(Html.fromHtml(data.getCountryVal()));
            tvNumberOfCycles.setText("عدد دورات زراعة المحصول: " + data.getCropPlantingCycles() + "");
            tvOwnerName.setText(data.getOwner());
            tvContractSpace.setText("مساحة العقد: " + data.getContractSpace() + " فدان");
            tvActualSpace.setText("المساحة الفعلية: " + data.getAcre().getTitle());
            tvCropType.setText(" " + cropType);
            rlLand.setVisibility(View.VISIBLE);
            Glide.with(LandActivity.this).load(data.getFieldImage().getUrl()).into(imgFieldImage);
            Glide.with(LandActivity.this).load(data.getFieldIndexAreaImage().getUrl()).into(imgFieldArea);
            if (data.getFieldReport().getExists() == true) {
                rlDownloadReport.setVisibility(View.VISIBLE);
                btnDownloadPdf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getFieldReport().getUrl()));
                        startActivity(browserIntent);
                    }
                });
            }
        } else {
            ErrorDialog errorDialog = new ErrorDialog(LandActivity.this, "عفواً طلبك ما زال تحت التنفيذ");
            errorDialog.show();
        }
    }


    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void initBarChart(HistoricalNdvi historicalNdvi) {
        APIlib.getInstance().setActiveAnyChartView(barChartIndicatorVegetation);
        Cartesian cartesian = AnyChart.line();
        cartesian.animation(true);
        cartesian.padding(10d, 20d, 5d, 20d);
        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title(historicalNdvi.getCharts().getFirst().getTitle());

        List<DataEntry> seriesData = new ArrayList<>();
            for (int i = 0; i < historicalNdvi.getCharts().getFirst().getData().get(0).getValues().size(); i++) {
                Double waset = historicalNdvi.getCharts().getFirst().getData().get(0).getValues().get(i);
                Double aqsa = historicalNdvi.getCharts().getFirst().getData().get(1).getValues().get(i);
                Double adna = historicalNdvi.getCharts().getFirst().getData().get(2).getValues().get(i);
                Double mtwst = historicalNdvi.getCharts().getFirst().getData().get(3).getValues().get(i);

                seriesData.add(new CustomDataEntry(historicalNdvi.getDays().get(i),
                        waset, aqsa, adna , mtwst));
            }

        com.anychart.data.Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");
        Mapping series4Mapping = set.mapAs("{ x: 'x', value: 'value4' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name(historicalNdvi.getCharts().getFirst().getData().get(0).getTitle());
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series2 = cartesian.line(series2Mapping);
        series2.name(historicalNdvi.getCharts().getFirst().getData().get(1).getTitle());
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series3 = cartesian.line(series3Mapping);
        series3.name(historicalNdvi.getCharts().getFirst().getData().get(2).getTitle());
        series3.hovered().markers().enabled(true);
        series3.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series3.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series4 = cartesian.line(series4Mapping);
        series4.name(historicalNdvi.getCharts().getFirst().getData().get(3).getTitle());
        series4.hovered().markers().enabled(true);
        series4.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series4.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);
        barChartIndicatorVegetation.setChart(cartesian);
        initValuesOfTheVegetationIndex(historicalNdvi);
    }

    @Override
    public void initValuesOfTheVegetationIndex(HistoricalNdvi historicalNdvi) {
        APIlib.getInstance().setActiveAnyChartView(barChartIndicatorVegetationValuesOftheVegetationIndex);
        Cartesian cartesian = AnyChart.line();
        cartesian.animation(true);
        cartesian.padding(10d, 20d, 5d, 20d);
        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title(historicalNdvi.getCharts().getSecond().getTitle());
        List<DataEntry> seriesData = new ArrayList<>();
        for (int i = 0; i < historicalNdvi.getCharts().getFirst().getData().get(0).getValues().size(); i++) {
            Double thirdValue = historicalNdvi.getCharts().getFirst().getData().get(0).getValues().get(i);
            Double firstValue = historicalNdvi.getCharts().getFirst().getData().get(1).getValues().get(i);
            Double en7rafMe3iary = historicalNdvi.getCharts().getFirst().getData().get(2).getValues().get(i);
            seriesData.add(new CustomDataEntryTwoValues(historicalNdvi.getDays().get(i),
                    thirdValue, firstValue, en7rafMe3iary));
        }

        com.anychart.data.Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name(historicalNdvi.getCharts().getSecond().getData().get(0).getTitle());
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series2 = cartesian.line(series2Mapping);
        series2.name(historicalNdvi.getCharts().getSecond().getData().get(1).getTitle());
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series3 = cartesian.line(series3Mapping);
        series3.name(historicalNdvi.getCharts().getSecond().getData().get(2).getTitle());
        series3.hovered().markers().enabled(true);
        series3.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series3.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);
        barChartIndicatorVegetationValuesOftheVegetationIndex.setChart(cartesian);
    }

    @Override
    public void quotaFinishedError(String message) {
        rlLand.setVisibility(View.GONE);
        Snackbar snackbar = Snackbar.make(rlLand, message, Snackbar.LENGTH_LONG);
        snackbar.setAction("تجاهل", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
                LandActivity.this.finish();
            }
        });
        ViewCompat.setLayoutDirection(snackbar.getView(), ViewCompat.LAYOUT_DIRECTION_RTL);
        snackbar.setActionTextColor(getResources().getColor(R.color.white));
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(getResources().getColor(R.color.baseGreen));
        snackbar.show();
    }

    @Override
    public void showProgress(boolean isVisible) {
        progressBar.setVisibility(isVisible == true ? View.VISIBLE : View.GONE);
        rlDate.setVisibility(isVisible == true ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void unAuthorized(String message) {
        if (getSharedPreferences("MahmoudPref" , MODE_PRIVATE).edit().clear().commit() == true){
            Intent intent = new Intent(this , LoginActivity.class);
            startActivity(intent);
            finishAffinity();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        landPresenter.setView(LandActivity.this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        landPresenter.setView(null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        landPresenter.setView(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        landPresenter.setView(LandActivity.this);
    }

    public void download(View v, String url, String name) {
        new DownloadFile().execute(url, name + ".pdf");
    }

    public void view(View v) {
        File pdfFile = new File(getFilesDir().getAbsolutePath() + "/pdf/" + "report.pdf");  // -> filename = maven.pdf
        Uri path = Uri.fromFile(pdfFile);
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try {
            startActivity(pdfIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(LandActivity.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
        }
    }

    public JSONObject objectToJSONObject(Object object) {
        Object json = null;
        JSONObject jsonObject = null;
        try {
            json = new JSONTokener(object.toString()).nextValue();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (json instanceof JSONObject) {
            jsonObject = (JSONObject) json;
        }
        return jsonObject;
    }
}

class DownloadFile extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... strings) {
        String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
        String fileName = strings[1];  // -> maven.pdf
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        File folder = new File(extStorageDirectory, "testthreepdf");
        folder.mkdir();

        File pdfFile = new File(folder, fileName);

        try {
            pdfFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileDownloader.downloadFile(fileUrl, pdfFile);
        return null;
    }
}
 class CustomDataEntry extends ValueDataEntry {

    CustomDataEntry(String x, Number value, Number value2, Number value3 , Number value4) {
        super(x, value);
        setValue("value2", value2);
        setValue("value3", value3);
        setValue("value4", value4);
    }
}

class CustomDataEntryTwoValues extends ValueDataEntry {

    CustomDataEntryTwoValues(String x, Number value, Number value2, Number value3) {
        super(x, value);
        setValue("value2", value2);
        setValue("value3", value3);
    }
}
