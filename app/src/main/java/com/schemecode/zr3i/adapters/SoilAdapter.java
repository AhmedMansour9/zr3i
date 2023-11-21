package com.schemecode.zr3i.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.schemecode.zr3i.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SoilAdapter extends RecyclerView.Adapter<SoilAdapter.SoilViewHolder> {

    private Context context;
    private Map<String, Map<String, String>> soilMap;
    List<String> keyList ;
    public SoilAdapter(Map<String, Map<String, String>> soilMap) {
        this.soilMap = soilMap;
        keyList = new ArrayList<String>(soilMap.keySet());
    }

    public class SoilViewHolder extends RecyclerView.ViewHolder {

        private TextView tvField;
        private RecyclerView rvSoilDtails ;
        private LinearLayoutManager linearLayoutManager ;
        private SoilDetailsAdapter soilDetailsAdapter;
        public SoilViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayoutManager = new LinearLayoutManager(context , LinearLayoutManager.VERTICAL , false);
            tvField = itemView.findViewById(R.id.healh_fields_text_view);
//            rvSoilDtails = itemView.findViewById(R.id.inner_soil_recycler_view);

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
        String key = keyList.get(position);
        Log.e("keyyyyyyyyyyyy" , key);
        Map<String , String> innerMap = soilMap.get(key);
        Iterator innerKeys = innerMap.entrySet().iterator();
        holder.tvField.setText(key);
//        holder.soilDetailsAdapter = new SoilDetailsAdapter(innerMap);
        holder.rvSoilDtails.setLayoutManager(holder.linearLayoutManager);
        holder.rvSoilDtails.setAdapter(holder.soilDetailsAdapter);
        holder.rvSoilDtails.setHasFixedSize(false);

        while (innerKeys.hasNext()){
            Map.Entry pair = (Map.Entry) innerKeys.next();
            String innerKey = (String) pair.getKey();
            String innerValue = (String) pair.getValue();
            Log.e("SoildAdapterKey" , innerKey);
            Log.e("SoildAdapterValue" + "" , innerValue);
        }
    }

    @Override
    public int getItemCount() {
        return keyList.size();
    }


}
