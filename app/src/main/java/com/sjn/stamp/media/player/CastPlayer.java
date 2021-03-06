package com.sjn.stamp.media.player;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.media.MediaBrowserServiceCompat;

import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.sjn.stamp.utils.LogHelper;
import com.sjn.stamp.utils.TvHelper;

public class CastPlayer {

    private static final String TAG = LogHelper.makeLogTag(CastPlayer.class);

    // Extra on MediaSession that contains the Cast device name currently connected to
    public static final String EXTRA_CONNECTED_CAST = "com.sjn.stamp.CAST_NAME";

    private Context mContext;
    private SessionManager mCastSessionManager;
    private SessionManagerListener mSessionManagerListener;

    public CastPlayer(MediaBrowserServiceCompat context, SessionManagerListener sessionManagerListener) {
        mContext = context;
        mSessionManagerListener = sessionManagerListener;

        if (!TvHelper.isTvUiMode(mContext)) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        mCastSessionManager = CastContext.getSharedInstance(mContext).getSessionManager();
                        mCastSessionManager.addSessionManagerListener(mSessionManagerListener, CastSession.class);
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void stop() {
        CastContext.getSharedInstance(mContext).getSessionManager().endCurrentSession(true);
    }

    public void finish() {
        if (mCastSessionManager != null) {
            mCastSessionManager.removeSessionManagerListener(mSessionManagerListener, CastSession.class);
        }
    }
}
