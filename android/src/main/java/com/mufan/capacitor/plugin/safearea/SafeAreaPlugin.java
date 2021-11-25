package com.mufan.capacitor.plugin.safearea;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.WindowInsets;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "SafeArea")
public class SafeAreaPlugin extends Plugin {
    @PluginMethod
    public void getSafeArea(PluginCall call) {
        WindowInsets windowInsets = this.getBridge().getActivity().getWindow().getDecorView().getRootWindowInsets();

        if (windowInsets == null) {
            Log.i(SafeAreaPlugin.class.toString(), "WindowInsets is not available.");
            return;
        }

        DisplayCutout displayCutout = windowInsets.getDisplayCutout();

        if (displayCutout == null) {
            Log.i(SafeAreaPlugin.class.toString(), "DisplayCutout is not available.");
            return;
        }

        JSObject ret = new JSObject();

        ret.put("top", this.convertDimension(displayCutout.getSafeInsetTop()));
        ret.put("left", this.convertDimension(displayCutout.getSafeInsetLeft()));
        ret.put("bottom", this.convertDimension(displayCutout.getSafeInsetBottom()));
        ret.put("right", this.convertDimension(displayCutout.getSafeInsetRight()));

        call.resolve(ret);
    }

    @PluginMethod
    public void getStatusBarHeight(PluginCall call) {
        Activity activity = getActivity();

        float dimension = this.convertDimension(
                activity.getResources().getDimension(
                        activity.getResources().getIdentifier("status_bar_height", "dimen", "android")
                ));

        JSObject ret = new JSObject();

        ret.put("value", dimension);
        call.resolve(ret);
    }

    @PluginMethod
    public void getNavigationBarHeight(PluginCall call) {
        Activity activity = getActivity();

        float dimension = this.convertDimension(
                activity.getResources().getDimension(
                        activity.getResources().getIdentifier("navigation_bar_height", "dimen", "android")
                ));

        JSObject ret = new JSObject();

        ret.put("value", dimension);
        call.resolve(ret);
    }

    private int convertDimension(float dimension) {
        Context context = this.getContext();

        float density = context.getResources().getDisplayMetrics().density;

        float dp = dimension / density;

        return (int) Math.ceil(dp);
    }
}
