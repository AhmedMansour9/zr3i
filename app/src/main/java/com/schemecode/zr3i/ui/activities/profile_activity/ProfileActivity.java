package com.schemecode.zr3i.ui.activities.profile_activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.google.android.material.textfield.TextInputEditText;
import com.schemecode.zr3i.R;
import com.schemecode.zr3i.data.models.countries.Datum;
import com.schemecode.zr3i.data.models.profile.Data;
import com.schemecode.zr3i.helpers.FileUtils;
import com.schemecode.zr3i.ui.activities.login_activity.LoginActivity;
import com.schemecode.zr3i.ui.activities.new_land_activity.LandCreationActivity;
import com.schemecode.zr3i.ui.activities.register_activity.RegisterActivity;
import com.schemecode.zr3i.ui.dialogs.error_dialog.ErrorDialog;
import com.schemecode.zr3i.ui.dialogs.success_dialog.SuccessDialog;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity implements ProfileContract.view {
    private int positionOfCountryInCountryList;
    private TextInputEditText etFirstName, etLastName, etMail, etPhone, etCity;
    private SmartMaterialSpinner spDay, spMonth, spYear, spKind, spCountry;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String token, secret;
    private ProfilePresenter profilePresenter;
    int country;
    private ArrayList<String> genderList, monthList, dayList, yearList;
    private AppCompatButton btnSaveChanges;
    private ProgressBar progressBarSubmit;
    private ImageView imgBack;
    private CircleImageView circleImageView;
    public static final int PICK_IMAGE = 1;
    private Uri selectedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profilePresenter = new ProfilePresenter(this);
        profilePresenter.start();
        sharedPreferences = getSharedPreferences("MahmoudPref", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        token = sharedPreferences.getString("token", "token");
        secret = sharedPreferences.getString("secret", "secret");
        profilePresenter.getMyProfile(token, secret);
    }

    @Override
    public void initUi() {
        imgBack = findViewById(R.id.back_image_view);
        etFirstName = findViewById(R.id.first_name_edit_text);
        etLastName = findViewById(R.id.last_name_edit_text);
        etCity = findViewById(R.id.city_edit_text);
        etMail = findViewById(R.id.mail_edit_text);
        etPhone = findViewById(R.id.phone_edit_text);
        btnSaveChanges = findViewById(R.id.save_edits_button);
        progressBarSubmit = findViewById(R.id.edit_progress_bar);
        circleImageView = findViewById(R.id.profile_circle_image_view);
    }

    @Override
    public void initSpinners() {
        spCountry = findViewById(R.id.country_spinner);
        spKind = findViewById(R.id.gender_spinner);
        spDay = findViewById(R.id.day_spinner);
        spMonth = findViewById(R.id.month_spinner);
        spYear = findViewById(R.id.year_spinner);
        dayList = new ArrayList<>();
        monthList = new ArrayList<>();
        yearList = new ArrayList<>();
        genderList = new ArrayList<>();
        monthList.add("يناير");
        monthList.add("فبراير");
        monthList.add("مارس");
        monthList.add("ابريل");
        monthList.add("مايو");
        monthList.add("يونيو");
        monthList.add("يوليو");
        monthList.add("إغسطس");
        monthList.add("سبتمبر");
        monthList.add("أكتوبر");
        monthList.add("نوفمبر");
        monthList.add("ديسمبر");
        genderList.add("ذكر");
        genderList.add("أنثي");
        for (int year = 1940; year <= 2021; year++) {
            yearList.add(String.valueOf(year));
        }
        for (int day = 1; day <= 31; day++) {
            dayList.add(String.valueOf(day));
        }
        spMonth.setItem(monthList);
        spYear.setItem(yearList);
        spDay.setItem(dayList);
        spKind.setItem(genderList);
    }

    @Override
    public void getListOfCountires(List<com.schemecode.zr3i.data.models.countries.Datum> countriesList) {
        List<String> countries = new ArrayList<>();
        for (int i = 0; i < countriesList.size(); i++) {
            countries.add(countriesList.get(i).getTitle());
            if (countriesList.get(i).getId() == country) {
                positionOfCountryInCountryList = i;
            }
        }
        spCountry.setItem(countries);
        spCountry.setSelection(positionOfCountryInCountryList);
        country = countriesList.get(positionOfCountryInCountryList).getId();
        spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = countriesList.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void setData(Data accProfile) {
        country = Integer.parseInt(accProfile.getCountryId());
        etFirstName.setText(accProfile.getFirstName());
        etLastName.setText(accProfile.getLastName());
        etCity.setText(accProfile.getCity());
        etPhone.setText(accProfile.getMobile());
        etMail.setText(accProfile.getEmail());
        profilePresenter.getCountries();
        spKind.setSelection(accProfile.getGender() - 1);
        String[] dateSplite = accProfile.getBirthDate().split("-");
        int poistionOfYear = yearList.indexOf(dateSplite[0]);
        spMonth.setSelection(Integer.parseInt(dateSplite[1]) - 1);
        spDay.setSelection(Integer.parseInt(dateSplite[2]) - 1);
        spYear.setSelection(poistionOfYear);
        setBtnEnabledOrDisable(true);
        Glide.with(ProfileActivity.this).load(accProfile.getImage_url()).into(circleImageView);
    }

    @Override
    public void handleClicks() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fName, lName, email, phone, city;
                int genderPos, countryPos, monthPos, dayPos, yearPos;
                fName = etFirstName.getText().toString().trim();
                lName = etLastName.getText().toString().trim();
                email = etMail.getText().toString().trim();
                phone = etPhone.getText().toString().trim();
                city = etCity.getText().toString().trim();
                genderPos = spKind.getSelectedItemPosition();
                countryPos = spCountry.getSelectedItemPosition();
                dayPos = spDay.getSelectedItemPosition();
                monthPos = spMonth.getSelectedItemPosition();
                yearPos = spYear.getSelectedItemPosition();
                profilePresenter.updateProfile(fName, lName, genderPos + 1, country, city,
                        phone, Integer.parseInt(yearList.get(yearPos)), Integer.parseInt(dayList.get(dayPos))
                        , monthPos + 1, email, selectedImage, token, secret);
            }
        });
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                permission();
            }
        });
    }

    @Override
    public void showOrHideProgressProgress(int visibilty) {
        progressBarSubmit.setVisibility(visibilty == View.VISIBLE ? View.VISIBLE : View.GONE);
        btnSaveChanges.setVisibility(visibilty == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    @Override
    public void setDataToShared(String image) {
        editor.putString("image_url" , image);
        editor.commit();
    }

    @Override
    public void inflateSuccessDialog(boolean is_success, String message) {
        if (is_success == true) {
            SuccessDialog successDialog = new SuccessDialog(ProfileActivity.this, message);
            successDialog.show();
        } else {
            ErrorDialog errorDialog = new ErrorDialog(ProfileActivity.this, message);
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
    public void setBtnEnabledOrDisable(boolean is_enabled) {
        if (is_enabled == true) {
            btnSaveChanges.setEnabled(true);
        } else {
            btnSaveChanges.setEnabled(false);
        }
    }

    @Override
    public Context returnContext() {
        return ProfileActivity.this;
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean permission() {
        if (this.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            Log.e("Permission error", "You have permission");
            return true;
        } else {
            Log.e("Permission error", "You have asked for permission");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            Toast.makeText(this, "لابد من السماح للإذونات", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && data != null) {
            selectedImage = data.getData();
            Log.e("uri", selectedImage.toString());
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            circleImageView.setImageURI(selectedImage);
            String originalFile = FileUtils.getPath(ProfileActivity.this, selectedImage);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        profilePresenter.setView(null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        profilePresenter.setView(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        profilePresenter.setView(ProfileActivity.this);
    }
}