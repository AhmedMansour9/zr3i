package com.schemecode.zr3i.ui.activities.new_land_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.maps.android.SphericalUtil;
import com.schemecode.zr3i.R;
import com.schemecode.zr3i.data.classes.LatLngClass;
import com.schemecode.zr3i.data.models.crops.Datum;
import com.schemecode.zr3i.ui.activities.login_activity.LoginActivity;
import com.schemecode.zr3i.ui.dialogs.error_dialog.ErrorDialog;
import com.schemecode.zr3i.ui.dialogs.success_dialog.SuccessDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

public class LandCreationActivity extends AppCompatActivity implements OnMapReadyCallback, LandCreationContract.view {
    private TextInputEditText etCity, etCropsNumber, etOwner, etHay, etSpaceOfContract;
    private SmartMaterialSpinner spCountry, spCrops;
    private EditText etDesc;
    private ImageView imgSelectMap;
    GoogleMap mMap;
    private Button btnDraw, btnRemove, btnSubmit;
    private LandCreationPresenter landCreationPresenter;
    private List<LatLng> latLngList = new ArrayList<>();
    private List<Marker> markerList = new ArrayList<>();
    private List<LatLngClass> latLngClassList;
    private Polygon polygon = null;
    int country, cropId, numberOfCropCycles = 0;
    private String owner, city, hay, space, token, secret , contractSpace;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    //    List<com.schemecode.zr3i.data.classes.LatLng> latLngListToSent;
    List<com.schemecode.zr3i.data.classes.Polygon> latLngListToSent;
    private HashMap<String, List<com.schemecode.zr3i.data.classes.LatLng>> listMap;
    private HashMap<String, Map<String, String>> listMap2;
    private HashMap<String, String> arrayJsonMap;
    private JSONArray jsonArray;
    private ProgressBar progressBarStoreLand;
    public static final CharSequence[] MAP_TYPE_ITEMS =
            {"Road Map", "Hybrid", "Satellite", "Terrain"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_creation);
        sharedPreferences = getSharedPreferences("MahmoudPref", MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        secret = sharedPreferences.getString("secret", "");
        landCreationPresenter = new LandCreationPresenter(this);
        landCreationPresenter.start();
    }

    @Override
    public void initUi() {
        imgSelectMap = findViewById(R.id.select_map_type_image);
        btnSubmit = findViewById(R.id.continue_ploygon_button);
        btnDraw = findViewById(R.id.draw_ploygon_button);
        btnRemove = findViewById(R.id.remove_ploygon_button);
        etCity = findViewById(R.id.city_edit_text);
        etCropsNumber = findViewById(R.id.number_of_zra3a_edit_text);
        etDesc = findViewById(R.id.desc_edit_text);
        etOwner = findViewById(R.id.owner_edit_text);
        etHay = findViewById(R.id.hay_edit_text);
        etSpaceOfContract = findViewById(R.id.contract_space_edit_text);
        progressBarStoreLand = findViewById(R.id.store_land_progress_bar);
    }


    @Override
    public void getListOfCountires(List<com.schemecode.zr3i.data.models.countries.Datum> countriesList) {
        List<String> countries = new ArrayList<>();
        for (int i = 0; i < countriesList.size(); i++) {
            countries.add(countriesList.get(i).getTitle());
        }
        spCountry.setItem(countries);
        spCountry.setSelection(0);
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
    public void getListOfCrops(List<Datum> cropsList) {
        List<String> crops = new ArrayList<>();
        for (int i = 0; i < cropsList.size(); i++) {
            crops.add(cropsList.get(i).getTitle());
        }
        spCrops.setItem(crops);
        spCrops.setSelection(0);
        spCrops.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cropId = cropsList.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void initSpinners() {
        spCountry = findViewById(R.id.country_spinner_store_land_activity);
        spCrops = findViewById(R.id.crops_spinner_store_land_activity);
        landCreationPresenter.getCountries();
        landCreationPresenter.getCrops();
    }

    @Override
    public void handleClicks() {
        imgSelectMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMapTypeSelectorDialog();
            }
        });

        btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (polygon != null) {
                    polygon.remove();
                } else if (polygon == null && !latLngList.isEmpty() && !markerList.isEmpty()) {
                    jsonArray = new JSONArray();
                    listMap = new HashMap<>();
                    listMap2 = new HashMap<>();
                    listMap.clear();
                    listMap2.clear();
                    arrayJsonMap = new HashMap<>();
                    arrayJsonMap.clear();
                    latLngListToSent = new ArrayList<>();
                    latLngClassList = new ArrayList<>();
                    latLngListToSent.clear();
                    latLngClassList.clear();
                    PolygonOptions polygonOptions = new PolygonOptions().addAll(latLngList).clickable(true);
                    polygon = mMap.addPolygon(polygonOptions);
                    int strokeColor = polygon.getStrokeColor() ^ 0x00ffffff;
                    polygon.setStrokeColor(strokeColor);
                    space = String.valueOf(SphericalUtil.computeArea(latLngList));
//                    etSpaceOfContract.setText(space);
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    Double firstLat = null, firstLng = null;
                    for (int i = 0; i < polygon.getPoints().size(); i++) {
                        StringTokenizer stringTokenizer = new StringTokenizer("(");
                        String[] lat = String.valueOf(polygon.getPoints().get(i).toString()).split(" ");
                        String[] latLangFinsh = lat[1].substring(1, lat[1].length() - 1).split(",");
                        com.schemecode.zr3i.data.classes.LatLng latLng = new com.schemecode.zr3i.data.classes.LatLng(latLangFinsh[0], latLangFinsh[1]);
                        latLngListToSent.add(new com.schemecode.zr3i.data.classes.Polygon(Double.parseDouble(latLangFinsh[0]), Double.parseDouble(latLangFinsh[1])));
                        firstLat = Double.valueOf(latLangFinsh[0]);
                        firstLng = Double.valueOf(latLangFinsh[1]);
//                        latLngClassList.add(new LatLngClass(latLangFinsh[0] + latLangFinsh[1]));
//                        jsonArray.put(gson.toJson(latLng));
                    }

                    Geocoder geocoder;
                    List<Address> addresses;
                    Locale locale;
                    locale = new Locale("ar");
                    geocoder = new Geocoder(LandCreationActivity.this, locale);

                    try {
                        addresses = geocoder.getFromLocation(firstLat, firstLng, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                        String city = addresses.get(0).getLocality();
                        String state = addresses.get(0).getAdminArea();
                        String country = addresses.get(0).getCountryName();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                etCity.setText(city);
                                etHay.setText(state);
                            }
                        });

                    } catch (IOException e) {
                        Toast.makeText(LandCreationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

//                    listMap.put("map[polygon]", latLngListToSent);

                }
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (polygon != null) polygon.remove();
                for (Marker marker : markerList) {
                    marker.remove();
                }
                latLngList.clear();
                markerList.clear();
                polygon = null;
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                owner = etOwner.getText().toString().trim();
                city = etCity.getText().toString().trim();
                hay = etHay.getText().toString().trim();
                contractSpace = etSpaceOfContract.getText().toString();
                if (owner.matches("") || space.matches("")
                         || etCropsNumber.getText().toString().trim().matches("") || contractSpace.matches("") || space == null || contractSpace.matches("0")) {
                    Snackbar snackbar = Snackbar.make(v, "من فضلك قم بإدخال جميع البيانات", Snackbar.LENGTH_LONG);
                    snackbar.setAction("تجاهل", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });
                    ViewCompat.setLayoutDirection(snackbar.getView(), ViewCompat.LAYOUT_DIRECTION_RTL);
                    snackbar.setActionTextColor(getResources().getColor(R.color.white));
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.baseGreen));
                    snackbar.show();
                } else if (polygon == null) {
                    return;
                } else if (polygon.getPoints().isEmpty()) {
                    return;
                } else if (latLngListToSent == null) {

                    return;
                } else if (latLngListToSent.isEmpty()) {
                    return;
                } else {
                    numberOfCropCycles = Integer.parseInt(etCropsNumber.getText().toString());
                    LatLng currentMapCordinator = mMap.getCameraPosition().target;
                    float zoom = mMap.getCameraPosition().zoom;
                    Gson gson = new Gson();
                    arrayJsonMap = new HashMap<>();
                    String jsonArray1 = gson.toJson(latLngListToSent);
                    arrayJsonMap.put("map[latitude]", String.valueOf(currentMapCordinator.latitude));
                    arrayJsonMap.put("map[longitude]", String.valueOf(currentMapCordinator.longitude));
                    arrayJsonMap.put("map[zoom]", String.valueOf(zoom));
                    arrayJsonMap.put("map[polygon]", jsonArray1);
                    Log.e("json" , jsonArray1);
                    landCreationPresenter.storeLand(arrayJsonMap, owner, city, hay, space,
                            contractSpace, country, cropId, numberOfCropCycles,
                            etDesc.getText().toString().trim(), token, secret);
                }
            }
        });
    }

    @Override
    public void initDialog() {

    }

    @Override
    public void showError(String text) {
        ErrorDialog errorDialog = new ErrorDialog(LandCreationActivity.this, text, "land");
        errorDialog.show();
    }

    @Override
    public void showSuccessMessage(String txt) {
        SuccessDialog successDialog = new SuccessDialog(LandCreationActivity.this, txt, "land");
        successDialog.show();
    }

    @Override
    public void showMapTypeSelectorDialog() {
        // Prepare the dialog by setting up a Builder.
        final String fDialogTitle = "Select Map Type";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(fDialogTitle);

        // Find the current map type to pre-check the item representing the current state.
        int checkItem = mMap.getMapType() - 1;

        // Add an OnClickListener to the dialog, so that the selection will be handled.
        builder.setSingleChoiceItems(
                MAP_TYPE_ITEMS,
                checkItem,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 1:
                                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                                break;
                            case 2:
                                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                                break;
                            case 3:
                                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                                break;
                            default:
                                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        }
                        dialog.dismiss();
                    }
                }
        );

        // Build the dialog and show it.
        AlertDialog fMapTypeDialog = builder.create();
        fMapTypeDialog.setCanceledOnTouchOutside(true);
        fMapTypeDialog.show();

    }

    @Override
    public void showOrHideProgressProgress(int visibilty) {
        progressBarStoreLand.setVisibility(visibilty == View.VISIBLE ? View.VISIBLE : View.GONE);
        btnSubmit.setVisibility(visibilty == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    @Override
    public void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.Zr3iMap);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        checkPermissionAndGetCurrentLocation();
        initPlaces();
        if (ContextCompat.checkSelfPermission(LandCreationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            mMap.setMyLocationEnabled(true);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions().position(latLng);
                Marker marker = mMap.addMarker(markerOptions);
                latLngList.add(latLng);
                markerList.add(marker);
            }
        });
    }

    private void checkPermissionAndGetCurrentLocation() {
        ActivityCompat.requestPermissions(LandCreationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
        final LocationManager manager = (LocationManager) LandCreationActivity.this.getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }
    }


    private void GetLocation() {
        ActivityCompat.requestPermissions(LandCreationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);

        final LocationManager manager = (LocationManager) LandCreationActivity.this.getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }
    }

    private void getCurrentLocation() {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(LandCreationActivity.this);
        if (ActivityCompat.checkSelfPermission(LandCreationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                LandCreationActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 17.0f));
                    }
                }
            });
        }
    }


    private void initPlaces() {
//        Places.initialize(getContext(), "AIzaSyC-J-HZNhNSq2w4h9Jjut3Ep2s4SNONrAA");
        Places.initialize(this, getResources().getString(R.string.places_app_key));
        Places.createClient(this);
        setupPlacesAutoComplete();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @androidx.annotation.NonNull String[] permissions, @androidx.annotation.NonNull int[] grantResults) {
        if (requestCode == 100 && permissions.length > 0) {
            int deniedPermissionsCount = 0;
            for (int i = 0; i < permissions.length; i++)
                if (grantResults[i] == PackageManager.PERMISSION_DENIED)
                    deniedPermissionsCount++;

            if (deniedPermissionsCount == 0)
                getCurrentLocation();

        } else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    private void setupPlacesAutoComplete() {
        findViewById(R.id.autocompleatebackground).setVisibility(android.view.View.VISIBLE);
        AutocompleteSupportFragment placesFragment = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.zr3i_autocomplete);
        placesFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS));
        placesFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@androidx.annotation.NonNull Place place) {
                Toast.makeText(LandCreationActivity.this, "good", Toast.LENGTH_SHORT).show();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 17.0f));
            }

            @Override
            public void onError(@androidx.annotation.NonNull com.google.android.gms.common.api.Status status) {
                Toast.makeText(LandCreationActivity.this, status.getStatusMessage(), Toast.LENGTH_SHORT).show();
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

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(LandCreationActivity.this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        landCreationPresenter.setView(LandCreationActivity.this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        landCreationPresenter.setView(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        landCreationPresenter.setView(LandCreationActivity.this);
    }

}