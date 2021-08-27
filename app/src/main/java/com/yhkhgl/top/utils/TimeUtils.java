package com.yhkhgl.top.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TimeUtils {
    public static int year, month, day;
    public static void getAllTime(List<String> yser_list, List<List<String>> month_list, List<List<List<String>>> data_list) {
        Calendar calendar = Calendar.getInstance();
//获取系统的日期
//年
        year = calendar.get(Calendar.YEAR);
//月  int month = calendar.get(Calendar.MONTH) + 1;
        month = calendar.get(Calendar.MONTH) + 1;
//日
        day = calendar.get(Calendar.DAY_OF_MONTH);

        for (int i = year + 100; i >= 1970; i--) {
            yser_list.add(i + "");
        }
        for (int i = 0; i < yser_list.size(); i++) {
            List<String> one = new ArrayList<>();
            List<List<String>> three = new ArrayList<>();
            for (int m = 1; m <= 12; m++) {
                one.add(m + "");
                List<String> two = new ArrayList<>();
                int data = data(Integer.parseInt(yser_list.get(i)), m);
                for (int d = 1; d <= data; d++) {
                    if (d < 10) {
                        two.add("0" + d + "");
                    } else {
                        two.add(d + "");
                    }
                }
                three.add(two);
            }
            month_list.add(one);
            data_list.add(three);
        }
    }

    public static void getStartToEnd(int start, int end, List<String> yser_list, List<List<String>> month_list, List<List<List<String>>> data_list) {
        Calendar calendar = Calendar.getInstance();
//获取系统的日期
//年
        year = calendar.get(Calendar.YEAR);
//月  int month = calendar.get(Calendar.MONTH) + 1;
        month = calendar.get(Calendar.MONTH) + 1;
//日
        day = calendar.get(Calendar.DAY_OF_MONTH);

        for (int i = start; i<= end; i++) {
            yser_list.add(i + "");
        }
        for (int i = 0; i < yser_list.size(); i++) {
            List<String> one = new ArrayList<>();
            List<List<String>> three = new ArrayList<>();
            if (i == 0) {
                for (int m = month; m <= 12; m++) {
                    if (m < 10) {
                        one.add("0" + m);
                    } else {
                        one.add(m + "");
                    }
                    List<String> two = new ArrayList<>();
                    int data = data(Integer.parseInt(yser_list.get(i)), m);
                    if (m == month) {
                        for (int d = day; d <= data; d++) {
                            if (d < 10) {
                                two.add("0" + d + "");
                            } else {
                                two.add(d + "");
                            }
                        }
                    }else {
                        for (int d = 1; d <= data; d++) {
                            if (d < 10) {
                                two.add("0" + d + "");
                            } else {
                                two.add(d + "");
                            }
                        }
                    }

                    three.add(two);
                }
            } else {
                for (int m = 1; m <= 12; m++) {
                    if (m < 10) {
                        one.add("0" + m);
                    } else {
                        one.add(m + "");
                    }
                    List<String> two = new ArrayList<>();
                    int data = data(Integer.parseInt(yser_list.get(i)), m);
                    for (int d = 1; d <= data; d++) {
                        if (d < 10) {
                            two.add("0" + d + "");
                        } else {
                            two.add(d + "");
                        }
                    }
                    three.add(two);
                }
            }
            month_list.add(one);
            data_list.add(three);
        }
    }

    public static void getBeforeTime(List<String> yser_list, List<List<String>> month_list, List<List<List<String>>> data_list) {
        Calendar calendar = Calendar.getInstance();
//获取系统的日期
//年
        year = calendar.get(Calendar.YEAR);
//月  int month = calendar.get(Calendar.MONTH) + 1;
        month = calendar.get(Calendar.MONTH) + 1;
//日
        day = calendar.get(Calendar.DAY_OF_MONTH);

        for (int i = year + 100; i >= year; i--) {
            yser_list.add(i + "");
        }
        for (int i = 0; i < yser_list.size(); i++) {
            List<String> one = new ArrayList<>();
            List<List<String>> three = new ArrayList<>();
            for (int m = 1; m <= 12; m++) {
                one.add(m + "");
                List<String> two = new ArrayList<>();
                int data = data(Integer.parseInt(yser_list.get(i)), m);
                for (int d = 1; d <= data; d++) {
                    if (d < 10) {
                        two.add("0" + d + "");
                    } else {
                        two.add(d + "");
                    }
                }
                three.add(two);
            }
            month_list.add(one);
            data_list.add(three);
        }
    }

    public static void getAfterTime(List<String> yser_list, List<List<String>> month_list, List<List<List<String>>> data_list) {
        Calendar calendar = Calendar.getInstance();
//获取系统的日期
//年
        year = calendar.get(Calendar.YEAR);
//月  int month = calendar.get(Calendar.MONTH) + 1;
        month = calendar.get(Calendar.MONTH) + 1;
//日
        day = calendar.get(Calendar.DAY_OF_MONTH);

        for (int i = year; i >= 1970; i--) {
            yser_list.add(i + "");
        }
        for (int i = 0; i < yser_list.size(); i++) {
            List<String> one = new ArrayList<>();
            List<List<String>> three = new ArrayList<>();
            for (int m = 1; m <= 12; m++) {
                one.add(m + "");
                List<String> two = new ArrayList<>();
                int data = data(Integer.parseInt(yser_list.get(i)), m);
                for (int d = 1; d <= data; d++) {
                    if (d < 10) {
                        two.add("0" + d + "");
                    } else {
                        two.add(d + "");
                    }
                }
                three.add(two);
            }
            month_list.add(one);
            data_list.add(three);
        }
    }

    public static int data(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                if (year % 4 == 0 && year % 100 != 0) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 30;

        }
    }
//    public void setTimeTypePickerView() {
//        if (mPickerView == null) {
//            mPickerView = new OptionsPickerView.Builder(this, (int options1, int option2, int options3, View view) -> {
//                if (type == 0) {
//
////                    next_follow_date = yser_list.get(options1) + "-" + month_list.get(options1).get(option2) + "-" + data_list.get(options1).get(option2).get(options3);
////                    newt_tv.setText(next_follow_date);
////                } else {
////
////                    visit_date = yser_list.get(options1) + "-" + month_list.get(options1).get(option2) + "-" + data_list.get(options1).get(option2).get(options3);
////                    tv_uphouse.setText(visit_date);
//                }
//            })//设置选择第一个
//                    .setSubmitColor(mActivity.getResources().getColor(R.color.bg_85736))//确定按钮文字颜色
//                    .setCancelColor(mActivity.getResources().getColor(R.color.text_959595))//取消按钮文字颜色
//                    .setTitleBgColor(mActivity.getResources().getColor(R.color.colorWhite))//标题背景颜色 Night mode
//                    .setContentTextSize(15) // 滚轮文字大小
//                    .setSubCalSize(14) // 确定取消按钮文字大小
//                    .setTitleSize(17)  // 标题文字大小
//                    .setTitleText(mTitle) // 标题文字
//                    .setLineSpacingMultiplier(2f)//设置间距倍数,但是只能在1.2-2.0f之间
//                    .setTitleColor(mActivity.getResources().getColor(R.color.text_1b1b1b)) // 标题文字颜色
//                    .setLabels("", "", "")
//                    .build();
//        }
//    }
}
