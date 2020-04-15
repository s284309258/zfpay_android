package com.lython.network.tools.converter;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lckj.jycm.utils.Base64;
import com.lckj.jycm.utils.JsonUtil;
import com.lckj.jycm.utils.RSAUtil;
import com.lckj.jycm.zfqg_network.RSARequest;
import com.lcwl.base.NetworkApplication;
import com.lython.network.BuildConfig;
import com.lython.network.R;
import com.lython.network.lib.helper.SecurityUtils;
import com.lython.network.model.Const;
import com.lython.network.tools.interceptor.MD5Util;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

public class CustomRequestConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");


    private final Gson gson;
    private final Type type;

    CustomRequestConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        try {
            HashMap<String, Object> paramMap = sign(value, Const.SHOP_SIGN);
            if ("debug".equals(BuildConfig.BUILD_TYPE)) {
                printParams(paramMap);
            }

            String json = JsonUtil.map2json(paramMap);
            if ("debug".equals(BuildConfig.BUILD_TYPE)) {
                System.out.println("加密的JSON:" + json);
            }
            if (value instanceof RSARequest) {
                byte[] passStr = RSAUtil.encryptByPublicKey(json.getBytes(), RSAUtil.publicKey);
                String data = Base64.encode(passStr);
                HashMap<String, String> map = new HashMap<>();
                map.put("data", data);
                String dataJson = JsonUtil.map2json(map);
                if ("debug".equals(BuildConfig.BUILD_TYPE)) {
                    System.out.println("加密，提交的参数:" + dataJson);
                }
                writer.write(dataJson);
                writer.flush();
            } else {
                gson.toJson(paramMap, HashMap.class, writer);
                writer.flush();
            }
        } catch (Exception e) {
            throw new AssertionError(e); // Writing to Buffer does no I/O.
        }
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
    }

    private void printParams(HashMap<String, Object> paramMap) {

        StringBuilder sb = new StringBuilder();
        for (String key : paramMap.keySet()) {
            sb.append(key).append(":").append(paramMap.get(key));
        }

        Log.d("request", "printParams: " + sb.toString());
    }

    public static HashMap<String, Object> sign(Object obj, int keyType) throws Exception {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        Class clazz = obj.getClass();
        List<Class> clazzs = new ArrayList<Class>();
        do {
            clazzs.add(clazz);
            clazz = clazz.getSuperclass();
        } while (!clazz.equals(Object.class));

        for (Class iClazz : clazzs) {
            Field[] fields = iClazz.getDeclaredFields();
            for (Field field : fields) {
                Object objVal = null;
                field.setAccessible(true);
                if (field.getName().equals("login_password")
                        || field.getName().equals("pass1")/*注册*/
                        || field.getName().equals("pass2")/*注册*/
                        || field.getName().equals("pass")/*登录*/
                        || field.getName().equals("passWord")/*转账、兑换密码*/
                        || field.getName().equals("secondPass")/*修改的登录、支付密码，忘记登录密码*/
                        || field.getName().equals("newPass"))/*修改的登录、支付密码，忘记登录密码*/ {
                    String pwd = (String) field.get(obj);
                    if (!TextUtils.isEmpty(pwd)) {
                        if (pwd.length() < 6 || pwd.length() > 16) {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(NetworkApplication.getInstance().getApplicationContext(), NetworkApplication.getInstance().getApplicationContext().getString(R.string.pwd_err_1), Toast.LENGTH_SHORT).show();
                                }
                            });
                            throw new Exception("passwordCheckErr");
                        }
                        String regex = "^(?![a-zA-z]+$)(?!\\d+$)(?![!@#$%^&*]+$)[a-zA-Z\\d!@#$%^&*]+$";
                        Pattern p = Pattern.compile(regex);
                        Matcher m = p.matcher(pwd);
                        boolean isMatch = m.matches();
                        if (!isMatch) {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(NetworkApplication.getInstance().getApplicationContext(), NetworkApplication.getInstance().getApplicationContext().getString(R.string.pwd_err_1), Toast.LENGTH_SHORT).show();
                                }
                            });
                            throw new Exception("passwordCheckErr");
                        }
                        field.set(obj, MD5Util.getMD5ofStr(pwd, false));
                    }

                } else if (field.getName().equals("pay_password")) {
                    String pwd = (String) field.get(obj);
                    if (!TextUtils.isEmpty(pwd)) {
                        if (pwd.length() != 6) {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(NetworkApplication.getInstance().getApplicationContext(), NetworkApplication.getInstance().getApplicationContext().getString(R.string.pwd_err_2), Toast.LENGTH_SHORT).show();
                                }
                            });
                            throw new Exception("passwordCheckErr");
                        }
                        field.set(obj, MD5Util.getMD5ofStr(pwd, false));
                    }
                }
                if (field.getName().equals("SignKeyType")) {      //wp.nine 通过该字段来修改签名的KEY值
                    keyType = (int) field.get(obj);
                } else if (field.getName().equals("serialVersionUID")) {
                    continue;
                } else {
                    objVal = field.get(obj);
                    if (objVal != null)//如果为null值 则不计入 md5摘要 ，因为 http传输层 不会把null值送出去 modified by janyo at 2015-5-7
                        hashMap.put(field.getName(), objVal);
                }
            }
        }
        String sign = SecurityUtils.signParams(hashMap);
        hashMap.put(Const.SignField, sign);
        return hashMap;
    }
}