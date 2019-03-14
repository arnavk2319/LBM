package com.single.arnavkaushal.book_client_app.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.single.arnavkaushal.book_client_app.Fragment.TabOne;
import com.single.arnavkaushal.book_client_app.Fragment.TabTwo;

public class Pager extends FragmentStatePagerAdapter {

    String[] tabTitles = new String[] {"Movies","Books"};

    public CharSequence getPageTitle(int position)
    {
        return tabTitles[position];

    }

    public Pager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                TabOne tabOne = new TabOne();
                return tabOne;
            case 1:
                TabTwo tabTwo = new TabTwo();
                return tabTwo;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
