package com.lckj.base;

import android.support.v4.app.Fragment;

import com.lckj.zfqg.fragment.EarningsFragment;
import com.lckj.zfqg.fragment.HomeFragment;
import com.lckj.zfqg.fragment.MyFragment;
import com.lckj.zfqg.fragment.ReportFragment;

import java.util.HashMap;
import java.util.Map;
public class FragmentFactory {
    private static FragmentFactory fragmentFactory;

    public static Map<Integer, Fragment> mMainMap = new HashMap<>();

    private FragmentFactory() {
    }

    public static FragmentFactory getInstanes() {
        if (fragmentFactory == null) {
            synchronized (FragmentFactory.class) {
                if (fragmentFactory == null) {
                    fragmentFactory =  new FragmentFactory();
                }
            }
        }
        return fragmentFactory;
    }

    /**
     * 存储Fragment
     *
     * @param position
     * @return Fragment
     */
    public Fragment createMainFragment(int position) {

        // 如果内存中有，那么就从内存中获取，否则就重新创建
        if (mMainMap.containsKey(position)) {
            return mMainMap.get(position);
        }

        Fragment fragment = null;
        switch (position) {
            case 0:// 云信页面
                fragment = new HomeFragment();
                break;
            case 1:// 云币页面
                fragment = new ReportFragment();
                break;
            case 2:// 云币页面
                fragment = new EarningsFragment();
                break;
            case 3:// 我的页面
                fragment = new MyFragment();
                break;
            default:
                fragment = new HomeFragment();
                break;
        }
        mMainMap.put(position, fragment);
        return fragment;
    }

    public void setNull() {
        mMainMap.clear();
    }
}
