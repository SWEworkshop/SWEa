package com.pushwoosh.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.NonNull;
import com.pushwoosh.internal.utils.JsonUtils;
import com.pushwoosh.internal.utils.PWLog;
import java.util.HashSet;
import java.util.Set;

public class c extends SQLiteOpenHelper {
    private static final String a = b.class.getSimpleName();
    private final Object b = new Object();

    public interface a {
        void a(b bVar);
    }

    public c(Context context) {
        super(context, "localNotification.db", (SQLiteDatabase.CursorFactory) null, 2);
    }

    @NonNull
    private b a(Cursor cursor) {
        return new b(cursor.getInt(cursor.getColumnIndex("requestId")), cursor.getInt(cursor.getColumnIndex("notificationId")), cursor.getString(cursor.getColumnIndex("notificationTag")), cursor.getLong(cursor.getColumnIndex("triggerAtMilles")), JsonUtils.jsonStringToBundle(cursor.getString(cursor.getColumnIndex("bundle"))));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0013, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0017, code lost:
        if (r1 != null) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0019, code lost:
        if (r2 != null) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x001f, code lost:
        r1.close();
     */
    private b a(String str, String str2) {
        b a2;
        synchronized (this.b) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                a2 = a(str, str2, writableDatabase);
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't get notification from db with requestId: " + str, e);
                return new b();
            }
        }
        return a2;
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0061, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0066, code lost:
        if (r6 != null) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0068, code lost:
        if (r4 != null) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006e, code lost:
        r6.close();
     */
    private b a(String str, String str2, SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select * from " + str2 + " where " + "requestId" + "='" + str + "';", null);
        if (rawQuery.moveToFirst()) {
            b a2 = a(rawQuery);
            if (rawQuery != null) {
                rawQuery.close();
            }
            return a2;
        }
        if (rawQuery != null) {
            rawQuery.close();
        }
        String str3 = a;
        PWLog.noise(str3, "cant find local notification in table " + str2 + " by id " + str);
        return null;
        throw th;
    }

    private void a(int i, ContentValues contentValues, SQLiteDatabase sQLiteDatabase, String str) {
        String num = Integer.toString(i);
        if (sQLiteDatabase.updateWithOnConflict(str, contentValues, "requestId= ?", new String[]{num}, 4) == 0 && sQLiteDatabase.insert(str, null, contentValues) == 0) {
            PWLog.warn("notification", "Not stored " + num);
        }
    }

    private void a(int i, SQLiteDatabase sQLiteDatabase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("value", Integer.valueOf(i));
        if (sQLiteDatabase.updateWithOnConflict("nextRequestId", contentValues, null, null, 4) == 0 && sQLiteDatabase.insert("nextRequestId", null, contentValues) == 0) {
            PWLog.warn("saveNextId", "Not stored ");
        }
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format("create table %s (", "nextRequestId") + String.format("%s INTEGER primary key ", "value") + ");");
    }

    private void a(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL(String.format("create table %s (", str) + String.format("%s INTEGER primary key, ", "requestId") + String.format("%s INTEGER, ", "notificationId") + String.format("%s INTEGER, ", "triggerAtMilles") + String.format("%s TEXT, ", "notificationTag") + String.format("%s TEXT ", "bundle") + ");");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003b, code lost:
        r8 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0040, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0041, code lost:
        r3 = r8;
        r8 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0050, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0055, code lost:
        if (r1 != null) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0057, code lost:
        if (r7 != null) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x005d, code lost:
        r1.close();
     */
    private void a(String str, a aVar) {
        Cursor rawQuery;
        Throwable th;
        Throwable th2;
        synchronized (this.b) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                rawQuery = writableDatabase.rawQuery("Select * from " + str + ";", null);
                while (rawQuery.moveToNext()) {
                    aVar.a(a(rawQuery));
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't get NotificationList: ", e);
            }
        }
        return;
        throw th;
        if (rawQuery != null) {
            if (th2 != null) {
                try {
                    rawQuery.close();
                } catch (Throwable unused) {
                }
            } else {
                rawQuery.close();
            }
        }
        throw th;
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0030, code lost:
        if (r3 != null) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0032, code lost:
        if (r0 != null) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0038, code lost:
        r3.close();
     */
    private int b(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select * from nextRequestId;", null);
        if (rawQuery.moveToFirst()) {
            int i = rawQuery.getInt(rawQuery.getColumnIndex("value"));
            if (rawQuery != null) {
                rawQuery.close();
            }
            return i;
        }
        if (rawQuery != null) {
            rawQuery.close();
        }
        PWLog.noise(a, "nextId is empty, return 0");
        return 0;
        throw th;
    }

    @NonNull
    private String b(String str, String str2) {
        return "Select * from localNotificationShown where notificationId=" + str + " AND " + "notificationTag" + "='" + str2 + "';";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x006d, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0072, code lost:
        if (r1 != null) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0074, code lost:
        if (r6 != null) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007a, code lost:
        r1.close();
     */
    private void b(int i, String str) {
        String str2;
        String str3;
        synchronized (this.b) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            int delete = writableDatabase.delete(str, "requestId=" + i, null);
            PWLog.debug("delete notification " + delete + " by requestID:" + i);
            if (delete > 0) {
                str2 = a;
                str3 = "success remove local notification by " + i;
            } else {
                str2 = a;
                str3 = "fail remove local notification by " + i;
            }
            PWLog.noise(str2, str3);
            if (writableDatabase != null) {
                writableDatabase.close();
            }
        }
        return;
        throw th;
    }

    @NonNull
    private ContentValues c(b bVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("requestId", Integer.valueOf(bVar.c()));
        contentValues.put("notificationId", Integer.valueOf(bVar.d()));
        contentValues.put("notificationTag", bVar.e());
        contentValues.put("triggerAtMilles", Long.valueOf(bVar.a()));
        contentValues.put("bundle", JsonUtils.bundleToJson(bVar.b()).toString());
        return contentValues;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002f, code lost:
        if (r4 != null) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        if (r0 != null) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0037, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002a, code lost:
        r0 = move-exception;
     */
    @NonNull
    private Set<Integer> c(SQLiteDatabase sQLiteDatabase) {
        HashSet hashSet = new HashSet();
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select requestId from localNotification;", null);
        while (rawQuery.moveToNext()) {
            hashSet.add(Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("requestId"))));
        }
        if (rawQuery != null) {
            rawQuery.close();
        }
        return hashSet;
        throw th;
    }

    private long d(SQLiteDatabase sQLiteDatabase) {
        return DatabaseUtils.queryNumEntries(sQLiteDatabase, "localNotificationShown");
    }

    private void e(SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query("localNotificationShown", null, null, null, null, null, "requestId");
        if (query.moveToFirst()) {
            sQLiteDatabase.delete("localNotificationShown", "requestId=?", new String[]{query.getString(query.getColumnIndex("requestId"))});
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001c, code lost:
        if (r1 != null) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001e, code lost:
        if (r2 != null) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0024, code lost:
        r1.close();
     */
    public int a() {
        int b2;
        synchronized (this.b) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                b2 = b(writableDatabase);
                a(b2 + 1, writableDatabase);
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't set next RequestId", e);
                return 0;
            }
        }
        return b2;
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0035, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0036, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x003a, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x003b, code lost:
        r3 = r2;
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x004a, code lost:
        r6 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x004b, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x004f, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0050, code lost:
        r2 = r6;
        r6 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0055, code lost:
        if (r2 != null) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x005b, code lost:
        r1.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x004a A[ExcHandler: all (th java.lang.Throwable)] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0055  */
    public b a(int i, String str) {
        SQLiteDatabase writableDatabase;
        Throwable th;
        Cursor rawQuery;
        Throwable th2;
        Throwable th3;
        String b2 = b(Integer.toString(i), str);
        synchronized (this.b) {
            try {
                writableDatabase = getWritableDatabase();
                rawQuery = writableDatabase.rawQuery(b2, null);
                if (rawQuery.moveToNext()) {
                    b a2 = a(rawQuery);
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
                PWLog.error("Can't get Notification: ", e);
            }
        }
        if (writableDatabase != null) {
        }
        throw th;
        throw th3;
        throw th;
        if (rawQuery != null) {
            if (th2 != null) {
                try {
                    rawQuery.close();
                } catch (Throwable th4) {
                }
            } else {
                rawQuery.close();
            }
        }
        throw th3;
    }

    public b a(String str) {
        return a(str, "localNotificationShown");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0017, code lost:
        if (r1 != null) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0019, code lost:
        if (r2 != null) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001f, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        r4 = move-exception;
     */
    public void a(int i) {
        synchronized (this.b) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                a(i + 1, writableDatabase);
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't set next RequestId", e);
            }
        }
        return;
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0020, code lost:
        if (r2 != null) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0022, code lost:
        if (r6 != null) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0028, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        r6 = move-exception;
     */
    public void a(b bVar) {
        int c = bVar.c();
        ContentValues c2 = c(bVar);
        synchronized (this.b) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                a(c, c2, writableDatabase, "localNotification");
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't update preference value:" + c, e);
            }
        }
        return;
        throw th;
    }

    public void a(a aVar) {
        a("localNotification", aVar);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0013, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0017, code lost:
        if (r1 != null) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0019, code lost:
        if (r2 != null) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x001f, code lost:
        r1.close();
     */
    public Set<Integer> b() {
        Set<Integer> c;
        synchronized (this.b) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                c = c(writableDatabase);
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't get all request ids", e);
                return new HashSet();
            }
        }
        return c;
        throw th;
    }

    public void b(int i) {
        b(i, "localNotification");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002d, code lost:
        if (r2 != null) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002f, code lost:
        if (r0 != null) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0035, code lost:
        r2.close();
     */
    public void b(b bVar) {
        ContentValues c = c(bVar);
        synchronized (this.b) {
            int c2 = bVar.c();
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                a(c2, c, writableDatabase, "localNotificationShown");
                if (d(writableDatabase) > 10) {
                    e(writableDatabase);
                }
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't update preference value:" + c2, e);
            }
        }
        return;
        throw th;
    }

    public void c(int i) {
        b(i, "localNotificationShown");
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase);
        a(sQLiteDatabase, "localNotification");
        a(sQLiteDatabase, "localNotificationShown");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
