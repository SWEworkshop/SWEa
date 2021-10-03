package com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.a;

import com.pushwoosh.thirdpart.com.ironz.binaryprefs.d.a;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public final class b implements a {
    private static final String[] a = new String[0];
    private final File b;
    private final File c;

    public b(com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.b.b bVar) {
        this.b = bVar.a();
        this.c = bVar.b();
    }

    private void a(File file, File file2) {
        if (file.exists()) {
            if (file2.exists()) {
                file2.delete();
            }
            file.renameTo(file2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0044 A[SYNTHETIC, Splitter:B:27:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0049 A[Catch:{ Exception -> 0x004c }] */
    private void a(File file, byte[] bArr) {
        Throwable th;
        RandomAccessFile randomAccessFile;
        Exception e;
        FileChannel channel;
        FileChannel fileChannel = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            try {
                randomAccessFile.setLength(0);
                channel = randomAccessFile.getChannel();
            } catch (Exception e2) {
                e = e2;
                try {
                    throw new a(e);
                } catch (Throwable th2) {
                    th = th2;
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (Exception unused) {
                            throw th;
                        }
                    }
                    if (fileChannel != null) {
                        fileChannel.close();
                    }
                    throw th;
                }
            }
            try {
                MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, (long) bArr.length);
                map.put(bArr);
                channel.write(map);
                map.force();
                try {
                    randomAccessFile.close();
                    if (channel != 0) {
                        channel.close();
                    }
                } catch (Exception unused2) {
                }
            } catch (Exception e3) {
                e = e3;
                fileChannel = channel;
                throw new a(e);
            } catch (Throwable th3) {
                th = th3;
                fileChannel = channel;
                if (randomAccessFile != null) {
                }
                if (fileChannel != null) {
                }
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            randomAccessFile = null;
            throw new a(e);
        } catch (Throwable th4) {
            th = th4;
            randomAccessFile = null;
            if (randomAccessFile != null) {
            }
            if (fileChannel != null) {
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0047 A[SYNTHETIC, Splitter:B:27:0x0047] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x004c A[Catch:{ Exception -> 0x004f }] */
    private byte[] a(File file) {
        FileChannel fileChannel;
        RandomAccessFile randomAccessFile;
        Throwable th;
        Exception e;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            try {
                fileChannel = randomAccessFile.getChannel();
                try {
                    int length = (int) randomAccessFile.length();
                    byte[] bArr = new byte[length];
                    fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, (long) length).get(bArr);
                    try {
                        randomAccessFile.close();
                        if (fileChannel != null) {
                            fileChannel.close();
                        }
                    } catch (Exception unused) {
                    }
                    return bArr;
                } catch (Exception e2) {
                    e = e2;
                    try {
                        throw new a(e);
                    } catch (Throwable th2) {
                        th = th2;
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (Exception unused2) {
                                throw th;
                            }
                        }
                        if (fileChannel != null) {
                            fileChannel.close();
                        }
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                fileChannel = null;
                throw new a(e);
            } catch (Throwable th3) {
                th = th3;
                fileChannel = null;
                if (randomAccessFile != null) {
                }
                if (fileChannel != null) {
                }
                throw th;
            }
        } catch (Exception e4) {
            randomAccessFile = null;
            e = e4;
            fileChannel = null;
            throw new a(e);
        } catch (Throwable th4) {
            randomAccessFile = null;
            th = th4;
            fileChannel = null;
            if (randomAccessFile != null) {
            }
            if (fileChannel != null) {
            }
            throw th;
        }
    }

    private void b(File file) {
        if (file.exists()) {
            file.delete();
        }
    }

    private void b(String str, byte[] bArr) {
        if (bArr.length != 0) {
            File file = new File(this.b, str);
            File file2 = this.c;
            File file3 = new File(file2, str + ".bak");
            a(file, file3);
            a(file, bArr);
            b(file3);
            return;
        }
        throw new a(String.format("%s key's value is zero bytes for saving", str));
    }

    private String[] b() {
        String[] list = this.b.list();
        return list == null ? a : list;
    }

    private byte[] c(String str) {
        File file = this.c;
        File file2 = new File(file, str + ".bak");
        File file3 = new File(this.b, str);
        if (file2.exists()) {
            b(file3);
            a(file2, file3);
        }
        return a(file3);
    }

    private void d(String str) {
        try {
            File file = new File(this.b, str);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            throw new a(e);
        }
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.a.a
    public void a(String str, byte[] bArr) {
        b(str, bArr);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.a.a
    public byte[] a(String str) {
        return c(str);
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.a.a
    public String[] a() {
        return b();
    }

    @Override // com.pushwoosh.thirdpart.com.ironz.binaryprefs.e.a.a
    public void b(String str) {
        d(str);
    }
}
