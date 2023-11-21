package com.schemecode.zr3i.ui.activities.register_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.google.android.material.textfield.TextInputEditText;
import com.schemecode.zr3i.R;
import com.schemecode.zr3i.adapters.AccountTypesAdapter;
import com.schemecode.zr3i.data.models.plans.DataPlans;
import com.schemecode.zr3i.data.models.plans.Datum;
import com.schemecode.zr3i.ui.activities.home_activity.HomeActivity;
import com.schemecode.zr3i.ui.activities.login_activity.LoginActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.view {
    private CheckBox cbAgreeTerms;
    private ArrayList<String> genderList, monthList, dayList, yearList, coutryList;
    private SmartMaterialSpinner spinnerQuota, spinnerGender, spinnerCountry, spinnerDay, spinnerMonth, spinnerYear;
    private TextInputEditText etFirstName, etLastName, etCity, etMail, etPhone, etPass, etConfirmPass;
    private TextView tvLogin, tvErrors, tvKindOfAccount;
    private RegisterPresenter registerPresenter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private AppCompatButton btnCreateAccount;
    private int day, month, year, quota, plan, country, gender = 0;
    private List<Datum> dataPlansList;
    private RecyclerView rvAccountTypes;
    private AccountTypesAdapter accountTypesAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<com.schemecode.zr3i.data.models.account_types.Datum> accountsList;
    private ProgressBar progressBarAccountTypes, progressBarCountries, progressBarQuota, progressBarRegister;
    int accountTypeId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sharedPreferences = getSharedPreferences("MahmoudPref", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        registerPresenter = new RegisterPresenter(this);
        registerPresenter.start();

    }

    @Override
    public void initUi() {
        tvLogin = findViewById(R.id.login_text_view_register);
        cbAgreeTerms = findViewById(R.id.agree_trims);
        progressBarAccountTypes = findViewById(R.id.account_types_progress_bar);
        progressBarCountries = findViewById(R.id.countries_progress_bar);
        progressBarQuota = findViewById(R.id.quota_progress_bar);
        progressBarRegister = findViewById(R.id.register_progress_bar);
        tvErrors = findViewById(R.id.errors_text_view);
        etFirstName = findViewById(R.id.first_name_edit_text);
        etLastName = findViewById(R.id.name_of_room_edit_text);
        etCity = findViewById(R.id.city_edit_text);
        etMail = findViewById(R.id.mail_edit_text);
        etPhone = findViewById(R.id.phone_edit_text);
        etPass = findViewById(R.id.password_edit_text);
        etConfirmPass = findViewById(R.id.confirm_pass_edit_text);
        btnCreateAccount = findViewById(R.id.create_new_account_button);
        tvKindOfAccount = findViewById(R.id.kind_of_account_text_view);
    }

    @Override
    public void initRecycler() {
        accountsList = new ArrayList<>();
        rvAccountTypes = findViewById(R.id.account_types_recycler_view);
        accountTypesAdapter = new AccountTypesAdapter(accountsList);
        linearLayoutManager = new LinearLayoutManager(RegisterActivity.this, RecyclerView.HORIZONTAL, false);
        rvAccountTypes.setLayoutManager(linearLayoutManager);
        rvAccountTypes.setAdapter(accountTypesAdapter);

    }

    @Override
    public void intSpinners() {
        spinnerDay = findViewById(R.id.day_spinner);
        spinnerMonth = findViewById(R.id.month_spinner);
        spinnerYear = findViewById(R.id.year_spinner);
        spinnerGender = findViewById(R.id.gender_spinner);
        spinnerQuota = findViewById(R.id.quota_spinner);
        spinnerCountry = findViewById(R.id.country_spinner);
        coutryList = new ArrayList<>();
        dayList = new ArrayList<>();
        monthList = new ArrayList<>();
        yearList = new ArrayList<>();
        genderList = new ArrayList<>();
        coutryList.add("مصر");
        coutryList.add("السعودية");
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

        spinnerMonth.setItem(monthList);
        spinnerYear.setItem(yearList);
        spinnerDay.setItem(dayList);
        spinnerGender.setItem(genderList);
        spinnerCountry.setItem(coutryList);
        registerPresenter.getPlans();
        registerPresenter.getAccountTypes();
        registerPresenter.getCountries();

        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("year", yearList.get(position));
                year = Integer.parseInt(yearList.get(position));
                Log.e("year", String.valueOf(year));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("month", String.valueOf(position + 1));
                month = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("day", String.valueOf(position + 1));
                day = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void handleClicks() {
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);

            }
        });
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvErrors.setVisibility(View.GONE);
                String firstName, lastName, city, mail, phone, pass, confirmPass;
                firstName = etFirstName.getText().toString().trim();
                lastName = etLastName.getText().toString().trim();
                city = etCity.getText().toString().trim();
                mail = etMail.getText().toString().trim();
                phone = etPhone.getText().toString().trim();
                pass = etPass.getText().toString().trim();
                confirmPass = etConfirmPass.getText().toString().trim();
                if (firstName.matches("")) {
                    etFirstName.setError("هذا الحقل مطلوب");
                    return;
                } else if (lastName.matches("")) {
                    etLastName.setError("هذا الحقل مطلوب");
                } else if (city.matches("")) {
                    etCity.setError("هذا الحقل مطلوب");
                } else if (mail.matches("")) {
                    etMail.setError("هذا الحقل مطلوب");
                } else if (phone.matches("")) {
                    etPhone.setError("هذا الحقل مطلوب");
                } else if (pass.matches("")) {
                    etPass.setError("هذا الحقل مطلوب");
                } else if (confirmPass.matches("")) {
                    etConfirmPass.setError("هذا الحقل مطلوب");
                } else if (year == 0 || day == 0 || month == 0 || plan == 0 || country == 0) {
                    showErrorTextView("تأكد من ملئ البيانات");
                    return;
                } else if (cbAgreeTerms.isChecked() == false) {
                    showErrorTextView("بالرجاء الموافقة علي القواعد والشروط");
                } else {
                    registerPresenter.register(firstName, lastName,
                            gender, country, city, phone, year, day, month, plan
                            , null, accountTypeId, mail, pass, confirmPass, "ar", "android", "hash_key");
                }
            }
        });
    }

    @Override
    public void showProgressBar(String kindOfProgress) {
        if (kindOfProgress.equals("account_type")) {
            progressBarAccountTypes.setVisibility(View.VISIBLE);
            tvKindOfAccount.setVisibility(View.GONE);
        } else if (kindOfProgress.equals("quota")) {
            spinnerQuota.setVisibility(View.GONE);
            progressBarQuota.setVisibility(View.VISIBLE);
        } else {
            spinnerCountry.setVisibility(View.GONE);
            progressBarCountries.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgressBar(String kindOfProgress) {
        if (kindOfProgress.equals("account_type")) {
            progressBarAccountTypes.setVisibility(View.GONE);
            tvKindOfAccount.setVisibility(View.VISIBLE);

        } else if (kindOfProgress.equals("quota")) {
            spinnerQuota.setVisibility(View.VISIBLE);
            progressBarQuota.setVisibility(View.GONE);
        } else {
            progressBarCountries.setVisibility(View.GONE);
            spinnerCountry.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getListOfPlans(List<Datum> plansList) {
        List<String> plans = new ArrayList<>();
        for (int i = 0; i < plansList.size(); i++) {
            plans.add(plansList.get(i).getTitle());
        }
        spinnerQuota.setItem(plans);
        spinnerQuota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                plan = plansList.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void getListOfCountires(List<com.schemecode.zr3i.data.models.countries.Datum> countriesList) {
        List<String> countries = new ArrayList<>();
        for (int i = 0; i < countriesList.size(); i++) {
            countries.add(countriesList.get(i).getTitle());
        }
        spinnerCountry.setItem(countries);
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = countriesList.get(position).getId();
                Log.e("country", countriesList.get(position).getTitle());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void getAccountTypes(List<com.schemecode.zr3i.data.models.account_types.Datum> accountsList) {
        this.accountsList.clear();
        this.accountsList.addAll(accountsList);
        this.accountTypesAdapter.notifyDataSetChanged();
        accountTypesAdapter.setOnItemClickListener(new AccountTypesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                accountTypeId = accountsList.get(position).getId();
                Log.e("accountTypeId", String.valueOf(accountTypeId));
            }
        });
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorTextView(String error) {
        tvErrors.setVisibility(View.VISIBLE);
        tvErrors.setText(error);
    }

    @Override
    public void saveToSharedAndGoToHomeAct(String token, String secret , String message) {
        editor.putString("token", token);
        editor.putString("secret", secret);
        editor.commit();
        Intent homeintent = new Intent(RegisterActivity.this, HomeActivity.class);
        homeintent.putExtra("message" , message);
        startActivity(homeintent);
        finish();
    }

    @Override
    public void disableButtonAndShowProgressBar() {
        btnCreateAccount.setEnabled(false);
        progressBarRegister.setVisibility(View.VISIBLE);
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
    public void enableButtonAndHideProgressBar() {
        btnCreateAccount.setEnabled(true);
        progressBarRegister.setVisibility(View.GONE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        registerPresenter.setView(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerPresenter.setView(RegisterActivity.this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        registerPresenter.setView(null);
    }
}