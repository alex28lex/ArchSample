package com.lex.archsample.application.util;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static android.view.View.GONE;


@SuppressWarnings("CodeBlock2Expr")
public final class ViewUtils {

    private ViewUtils() {
        // Empty
    }

    public static void toggleEnableState(@NonNull ViewGroup rootViewGroup, boolean enabled) {
        for (int i = 0; i < rootViewGroup.getChildCount(); i++) {
            final View child = rootViewGroup.getChildAt(i);
            child.setEnabled(enabled);
            if (child instanceof ViewGroup) {
                toggleEnableState((ViewGroup) child, enabled);
            }
        }
    }

    //region GONE animation
    public static void goneAnimate(View view) {
        goneAnimate(view, false);
    }

    public static void goneAnimate(List<View> views) {
        for (View view : views) {
            goneAnimate(view, false);
        }
    }

    public static void goneAnimate(View view, boolean force) {
        if (force) {
            startGoneAnimation(view);
        } else if (view.getVisibility() != GONE) {
            startGoneAnimation(view);
        }
    }

    private static void startGoneAnimation(View view) {
        view.animate()
                .setDuration(view.getContext().getResources().getInteger(android.R.integer.config_shortAnimTime))
                .withStartAction(() -> {
                    view.setAlpha(1f);
                    view.setVisibility(View.VISIBLE);
                })
                .withEndAction(() -> {
                    view.setVisibility(GONE);
                })
                .alpha(0f)
                .start();
    }
    //endregion

    //region VISIBLE animation
    public static void visibleAnimate(View view) {
        visibleAnimate(view, false);
    }

    public static void visibleAnimate(List<View> views) {
        for (View view : views) {
            visibleAnimate(view, false);
        }
    }

    private static void visibleAnimate(View view, boolean force) {
        if (force) {
            startVisibleAnimation(view);
        } else if (view.getVisibility() != View.VISIBLE) {
            startVisibleAnimation(view);
        }
    }

    private static void startVisibleAnimation(View view) {
        view.animate()
                .setDuration(view.getContext().getResources().getInteger(android.R.integer.config_shortAnimTime))
                .withStartAction(() -> {
                    view.setAlpha(0f);
                    view.setVisibility(View.VISIBLE);
                })
                .withEndAction(() -> {
                    view.setVisibility(View.VISIBLE);
                })
                .alpha(1f)
                .start();
    }
    //endregion
}
