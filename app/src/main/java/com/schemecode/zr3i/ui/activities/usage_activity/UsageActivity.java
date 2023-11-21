package com.schemecode.zr3i.ui.activities.usage_activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSeekBar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.marcinmoskala.arcseekbar.ArcSeekBar;
import com.schemecode.zr3i.R;
import com.schemecode.zr3i.data.models.usage.Data;
import com.schemecode.zr3i.ui.activities.login_activity.LoginActivity;
import com.schemecode.zr3i.ui.activities.renewal_quota_acticity.RenewalQuotaActivity;
import com.schemecode.zr3i.ui.activities.upgrade_account_activity.UpgradeAccountActivity;
import com.schemecode.zr3i.ui.custom_views.customseekbar;

import me.tankery.lib.circularseekbar.CircularSeekBar;

public class UsageActivity extends AppCompatActivity implements UsageContract.view{

    private TextView tvQuota , tvPrice , tvRenewalDate , tvRemainingDays , tvActualSpace , tvUsedSpace , tvReminigSpace ;
    private UsagePresenter usagePresenter;
    private SharedPreferences sharedPreferences;
    private String token , secret ;
    private ArcSeekBar arcSeekBar;
    private ImageView imgBack ;
    private AppCompatButton btnUpgradeAccount , btnRenewalQuota ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage);
        sharedPreferences = getSharedPreferences("MahmoudPref" , MODE_PRIVATE);
        token = sharedPreferences.getString("token" , token);
        secret = sharedPreferences.getString("secret" , secret);
        usagePresenter = new UsagePresenter(this);
        usagePresenter.start();
        usagePresenter.getUsage(token , secret);
    }

    @Override
    public void initUi() {
        imgBack = findViewById(R.id.back_image_view);
        tvQuota = findViewById(R.id.quota_text_view);
        tvPrice = findViewById(R.id.price_text_view);
        tvRenewalDate = findViewById(R.id.renewal_data_text_view);
        tvRemainingDays = findViewById(R.id.remaining_days_text_view);
        tvActualSpace = findViewById(R.id.total_space_text_view);
        tvUsedSpace = findViewById(R.id.used_space_text_view);
        tvReminigSpace = findViewById(R.id.remaining_space);
        arcSeekBar = findViewById(R.id.usage_seekbar);
        btnRenewalQuota = findViewById(R.id.renewal_quota_button);
        btnUpgradeAccount = findViewById(R.id.upgrade_account_button);
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setData(Data data) {
        tvQuota.setText(data.getTitle());
        tvPrice.setText(data.getPrice() + "");
        tvRenewalDate.setText(data.getRenewalDate());
        tvRemainingDays.setText(data.getRenewalRemainingDaysRead()+"");
        tvActualSpace.setText(data.getUsageDetails().getUsage().getTitle());
        tvUsedSpace.setText(data.getUsageDetails().getUsed().getTitle());
        tvReminigSpace.setText(data.getUsageDetails().getRemaining().getTitle());
        arcSeekBar.setProgress((int) Math.round(data.getUsageDetails().getUsed().getPercent()));
        if (data.getIsRenewAvailable() == true){
            btnRenewalQuota.setVisibility(View.VISIBLE);
            btnRenewalQuota.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UsageActivity.this , RenewalQuotaActivity.class);
                    intent.putExtra("quota_object" , data);
                    startActivity(intent);
                }
            });
        }
        if (data.getIsUpgradeAvailable() == true && data.getId() != 0){
            btnUpgradeAccount.setVisibility(View.VISIBLE);
            btnUpgradeAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UsageActivity.this , UpgradeAccountActivity.class);
                    intent.putExtra("quota_object" , data);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
        usagePresenter.setView(null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        usagePresenter.setView(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        usagePresenter.setView(UsageActivity.this);
    }
}