package com.schemecode.zr3i.ui.activities.edit_land_details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.google.android.material.textfield.TextInputEditText;
import com.schemecode.zr3i.R;
import com.schemecode.zr3i.data.classes.PrefLand;
import com.schemecode.zr3i.data.models.countries.Datum;
import com.schemecode.zr3i.ui.activities.login_activity.LoginActivity;
import com.schemecode.zr3i.ui.activities.splash_activity.LoginSplashActivity;
import com.schemecode.zr3i.ui.dialogs.error_dialog.ErrorDialog;
import com.schemecode.zr3i.ui.dialogs.success_dialog.SuccessDialog;

import java.util.ArrayList;
import java.util.List;

public class EditLandActivity extends AppCompatActivity implements EditLandDetailsContract.view{

    private TextInputEditText etOwner , etCrops , etCity , etHay ,  etCropsNumber , etContractSpace , etActualSpace ;
    private EditText etDesc ;
    private SmartMaterialSpinner spinnerCountry ;
    private ProgressBar progressBarEdit ;
    private EditLandPresenter editLandPresenter;
    private SharedPreferences sharedPreferences;
    private String token , secret ;
    private PrefLand prefLand;
    private List<Datum> countriesList;
    int countryId ;
    private AppCompatButton btnEdit ;
    private ImageView imgBack ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_land);
        Intent intent = getIntent();
        prefLand = (PrefLand) intent.getSerializableExtra("land_obj");
        sharedPreferences = getSharedPreferences("MahmoudPref" , MODE_PRIVATE);
        token = sharedPreferences.getString("token" , "token");
        secret = sharedPreferences.getString("secret" , "secret");
        editLandPresenter = new EditLandPresenter(this);
        editLandPresenter.start();
    }

    @Override
    public void initUi() {
        progressBarEdit = findViewById(R.id.save_edits_progress_Bar);
        etOwner = findViewById(R.id.owener_layout_edit_text);
        etCrops = findViewById(R.id.crops_edit_text);
        etCity = findViewById(R.id.city_edit_text);
        etHay = findViewById(R.id.hay_layout_edit_text);
        etCropsNumber = findViewById(R.id.crops_number_edit_text);
        etContractSpace = findViewById(R.id.contract_edit_text);
        etActualSpace = findViewById(R.id.actual_space_edit_text);
        etDesc = findViewById(R.id.desc_edit_text);
        btnEdit = findViewById(R.id.save_edits_button);
        imgBack = findViewById(R.id.back_image_view);
        countriesList = new ArrayList<>();
    }

    @Override
    public void initSpinners() {
        spinnerCountry = findViewById(R.id.country_spinner);
    }

    @Override
    public void setData() {
        etOwner.setText(prefLand.getOwner());
        etCrops.setText(prefLand.getCrops());
        etCity.setText(prefLand.getCity());
        etHay.setText(prefLand.getCity_area());
        etCropsNumber.setText(prefLand.getNumberOfCycles() + "");
        etContractSpace.setText(prefLand.getContractSpace() + "");
        etActualSpace.setText(prefLand.getActualSpace() + "");
        etDesc.setText(prefLand.getDesc());
    }

    @Override
    public void handleClicks() {
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editLandPresenter.editLand(etOwner.getText().toString() , etCity.getText().toString() , etHay.getText().toString().trim()
                , etContractSpace.getText().toString() , countryId ,Integer.parseInt(etCropsNumber.getText().toString().trim()) , etDesc.getText().toString().trim()
                , prefLand.getId() , "ar" , "android" , "hash_key" , token , secret);
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void getListOfCountires(List<Datum> countires) {
        btnEdit.setEnabled(true);
        List<String> countriesStringList = new ArrayList<>();
        String prefCountry = prefLand.getCountry();
        int position = 0;
        for (int i = 0 ; i < countires.size() ; i++){
            countriesStringList.add(countires.get(i).getTitle());
            if (countires.get(i).getTitleAr().equals(prefCountry)){
                position = i;
            }
        }
        spinnerCountry.setItem(countriesStringList);
        spinnerCountry.setSelection(position);
        countryId = countires.get(position).getId();
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countryId = countires.get(position).getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showOrHideProgressBar(int visibilty) {
        progressBarEdit.setVisibility(visibilty == View.VISIBLE ? View.VISIBLE : View.GONE);
        btnEdit.setVisibility(visibilty == View.VISIBLE ? View.GONE : View.VISIBLE);
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
    public void showSuuccessOrFailureDialog(boolean is_success, String message) {
        if (is_success == true){
            SuccessDialog successDialog = new SuccessDialog(EditLandActivity.this , message);
            successDialog.show();
        }
        else {
            ErrorDialog errorDialog = new ErrorDialog(EditLandActivity.this , message);
            errorDialog.show();
        }
    }
}