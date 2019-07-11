package com.lmf.appmovies.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.MenuItem;

public class AppUtils {

    public static void tintMenuIcon(Context context, MenuItem item, @ColorRes int color) {
        Drawable itemIcon = item.getIcon();
        Drawable iconWrapper = DrawableCompat.wrap(itemIcon);
        DrawableCompat.setTint(iconWrapper, context.getResources().getColor(color));

        item.setIcon(iconWrapper);
    }
}
