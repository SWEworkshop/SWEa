package androidx.core.graphics;

import android.content.Context;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.provider.FontsContractCompat;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RequiresApi(21)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
    private static final String TAG = "TypefaceCompatApi21Impl";

    TypefaceCompatApi21Impl() {
    }

    private File getFile(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            String readlink = Os.readlink("/proc/self/fd/" + parcelFileDescriptor.getFd());
            if (OsConstants.S_ISREG(Os.stat(readlink).st_mode)) {
                return new File(readlink);
            }
            return null;
        } catch (ErrnoException unused) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0045, code lost:
        r5 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0046, code lost:
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004a, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004b, code lost:
        r8 = r5;
        r5 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x005d, code lost:
        r5 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x005e, code lost:
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0062, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0063, code lost:
        r7 = r5;
        r5 = r7;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x005d A[ExcHandler: all (th java.lang.Throwable)] */
    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontInfo(Context context, CancellationSignal cancellationSignal, @NonNull FontsContractCompat.FontInfo[] fontInfoArr, int i) {
        ParcelFileDescriptor openFileDescriptor;
        Throwable th;
        Throwable th2;
        FileInputStream fileInputStream;
        Throwable th3;
        Throwable th4;
        if (fontInfoArr.length < 1) {
            return null;
        }
        try {
            openFileDescriptor = context.getContentResolver().openFileDescriptor(findBestInfo(fontInfoArr, i).getUri(), "r", cancellationSignal);
            File file = getFile(openFileDescriptor);
            if (file != null) {
                if (file.canRead()) {
                    Typeface createFromFile = Typeface.createFromFile(file);
                    if (openFileDescriptor != null) {
                        openFileDescriptor.close();
                    }
                    return createFromFile;
                }
            }
            fileInputStream = new FileInputStream(openFileDescriptor.getFileDescriptor());
            Typeface createFromInputStream = super.createFromInputStream(context, fileInputStream);
            fileInputStream.close();
            if (openFileDescriptor != null) {
                openFileDescriptor.close();
            }
            return createFromInputStream;
        } catch (IOException unused) {
            return null;
        }
        throw th2;
        if (openFileDescriptor != null) {
            if (th != null) {
                try {
                    openFileDescriptor.close();
                } catch (Throwable th5) {
                    th.addSuppressed(th5);
                }
            } else {
                openFileDescriptor.close();
            }
        }
        throw th2;
        throw th4;
        if (th3 != null) {
            try {
                fileInputStream.close();
            } catch (Throwable th6) {
            }
        } else {
            fileInputStream.close();
        }
        throw th4;
    }
}
