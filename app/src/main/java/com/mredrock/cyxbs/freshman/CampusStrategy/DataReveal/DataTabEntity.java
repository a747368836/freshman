package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal;

import com.flyco.tablayout.listener.CustomTabEntity;

public class DataTabEntity implements CustomTabEntity {
    public String title;

    public DataTabEntity(String title) {
        this.title = title;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }
}
