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

public class GuanLiPresent extends BasePresenter<MainView> {

    public GuanLiPresent(MainView baseView) {
        super(baseView);
    }
    public void getSaixuan() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", App.token);
        ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.condition_content(params), new BaseObserver(baseView) {
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
    public void getList(HashMap params){

        ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.customer_list(params), new BaseObserver(baseView) {
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
    public void getSeachList(HashMap params){

        ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.user_customization_search(params), new BaseObserver(baseView) {
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
    public void add_info(HashMap params){
        ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.customer_edit(params), new BaseObserver(baseView) {
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
    public void getDetail(HashMap params){
        ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.customer_detail(params), new BaseObserver(baseView) {
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
    public void getDetailList(HashMap params){
        ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.cus_follow_list(params), new BaseObserver(baseView) {
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
    public void getDetailListTwo(HashMap params){
        ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.user_customization_history(params), new BaseObserver(baseView) {
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
    public void addGenJin(HashMap params){
        ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.edit_follow(params), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onCheShiSuccess((BaseModel<Object>) o);
            }

            @Override
            public void onError(String msg) {
                baseView.hideLoading();
                if (baseView != null) {
                    baseView.showError(msg);
                }
            }
        });
    }
    public void setDetailcus(HashMap params,int i){
        ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.follow_del(params), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {

                baseView.onSuccess((BaseModel<Object>) o,i);
            }

            @Override
            public void onError(String msg) {

                if (baseView != null) {
                    baseView.showError(msg);
                }
            }
        });
    }
    public void getHuaShu(){
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("token",App.token);
        ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.speech_technique(hashMap), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {

                baseView.onRestrictionsSuccess((BaseModel<Object>) o);
            }

            @Override
            public void onError(String msg) {

                if (baseView != null) {
                    baseView.showError(msg);
                }
            }
        });
    }
    public void setHuaShu( HashMap map){

        ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.speech_technique_update(map), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {
                baseView.hideLoading();
                baseView.onUpLoadImgSuccess((BaseModel<Object>) o);
            }

            @Override
            public void onError(String msg) {

                if (baseView != null) {
                    baseView.hideLoading();
                    baseView.showError(msg);
                }
            }
        });
    }
    public void getListTwo(HashMap<String, String> params) {
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
    public void getDetailTwo(HashMap map) {
        final ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.commission_manage(map), new BaseObserver(baseView) {
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
    public void getInfo() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", App.token);
        ApiServer apiServer = ApiRetrofit.getBaseUrlInstance(HttpUrl.HOSTURL).getApiService();
        addDisposable(apiServer.info(params), new BaseObserver(baseView) {
            @Override
            public void onSuccess(BaseModel o) {

                baseView.onUpLoadImgSuccess((BaseModel<Object>) o);
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
