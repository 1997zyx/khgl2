package com.yhkhgl.top.ui;



import com.yhkhgl.top.activity.MainBean;
import com.yhkhgl.top.base.mvp.BaseModel;
import com.yhkhgl.top.base.mvp.BaseView;
import com.yhkhgl.top.bean.BaseCallBackBean;

import java.util.List;

/**
 * File descripition:
 *
 * @author lp
 * @date 2018/6/19
 */

public interface ContrastView extends BaseView {
    void onMainSuccess(BaseModel<List<MainBean>> o);
    void onTextSuccess(BaseModel<BaseCallBackBean> o);
    void onUpLoadImgSuccess(BaseModel<Object> o);
    void onTableListSuccess(BaseModel<Object> o);
    void onSuccess(BaseModel<Object> o, int i);
    void onPk(BaseModel<Object> o, int i);

    void onRestrictionsSuccess(BaseModel<Object> o);
    void onCheShiSuccess(BaseModel<Object> o);
    void onZanPinglunSuccess(BaseModel<Object> o, int position);
}
