package com.schemecode.zr3i.ui.activities.upgrade_account_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.schemecode.zr3i.R;
import com.schemecode.zr3i.data.models.plans.Datum;
import com.schemecode.zr3i.data.models.usage.Data;
import com.schemecode.zr3i.data.models.usage.PlansForUpgrade;
import com.schemecode.zr3i.ui.activities.login_activity.LoginActivity;
import com.schemecode.zr3i.ui.activities.montada_web_view.MontadaActivity;
import com.schemecode.zr3i.ui.activities.renewal_quota_acticity.RenewalQuotaActivity;
import com.schemecode.zr3i.ui.activities.renewal_quota_acticity.RenewalQuotaPresenter;
import com.schemecode.zr3i.ui.activities.usage_activity.UsageActivity;
import com.schemecode.zr3i.ui.dialogs.error_dialog.ErrorDialog;
import com.schemecode.zr3i.ui.dialogs.success_dialog.SuccessDialog;

import java.util.ArrayList;
import java.util.List;

public class UpgradeAccountActivity extends AppCompatActivity implements UpgradeAccountContract.view{
    private ImageView imgBack ;
    private TextView tvQuotaKind, tvPrice, tvUpdateDate ;
    private SmartMaterialSpinner spQuota;
    private UpgradeAccountPresenter upgradeAccountPresenter;
    private AppCompatButton btnPay;
    private SharedPreferences sharedPreferences;
    private String token, secret;
    private ProgressBar progressBarRenewal;
    private com.schemecode.zr3i.data.models.usage.Data data;
    int planId = -1 ;
    List<PlansForUpgrade> plansForUpgrades;
    private boolean isStooped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_account);
        Intent intent = getIntent();
        data = (com.schemecode.zr3i.data.models.usage.Data) intent.getSerializableExtra("quota_object");
        plansForUpgrades = data.getPlansForUpgrade();
        sharedPreferences = getSharedPreferences("MahmoudPref", MODE_PRIVATE);
        token = sharedPreferences.getString("token", "token");
        secret = sharedPreferences.getString("secret", "secret");
        upgradeAccountPresenter = new UpgradeAccountPresenter(this);
        upgradeAccountPresenter.start();
        upgradeAccountPresenter.setData(data);
    }

    @Override
    public void initUi() {
        progressBarRenewal = findViewById(R.id.renew_progress_bar);
        tvQuotaKind = findViewById(R.id.quota_kind_text_view);
        tvPrice = findViewById(R.id.price_text_view);
        tvUpdateDate = findViewById(R.id.updated_date_text_view);
        imgBack = findViewById(R.id.back_image_view);
        btnPay = findViewById(R.id.pay_button);
        btnPay.setEnabled(false);
    }

    @Override
    public void initSpinner() {
        spQuota = findViewById(R.id.quota_spinner);
        ArrayList<String> plansList = new ArrayList<>();
        for (int i = 0; i < plansForUpgrades.size(); i++) {
            plansList.add(plansForUpgrades.get(i).getTitle());
        }
        spQuota.setItem(plansList);
        spQuota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                planId = plansForUpgrades.get(position).getId();
                btnPay.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void handleCLicks() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (planId != -1) {
                    upgradeAccountPresenter.upgrade(planId, "ar", "android", "hash_key", token, secret);
                }
            }
        });
    }

    @Override
    public void passPaymentUrl(String url) {
//        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        Intent browserIntent = new Intent(this, MontadaActivity.class);
        browserIntent.putExtra("url" , url);
        startActivity(browserIntent);

    }

    @Override
    public void setData(Data data) {
        tvQuotaKind.setText(data.getTitle());
        tvPrice.setText(data.getPriceTitle());
        tvUpdateDate.setText(data.getRenewalDate());
    }



    @Override
    public void showOrHideProgress(boolean isLoading) {
        if (isLoading == true) {
            progressBarRenewal.setVisibility(View.VISIBLE);
            btnPay.setEnabled(false);
        } else {
            progressBarRenewal.setVisibility(View.GONE);
            btnPay.setEnabled(true);
        }
    }

    @Override
    public void inflateDialog(boolean isSuccess, String message) {
        if (isSuccess == true) {
            SuccessDialog successDialog = new SuccessDialog(UpgradeAccountActivity.this, message);
            successDialog.show();
        } else {
            ErrorDialog errorDialog = new ErrorDialog(UpgradeAccountActivity.this, message);
            errorDialog.show();
        }
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
        upgradeAccountPresenter.setView(null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        upgradeAccountPresenter.setView(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        upgradeAccountPresenter.setView(UpgradeAccountActivity.this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}