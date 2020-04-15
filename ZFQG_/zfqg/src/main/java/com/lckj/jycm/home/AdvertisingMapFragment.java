package com.lckj.jycm.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseFragment;
import com.lckj.jycm.R;
import com.lckj.jycm.activity.ShoppingTrolleyActivity;
import com.lckj.jycm.home.location.Location;
import com.lckj.jycm.network.InfoService;
import com.lckj.jycm.network.MerchantListBean;
import com.lckj.jycm.network.MerchantListRequest;
import com.lckj.jycm.widget.ListPopupWindow;
import com.lckj.utilslib.TextWatcherAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.db.DBManager;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.HotCity;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AdvertisingMapFragment extends BaseFragment {

    private static final int NEARBY_CODE = 0;
    private static final int VOCATION_CODE = 2;
    private static final int AREA_CODE = 1;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.iv_shopping_trolley)
    ImageView ivShoppingTrolley;
    @BindView(R.id.rl_shopping_trolley)
    RelativeLayout rlShoppingTrolley;
    @BindView(R.id.ll_nearby)
    LinearLayout llNearby;
    @BindView(R.id.ll_area)
    LinearLayout llArea;
    @BindView(R.id.ll_vocation)
    LinearLayout llVocation;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.ll_menu)
    LinearLayout llMenu;
    @BindView(R.id.tv_nearby)
    TextView tvNearby;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.iv_jiantou)
    ImageView ivJiantou;
    @BindView(R.id.tv_vocation)
    TextView tvVocation;
    @BindView(R.id.iv_jiantou2)
    ImageView ivJiantou2;
    @BindView(R.id.xx)
    View xx;
    private Unbinder mBind;
    private ArrayList<String> mDatas = new ArrayList<>();
    private ArrayList<HotCity> hotCities;
    private ArrayList<String> mAreaDatas;
    private ListPopupWindow mListPopupWindow;
    private HashMap<Integer, Integer> mMap = new HashMap<>();
    private ArrayList<String> mNearbyDatas;
    private ArrayList<String> mVocationDatas;
    @Inject
    InfoService infoService;
    @Inject
    DataManager dataManager;
    private String queryKey = "";
    private String industry = null;
    private String lat = "22.639906930319373";
    private String lng = "114.037762121531";
    private int page = 1;
    private String dis = "0.5";
    private ArrayList<MerchantListBean.DataBean.ListBean> listDatas = new ArrayList<>();
    private AdvertisingMapAdapter adapter;
    private DBManager dbManager;
    private ArrayList<City> areas = new ArrayList<>();
    private boolean isSearchRange = true;
    private String city;
    private String area;
    private String location = "定位中...";

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        View view = setContentView(R.layout.frg_advertising_map);
        mBind = ButterKnife.bind(this, view);
        MainApplication.getInstance().getInjectGraph().inject(this);
        initView();
        initData();
        location();

    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AdvertisingMapAdapter(getActivity(),listDatas);
        recyclerView.setAdapter(adapter);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                page++;
                getDatas();
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                page = 1;
                getDatas();
            }
        });
        initPopWindow();
        tvSearch.addTextChangedListener(new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                queryKey = s.toString();
            }
        });
        tvSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    page = 1;
                    getDatas();
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }
        });

    }

    private void initPopWindow() {
        mListPopupWindow = ListPopupWindow.getInstance(getActivity(), new ListPopupWindow.ItemOnClickListener() {
            @Override
            public void onClick(int position, String typeName, int code) {
                mMap.put(code, position);
                switch (code) {
                    case NEARBY_CODE:
                        tvNearby.setText(typeName);
                        dis = String.valueOf(getResources().getIntArray(R.array.distance)[position]/1000f);
                        page = 1;
                        isSearchRange = true;
                        tvArea.setText("地区");
                        tvLocation.setText(location);
                        getDatas();
                        break;
                    case AREA_CODE:
                        tvArea.setText(typeName);
                        if (mAreaDatas.size()==areas.size()+1){
                            City city = areas.get(position-1);
                            area = city.getName();
                        }
                        tvNearby.setText("附近");
                        isSearchRange = false;
                        page = 1;
                        getDatas();
                        break;
                    case VOCATION_CODE:
                        tvVocation.setText(typeName);
                        if (position==0) {
                            industry = null;
                        }else{
                            industry = String.valueOf(position+1);
                        }
                        page = 1;
                        getDatas();
                        break;
                }
            }
        });
    }

    private void initData() {
        hotCities = new ArrayList<>();
        mMap.put(NEARBY_CODE, 0);
        mMap.put(AREA_CODE, 0);
        mMap.put(VOCATION_CODE, 0);
        mAreaDatas = new ArrayList<>();
        mNearbyDatas = new ArrayList<>();
        mVocationDatas = new ArrayList<>();
        mNearbyDatas.add("附近");
        mNearbyDatas.addAll(Arrays.asList(getResources().getStringArray(R.array.distance_str)));
        mVocationDatas.add("全部");
        mVocationDatas.addAll(Arrays.asList(getResources().getStringArray(R.array.industrys)));
        ProgressDlgHelper.openDialog(getContext());
        if (dbManager==null) {
            dbManager = new DBManager(getActivity());
        }
        City city = new City("深圳","440300","","","114.085947","22.547");
        onPick(city);
        ProgressDlgHelper.closeDialog();
        tvArea.setText("全部");
        hotCities.add(new HotCity("北京", "", "101010100", "116.405285", "39.904989"));
        hotCities.add(new HotCity("上海", "", "101020100", "121.472644", "31.231706"));
        hotCities.add(new HotCity("广州", "", "101280101", "113.280637", "23.125178"));
        hotCities.add(new HotCity("深圳", "", "101280601", "114.085947", "22.547"));
        hotCities.add(new HotCity("杭州", "", "101210101", "120.153576", "30.287459"));
    }

    private void location() {
        new Location().startLocation(getContext(), new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        lng= String.valueOf(amapLocation.getLongitude());
                        lat = String.valueOf(amapLocation.getLatitude());
                        location = amapLocation.getCity();
                        getDatas();
                    } else {
                        Log.e("AmapError","location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                        getDatas();
                    }
                }

            }
        });
    }

    private void getDatas() {
        infoService.queryMerchantList(new MerchantListRequest(dataManager.getToken(),queryKey,industry
                ,!isSearchRange ?null:dis,!isSearchRange ?null:lat,!isSearchRange ?null:lng
                ,isSearchRange ?null:city,isSearchRange ?null:area
                ,page))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<MerchantListBean>(this) {
                    @Override
                    public void onSuccessCall(MerchantListBean response) {
                        refreshLayout.finishLoadMore();
                        refreshLayout.finishRefresh();
                        ProgressDlgHelper.closeDialog();
                        List<MerchantListBean.DataBean.ListBean> list = response.getData().getList();
                        if (page==1){
                            listDatas.clear();
                        }
                        listDatas.addAll(list);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailedCall(MerchantListBean response) {
                        super.onFailedCall(response);
                        refreshLayout.finishLoadMore();
                        refreshLayout.finishRefresh();
                    }
                }, new ThrowableConsumer<Throwable>(this){
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                        refreshLayout.finishLoadMore();
                        refreshLayout.finishRefresh();
                    }
                });

    }


    @OnClick({R.id.tv_location, R.id.rl_shopping_trolley, R.id.ll_nearby, R.id.ll_area, R.id.ll_vocation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_location:
                showCityPicker();
                break;
            case R.id.rl_shopping_trolley:
                startActivity(new Intent(getContext(), ShoppingTrolleyActivity.class));
                break;
            case R.id.ll_nearby:
                showPopWindow(NEARBY_CODE);
                break;
            case R.id.ll_area:
                showPopWindow(AREA_CODE);
                break;
            case R.id.ll_vocation:
                showPopWindow(VOCATION_CODE);
                break;
        }
    }


    private void showCityPicker() {
        CityPicker.from(this)
                .enableAnimation(true)
                .setLocatedCity(null)
                .setHotCities(hotCities)
                .setOnPickListener(new OnPickListener() {
                    @Override
                    public void onPick(int position, City data) {
                        AdvertisingMapFragment.this.onPick(data);
                    }

                    @Override
                    public void onCancel() {
                    }

                    @Override
                    public void onLocate() {
                        new Location().startLocation(getContext(), new AMapLocationListener() {
                            @Override
                            public void onLocationChanged(AMapLocation amapLocation) {
                                if (amapLocation != null) {
                                    if (amapLocation.getErrorCode() == 0) {
                                        CityPicker.from(AdvertisingMapFragment.this).locateComplete(new LocatedCity(amapLocation.getCity(), "", ""
                                                , String.valueOf(amapLocation.getLongitude()), String.valueOf(amapLocation.getLatitude())), LocateState.SUCCESS);
                                        location = amapLocation.getCity();
                                    } else {
                                        Log.e("AmapError","location Error, ErrCode:"
                                                + amapLocation.getErrorCode() + ", errInfo:"
                                                + amapLocation.getErrorInfo());
                                    }
                                }

                            }
                        });
                    }
                })
                .show();
    }

    private void onPick(City data) {
        tvLocation.setText(data.getName());
        ProgressDlgHelper.openDialog(getContext());
        if (dbManager==null) {
            dbManager = new DBManager(getActivity());
        }
        if (!TextUtils.isEmpty(data.getId())){
            areas = dbManager.getAreas(data.getId());
        }else{
            areas = dbManager.getAreasByName(data.getName());
        }
        mAreaDatas.clear();
        mAreaDatas.add(0,"全部");
        for (int i = 0; i < areas.size(); i++) {
            mAreaDatas.add(areas.get(i).getName());
        }
        mMap.put(AREA_CODE, 0);
        ProgressDlgHelper.closeDialog();
        tvArea.setText("全部");
        isSearchRange = false;
        city = data.getName();
        area =  null;
        getDatas();
    }

    private void showPopWindow(int code) {
        switch (code) {
            case NEARBY_CODE:
                mListPopupWindow.show(xx, mMap.get(NEARBY_CODE), mNearbyDatas, code);
                break;
            case AREA_CODE:
                mListPopupWindow.show(xx, mMap.get(AREA_CODE), mAreaDatas, code);
                break;
            case VOCATION_CODE:
                mListPopupWindow.show(xx, mMap.get(VOCATION_CODE), mVocationDatas, code);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }

}
