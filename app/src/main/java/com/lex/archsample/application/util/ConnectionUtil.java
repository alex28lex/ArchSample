package com.lex.archsample.application.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;


@SuppressWarnings("unused")
public final class ConnectionUtil {

    private ConnectionUtil() {
    }

    public static boolean isOnline(@NonNull Context context) {
        final ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        }

        final NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    public static void registerConnectionChangedReceiver(@NonNull Context context,
                                                         @NonNull BroadcastReceiver receiver) {
        context.registerReceiver(
                receiver,
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        );
    }

    public static void unregisterConnectionChangedReceiver(@NonNull Context context,
                                                           @NonNull BroadcastReceiver receiver) {
        context.unregisterReceiver(receiver);
    }
}
