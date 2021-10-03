package com.pushwoosh.internal.crash;

import android.content.Context;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* access modifiers changed from: package-private */
public class c {
    public static final SimpleDateFormat a = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
    private final String b;
    private Date c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;

    public c(String str) {
        this.b = str;
        this.m = "";
    }

    public c(String str, Throwable th) {
        this(str);
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        this.m = stringWriter.toString();
    }

    private void a(Writer writer, String str, String str2) throws IOException {
        writer.write(str + ": " + str2 + "\n");
    }

    public void a(Context context) {
        File a2 = h.a(context);
        a(new File(a2, this.b + h.a()));
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00a4 A[SYNTHETIC, Splitter:B:22:0x00a4] */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    public void a(File file) {
        Throwable th;
        IOException e2;
        a.b("Writing unhandled exception to: " + file.getAbsolutePath());
        BufferedWriter bufferedWriter = null;
        try {
            BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file));
            try {
                a(bufferedWriter2, "Package", this.h);
                a(bufferedWriter2, "Version Code", this.j);
                a(bufferedWriter2, "Version Name", this.i);
                a(bufferedWriter2, "Android", this.d);
                a(bufferedWriter2, "Android Build", this.e);
                a(bufferedWriter2, "Manufacturer", this.f);
                a(bufferedWriter2, "Model", this.g);
                a(bufferedWriter2, "Thread", this.l);
                a(bufferedWriter2, "Date", a.format(this.c));
                a(bufferedWriter2, "Pushwoosh Application Code", this.k);
                a(bufferedWriter2, "Pushwoosh Plugin", this.n);
                bufferedWriter2.write("\n");
                bufferedWriter2.write(this.m);
                bufferedWriter2.flush();
                try {
                    bufferedWriter2.close();
                } catch (IOException e3) {
                    a.a("Error saving crash report!", e3);
                }
            } catch (IOException e4) {
                e2 = e4;
                bufferedWriter = bufferedWriter2;
                try {
                    a.a("Error saving crash report!", e2);
                    if (bufferedWriter == null) {
                        bufferedWriter.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedWriter2 = bufferedWriter;
                    if (bufferedWriter2 != null) {
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (bufferedWriter2 != null) {
                    try {
                        bufferedWriter2.close();
                    } catch (IOException e5) {
                        a.a("Error saving crash report!", e5);
                    }
                }
                throw th;
            }
        } catch (IOException e6) {
            e2 = e6;
            a.a("Error saving crash report!", e2);
            if (bufferedWriter == null) {
            }
        }
    }

    public void a(String str) {
        this.d = str;
    }

    public void a(Date date) {
        this.c = date;
    }

    public void b(String str) {
        this.e = str;
    }

    public void c(String str) {
        this.f = str;
    }

    public void d(String str) {
        this.g = str;
    }

    public void e(String str) {
        this.h = str;
    }

    public void f(String str) {
        this.i = str;
    }

    public void g(String str) {
        this.j = str;
    }

    public void h(String str) {
        this.l = str;
    }

    public void i(String str) {
        this.k = str;
    }

    public void j(String str) {
        this.n = str;
    }
}
