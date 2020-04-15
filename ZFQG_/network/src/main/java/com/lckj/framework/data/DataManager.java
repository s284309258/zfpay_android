package com.lckj.framework.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.lckj.jycm.zfqg_network.GetAppImgListBean;
import com.lython.network.R;

public class DataManager {
    /*不要随意修改，其他库有引用"lcwl"*/
    private static final String PREF_NAME = "jycm";
    private static final String PREF_TOKEN = "pref_token";
    /*不要随意修改，其他库有引用"pref_qiniu_domain"*/
    private static final String PREF_QINIU_DOMAIN = "pref_qiniu_domain";
    private static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    private static final String USER_AGE = "user_age";
    private static final String USER_SEX = "user_sex";
    public static final String USER_HEAD_PHOTO = "user_head_photo";
    private static final String USER_TEL = "user_tel";
    private static final String INVITATION_CODE = "invitation_code";
    public static final String USER_GOLD = "user_gold";
    private final SharedPreferences mPref;
    private final Context mContext;
    private final ACache mACache;


    public SharedPreferences getmPref() {
        return mPref;
    }

    public DataManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        mACache = ACache.get(context);
        mContext = context;
    }

    public String getToken() {
        return mPref.getString(PREF_TOKEN, "");
    }


    public void setToken(String token) {
        mPref.edit().putString(PREF_TOKEN, token).commit();
    }

    public void setQiniuDomain(String domain) {
        mPref.edit().putString(PREF_QINIU_DOMAIN, domain).commit();
    }

    public String getQiniuDomain() {
        String string = mPref.getString(PREF_QINIU_DOMAIN, "");
        if (TextUtils.isEmpty(string)) string = "http://cdn.rxhwl.com";
        return string;
    }

    public void setUserId(String userId) {
        mPref.edit().putString(USER_ID, userId).commit();
    }

    public String getUserId() {
        return mPref.getString(USER_ID, "");
    }

    public void setUserName(String userName) {
        mPref.edit().putString(USER_NAME, userName).commit();

    }

    public String getUserName() {
        String username = null;
        if (TextUtils.isEmpty(mPref.getString(USER_NAME, ""))) {
            switch (getAuthStatus()) {
                case "00":
                    username = mContext.getString(R.string.weishiming);
                    break;
                case "04":
                    username = mContext.getString(R.string.shimingshenhe);
                    break;
                case "08":
                    username = mContext.getString(R.string.shimingshibai);
                    break;
                case "09":
                    username = mContext.getString(R.string.shimingchenggong);
                    break;
            }
        } else {
            username = mPref.getString(USER_NAME, "");
        }
        return username;
    }

    public void setAge(int age) {
        mPref.edit().putInt(USER_AGE, age).commit();

    }

    public int getAge() {
        return mPref.getInt(USER_AGE, 0);
    }

    public void setSex(int sex) {
        mPref.edit().putInt(USER_SEX, sex).commit();

    }

    public int getSex() {
        return mPref.getInt(USER_SEX, 0);
    }

    public void setHeadPhoto(String headPhoto) {
        mPref.edit().putString(USER_HEAD_PHOTO, headPhoto).commit();

    }

    public String getHeadPhoto() {
        return mPref.getString(USER_HEAD_PHOTO, "");
    }

    public void setUserTel(String userTel) {
        mPref.edit().putString(USER_TEL, userTel).commit();

    }

    public String getUserTel() {
        return mPref.getString(USER_TEL, "");
    }

    public void setInvitationCode(String invitationCode) {
        mPref.edit().putString(INVITATION_CODE, invitationCode).commit();
    }

    public String getInvitationCode() {
        return mPref.getString(INVITATION_CODE, "");
    }

    public void setGold(String gold) {
        mPref.edit().putString(USER_GOLD, gold).commit();
    }

    public String getGold() {
        return mPref.getString(USER_GOLD, "0");
    }

    public boolean islogin() {
        return mPref.getBoolean("is_login", false);
    }

    public void setLoin(boolean login) {
        mPref.edit().putBoolean("is_login", login).commit();
    }

    public void setPw(String isPw) {
        mPref.edit().putString("pw", isPw).commit();
    }

    public String getPw() {
        return mPref.getString("pw", "");
    }

    public void setSignInfo(int signSum) {
        mPref.edit().putInt("sign_info", signSum).commit();
    }

    public int getSignInfo() {
        return mPref.getInt("sign_info", -1);
    }

    public void setAmount(String amount) {
        mPref.edit().putString("amount", amount).commit();
    }

    public String getAmount() {
        return mPref.getString("amount", "");
    }

    public void setTeamMember(int teamMember) {
        mPref.edit().putInt("team_member", teamMember).commit();
    }

    public int getTeamMember() {
        return mPref.getInt("team_member", 0);
    }

    public void setQrCodeUrl(String qr_code_url) {
        mPref.edit().putString("qr_code_url", qr_code_url).commit();
    }

    public String getQrCodeUrl() {
        return mPref.getString("qr_code_url", "");
    }

    public void setAuthStatus(String auth_status) {
        mPref.edit().putString("auth_status", auth_status).commit();
    }

    public String getAuthStatus() {
        return mPref.getString("auth_status", "");
    }

    public void setAlgebra(String algebra) {
        mPref.edit().putString("algebra", algebra).commit();
    }

    public String getAlgebra() {
        return mPref.getString("algebra", "");
    }

    public void setPwChecked(boolean isChecked) {
        mPref.edit().putBoolean("PwChecked", isChecked).commit();

    }

    public boolean getPwChecked() {
        return mPref.getBoolean("PwChecked", false);
    }

    public void setAccount(String account) {
        mPref.edit().putString("account", account).commit();

    }

    public String getAccount() {
        return mPref.getString("account", "");
    }

    public void setPayPw(boolean payPw) {
        mPref.edit().putBoolean("payPw", payPw).commit();

    }

    public boolean getPayPw() {
        return mPref.getBoolean("payPw", false);
    }

    public void setAppId(String app_id) {
        mPref.edit().putString("app_id", app_id).commit();
    }

    public String getAppId() {
        return mPref.getString("app_id", "");
    }

    public void setStartImage(String startImage) {
        mPref.edit().putString("startImage", startImage).commit();
    }

    public String getStartImage() {
        return mPref.getString("startImage", "");
    }
}
