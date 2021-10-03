package com.pushwoosh.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;
import com.pushwoosh.inapp.view.b.a.b;
import com.pushwoosh.internal.utils.PWLog;
import java.util.ArrayList;
import java.util.List;

public class LockScreenMediaStorageImpl extends SQLiteOpenHelper implements o {
    private static final String a = b.class.getSimpleName();
    private final Object b = new Object();

    private static class Column {
        static final String REMOTE_URL = "remoteUrl";
        static final String RICH_MEDIA = "richMedia";
        static final String SOUND = "sound";

        private Column() {
        }
    }

    public LockScreenMediaStorageImpl(Context context) {
        super(context, "lockScreenRichMediaResources.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    private ContentValues a(String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("remoteUrl", str);
        return contentValues;
    }

    private b a(Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndex("richMedia"));
        return new b.a().a(string).c(cursor.getString(cursor.getColumnIndex("sound"))).a(true).a();
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format("create table %s (", "lockScreenResources") + String.format("%s TEXT , ", "richMedia") + String.format("%s TEXT ", "sound") + ");");
    }

    private ContentValues b(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("richMedia", str);
        contentValues.put("sound", str2);
        return contentValues;
    }

    private Uri b(Cursor cursor) {
        return Uri.parse(cursor.getString(cursor.getColumnIndex("remoteUrl")));
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format("create table %s (", "lockScreenRemoteUrls") + String.format("%s TEXT ", "remoteUrl") + ");");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003c, code lost:
        r3 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003d, code lost:
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0041, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0042, code lost:
        r4 = r3;
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0051, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0056, code lost:
        if (r10 != null) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0058, code lost:
        if (r2 != null) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x005e, code lost:
        r10.close();
     */
    @Override // com.pushwoosh.repository.o
    public List<b> a() {
        Cursor query;
        Throwable th;
        Throwable th2;
        ArrayList arrayList = new ArrayList();
        synchronized (this.b) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                query = writableDatabase.query("lockScreenResources", null, null, null, null, null, null);
                while (query.moveToNext()) {
                    arrayList.add(a(query));
                }
                if (!query.isClosed()) {
                    query.close();
                }
                if (query != null) {
                    query.close();
                }
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't get cached resources: ", e);
            }
        }
        return arrayList;
        throw th2;
        throw th;
        if (query != null) {
            if (th != null) {
                try {
                    query.close();
                } catch (Throwable unused) {
                }
            } else {
                query.close();
            }
        }
        throw th2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004d, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0051, code lost:
        if (r1 != null) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0053, code lost:
        if (r2 != null) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0059, code lost:
        r1.close();
     */
    @Override // com.pushwoosh.repository.o
    public void a(Uri uri) {
        synchronized (this.b) {
            String uri2 = uri.toString();
            if (TextUtils.isEmpty(uri2)) {
                PWLog.warn(a, "Remote url is empty.");
                return;
            }
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase.insertWithOnConflict("lockScreenRemoteUrls", null, a(uri2), 5) == -1) {
                    String str = a;
                    PWLog.warn(str, "Remote url " + uri2 + " was not stored.");
                }
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't cache remote url: " + uri2, e);
            }
            return;
        }
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003a, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003f, code lost:
        if (r1 != null) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0041, code lost:
        if (r9 != null) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
        r1.close();
     */
    @Override // com.pushwoosh.repository.o
    public void a(String str, String str2) {
        synchronized (this.b) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase.insertWithOnConflict("lockScreenResources", null, b(str, str2), 5) == -1) {
                    String str3 = a;
                    PWLog.warn(str3, "Rich media " + str + " was not stored.");
                }
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't cache richMedia resource: " + str, e);
            }
        }
        return;
        throw th;
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
        r3 = move-exception;
     */
    @Override // com.pushwoosh.repository.o
    public void b() {
        synchronized (this.b) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                writableDatabase.execSQL("delete from lockScreenResources");
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't clear resources", e);
            }
        }
        return;
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003c, code lost:
        r3 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003d, code lost:
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0041, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0042, code lost:
        r4 = r3;
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0051, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0056, code lost:
        if (r10 != null) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0058, code lost:
        if (r2 != null) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x005e, code lost:
        r10.close();
     */
    @Override // com.pushwoosh.repository.o
    public List<Uri> c() {
        Cursor query;
        Throwable th;
        Throwable th2;
        ArrayList arrayList = new ArrayList();
        synchronized (this.b) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                query = writableDatabase.query("lockScreenRemoteUrls", null, null, null, null, null, null);
                while (query.moveToNext()) {
                    arrayList.add(b(query));
                }
                if (!query.isClosed()) {
                    query.close();
                }
                if (query != null) {
                    query.close();
                }
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't get cached resources: ", e);
            }
        }
        return arrayList;
        throw th2;
        throw th;
        if (query != null) {
            if (th != null) {
                try {
                    query.close();
                } catch (Throwable unused) {
                }
            } else {
                query.close();
            }
        }
        throw th2;
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
        r3 = move-exception;
     */
    @Override // com.pushwoosh.repository.o
    public void d() {
        synchronized (this.b) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                writableDatabase.execSQL("delete from lockScreenRemoteUrls");
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't clear remote urls", e);
            }
        }
        return;
        throw th;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase);
        b(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
