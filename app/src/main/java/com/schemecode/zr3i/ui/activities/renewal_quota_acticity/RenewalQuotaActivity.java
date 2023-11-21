package com.schemecode.zr3i.ui.activities.renewal_quota_acticity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.schemecode.zr3i.R;
import com.schemecode.zr3i.data.models.plan_renew.Data;
import com.schemecode.zr3i.ui.activities.login_activity.LoginActivity;
import com.schemecode.zr3i.ui.activities.montada_web_view.MontadaActivity;
import com.schemecode.zr3i.ui.activities.upgrade_account_activity.UpgradeAccountActivity;
import com.schemecode.zr3i.ui.activities.usage_activity.UsageActivity;
import com.schemecode.zr3i.ui.dialogs.error_dialog.ErrorDialog;
import com.schemecode.zr3i.ui.dialogs.success_dialog.SuccessDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RenewalQuotaActivity extends AppCompatActivity implements RenewalQuotaContract.view {
    private ImageView imgBack;
    private TextView tvQuotaKind, tvPrice, tvUpdateDate, tvTotalPrice, tvNextRenewal;
    private SmartMaterialSpinner spMonth;
    private RenewalQuotaPresenter renewalQuotaPresenter;
    private AppCompatButton btnPay;
    int numberOfRenewalMonth = 0;
    private SharedPreferences sharedPreferences;
    private String token, secret;
    private ProgressBar progressBarRenewal;
    private com.schemecode.zr3i.data.models.usage.Data data;
    boolean isStooped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renewal_quota);
        Intent intent = getIntent();
        data = (com.schemecode.zr3i.data.models.usage.Data) intent.getSerializableExtra("quota_object");
        sharedPreferences = getSharedPreferences("MahmoudPref", MODE_PRIVATE);
        token = sharedPreferences.getString("token", "token");
        secret = sharedPreferences.getString("secret", "secret");
        renewalQuotaPresenter = new RenewalQuotaPresenter(this);
        renewalQuotaPresenter.start();
        renewalQuotaPresenter.setData(data);
    }

    @Override
    public void initUi() {
        progressBarRenewal = findViewById(R.id.renew_progress_bar);
        tvQuotaKind = findViewById(R.id.quota_kind_text_view);
        tvPrice = findViewById(R.id.price_text_view);
        tvUpdateDate = findViewById(R.id.updated_date_text_view);
        tvTotalPrice = findViewById(R.id.total_price_text_view);
        tvNextRenewal = findViewById(R.id.next_update_text_view);
        imgBack = findViewById(R.id.back_image_view);
        btnPay = findViewById(R.id.pay_button);
        btnPay.setEnabled(false);
    }

    @Override
    public void initSpinner() {
        List<Integer> monthsList = new ArrayList<>();
        monthsList.clear();
        spMonth = findViewById(R.id.update_to_spinner);
        for (int i = 0; i < 12; i++) {
            monthsList.add(i + 1);
        }
        spMonth.setItem(monthsList);
        spMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                btnPay.setEnabled(true);
                numberOfRenewalMonth = monthsList.get(position);
                tvTotalPrice.setText(numberOfRenewalMonth * data.getPrice() + " EGP");
                String[] dateSpilts = data.getRenewalDate().split("-");
                Calendar cal = Calendar.getInstance();
                cal.set(Integer.parseInt(dateSpilts[0]), Integer.parseInt(dateSpilts[1]), Integer.parseInt(dateSpilts[2]));
                cal.add(Calendar.MONTH, numberOfRenewalMonth - 1);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                tvNextRenewal.setText(dateFormat.format(cal.getTime()));
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
                if (numberOfRenewalMonth != 0) {
                    renewalQuotaPresenter.renew(numberOfRenewalMonth, "ar", "android", "hash_key", token, secret);
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
    public void setData(com.schemecode.zr3i.data.models.usage.Data data) {
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
            SuccessDialog successDialog = new SuccessDialog(RenewalQuotaActivity.this, message);
            successDialog.show();
        } else {
            ErrorDialog errorDialog = new ErrorDialog(RenewalQuotaActivity.this, message);
            errorDialog.show();
        }
    }

    @Override
    public void unAuthorized(String message) {
        if (getSharedPreferences("MahmoudPref", MODE_PRIVATE).edit().clear().commit() == true) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finishAffinity();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("paused", "paused");
    }


    @Override
    protected void onPause() {
        super.onPause();
        renewalQuotaPresenter.setView(null);
        Log.e("paused", "paused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        renewalQuotaPresenter.setView(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        renewalQuotaPresenter.setView(RenewalQuotaActivity.this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}