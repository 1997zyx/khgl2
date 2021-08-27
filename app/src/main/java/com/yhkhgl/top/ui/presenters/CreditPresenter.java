package com.yhkhgl.top.ui.presenters;

import com.yhkhgl.top.App;
import com.yhkhgl.top.activity.MainView;
import com.yhkhgl.top.api.ApiRetrofit;
import com.yhkhgl.top.api.ApiServer;
import com.yhkhgl.top.api.HttpUrl;
import com.yhkhgl.top.base.mvp.BaseModel;
import com.yhkhgl.top.base.mvp.BaseObserver;
import com.yhkhgl.top.base.mvp.BasePresenter;

import java.util.HashMap;

public class CreditPresenter extends BasePresenter<MainView> {
    public CreditPresenter(MainView baseView) {
        super(baseView);
    }
    public void getCity(){
        final HashMap<String, String> params = new HashMap<>();
        params.put("token", App.token);
        final ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.first_list(params), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.onSuccess((BaseModel<Object>) o,0);
            }

            @Override
            public void onError(String msg) {
                if (baseView != null) {
                    baseView.showError(msg);
                }
            }
        });
    }
    public void getQu(String city_id){
        final HashMap<String, String> params = new HashMap<>();
        params.put("token", App.token);
        params.put("city_id", city_id);
        final ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.second_list(params), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.onSuccess((BaseModel<Object>) o,1);
            }

            @Override
            public void onError(String msg) {
                if (baseView != null) {
                    baseView.showError(msg);
                }
            }
        });
    }
    public void getList(HashMap map){

        final ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.outlets_list_new(map), new BaseObserver(baseView) {
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
