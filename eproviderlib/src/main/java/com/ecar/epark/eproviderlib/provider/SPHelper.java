package com.ecar.epark.eproviderlib.provider;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

import com.ecar.pushlib.util.EpushUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.ecar.pushlib.provider.ConstantUtil.CONTENT_URI;
import static com.ecar.pushlib.provider.ConstantUtil.CURSOR_COLUMN_NAME;
import static com.ecar.pushlib.provider.ConstantUtil.CURSOR_COLUMN_TYPE;
import static com.ecar.pushlib.provider.ConstantUtil.CURSOR_COLUMN_VALUE;
import static com.ecar.pushlib.provider.ConstantUtil.NULL_STRING;
import static com.ecar.pushlib.provider.ConstantUtil.SEPARATOR;
import static com.ecar.pushlib.provider.ConstantUtil.TYPE_BOOLEAN;
import static com.ecar.pushlib.provider.ConstantUtil.TYPE_CLEAN;
import static com.ecar.pushlib.provider.ConstantUtil.TYPE_CONTAIN;
import static com.ecar.pushlib.provider.ConstantUtil.TYPE_FLOAT;
import static com.ecar.pushlib.provider.ConstantUtil.TYPE_GET_ALL;
import static com.ecar.pushlib.provider.ConstantUtil.TYPE_INT;
import static com.ecar.pushlib.provider.ConstantUtil.TYPE_LONG;
import static com.ecar.pushlib.provider.ConstantUtil.TYPE_STRING;
import static com.ecar.pushlib.provider.ConstantUtil.TYPE_STRING_SET;
import static com.ecar.pushlib.provider.ConstantUtil.VALUE;

public class SPHelper {
    public static final String COMMA_REPLACEMENT = "__COMMA__";

    public Context context;
    private String uri;

    public SPHelper() {

    }

    private static SPHelper spHelper;

    public static SPHelper getInstance() {
        synchronized (SPHelper.class) {
            if (spHelper == null) {
                synchronized (SPHelper.class) {
                    spHelper = new SPHelper();
                }
            }
        }
        return spHelper;
    }

    public boolean checkContext() {
        if (context == null) {
//            throw new IllegalStateException("context has not been initialed");
            EpushUtil.loge("qob", "SPHelper context is null");
            return false;
        }
        return true;
    }

    public void init(Context application) {
        context = application.getApplicationContext();
        getAuthority();
    }

    public synchronized void save(String name, Boolean t) {
        if (checkContext()) {
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(getAuthority() + SEPARATOR + TYPE_BOOLEAN + SEPARATOR + name);
            ContentValues cv = new ContentValues();
            cv.put(VALUE, t);
            cr.update(uri, cv, null, null);
        }
    }

    private String getAuthority() {
        if (checkContext()) {
            if (!TextUtils.isEmpty(uri)) {
                return uri;
            }
            uri = CONTENT_URI + context.getPackageName() + ".epush.provider";
            return uri;
        }
        return "";
    }

    public synchronized void save(String name, String t) {
        if (checkContext()) {
            ContentResolver cr = context.getContentResolver();
            if (cr == null) {
                return;
            }
            Uri uri = Uri.parse(getAuthority() + SEPARATOR + TYPE_STRING + SEPARATOR + name);
            ContentValues cv = new ContentValues();
            cv.put(VALUE, t);
            cr.update(uri, cv, null, null);
        }

    }

    public synchronized void save(String name, Integer t) {
        if (checkContext()) {
            ContentResolver cr = context.getContentResolver();
            if (cr == null) {
                return;
            }
            Uri uri = Uri.parse(getAuthority() + SEPARATOR + TYPE_INT + SEPARATOR + name);
            ContentValues cv = new ContentValues();
            cv.put(VALUE, t);
            cr.update(uri, cv, null, null);
        }

    }

    public synchronized void save(String name, Long t) {
        if (checkContext()) {
            ContentResolver cr = context.getContentResolver();
            if (cr == null) {
                return;
            }
            Uri uri = Uri.parse(getAuthority() + SEPARATOR + TYPE_LONG + SEPARATOR + name);
            ContentValues cv = new ContentValues();
            cv.put(VALUE, t);
            cr.update(uri, cv, null, null);
        }

    }

    public synchronized void save(String name, Float t) {
        if (checkContext()) {
            ContentResolver cr = context.getContentResolver();
            if (cr == null) {
                return;
            }
            Uri uri = Uri.parse(getAuthority() + SEPARATOR + TYPE_BOOLEAN + SEPARATOR + name);
            ContentValues cv = new ContentValues();
            cv.put(VALUE, t);
            cr.update(uri, cv, null, null);
        }

    }


    public synchronized void save(String name, Set<String> t) {
        if (checkContext()) {
            ContentResolver cr = context.getContentResolver();
            if (cr == null) {
                return;
            }
            Uri uri = Uri.parse(getAuthority() + SEPARATOR + TYPE_STRING_SET + SEPARATOR + name);
            ContentValues cv = new ContentValues();
            Set<String> convert = new HashSet<>();
            for (String string : t) {
                convert.add(string.replace(",", COMMA_REPLACEMENT));
            }
            cv.put(VALUE, convert.toString());
            cr.update(uri, cv, null, null);
        }

    }

    public String getString(String name, String defaultValue) {
        String rtn = "" + defaultValue;
        if (checkContext()) {
            ContentResolver cr = context.getContentResolver();
            if (cr == null) {
                return rtn;
            }
            Uri uri = Uri.parse(getAuthority() + SEPARATOR + TYPE_STRING + SEPARATOR + name);
            rtn = cr.getType(uri);
            if (rtn == null || rtn.equals(NULL_STRING)) {
                return defaultValue;
            }
        }
        return rtn;
    }

    public int getInt(String name, int defaultValue) {
        String rtn = "" + defaultValue;
        if (checkContext()) {
            ContentResolver cr = context.getContentResolver();
            if (cr == null) {
                return defaultValue;
            }
            Uri uri = Uri.parse(getAuthority() + SEPARATOR + TYPE_INT + SEPARATOR + name);
            rtn = cr.getType(uri);
            if (rtn == null || rtn.equals(NULL_STRING)) {
                return defaultValue;
            }
        }
        return Integer.parseInt(rtn);
    }

    public float getFloat(String name, float defaultValue) {
        String rtn = "" + defaultValue;
        if (checkContext()) {
            ContentResolver cr = context.getContentResolver();
            if (cr == null) {
                return defaultValue;
            }
            Uri uri = Uri.parse(getAuthority() + SEPARATOR + TYPE_FLOAT + SEPARATOR + name);
            rtn = cr.getType(uri);
            if (rtn == null || rtn.equals(NULL_STRING)) {
                return defaultValue;
            }
        }
        return Float.parseFloat(rtn);
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        String rtn = "" + defaultValue;
        if (checkContext()) {
            ContentResolver cr = context.getContentResolver();
            if (cr == null) {
                return defaultValue;
            }
            Uri uri = Uri.parse(getAuthority() + SEPARATOR + TYPE_BOOLEAN + SEPARATOR + name);
            rtn = cr.getType(uri);
            if (rtn == null || rtn.equals(NULL_STRING)) {
                return defaultValue;
            }
        }

        return Boolean.parseBoolean(rtn);
    }

    public long getLong(String name, long defaultValue) {
        String rtn = "" + defaultValue;
        if (checkContext()) {
            ContentResolver cr = context.getContentResolver();
            if (cr == null) {
                return defaultValue;
            }
            Uri uri = Uri.parse(getAuthority() + SEPARATOR + TYPE_LONG + SEPARATOR + name);
            rtn = cr.getType(uri);
            if (rtn == null || rtn.equals(NULL_STRING)) {
                return defaultValue;
            }
        }
        return Long.parseLong(rtn);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Set<String> getStringSet(String name, Set<String> defaultValue) {
        Set<String> returns = defaultValue;
        if (checkContext()) {
            ContentResolver cr = context.getContentResolver();
            if (cr == null) {
                return defaultValue;
            }
            Uri uri = Uri.parse(getAuthority() + SEPARATOR + TYPE_STRING_SET + SEPARATOR + name);
            String rtn = cr.getType(uri);
            if (rtn == null || rtn.equals(NULL_STRING)) {
                return defaultValue;
            }
            if (!rtn.matches("\\[.*\\]")) {
                return defaultValue;
            }
            String sub = rtn.substring(1, rtn.length() - 1);
            String[] spl = sub.split(", ");
            returns = new HashSet<>();
            for (String t : spl) {
                returns.add(t.replace(COMMA_REPLACEMENT, ", "));
            }
        }

        return returns;
    }

    public boolean contains(String name) {
        String rtn = "" + false;
        if (checkContext()) {
            ContentResolver cr = context.getContentResolver();
            if (cr == null) {
                return false;
            }
            Uri uri = Uri.parse(getAuthority() + SEPARATOR + TYPE_CONTAIN + SEPARATOR + name);
            rtn = cr.getType(uri);
            if (rtn == null || rtn.equals(NULL_STRING)) {
                return false;
            } else {
                return Boolean.parseBoolean(rtn);
            }
        }
        return Boolean.parseBoolean(rtn);
    }

    public void remove(String name) {
        if (checkContext()) {
            ContentResolver cr = context.getContentResolver();
            if (cr == null) {
                return;
            }
            Uri uri = Uri.parse(getAuthority() + SEPARATOR + TYPE_LONG + SEPARATOR + name);
            cr.delete(uri, null, null);
        }

    }

    public void clear() {
        if (checkContext()) {
            ContentResolver cr = context.getContentResolver();
            if (cr == null) {
                return;
            }
            Uri uri = Uri.parse(getAuthority() + SEPARATOR + TYPE_CLEAN);
            cr.delete(uri, null, null);
        }
    }

    public Map<String, ?> getAll() {
        HashMap resultMap = new HashMap();
        if (checkContext()) {
            ContentResolver cr = context.getContentResolver();
            if (cr == null) {
                return resultMap;
            }
            Uri uri = Uri.parse(getAuthority() + SEPARATOR + TYPE_GET_ALL);
            Cursor cursor = cr.query(uri, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex(CURSOR_COLUMN_NAME);
                int typeIndex = cursor.getColumnIndex(CURSOR_COLUMN_TYPE);
                int valueIndex = cursor.getColumnIndex(CURSOR_COLUMN_VALUE);
                do {
                    String key = cursor.getString(nameIndex);
                    String type = cursor.getString(typeIndex);
                    Object value = null;
                    if (type.equalsIgnoreCase(TYPE_STRING)) {
                        value = cursor.getString(valueIndex);
                        if (((String) value).contains(COMMA_REPLACEMENT)) {
                            String str = (String) value;
                            if (str.matches("\\[.*\\]")) {
                                String sub = str.substring(1, str.length() - 1);
                                String[] spl = sub.split(", ");
                                Set<String> returns = new HashSet<>();
                                for (String t : spl) {
                                    returns.add(t.replace(COMMA_REPLACEMENT, ", "));
                                }
                                value = returns;
                            }
                        }
                    } else if (type.equalsIgnoreCase(TYPE_BOOLEAN)) {
                        value = cursor.getString(valueIndex);
                    } else if (type.equalsIgnoreCase(TYPE_INT)) {
                        value = cursor.getInt(valueIndex);
                    } else if (type.equalsIgnoreCase(TYPE_LONG)) {
                        value = cursor.getLong(valueIndex);
                    } else if (type.equalsIgnoreCase(TYPE_FLOAT)) {
                        value = cursor.getFloat(valueIndex);
                    } else if (type.equalsIgnoreCase(TYPE_STRING_SET)) {
                        value = cursor.getString(valueIndex);
                    }
                    resultMap.put(key, value);
                }
                while (cursor.moveToNext());
                cursor.close();
            }
        }
        return resultMap;
    }

}