package com.lckj.jycm.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseFragment;
import com.lckj.jycm.R;
import com.lckj.jycm.activity.PwLoginActivity;
import com.lckj.jycm.article.activity.MyArticleActivity;
import com.lckj.jycm.network.HomePageBean;
import com.lckj.jycm.network.InfoService;
import com.lckj.jycm.network.TokenRequest;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsFragment extends BaseFragment {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.tv_store)
    TextView tvStore;
    @BindView(R.id.ll_menu)
    LinearLayout llMenu;
    @BindView(R.id.tv_look)
    TextView tvLook;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Unbinder bind;
    private ArrayList<HomePageBean.DataBean.ListBean> mDatas = new ArrayList();

    @Inject
    DataManager dataManager;
    @Inject
    InfoService infoService;
    private int status = 2;
    private int type = 2;
    private NewsAdapter adapter;
    List<String> bannerImages = new ArrayList<>();

    @Nullable
    @Override
    public void onCreateView(@Nullable Bundle savedInstanceState) {
        View view = setContentView(R.layout.frg_news);
        bind = ButterKnife.bind(this, view);
        MainApplication.getInstance().getInjectGraph().inject(this);
        initView();
        ProgressDlgHelper.openDialog(getContext());
        getData();
    }

    public void getData() {
        infoService.showHomePage(new TokenRequest("showHomePage"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HomePageBean>(this) {
                    @Override
                    public void onSuccessCall(HomePageBean response) {
                        ProgressDlgHelper.closeDialog();
                        refreshLayout.finishRefresh();
                        mDatas.clear();
                        List<HomePageBean.DataBean.ListBean> list = response.getData().getList();
                        mDatas.addAll(list);
                        adapter.notifyDataSetChanged();
                        bannerImages.clear();
                        String homePageBanner = response.getData().getHomePageBanner();
                        String[] split = homePageBanner.split(",");
                        bannerImages.addAll(Arrays.asList(split));
                        banner.setImages(bannerImages);
                        banner.start();

                    }

                    @Override
                    public void onFailedCall(HomePageBean response) {
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
        /*infoService.showAdvArticleList(new AdvArticleListRequest(page, status, dataManager.getToken(), type))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<AdvArticleListBean>(this) {
                    @Override
                    public void onSuccessCall(AdvArticleListBean response) {
                        ProgressDlgHelper.closeDialog();
//                        finishLoad();
                        List<AdvArticleListBean.DataBean.ListBean> list = response.getData().getList();
//                        if (page == 1) mDatas.clear();
                        mDatas.addAll(list.subList(0, 3));
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailedCall(AdvArticleListBean response) {
                        super.onFailedCall(response);
//                        finishLoad();
                    }


                }, new ThrowableConsumer<Throwable>(this) {
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
//                        finishLoad();
                    }
                });*/

    }

    @Override
    protected void initView() {
        //动态设置banner高度
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        Float widthPixels = Float.valueOf(dm.widthPixels);
        ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
        layoutParams.height = (int) ((widthPixels / 750f) * 360f);
        banner.setLayoutParams(layoutParams);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setNestedScrollingEnabled(false);
        adapter = new NewsAdapter(mDatas);
        recyclerView.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getData();
            }
        });
        /*
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                page++;
                getData();
            }
        });*/
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                com.lckj.lckjlib.imageloader.ImageLoader.loadImage(String.valueOf(path), imageView);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    @OnClick({R.id.tv_share, R.id.tv_look})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_share:
                if (!TextUtils.isEmpty(dataManager.getToken())) {
                    HomeActivity activity = (HomeActivity) getActivity();
                    activity.changeFragment(2);
                    activity.setSelector(2);
                } else {
                    startActivity(new Intent(getActivity(), PwLoginActivity.class));
                }
                break;
            case R.id.tv_look:
                if (!TextUtils.isEmpty(dataManager.getToken())) {
                    Intent intent = new Intent(getActivity(), MyArticleActivity.class);
                    intent.putExtra("type", 1);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(getActivity(), PwLoginActivity.class));
                }
                break;
        }
    }
}
