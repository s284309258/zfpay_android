package com.lckj.zfqg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gongwen.marqueen.MarqueeFactory;
import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetNewNoticeBean;

//MarqueeFactory<T extends View, E>
//泛型T:指定ItemView的类型
//泛型E:指定ItemView填充的数据类型
public class ComplexViewMF extends MarqueeFactory<RelativeLayout, GetNewNoticeBean.DataBean.NoticeInfoListBean> {
    private LayoutInflater inflater;

    public ComplexViewMF(Context context) {
        super(context);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RelativeLayout generateMarqueeItemView(GetNewNoticeBean.DataBean.NoticeInfoListBean data) {
        RelativeLayout mView = (RelativeLayout) inflater.inflate(R.layout.item_affiche, null);
        ((TextView) mView.findViewById(R.id.tv_title)).setText(data.getNotice_title());
        ((TextView) mView.findViewById(R.id.tv_time)).setText(data.getCre_date());
        return mView;
    }


}
