package com.schemecode.zr3i.ui.activities.land_activity;

import com.schemecode.zr3i.data.models.show_details_lands.Data;
import com.schemecode.zr3i.data.models.show_details_lands.ForecastingByDays;
import com.schemecode.zr3i.data.models.show_details_lands.HistoricalNdvi;
import com.schemecode.zr3i.data.models.show_details_lands.Indicators;
import com.schemecode.zr3i.data.models.show_details_lands.WeatherExpectationObject;

import org.json.JSONException;

import java.util.List;
import java.util.Map;

public interface LandContract {
    interface view {
        void initUi();
        void initSpinner(List<ForecastingByDays> forecastingByDays , List<String> days);
        void initSoilAdapter(Indicators indicators);
        void initWeatherExcpectationsAdapter(List<List<WeatherExpectationObject>> weatherList);
        void handleClicks();
        void setData(Data data  , boolean is_land_details_available , String message) throws JSONException;
        void setDataOld(com.schemecode.zr3i.data.models.show_lands.Data data) throws JSONException;
        void showToast(String text);
        void unAuthorized(String message);
        void initBarChart(HistoricalNdvi historicalNdvi);
        void initValuesOfTheVegetationIndex(HistoricalNdvi historicalNdvi);
        void quotaFinishedError(String message);
        void showProgress(boolean isVisible);
    }
    interface presenter {
        void start();
        void showLand(int id , String token , String secret);
    }
}
