package com.example.messaging.ui.callback;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

public class TabClickListener implements TabLayout.OnTabSelectedListener{

    private final ViewPager pager;

    public TabClickListener(ViewPager pager) {
        this.pager = pager;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
