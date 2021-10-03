package com.pushwoosh.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import com.pushwoosh.internal.utils.JsonUtils;
import com.pushwoosh.internal.utils.PWLog;
import java.util.ArrayList;
import java.util.List;

public class PushBundleStorageImpl extends SQLiteOpenHelper implements r {
    private static final String a = "PushBundleStorageImpl";
    private final Object b = new Object();

    private static class Column {
        static final String PUSH_BUNDLE_JSON = "push_bundle_json";
        static final String ROW_ID = "rowid";

        private Column() {
        }
    }

    public PushBundleStorageImpl(Context context) {
        super(context, "pushBundleDb.db", (SQLiteDatabase.CursorFactory) null, 3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002b, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0030, code lost:
        if (r1 != null) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0032, code lost:
        if (r7 != null) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0038, code lost:
        r1.close();
     */
    private long a(Bundle bundle, String str) throws Exception {
        long insertWithOnConflict;
        synchronized (this.b) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                insertWithOnConflict = writableDatabase.insertWithOnConflict(str, null, c(bundle), 5);
                if (insertWithOnConflict == -1) {
                    PWLog.warn(a, "Push bundle with message was not stored.");
                    throw new Exception();
                } else if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Error occurred while storing push bundle", e);
                throw e;
            }
        }
        return insertWithOnConflict;
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0064, code lost:
        r3 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0065, code lost:
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0069, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006a, code lost:
        r4 = r3;
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0079, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x007e, code lost:
        if (r1 != null) goto L_0x0080;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0080, code lost:
        if (r9 != null) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0086, code lost:
        r1.close();
     */
    private Bundle a(long j, String str) throws Exception {
        Cursor rawQuery;
        Throwable th;
        Throwable th2;
        Bundle a2;
        synchronized (this.b) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                rawQuery = writableDatabase.rawQuery("Select * from " + str + " where " + "rowid" + "='" + j + "';", null);
                if (rawQuery.moveToFirst()) {
                    a2 = a(rawQuery);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    if (writableDatabase != null) {
                        writableDatabase.close();
                    }
                } else {
                    PWLog.error("Can't get push bundle with id: " + j);
                    throw new Exception();
                }
            } catch (Exception e) {
                PWLog.error("Can't get push bundle with id: " + j, e);
                throw e;
            }
        }
        return a2;
        throw th2;
        throw th;
        if (rawQuery != null) {
            if (th != null) {
                try {
                    rawQuery.close();
                } catch (Throwable unused) {
                }
            } else {
                rawQuery.close();
            }
        }
        throw th2;
    }

    private Bundle a(Cursor cursor) {
        return JsonUtils.jsonStringToBundle(cursor.getString(cursor.getColumnIndex("push_bundle_json")));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003c, code lost:
        r3 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003d, code lost:
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0041, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0042, code lost:
        r4 = r3;
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0051, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0056, code lost:
        if (r1 != null) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0058, code lost:
        if (r7 != null) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x005e, code lost:
        r1.close();
     */
    private List<Bundle> a(String str) {
        Cursor rawQuery;
        Throwable th;
        Throwable th2;
        ArrayList arrayList;
        synchronized (this.b) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                rawQuery = writableDatabase.rawQuery("Select * from " + str, null);
                arrayList = new ArrayList();
                while (rawQuery.moveToNext()) {
                    arrayList.add(a(rawQuery));
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't get group push bundles", e);
                throw e;
            }
        }
        return arrayList;
        throw th2;
        throw th;
        if (rawQuery != null) {
            if (th != null) {
                try {
                    rawQuery.close();
                } catch (Throwable unused) {
                }
            } else {
                rawQuery.close();
            }
        }
        throw th2;
    }

    private void a(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL(String.format("create table %s (", str) + c() + ");");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003c, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0041, code lost:
        if (r1 != null) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0043, code lost:
        if (r6 != null) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0049, code lost:
        r1.close();
     */
    private void b(long j, String str) {
        synchronized (this.b) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase.delete(str, "rowid=" + j, null) <= 0) {
                String str2 = a;
                PWLog.noise(str2, "failed to remove push bundle with id: " + j);
            }
            if (writableDatabase != null) {
                writableDatabase.close();
            }
        }
        return;
        throw th;
    }

    private void b(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
        a(sQLiteDatabase, str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0021, code lost:
        if (r1 != null) goto L_0x0023;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0023, code lost:
        if (r5 != null) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0029, code lost:
        r1.close();
     */
    private void b(String str) {
        synchronized (this.b) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase.delete(str, null, null) <= 0) {
                PWLog.noise(a, "failed to remove group push bundles");
            }
            if (writableDatabase != null) {
                writableDatabase.close();
            }
        }
        return;
        throw th;
    }

    private ContentValues c(Bundle bundle) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("push_bundle_json", JsonUtils.bundleToJson(bundle).toString());
        return contentValues;
    }

    private String c() {
        return String.format("%s TEXT ", "push_bundle_json");
    }

    @Override // com.pushwoosh.repository.r
    public long a(Bundle bundle) throws Exception {
        return a(bundle, "pushBundles");
    }

    @Override // com.pushwoosh.repository.r
    public Bundle a(long j) throws Exception {
        return a(j, "pushBundles");
    }

    @Override // com.pushwoosh.repository.r
    public List<Bundle> a() {
        return a("groupPushBundles");
    }

    @Override // com.pushwoosh.repository.r
    public long b(Bundle bundle) throws Exception {
        return a(bundle, "groupPushBundles");
    }

    @Override // com.pushwoosh.repository.r
    public void b() {
        b("groupPushBundles");
    }

    @Override // com.pushwoosh.repository.r
    public void b(long j) {
        b(j, "pushBundles");
    }

    @Override // com.pushwoosh.repository.r
    public void c(long j) {
        b(j, "groupPushBundles");
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase, "pushBundles");
        a(sQLiteDatabase, "groupPushBundles");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        b(sQLiteDatabase, "pushBundles");
        b(sQLiteDatabase, "groupPushBundles");
    }
}
