package com.yhkhgl.top.second2demo.activity;



import com.yhkhgl.top.second2demo.bean.Bean1;
import com.yhkhgl.top.second2demo.bean.Bean2;
import com.yhkhgl.top.second2demo.bean.Bean3;
import com.yhkhgl.top.second2demo.mvp.BaseModel;
import com.yhkhgl.top.second2demo.mvp.BaseView;

import java.util.List;

/**
 * File descripition:
 *
 * @author lp
 * @date 2018/6/19
 */

public interface SecondView extends BaseView {
    void onMainSuccess(BaseModel<List<Bean1>, Bean2, List<Bean3>> o);
    /*void onMainSuccess(BaseModel<List<MainBean>> o);

    void onUpLoadImgSuccess(BaseModel<Object> o);

    void onUpHeadImgSuccess(BaseModel<Object> o);

    void onExpressAddSuccess(BaseModel<Object> o);*/
}
