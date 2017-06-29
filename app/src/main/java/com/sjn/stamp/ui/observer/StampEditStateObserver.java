package com.sjn.stamp.ui.observer;

import com.sjn.stamp.utils.LogHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.experimental.Accessors;


public class StampEditStateObserver {

    public enum State {
        OPEN,
        CLOSE
    }

    private static final String TAG = LogHelper.makeLogTag(StampEditStateObserver.class);

    private static StampEditStateObserver sInstance;

    State mState = State.CLOSE;

    @Accessors(prefix = "m")
    @Getter
    List<String> mSelectedStampList = new ArrayList<>();

    private List<Listener> mListenerList = Collections.synchronizedList(new ArrayList<Listener>());

    public boolean isOpen() {
        return mState == State.OPEN;
    }

    public void notifyAllStampChange(String stamp) {
        LogHelper.i(TAG, "notifyAllStampChange ", mListenerList.size());
        if (mListenerList != null) {
            List<Listener> tempList = new ArrayList<>(mListenerList);
            for (Listener listener : tempList) {
                listener.onNewStampCreated(stamp);
            }
        }
    }

    public void notifySelectedStampListChange(List<String> stampList) {
        LogHelper.i(TAG, "notifySelectedStampListChange ", mListenerList.size());
        mSelectedStampList = stampList;
        if (mListenerList != null) {
            List<Listener> tempList = new ArrayList<>(mListenerList);
            for (Listener listener : tempList) {
                listener.onSelectedStampChange(mSelectedStampList);
            }
        }
    }

    public void notifyStateChange(State state) {
        LogHelper.i(TAG, "notifyStateChange ", state);
        mState = state;
        if (mListenerList != null) {
            List<Listener> tempList = new ArrayList<>(mListenerList);
            for (Listener listener : tempList) {
                listener.onStateChange(mState);
            }
        }
    }

    public interface Listener {
        void onSelectedStampChange(List<String> selectedStampList);

        void onNewStampCreated(String stamp);

        void onStateChange(State state);
    }

    public void addListener(Listener listener) {
        if (mListenerList.contains(listener)) {
            return;
        }
        mListenerList.add(listener);
    }

    public void removeListener(Listener listener) {
        if (!mListenerList.contains(listener)) {
            return;
        }
        mListenerList.remove(listener);
    }

    public static StampEditStateObserver getInstance() {
        if (sInstance == null) {
            sInstance = new StampEditStateObserver();
        }
        return sInstance;
    }

    private StampEditStateObserver() {
    }

}
