package com.yhkhgl.top.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.yhkhgl.top.R;
import com.yhkhgl.top.bean.GuanliListBean;
import com.yhkhgl.top.recyclerview.base.ViewHolder;
import com.yhkhgl.top.ui.activity.GuanliDetailActivity;
import com.yhkhgl.top.ui.activity.GuanliSeachActivity;
import com.yhkhgl.top.utils.Diputil;
import com.yhkhgl.top.utils.FlowLayout;

import java.lang.ref.SoftReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.blankj.utilcode.util.ColorUtils.getColor;

public class GuanliSeachAdapter extends RecyclerView.Adapter<ViewHolder> {
    private SoftReference<GuanliSeachActivity> softReference;
    private List<GuanliListBean> listBeans;
    int year, month, day;
    String str;

    public GuanliSeachAdapter(GuanliSeachActivity mcontext, List<GuanliListBean> beans) {
        this.softReference = new SoftReference<>(mcontext);
        this.listBeans = beans;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-d");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        str = formatter.format(curDate);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(softReference.get()).inflate(R.layout.guanli_item, parent, false);
        return new ViewHolder(softReference.get(), view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FlowLayout ll_content = holder.itemView.findViewById(R.id.ll_content);
        ll_content.removeAllViews();
        for (int i = 0; i < listBeans.get(position).getCus_type_arr().length; i++) {
            if (i < 3) {
                TextView checkBox = new TextView(softReference.get());
                checkBox.setText(listBeans.get(position).getCus_type_arr()[i]);
                checkBox.setBackground(softReference.get().getResources().getDrawable(R.drawable.guanli_check_true));
                checkBox.setTextSize(11);
                checkBox.setTextColor(softReference.get().getResources().getColor(R.color.ff215dff));
                checkBox.setPadding(Diputil.dip2px(softReference.get(), 10), Diputil.dip2px(softReference.get(), 2), Diputil.dip2px(softReference.get(), 10), Diputil.dip2px(softReference.get(), 2));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, 0, Diputil.dip2px(softReference.get(), 7), 0);//4个参数按顺序分别是左上右下
                checkBox.setLayoutParams(layoutParams);
                ll_content.addView(checkBox);
            }
            if (i == 3) {
                TextView checkBox = new TextView(softReference.get());
                checkBox.setText(listBeans.get(position).getCus_type_arr()[i]);
                checkBox.setBackground(softReference.get().getResources().getDrawable(R.drawable.guanli_check_true));
                checkBox.setTextSize(11);
                checkBox.setTextColor(softReference.get().getResources().getColor(R.color.ff215dff));
                checkBox.setPadding(Diputil.dip2px(softReference.get(), 5), Diputil.dip2px(softReference.get(), 2), Diputil.dip2px(softReference.get(), 5), Diputil.dip2px(softReference.get(), 2));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, 0, Diputil.dip2px(softReference.get(), 7), 0);//4个参数按顺序分别是左上右下
                checkBox.setLayoutParams(layoutParams);
                ll_content.addView(checkBox);
            }

        }


        TextView textView = holder.itemView.findViewById(R.id.name_tv);
        textView.setText(listBeans.get(position).getSurname());
        TextView mobile = holder.itemView.findViewById(R.id.mobile);
        mobile.setText(listBeans.get(position).getMobile());
        TextView qudao_tv = holder.itemView.findViewById(R.id.qudao_tv);
        qudao_tv.setText(listBeans.get(position).getChannel());
        TextView dengji_tv = holder.itemView.findViewById(R.id.dengji_tv);
        dengji_tv.setText(listBeans.get(position).getKey_categories());
        TextView time_tv = holder.itemView.findViewById(R.id.time_tv);
        if (listBeans.get(position).getNext_s_date() == null || listBeans.get(position).getNext_s_date().equals("")) {
            time_tv.setText("下次跟进时间:无");
            time_tv.setTextColor(getColor(R.color.ff215dff));
        } else {
            String s = listBeans.get(position).getNext_s_date();
            int s1 = Integer.parseInt(s.replaceAll("-", ""));
            int jin = Integer.parseInt(str.replaceAll("-", ""));
            if (s1 < jin) {
                time_tv.setText("下次跟进时间:" + listBeans.get(position).getNext_s_date());
                time_tv.setTextColor(getColor(R.color.fe60012));
            } else if (s1 == jin) {
                time_tv.setText("下次跟进时间:今天");
                time_tv.setTextColor(getColor(R.color.fe60012));
            } else {
                time_tv.setText("下次跟进时间:" + listBeans.get(position).getNext_s_date());
                time_tv.setTextColor(getColor(R.color.ff215dff));
            }
        }
        TextView cus_info_tv = holder.itemView.findViewById(R.id.cus_info_tv);
        if (listBeans.get(position).getCus_info() == null || listBeans.get(position).getCus_info().equals("")) {
            cus_info_tv.setText("无");
        } else {
            cus_info_tv.setText(listBeans.get(position).getCus_info());


        }
        TextView zhengxin_tv = holder.itemView.findViewById(R.id.zhengxin_tv);
        TextView follow_tv = holder.itemView.findViewById(R.id.follow_tv);
        zhengxin_tv.setText(listBeans.get(position).getCredit_inquiry());
        String follow_status = listBeans.get(position).getFollow_status();
        follow_tv.setText(follow_status);
        switch (follow_status) {
            case "重点":
            case "办理中":
                follow_tv.setTextColor(getColor(R.color.colorWhite));
                follow_tv.setBackgroundResource(R.drawable.guanli_huasu);
                follow_tv.setTextSize(12);
                break;
            default:
                follow_tv.setTextColor(getColor(R.color.text_1b1b1b));
                follow_tv.setBackgroundResource(0);
                follow_tv.setTextSize(15);
                break;
        }
        ImageView imageView = holder.itemView.findViewById(R.id.iv_phone);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建打电话的意图
                Intent intent = new Intent();
                //设置拨打电话的动作
                intent.setAction(Intent.ACTION_DIAL);
                //设置拨打电话的号码
                intent.setData(Uri.parse("tel:" + listBeans.get(position).getMobile()));
                //开启打电话的意图
                softReference.get().startActivity(intent);

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (softReference.get().jsonObject != null) {
                    Intent intent = new Intent(softReference.get(), GuanliDetailActivity.class);
                    intent.putExtra("id", listBeans.get(position).getId());
                    intent.putExtra("jsonObject", softReference.get().jsonObject.toString());
                    softReference.get().startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    public void updateList(List<GuanliListBean> newDatas) {
        // 在原有的数据之上增加新数据
        int i = 0;
        if (listBeans != null) {
            listBeans.clear();
        }
        if (newDatas != null) {
            listBeans.addAll(newDatas);
        }
        notifyDataSetChanged();
//        notifyItemRangeChanged(i, getItemCount());
    }

    // 暴露接口，更新数据源，并修改hasMore的值，如果有增加数据，hasMore为true，否则为false
    public void remove() {
        if (listBeans != null) {
            listBeans.clear();
        }
        notifyDataSetChanged();
    }
}
