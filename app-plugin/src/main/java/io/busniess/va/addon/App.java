package io.busniess.va.addon;

import android.app.Application;
import android.content.Context;

import com.lody.virtual.DelegateApplicationExt;

import java.lang.reflect.Method;

/**
 * @author Lody
 */
public class App extends DelegateApplicationExt {
    Object commonApp = null;
    Class<?> commonAppClzz;

    @Override
    protected boolean fullExt() {
        return BuildConfig.fullExt;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if (fullExt()) {
            try {
                commonAppClzz = getClassLoader().loadClass("io.busniess.va.common.CommonApp");
                commonApp = commonAppClzz.newInstance();
                Method attachBaseContextMethod = commonAppClzz.getMethod("attachBaseContext", Context.class);
                attachBaseContextMethod.invoke(commonApp, base);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (fullExt()) {
            try {
                Method onCreateMethod = commonAppClzz.getMethod("onCreate", Application.class);
                onCreateMethod.invoke(commonApp, this);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected String getMainPackageName() {
        return BuildConfig.PACKAGE_NAME_MAIN;
    }

}
