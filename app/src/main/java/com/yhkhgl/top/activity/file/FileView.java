package com.yhkhgl.top.activity.file;


import com.yhkhgl.top.base.mvp.BaseView;

import java.io.File;

/**
 * File descripition:
 *
 * @author lp
 * @date 2019/8/6
 */
public interface FileView extends BaseView {
    void onFileSuccess(File file);
}
