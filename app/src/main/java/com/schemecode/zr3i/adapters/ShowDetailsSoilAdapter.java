package com.schemecode.zr3i.adapters;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.schemecode.zr3i.R;
import com.schemecode.zr3i.data.models.show_details_lands.Indicators;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ShowDetailsSoilAdapter extends RecyclerView.Adapter<ShowDetailsSoilAdapter.SoilViewHolder> {

    private Context context;
    private Indicators indicators;

    public ShowDetailsSoilAdapter(Indicators indicators) {
        this.indicators = indicators;
    }

    public class SoilViewHolder extends RecyclerView.ViewHolder {

        private TextView tvField , tvSubtitle , tvDesc ;
        private RecyclerView rvSoilDtails;
        private LinearLayoutManager linearLayoutManager;
        private SoilDetailsAdapter soilDetailsAdapter;
        private AppCompatButton btnDetails ;
        private RelativeLayout rlSubtitleDetails ;
        public SoilViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            tvField = itemView.findViewById(R.id.healh_fields_text_view);
            btnDetails = itemView.findViewById(R.id.details_button);
            rvSoilDtails = itemView.findViewById(R.id.soil_details_recycler_view);
            rlSubtitleDetails = itemView.findViewById(R.id.subtitile_description_details_relative_layout);
            tvSubtitle = itemView.findViewById(R.id.subtitle_text_view);
            tvDesc = itemView.findViewById(R.id.details_text_view);
            btnDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (rlSubtitleDetails.getVisibility() == View.GONE){
                        rlSubtitleDetails.setVisibility(View.VISIBLE);
                    }
                    else {
                        rlSubtitleDetails.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public SoilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.soil_layout, parent, false);
        SoilViewHolder accountsTypesViewHolder = new SoilViewHolder(view);
        this.context = parent.getContext();
        return accountsTypesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SoilViewHolder holder, int position) {
        JSONObject jsonObject;
        Iterator<String> keysIterator;
        List<String> soildDetailsList;
        switch (position) {
            case 0:
                if (!indicators.getSi().getSubTitle().matches("")){
                    holder.btnDetails.setVisibility(View.VISIBLE);
                    holder.tvSubtitle.setText(indicators.getSi().getSubTitle());
                    holder.tvDesc.setText(Html.fromHtml(indicators.getSi().getDescription()));
                }
                holder.tvField.setText(indicators.getSi().getTitle() + " (" + indicators.getSi().getCode() + ")");
                jsonObject = new JSONObject((Map) indicators.getSi().getValues());
                keysIterator = jsonObject.keys();
                soildDetailsList = new ArrayList<>();
                soildDetailsList.clear();
                while (keysIterator.hasNext()) {
                    String key = keysIterator.next();
                    String value = null;
                    try {
                        value = key + "--" + jsonObject.get(key);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    soildDetailsList.add(value);
                }
                holder.soilDetailsAdapter = new SoilDetailsAdapter(context, soildDetailsList);
                holder.rvSoilDtails.setAdapter(holder.soilDetailsAdapter);
                holder.rvSoilDtails.setLayoutManager(holder.linearLayoutManager);
                holder.rvSoilDtails.setHasFixedSize(true);
                break;
            case 1:
                if (!indicators.getAvi().getSubTitle().matches("")){
                    holder.btnDetails.setVisibility(View.VISIBLE);
                    holder.tvSubtitle.setText(indicators.getAvi().getSubTitle());
                    holder.tvDesc.setText(indicators.getAvi().getDescription());
                }
                holder.tvField.setText(indicators.getAvi().getTitle() + " (" + indicators.getAvi().getCode() + ")");
                jsonObject = new JSONObject((Map) indicators.getAvi().getValues());
                keysIterator = jsonObject.keys();
                soildDetailsList = new ArrayList<>();
                soildDetailsList.clear();
                while (keysIterator.hasNext()) {
                    String key = keysIterator.next();
                    String value = null;
                    try {
                        value = key + "--" + jsonObject.get(key);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    soildDetailsList.add(value);
                }
                holder.soilDetailsAdapter = new SoilDetailsAdapter(context, soildDetailsList);
                holder.rvSoilDtails.setAdapter(holder.soilDetailsAdapter);
                holder.rvSoilDtails.setLayoutManager(holder.linearLayoutManager);
                holder.rvSoilDtails.setHasFixedSize(true);
                break;
            case 2:
                if (!indicators.getBsi().getSubTitle().matches("")){
                    holder.btnDetails.setVisibility(View.VISIBLE);
                    holder.tvSubtitle.setText(indicators.getBsi().getSubTitle());
                    holder.tvDesc.setText(indicators.getBsi().getDescription());
                }
                holder.tvField.setText(indicators.getBsi().getTitle() + " (" + indicators.getBsi().getCode() + ")");
                jsonObject = new JSONObject((Map) indicators.getBsi().getValues());
                keysIterator = jsonObject.keys();
                soildDetailsList = new ArrayList<>();
                soildDetailsList.clear();
                while (keysIterator.hasNext()) {
                    String key = keysIterator.next();
                    String value = null;
                    try {
                        value = key + "--" + jsonObject.get(key);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    soildDetailsList.add(value);
                }
                holder.soilDetailsAdapter = new SoilDetailsAdapter(context, soildDetailsList);
                holder.rvSoilDtails.setAdapter(holder.soilDetailsAdapter);
                holder.rvSoilDtails.setLayoutManager(holder.linearLayoutManager);
                holder.rvSoilDtails.setHasFixedSize(true);
                break;
            case 3:
                if (!indicators.getEvi().getSubTitle().matches("")){
                    holder.btnDetails.setVisibility(View.VISIBLE);
                    holder.tvSubtitle.setText(indicators.getEvi().getSubTitle());
                    holder.tvDesc.setText(indicators.getEvi().getDescription());
                }
                holder.tvField.setText(indicators.getEvi().getTitle() + " (" + indicators.getEvi().getCode() + ")");
                jsonObject = new JSONObject((Map) indicators.getEvi().getValues());
                keysIterator = jsonObject.keys();
                soildDetailsList = new ArrayList<>();
                soildDetailsList.clear();
                while (keysIterator.hasNext()) {
                    String key = keysIterator.next();
                    String value = null;
                    try {
                        value = key + "--" + jsonObject.get(key);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    soildDetailsList.add(value);
                }
                holder.soilDetailsAdapter = new SoilDetailsAdapter(context, soildDetailsList);
                holder.rvSoilDtails.setAdapter(holder.soilDetailsAdapter);
                holder.rvSoilDtails.setLayoutManager(holder.linearLayoutManager);
                holder.rvSoilDtails.setHasFixedSize(true);
                break;
            case 4:
                if (!indicators.getSoc().getSubTitle().matches("")){
                    holder.btnDetails.setVisibility(View.VISIBLE);
                    holder.tvSubtitle.setText(indicators.getSoc().getSubTitle());
                    holder.tvDesc.setText(indicators.getSoc().getDescription());
                }
                holder.tvField.setText(indicators.getSoc().getTitle() + " (" + indicators.getSoc().getCode() + ")");
                jsonObject = new JSONObject((Map) indicators.getSoc().getValues());
                keysIterator = jsonObject.keys();
                soildDetailsList = new ArrayList<>();
                soildDetailsList.clear();
                while (keysIterator.hasNext()) {
                    String key = keysIterator.next();
                    String value = null;
                    try {
                        value = key + "--" + jsonObject.get(key);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    soildDetailsList.add(value);
                }
                holder.soilDetailsAdapter = new SoilDetailsAdapter(context, soildDetailsList);
                holder.rvSoilDtails.setAdapter(holder.soilDetailsAdapter);
                holder.rvSoilDtails.setLayoutManager(holder.linearLayoutManager);
                holder.rvSoilDtails.setHasFixedSize(true);
                break;
            case 5:
                if (!indicators.getNdre().getSubTitle().matches("")){
                    holder.btnDetails.setVisibility(View.VISIBLE);
                    holder.tvSubtitle.setText(indicators.getNdre().getSubTitle());
                    holder.tvDesc.setText(indicators.getNdre().getDescription());
                }
                holder.tvField.setText(indicators.getNdre().getTitle() + " (" + indicators.getNdre().getCode() + ")");
                jsonObject = new JSONObject((Map) indicators.getNdre().getValues());
                keysIterator = jsonObject.keys();
                soildDetailsList = new ArrayList<>();
                soildDetailsList.clear();
                while (keysIterator.hasNext()) {
                    String key = keysIterator.next();
                    String value = null;
                    try {
                        value = key + "--" + jsonObject.get(key);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    soildDetailsList.add(value);
                }
                holder.soilDetailsAdapter = new SoilDetailsAdapter(context, soildDetailsList);
                holder.rvSoilDtails.setAdapter(holder.soilDetailsAdapter);
                holder.rvSoilDtails.setLayoutManager(holder.linearLayoutManager);
                holder.rvSoilDtails.setHasFixedSize(true);
                break;
            case 6:
                if (!indicators.getNdvi().getSubTitle().matches("")){
                    holder.btnDetails.setVisibility(View.VISIBLE);
                    holder.tvSubtitle.setText(indicators.getNdvi().getSubTitle());
                    holder.tvDesc.setText(indicators.getNdvi().getDescription());
                }
                holder.tvField.setText(indicators.getNdvi().getTitle() + " (" + indicators.getNdvi().getCode() + ")");
                jsonObject = new JSONObject((Map) indicators.getNdvi().getValues());
                keysIterator = jsonObject.keys();
                soildDetailsList = new ArrayList<>();
                soildDetailsList.clear();
                while (keysIterator.hasNext()) {
                    String key = keysIterator.next();
                    String value = null;
                    try {
                        value = key + "--" + jsonObject.get(key);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    soildDetailsList.add(value);
                }
                holder.soilDetailsAdapter = new SoilDetailsAdapter(context, soildDetailsList);
                holder.rvSoilDtails.setAdapter(holder.soilDetailsAdapter);
                holder.rvSoilDtails.setLayoutManager(holder.linearLayoutManager);
                holder.rvSoilDtails.setHasFixedSize(true);
                break;
            case 7:
                if (!indicators.getNdwi().getSubTitle().matches("")){
                    holder.btnDetails.setVisibility(View.VISIBLE);
                    holder.tvSubtitle.setText(indicators.getNdwi().getSubTitle());
                    holder.tvDesc.setText(indicators.getNdwi().getDescription());
                }
                holder.tvField.setText(indicators.getNdwi().getTitle() + " (" + indicators.getNdwi().getCode() + ")");
                jsonObject = new JSONObject((Map) indicators.getNdwi().getValues());
                keysIterator = jsonObject.keys();
                soildDetailsList = new ArrayList<>();
                soildDetailsList.clear();
                while (keysIterator.hasNext()) {
                    String key = keysIterator.next();
                    String value = null;
                    try {
                        value = key + "--" + jsonObject.get(key);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    soildDetailsList.add(value);
                }
                holder.soilDetailsAdapter = new SoilDetailsAdapter(context, soildDetailsList);
                holder.rvSoilDtails.setAdapter(holder.soilDetailsAdapter);
                holder.rvSoilDtails.setLayoutManager(holder.linearLayoutManager);
                holder.rvSoilDtails.setHasFixedSize(true);
                break;
            case 8:
                if (!indicators.getSavi().getSubTitle().matches("")){
                    holder.btnDetails.setVisibility(View.VISIBLE);
                    holder.tvSubtitle.setText(indicators.getSavi().getSubTitle());
                    holder.tvDesc.setText(indicators.getSavi().getDescription());
                }
                holder.tvField.setText(indicators.getSavi().getTitle() + " (" + indicators.getSavi().getCode() + ")");
                jsonObject = new JSONObject((Map) indicators.getSavi().getValues());
                keysIterator = jsonObject.keys();
                soildDetailsList = new ArrayList<>();
                soildDetailsList.clear();
                while (keysIterator.hasNext()) {
                    String key = keysIterator.next();
                    String value = null;
                    try {
                        value = key + "--" + jsonObject.get(key);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    soildDetailsList.add(value);
                }
                holder.soilDetailsAdapter = new SoilDetailsAdapter(context, soildDetailsList);
                holder.rvSoilDtails.setAdapter(holder.soilDetailsAdapter);
                holder.rvSoilDtails.setLayoutManager(holder.linearLayoutManager);
                holder.rvSoilDtails.setHasFixedSize(true);
                break;
            case 9:
                if (!indicators.getVari().getSubTitle().matches("")){
                    holder.btnDetails.setVisibility(View.VISIBLE);
                    holder.tvSubtitle.setText(indicators.getVari().getSubTitle());
                    holder.tvDesc.setText(indicators.getVari().getDescription());
                }
                holder.tvField.setText(indicators.getVari().getTitle() + " (" + indicators.getVari().getCode() + ")");
                jsonObject = new JSONObject((Map) indicators.getVari().getValues());
                keysIterator = jsonObject.keys();
                soildDetailsList = new ArrayList<>();
                soildDetailsList.clear();
                while (keysIterator.hasNext()) {
                    String key = keysIterator.next();
                    String value = null;
                    try {
                        value = key + "--" + jsonObject.get(key);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    soildDetailsList.add(value);
                }
                holder.soilDetailsAdapter = new SoilDetailsAdapter(context, soildDetailsList);
                holder.rvSoilDtails.setAdapter(holder.soilDetailsAdapter);
                holder.rvSoilDtails.setLayoutManager(holder.linearLayoutManager);
                holder.rvSoilDtails.setHasFixedSize(true);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }


}
