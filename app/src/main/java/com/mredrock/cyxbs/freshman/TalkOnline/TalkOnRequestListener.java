package com.mredrock.cyxbs.freshman.TalkOnline;

import java.util.List;

public interface TalkOnRequestListener {

    void OnSuccess(List<TalkBean.ArrayBean.Array1Bean> list);

    void OnError(String e);

}
