package com.pushwoosh.inbox.e.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.pushwoosh.inbox.data.InboxMessageType;
import com.pushwoosh.inbox.internal.data.InboxMessageSource;
import com.pushwoosh.inbox.internal.data.InboxMessageStatus;
import com.pushwoosh.inbox.internal.data.b;
import com.pushwoosh.internal.utils.PWLog;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class b extends SQLiteOpenHelper {
    private final Object a = new Object();

    /* access modifiers changed from: private */
    public static class a {
        static String a = "inboxMessage";
    }

    public b(Context context) {
        super(context, "PwInbox.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    @NonNull
    private ContentValues a(com.pushwoosh.inbox.internal.data.b bVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("inbox_id", bVar.a());
        contentValues.put("inbox_order", Long.valueOf(bVar.b()));
        contentValues.put("expired_date", Long.valueOf(bVar.c()));
        contentValues.put("send_date", Long.valueOf(bVar.d()));
        contentValues.put("title", bVar.f());
        contentValues.put("hash", bVar.e());
        contentValues.put("message", bVar.g());
        contentValues.put("image", bVar.h());
        contentValues.put("type", Integer.valueOf(bVar.i().getCode()));
        contentValues.put("action_params", bVar.j());
        contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(bVar.l().getCode()));
        contentValues.put(FirebaseAnalytics.Param.SOURCE, Integer.valueOf(bVar.m().getCode()));
        return contentValues;
    }

    static /* synthetic */ Integer a(Cursor cursor) {
        return Integer.valueOf(cursor.moveToFirst() ? cursor.getInt(cursor.getColumnIndex("COUNT")) : -1);
    }

    /* JADX INFO: finally extract failed */
    private <T> T a(String str, com.pushwoosh.inbox.a.a<Cursor, T> aVar) {
        T a2;
        synchronized (this.a) {
            try {
                SQLiteDatabase readableDatabase = getReadableDatabase();
                try {
                    readableDatabase.beginTransaction();
                    Cursor rawQuery = readableDatabase.rawQuery(str, new String[0]);
                    try {
                        a2 = aVar.a(rawQuery);
                        readableDatabase.setTransactionSuccessful();
                        rawQuery.close();
                        readableDatabase.endTransaction();
                    } catch (Throwable th) {
                        rawQuery.close();
                        readableDatabase.endTransaction();
                        throw th;
                    }
                } catch (Exception e) {
                    PWLog.error("Failed work with db", e);
                    return null;
                } finally {
                    readableDatabase.close();
                }
            } catch (Exception e2) {
                PWLog.error("Problem with db executing", e2);
                return null;
            }
        }
        return a2;
    }

    static /* synthetic */ List a(b bVar, Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        if (cursor.moveToFirst()) {
            do {
                arrayList.add(bVar.b(cursor));
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    private List<String> a(List<String> list, SQLiteDatabase sQLiteDatabase) {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT inbox_id FROM " + a.a + " WHERE " + "inbox_id" + " NOT IN ('" + TextUtils.join("', '", list) + "')", new String[0]);
        try {
            if (rawQuery.moveToFirst()) {
                do {
                    arrayList.add(rawQuery.getString(rawQuery.getColumnIndex("inbox_id")));
                } while (rawQuery.moveToNext());
            }
            return arrayList;
        } finally {
            rawQuery.close();
        }
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.delete(a.a, "expired_date < ?", new String[]{String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()))});
    }

    private void a(SQLiteDatabase sQLiteDatabase, com.pushwoosh.inbox.internal.data.b bVar) {
        if (sQLiteDatabase.insert(a.a, null, a(bVar)) == 0) {
            PWLog.warn("Not stored " + bVar.a());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0092 A[Catch:{ all -> 0x00b9 }] */
    private void a(SQLiteDatabase sQLiteDatabase, com.pushwoosh.inbox.internal.data.b bVar, com.pushwoosh.inbox.e.a.a aVar) {
        boolean z;
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT status,source FROM " + a.a + " WHERE " + "inbox_id" + " = '" + bVar.a() + "'", new String[0]);
        try {
            if (rawQuery.moveToFirst()) {
                InboxMessageStatus byCode = InboxMessageStatus.getByCode(rawQuery.getInt(rawQuery.getColumnIndex(NotificationCompat.CATEGORY_STATUS)));
                if (!(InboxMessageSource.getByCode(rawQuery.getInt(rawQuery.getColumnIndex(FirebaseAnalytics.Param.SOURCE))) == InboxMessageSource.PUSH || byCode == null)) {
                    if (!byCode.isLowerStatus(bVar.l())) {
                        if (bVar.l().isLowerStatus(byCode)) {
                            aVar.e().put(bVar.a(), byCode);
                            z = false;
                            ContentValues a2 = a(bVar);
                            if (!z) {
                                a2.remove(NotificationCompat.CATEGORY_STATUS);
                            }
                            sQLiteDatabase.update(a.a, a2, "inbox_id = ?", new String[]{bVar.a()});
                        }
                        z = true;
                        ContentValues a22 = a(bVar);
                        if (!z) {
                        }
                        sQLiteDatabase.update(a.a, a22, "inbox_id = ?", new String[]{bVar.a()});
                    }
                }
                aVar.f().add(bVar.a());
                z = true;
                ContentValues a222 = a(bVar);
                if (!z) {
                }
                sQLiteDatabase.update(a.a, a222, "inbox_id = ?", new String[]{bVar.a()});
            } else {
                aVar.d().add(bVar.a());
                a(sQLiteDatabase, bVar);
            }
        } finally {
            rawQuery.close();
        }
    }

    private com.pushwoosh.inbox.internal.data.b b(Cursor cursor) {
        return new b.a().a(cursor.getString(cursor.getColumnIndex("inbox_id"))).a(cursor.getLong(cursor.getColumnIndex("inbox_order"))).b(cursor.getLong(cursor.getColumnIndex("expired_date"))).c(cursor.getLong(cursor.getColumnIndex("send_date"))).c(cursor.getString(cursor.getColumnIndex("title"))).b(cursor.getString(cursor.getColumnIndex("hash"))).d(cursor.getString(cursor.getColumnIndex("message"))).e(cursor.getString(cursor.getColumnIndex("image"))).a(InboxMessageType.getByCode(cursor.getInt(cursor.getColumnIndex("type")))).f(cursor.getString(cursor.getColumnIndex("action_params"))).a(InboxMessageStatus.getByCode(cursor.getInt(cursor.getColumnIndex(NotificationCompat.CATEGORY_STATUS)))).a(InboxMessageSource.getByCode(cursor.getInt(cursor.getColumnIndex(FirebaseAnalytics.Param.SOURCE)))).a();
    }

    static /* synthetic */ com.pushwoosh.inbox.internal.data.b b(b bVar, Cursor cursor) {
        if (cursor.moveToFirst()) {
            return bVar.b(cursor);
        }
        return null;
    }

    /* JADX INFO: finally extract failed */
    private void b(String str) {
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                try {
                    writableDatabase.beginTransaction();
                    try {
                        writableDatabase.execSQL(str);
                        writableDatabase.setTransactionSuccessful();
                        writableDatabase.endTransaction();
                    } catch (Throwable th) {
                        writableDatabase.endTransaction();
                        throw th;
                    }
                } finally {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Problem with db executing", e);
            }
        }
    }

    static /* synthetic */ List c(b bVar, Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        if (cursor.moveToFirst()) {
            do {
                arrayList.add(bVar.b(cursor));
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    private Iterable<Integer> d(Collection<InboxMessageStatus> collection) {
        ArrayList arrayList = new ArrayList();
        for (InboxMessageStatus inboxMessageStatus : collection) {
            arrayList.add(Integer.valueOf(inboxMessageStatus.getCode()));
        }
        return arrayList;
    }

    static /* synthetic */ List d(b bVar, Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        if (cursor.moveToFirst()) {
            do {
                arrayList.add(bVar.b(cursor));
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    @NonNull
    private static String e(Collection<String> collection) {
        return "DELETE FROM " + a.a + " WHERE " + "inbox_id" + " IN ('" + TextUtils.join("', '", collection) + "')";
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public com.pushwoosh.inbox.e.a.a a(Collection<com.pushwoosh.inbox.internal.data.b> collection, boolean z) {
        com.pushwoosh.inbox.e.a.a a2 = com.pushwoosh.inbox.e.a.a.a();
        ArrayList arrayList = new ArrayList();
        synchronized (this.a) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            try {
                writableDatabase.beginTransaction();
                try {
                    a(writableDatabase);
                    for (com.pushwoosh.inbox.internal.data.b bVar : collection) {
                        arrayList.add(bVar.a());
                        a(writableDatabase, bVar, a2);
                    }
                    if (z) {
                        a2.c().addAll(a(arrayList, writableDatabase));
                        writableDatabase.execSQL(e(a2.c()));
                    }
                    writableDatabase.setTransactionSuccessful();
                } finally {
                    writableDatabase.endTransaction();
                }
            } catch (Exception e) {
                PWLog.error("Failed work with db", e);
            } catch (Throwable th) {
                writableDatabase.close();
                throw th;
            }
            writableDatabase.close();
        }
        return a2;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public com.pushwoosh.inbox.internal.data.b a(String str) {
        return (com.pushwoosh.inbox.internal.data.b) a("SELECT * FROM " + a.a + " WHERE " + "inbox_id" + " = '" + str + "' AND " + "expired_date" + " > " + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()), f.a(this));
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Collection<com.pushwoosh.inbox.internal.data.b> a() {
        return (Collection) a("SELECT * FROM " + a.a + " WHERE " + FirebaseAnalytics.Param.SOURCE + " = " + InboxMessageSource.PUSH.getCode(), c.a(this));
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Collection<com.pushwoosh.inbox.internal.data.b> a(Collection<InboxMessageStatus> collection, long j, int i) {
        String str;
        if (i == -1) {
            str = "";
        } else {
            str = "LIMIT " + i;
        }
        return (Collection) a("SELECT * FROM " + a.a + " WHERE " + NotificationCompat.CATEGORY_STATUS + " IN (" + TextUtils.join(",", d(collection)) + ") AND " + "expired_date" + " > " + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + " AND " + "inbox_order" + " < " + j + " ORDER BY " + "inbox_order" + " DESC " + str, d.a(this));
    }

    /* access modifiers changed from: package-private */
    public Collection<String> a(Collection<String> collection, InboxMessageStatus inboxMessageStatus) {
        ArrayList arrayList = new ArrayList(collection);
        List<com.pushwoosh.inbox.internal.data.b> c = c(collection);
        if (c == null) {
            return Collections.emptyList();
        }
        for (com.pushwoosh.inbox.internal.data.b bVar : c) {
            if (bVar.l() == inboxMessageStatus) {
                arrayList.remove(bVar.a());
            }
        }
        if (arrayList.isEmpty()) {
            return Collections.emptyList();
        }
        b("UPDATE " + a.a + " SET " + NotificationCompat.CATEGORY_STATUS + " = " + inboxMessageStatus.getCode() + " WHERE " + "inbox_id" + " IN ('" + TextUtils.join("', '", arrayList) + "')");
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public void a(Collection<String> collection) {
        b(e(collection));
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Integer b(Collection<InboxMessageStatus> collection) {
        return (Integer) a("SELECT count(*) AS COUNT FROM " + a.a + " WHERE " + NotificationCompat.CATEGORY_STATUS + " IN ('" + TextUtils.join("', '", d(collection)) + "') AND " + "expired_date" + " > " + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()), e.a());
    }

    /* JADX INFO: finally extract failed */
    public void b() {
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                try {
                    writableDatabase.beginTransaction();
                    try {
                        writableDatabase.delete(a.a, "", new String[0]);
                        writableDatabase.setTransactionSuccessful();
                        writableDatabase.endTransaction();
                    } catch (Throwable th) {
                        writableDatabase.endTransaction();
                        throw th;
                    }
                } finally {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Problem with db executing", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public List<com.pushwoosh.inbox.internal.data.b> c(Collection<String> collection) {
        return (List) a("SELECT * FROM " + a.a + " WHERE " + "inbox_id" + " IN ('" + TextUtils.join("', '", collection) + "') AND " + "expired_date" + " > " + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + " ORDER BY " + "inbox_order" + " DESC", g.a(this));
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE " + a.a + " (" + "inbox_id" + " text primary key, " + "inbox_order" + " integer default 0, " + "expired_date" + " integer default 0, " + "send_date" + " integer default 0, " + "title" + " text, " + "hash" + " text, " + "message" + " text, " + "image" + " text, " + "type" + " integer default -1, " + "action_params" + " text, " + NotificationCompat.CATEGORY_STATUS + " integer default -1, " + FirebaseAnalytics.Param.SOURCE + " integer default -1);");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
