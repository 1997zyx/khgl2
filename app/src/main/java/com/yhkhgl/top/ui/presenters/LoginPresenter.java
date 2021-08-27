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

public class LoginPresenter extends BasePresenter<MainView> {

    public LoginPresenter(MainView baseView) {
        super(baseView);
    }
    public void SendSms( HashMap<String, String> params){
        final ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.send_sms(params), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.showError(o.getErrmsg());
            }

            @Override
            public void onError(String msg) {
                if (baseView != null) {
                    baseView.showError(msg);
                }
            }
        });
    }
    public void setReg( HashMap<String, String> params){
        final ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.reg(params), new BaseObserver(baseView) {
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

    public void getLogin( HashMap<String, String> params){
        final ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.login(params), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.onSuccess((BaseModel<Object>) o,5);
            }

            @Override
            public void onError(String msg) {
                if (baseView != null) {
                    baseView.showError(msg);
//                    Toast.makeText(App.getContext(),msg+"",Toast.LENGTH_SHORT).show();;
                }
            }
        });
    }
    public void setpassword( HashMap<String, String> params){
        final ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.retrieve_password(params), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.showError(o.getErrmsg());
            }

            @Override
            public void onError(String msg) {
                if (baseView != null) {
                    baseView.showError(msg);
                }
            }
        });
    }
    public void getInfo() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", App.token);
        ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.info(params), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {

                baseView.onTableListSuccess((BaseModel<Object>) o);
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
