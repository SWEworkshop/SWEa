package com.pushwoosh.internal.network;

import android.os.AsyncTask;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.repository.RegistrationPrefs;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class e implements RequestManager {
    private final RegistrationPrefs a;
    private String b;

    /* access modifiers changed from: private */
    public static class a {
        private int a;
        private int b;
        private JSONObject c;

        a(int i, int i2, JSONObject jSONObject) {
            this.b = i;
            this.a = i2;
            this.c = jSONObject;
        }

        /* access modifiers changed from: package-private */
        public int a() {
            return this.b;
        }

        /* access modifiers changed from: package-private */
        public int b() {
            return this.a;
        }

        /* access modifiers changed from: package-private */
        public JSONObject c() {
            return this.c;
        }
    }

    /* access modifiers changed from: private */
    public static class b<Response> extends AsyncTask<Void, Void, Result<Response, NetworkException>> {
        private final WeakReference<e> a;
        private final PushRequest<Response> b;
        private final String c;
        private final Callback<Response, NetworkException> d;

        b(e eVar, PushRequest<Response> pushRequest, String str, Callback<Response, NetworkException> callback) {
            this.a = new WeakReference<>(eVar);
            this.b = pushRequest;
            this.c = str;
            this.d = callback;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Result<Response, NetworkException> doInBackground(Void... voidArr) {
            if (this.a.get() != null) {
                return this.a.get().a(this.b, this.c);
            }
            return null;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Result<Response, NetworkException> result) {
            Callback<Response, NetworkException> callback;
            super.onPostExecute(result);
            if (result != null && (callback = this.d) != null) {
                callback.process(result);
            }
        }
    }

    e(RegistrationPrefs registrationPrefs) {
        this.a = registrationPrefs;
        this.b = registrationPrefs.baseUrl().get();
        if (Build.VERSION.SDK_INT <= 19) {
            try {
                HttpsURLConnection.setDefaultSSLSocketFactory(new h());
            } catch (Exception e) {
                PWLog.error("RequestManager", "ERROR: " + e.getMessage(), e);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private <Response> Result<Response, NetworkException> a(PushRequest<Response> pushRequest, String str) {
        NetworkException networkException;
        if (str == null) {
            str = this.b;
        }
        if (!a(pushRequest)) {
            PWLog.debug("RequestManager", "Try To send: " + pushRequest.getMethod() + "; baseUrl: " + str);
        }
        try {
            a a2 = a(str, pushRequest.b(), pushRequest.getMethod(), a(pushRequest));
            if (200 == a2.a() && 200 == a2.b()) {
                if (!a(pushRequest)) {
                    PWLog.debug("RequestManager", pushRequest.getMethod() + " response success");
                }
                JSONObject c = a2.c();
                if (c.has("base_url") && str.equals(this.b)) {
                    a(c.optString("base_url"));
                }
                JSONObject optJSONObject = c.optJSONObject("response");
                if (optJSONObject == null) {
                    optJSONObject = new JSONObject();
                }
                return Result.fromData(pushRequest.parseResponse(optJSONObject));
            }
            networkException = new NetworkException(a2.c().toString());
            if (!a(pushRequest)) {
                PWLog.error("RequestManager", "ERROR: " + networkException.getMessage(), networkException);
            }
            return Result.fromException(networkException);
        } catch (Exception e) {
            networkException = new b(e.getMessage());
        }
    }

    /* JADX INFO: finally extract failed */
    private a a(String str, JSONObject jSONObject, String str2, boolean z) throws Exception {
        try {
            URL url = new URL(str + str2);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            httpURLConnection.setDoOutput(true);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("request", jSONObject);
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(jSONObject2.toString().getBytes().length));
            httpURLConnection.setUseCaches(false);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            try {
                outputStream.write(jSONObject2.toString().getBytes());
                outputStream.flush();
                outputStream.close();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = bufferedInputStream.read(bArr);
                            if (read < 0) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, read);
                        }
                        String trim = new String(byteArrayOutputStream.toByteArray()).trim();
                        byteArrayOutputStream.close();
                        if (!z) {
                            PWLog.info("RequestManager", "\nx\n|     Pushwoosh request:\n| Url: " + url.toString() + "\n| Payload: " + jSONObject2.toString() + "\n| Response: " + trim + "\nx");
                        }
                        JSONObject jSONObject3 = new JSONObject(trim);
                        return new a(httpURLConnection.getResponseCode(), jSONObject3.getInt("status_code"), jSONObject3);
                    } catch (Throwable th) {
                        byteArrayOutputStream.close();
                        throw th;
                    }
                } finally {
                    bufferedInputStream.close();
                }
            } catch (Throwable th2) {
                outputStream.close();
                throw th2;
            }
        } catch (Exception e) {
            if (str.equals(this.b)) {
                this.b = this.a.getDefaultBaseUrl();
            }
            throw e;
        }
    }

    private void a(String str) {
        this.b = str;
        this.a.baseUrl().set(str);
    }

    private boolean a() {
        boolean z = this.a.removeAllDeviceData().get();
        if (z) {
            PWLog.noise("RequestManager", "remove all data device is true, it is block request to server");
        }
        return z;
    }

    private <Response> boolean a(PushRequest<Response> pushRequest) {
        return pushRequest instanceof g;
    }

    @Override // com.pushwoosh.internal.network.RequestManager
    public <Response> void sendRequest(PushRequest<Response> pushRequest) {
        if (!a()) {
            sendRequest(pushRequest, null);
        }
    }

    @Override // com.pushwoosh.internal.network.RequestManager
    public <Response> void sendRequest(PushRequest<Response> pushRequest, @Nullable Callback<Response, NetworkException> callback) {
        sendRequest(pushRequest, this.b, callback);
    }

    @Override // com.pushwoosh.internal.network.RequestManager
    public <Response> void sendRequest(PushRequest<Response> pushRequest, String str, Callback<Response, NetworkException> callback) {
        if (!a()) {
            new b(this, pushRequest, str, callback).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        } else if (callback != null) {
            callback.process(Result.fromException(new NetworkException("Device data was removed from Pushwoosh and all interactions were stopped")));
        }
    }

    @Override // com.pushwoosh.internal.network.RequestManager
    @NonNull
    public <Response> Result<Response, NetworkException> sendRequestSync(PushRequest<Response> pushRequest) {
        return a() ? Result.fromData(null) : a(pushRequest, this.b);
    }

    @Override // com.pushwoosh.internal.network.RequestManager
    public void updateBaseUrl(String str) {
        a(str);
    }
}
