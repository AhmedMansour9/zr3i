package com.schemecode.zr3i.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.schemecode.zr3i.R;
import com.schemecode.zr3i.data.classes.PrefLand;
import com.schemecode.zr3i.data.models.list_lands.Datum;
import com.schemecode.zr3i.ui.activities.edit_land_details.EditLandActivity;
import com.schemecode.zr3i.ui.activities.land_activity.LandActivity;
import com.schemecode.zr3i.ui.activities.land_map_activity.LandMapActivity;
import com.schemecode.zr3i.ui.activities.list_farmers.ListFarmersActivity;
import com.schemecode.zr3i.ui.dialogs.delete_dialog.DeleteDialog;

import java.util.List;

public class ListLandsAdapter extends RecyclerView.Adapter<ListLandsAdapter.ListLandsViewHolder> {

    private Context context;
    private List<Datum> landsList;
    private OnItemClickListener mListener;
    private String token , secret ;

    public ListLandsAdapter(List<Datum> landsList, String token, String secret) {
        this.landsList = landsList;
        this.token = token;
        this.secret = secret;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    public class ListLandsViewHolder extends RecyclerView.ViewHolder {
        private AppCompatButton btnShowDetails , btnEditLand , btnFarmers , btnShowMap , btnDeleteLand;
        private TextView tvNameOfLand , tvCity , tvCreatedDate , tvUpdatedDate , tvCropType , tvContractSpace , tvActualSpace , tvDesc;
        private int indexOfAccountType ;
        private ImageView  imgSecondLand ;
        public ListLandsViewHolder(@NonNull View itemView  , final OnItemClickListener listiner) {
            super(itemView);
            btnFarmers = itemView.findViewById(R.id.farmers_button);
            btnShowDetails = itemView.findViewById(R.id.show_details_button);
            btnEditLand = itemView.findViewById(R.id.edit_land_button);
            btnFarmers = itemView.findViewById(R.id.farmers_button);
            btnShowMap = itemView.findViewById(R.id.map_location_button);
            btnDeleteLand = itemView.findViewById(R.id.delete_land_button);
            tvNameOfLand = itemView.findViewById(R.id.owener_text_view);
            tvCreatedDate = itemView.findViewById(R.id.creation_date_dynamic_text_view);
            tvUpdatedDate = itemView.findViewById(R.id.last_update_static_text_view);
            tvCropType = itemView.findViewById(R.id.crop_name_text_view);
            tvContractSpace = itemView.findViewById(R.id.contract_space_text_view);
            tvActualSpace = itemView.findViewById(R.id.actual_space_text_view);
            tvCity = itemView.findViewById(R.id.city_text_view);
            tvDesc = itemView.findViewById(R.id.desc_text_view);
            imgSecondLand = itemView.findViewById(R.id.map_image_view);
        }
    }

    @NonNull
    @Override
    public ListLandsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.land_layout, parent, false);
        ListLandsViewHolder listLandsViewHolder = new ListLandsViewHolder(view , mListener);
        this.context = parent.getContext();
        return listLandsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListLandsViewHolder holder, int position) {
        Datum land = landsList.get(position);
        holder.tvNameOfLand.setText(land.getOwner());
        holder.tvActualSpace.setText("المساحة الفعلية : "+ land.getActualSpace() + " فدان");
        holder.tvContractSpace.setText("مساحة العقد : " + land.getContractSpace()  + " فدان");
        holder.tvCropType.setText(land.getCrop().getTitle());
        holder.tvCreatedDate.setText(land.getCreatedAtFormated());
        holder.tvUpdatedDate.setText("اخر تحديث #"+land.getUpdatedAtFormated());
        holder.tvCity.setText(land.getCountry().getTitleAr());
        Glide.with(context).load(land.getMap_image_url()).into(holder.imgSecondLand);
        if (land.getDesc() != null){
            holder.tvDesc.setText(land.getDesc());
            holder.tvDesc.setVisibility(View.VISIBLE);
        }
        holder.btnShowDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent landIntent = new Intent(context , LandActivity.class);
                landIntent.putExtra("land_id" , land.getId());
                landIntent.putExtra("crop_type" , land.getCrop().getTitle());
                context.startActivity(landIntent);
            }
        });
        holder.btnFarmers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent famersIntent = new Intent(context , ListFarmersActivity.class);
                famersIntent.putExtra("land_id" , land.getId());
                context.startActivity(famersIntent);
            }
        });
        holder.btnEditLand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefLand prefLand = new PrefLand(land.getId() , land.getOwner() , land.getCrop().getTitle() , land.getCountry().getTitle() , land.getCity() , land.getCityArea() , land.getCropPlantingCycles(), land.getContractSpace() , land.getActualSpace() , land.getDesc());
                Intent editLandIntent = new Intent(context , EditLandActivity.class);
                editLandIntent.putExtra("land_obj" ,prefLand);
                context.startActivity(editLandIntent);
            }
        });
        holder.btnShowMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent landMapIntent = new Intent(context , LandMapActivity.class);
                landMapIntent.putExtra("land_id" ,land.getId());
                context.startActivity(landMapIntent);
            }
        });
        holder.btnDeleteLand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteDialog deleteDialog = new DeleteDialog(context , token , secret , land.getId() , "land");
                deleteDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return landsList.size();
    }

}
