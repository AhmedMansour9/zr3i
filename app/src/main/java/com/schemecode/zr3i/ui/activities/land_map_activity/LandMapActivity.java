package com.schemecode.zr3i.ui.activities.land_map_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.maps.android.SphericalUtil;
import com.schemecode.zr3i.R;
import com.schemecode.zr3i.data.models.list_lands.Datum;
import com.schemecode.zr3i.data.models.show_lands.A;
import com.schemecode.zr3i.data.models.show_lands.Crop_Field;
import com.schemecode.zr3i.data.models.show_lands.Data;
import com.schemecode.zr3i.ui.activities.login_activity.LoginActivity;
import com.schemecode.zr3i.ui.activities.new_land_activity.LandCreationActivity;
import com.schemecode.zr3i.ui.dialogs.error_dialog.ErrorDialog;
import com.schemecode.zr3i.ui.dialogs.success_dialog.SuccessDialog;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;
import static com.schemecode.zr3i.ui.activities.new_land_activity.LandCreationActivity.MAP_TYPE_ITEMS;

public class LandMapActivity extends AppCompatActivity implements OnMapReadyCallback, LandMapContract.view {

    private ImageView imgSelectMap;
    private GoogleMap mMap;
    private Button btnDraw, btnRemove, btnSubmit;
    private List<LatLng> latLngList = new ArrayList<>();
    private List<Marker> markerList = new ArrayList<>();
    List<com.schemecode.zr3i.data.classes.LatLng> latLngListToSent;
    private HashMap<String, List<com.schemecode.zr3i.data.classes.LatLng>> listMap;
    private HashMap<String, Map<String, String>> listMap2;
    private Polygon polygon = null;
    private String space, token, secret, contractspace;
    private LandMapPresenter landMapPresenter;
    private SharedPreferences sharedPreferences;
    private int landId;
    private HashMap<String, String> arrayJsonMap;
    List<Crop_Field> itemList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_map);
        landMapPresenter = new LandMapPresenter(this);
        Intent intent = getIntent();
        landMapPresenter.start();
        landId = intent.getIntExtra("land_id", 0);
        sharedPreferences = getSharedPreferences("MahmoudPref", MODE_PRIVATE);
        token = sharedPreferences.getString("token", "token");
        secret = sharedPreferences.getString("secret", "secret");
        landMapPresenter.getLandObject(landId, token, secret);
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Crop_Field>>() {
        }.getType();

         itemList = gson.fromJson(intent.getStringExtra("list"), listType);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        checkPermissionAndGetCurrentLocation();
        initPlaces();
        if (ContextCompat.checkSelfPermission(LandMapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
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

    @Override
    public void initUi() {
        listMap = new HashMap<>();
        imgSelectMap = findViewById(R.id.select_map_type_image);
        btnDraw = findViewById(R.id.draw_ploygon_button);
        btnRemove = findViewById(R.id.remove_ploygon_button);
        btnSubmit = findViewById(R.id.submit_ploygon_button);
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
                    listMap = new HashMap<>();
                    listMap2 = new HashMap<>();
                    listMap.clear();
                    listMap2.clear();
                    arrayJsonMap = new HashMap<>();
                    arrayJsonMap.clear();
                    latLngListToSent = new ArrayList<>();
                    latLngListToSent.clear();
                    PolygonOptions polygonOptions = new PolygonOptions().addAll(latLngList).clickable(true);
                    polygon = mMap.addPolygon(polygonOptions);
                    int strokeColor = polygon.getStrokeColor() ^ 0x00ffffff;
                    polygon.setStrokeColor(strokeColor);
                    space = String.valueOf(SphericalUtil.computeArea(latLngList));
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    for (int i = 0; i < polygon.getPoints().size(); i++) {
                        StringTokenizer stringTokenizer = new StringTokenizer("(");
                        String[] lat = String.valueOf(polygon.getPoints().get(i).toString()).split(" ");
                        String[] latLangFinsh = lat[1].substring(1, lat[1].length() - 1).split(",");
                        com.schemecode.zr3i.data.classes.LatLng latLng = new com.schemecode.zr3i.data.classes.LatLng(latLangFinsh[0], latLangFinsh[1]);
                        latLngListToSent.add(new com.schemecode.zr3i.data.classes.LatLng(latLangFinsh[0], latLangFinsh[1]));
                    }
                    listMap.put("map[polygon]", latLngListToSent);
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
                if (polygon == null) {
                    return;
                } else if (polygon.getPoints().isEmpty()) {
                    return;
                } else if (latLngListToSent == null) {
                    return;
                } else if (latLngListToSent.isEmpty()) {
                    return;
                } else {
                    LatLng currentMapCordinator = mMap.getCameraPosition().target;
                    float zoom = mMap.getCameraPosition().zoom;
                    Gson gson = new Gson();
                    arrayJsonMap = new HashMap<>();
                    String latLngJsonArray = gson.toJson(latLngListToSent);
                    arrayJsonMap.put("map[latitude]", String.valueOf(currentMapCordinator.latitude));
                    arrayJsonMap.put("map[longitude]", String.valueOf(currentMapCordinator.longitude));
                    arrayJsonMap.put("map[zoom]", String.valueOf(zoom));
                    arrayJsonMap.put("map[polygon]", latLngJsonArray);
                    Log.e("space", space);
                    landMapPresenter.editLandMap(landId, arrayJsonMap, space, token, secret);
                }
            }
        });
    }

    @Override
    public void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.Zr3iMap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void setData(Data data) {

        if (itemList.get(0).getMapDetails().getLatitude() != null &&
                itemList.get(0).getMapDetails().getLongitude() != null &&
                itemList.get(0).getMapDetails().getZoom() != null &&
                itemList.get(0).getMapDetails().getPolygon() != null) {
            btnDraw.setEnabled(true);
            btnRemove.setEnabled(true);
            btnSubmit.setEnabled(true);
            double lat = Double.parseDouble(itemList.get(0).getMapDetails().getLatitude());
            double lng = Double.parseDouble(itemList.get(0).getMapDetails().getLongitude());
            float zoom = Float.parseFloat(itemList.get(0).getMapDetails().getZoom());
            Log.e("lat", String.valueOf(lat));
            Log.e("lng", String.valueOf(lng));
            Log.e("zoom", String.valueOf(zoom));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), zoom));
            for (int i = 0; i < itemList.get(0).getMapDetails().getPolygon().size(); i++) {
                LatLng latLng = new LatLng(itemList.get(0).getMapDetails().getPolygon().get(i).getLat(), itemList.get(0).getMapDetails().getPolygon().get(i).getLng());
                latLngList.add(latLng);
            }
            PolygonOptions polygonOptions = new PolygonOptions().addAll(latLngList).clickable(true);
            polygon = mMap.addPolygon(polygonOptions);
            int strokeColor = polygon.getStrokeColor() ^ 0x00ffffff;
            polygon.setStrokeColor(RED);
            polygon.setFillColor(R.color.tranparent_red);
            polygon.setStrokeWidth(2);
            space = String.valueOf(SphericalUtil.computeArea(latLngList));
        } else {
            landMapPresenter.inflateDialog(false, "عفوا طلبك ما زال تحت التنفيذ");
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void inflateSuccessDialog(boolean isSuccess, String message) {
        if (isSuccess == true) {
            SuccessDialog successDialog = new SuccessDialog(LandMapActivity.this, message);
            successDialog.show();
        } else {
            ErrorDialog errorDialog = new ErrorDialog(LandMapActivity.this, message);
            errorDialog.show();
        }
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

    private void checkPermissionAndGetCurrentLocation() {
        ActivityCompat.requestPermissions(LandMapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
    }

    private void getCurrentLocation() {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(LandMapActivity.this);
        if (ActivityCompat.checkSelfPermission(LandMapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                LandMapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
//                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 17.0f));
                    }
                }
            });
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

    private void initPlaces() {
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
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 17.0f));
            }

            @Override
            public void onError(@androidx.annotation.NonNull com.google.android.gms.common.api.Status status) {
                Toast.makeText(LandMapActivity.this, status.getStatusMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        landMapPresenter.setView(null);
    }

    @Override
    protected void onStart() {
        super.onStart();
        landMapPresenter.setView(LandMapActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        landMapPresenter.setView(LandMapActivity.this);
    }
}