package com.schemecode.zr3i.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.schemecode.zr3i.R;
import com.schemecode.zr3i.data.api.ApiClient;
import com.schemecode.zr3i.data.api.ApiService;
import com.schemecode.zr3i.data.models.delete_farmer.FarmersDelete;
import com.schemecode.zr3i.data.models.show_farmers.Datum;
import com.schemecode.zr3i.ui.activities.Farmer.FarmerActivity;
import com.schemecode.zr3i.ui.activities.Farmer.FarmerPresenter;
import com.schemecode.zr3i.ui.activities.land_activity.LandActivity;
import com.schemecode.zr3i.ui.dialogs.delete_dialog.DeleteDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFarmersAdapter extends RecyclerView.Adapter<ListFarmersAdapter.ListFarmersViewHolder> {

    private Context context;
    private List<Datum> farmerList;
    private OnItemClickListener mListener;
    private ApiService apiService;
    private String token , secret ;

    public ListFarmersAdapter(List<Datum> farmerList , String token , String secret) {
        this.farmerList = farmerList;
        this.token = token;
        this.secret = secret;
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    public class ListFarmersViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameOfFarmer , tvPhone ,tvCityOfFarmer , tvAgeOfFarmer , tvAddressOfFarmer , tvEmail , tvIdOfFarmer ;
        private RelativeLayout rlDelete , rlEdit ;
        public ListFarmersViewHolder(@NonNull View itemView  , final OnItemClickListener listiner) {
            super(itemView);
            tvNameOfFarmer = itemView.findViewById(R.id.name_of_farmer_text_view);
            tvPhone = itemView.findViewById(R.id.phone_of_farmer_text_view);
            tvAddressOfFarmer = itemView.findViewById(R.id.address_of_farmer_text_view);
            tvCityOfFarmer = itemView.findViewById(R.id.government_of_farmer_text_view);
            tvAgeOfFarmer = itemView.findViewById(R.id.age_of_farmer_text_view);
            tvEmail = itemView.findViewById(R.id.email_of_farmer_text_view);
            tvIdOfFarmer = itemView.findViewById(R.id.code_of_farmer_text_view);
            rlEdit = itemView.findViewById(R.id.edit_farmer_relative);
            rlDelete = itemView.findViewById(R.id.delete_farmer_relative);
        }
    }

    @NonNull
    @Override
    public ListFarmersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.farmer_layout, parent, false);
        ListFarmersViewHolder listFarmersViewHolder = new ListFarmersViewHolder(view , mListener);
        this.context = parent.getContext();
        return listFarmersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListFarmersViewHolder holder, int position) {
        Datum farmer = farmerList.get(position);
        holder.tvNameOfFarmer.setText(farmer.getName());
        if (farmer.getAge() != null){
            holder.tvAgeOfFarmer.setText(farmer.getAge() + "");
            holder.tvAgeOfFarmer.setVisibility(View.VISIBLE);
        }
        if (farmer.getAddress() != null){
            holder.tvAddressOfFarmer.setText(farmer.getAddress());
            holder.tvAddressOfFarmer.setVisibility(View.VISIBLE);
        }
        if (farmer.getCity() != null){
            holder.tvCityOfFarmer.setText(farmer.getCity());
            holder.tvCityOfFarmer.setVisibility(View.VISIBLE);
        }
        if (farmer.getEmail() != null){
            holder.tvEmail.setText(farmer.getEmail());
            holder.tvEmail.setVisibility(View.VISIBLE);
        }
        if (farmer.getMobile() != null){
            holder.tvPhone.setText(farmer.getMobile());
            holder.tvPhone.setVisibility(View.VISIBLE);
        }
        if (farmer.getNationalId() != null){
            holder.tvIdOfFarmer.setText(farmer.getNationalId());
            holder.tvIdOfFarmer.setVisibility(View.VISIBLE);
        }
        holder.tvIdOfFarmer.setText(farmer.getNationalId());
        holder.rlEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , FarmerActivity.class);
                intent.putExtra("FarmerObj" , farmer);
                context.startActivity(intent);
            }
        });
        holder.rlDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteDialog deleteDialog = new DeleteDialog(context , token , secret , farmer.getId() , "farmer");
                deleteDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return farmerList.size();
    }

}
