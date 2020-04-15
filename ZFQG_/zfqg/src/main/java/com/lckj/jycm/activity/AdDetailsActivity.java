package com.lckj.jycm.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.network.MerchantListBean;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.widget.ObservableScrollView;
import com.lckj.lckjlib.map.MapFrameLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.amap.api.maps.AMap.MAP_TYPE_NORMAL;

public class AdDetailsActivity extends BaseActvity implements ObservableScrollView.OnObservableScrollViewListener {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.tv_vocation)
    TextView tvVocation;
    @BindView(R.id.tv_size)
    TextView tvSize;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_details)
    TextView tvDetails;
    @BindView(R.id.tv_map)
    TextView tvMap;
    @BindView(R.id.map_view)
    MapView mapView;
    @BindView(R.id.map_view_frame)
    MapFrameLayout mapViewFrame;
    @BindView(R.id.scroll_view)
    ObservableScrollView scrollView;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ll_head)
    LinearLayout llHead;
    @BindView(R.id.tv_shopping_trolley)
    TextView tvShoppingTrolley;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.btn_investment)
    Button btnInvestment;
    @BindView(R.id.btn_add_plan)
    Button btnAddPlan;
    @BindView(R.id.rl_bottom_menu)
    RelativeLayout rlBottomMenu;
    private int mHeight;
    private MerchantListBean.DataBean.ListBean data;
    private AMap aMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentParams();
        setContentView(R.layout.activity_ad_details);
        ButterKnife.bind(this);
        initView();
        mapView.onCreate(savedInstanceState);
    }

    private void getIntentParams() {
        Intent intent = getIntent();
        Serializable data = intent.getSerializableExtra("data");
        if (data instanceof MerchantListBean.DataBean.ListBean) {
            this.data = (MerchantListBean.DataBean.ListBean) data;
        }
    }

    @Override
    protected void initView() {
        tvDetails.setSelected(true);
        //获取标题栏高度
        ViewTreeObserver viewTreeObserver = banner.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                banner.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mHeight = banner.getHeight() - llHead.getHeight();//这里取的高度应该为图片的高度-标题栏
                //注册滑动监听
                scrollView.setOnObservableScrollViewListener(AdDetailsActivity.this);
            }
        });
        tvName.setText(data.getMerName());
        tvLocation.setText(data.getProCity()+data.getLocaltion());
        tvDistance.setText(Utils.formatDistance(data.getDistance()));
        tvVocation.setText(getResources().getStringArray(R.array.industrys)[data.getIndustryIndex()]);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                com.lckj.lckjlib.imageloader.ImageLoader.loadImage(String.valueOf(path), imageView);
            }
        });
        banner.setImages(getImages());
        banner.setDelayTime(5000);
        banner.start();
        initMap();
    }

    private void initMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(Double.parseDouble(data.getLat()), Double.parseDouble(data.getLng())));
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_location));
        aMap.addMarker(markerOptions);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(20));
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(Double.parseDouble(data.getLat()), Double.parseDouble(data.getLng()))));
        aMap.setInfoWindowAdapter(new AMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                View inflate = LayoutInflater.from(AdDetailsActivity.this).inflate(R.layout.map_info_window, null);
                TextView text = inflate.findViewById(R.id.tv_text);
                text.setText(data.getLocaltion());
                return inflate;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    private List<?> getImages() {
        ArrayList<String> strings = new ArrayList<>();
        String showImg = data.getShowImg();
        String[] split = showImg.split(",");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            strings.add(s);
        }
        return strings;
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onObservableScrollViewListener(int l, int t, int oldl, int oldt) {
        if (t <= 0) {
            //顶部图处于最顶部，标题栏透明
            llHead.setBackgroundColor(Color.argb(0, 48, 63, 159));
        } else if (t > 0 && t < mHeight) {
            //滑动过程中，渐变
            float scale = (float) t / mHeight;//算出滑动距离比例
            float alpha = (255 * scale);//得到透明度
            llHead.setBackgroundColor(Color.argb((int) alpha, 255, 174, 46));
        } else {
            //过顶部图区域，标题栏定色
            llHead.setBackgroundColor(Color.argb(255, 255, 174, 46));
        }
    }

    @OnClick({R.id.tv_details, R.id.tv_map, R.id.iv_back, R.id.tv_shopping_trolley, R.id.btn_investment, R.id.btn_add_plan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_details:
                mapViewFrame.setVisibility(View.INVISIBLE);
                tvDetails.setSelected(true);
                tvMap.setSelected(false);
                break;
            case R.id.tv_map:
                mapViewFrame.setVisibility(View.VISIBLE);
                tvDetails.setSelected(false);
                tvMap.setSelected(true);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_shopping_trolley:
                startActivity(new Intent(this, ShoppingTrolleyActivity.class));
                break;
            case R.id.btn_investment:
                break;
            case R.id.btn_add_plan:
                break;
        }
    }
}
