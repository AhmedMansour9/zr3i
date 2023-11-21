package com.schemecode.zr3i.data.api;

import com.schemecode.zr3i.data.classes.LatLng;
import com.schemecode.zr3i.data.models.account_types.DataAccountTypes;
import com.schemecode.zr3i.data.models.add_farmer.FarmersStore;
import com.schemecode.zr3i.data.models.countries.DataCountries;
import com.schemecode.zr3i.data.models.crops.DataCrops;
import com.schemecode.zr3i.data.models.delete_farmer.FarmersDelete;
import com.schemecode.zr3i.data.models.edit_farmers.FarmersUpdate;
import com.schemecode.zr3i.data.models.fblogin.AuthSocialLogin;
import com.schemecode.zr3i.data.models.forget.AuthForget;
import com.schemecode.zr3i.data.models.generate_forum_link.GenerateLink;
import com.schemecode.zr3i.data.models.land_store.LandStore;
import com.schemecode.zr3i.data.models.list_lands.LandList;
import com.schemecode.zr3i.data.models.login.AuthLogin;
import com.schemecode.zr3i.data.models.plan_renew.AccPlanRenew;
import com.schemecode.zr3i.data.models.plan_upgrade.AccPlanUpgrade;
import com.schemecode.zr3i.data.models.plans.DataPlans;
import com.schemecode.zr3i.data.models.profile.AccProfile;
import com.schemecode.zr3i.data.models.register.AuthRegister;
import com.schemecode.zr3i.data.models.show_details_lands.LandsShowDetails;
import com.schemecode.zr3i.data.models.show_farmers.FarmersList;
import com.schemecode.zr3i.data.models.show_lands.LandShow;
import com.schemecode.zr3i.data.models.update_land.LandsUpdate;
import com.schemecode.zr3i.data.models.update_map.LandsUpdateMap;
import com.schemecode.zr3i.data.models.update_pass.AccUpdate;
import com.schemecode.zr3i.data.models.update_profile.AccProfileUpdate;
import com.schemecode.zr3i.data.models.usage.AccUsage;

import org.json.JSONArray;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {
    @POST("data/plans")
    @FormUrlEncoded
    Call<DataPlans> getPlans(
            @Field("lang") String lang,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey
    );

    @POST("data/account_types")
    @FormUrlEncoded
    Call<DataAccountTypes> getAccountTypes(
            @Field("lang") String lang,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey
    );


    @POST("data/countries")
    @FormUrlEncoded
    Call<DataCountries> getCountries(
            @Field("lang") String lang,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey
    );


    @POST("data/crops")
    @FormUrlEncoded
    Call<DataCrops> getCrops(
            @Field("lang") String lang,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey
    );

    @POST("auth/register")
    @FormUrlEncoded
    Call<AuthRegister> register(
            @Field("first_name") String firstName,
            @Field("last_name") String lastName,
            @Field("gender") int gender,
            @Field("country_id") int countryId,
            @Field("city") String city,
            @Field("mobile") String mobile,
            @Field("year") int year,
            @Field("day") int day,
            @Field("month") int month,
            @Field("plan") int plan,
            @Field("address") String address,
            @Field("account_type") int accountType,
            @Field("email") String email,
            @Field("password") String password,
            @Field("password_confirmation") String passConfirmation,
            @Field("lang") String lang,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey
    );

    @POST("auth/login")
    @FormUrlEncoded
    Call<AuthLogin> login(
            @Field("email") String email,
            @Field("password") String password,
            @Field("lang") String lang,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey
    );

    @POST("auth/social_login")
    @FormUrlEncoded
    Call<AuthSocialLogin> fbLogin(
            @Field("email") String email,
            @Field("name") String name,
            @Field("id") String id,
            @Field("social") String social,
            @Field("lang") String lang,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey
    );

    @POST("auth/forget_password")
    @FormUrlEncoded
    Call<AuthForget> forgetPass(
            @Field("email") String email,
            @Field("lang") String lang,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey
    );


    @POST("lands/store")
    @FormUrlEncoded
    Call<LandStore> storeLand(
            @FieldMap Map<String, String> fields,
            @Field("actual_space") String actualSpace,
            @Field("owner") String owner,
            @Field("city") String city,
            @Field("city_area") String cityArea,
            @Field("contract_space") String contractSpace,
            @Field("country_id") int countryId,
            @Field("crop_id") int cropId,
            @Field("crop_planting_cycles") int cropPlantingCycles,
            @Field("desc") String desc ,
            @Field("lang") String lang ,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey,
            @Field("token") String token,
            @Field("secret") String secret
    );

    @POST("lands/list")
    @FormUrlEncoded
    Call<LandList> listLands(
            @Field("page") int page ,
            @Field("lang") String lang ,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey,
            @Field("token") String token,
            @Field("secret") String secret
    );


    @POST("lands/show/{id}")
    @FormUrlEncoded
    Call<LandShow> showLands(
            @Field("lang") String lang ,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey,
            @Field("token") String token,
            @Field("secret") String secret ,
            @Path("id") int id
    );


    @POST("lands/show_details/{id}")
    @FormUrlEncoded
    Call<LandsShowDetails> showDetailsLand(
            @Field("lang") String lang ,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey,
            @Field("token") String token,
            @Field("secret") String secret ,
            @Path("id") int id
    );

    @POST("account/get_profile")
    @FormUrlEncoded
    Call<AccProfile> getProfile(
            @Field("lang") String lang ,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey,
            @Field("token") String token,
            @Field("secret") String secret
    );

    @POST("account/update_profile")
    @Multipart
    Call<AccProfileUpdate> updateProfile(
            @Part("first_name") RequestBody firstName,
            @Part("last_name") RequestBody lastName,
            @Part("gender") RequestBody gender,
            @Part("country_id") RequestBody countryId,
            @Part("city") RequestBody city,
            @Part("mobile") RequestBody mobile,
            @Part("year") RequestBody year,
            @Part("day") RequestBody day,
            @Part("month") RequestBody month,
            @Part("email") RequestBody email ,
            @Part("image") RequestBody image ,
            @Part MultipartBody.Part file ,
            @Part("lang") RequestBody lang ,
            @Part("platform") RequestBody platform,
            @Part("hash_key") RequestBody hashKey,
            @Part("token") RequestBody token,
            @Part("secret") RequestBody secret
    );

    @POST("account/usage")
    @FormUrlEncoded
    Call<AccUsage> usage(
            @Field("lang") String lang ,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey,
            @Field("token") String token,
            @Field("secret") String secret
    );
    @POST("account/update_password")
    @FormUrlEncoded
    Call<AccUpdate> changePass(
            @Field("current_password") String currentPass ,
            @Field("password") String newPass ,
            @Field("password_confirmation") String newPassConfirmation ,
            @Field("lang") String lang ,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey,
            @Field("token") String token,
            @Field("secret") String secret
    );

    @POST("farmers/list")
    @FormUrlEncoded
    Call<FarmersList> listFarmers(
            @Field("land_id") int landId ,
            @Field("lang") String lang ,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey,
            @Field("token") String token,
            @Field("secret") String secret
    );

    @POST("farmers/update")
    @FormUrlEncoded
    Call<FarmersUpdate> updateFarmer(
            @Field("id") int landId ,
            @Field("name") String name ,
            @Field("mobile") String mobile ,
            @Field("email") String email ,
            @Field("city") String city ,
            @Field("address") String address ,
            @Field("national_id") String nationalId ,
            @Field("age") String age ,
            @Field("lang") String lang ,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey,
            @Field("token") String token,
            @Field("secret") String secret
    );

    @POST("farmers/store")
    @FormUrlEncoded
    Call<FarmersStore> storeFarmer(
            @Field("land_id") int landId ,
            @Field("name") String name ,
            @Field("mobile") String mobile ,
            @Field("email") String email ,
            @Field("city") String city ,
            @Field("address") String address ,
            @Field("national_id") String nationalId ,
            @Field("age") String age ,
            @Field("lang") String lang ,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey,
            @Field("token") String token,
            @Field("secret") String secret
    );

    @POST("farmers/delete")
    @FormUrlEncoded
    Call<FarmersDelete> deleteFarmer(
            @Field("id") int id,
            @Field("lang") String lang ,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey,
            @Field("token") String token,
            @Field("secret") String secret
    );

    @POST("lands/delete")
    @FormUrlEncoded
    Call<FarmersDelete> deleteLand(
            @Field("id") int id,
            @Field("lang") String lang ,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey,
            @Field("token") String token,
            @Field("secret") String secret
    );



    @POST("lands/update")
    @FormUrlEncoded
    Call<LandsUpdate> updateLand(
            @Field("owner") String owner,
            @Field("city") String city,
            @Field("city_area") String cityArea,
            @Field("contract_space") String contractSpace,
            @Field("country_id") int countryId,
            @Field("crop_planting_cycles") int cropPlantingCycles,
            @Field("desc") String desc ,
            @Field("id") int id ,
            @Field("lang") String lang ,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey,
            @Field("token") String token,
            @Field("secret") String secret
    );

    @POST("lands/update_map")
    @FormUrlEncoded
    Call<LandsUpdateMap> updateMap(
            @Field("id") int landId ,
            @FieldMap Map<String, String> fields,
            @Field("actual_space") String actualSpace,
            @Field("lang") String lang ,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey,
            @Field("token") String token,
            @Field("secret") String secret
    );

    @POST("account/generate_forum_link")
    @FormUrlEncoded
    Call<GenerateLink> generateMontadeLink(
            @Field("lang") String lang ,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey,
            @Field("token") String token,
            @Field("secret") String secret
    );

    @POST("account/plan_renew")
    @FormUrlEncoded
    Call<AccPlanRenew> plan_renew(
            @Field("months") int month ,
            @Field("lang") String lang ,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey,
            @Field("token") String token,
            @Field("secret") String secret
    );

    @POST("account/plan_upgrade")
    @FormUrlEncoded
    Call<AccPlanUpgrade> plan_upgrade(
            @Field("plan_id") int month ,
            @Field("lang") String lang ,
            @Field("platform") String platform,
            @Field("hash_key") String hashKey,
            @Field("token") String token,
            @Field("secret") String secret
    );

}
