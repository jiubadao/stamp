package com.sjn.stamp.ui.activity;


import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;

import java.util.List;


public interface MediaBrowsable {
    MediaBrowserCompat getMediaBrowser();

    void search(String query, Bundle extras, MediaBrowserCompat.SearchCallback callback);

    void playByCategory(String mediaId);

    void onMediaItemSelected(String musicId);

    void onMediaItemSelected(String mediaId, boolean isPlayable, boolean isBrowsable);

    void onMediaItemSelected(MediaBrowserCompat.MediaItem item);

    void sendCustomAction(String action, Bundle extras, MediaBrowserCompat.CustomActionCallback callback);
}
