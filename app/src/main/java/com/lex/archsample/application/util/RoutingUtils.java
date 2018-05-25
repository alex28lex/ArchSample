package com.lex.archsample.application.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


@SuppressWarnings("unused")
public final class RoutingUtils {
    private static final String TAG = RoutingUtils.class.getSimpleName();
    private static final String BUNDLE = TAG + "_BUNDLE";

    private RoutingUtils() {
        // Empty
    }

    //region Activity
    public static void startActivity(@NonNull Activity fromActivity,
                                     @NonNull Class<? extends Activity> toActivityClass) {
        startActivity(fromActivity, toActivityClass, null, null, false);
    }

    public static void startActivity(@NonNull Activity fromActivity,
                                     @NonNull Class<? extends Activity> toActivityClass,
                                     @Nullable Integer activityFlags) {
        startActivity(fromActivity, toActivityClass, activityFlags, null, false);
    }

    public static void startActivity(@NonNull Activity fromActivity,
                                     @NonNull Class<? extends Activity> toActivityClass,
                                     @Nullable Bundle bundle) {
        startActivity(fromActivity, toActivityClass, null, bundle, false);
    }

    public static void startActivity(@NonNull Activity fromActivity,
                                     @NonNull Class<? extends Activity> toActivityClass,
                                     boolean finishFromActivity) {
        startActivity(fromActivity, toActivityClass, null, null, finishFromActivity);
    }

    public static void startActivity(@NonNull Activity fromActivity,
                                     @NonNull Class<? extends Activity> toActivityClass,
                                     @Nullable Integer activityFlags,
                                     @Nullable Bundle bundle,
                                     boolean finishFromActivity) {
        final Intent intent = new Intent(fromActivity, toActivityClass);

        if (activityFlags != null) {
            intent.setFlags(activityFlags);
        }

        if (bundle != null) {
            intent.putExtra(BUNDLE, bundle);
        }

        fromActivity.startActivity(intent);

        if (finishFromActivity) {
            fromActivity.finish();
        }
    }
    //endregion

    //region ActivityForResult
    public static void startForResult(@NonNull Activity fromActivity,
                                      @NonNull Class<? extends Activity> toActivityClass,
                                      int requestCode) {
        startForResult(fromActivity, toActivityClass, null, null, requestCode);
    }

    public static void startForResult(@NonNull Activity fromActivity,
                                      @NonNull Class<? extends Activity> toActivityClass,
                                      @Nullable Integer activityFlags,
                                      int requestCode) {
        startForResult(fromActivity, toActivityClass, activityFlags, null, requestCode);
    }

    public static void startForResult(@NonNull Activity fromActivity,
                                      @NonNull Class<? extends Activity> toActivityClass,
                                      @Nullable Bundle bundle,
                                      int requestCode) {
        startForResult(fromActivity, toActivityClass, null, bundle, requestCode);
    }

    public static void startForResult(@NonNull Activity fromActivity,
                                      @NonNull Class<? extends Activity> toActivityClass,
                                      @Nullable Integer activityFlags,
                                      @Nullable Bundle bundle,
                                      int requestCode) {
        final Intent intent = new Intent(fromActivity, toActivityClass);

        if (activityFlags != null) {
            intent.setFlags(activityFlags);
        }

        if (bundle != null) {
            intent.putExtra(BUNDLE, bundle);
        }

        fromActivity.startActivityForResult(intent, requestCode);
    }
    //endregion

    //region Fragment
    public static void showFragment(@NonNull AppCompatActivity activity,
                                    @IdRes int containerViewId,
                                    @NonNull Fragment fragment) {
        showFragment(activity, null, containerViewId, fragment, false);
    }

    public static void showFragment(@NonNull AppCompatActivity activity,
                                    @Nullable Bundle savedInstanceState,
                                    @IdRes int containerViewId,
                                    @NonNull Fragment fragment) {
        showFragment(activity, savedInstanceState, containerViewId, fragment, false);
    }

    public static void showFragment(@NonNull AppCompatActivity activity,
                                    @Nullable Bundle savedInstanceState,
                                    @IdRes int containerViewId,
                                    @NonNull Fragment fragment,
                                    boolean addToBackStack) {
        if (savedInstanceState != null) {
            // After recreation. No action is required.
            return;
        }

        final FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        final String fragmentTag = fragment.getClass().getName();

        ft.replace(containerViewId, fragment, fragmentTag);

        if (addToBackStack) {
            ft.addToBackStack(fragmentTag);
        }

        ft.commit();
    }
    //endregion
}
