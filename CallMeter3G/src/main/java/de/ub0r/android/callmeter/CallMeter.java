/*
 * Copyright (C) 2009-2013 Felix Bechstein
 * 
 * This file is part of Call Meter 3G.
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; If not, see <http://www.gnu.org/licenses/>.
 */
package de.ub0r.android.callmeter;

import com.actionbarsherlock.app.ActionBar;

import android.app.Application;
import android.content.res.Resources;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;

import de.ub0r.android.lib.Utils;

/**
 * @author flx
 */
public final class CallMeter extends Application {

    /**
     * Minimum date.
     */
    public static final long MIN_DATE = 10000000000L;

    /**
     * Milliseconds per seconds.
     */
    public static final long MILLIS = 1000L;

    /**
     * 80.
     */
    public static final int EIGHTY = 80;

    /**
     * 100.
     */
    public static final int HUNDRED = 100;

    /**
     * Seconds of a minute.
     */
    public static final int SECONDS_MINUTE = 60;

    /**
     * Seconds of a hour.
     */
    public static final int SECONDS_HOUR = 60 * SECONDS_MINUTE;

    /**
     * Seconds of a day.
     */
    public static final int SECONDS_DAY = 24 * SECONDS_HOUR;

    /**
     * 10.
     */
    public static final int TEN = 10;

    /**
     * Bytes: kB.
     */
    public static final long BYTE_KB = 1024L;

    /**
     * Bytes: MB.
     */
    public static final long BYTE_MB = BYTE_KB * BYTE_KB;

    /**
     * Bytes: GB.
     */
    public static final long BYTE_GB = BYTE_MB * BYTE_KB;

    /**
     * Bytes: TB.
     */
    public static final long BYTE_TB = BYTE_GB * BYTE_KB;

    @Override
    public void onCreate() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
            // fix AsyncTask for some old devices + broken gms
            // http://stackoverflow.com/a/27239869/2331953
            try {
                Class.forName("android.os.AsyncTask");
            } catch (Throwable ignore) {
            }
        }

        super.onCreate();
        Utils.setLocale(this);
    }

    /**
     * Fix ActionBar background. See http://b.android.com/15340.
     *
     * @param ab      {@link ActionBar}
     * @param r       {@link Resources}
     * @param bg      res id of background {@link BitmapDrawable}
     * @param bgSplit res id of background {@link BitmapDrawable} in split mode
     */
    public static void fixActionBarBackground(final ActionBar ab, final Resources r, final int bg,
            final int bgSplit) {
        // This is a workaround for http://b.android.com/15340 from
        // http://stackoverflow.com/a/5852198/132047
        BitmapDrawable d = (BitmapDrawable) r.getDrawable(bg);
        d.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
        ab.setBackgroundDrawable(d);
        if (bgSplit >= 0) {
            d = (BitmapDrawable) r.getDrawable(bgSplit);
            d.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
            ab.setSplitBackgroundDrawable(d);
        }
    }
}
