package com.lex.archsample.application;

import android.util.Log;

import timber.log.Timber;


public final class TimberReleaseTree extends Timber.Tree {

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        switch (priority) {
            case Log.VERBOSE:
            case Log.DEBUG:
                // Ignoring
                break;
            case Log.INFO:
            case Log.WARN:
            case Log.ERROR:
            default:
                // For example: Crashlytics.logException(t);
                break;
        }
    }
}
