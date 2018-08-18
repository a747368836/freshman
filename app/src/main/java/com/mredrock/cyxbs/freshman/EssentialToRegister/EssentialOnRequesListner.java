package com.mredrock.cyxbs.freshman.EssentialToRegister;


import java.util.List;



public interface EssentialOnRequesListner {
    void OnSuccess(List<EssentialDataBean.DescribeBean> list);
    void OnError(String e);

}
