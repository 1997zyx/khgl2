package com.yhkhgl.top.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;
import com.yhkhgl.top.App;
import com.yhkhgl.top.base.mvp.BaseModel;
import com.yhkhgl.top.base.mvp.BasePresenter;
import com.yhkhgl.top.base.mvp.BaseView;
import com.yhkhgl.top.utils.GsonFaultCreator;
import com.yhkhgl.top.utils.lightStatusBarUtils.IsStatusBarCanChangeTextColor;
import com.yhkhgl.top.view.LoadingDialog;

import static com.yhkhgl.top.utils.sunUi.SunUiUtil.fixLayout;


/**
 * File descripition:   ftagment 基类
 *
 * @author lp
 * @date 2018/6/19
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    private static long lastClickTime;
    public View view;
    public Gson gson = GsonFaultCreator.createFaultGsonObject().create();
    public Context mContext;
    protected P mPresenter;
    private LoadingDialog loadingDialog;
    protected abstract P createPresenter();

    protected boolean isViewInitiated;//判断视图是否初始化
    protected boolean isVisibleToUser;//用户是否可见
    protected boolean isDataInitiated;//数据是否初始化


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        isViewInitiated= true;
        mContext = getActivity();
        mPresenter = createPresenter();
        IsStatusBarCanChangeTextColor.setStatusBarTextIsDark(this, true); // 状态栏文字颜色是否显示黑色
        fixLayout(this);
        this.initToolbar(savedInstanceState);
        this.initView();
        prepareFetchData();
        return view;
    }
    //在所有生命周期函数之前调用，查看是否可见
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }


    //准备加载数据，这里不是强制刷新
    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    /**
     * 获取布局ID
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 处理顶部title
     *
     * @param savedInstanceState
     */
    protected abstract void initToolbar(Bundle savedInstanceState);

    protected abstract void initView();
    /**
     * 数据初始化操作
     */
    protected abstract void initData();

    /**
     * 封装toast方法（自行定制实现）
     *
     * @param str
     */
    public void showToast(String str) {
        ToastUtils.show(str);
    }
    public void setLoadingDialog(String msg){
        loadingDialog=new LoadingDialog(mContext);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setMessage(msg);
        loadingDialog.show();
    }
    public void closeLoadingDialog(){
        if (loadingDialog!=null){
            loadingDialog.dismiss();
        }

    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void onErrorCode(BaseModel model) {
    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        dissMissDialog();
    }
    public void showLoadingDialog() {
        showLoadingDialog("加载中...");
    }
    /**
     * 消失  黑框...
     */
    public void dissMissDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }
    /**
     * 加载  黑框...
     */
    public void showLoadingDialog(String msg) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(mContext);

        }
        loadingDialog.setMessage(msg);
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.view = null;
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }


    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
    public void fixTitlePadding(View titleView) {
        if (titleView == null)
            return;
        int paddingLeft = titleView.getPaddingLeft();
        int paddingTop = titleView.getPaddingTop();
        int paddingRight = titleView.getPaddingRight();
        int paddingBottom = titleView.getPaddingBottom();
        titleView.setPadding(paddingLeft, paddingTop + App.fixTitleBarHeight, paddingRight, paddingBottom);
    }
    //用户强制刷新的话，就应该是用户主动进行刷新了，当然也要去取数据了，用户第一嘛
    public boolean prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            this.initData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }

//        if (FileUtils.mkdir(mActivity)) {
//            rootPath = FileUtils.getRootPath(mActivity);
//
//    }

    /*
     * 防止短时间内多处点击
     * */
    public static boolean isFastDoubleClick() {

        long time = System.currentTimeMillis();

        if ( time - lastClickTime < 100) {

            return true;

        }

        lastClickTime = time;

        return false;

    }

}
