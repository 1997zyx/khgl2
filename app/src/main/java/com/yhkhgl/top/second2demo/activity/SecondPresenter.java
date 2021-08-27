package com.yhkhgl.top.second2demo.activity;


import com.yhkhgl.top.second2demo.mvp.BasePresenter;

/**
 * File descripition:
 *
 * @author lp
 * @date 2018/6/19
 */

public class SecondPresenter extends BasePresenter<SecondView> {
    public SecondPresenter(SecondView baseView) {
        super(baseView);
    }

    /**
     * 写法好多种  怎么顺手怎么来
     */
    public void getManApi() {
//        addDisposable(apiServer.getMain2Demo("year"), new BaseObserver(baseView) {
//            @Override
//            public void onSuccess(BaseModel o) {
//                baseView.onMainSuccess((BaseModel<List<Bean1>, Bean2, List<Bean3>>) o);
//            }
//
//            @Override
//            public void onError(String msg) {
//                if (baseView != null) {
//                    baseView.showError(msg);
//                }
//            }
//        });
    }
}
