package com.sotdogn.bldogkepd;


import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.StrictMode;

// import androidx.multidex.MultiDex;


import com.luciada.modids.Applicationclass;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class AppVC extends Applicationclass {

    private static AppVC myApp;
    public static Resources resource;

    public static final String TAG = Application.class.getSimpleName();
    private static AppVC mInstance;

    public static synchronized AppVC getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        resource=getResources();
        myApp = this;

        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .build()))
                .build());

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
     //   MultiDex.install(this);
    }

    public static AppVC getInstant()
    {
        return myApp;
    }

    public static Context getContext() {
        return myApp.getContext();
    }

}