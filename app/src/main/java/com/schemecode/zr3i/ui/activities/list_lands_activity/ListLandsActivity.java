package com.schemecode.zr3i.ui.activities.list_lands_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.schemecode.zr3i.R;
import com.schemecode.zr3i.adapters.ListLandsAdapter;
import com.schemecode.zr3i.data.models.list_lands.Datum;
import com.schemecode.zr3i.ui.activities.home_activity.HomeActivity;
import com.schemecode.zr3i.ui.activities.login_activity.LoginActivity;
import com.schemecode.zr3i.ui.dialogs.error_dialog.ErrorDialog;

import java.util.ArrayList;
import java.util.List;

public class ListLandsActivity extends AppCompatActivity implements ListLandsContract.view{

    private RecyclerView rvListLands ;
    private ImageView imgBack ;
    private LinearLayoutManager linearLayoutManager;
    private ListLandsPresenter listLandsPresenter;
    private ListLandsAdapter listLandsAdapter;
    private List<Datum> landList ;
    private SharedPreferences sharedPreferences;
    private String token , secret ;
    private ProgressBar progressBarListLand ;
    private TextView tvNoLands ;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private int currentPage = 1 , lastPage = 0 , next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lands);
        sharedPreferences = getSharedPreferences("MahmoudPref" , MODE_PRIVATE);
        token = sharedPreferences.getString("token" , "token");
        secret = sharedPreferences.getString("secret" , "secret");
        listLandsPresenter = new ListLandsPresenter(this);
        listLandsPresenter.start();
        listLandsPresenter.getLmyLandsList(currentPage ,token , secret);
    }

    @Override
    public void initUi() {
        imgBack = findViewById(R.id.back_image);
        progressBarListLand = findViewById(R.id.list_lands_progress);
        tvNoLands = findViewById(R.id.no_lands_text_view);
    }

    @Override
    public void initRecycler() {
        landList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(ListLandsActivity.this , LinearLayoutManager.VERTICAL , false);
        listLandsAdapter = new ListLandsAdapter(landList , token , secret);
        rvListLands = findViewById(R.id.list_lands_recycler_view);
        rvListLands.setLayoutManager(linearLayoutManager);
        rvListLands.setAdapter(listLandsAdapter);
        landList.clear();

    }

    @Override
    public void notifyAdapter(List<Datum> list , int lastPage) {
        landList.addAll(list);
        listLandsAdapter.notifyDataSetChanged();
        this.lastPage = lastPage;
        Log.e("called" , "called");
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showOrHideProgress(int visibilty) {
        progressBarListLand.setVisibility(visibilty == View.VISIBLE ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showFailureDialog(String message) {
        ErrorDialog errorDialog = new ErrorDialog(ListLandsActivity.this , message);
        errorDialog.show();
    }

    @Override
    public void noData() {
        tvNoLands.setVisibility(View.VISIBLE);
    }

    @Override
    public void handleClicks() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
    public void paginate() {
        rvListLands.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            Log.v("...", "Last Item Wow !");
                            // Do pagination.. i.e. fetch new data
                            if (lastPage != 0 && lastPage > currentPage){
                                currentPage ++;
                                listLandsPresenter.getLmyLandsList(currentPage , token , secret);
                            }
                            loading = true;
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        listLandsPresenter.setView(null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        listLandsPresenter.setView(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listLandsPresenter.setView(ListLandsActivity.this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}