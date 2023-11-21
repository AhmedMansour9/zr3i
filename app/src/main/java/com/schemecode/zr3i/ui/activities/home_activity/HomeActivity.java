package com.schemecode.zr3i.ui.activities.home_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.android.material.navigation.NavigationView;
import com.schemecode.zr3i.MainActivity;
import com.schemecode.zr3i.R;
import com.schemecode.zr3i.data.models.show_details_lands.Main;
import com.schemecode.zr3i.ui.activities.SubDomainScreen;
import com.schemecode.zr3i.ui.activities.change_pass.ChangePassActivity;
import com.schemecode.zr3i.ui.activities.list_lands_activity.ListLandsActivity;
import com.schemecode.zr3i.ui.activities.login_activity.LoginActivity;
import com.schemecode.zr3i.ui.activities.montada_web_view.MontadaActivity;
import com.schemecode.zr3i.ui.activities.new_land_activity.LandCreationActivity;
import com.schemecode.zr3i.ui.activities.profile_activity.ProfileActivity;
import com.schemecode.zr3i.ui.activities.splash_activity.LoginSplashActivity;
import com.schemecode.zr3i.ui.activities.usage_activity.UsageActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements HomeContract.view {
    private DrawerLayout drawerLayout;
    private ImageView imgMenueIcon;
    private NavigationView navView;
    private HomePresenter homePresenter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private AppCompatButton btnNewLand , btnShowLands , btnZra3inMontada ;
    private String token , secret ;
    private CircleImageView imgProfile;
    private TextView tvNameOfPerson ;
    private String imageUrl , message ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedPreferences = getSharedPreferences("MahmoudPref" , MODE_PRIVATE);
        imageUrl = sharedPreferences.getString("image_url" , "");
        message = sharedPreferences.getString("message" , "");
        token = sharedPreferences.getString("token" , "token");
        secret = sharedPreferences.getString("secret" , "secret");
        homePresenter = new HomePresenter(this);
        homePresenter.start();
    }

    @Override
    public void initUi() {
        imgMenueIcon = findViewById(R.id.menue_icon_image_view);
        btnNewLand = findViewById(R.id.new_land_button);
        btnShowLands = findViewById(R.id.lands_button);
        btnZra3inMontada = findViewById(R.id.zra3iin_button);
        navView = findViewById(R.id.nav_view);
        View header = navView.getHeaderView(0);
        tvNameOfPerson = header.findViewById(R.id.profile_text_view);
        imgProfile = header.findViewById(R.id.profile_image_view);
        Glide.with(HomeActivity.this).load(imageUrl).into(imgProfile);
        tvNameOfPerson.setText(message);
    }

    @Override
    public void handleClicks() {
        btnNewLand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this , LandCreationActivity.class);
                startActivity(intent);
            }
        });
        btnShowLands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this , ListLandsActivity.class);
                startActivity(intent);
            }
        });
        btnZra3inMontada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.generateMontadaLink(token , secret);
            }
        });

    }

    @Override
    public void initDrawer() {
        drawerLayout = findViewById(R.id.drawerMenu);
        navView = findViewById(R.id.nav_view);
        openDrawer();
    }

    @Override
    public void openDrawer() {
        imgMenueIcon.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void SelectItemInDrawerMenu() {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@androidx.annotation.NonNull MenuItem menuItem) {
                int itemid = menuItem.getItemId();
                switch (itemid) {
                    case R.id.store_land:
                        createNewLandActivity();
                        break;
                    case R.id.show_lands:
                        showLandsActivity();
                        break;
                    case R.id.profile:
                        profileActivity();
                        break;
                    case R.id.usage:
                        usageActivity();
                        break;
                    case R.id.change_pass:
                        changePassActivity();
                        break;
                    case R.id.logout:
                        logout();
                        break;
                }
                return false;
            }
        });
    }
    public void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();

            }
        }).executeAsync();
    }
    private void logout() {
        if (getSharedPreferences("MahmoudPref", MODE_PRIVATE).edit().clear().commit() == true) {
            disconnectFromFacebook();
            Intent intent = new Intent(HomeActivity.this , SubDomainScreen.class);
            startActivity(intent);
            finishAffinity();
        }

    }


    @Override
    public void createNewLandActivity() {
        Intent intent = new Intent(HomeActivity.this, LandCreationActivity.class);
        startActivity(intent);
    }

    @Override
    public void showLandsActivity() {
        Intent intent = new Intent(HomeActivity.this, ListLandsActivity.class);
        startActivity(intent);
    }

    @Override
    public void profileActivity() {
        Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void usageActivity() {
        Intent intent = new Intent(HomeActivity.this, UsageActivity.class);
        startActivity(intent);
    }

    @Override
    public void changePassActivity() {
        Intent intent = new Intent(HomeActivity.this, ChangePassActivity.class);
        startActivity(intent);
    }

    @Override
    public void join(String url) {
        Intent montadaIntent = new Intent(HomeActivity.this , MontadaActivity.class);
        montadaIntent.putExtra("url" , url);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(montadaIntent);
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
    public void onBackPressed() {
        super.onBackPressed();
//        moveTaskToBack(true);
//        finishAffinity();
//        finish();
    }
}