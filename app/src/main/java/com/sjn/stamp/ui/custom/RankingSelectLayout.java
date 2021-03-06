package com.sjn.stamp.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.sjn.stamp.R;


public class RankingSelectLayout extends PeriodSelectLayout {

    public int getSongNum() {
        return mSongNum;
    }

    public void setSongNum(int songNum) {
        mSongNum = songNum;
    }

    private int mSongNum = 3;

    public RankingSelectLayout(Context context, AttributeSet attr, Period period) {
        super(context, attr, period);
        final Spinner spinnerSongNum = mLayout.findViewById(R.id.song_num_spinner);

        Integer[] songNumList = new Integer[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        };
        spinnerSongNum.setAdapter(createSpinnerAdapter(songNumList));
        spinnerSongNum.setSelection(findOr0(songNumList, mSongNum));
        spinnerSongNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mSongNum = Integer.valueOf(spinnerSongNum.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    int getResourceId(){
        return R.layout.layout_ranking_select;
    }

}