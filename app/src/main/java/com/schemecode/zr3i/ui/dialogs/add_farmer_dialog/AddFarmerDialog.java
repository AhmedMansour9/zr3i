package com.schemecode.zr3i.ui.dialogs.add_farmer_dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputEditText;
import com.schemecode.zr3i.R;
import com.schemecode.zr3i.ui.activities.list_farmers.ListFarmersActivity;

public class AddFarmerDialog extends Dialog implements AddFarmerContract.view{

    private AddFarmerPresenter addFarmerPresenter;
    private AppCompatButton btnAddFarmer ;
    private TextInputEditText etName , etAge , etMail , etCity , etPhone , etNationalId , etAddress ;
    String token , secret ;
    private int landId ;
    private Context context;

    public AddFarmerDialog(@NonNull Context context , int landId ,String token , String secret) {
        super(context);
        this.token = token;
        this.secret = secret;
        this.landId = landId;
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setWindowAnimations(R.style.DialogAnimation);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_farmer_dialog_layout);
        addFarmerPresenter = new AddFarmerPresenter(this);
        addFarmerPresenter.start();
    }

    @Override
    public void initUi() {
        btnAddFarmer = findViewById(R.id.add_farmer_button);
        etName = findViewById(R.id.name_edit_text);
        etAddress = findViewById(R.id.address_edit_text);
        etAge = findViewById(R.id.age_edit_text);
        etCity = findViewById(R.id.city_edit_text);
        etNationalId = findViewById(R.id.national_id_edit_text);
        etMail = findViewById(R.id.mail_edit_text);
        etPhone = findViewById(R.id.mobile_edit_text);
    }

    @Override
    public void handleClicks() {
        btnAddFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String address = etAddress.getText().toString().trim();
                String city = etCity.getText().toString().trim();
                String age = etAge.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String nationalId = etNationalId.getText().toString().trim();
                String mail = etMail.getText().toString().trim();
                if (name.matches("")){
                    Toast.makeText(getContext(), "من فضلك قم بالتاكد من ملئ البيانات", Toast.LENGTH_SHORT).show();
                    etName.setError("إجباري");
                    return;
                }
                if (address.matches("") ){
                    address = null;
                }
                if (city.matches("") ){
                    city = null;
                }

                if (phone.matches("") ){
                    phone = null;
                }
                if (nationalId.matches("") ){
                    nationalId = null;
                }
                if (mail.matches("") ){
                    mail = null;
                }
                if (age.matches("") ){
                    addFarmerPresenter.addFarmer(landId , name ,null , phone , city , mail , nationalId , address , token ,secret);
                }
                else {
                    addFarmerPresenter.addFarmer(landId , name ,age , phone , city , mail , nationalId , address , token ,secret);
                }
            }
        });
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext() , ListFarmersActivity.class);
        intent.putExtra("land_id" , landId);
        Activity activity = (Activity) context;
        getContext().startActivity(intent);
        activity.finish();
    }
}
