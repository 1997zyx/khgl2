package com.yhkhgl.top.utils;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.yhkhgl.top.App;
import com.yhkhgl.top.R;
import com.yhkhgl.top.base.BaseActivity;
import com.yhkhgl.top.view.ProgressDialog;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;


/**
 * 保存图片的工具类
 */
public class ShareUtil {
    private static BaseActivity activity;
    private static String filePath;
    private static Bitmap mBitmap;
    private static String mSaveMessage = "失败";
    private static ProgressDialog mSaveDialog = null;
    private static String allpath = Environment.getExternalStorageDirectory().getPath()+"/" +App.getContext().getPackageName()+"/Pic/";
    private static Bitmap createWaterMaskBitmap(Bitmap src, Bitmap watermark, int paddingLeft, int paddingTop) {
        if (src == null) {
            return null;
        }
        int width = src.getWidth();
        int height = src.getHeight();

        //创建一个bitmap
        Bitmap newb = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图

        //将该图片作为画布
        Canvas canvas = new Canvas(newb);
        //在画布 0，0坐标上开始绘制原始图片
        canvas.drawBitmap(src, 0, 0, null);

        //在画布上绘制水印图片
        canvas.drawBitmap(watermark, paddingLeft, paddingTop, null);

        // 保存
        canvas.save();

        // 存储
        canvas.restore();

        return newb;
    }

    public static void saveBitmap(BaseActivity activity, View view, String filePath) {//带水印的分享
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean permissionReadExternal = ContextCompat.checkSelfPermission(activity, "android.permission.READ_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED;
            boolean permissionWriteExternal = ContextCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED;
            if (permissionReadExternal && permissionWriteExternal) {
                // 创建对应大小的bitmap
                Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                        Bitmap.Config.ARGB_8888);

                Canvas canvas = new Canvas(bitmap);
                view.draw(canvas);
                //存储
                FileOutputStream outStream = null;
                File file=new File(setFilepath());
                try {

                    outStream = new FileOutputStream(file);
                    Bitmap sourBitmap = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.suiyin);

//                    Bitmap sourzhiliang= compressImage(sourBitmap);


                    Bitmap b = createWaterMaskBitmap(bitmap, sourBitmap, 0, 0);

                    b.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                    outStream.flush();


                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        bitmap.recycle();
                        if (outStream != null) {
                            outStream.close();
                        }
                        activity.hideLoading();

                        Intent share_intent = new Intent();
                        share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            share_intent.putExtra(Intent.EXTRA_STREAM, getImageContentUri(activity,file.getPath()));
                        } else {
                            share_intent.putExtra(Intent.EXTRA_STREAM, file);
                            // 使用图片路径加载
                        }
                        share_intent.setType("image/jpeg");//设置分享内容的类型
                        share_intent.putExtra(Intent.EXTRA_SUBJECT, "汇融智配");//添加分享内容标题
                        share_intent.putExtra(Intent.EXTRA_TEXT, "汇融智配");//添加分享内容
                        //创建分享的Dialog
                        share_intent = Intent.createChooser(share_intent, "汇融智配");
                        activity.startActivityForResult(share_intent, 1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                activity.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
        } else {
            activity.showLoading();
            // 创建对应大小的bitmap
            Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                    Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);
            //存储
            FileOutputStream outStream = null;
            String fileName = UUID.randomUUID().toString() + ".jpg";

            File file=new File(setFilepath());
            try {
                outStream = new FileOutputStream(file);
                Bitmap sourBitmap = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.suiyin);
                Bitmap b = createWaterMaskBitmap(bitmap, sourBitmap, 0, 0);
                b.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                outStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bitmap.recycle();
                    if (outStream != null) {
                        outStream.close();
                    }

                    Intent share_intent = new Intent();
                    share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        share_intent.putExtra(Intent.EXTRA_STREAM, getImageContentUri(activity, file.getPath()));
                    } else {
                        share_intent.putExtra(Intent.EXTRA_STREAM, file);
                        // 使用图片路径加载
                    }
                    share_intent.setType("image/jpeg");//设置分享内容的类型
                    share_intent.putExtra(Intent.EXTRA_SUBJECT, "汇融智配");//添加分享内容标题
                    share_intent.putExtra(Intent.EXTRA_TEXT, "汇融智配");//添加分享内容
                    //创建分享的Dialog
                    share_intent = Intent.createChooser(share_intent, "汇融智配");
                    activity.startActivityForResult(share_intent, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static void saveBitmapTwo(BaseActivity activity, View view, String filePath) {//不带水印的分享


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean permissionReadExternal = ContextCompat.checkSelfPermission(activity, "android.permission.READ_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED;
            boolean permissionWriteExternal = ContextCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED;
            if (permissionReadExternal && permissionWriteExternal) {


                // 创建对应大小的bitmap
                Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                        Bitmap.Config.ARGB_8888);

                Canvas canvas = new Canvas(bitmap);
                view.draw(canvas);


                FileOutputStream outStream=null;
                File file=new File(setFilepath());


                try {
                    outStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                    outStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        bitmap.recycle();
                        if (outStream != null) {
                            outStream.close();
                        }
                        activity.hideLoading();

                        Intent share_intent = new Intent();
                        share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            share_intent.putExtra(Intent.EXTRA_STREAM, getImageContentUri(activity, file.getPath()));
                        } else {
                            share_intent.putExtra(Intent.EXTRA_STREAM, file);
                            // 使用图片路径加载
                        }
                        share_intent.setType("image/jpeg");//设置分享内容的类型
                        share_intent.putExtra(Intent.EXTRA_SUBJECT, "汇融智配");//添加分享内容标题
                        share_intent.putExtra(Intent.EXTRA_TEXT, "汇融智配");//添加分享内容
                        //创建分享的Dialog
                        share_intent = Intent.createChooser(share_intent, "汇融智配");
                        activity.startActivityForResult(share_intent, 1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                activity.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
        } else {
            activity.showLoading();
            // 创建对应大小的bitmap
            Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                    Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);
            //存储
            FileOutputStream outStream = null;

            File file=new File(setFilepath());
            try {
                outStream = new FileOutputStream(file);
                Bitmap sourBitmap = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.suiyin);
                Bitmap b = createWaterMaskBitmap(bitmap, sourBitmap, 0, 0);
                b.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                outStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bitmap.recycle();
                    if (outStream != null) {
                        outStream.close();
                    }

                    Intent share_intent = new Intent();
                    share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        share_intent.putExtra(Intent.EXTRA_STREAM, getImageContentUri(activity, file.getPath()));
                    } else {
                        share_intent.putExtra(Intent.EXTRA_STREAM, file);
                        // 使用图片路径加载
                    }
                    share_intent.setType("image/jpeg");//设置分享内容的类型
                    share_intent.putExtra(Intent.EXTRA_SUBJECT, "汇融智配");//添加分享内容标题
                    share_intent.putExtra(Intent.EXTRA_TEXT, "汇融智配");//添加分享内容
                    //创建分享的Dialog
                    share_intent = Intent.createChooser(share_intent, "汇融智配");
                    activity.startActivityForResult(share_intent, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static void saveimage(BaseActivity activity, View view) {//保存页面的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean permissionReadExternal = ContextCompat.checkSelfPermission(activity, "android.permission.READ_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED;
            boolean permissionWriteExternal = ContextCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED;
            if (permissionReadExternal && permissionWriteExternal) {
                // 创建对应大小的bitmap
                Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                        Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                view.draw(canvas);
                //存储
                FileOutputStream outStream = null;
                File file=new File(setFilepath());
                try {
                    outStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                    outStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();

                } finally {
                    try {
                        bitmap.recycle();
                        if (outStream != null) {
                            outStream.close();
                            // 最后通知图库更新
                            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                            Uri uri = Uri.fromFile(file);
                            intent.setData(uri);
                            activity.sendBroadcast(intent);
                        }
                        activity.showError("保存成功");
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
            } else {
                activity.showError("请开启存储权限");
                activity.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
        } else {
            // 创建对应大小的bitmap
            Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                    Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);
            //存储
            FileOutputStream outStream = null;

            File file=new File(setFilepath());
            try {
                outStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                outStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
                activity.showError("请开启存储权限");
            } finally {
                try {
                    bitmap.recycle();
                    if (outStream != null) {
                        outStream.close();
                        // 最后通知图库更新
                        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                        Uri uri = Uri.fromFile(file);
                        intent.setData(uri);
                        activity.sendBroadcast(intent);
                    }
                    activity.showError("保存成功");
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }

    }

    //安卓7以后不允许直接冲文件中获取、要通过
    public static Uri getImageContentUri(Context context, String path) {
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID}, MediaStore.Images.Media.DATA + "=? ",
                new String[]{path}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {

// 如果图片不在手机的共享图片数据库，就先把它插入。
            if (new File(path).exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, path);
                return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    public static void donwloadImg(BaseActivity a, String filePaths) {
        activity = a;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean permissionReadExternal = ContextCompat.checkSelfPermission(activity, "android.permission.READ_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED;
            boolean permissionWriteExternal = ContextCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED;
            boolean permissionmountExternal = ContextCompat.checkSelfPermission(activity, "android.permission.MOUNT_UNMOUNT_FILESYSTEMS") == PackageManager.PERMISSION_GRANTED;
            if (permissionReadExternal && permissionWriteExternal) {
                activity = a;
                filePath = filePaths;
                new Thread(saveFileRunnable).start();
            } else {
                a.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS}, 100);
            }
        } else {
            filePath = filePaths;
            new Thread(saveFileRunnable).start();
        }

    }

    private static Runnable saveFileRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                if (!TextUtils.isEmpty(filePath)) {
//网络图片
// 对资源链接
                    URL url = new URL(filePath);
//打开输入流
                    InputStream inputStream = url.openStream();
//对网上资源进行下载转换位图图片
                    mBitmap = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                }
                saveFile(mBitmap);
                activity.showError("图片保存成功！");
            } catch (IOException e) {
                activity.showError("图片保存失败");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    public static String setFilepath(){
        String fileName = UUID.randomUUID().toString() + ".jpg";
        File sdDir = new File(allpath);
        if (!sdDir.exists()) {
            sdDir.mkdirs();
        }
        return allpath+fileName;
    }
    public static void saveFile(Bitmap bm) throws IOException {

        File file=new File(setFilepath());
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
//把图片保存后声明这个广播事件通知系统相册有新图片到来
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        activity.sendBroadcast(intent);
    }



}