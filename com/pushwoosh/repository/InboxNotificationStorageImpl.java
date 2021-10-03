package com.pushwoosh.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.utils.PWLog;

public class InboxNotificationStorageImpl extends SQLiteOpenHelper implements m {
    private static final String a = "InboxNotificationStorageImpl";
    private final Object b = new Object();

    private static class Column {
        static final String INBOX_MESSAGE_ID = "inbox_message_id";
        static final String NOTIFICATION_ID = "notification_id";
        static final String NOTIFICATION_TAG = "notification_tag";

        private Column() {
        }
    }

    public InboxNotificationStorageImpl(Context context) {
        super(context, "inboxNotificationDb.db", (SQLiteDatabase.CursorFactory) null, 2);
    }

    private int a(Cursor cursor) {
        return cursor.getInt(cursor.getColumnIndex("notification_id"));
    }

    private void a(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL(String.format("create table %s (", str) + String.format("%s TEXT, ", "inbox_message_id") + String.format("%s TEXT, ", "notification_tag") + String.format("%s INTEGER ", "notification_id") + ");");
    }

    private ContentValues b(String str, int i, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("inbox_message_id", str);
        contentValues.put("notification_id", Integer.valueOf(i));
        contentValues.put("notification_tag", str2);
        return contentValues;
    }

    private String b(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex("notification_tag"));
    }

    private void b(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
        a(sQLiteDatabase, str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0058, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0059, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005d, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005e, code lost:
        r5 = r4;
        r4 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x006d, code lost:
        r3 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x006e, code lost:
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0072, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0073, code lost:
        r4 = r3;
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0078, code lost:
        if (r4 != null) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x007e, code lost:
        r2.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006d A[ExcHandler: all (th java.lang.Throwable)] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0078  */
    @Override // com.pushwoosh.repository.m
    @Nullable
    public Integer a(String str) {
        SQLiteDatabase writableDatabase;
        Throwable th;
        Cursor rawQuery;
        Throwable th2;
        Throwable th3;
        Integer valueOf;
        synchronized (this.b) {
            try {
                writableDatabase = getWritableDatabase();
                rawQuery = writableDatabase.rawQuery("Select * from inboxNotifications where inbox_message_id='" + str + "';", null);
                if (rawQuery.moveToFirst()) {
                    valueOf = Integer.valueOf(a(rawQuery));
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    if (writableDatabase != null) {
                        writableDatabase.close();
                    }
                } else {
                    PWLog.error(a, "Can't find InboxMessage with id: " + str);
                    throw new Exception();
                }
            } catch (Exception e) {
                PWLog.error(a, "Can't get NotificationId for InboxMessage with id: " + str, e);
                return null;
            }
        }
        return valueOf;
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
        if (writableDatabase != null) {
        }
        throw th;
        throw th3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003a, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003f, code lost:
        if (r1 != null) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0041, code lost:
        if (r7 != null) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
        r1.close();
     */
    @Override // com.pushwoosh.repository.m
    public void a(String str, int i, String str2) {
        synchronized (this.b) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase.insertWithOnConflict("inboxNotifications", null, b(str, i, str2), 5) == -1) {
                    String str3 = a;
                    PWLog.warn(str3, "Notification with inboxMessageId: " + str + " was not stored.");
                }
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error(a, "Error occurred while storing notification id and notification tag", e);
            }
        }
        return;
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0054, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0055, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0059, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005a, code lost:
        r5 = r4;
        r4 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0069, code lost:
        r3 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x006a, code lost:
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x006e, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x006f, code lost:
        r4 = r3;
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0074, code lost:
        if (r4 != null) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x007a, code lost:
        r2.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0069 A[ExcHandler: all (th java.lang.Throwable)] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0074  */
    @Override // com.pushwoosh.repository.m
    @Nullable
    public String b(String str) {
        SQLiteDatabase writableDatabase;
        Throwable th;
        Cursor rawQuery;
        Throwable th2;
        Throwable th3;
        String b2;
        synchronized (this.b) {
            try {
                writableDatabase = getWritableDatabase();
                rawQuery = writableDatabase.rawQuery("Select * from inboxNotifications where inbox_message_id='" + str + "';", null);
                if (rawQuery.moveToFirst()) {
                    b2 = b(rawQuery);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    if (writableDatabase != null) {
                        writableDatabase.close();
                    }
                } else {
                    PWLog.error(a, "Can't find InboxMessage with id: " + str);
                    throw new Exception();
                }
            } catch (Exception e) {
                PWLog.error(a, "Can't get NotificationTag for InboxMessage with id: " + str, e);
                return null;
            }
        }
        return b2;
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

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase, "inboxNotifications");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        b(sQLiteDatabase, "inboxNotifications");
    }
}
