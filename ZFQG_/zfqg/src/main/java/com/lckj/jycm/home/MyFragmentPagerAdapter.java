package com.lckj.jycm.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager fragmetnmanager;  //创建FragmentManager
    private List<Fragment> listfragment; //创建一个List<Fragment>

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fragmetnmanager = fm;
        this.listfragment = list;
    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return listfragment.get(arg0); //返回第几个fragment
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //page都不会被销毁
//            super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listfragment.size(); //总共有多少个fragment
    }
}
