package com.yhkhgl.top.ui.presenters;

import com.yhkhgl.top.activity.MainView;
import com.yhkhgl.top.api.ApiRetrofit;
import com.yhkhgl.top.api.ApiServer;
import com.yhkhgl.top.api.HttpUrl;
import com.yhkhgl.top.base.mvp.BaseModel;
import com.yhkhgl.top.base.mvp.BaseObserver;
import com.yhkhgl.top.base.mvp.BasePresenter;

import java.util.HashMap;

public class SearchPresent extends BasePresenter<MainView> {
    public SearchPresent(MainView baseView) {
        super(baseView);
    }
    public void SetSearch(HashMap map){
        final ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.search_data(map), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.onCheShiSuccess((BaseModel<Object>) o);
            }

            @Override
            public void onError(String msg) {

                if (baseView != null) {
                    baseView.showError(msg);

                }
            }
        });
    }
}
