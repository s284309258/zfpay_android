package com.lckj.zfqg.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int mType;
    private int space;  //位移间距

    public SpaceItemDecoration(int space, int type) {
        this.space = space;
        mType = type;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        switch (mType) {
            case 0:
                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.top = space;
                    outRect.bottom = space;
                } else {
                    outRect.bottom = space;
                }
                break;
            case 1:
                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.bottom = space;
                } else {
                    outRect.top = space;
                    outRect.bottom = space;
                }
                break;
            case 2:
                if (parent.getChildAdapterPosition(view) % 4 == 0) {
                    outRect.left = 0;
                } else {
                    outRect.left = space;
                }
                if (parent.getChildAdapterPosition(view) >= 4) {
                    outRect.top = space;
                } else {
                    outRect.bottom = 0;
                }
                break;
            case 3:
                if (parent.getChildAdapterPosition(view) % 2 == 0) {
                    outRect.left = space;
                    outRect.right = space;
                    outRect.top = space;
                } else {
                    outRect.right = space;
                    outRect.top = space;
                }
                break;
        }
    }

}
