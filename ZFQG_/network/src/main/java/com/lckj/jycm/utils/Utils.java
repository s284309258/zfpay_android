package com.lckj.jycm.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.Service;
import android.bluetooth.BluetoothManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lython.network.R;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.Key;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 */
public class Utils {
    // TODO: 2018/10/12 调试使用 后面修改
    public final static boolean DEBUG = true;
    public static String LoginToOriginalAct_Field = "LoginToOriginalAct";
    private static Toast mToast;
    private static boolean isShowingToast = false;
    private static String mLastToastMsg = null;
    private static final int WHAT_TOAST_RELEASE = 1;
    private static float sDensity;
    private static int sWidth;
    private static int sHeight;
    private static Handler sHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT_TOAST_RELEASE:
                    isShowingToast = false;
                    break;
            }
        }
    };
    private static int sDpi;

    public static int getWidth(Context ctx) {
        if (sWidth == 0) {
            getDensity(ctx);
        }
        return sWidth;
    }

    public static int getsHeight(Context ctx) {
        if (sHeight == 0) {
            getDensity(ctx);
        }
        return sHeight;
    }

    public static float getDensity(Context ctx) {
        if (sDensity == 0) {
            WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            sDpi = dm.densityDpi;
            sWidth = dm.widthPixels;
            sHeight = dm.heightPixels;
            sDensity = dm.density;
        }
        return sDensity;
    }


    public static Handler getUIHandler() {
        return sHandler;
    }


    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    /**
     * px转换sp
     */
    public static int px2sp(Context context, int pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);

    }

    public static void showMsg(Context context, int resId) {
        showMsg(context, context.getString(resId));
    }

    public static void showMsg(Context context, CharSequence msg) {
        if (msg == null) {
            return;
        }
        String result = msg.toString();
        if (!isShowingToast || mLastToastMsg == null || !mLastToastMsg.equals(result)) {//若是不一致的信息，则必须显示 2016.5.6 by wp.nine
            isShowingToast = true;
            mLastToastMsg = result;
            if (null == mToast) {
                mToast = Toast.makeText(context, result, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(msg);
            }
            mToast.show();
            sHandler.removeMessages(WHAT_TOAST_RELEASE);
            sHandler.sendEmptyMessageDelayed(WHAT_TOAST_RELEASE, 1500);
        }

    }

    public static void releaseToast() {
        if (null != mToast) {
            mToast.cancel();
            isShowingToast = false;
            mToast = null;
        }
    }

    public static String getVersionName(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packInfo = pm.getPackageInfo(context.getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0.0";
        }
    }

    public static int getVersionCode(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packInfo = pm.getPackageInfo(context.getPackageName(), 0);
            return packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }

    public static String convertObjToJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }


    public static HashMap<String, Object> convertObjectToMap(Object obj) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        Class clazz = obj.getClass();
        List<Class> clazzs = new ArrayList<Class>();
        do {
            clazzs.add(clazz);
            clazz = clazz.getSuperclass();
        } while (!clazz.equals(Object.class));
        try {
            for (Class iClazz : clazzs) {
                Field[] fields = iClazz.getDeclaredFields();
                for (Field field : fields) {
                    Object objVal = null;
                    field.setAccessible(true);

                    objVal = field.get(obj);
                    if (objVal != null) {
                        //如果为null值 则不计入 md5摘要 ，因为 http传输层 不会把null值送出
                        hashMap.put(field.getName(), objVal);
                    }
                }
            }
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }

        return hashMap;
    }

    /**
     * 获取文件夹大小
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 删除指定目录下文件及目录
     *
     * @param deleteThisPath
     * @param filePath
     * @return
     */
    public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {// 如果下面还有文件
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    } else {// 目录
                        if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }

    /**
     * 根据某个日期得到前一天日期
     *
     * @param d
     * @return
     */
    public static long getBeforeDate(long d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(d * 1000L);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTimeInMillis();
    }

    /**
     * 根据某个日期的下个月的这天
     *
     * @param d
     * @return
     */
    public static long getNextDate(long d, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(d * 1000L);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取时间相关显示串
     *
     * @param isTitle
     * @param curDate
     * @return
     */
    public static String getDisplayStr(boolean isTitle, Date curDate) {
        final String TIME_DIVIDER = ":"; //时间分隔符
        final String DATE_DIVIDER = "-"; //日期分隔符
        String sectionTitle;
        Date nowTime = new Date();
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTime(nowTime);
        Calendar calendar = Calendar.getInstance();// null;
        calendar.setTime(curDate);

        if (calendar.get(Calendar.YEAR) == calendarNow.get(Calendar.YEAR))//日期不带年 如 3月
        {
            if (calendar.get(Calendar.MONTH) == calendarNow.get(Calendar.MONTH))//本月
            {
                sectionTitle = "本月";
                if (!isTitle) {
                    int spanDay = calendarNow.get(Calendar.DATE) - calendar.get(Calendar.DATE);
                    sectionTitle = formatDateStr(TIME_DIVIDER,
                            calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE));
                    if (calendarNow.get(Calendar.DATE) - calendar.get(Calendar.DATE) < 3) {
                        switch (spanDay) {
                            case 0:
                                //这里时间不做变化
                                break;          //修复日期显示错误 2015-11-9 by wp.nine
                            case 1:
                                sectionTitle = "昨天" + sectionTitle;
                                break;
                            case 2:
                                sectionTitle = "前天" + sectionTitle;
                                break;
                        }
                    } else {
                        sectionTitle = formatDateStr(DATE_DIVIDER, calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
                    }
                }
            } else// 3月
            {
                sectionTitle = (calendar.get(Calendar.MONTH) + 1) + "月";
                if (!isTitle) {
                    sectionTitle = formatDateStr(DATE_DIVIDER, calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
                }
            }
        } else//日期带年 如 2014年4月
        {
            sectionTitle = calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月";
            if (!isTitle) {
                sectionTitle = formatDateStr(DATE_DIVIDER,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH) + 1,
                        calendar.get(Calendar.DATE));
            }
        }
        if (sectionTitle.equals("0月")) {
            sectionTitle += " ";
        }
        return sectionTitle;
    }


    private static String formatDateStr(String divider, int... time) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < time.length; i++) {
            if (time[i] < 10) {
                sb.append("0" + time[i]);
            } else {
                sb.append("" + time[i]);
            }
            sb.append(divider);
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - divider.length(), sb.length());        //减去最后一个符中的长度
        }
        return sb.toString();
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void enableStrictMode() {
        if (Utils.hasGingerbread()) {
            StrictMode.ThreadPolicy.Builder threadPolicyBuilder =
                    new StrictMode.ThreadPolicy.Builder()
                            .detectAll()
                            .penaltyLog();
            StrictMode.VmPolicy.Builder vmPolicyBuilder =
                    new StrictMode.VmPolicy.Builder()
                            .detectAll()
                            .penaltyLog();

            StrictMode.setThreadPolicy(threadPolicyBuilder.build());
            StrictMode.setVmPolicy(vmPolicyBuilder.build());
        }
    }

    /**
     * 获得状态栏的高度
     */
    public static int getStatusBarHeight(Resources res) {
        String key = "status_bar_height";
        int result = 0;
        int resourceId = res.getIdentifier(key, "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }


    public static boolean hasFroyo() {
        // Can use static final constants like FROYO, declared in later versions
        // of the OS since they are inlined at compile time. This is guaranteed behavior.
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean hasJellyBeanMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    /**
     * 检查当前的网络状态，使用WIFI，并且网络是连接的
     */
    public static boolean isWIFIAvailable(Context context) {
        ConnectivityManager connectMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectMgr == null) {
            return false;
        }
        NetworkInfo info = connectMgr.getActiveNetworkInfo();
        if (info != null && info.getType() == ConnectivityManager.TYPE_WIFI && info.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * 检查当前的网络状态，使用数据流量，并且网络是连接的
     */
    public static boolean isUsingInternet(Context context) {
        ConnectivityManager connectMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectMgr == null) {
            return false;
        }
        NetworkInfo info = connectMgr.getActiveNetworkInfo();
        if (info != null && info.getType() == ConnectivityManager.TYPE_MOBILE && info.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * 检查当前的网络状态
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null || !info.isAvailable() || !info.isConnected()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断数据是否全为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;

    }

    public static String formatMoney(double money) {
        return String.format("%.2f", money);
    }

    public static String formatDate(Date date, String daytype) {
        return new SimpleDateFormat(daytype).format(date);
    }

    public static Date parseDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static boolean checkTextView(TextView textView, String error) {
        if (textView == null)
            return false;

        textView.setError(null);
        if (TextUtils.isEmpty(textView.getText().toString())) {
            textView.setError(error);
            return false;
        }

        return true;
    }

    /**
     * 判断微信客户端是否安装
     *
     * @param context
     * @return
     */
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }

        return false;
    }


    public static boolean checkEditText(EditText editText, String error) {
        if (editText == null)
            return false;

        editText.setError(null);
        if (TextUtils.isEmpty(editText.getText().toString())) {
            editText.setError(error);
            return false;
        }

        return true;
    }

    public static BigDecimal str2BigDecimal(String arg) {
        if (TextUtils.isEmpty(arg)) {
            return BigDecimal.ZERO;
        }

        BigDecimal bigDecimal = null;
        try {
            bigDecimal = new BigDecimal(arg);
            bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
            return bigDecimal;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 保留小数点后两位，直接删除小数点后的多余位数
     *
     * @param price
     * @return
     */
    public static String showPrice(BigDecimal price) {
        return price.setScale(2, BigDecimal.ROUND_DOWN).toPlainString();
    }

    /**
     * 判断传入的服务名对应的服务是否在运行
     *
     * @param context
     * @return
     */
    public static boolean isRunningService(Context context, Class<? extends Service> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        boolean hasService = false;
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                hasService = true;
                break;
            }
        }
        return hasService;
    }

    /**
     * 检测通知监听服务是否被授权
     */
    public static boolean isNotificationListenerEnabled(Context context) {
        /* Android在4.3的版本中加入的NotificationListenerService*/
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return true;
        }
        Set<String> packageNames = NotificationManagerCompat.getEnabledListenerPackages(context);
        return packageNames.contains(context.getPackageName());
    }

    /**
     * json string convert to javaBean、map
     */
    public static <T> T json2obj(String jsonStr, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, clazz);
    }

    /**
     * 价格除100得到真实价格
     */
    public static String getReallyPrice(String price) {
        BigDecimal totalPrice = str2BigDecimal(price).multiply(str2BigDecimal(0.01 + ""));
        DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
        return decimalFormat.format(totalPrice.doubleValue());

    }

    public static String getReallyPrice(int price) {
        BigDecimal totalPrice = str2BigDecimal(price + "").multiply(str2BigDecimal(0.01 + ""));
        DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
        return decimalFormat.format(totalPrice.doubleValue());
    }

    public static String getReallyPrice(double price) {
        BigDecimal totalPrice = str2BigDecimal(price + "").multiply(str2BigDecimal(0.01 + ""));
        DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
        return decimalFormat.format(totalPrice.doubleValue());
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean hasLowPermission(Context context, String[]... permissions) {
        for (String[] permissionGroup : permissions) {
            for (String permission : permissionGroup) {
                if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }

            }
        }
        return true;
    }

    // 音频获取源
    public static int audioSource = MediaRecorder.AudioSource.MIC;
    // 设置音频采样率，44100是目前的标准，但是某些设备仍然支持22050，16000，11025
    public static int sampleRateInHz = 44100;
    // 设置音频的录制的声道CHANNEL_IN_STEREO为双声道，CHANNEL_CONFIGURATION_MONO为单声道
    public static int channelConfig = AudioFormat.CHANNEL_IN_STEREO;
    // 音频数据格式:PCM 16位每个样本。保证设备支持。PCM 8位每个样本。不一定能得到设备支持。
    public static int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
    // 缓冲区字节大小
    public static int bufferSizeInBytes = 0;

    /**
     * 判断是是否有录音权限
     */
    public static boolean isHasPermission(final Context context) {
        bufferSizeInBytes = 0;
        bufferSizeInBytes = AudioRecord.getMinBufferSize(sampleRateInHz,
                channelConfig, audioFormat);
        AudioRecord audioRecord = new AudioRecord(audioSource, sampleRateInHz,
                channelConfig, audioFormat, bufferSizeInBytes);
        //开始录制音频
        try {
            // 防止某些手机崩溃，例如联想
            audioRecord.startRecording();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        /**
         * 根据开始录音判断是否有录音权限
         */
        if (audioRecord.getRecordingState() != AudioRecord.RECORDSTATE_RECORDING) {
            return false;
        }
        audioRecord.stop();
        audioRecord.release();
        audioRecord = null;

        return true;
    }


    public static boolean grantedPermission(int[] grantResults) {
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    public static void setImageHint(EditText editText, final Context context, String hint, int img, final float size) {
        final Html.ImageGetter imageGetter = new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                Drawable drawable = null;
                int rId = Integer.parseInt(source);
                drawable = context.getResources().getDrawable(rId);
                drawable.setBounds(0, 0, (int) size, (int) size);
                return drawable;
            }
        };
        final String hintText = "<img src=\"" + img + "\" />" + hint;
        editText.setHint(Html.fromHtml(hintText, imageGetter, null));
    }


    /*判断是否需要展示在状态栏下 2016.4.28 by wp.nine
     * */
    public static boolean isBelowStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return true;
        }
        return false;
    }

    //地球半径 单位：千米
    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    //根据经纬度获取距离
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    /**
     * 打开播号盘
     */
    public static void openPhoneNumberDialog(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public static String formatDistance(String distance) {
        if (TextUtils.isEmpty(distance)) {
            return "0m";
        }
        double l = Double.parseDouble(distance);
        if (l >= 1000) {
            int i = (int) (l / 1000);
            int j = i * 1000;
            double v = l - j;
            if (v >= 100) {
                int v1 = (int) (v / 100);
                return i + "." + v1 + "km";
            } else {
                return i + "km";
            }
        } else {
            int i = (int) (l);
            return i + "m";
        }
    }

    /**
     * 获取外网ip
     *
     * @return
     */
    public static String getLifePayUrl() {
        URL infoUrl;
        InputStream inStream;
        try {
            infoUrl = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=myip");
            URLConnection connection = infoUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                StringBuilder strber = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    strber.append(line).append("\n");
                }
                inStream.close();
                line = strber.toString();
                IpBean ipBean = new Gson().fromJson(line, IpBean.class);
                return ipBean.data.ip;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取外网ip
     *
     * @return
     */
    public static String GetNetIp() {
        URL infoUrl;
        InputStream inStream;
        try {
            infoUrl = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=myip");
            URLConnection connection = infoUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                StringBuilder strber = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    strber.append(line).append("\n");
                }
                inStream.close();
                line = strber.toString();
                IpBean ipBean = new Gson().fromJson(line, IpBean.class);
                return ipBean.data.ip;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 脱敏
     *
     * @param tel
     * @return
     */
    public static String encryptTel(String tel) {
        StringBuilder sb = new StringBuilder(tel);
        sb.replace(3, 7, "****");
        return sb.toString();
    }

    public static String getString(Context context, int resId) {
        return context.getResources().getString(resId);
    }

    public static String getPlainString(String decimal) {
        if (TextUtils.isEmpty(decimal)) return "";
        BigDecimal a = new BigDecimal(decimal);
        return a.stripTrailingZeros().toPlainString();
    }

    public static String formateTime(Long time) {
        Date dateparam = new Date(time);

        Date currentTime = new Date(System.currentTimeMillis());

        switch (currentTime.getDate() - dateparam.getDate()) {
            case 0:
                int i = currentTime.getHours() - dateparam.getHours();
                if (i > 0) {
                    int i2 = currentTime.getMinutes() - dateparam.getMinutes();
                    if (i2 > 0)
                        return i + "小时前";
                    else if (i2 > -60)
                        return 60 + i2 + "分钟前";
                    else return "刚刚";
                } else {
                    int i1 = currentTime.getMinutes() - dateparam.getMinutes();
                    if (i1 > 0) {
                        return i1 + "分钟前";
                    } else
                        return "刚刚";
                }
            case 1:
                return "昨天" + formateStr(dateparam.getHours()) + ":" + formateStr(dateparam.getMinutes()) + ":" + formateStr(dateparam.getSeconds());
            case 2:
                return "前天" + formateStr(dateparam.getHours()) + ":" + formateStr(dateparam.getMinutes()) + ":" + formateStr(dateparam.getSeconds());
            default:
                return (currentTime.getTime() - dateparam.getTime()) / (3600 * 1000 * 24) + "天前";
        }
    }

    public static String formateStr(int str) {
        return new String().format("%02d", str);
    }

    public static String stampToDate(String s) {
        long lt = new Long(s);
        Date date1 = new Date();
        long time = date1.getTime();
        long todayTime = time - (time % (24 * 3600 * 1000));
        long yesterdayTime = todayTime - 24 * 3600 * 1000;
        String header = "";
        String res;
        SimpleDateFormat simpleDateFormat;
        if (lt % todayTime < 24 * 3600 * 1000) {
            header = "今天";
            lt = lt % 24 * 3600 * 1000;
            simpleDateFormat = new SimpleDateFormat(" HH:mm");
        } else if (lt % yesterdayTime < 24 * 3600 * 1000) {
            header = "昨天";
            lt = lt % 24 * 3600 * 1000;
            simpleDateFormat = new SimpleDateFormat(" HH:mm");
        } else {
            simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        }

        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return header + res;
    }

    public static String getDevice(Context context) {

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "";
        }
        @SuppressLint("MissingPermission") String imei = telephonyManager.getDeviceId();
        return imei;
    }

    /**
     * 计算地球上任意两点(经纬度)距离
     *
     * @return 返回距离 单位：米
     *//*
    public static double getDistance(double long1, double lat1, double long2, double lat2) {
        double a, b, R;
        R = 6378137; // 地球半径
        lat1 = lat1 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;
        a = lat1 - lat2;
        b = (long1 - long2) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2
                * R
                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
                * Math.cos(lat2) * sb2 * sb2));
        return d;
    }*/

    private static class IpBean {

        /**
         * code : 0
         * data : {"country":"中国","country_id":"CN","area":"华南","area_id":"800000","region":"广东省","region_id":"440000","city":"深圳市","city_id":"440300","county":"","county_id":"-1","isp":"电信","isp_id":"100017","ip":"119.136.34.18"}
         */

        public int code;
        /**
         * country : 中国
         * country_id : CN
         * area : 华南
         * area_id : 800000
         * region : 广东省
         * region_id : 440000
         * city : 深圳市
         * city_id : 440300
         * county :
         * county_id : -1
         * isp : 电信
         * isp_id : 100017
         * ip : 119.136.34.18
         */

        public DataEntity data;

        public static class DataEntity {
            public String country;
            public String country_id;
            public String area;
            public String area_id;
            public String region;
            public String region_id;
            public String city;
            public String city_id;
            public String county;
            public String county_id;
            public String isp;
            public String isp_id;
            public String ip;
        }
    }

    //验证是否为合法的手机号
    public static boolean isPhoneNumberValid(String phoneNumber) {
        String telRegex = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String planeRegex = "([0-9]{3,4}-)?[0-9]{7,8}";
        if (TextUtils.isEmpty(phoneNumber)) {
            return false;
        } else {
          /*  if (phoneNumber.matches(telRegex) || phoneNumber.matches(planeRegex)) {
                return true;
            } else {
                return false;
            }*/
            if (11 == phoneNumber.length() && phoneNumber.startsWith("1")) {
                return true;
            } else {
                return false;
            }
        }
    }

    //验证是否为合法的手机号
    public static boolean isPhoneValid(String phoneNumber) {
        String telRegex = "[1]\\d{10}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(phoneNumber)) return false;
        else return phoneNumber.matches(telRegex);
    }

    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @return
     */
    public static String bitmap2Base64(Bitmap bitmap) {
        if (bitmap == null) {
            return "";
        }
        String result = "";
        ByteArrayOutputStream out = null;
        try {
            // 先将bitmap转换为普通的字节数组
            out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, out);
            out.flush();
            out.close();
            result = Base64.encodeToString(out.toByteArray(), Base64.DEFAULT);
        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    //      bitmap.recycle();
                    out.close();
                } catch (Throwable e) {

                }
            }
        }
        return result;
    }


    public static String getMacAddress(Context context) {
        try {
            WifiManager wifi = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            return info.getMacAddress();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }


    /**
     * 将long类型  转化为 hh:mm:ss 格式
     *
     * @param second
     * @return
     */
    public static String second2TimeSecond(long second) {
        long hours = second / 3600;
        long minutes = (second % 3600) / 60;
        long seconds = second % 60;

        String hourString = "";
        String minuteString = "";
        String secondString = "";
        if (hours < 10) {
            hourString = "0";
            if (hours == 0) {
                hourString += "0";
            } else {
                hourString += String.valueOf(hours);
            }
        } else {
            hourString = String.valueOf(hours);
        }
        if (minutes < 10) {
            minuteString = "0";
            if (minutes == 0) {
                minuteString += "0";
            } else {
                minuteString += String.valueOf(minutes);
            }
        } else {
            minuteString = String.valueOf(minutes);
        }
        if (seconds < 10) {
            secondString = "0";
            if (seconds == 0) {
                secondString += "0";
            } else {
                secondString += String.valueOf(seconds);
            }
        } else {
            secondString = String.valueOf(seconds);
        }
        return hourString + ":" + minuteString + ":" + secondString;
    }

    public static final String SECRETKEY_DEFAULT = "0877E5F622298E46A3674C111F3806EB";
    public static final String IV_DEFAULT = "oeasydes";

    /**
     * 3DES解密算法
     * text为要解密的字符串
     */
    public static String DES3Decode(String secretKey, String iv, String text) {
        Key deskey;
        try {
            DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
            //            byte[] decryptData = cipher.doFinal(new BASE64Decoder().decodeBuffer(text));
            byte[] decryptData = cipher.doFinal(Base64.decode(text, Base64.DEFAULT));
            return new String(decryptData, "utf-8");
        } catch (Throwable e) {
            return text;
        }
    }

    private final static String secretKey = "0877E5F622298E46A3674C111F3806EB";
    // 向量 可有可无 终端后台也要约定
    private final static String iv = "oeasydes";
    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8";

    /**
     * 3DES加密
     *
     * @param plainText 普通文本
     * @return
     * @throws Exception
     */
    public static String encode(String plainText) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
        return new String(Base64.encode(encryptData, Base64.DEFAULT));
    }

    /**
     * 3DES解密雄迈账号密码
     */
    public static String DES3Decode(String text) {
        return DES3Decode(SECRETKEY_DEFAULT, IV_DEFAULT, text);
    }

    public static boolean isSupportBle(Context context) {
        if (context == null) return false;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return false;
        }
        // Use this check to determine whether BLE is supported on the device.  Then you can
        // selectively disable BLE-related features.
        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            return false;
        }

        final BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        if (bluetoothManager == null || bluetoothManager.getAdapter() == null) {
            return false;
        }
        return true;
    }

    public static String removeAroundDoubleQuotes(String string) {
        if (string == null) {
            return "";
        }
        int length = string.length();
        if ((length > 1) && (string.charAt(0) == '"')
                && (string.charAt(length - 1) == '"')) {
            return string.substring(1, length - 1);
        }
        return string;
    }

    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     *
     * @param context
     * @return true 表示开启
     */
    public static final boolean isOPenGPS(final Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }

        return false;
    }

    /**
     * 判断小区是否开通银联扫码支付功能 （产品要求在前端写死）
     *
     * @param unitId
     * @return
     */
    public static boolean isOpenUnionPayScan(String unitId) {
        boolean isOpen = false;
        String[] unitIdArray = {"3913", "3331", "3308", "2562", "2287", "2207", "2199", "2111",
                "2099", "2093", "2092", "2033", "2032", "2806", "2805", "2040", "2090", "52", "2730"}; // 开通的银联扫码的小区ID
        for (int i = 0; i < unitIdArray.length; i++) {
            if (unitId.equals(unitIdArray[i])) {
                isOpen = true;
                break;
            }
        }
        return isOpen;
    }

    // 保存图片到手机指定目录
    public static String savaBitmap(Context context, String imgName, byte[] bytes) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String filePath = null;
            FileOutputStream fos = null;
            try {
                filePath = Environment.getExternalStorageDirectory().getCanonicalPath() + "/MyImg";
                File imgDir = new File(filePath);
                if (!imgDir.exists()) {
                    imgDir.mkdirs();
                }
                imgName = filePath + "/" + imgName;
                fos = new FileOutputStream(imgName);
                fos.write(bytes);
//                Toast.makeText(context, "图片已保存到" + filePath, Toast.LENGTH_SHORT).show();
                return imgName;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(context, "请检查SD卡是否可用", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    public static String getCurFormatDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }

    public static long getLongTime(String timeStr) {
        long time = 1;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(timeStr);
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            time = timestamp.getTime() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 是否安装支付宝
     */
    public static boolean checkAliPayInstalled(Context context) {
        Uri uri = Uri.parse("alipays://platformapi/startApp");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        ComponentName componentName = intent.resolveActivity(context.getPackageManager());
        return componentName != null;
    }


    /**
     * 判断app是否处于后台
     *
     * @param context
     * @return
     */
    public static boolean isAppBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                /*
                BACKGROUND=400 EMPTY=500 FOREGROUND=100
                GONE=1000 PERCEPTIBLE=130 SERVICE=300 ISIBLE=200
                 */
                if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    //(x,y)是否在view的区域内
    public static boolean isTouchPointInView(View view, int x, int y) {
        if (view == null) {
            return false;
        }
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();
        //view.isClickable() &&
        if (y >= top && y <= bottom && x >= left
                && x <= right) {
            return true;
        }
        return false;
    }

    public static boolean isPhone(String phone, Context context) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            showMsg(context, context.getString(R.string.toast_phone_error));
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (!isMatch) {
                showMsg(context, context.getString(R.string.toast_phone_error));
            }
            return isMatch;
        }
    }
    /**
     * 隐藏软键盘
     * @param context :上下文
     * @param view    :一般为EditText
     */
    public static void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    /**
     * 判断 用户是否安装QQ客户端
     */
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equalsIgnoreCase("com.tencent.qqlite") || pn.equalsIgnoreCase("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断 Uri是否有效
     */
    public static boolean isValidIntent(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        return !activities.isEmpty();
    }

    public static void saveToSystemGallery(final Context context, Bitmap bmp) {
        try {
            // 首先保存图片
            File appDir = new File(Environment.getExternalStorageDirectory(), "vgmap");
            if (!appDir.exists()) {
                appDir.mkdirs();
            }
            String fileName = System.currentTimeMillis() + ".jpg";
            File file = new File(appDir, fileName);
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            // 其次把文件插入到系统图库
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);

            // 通知图库更新
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                MediaScannerConnection.scanFile(context, new String[]{file.getAbsolutePath()}, null,
                        new MediaScannerConnection.OnScanCompletedListener() {
                            public void onScanCompleted(String path, Uri uri) {
                                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                                mediaScanIntent.setData(uri);
                                context.sendBroadcast(mediaScanIntent);
                            }
                        });
            } else {
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.fromFile(file.getAbsoluteFile())));
            }
            showMsg(context, context.getString(R.string.save_succeed));
        } catch (IOException e) {
            e.printStackTrace();
            showMsg(context, context.getString(R.string.save_error));
        }
    }

}

