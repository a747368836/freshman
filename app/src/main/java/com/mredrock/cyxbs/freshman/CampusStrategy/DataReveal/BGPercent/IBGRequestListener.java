package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.BGPercent;

import java.util.List;

public interface IBGRequestListener {
    void OnSuccess(BGBean bean);
    void OnError(String e);
}
