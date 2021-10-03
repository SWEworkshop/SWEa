package com.pushwoosh.internal.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.crash.LogSender;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class e {
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00a4, code lost:
        r8 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00a6, code lost:
        r8 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00b6, code lost:
        if (r1 != null) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00c7, code lost:
        if (r1 != null) goto L_0x00b8;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0094 A[SYNTHETIC, Splitter:B:43:0x0094] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00a6 A[ExcHandler: FileNotFoundException (e java.io.FileNotFoundException), Splitter:B:13:0x002f] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00ce A[SYNTHETIC, Splitter:B:73:0x00ce] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0035 A[SYNTHETIC] */
    @Nullable
    public static File a(@Nullable File file, @Nullable File file2) {
        Throwable th;
        ZipInputStream zipInputStream;
        Throwable th2;
        BufferedOutputStream bufferedOutputStream;
        IOException e;
        if (file == null || file2 == null || !d(file)) {
            return null;
        }
        a(file2);
        if (!file2.isDirectory() && !file2.mkdirs()) {
            return null;
        }
        try {
            zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(file), 8192));
            try {
                byte[] bArr = new byte[8192];
                String canonicalPath = file2.getCanonicalPath();
                while (true) {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry != null) {
                        File file3 = new File(canonicalPath, nextEntry.getName());
                        if (!file3.getCanonicalPath().startsWith(canonicalPath)) {
                            throw new SecurityException("Provided zip file path has Path Traversal Vulnerability");
                        } else if (nextEntry.isDirectory()) {
                            file3.mkdir();
                        } else {
                            new File(file3.getParent()).mkdirs();
                            try {
                                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file3), 8192);
                                while (true) {
                                    try {
                                        int read = zipInputStream.read(bArr);
                                        if (read == -1) {
                                            break;
                                        }
                                        bufferedOutputStream.write(bArr, 0, read);
                                    } catch (IOException e2) {
                                        e = e2;
                                        try {
                                            PWLog.exception(e);
                                            file3.delete();
                                            if (bufferedOutputStream == null) {
                                            }
                                            bufferedOutputStream.close();
                                        } catch (Throwable th3) {
                                            th2 = th3;
                                            if (bufferedOutputStream != null) {
                                                bufferedOutputStream.close();
                                            }
                                            throw th2;
                                        }
                                    }
                                }
                                bufferedOutputStream.flush();
                            } catch (IOException e3) {
                                e = e3;
                                bufferedOutputStream = null;
                                PWLog.exception(e);
                                file3.delete();
                                if (bufferedOutputStream == null) {
                                }
                                bufferedOutputStream.close();
                            } catch (Throwable th4) {
                                th2 = th4;
                                bufferedOutputStream = null;
                                if (bufferedOutputStream != null) {
                                }
                                throw th2;
                            }
                            bufferedOutputStream.close();
                        }
                    } else {
                        try {
                            zipInputStream.close();
                        } catch (IOException unused) {
                        }
                        return file2;
                    }
                }
            } catch (IOException unused2) {
            } catch (FileNotFoundException e4) {
            }
        } catch (FileNotFoundException e5) {
            FileNotFoundException e6 = e5;
            zipInputStream = null;
            PWLog.error("FileUtils", e6.getMessage(), e6);
        } catch (IOException e7) {
            IOException e8 = e7;
            zipInputStream = null;
            try {
                PWLog.error("FileUtils", e8.getMessage(), e8);
            } catch (Throwable th5) {
                th = th5;
                if (zipInputStream != null) {
                }
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            zipInputStream = null;
            if (zipInputStream != null) {
                try {
                    zipInputStream.close();
                } catch (IOException unused3) {
                }
            }
            throw th;
        }
        return null;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:25|26|(2:27|(1:29)(1:103))|30|31|32|33|34|(1:37)|38) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0020, code lost:
        com.pushwoosh.internal.utils.PWLog.error("FileUtils", "fail download: " + r10 + "  responseCode: " + r2.getResponseCode());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0042, code lost:
        if (r3 == null) goto L_0x0049;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x007e */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x00bb A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0052 A[SYNTHETIC, Splitter:B:18:0x0052] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00aa A[SYNTHETIC, Splitter:B:60:0x00aa] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00b1 A[SYNTHETIC, Splitter:B:64:0x00b1] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00c1 A[SYNTHETIC, Splitter:B:72:0x00c1] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x00c8 A[SYNTHETIC, Splitter:B:76:0x00c8] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x00d6 A[SYNTHETIC, Splitter:B:85:0x00d6] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x00df A[SYNTHETIC, Splitter:B:90:0x00df] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x00e6  */
    @Nullable
    public static File a(String str, File file) {
        HttpURLConnection httpURLConnection;
        Throwable th;
        IOException e;
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream2 = null;
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i >= 3) {
                break;
            }
            try {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode() != 200) {
                        break;
                    }
                    BufferedInputStream bufferedInputStream2 = new BufferedInputStream(httpURLConnection.getInputStream());
                    try {
                        bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                    } catch (MalformedURLException unused) {
                        bufferedInputStream = bufferedInputStream2;
                        if (bufferedInputStream != null) {
                        }
                        if (bufferedOutputStream2 != null) {
                        }
                        if (httpURLConnection != null) {
                        }
                        return null;
                    } catch (IOException e2) {
                        e = e2;
                        bufferedInputStream = bufferedInputStream2;
                        try {
                            PWLog.exception(e);
                            if (bufferedInputStream != null) {
                            }
                            if (bufferedOutputStream2 != null) {
                            }
                            if (httpURLConnection == null) {
                            }
                            i = i2;
                        } catch (Throwable th2) {
                            th = th2;
                            if (bufferedInputStream != null) {
                            }
                            if (bufferedOutputStream2 != null) {
                            }
                            if (httpURLConnection != null) {
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedInputStream = bufferedInputStream2;
                        if (bufferedInputStream != null) {
                        }
                        if (bufferedOutputStream2 != null) {
                        }
                        if (httpURLConnection != null) {
                        }
                        throw th;
                    }
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = bufferedInputStream2.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            bufferedOutputStream.write(bArr, 0, read);
                        }
                        bufferedOutputStream.flush();
                        bufferedInputStream2.close();
                        bufferedOutputStream.close();
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        return file;
                    } catch (MalformedURLException unused2) {
                        bufferedOutputStream2 = bufferedOutputStream;
                        bufferedInputStream = bufferedInputStream2;
                        if (bufferedInputStream != null) {
                        }
                        if (bufferedOutputStream2 != null) {
                        }
                        if (httpURLConnection != null) {
                        }
                        return null;
                    } catch (IOException e3) {
                        bufferedOutputStream2 = bufferedOutputStream;
                        bufferedInputStream = bufferedInputStream2;
                        e = e3;
                        PWLog.exception(e);
                        if (bufferedInputStream != null) {
                        }
                        if (bufferedOutputStream2 != null) {
                        }
                        if (httpURLConnection == null) {
                        }
                        i = i2;
                    } catch (Throwable th4) {
                        th = th4;
                        bufferedOutputStream2 = bufferedOutputStream;
                        bufferedInputStream = bufferedInputStream2;
                        if (bufferedInputStream != null) {
                        }
                        if (bufferedOutputStream2 != null) {
                        }
                        if (httpURLConnection != null) {
                        }
                        throw th;
                    }
                } catch (MalformedURLException unused3) {
                    if (bufferedInputStream != null) {
                    }
                    if (bufferedOutputStream2 != null) {
                    }
                    if (httpURLConnection != null) {
                    }
                    return null;
                } catch (IOException e4) {
                    e = e4;
                    PWLog.exception(e);
                    if (bufferedInputStream != null) {
                    }
                    if (bufferedOutputStream2 != null) {
                    }
                    if (httpURLConnection == null) {
                    }
                    i = i2;
                }
            } catch (MalformedURLException unused4) {
                httpURLConnection = null;
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException unused5) {
                    }
                }
                if (bufferedOutputStream2 != null) {
                    try {
                        bufferedOutputStream2.close();
                    } catch (IOException unused6) {
                    }
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return null;
            } catch (IOException e5) {
                e = e5;
                httpURLConnection = null;
                PWLog.exception(e);
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException unused7) {
                    }
                }
                if (bufferedOutputStream2 != null) {
                    try {
                        bufferedOutputStream2.close();
                    } catch (IOException unused8) {
                    }
                }
                if (httpURLConnection == null) {
                    httpURLConnection.disconnect();
                }
                i = i2;
            } catch (Throwable th5) {
                th = th5;
                httpURLConnection = null;
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException unused9) {
                    }
                }
                if (bufferedOutputStream2 != null) {
                    try {
                        bufferedOutputStream2.close();
                    } catch (IOException unused10) {
                    }
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
            i = i2;
        }
        return null;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e6) {
                if (!a(e6)) {
                    LogSender.submitCustomError(e6);
                }
            }
        }
        return null;
        if (bufferedOutputStream2 != null) {
            try {
                bufferedOutputStream2.close();
            } catch (IOException unused11) {
            }
        }
        if (httpURLConnection != null) {
        }
        return null;
    }

    public static String a(String str) {
        String[] split = str.split("/");
        return split[split.length - 1];
    }

    public static boolean a(File file) {
        File[] listFiles;
        if (!file.exists()) {
            return true;
        }
        if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                a(file2);
            }
        }
        return file.delete();
    }

    private static boolean a(Exception exc) {
        return (exc instanceof NullPointerException) && exc.getMessage().equals("ssl_session == null");
    }

    /* JADX INFO: finally extract failed */
    public static String b(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                    sb.append("\n");
                } else {
                    bufferedReader.close();
                    fileInputStream.close();
                    return sb.toString();
                }
            } catch (Throwable th) {
                bufferedReader.close();
                fileInputStream.close();
                throw th;
            }
        }
    }

    public static String b(String str) {
        return str.substring(0, str.lastIndexOf("."));
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0076 A[SYNTHETIC, Splitter:B:26:0x0076] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0098 A[SYNTHETIC, Splitter:B:34:0x0098] */
    @NonNull
    public static String c(@NonNull File file) {
        BufferedInputStream bufferedInputStream = null;
        try {
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = bufferedInputStream2.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    instance.update(bArr, 0, read);
                }
                StringBuilder sb = new StringBuilder();
                byte[] digest = instance.digest();
                int length = digest.length;
                for (int i = 0; i < length; i++) {
                    sb.append(String.format("%02x", Byte.valueOf(digest[i])));
                }
                String sb2 = sb.toString();
                try {
                    bufferedInputStream2.close();
                } catch (IOException e) {
                    PWLog.error("Failed to read file " + file.getName(), e);
                }
                return sb2;
            } catch (IOException unused) {
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                }
                return "";
            } catch (NoSuchAlgorithmException unused2) {
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                }
                return "";
            } catch (Throwable th) {
                if (bufferedInputStream2 != null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException e2) {
                        PWLog.error("Failed to read file " + file.getName(), e2);
                    }
                }
                throw th;
            }
        } catch (IOException unused3) {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e3) {
                    PWLog.error("Failed to read file " + file.getName(), e3);
                }
            }
            return "";
        } catch (NoSuchAlgorithmException unused4) {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e4) {
                    PWLog.error("Failed to read file " + file.getName(), e4);
                }
            }
            return "";
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000c, code lost:
        return false;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    private static boolean d(File file) {
        try {
            new ZipFile(file).close();
        } catch (IOException unused) {
        }
        return true;
    }
}
