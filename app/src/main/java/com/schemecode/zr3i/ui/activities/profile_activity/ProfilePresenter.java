package com.schemecode.zr3i.ui.activities.profile_activity;

import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.schemecode.zr3i.data.api.ApiClient;
import com.schemecode.zr3i.data.api.ApiService;
import com.schemecode.zr3i.data.models.countries.DataCountries;
import com.schemecode.zr3i.data.models.profile.AccProfile;
import com.schemecode.zr3i.data.models.update_profile.AccProfileUpdate;
import com.schemecode.zr3i.helpers.FileUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter implements ProfileContract.presenter {

    private ProfileContract.view view;
    private ApiService apiService;

    public ProfilePresenter(ProfileContract.view view) {
        this.view = view;
        apiService = ApiClient.getClient().create(ApiService.class);

    }

    public void setView(ProfileContract.view view) {
        this.view = view;
    }


    @Override
    public void start() {
        this.view.initUi();
        this.view.initSpinners();
        this.view.handleClicks();
    }

    @Override
    public void getCountries() {
        Call<DataCountries> countriesCall = apiService.getCountries("ar", "android", "hash_key");
        countriesCall.enqueue(new Callback<DataCountries>() {
            @Override
            public void onResponse(Call<DataCountries> call, Response<DataCountries> response) {
                if (view != null) {
                }
                if (response.isSuccessful()) {
                    if (response.body().getResult() == true && response.body().getSuccess() == true) {
                        if (view != null) {
                            view.getListOfCountires(response.body().getData());
                        }
                    } else {
                        if (view != null) {
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DataCountries> call, Throwable t) {
                if (view != null) {

                }
            }
        });
    }

    @Override
    public void updateProfile(String firstName, String lastName, int gender, int countryId, String city, String mobile, int year, int day, int month, String email, Uri uri, String token, String secret) {
        RequestBody firstNameRequest = RequestBody.create(MediaType.parse("text/plain"), firstName);
        RequestBody lastNameRequest = RequestBody.create(MediaType.parse("text/plain"), lastName);
        RequestBody genderRequest = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(gender));
        RequestBody countryIdRequest = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(countryId));
        RequestBody cityRequest = RequestBody.create(MediaType.parse("text/plain"), city);
        RequestBody mobileRequest = RequestBody.create(MediaType.parse("text/plain"), mobile);
        RequestBody yearRequest = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(year));
        RequestBody dayRequest = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(day));
        RequestBody monthRequest = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(month));
        RequestBody emailRequest = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody landRequest = RequestBody.create(MediaType.parse("text/plain"), "ar");
        RequestBody platformRequest = RequestBody.create(MediaType.parse("text/plain"), "android");
        RequestBody hashKeyRequest = RequestBody.create(MediaType.parse("text/plain"), "hash_key");
        RequestBody tokenRequest = RequestBody.create(MediaType.parse("text/plain"), token);
        RequestBody secretRequest = RequestBody.create(MediaType.parse("text/plain"), secret);
        Call<AccProfileUpdate> updateCall ;
        if (uri != null) {
            File originalFile = FileUtils.getFile(view.returnContext(), uri);
            RequestBody descriptionPart = RequestBody.create(MultipartBody.FORM, "FileUtils.getPath(ProfileActivity.this, uri)");
            RequestBody filePart = RequestBody.create(MediaType.parse(view.returnContext().getContentResolver().getType(uri)), originalFile);
            MultipartBody.Part file = MultipartBody.Part.createFormData("image", originalFile.getName(), filePart);
            updateCall = apiService.updateProfile(firstNameRequest, lastNameRequest, genderRequest, countryIdRequest
                    , cityRequest, mobileRequest, yearRequest, dayRequest,
                    monthRequest, emailRequest, descriptionPart, file, landRequest, platformRequest, hashKeyRequest, tokenRequest, secretRequest);
        } else {
            updateCall = apiService.updateProfile(firstNameRequest, lastNameRequest, genderRequest, countryIdRequest
                    , cityRequest, mobileRequest, yearRequest, dayRequest,
                    monthRequest, emailRequest , null , null ,  landRequest, platformRequest, hashKeyRequest, tokenRequest, secretRequest);
        }
        view.showOrHideProgressProgress(View.VISIBLE);
        updateCall.enqueue(new Callback<AccProfileUpdate>() {
            @Override
            public void onResponse(Call<AccProfileUpdate> call, Response<AccProfileUpdate> response) {
                if (view != null) {
                    view.showOrHideProgressProgress(View.GONE);
                }
                if (response.isSuccessful()) {
                    if (response.body().getMessage().equals("فضلا قم بتسجيل الدخــول أولا") && view != null) {
                        Log.e("error", response.body().getMessage());
                        view.unAuthorized(response.body().getMessage());
                    } else if (response.body().getResult() != null && response.body().getSuccess() != null && response.body().getResult() == true && response.body().getSuccess() == true) {
                        if (view != null) {
                            view.setDataToShared(response.body().getImage());
                            view.inflateSuccessDialog(true, response.body().getMessage());
                        }
                    } else {
                        if (view != null) {
                            view.inflateSuccessDialog(false, response.body().getMessage());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AccProfileUpdate> call, Throwable t) {
                if (view != null) {
                    view.showOrHideProgressProgress(View.GONE);
                    view.inflateSuccessDialog(false, t.getMessage());
                }
            }
        });

    }

    @Override
    public void getMyProfile(String token, String secret) {
        Call<AccProfile> profileCall = apiService.getProfile("ar", "android", "hash_key", token, secret);
        profileCall.enqueue(new Callback<AccProfile>() {
            @Override
            public void onResponse(Call<AccProfile> call, Response<AccProfile> response) {
                if (response.isSuccessful()) {
                    if (response.body().getMessage() != null && response.body().getMessage().equals("فضلا قم بتسجيل الدخــول أولا") && view != null) {
                        Log.e("error", response.body().getMessage());
                        view.unAuthorized(response.body().getMessage());
                    } else if (response.body().getData() != null && response.body().getResult() == true && response.body().getSuccess() == true) {
                        if (view != null) {
                            Log.e("look", "yes");
                            view.setData(response.body().getData());
                        }
                    } else {
                        Log.e("look", "no");

                    }
                }
            }

            @Override
            public void onFailure(Call<AccProfile> call, Throwable t) {
                Log.e("failure", t.getMessage());

            }
        });
    }

}
