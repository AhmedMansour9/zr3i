package com.schemecode.zr3i.ui.activities.Farmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputEditText;
import com.schemecode.zr3i.R;
import com.schemecode.zr3i.ui.activities.login_activity.LoginActivity;
import com.schemecode.zr3i.ui.activities.splash_activity.LoginSplashActivity;
import com.schemecode.zr3i.ui.dialogs.error_dialog.ErrorDialog;
import com.schemecode.zr3i.ui.dialogs.success_dialog.SuccessDialog;
import com.schemecode.zr3i.data.models.show_farmers.Datum;
public class FarmerActivity extends AppCompatActivity implements FarmerContract.view{

    private TextInputEditText etName , etCity , etMobile , etAge , etMail , etNationalId , etAdress ;
    private FarmerPresenter farmerPresenter ;
    private AppCompatButton btnEdit ;
    private Datum farmer ;
    private SharedPreferences sharedPreferences ;
    private String token , secret ;
    private ProgressBar progressBarEditFarmer ;
    private ImageView imgEdit ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer);
        sharedPreferences = getSharedPreferences("MahmoudPref" , MODE_PRIVATE);
        token = sharedPreferences.getString("token" , "token");
        secret = sharedPreferences.getString("secret" , "secret");
        Intent intent = getIntent();
        farmer = (Datum) intent.getSerializableExtra("FarmerObj");
        farmerPresenter = new FarmerPresenter(this);
        farmerPresenter.start();
        farmerPresenter.setData(farmer);
    }

    @Override
    public void initUi() {
        progressBarEditFarmer = findViewById(R.id.edit_farmer_progress_bar);
        etName = findViewById(R.id.farmer_name_edit_text);
        etCity = findViewById(R.id.city_edit_text);
        etMobile = findViewById(R.id.phone_number_edit_text);
        etAge = findViewById(R.id.age_name_edit_text);
        etMail = findViewById(R.id.mail_edit_text);
        etNationalId = findViewById(R.id.national_id_edit_text);
        etAdress = findViewById(R.id.address_edit_text);
        btnEdit = findViewById(R.id.edit_farmer_button);
        imgEdit = findViewById(R.id.back_image_view);
    }

    @Override
    public void handleClicks() {
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                farmerPresenter.updateFarmer(farmer.getId() , etName.getText().toString().trim() , etAge.getText().toString().trim() , etMobile.getText().toString().trim() ,
                        etCity.getText().toString().trim() ,
                        etMail.getText().toString().trim() , etNationalId.getText().toString().trim() ,
                        etAdress.getText().toString().trim() , token , secret);
            }
        });
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setData(Datum farmer) {
        etName.setText(farmer.getName());
        etCity.setText(farmer.getCity());
        if (farmer.getAge() != null){
            etAge.setText(farmer.getAge() + "");
        }
        etMobile.setText(farmer.getMobile());
        etMail.setText(farmer.getEmail());
        etNationalId.setText(farmer.getNationalId());
        etAdress.setText(farmer.getAddress());

    }

    @Override
    public void showSuccessOrFailed(boolean is_success, String message) {
        if (is_success == true){
            SuccessDialog successDialog = new SuccessDialog(FarmerActivity.this , message);
            successDialog.show();
        }
        else {
            ErrorDialog errorDialog = new ErrorDialog(FarmerActivity.this , message);
            errorDialog.show();
        }
    }

    @Override
    public void unAuthorized(String message) {
        if (getSharedPreferences("MahmoudPref" , MODE_PRIVATE).edit().clear().commit() == true){
            Intent intent = new Intent(this , LoginSplashActivity.class);
            startActivity(intent);
            finishAffinity();
        }

    }

    @Override
    public void showOrHideProgressBar(int visibilty) {
        progressBarEditFarmer.setVisibility(visibilty == View.VISIBLE ? View.VISIBLE : View.GONE);
        btnEdit.setVisibility(visibilty == View.VISIBLE ? View.GONE : View.VISIBLE);
    }
}