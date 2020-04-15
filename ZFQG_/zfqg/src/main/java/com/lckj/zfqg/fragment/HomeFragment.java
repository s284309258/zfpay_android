package com.lckj.zfqg.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gongwen.marqueen.MarqueeView;
import com.gongwen.marqueen.util.OnItemClickListener;
import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.network.HttpResponse;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseFragment;
import com.lckj.jycm.R;
import com.lckj.jycm.network.TokenRequest;
import com.lckj.jycm.zfqg_network.GetAppImgListBean;
import com.lckj.jycm.zfqg_network.GetAppImgListRequest;
import com.lckj.jycm.zfqg_network.GetNewNewsBean;
import com.lckj.jycm.zfqg_network.GetNewNoticeBean;
import com.lckj.jycm.zfqg_network.GetUnReadNewsBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.UpdateNewsReadFlagRequest;
import com.lckj.zfqg.activity.AfficheDetailsActivity;
import com.lckj.zfqg.activity.ApparatusManagerActivity;
import com.lckj.zfqg.activity.CoffersSchoolActivity;
import com.lckj.zfqg.activity.HomeMPOSActivity;
import com.lckj.zfqg.activity.MaterialActivity;
import com.lckj.zfqg.activity.OnlineActivity;
import com.lckj.zfqg.activity.RateApplyActivity;
import com.lckj.zfqg.activity.StoreQueryActivity;
import com.lckj.zfqg.adapter.ComplexViewMF;
import com.lckj.zfqg.adapter.HomeAdapter;
import com.lckj.zfqg.widget.PushCenterDialog;
import com.lckj.zfqg.widget.SelectDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_pos)
    TextView tvPos;
    @BindView(R.id.tv_mpos)
    TextView tvMpos;
    @BindView(R.id.tv_activity)
    TextView tvActivity;
    @BindView(R.id.tv_manager)
    TextView tvManager;
    @BindView(R.id.tv_rate_apply)
    TextView tvRateApply;
    @BindView(R.id.tv_push_center)
    TextView tvPushCenter;
    @BindView(R.id.tv_store_query)
    TextView tvStoreQuery;
    @BindView(R.id.tv_college)
    TextView tvCollege;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.rl_news)
    RelativeLayout rlNews;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.tv_rate_apply_red)
    TextView tvRateApplyRed;
    @BindView(R.id.tv_college_red)
    TextView tvCollegeRed;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_push_center_red)
    TextView tvPushCenterRed;
    private Unbinder mBind;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private HomeAdapter mHomeAdapter;
    private PushCenterDialog mPushCenterDialog;
    List<String> mImgs = new ArrayList<>();
    private List<GetNewNoticeBean.DataBean.NoticeInfoListBean> mNotices = new ArrayList<>();
    private List<GetNewNewsBean.DataBean.NewsInfoListBean> mNews = new ArrayList<>();
    private ComplexViewMF mComplexViewMF;
    List<String> mImgs2 = new ArrayList<>();
    private SelectDialog mSelectDialog;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        View view = setContentView(R.layout.frgment_home);
        mBind = ButterKnife.bind(this, view);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    private void initEvents() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                initData();
            }
        });
    }

    @SuppressLint("CheckResult")
    private void initData() {
        ProgressDlgHelper.openDialog(getContext());
        mMyService.getAppImgList(new GetAppImgListRequest(dataManager.getToken(), "01"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetAppImgListBean>(this) {
                    @Override
                    public void onSuccessCall(GetAppImgListBean response) {
                        refreshLayout.finishRefresh();
                        mImgs.clear();
                        for (int i = 0; i < response.getData().getAppImgList().size(); i++) {
                            mImgs.add(response.getData().getAppImgList().get(i).getImg_url());
                        }
                        banner.setImages(mImgs);
                        banner.setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                com.lckj.lckjlib.imageloader.ImageLoader.loadImage(String.valueOf(path), imageView);
                            }
                        });
                        banner.start();
                    }

                    @Override
                    public void onFailedCall(GetAppImgListBean response) {
                        super.onFailedCall(response);
                        refreshLayout.finishRefresh();
                    }
                }, new ThrowableConsumer<Throwable>(this) {
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                        refreshLayout.finishRefresh();
                    }
                });


        mMyService.getNewNotice(new TokenRequest(dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetNewNoticeBean>(this) {
                    @Override
                    public void onSuccessCall(GetNewNoticeBean response) {
                        refreshLayout.finishRefresh();
                        mNotices.clear();
                        mNotices.addAll(response.getData().getNoticeInfoList());
                        mComplexViewMF.setData(mNotices);
                    }

                    @Override
                    public void onFailedCall(GetNewNoticeBean response) {
                        super.onFailedCall(response);
                        refreshLayout.finishRefresh();
                    }
                }, new ThrowableConsumer<Throwable>(this) {
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                        refreshLayout.finishRefresh();
                    }
                });

        mMyService.getNewNews(new TokenRequest(dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetNewNewsBean>(this) {
                    @Override
                    public void onSuccessCall(GetNewNewsBean response) {
                        refreshLayout.finishRefresh();
                        ProgressDlgHelper.closeDialog();
                        mNews.clear();
                        mNews.addAll(response.getData().getNewsInfoList());
                        mHomeAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailedCall(GetNewNewsBean response) {
                        super.onFailedCall(response);
                        refreshLayout.finishRefresh();
                    }
                }, new ThrowableConsumer<Throwable>(this) {
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                        refreshLayout.finishRefresh();
                    }
                });
        getData();
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getData();
        }
    }

    private void getData() {
        if (mMyService == null) return;
        mMyService.getUnReadNews(new TokenRequest(dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetUnReadNewsBean>(this) {
                    @Override
                    public void onSuccessCall(GetUnReadNewsBean response) {
                        MainApplication.getInstance().setUnReadNews(response.getData());
                        if (MainApplication.getInstance().getUnReadNews() != null) {
                            tvRateApplyRed.setVisibility(MainApplication.getInstance().getUnReadNews().getApplyRateFlag().equals("0") ? View.VISIBLE : View.GONE);
                            tvCollegeRed.setVisibility(MainApplication.getInstance().getUnReadNews().getCollegeFlag().equals("0") ? View.VISIBLE : View.GONE);
                            tvPushCenterRed.setVisibility(MainApplication.getInstance().getUnReadNews().getAppImgFlag().equals("0") ? View.VISIBLE : View.GONE);
                        }
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mHomeAdapter = new HomeAdapter(getContext(), mNews);
        recyclerView.setNestedScrollingEnabled(false);//禁止滑动
        recyclerView.setAdapter(mHomeAdapter);

        mComplexViewMF = new ComplexViewMF(getContext());
        mComplexViewMF.setData(mNotices);
        marqueeView.setMarqueeFactory(mComplexViewMF);
        marqueeView.startFlipping();
        marqueeView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(View mView, Object mData, int mPosition) {
                startActivity(new Intent(getContext(), AfficheDetailsActivity.class).putExtra("id", mNotices.get(mPosition).getNotice_id()));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }

    @OnClick({R.id.tv_pos, R.id.tv_mpos, R.id.tv_activity, R.id.tv_manager, R.id.tv_rate_apply, R.id.tv_push_center, R.id.tv_store_query, R.id.tv_college, R.id.rl_news})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_pos:
//                startActivity(new Intent(getContext(), HomePOSActivity.class));
                showSelect();
                break;
            case R.id.tv_mpos:
                startActivity(new Intent(getContext(), HomeMPOSActivity.class));
                break;
            case R.id.tv_activity:
                startActivity(new Intent(getContext(), OnlineActivity.class));
                break;
            case R.id.tv_manager:
                startActivity(new Intent(getContext(), ApparatusManagerActivity.class));
                break;
            case R.id.tv_rate_apply:
                startActivity(new Intent(getContext(), RateApplyActivity.class));
                updateRed("applyRateFlag");
                break;
            case R.id.tv_push_center:
//                showDialog();
                startActivity(new Intent(getContext(), MaterialActivity.class));
                updateRed("appImgFlag");
                break;
            case R.id.tv_store_query:
                startActivity(new Intent(getContext(), StoreQueryActivity.class));
                break;
            case R.id.tv_college:
                startActivity(new Intent(getContext(), CoffersSchoolActivity.class));
                updateRed("collegeFlag");

                break;
            case R.id.rl_news:
                break;
        }
    }

    private void showSelect() {
        if (mSelectDialog == null)
            mSelectDialog = new SelectDialog(getActivity(), R.style.dialog);
        mSelectDialog.show();
    }

    private void updateRed(String type) {
        mMyService.updateNewsReadFlag(new UpdateNewsReadFlagRequest(dataManager.getToken(), type, "1"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        if (MainApplication.getInstance().getUnReadNews() != null) {
                            if (type.equals("collegeFlag")) {
                                MainApplication.getInstance().getUnReadNews().setCollegeFlag("1");
                                tvCollegeRed.setVisibility(View.GONE);
                            } else if (type.equals("applyRateFlag")){
                                MainApplication.getInstance().getUnReadNews().setApplyRateFlag("1");
                                tvRateApplyRed.setVisibility(View.GONE);
                            }else if (type.equals("appImgFlag")){
                                MainApplication.getInstance().getUnReadNews().setAppImgFlag("1");
                                tvPushCenterRed.setVisibility(View.GONE);
                            }
                        }
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    private void showDialog() {
        if (mPushCenterDialog == null) {
            mPushCenterDialog = new PushCenterDialog(getContext(), R.style.BottomDialog2);
        }
        mPushCenterDialog.show(mImgs2,"");
    }
}
