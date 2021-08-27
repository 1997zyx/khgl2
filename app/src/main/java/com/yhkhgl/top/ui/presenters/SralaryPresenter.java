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

public class SralaryPresenter extends BasePresenter<MainView> {
    public SralaryPresenter(MainView baseView) {
        super(baseView);
    }

    public void getList(HashMap<String, String> params) {
        final ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.employee_compensation_list(params), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.onTableListSuccess(o);
            }

            @Override
            public void onError(String msg) {
                if (baseView != null) {
                    baseView.showError(msg);
                }
            }
        });
    }

    public void getListDetail(HashMap params) {
        final ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.employee_compensation(params), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.onTableListSuccess(o);
            }

            @Override
            public void onError(String msg) {
                if (baseView != null) {
                    baseView.showError(msg);
                }
            }
        });
    }

    public void getdate() {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", App.token);
        final ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.service_date_list(map), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.onCheShiSuccess(o);
            }

            @Override
            public void onError(String msg) {
                if (baseView != null) {
                    baseView.showError(msg);
                }
            }
        });
    }
    public void getyjdate() {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", App.token);
        final ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.commission_date_list(map), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.onCheShiSuccess(o);
            }

            @Override
            public void onError(String msg) {
                if (baseView != null) {
                    baseView.showError(msg);
                }
            }
        });
    }
    public void getDetaildate(String s) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", App.token);
        map.put("service_user_id", s);
        final ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.staff_date_list(map), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.onCheShiSuccess(o);
            }

            @Override
            public void onError(String msg) {
                if (baseView != null) {
                    baseView.showError(msg);
                }
            }
        });
    }


    public void getDetail(HashMap map) {
        final ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.commission_manage(map), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.onSuccess(o, 0);
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
