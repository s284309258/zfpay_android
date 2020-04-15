package com.zaaach.citypicker.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.zaaach.citypicker.model.City;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.zaaach.citypicker.db.DBConfig.COLUMN_CITY_CODE;
import static com.zaaach.citypicker.db.DBConfig.COLUMN_LAT;
import static com.zaaach.citypicker.db.DBConfig.COLUMN_LEVEL_TYPE;
import static com.zaaach.citypicker.db.DBConfig.COLUMN_LNG;
import static com.zaaach.citypicker.db.DBConfig.COLUMN_MERGER_NAME;
import static com.zaaach.citypicker.db.DBConfig.COLUMN_NAME;
import static com.zaaach.citypicker.db.DBConfig.COLUMN_PARENT_ID;
import static com.zaaach.citypicker.db.DBConfig.COLUMN_PINYIN;
import static com.zaaach.citypicker.db.DBConfig.COLUMN_ID;
import static com.zaaach.citypicker.db.DBConfig.DB_NAME_V2;
import static com.zaaach.citypicker.db.DBConfig.LATEST_DB_NAME;
import static com.zaaach.citypicker.db.DBConfig.DB_NAME_V1;
import static com.zaaach.citypicker.db.DBConfig.TABLE_NAME;

/**
 * Author Bro0cL on 2016/1/26.
 */
public class DBManager {
    private static final int BUFFER_SIZE = 1024;

    private String DB_PATH;
    private Context mContext;

    public DBManager(Context context) {
        this.mContext = context;
        DB_PATH = File.separator + "data"
                + Environment.getDataDirectory().getAbsolutePath() + File.separator
                + context.getPackageName() + File.separator + "databases" + File.separator;
        copyDBFile();
    }

    private void copyDBFile(){
        File dir = new File(DB_PATH);
        if (!dir.exists()){
            dir.mkdirs();
        }
        //如果旧版数据库存在，则删除
        File dbV1 = new File(DB_PATH + DB_NAME_V1);
        if (dbV1.exists()){
            dbV1.delete();
        }
        //如果旧版数据库存在，则删除
        File dbV2 = new File(DB_PATH + DB_NAME_V2);
        if (dbV2.exists()){
            dbV2.delete();
        }
        //创建新版本数据库
        File dbFile = new File(DB_PATH + LATEST_DB_NAME);
        if (!dbFile.exists()){
            InputStream is;
            OutputStream os;
            try {
                is = mContext.getResources().getAssets().open(LATEST_DB_NAME);
                os = new FileOutputStream(dbFile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int length;
                while ((length = is.read(buffer, 0, buffer.length)) > 0){
                    os.write(buffer, 0, length);
                }
                os.flush();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<City> getAllCities(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + LATEST_DB_NAME, null);
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where  "+COLUMN_LEVEL_TYPE+"= ?", new String[]{"2"});
        List<City> result = new ArrayList<>();
        City city;
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String parentId = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
            String pinyin = cursor.getString(cursor.getColumnIndex(COLUMN_PINYIN));
            String code = cursor.getString(cursor.getColumnIndex(COLUMN_CITY_CODE));
            String lng = cursor.getString(cursor.getColumnIndex(COLUMN_LNG));
            String lat = cursor.getString(cursor.getColumnIndex(COLUMN_LAT));
            city = new City(name, parentId, pinyin, code,lng,lat);
            result.add(city);
        }
        cursor.close();
        db.close();
        Collections.sort(result, new CityComparator());
        return result;
    }

    public ArrayList<City> getAreas(String id){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + LATEST_DB_NAME, null);
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where  "+COLUMN_PARENT_ID+"= ?", new String[]{id});
        ArrayList<City> result = new ArrayList<>();
        City city;
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String lng = cursor.getString(cursor.getColumnIndex(COLUMN_LNG));
            String lat = cursor.getString(cursor.getColumnIndex(COLUMN_LAT));
            city = new City(name,lng,lat);
            result.add(city);
        }
        cursor.close();
        db.close();
        return result;
    }
    public ArrayList<City> getAreasByName(String name){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + LATEST_DB_NAME, null);
        String sql = "select * from " + TABLE_NAME + " where  "+COLUMN_MERGER_NAME+" like ? "+" and "+COLUMN_LEVEL_TYPE+"= ?";
        Cursor cursor = db.rawQuery(sql, new String[]{"%"+name+"%","3"});
        ArrayList<City> result = new ArrayList<>();
        City city;
        while (cursor.moveToNext()){
            String name1 = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String lng = cursor.getString(cursor.getColumnIndex(COLUMN_LNG));
            String lat = cursor.getString(cursor.getColumnIndex(COLUMN_LAT));
            city = new City(name1,lng,lat);
            result.add(city);
        }
        cursor.close();
        db.close();
        return result;
    }


    public List<City> searchCity(final String keyword){
        String sql = "select * from " + TABLE_NAME + " where "
                + COLUMN_NAME + " like ? " + "or "
                + COLUMN_PINYIN + " like ? "+" and "+COLUMN_LEVEL_TYPE+"= ?";
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + LATEST_DB_NAME, null);
        Cursor cursor = db.rawQuery(sql, new String[]{"%"+keyword+"%", keyword+"%","2"});

        List<City> result = new ArrayList<>();
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String id = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
            String pinyin = cursor.getString(cursor.getColumnIndex(COLUMN_PINYIN));
            String code = cursor.getString(cursor.getColumnIndex(COLUMN_CITY_CODE));
            String lng = cursor.getString(cursor.getColumnIndex(COLUMN_LNG));
            String lat = cursor.getString(cursor.getColumnIndex(COLUMN_LAT));
            City city = new City(name, id, pinyin, code, lng, lat);
            result.add(city);
        }
        cursor.close();
        db.close();
        CityComparator comparator = new CityComparator();
        Collections.sort(result, comparator);
        return result;
    }

    /**
     * sort by a-z
     */
    private class CityComparator implements Comparator<City>{
        @Override
        public int compare(City lhs, City rhs) {
            String a = lhs.getPinyin().substring(0, 1);
            String b = rhs.getPinyin().substring(0, 1);
            return a.compareTo(b);
        }
    }
}
