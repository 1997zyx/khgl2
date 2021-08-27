package com.yhkhgl.top.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.yhkhgl.top.R;

import static com.blankj.utilcode.util.ScreenUtils.getScreenWidth;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;


public class BottomDialogUtil {

    public static void Dia(Dialog bgSetDialog, Context context, View inflater) {

        //填充对话框的布局
        View view = inflater;
        //初始化控件
        //将布局设置给Dialog
        bgSetDialog.setContentView(view);
        //获取当前Activity所在的窗体
        Window dialogWindow = bgSetDialog.getWindow();
        //设置Dialog从窗体底部弹出

        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = getScreenWidth();
        lp.y = 0; //设置Dialog距离底部的距离

        dialogWindow.setAttributes(lp); //将属性设置给窗体
        bgSetDialog.show();//显示对话框
    }

    public static void Dia2(Dialog bgSetDialog, Context context, View inflater) {

        //填充对话框的布局
        View view = inflater;
        //初始化控件
        //将布局设置给Dialog
        bgSetDialog.setContentView(view);
        //获取当前Activity所在的窗体
        Window dialogWindow = bgSetDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = getScreenWidth();
        lp.y = 0; //设置Dialog距离底部的距离

        dialogWindow.setAttributes(lp); //将属性设置给窗体
        bgSetDialog.show();//显示对话框
    }

    public static void DiaCenter(Dialog bgSetDialog, Context context, View inflater) {

        //填充对话框的布局
        View view = inflater;
        //初始化控件
        //将布局设置给Dialog
        bgSetDialog.setContentView(view);
        //获取当前Activity所在的窗体
        Window dialogWindow = bgSetDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (getScreenWidth() * 0.85);
        lp.y = 0; //设置Dialog距离底部的距离
        dialogWindow.setAttributes(lp); //将属性设置给窗体
        bgSetDialog.show();//显示对话框
    }

    public static void AllCenter(Dialog bgSetDialog, Context context, View inflater) {

        //填充对话框的布局
        View view = inflater;
        //初始化控件
        //将布局设置给Dialog
        bgSetDialog.setContentView(view);
        //获取当前Activity所在的窗体
        Window dialogWindow = bgSetDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) getScreenWidth();
        lp.y = 0; //设置Dialog距离底部的距离
        dialogWindow.setAttributes(lp); //将属性设置给窗体
        bgSetDialog.show();//显示对话框
    }

    public static void Diamap(final Dialog bgSetDialog, final Context context, final String latitude, final String longitude, final String address) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.map_item, null);
        TextView gaode = itemView.findViewById(R.id.gaode);
        gaode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //百度转高德
                        double lon= Double.parseDouble(longitude);
                        double lat= Double.parseDouble(latitude);
                        double x = lon - 0.0065, y = lat - 0.006;
                        double z = sqrt(x * x + y * y) - 0.00002 * sin(y * Math.PI);
                        double theta = atan2(y, x) - 0.000003 * cos(x * Math.PI);
                       String gg_lon = String.valueOf(z * cos(theta));
                      String  gg_lat = String.valueOf(z * sin(theta));

                    Uri uri = Uri.parse("amapuri://route/plan/?dlat=" + gg_lat + "&dlon=" +  gg_lon + "&dname=" + address + "&dev=0&t=0");
                    Intent intent = new Intent("android.intent.action.VIEW", uri);
                    intent.addCategory("android.intent.category.DEFAULT");
                    context.startActivity(intent);

                } catch (Exception e) {
                    Toast.makeText(context, "请安装高德地图", Toast.LENGTH_SHORT).show();
                }


            }
        });
        TextView baidumap = itemView.findViewById(R.id.baidumap);
        baidumap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i1 = new Intent();

                    i1.setData(Uri.parse("baidumap://map/navi?query=" + address + "&src=andr.baidu.openAPIdemo"));


                    context.startActivity(i1);
                } catch (Exception e) {
                    Toast.makeText(context, "请安装百度", Toast.LENGTH_SHORT).show();
                }

            }
        });
        TextView textView = itemView.findViewById(R.id.clear_tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bgSetDialog.dismiss();
            }
        });
        //填充对话框的布局
        //初始化控件
        //将布局设置给Dialog
        bgSetDialog.setContentView(itemView);
        //获取当前Activity所在的窗体
        Window dialogWindow = bgSetDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = getScreenWidth();
        lp.y = 0; //设置Dialog距离底部的距离

        dialogWindow.setAttributes(lp); //将属性设置给窗体
        bgSetDialog.show();//显示对话框
    }




    public static String[] jingweidu(double lng, double lat) {
        double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
        double x = lng - 0.0065;
        double y = lat - 0.006;
        double z = sqrt(x * x + y * y) - 0.00002 * sin(y * x_pi);
        double theta = atan2(y, x) - 0.000003 * cos(x * x_pi);
        String lngs = String.valueOf(z * cos(theta));
        String lats = String.valueOf(z * sin(theta));
        String[] m={lats,lngs};
        return  m;
    }

}
