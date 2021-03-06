package com.yhkhgl.top.base;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.provider.Settings;
import android.transition.Fade;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;

import com.yhkhgl.top.App;
import com.yhkhgl.top.base.mvp.BaseModel;
import com.yhkhgl.top.base.mvp.BasePresenter;
import com.yhkhgl.top.base.mvp.BaseView;
import com.yhkhgl.top.utils.GsonFaultCreator;
import com.yhkhgl.top.utils.KeyBoardUtils;
import com.yhkhgl.top.utils.lightStatusBarUtils.IsStatusBarCanChangeTextColor;
import com.yhkhgl.top.view.LoadingDialog;
import com.yhkhgl.top.view.ProgressDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import static com.yhkhgl.top.utils.sunUi.SunUiUtil.fixLayout;

/**
 * File descripition: activity??????
 * <p>
 *
 * @author lp
 * @date 2018/5/16
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {
    // ???????????????????????????????????????????????????1000??????
    private static final int MIN_CLICK_DELAY_TIME = 500;
    private static long lastClickTime;
    public Gson gson = GsonFaultCreator.createFaultGsonObject().create();
    protected final String TAG = this.getClass().getSimpleName();
    public Context mContext;
    public P mPresenter;
    public String hint="??????????????????";
    protected abstract P createPresenter();

    private LoadingDialog loadingDialog;
    private ProgressDialog progressDialog;
    private View contentViewGroup;
    private String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private long downloadLength = 0;
    private long contentLength = 0;
    private Disposable downDisposable;
    TextView textView;
    //??????AMapLocationClientOption??????
    public static AMapLocationClientOption mLocationOption = null;
    //??????AMapLocationClient?????????
    public static AMapLocationClient mLocationClient = null;
    boolean iscuccess = false;
    private static volatile Activity mCurrentActivity;
    public static DecimalFormat df = new DecimalFormat("0.00");
    private static float sNoncompatDensity;
    private static float sNoncompatScaledDensity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCurrentActivity(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Fade().setDuration(200));
            getWindow().setExitTransition(new Fade().setDuration(200));
        }
        App.addActivity(this);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        IsStatusBarCanChangeTextColor.setStatusBarTextIsDark(this, true); // ???????????????????????????????????????
        mContext = this;
        setWindow(true);
        setCustomDensit(this);
//        getResources().getDisplayMetrics().density=1.0f*getScreenWidth(this)/1080;
        setContentView(getLayoutId());
        mPresenter = createPresenter();
        fixLayout(this);


        initView();
        this.initData();
    }

    public static Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    private void setCurrentActivity(Activity activity) {
        mCurrentActivity = activity;
    }

    protected void setWindow(boolean isTransalact) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int option = window.getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            window.getDecorView().setSystemUiVisibility(option);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * ????????????ID
     *
     * @return
     */
    protected abstract int getLayoutId();


    /**
     * ?????????????????????
     */
    protected abstract void initView();

    /**
     * ?????????????????????
     */
    protected abstract void initData();

    /**
     * ???????????????????????????
     */
    protected void setStatusBar() {
//        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);
    }

    /**
     * ??????toast??????????????????????????????
     *
     * @param str
     */
    public void showToast(String str) {
        ToastUtils.show(str);
    }

    public void showLongToast(String str) {
        ToastUtils.show(str);
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    /**
     * ??????????????????  ??????????????????  ?????????????????????????????????
     *
     * @param model
     */
    @Override
    public void onErrorCode(BaseModel model) {
        if (model.getErrcode() == 10000000) {
            //?????????????????????   ??????????????????????????????  ????????????????????????  ???super??????  ???????????????
//            App.put();
//            startActivity(LoginActivity.class);
        }
    }

    public void setLoadingDialog(String msg) {
        loadingDialog = new LoadingDialog(mContext);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setMessage(msg);
        loadingDialog.show();
    }

    public void closeLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }

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
        showLoadingDialog("?????????...");
    }

    /**
     * ??????  ??????...
     */
    public void showLoadingDialog(String msg) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
        loadingDialog.setMessage(msg);
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    /**
     * ??????  ??????...
     */
    public void dissMissDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityCompat.finishAfterTransition(this);
        }
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        App.removeActivity(this);

    }


    @Override
    public void showProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.getProgressBar().performAnimation();
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.getProgressBar().releaseAnimation();
            progressDialog.dismiss();
        }
    }

    @Override
    public void onProgress(int progress) {
        if (progressDialog != null) {
            progressDialog.updateProgress(progress);
        }
    }

    public Bundle getBundle() {
        Bundle b = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            b = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        }
        return b;
    }

    /**
     * [????????????]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(clz, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            startActivity(clz, null);
        }
//        startActivity(clz, null);
    }


    /**
     * [???????????????????????????]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            startActivity(intent);
        }
//        startActivity(intent);
    }

    /**
     * [??????Bundle??????Class??????????????????]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            startActivityForResult(intent, requestCode, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            startActivityForResult(intent, requestCode);
        }


    }

    /**
     * ?????????????????????????????????
     */

    /**
     * ??????editText?????????
     *
     * @param v   ????????????View
     * @param ids ?????????
     */
    public void clearViewFocus(View v, int... ids) {
        if (null != v && null != ids && ids.length > 0) {
            for (int id : ids) {
                if (v.getId() == id) {
                    v.clearFocus();
                    break;
                }
            }
        }
    }

    /**
     * ????????????
     *
     * @param v   ????????????View
     * @param ids ?????????
     * @return true???????????????edit???
     */
    public boolean isFocusEditText(View v, int... ids) {
        if (v instanceof EditText) {
            EditText et = (EditText) v;
            for (int id : ids) {
                if (et.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    //?????????????????????view??????,?????????????????????
    public boolean isTouchView(View[] views, MotionEvent ev) {
        if (views == null || views.length == 0) {
            return false;
        }
        int[] location = new int[2];
        for (View view : views) {
            view.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            if (ev.getX() > x && ev.getX() < (x + view.getWidth())
                    && ev.getY() > y && ev.getY() < (y + view.getHeight())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isTouchView(filterViewByIds(), ev)) {
                return super.dispatchTouchEvent(ev);
            }
            if (hideSoftByEditViewIds() == null || hideSoftByEditViewIds().length == 0) {
                return super.dispatchTouchEvent(ev);
            }
            View v = getCurrentFocus();
            if (isFocusEditText(v, hideSoftByEditViewIds())) {
                KeyBoardUtils.hideInputForce(this);
                clearViewFocus(v, hideSoftByEditViewIds());
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    /**
     * ??????EditText???Id
     * ???????????????EditText????????????
     *
     * @return id ??????
     */
    public int[] hideSoftByEditViewIds() {
        return null;
    }

    /**
     * ??????????????????View
     * ??????????????????????????????????????????????????????
     *
     * @return id ??????
     */
    public View[] filterViewByIds() {
        return null;
    }


    /**
     * ?????????????????????????????????title?????????????????????
     */
    public void fixTitlePadding(View titleView) {
        if (titleView == null)
            return;
        int paddingLeft = titleView.getPaddingLeft();
        int paddingTop = titleView.getPaddingTop();
        int paddingRight = titleView.getPaddingRight();
        int paddingBottom = titleView.getPaddingBottom();
        titleView.setPadding(paddingLeft, paddingTop + App.fixTitleBarHeight, paddingRight, paddingBottom);
//        Window window = getWindow();
//        int statusBarHeight = getStatusBarHeight(this.getBaseContext());
//        titleView.setPadding(0, statusBarHeight, 0, 0);
    }

    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * ???????????????
     */
    protected void setStatusBarFullTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {//21??????5.0
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {//19??????4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //?????????????????????
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /*????????????===============================================================================================*/
    /*

    @Override
    public int[] hideSoftByEditViewIds() {
        int[] ids = {R.id.et_company_name, R.id.et_address};
        return ids;
    }

    @Override
    public View[] filterViewByIds() {
        View[] views = {mEtCompanyName, mEtAddress};
        return views;
    }

    */
    public void upapk(TextView view, String url) {
        textView = view;
        int permission = ActivityCompat.checkSelfPermission(getApplication(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE,
                    1);
        } else {
            boolean haveInstallPermission = false;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                haveInstallPermission = getPackageManager().canRequestPackageInstalls();
            }
            if (!haveInstallPermission) {
                // ????????????????????????????????????
                Uri packageUri = Uri.parse("package:" + this.getPackageName());
                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageUri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(intent, 1);
            } else {
                down(url);
            }

        }
    }

    private void down(final String downloadUrl) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                downApk(downloadUrl, emitter);
            }
        }).subscribeOn(Schedulers.io())// ?????????????????????????????????
                .observeOn(AndroidSchedulers.mainThread())// ??????????????????????????????
                .subscribe(new Observer<Integer>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        downDisposable = d;
                    }

                    @Override
                    public void onNext(Integer result) {
                        //??????ProgressDialog ???????????????
//                        progressBar.setProgress(result);
                        textView.setText(result + "%");
                        if (result == 100) {
                            textView.setText("????????????");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplication(), "?????????????????????????????????", Toast.LENGTH_SHORT).show();
//                        upgrade.setEnabled(true);
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(getApplication(), "????????????????????????????????????", Toast.LENGTH_SHORT).show();
//                        upgrade.setEnabled(true);
                    }
                });
    }

    private void downApk(final String downloadUrl, final ObservableEmitter<Integer> emitter) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //????????????
                breakpoint(downloadUrl, emitter);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() == null) {
                    //????????????
                    breakpoint(downloadUrl, emitter);
                    return;
                }
                InputStream is = null;
                FileOutputStream fos = null;
                byte[] buff = new byte[2048];
                int len;
                try {
                    is = response.body().byteStream();
                    File file = createFile();
                    fos = new FileOutputStream(file);
                    long total = response.body().contentLength();
                    contentLength = total;
                    long sum = 0;
                    while ((len = is.read(buff)) != -1) {
                        fos.write(buff, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        //??????????????????????????????
                        emitter.onNext(progress);
                        downloadLength = sum;
                    }
                    fos.flush();
//                    App.file=file;
                    textView.setClickable(true);
                    //4.?????????????????????apk
                    installApk(file);
                } catch (Exception e) {
                    e.printStackTrace();
                    breakpoint(downloadUrl, emitter);
                } finally {
                    try {
                        if (is != null)
                            is.close();
                        if (fos != null)
                            fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    private void breakpoint(final String downloadUrl, final ObservableEmitter<Integer> emitter) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .addHeader("RANGE", "bytes=" + downloadLength + "-" + contentLength)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //????????????
                breakpoint(downloadUrl, emitter);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() == null) {
                    //????????????
                    breakpoint(downloadUrl, emitter);
                    return;
                }
                InputStream is = null;
                RandomAccessFile randomFile = null;
                byte[] buff = new byte[2048];
                int len;
                try {
                    is = response.body().byteStream();
                    String root = Environment.getExternalStorageDirectory().getPath();
                    File file = new File(root, "app-release.apk");
                    randomFile = new RandomAccessFile(file, "rwd");
                    randomFile.seek(downloadLength);
                    long total = contentLength;
                    long sum = downloadLength;
                    while ((len = is.read(buff)) != -1) {
                        randomFile.write(buff, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        //??????????????????????????????
                        emitter.onNext(progress);
                        downloadLength = sum;
                    }
                    //4.?????????????????????apk
                    installApk(file);
                } catch (Exception e) {
                    e.printStackTrace();
                    breakpoint(downloadUrl, emitter);
                } finally {
                    try {
                        if (is != null)
                            is.close();
                        if (randomFile != null)
                            randomFile.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Activity.RESULT_OK == resultCode && 1 == requestCode) {
            down("http://apiapp.hrzp.top/apk/app-release.apk");
        }
        if (requestCode == 4004) {
            showError("???????????????????????????");
        }
    }

    public void installApk(File file) {


        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            } else {
                Uri apkUri = FileProvider.getUriForFile(this, "com.yunxuan.yxhrzp.file_provider", file);///-----ide??????????????????
                //Granting Temporary Permissions to a URI
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivityForResult(intent, 4004);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File createFile() {
        String root = Environment.getExternalStorageDirectory().getPath();
        File file = new File(root, "app-release.apk");
        if (file.exists())
            file.delete();
        try {
            file.createNewFile();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * ????????????app version code
     */
    public static long getAppVersionCode(Context context) {
        long appVersionCode = 0;
        try {
            PackageInfo packageInfo = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                appVersionCode = packageInfo.getLongVersionCode();
            } else {
                appVersionCode = packageInfo.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("", e.getMessage());
        }
        return appVersionCode;
    }

    public static int getScreenWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        return display.getWidth();
    }

    public static void setCustomDensit(@NonNull Activity activity) {
//        px = density * dp
//
//
//        density = dpi/160
//
//
//        px = dp * (dpi/160)
//         density

        final DisplayMetrics displayMetrics = App.mContext.getResources().getDisplayMetrics();
        if (sNoncompatDensity == 0) {
            sNoncompatDensity = displayMetrics.density;
            sNoncompatScaledDensity = displayMetrics.scaledDensity;
            App.getContext().registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(@NonNull Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNoncompatScaledDensity = App.mContext.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }


        final float targetDensit = displayMetrics.widthPixels / 360;  //????????????????????????/360,?????????density
        final float targetScaledDensity = targetDensit * (sNoncompatScaledDensity / sNoncompatDensity);  //????????????????????????/360,?????????density
        final int targetdensitydpi = (int) (160 * targetDensit);  //???????????????????????????dpi??????160*density????????????densityDpi
        displayMetrics.density = targetDensit;
        displayMetrics.scaledDensity = targetScaledDensity;
        displayMetrics.densityDpi = targetdensitydpi;
        final DisplayMetrics activitydisplaymetrics = activity.getResources().getDisplayMetrics();
        activitydisplaymetrics.density = targetDensit;
        activitydisplaymetrics.scaledDensity = targetScaledDensity;
        activitydisplaymetrics.densityDpi = targetdensitydpi;

    }

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return !flag;
    }
    public String remove_s(String s){//????????????
        String s1=s.replace("[","");
        String s2=s1.replace("]","");
        String s3=s2.replace(" ","");
        return s3;
    }
    public String JSONTokener(String in) {//
        // consume an optional byte order mark (BOM) if it exists
        if (in != null && in.startsWith("\ufeff")) {
            in = in.substring(1);
        }
        return in;
    }
}
