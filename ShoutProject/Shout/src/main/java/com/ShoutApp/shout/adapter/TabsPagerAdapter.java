package com.ShoutApp.shout.adapter;

/**
 * Created by vishnu on 11/8/2013.
 */
import com.ShoutApp.shout.AlertsFragment;
import com.ShoutApp.shout.MyShoutsFragment;
import com.ShoutApp.shout.WallFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new WallFragment();
            case 1:
                // Games fragment activity
                return new MyShoutsFragment();
            case 2:
                // Movies fragment activity
                return new AlertsFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }

}