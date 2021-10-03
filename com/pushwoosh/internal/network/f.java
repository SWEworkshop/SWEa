package com.pushwoosh.internal.network;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.NonNull;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.k;
import org.json.JSONException;
import org.json.JSONObject;

public class f extends SQLiteOpenHelper {
    private static final String a = "f";
    private final Object b = new Object();
    private k c;

    public f(Context context, k kVar) {
        super(context, "request.db", (SQLiteDatabase.CursorFactory) null, 1);
        this.c = kVar;
    }

    private a a(Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndex("requestId"));
        String string2 = cursor.getString(cursor.getColumnIndex(FirebaseAnalytics.Param.METHOD));
        String string3 = cursor.getString(cursor.getColumnIndex("body"));
        JSONObject jSONObject = new JSONObject();
        if (string3 != null) {
            try {
                jSONObject = new JSONObject(string3);
            } catch (JSONException e) {
                PWLog.error("Can't parse body of request: ", e);
            }
        }
        return new a(string, string2, jSONObject);
    }

    @NonNull
    private ContentValues b(PushRequest<?> pushRequest) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("requestId", this.c.a());
        contentValues.put(FirebaseAnalytics.Param.METHOD, pushRequest.getMethod());
        try {
            contentValues.put("body", pushRequest.b().toString());
        } catch (InterruptedException | JSONException e) {
            PWLog.error(a, "not valid body request:", e);
        }
        return contentValues;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0019, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001e, code lost:
        if (r1 != null) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0020, code lost:
        if (r5 != null) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0026, code lost:
        r1.close();
     */
    public long a(PushRequest<?> pushRequest) {
        long insert;
        synchronized (this.b) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                insert = writableDatabase.insert("REQUEST", null, b(pushRequest));
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error(a, "error add request", e);
                return -1;
            }
        }
        return insert;
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0043, code lost:
        r8 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0044, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0048, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0049, code lost:
        r3 = r8;
        r8 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0058, code lost:
        r7 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0059, code lost:
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x005d, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x005e, code lost:
        r8 = r7;
        r7 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0063, code lost:
        if (r8 != null) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0069, code lost:
        r2.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0058 A[ExcHandler: all (th java.lang.Throwable)] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0063  */
    public a a(long j) {
        SQLiteDatabase writableDatabase;
        Throwable th;
        Cursor rawQuery;
        Throwable th2;
        Throwable th3;
        synchronized (this.b) {
            try {
                writableDatabase = getWritableDatabase();
                rawQuery = writableDatabase.rawQuery("Select * from REQUEST where rowid='" + j + "';", null);
                if (rawQuery.moveToFirst()) {
                    a a2 = a(rawQuery);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    if (writableDatabase != null) {
                        writableDatabase.close();
                    }
                    return a2;
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
                return null;
            } catch (Exception e) {
                PWLog.error(a, "Can't get cached request: ", e);
            }
        }
        if (writableDatabase != null) {
        }
        throw th;
        throw th2;
        throw th;
        if (rawQuery != null) {
            if (th3 != null) {
                try {
                    rawQuery.close();
                } catch (Throwable th4) {
                }
            } else {
                rawQuery.close();
            }
        }
        throw th2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0014, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0018, code lost:
        if (r1 != null) goto L_0x001a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001a, code lost:
        if (r2 != null) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0020, code lost:
        r1.close();
     */
    public void a() {
        synchronized (this.b) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            writableDatabase.execSQL("delete from REQUEST");
            if (writableDatabase != null) {
                writableDatabase.close();
            }
        }
        return;
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001b, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001f, code lost:
        if (r3 != null) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0021, code lost:
        if (r4 != null) goto L_0x0023;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0027, code lost:
        r3.close();
     */
    public void a(String str) {
        synchronized (this.b) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                writableDatabase.delete("REQUEST", "requestId=?", new String[]{str});
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error(a, String.format("Can't remove cached request by key %s: ", str), e);
            }
        }
        return;
        throw th;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format("create table %s (%s TEXT primary key, %s TEXT, %s TEXT)", "REQUEST", "requestId", FirebaseAnalytics.Param.METHOD, "body"));
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
