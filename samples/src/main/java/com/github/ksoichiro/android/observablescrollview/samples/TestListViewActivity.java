package com.github.ksoichiro.android.observablescrollview.samples;

import android.view.View;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;

public class TestListViewActivity extends FillGap2ListViewActivity {
    View mPaddingInList;
    View mMessageView;
    @Override
    protected ObservableListView createScrollable() {
        mMessageView = findViewById(R.id.message);

        final ObservableListView listView = (ObservableListView) findViewById(R.id.scroll);
        listView.setScrollViewCallbacks(this);

        mPaddingInList = new View(this);
        mPaddingInList.setMinimumHeight(mFlexibleSpaceImageHeight);
        listView.addHeaderView(mPaddingInList);

        ScrollUtils.addOnGlobalLayoutListener(listView, new Runnable() {
            @Override
            public void run() {
                mPaddingInList.setMinimumHeight(mFlexibleSpaceImageHeight + mHeader.getHeight());
            }
        });

        mHeaderBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMessageView.getVisibility() == View.GONE) {
                    mMessageView.setVisibility(View.VISIBLE);
                } else {
                    mMessageView.setVisibility(View.GONE);
                }
            }
        });

        setDummyData(listView);

        return listView;
    }

    // Returns Y direction translation of header
    @Override
    protected float getHeaderTranslationY(int scrollY) {
        // head should be just below the image.
        return ScrollUtils.getFloat(-scrollY + mFlexibleSpaceImageHeight, 0, Float.MAX_VALUE);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_testlistview;
    }
}
