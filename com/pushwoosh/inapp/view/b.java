package com.pushwoosh.inapp.view;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.function.Result;
import com.pushwoosh.inapp.view.a;
import java.lang.ref.WeakReference;

public class b extends Fragment implements a.AbstractC0008a {
    private AsyncTask<Void, Void, Result<com.pushwoosh.inapp.d.a, com.pushwoosh.inapp.b.a>> a;
    private WeakReference<a> b = new WeakReference<>(null);
    private EnumC0010b c = EnumC0010b.NONE;
    private com.pushwoosh.inapp.d.a d;
    private com.pushwoosh.inapp.b.a e;

    /* access modifiers changed from: package-private */
    public interface a {
        void a();

        void a(com.pushwoosh.inapp.b.a aVar);

        boolean a(com.pushwoosh.inapp.d.a aVar);

        void b();
    }

    /* renamed from: com.pushwoosh.inapp.view.b$b  reason: collision with other inner class name */
    public enum EnumC0010b {
        LOADING,
        SUCCESS,
        ERROR,
        NONE
    }

    public static b a(com.pushwoosh.inapp.e.b.b bVar) {
        b bVar2 = new b();
        Bundle bundle = new Bundle(1);
        bundle.putSerializable("keyInapp", bVar);
        bVar2.setArguments(bundle);
        return bVar2;
    }

    private void b(Result<com.pushwoosh.inapp.d.a, com.pushwoosh.inapp.b.a> result) {
        if (result.isSuccess()) {
            this.c = EnumC0010b.SUCCESS;
            this.d = result.getData();
            return;
        }
        this.c = EnumC0010b.ERROR;
        this.e = result.getException();
    }

    private void c(Result<com.pushwoosh.inapp.d.a, com.pushwoosh.inapp.b.a> result) {
        a aVar = this.b.get();
        if (aVar != null) {
            if (!result.isSuccess()) {
                aVar.b();
                aVar.a(result.getException());
            } else if (!aVar.a(result.getData())) {
                aVar.b();
            }
        }
    }

    @Override // com.pushwoosh.inapp.view.a.AbstractC0008a
    public void a() {
        this.c = EnumC0010b.LOADING;
        a aVar = this.b.get();
        if (aVar != null) {
            aVar.a();
        }
    }

    @Override // com.pushwoosh.inapp.view.a.AbstractC0008a
    public void a(Result<com.pushwoosh.inapp.d.a, com.pushwoosh.inapp.b.a> result) {
        b(result);
        c(result);
    }

    public void b() {
        if (getActivity() instanceof a) {
            this.b = new WeakReference<>((a) getActivity());
        }
        a aVar = this.b.get();
        if (aVar != null) {
            switch (this.c) {
                case SUCCESS:
                    aVar.b();
                    aVar.a(this.d);
                    return;
                case ERROR:
                    aVar.a(this.e);
                    aVar.b();
                    return;
                case LOADING:
                    aVar.a();
                    return;
                default:
                    if (getArguments() != null) {
                        b((com.pushwoosh.inapp.e.b.b) getArguments().getSerializable("keyInapp"));
                        return;
                    }
                    return;
            }
        }
    }

    public void b(com.pushwoosh.inapp.e.b.b bVar) {
        this.a = new a(bVar, this);
        this.a.execute(new Void[0]);
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        b();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        if (getArguments() != null) {
            com.pushwoosh.inapp.e.b.b bVar = (com.pushwoosh.inapp.e.b.b) getArguments().getSerializable("keyInapp");
            if (bundle == null) {
                b(bVar);
                return;
            }
            this.c = EnumC0010b.values()[bundle.getInt("[InApp]InAppFragment.key_STATE")];
            this.d = (com.pushwoosh.inapp.d.a) bundle.getSerializable("[InApp]InAppFragment.key_HTML_DATA");
            this.e = (com.pushwoosh.inapp.b.a) bundle.getSerializable("[InApp]InAppFragment.key_ERROR");
            if (this.c != EnumC0010b.SUCCESS && this.c != EnumC0010b.ERROR) {
                b(bVar);
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        AsyncTask<Void, Void, Result<com.pushwoosh.inapp.d.a, com.pushwoosh.inapp.b.a>> asyncTask = this.a;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.a = null;
        }
    }

    public void onDetach() {
        super.onDetach();
        this.b = null;
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("[InApp]InAppFragment.key_ERROR", this.e);
        bundle.putSerializable("[InApp]InAppFragment.key_HTML_DATA", this.d);
        bundle.putInt("[InApp]InAppFragment.key_STATE", this.c.ordinal());
    }
}
