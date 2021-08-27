package com.yhkhgl.top.utils;

import android.widget.EditText;

public class EditViewUtil {
    public static void mangeEditextDigit(CharSequence s, EditText et, int digit){//限制输入小数点后位数
        String str=s.toString();
        int len=s.length();
        if(len-str.replace(".", "").length()>1 ){
            //不允许输入1个以上的小数点
            et.setText(s.subSequence(0, len-1));
            et.setSelection(s.length()-1);
        }
        if (str.contains(".")) {
            //控制小数点后面位数
            if (len - 1 - str.indexOf(".") >digit ) {
                s = str.subSequence(0,
                        str.indexOf(".") + digit+1);
                et.setText(s);
                et.setSelection(s.length());
            }
        }
        if (str.substring(0).equals(".")) {
            //输入点，自动补充成0.
            s = "0" + s;
            et.setText(s);
            et.setSelection(2);
        }

        if (str.startsWith("0")
                && len > 1) {
            //以0开头不允许输入连续的0如00，000
            if (!str.substring(1, 2).equals(".")) {
                et.setText(s.subSequence(0, 1));
                et.setSelection(1);

            }
        }

    }
}
