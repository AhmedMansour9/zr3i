package com.schemecode.zr3i.ui.activities.list_farmers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.schemecode.zr3i.R;
import com.schemecode.zr3i.adapters.ListFarmersAdapter;
import com.schemecode.zr3i.data.models.show_farmers.Datum;
import com.schemecode.zr3i.ui.activities.login_activity.LoginActivity;
import com.schemecode.zr3i.ui.dialogs.add_farmer_dialog.AddFarmerDialog;

import java.util.ArrayList;
import java.util.List;

public class ListFarmersActivity extends AppCompatActivity implements ListFarmersContract.view {

    private ListFarmersPresenter listFarmersPresenter;
    private SharedPreferences sharedPreferences;
    private String token , secret ;
    private RecyclerView rvFarmers ;
    private LinearLayoutManager linearLayoutManager ;
    private ImageView imgBack ;
    private ListFarmersAdapter listFarmersAdapter;
    private List<Datum> farmers;
    private ProgressBar progressBarListFarmers ;
    private TextView  tvAddFarmer;
    int landId;
    private RelativeLayout rlAddFarmers ;
    private FloatingActionButton fabAddFarmer ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_farmers);
        Intent intent = getIntent();
        landId = intent.getIntExtra("land_id" , 0);
        sharedPreferences = getSharedPreferences("MahmoudPref" , MODE_PRIVATE);
        token = sharedPreferences.getString("token" , "token");
        secret = sharedPreferences.getString("secret" , "secret");
        listFarmersPresenter = new ListFarmersPresenter(this);
        listFarmersPresenter.start();
        listFarmersPresenter.getFarmers(landId , token , secret);
    }

    @Override
    public void initUi() {
        farmers = new ArrayList<>();
        fabAddFarmer = findViewById(R.id.add_farmer_fab);
        rlAddFarmers = findViewById(R.id._no_farmers_relative);
        tvAddFarmer = findViewById(R.id.add_farmers_text_view);
        imgBack = findViewById(R.id.back_image_view);
        progressBarListFarmers = findViewById(R.id.progress_bar_list_farmers);
        imgBack = findViewById(R.id.back_image_view);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fabAddFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFarmerDialog addFarmerDialog = new AddFarmerDialog(ListFarmersActivity.this , landId , token , secret);
                addFarmerDialog.show();
            }
        });
    }

    @Override
    public void initRecycler() {
        rvFarmers = findViewById(R.id.farmers_recycler_view);
        listFarmersAdapter = new ListFarmersAdapter(farmers , token , secret);
        linearLayoutManager = new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false);
        rvFarmers.setLayoutManager(linearLayoutManager);
        rvFarmers.setAdapter(listFarmersAdapter);
        rvFarmers.setHasFixedSize(true);
    }

    @Override
    public void notifyAdapter(List<Datum> farmers) {
        this.farmers.clear();
        this.farmers.addAll(farmers);
        listFarmersAdapter.notifyDataSetChanged();
    }

    @Override
    public void showOrHideProgress(int visiblity) {
        progressBarListFarmers.setVisibility(visiblity == View.VISIBLE ? View.VISIBLE : View.GONE);
    }

    @Override
    public void noFarmers(String message) {
        rlAddFarmers.setVisibility(View.VISIBLE);
        String addFarmer = getResources().getString(R.string.add_farmer);
        SpannableString spannableString = new SpannableString(addFarmer);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                AddFarmerDialog addFarmerDialog = new AddFarmerDialog(ListFarmersActivity.this , landId , token , secret);
                addFarmerDialog.show();
            }
        };
        spannableString.setSpan(clickableSpan , addFarmer.length() - 11 , addFarmer.length() , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvAddFarmer.setText(spannableString);
        tvAddFarmer.setMovementMethod(LinkMovementMethod.getInstance());
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
    protected void onPause() {
        super.onPause();
        listFarmersPresenter.setView(null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        listFarmersPresenter.setView(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listFarmersPresenter.setView(ListFarmersActivity.this);
        listFarmersPresenter.getFarmers(landId , token , secret);
    }
}