package com.yhkhgl.top.ui.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.yhkhgl.top.R;
import com.yhkhgl.top.bean.GualiDetailBean;
import com.yhkhgl.top.recyclerview.base.ViewHolder;
import com.yhkhgl.top.ui.activity.AddGenjinActivity;
import com.yhkhgl.top.ui.activity.GuanliDetailActivity;
import com.yhkhgl.top.ui.activity.YongjinTwoActivity;

import java.lang.ref.SoftReference;
import java.util.List;

public class GuanliDetailAdapter extends RecyclerView.Adapter<ViewHolder> {
    List<GualiDetailBean> list;
    SoftReference<GuanliDetailActivity> softReference;
    public int num;
   public String copy;
   public int p,type;
    public GuanliDetailAdapter(GuanliDetailActivity context, List<GualiDetailBean> list, int type) {
        this.softReference = new SoftReference<>(context);
        this.list = list;
        this.type = type;
        Log.i("GuanliDetailAdapter","GuanliDetailAdapter");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("onCreateViewHolder","onCreateViewHolder");
        View view = null;
        if (viewType == 0){
            view = LayoutInflater.from(softReference.get()).inflate(R.layout.guanli_detail_item, parent, false);
        }else {
            view = LayoutInflater.from(softReference.get()).inflate(R.layout.yongjin_two_item,null);
        }
        return new ViewHolder(softReference.get(), view);
//        return view;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i("onBindViewHolder","onBindViewHolder");
        if (type == 0){
            if (position == 0){
                copy = list.get(0).getContent();
            }
            View view= holder.itemView.findViewById(R.id.item_view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    p=position;
                    Intent intent = new Intent(softReference.get(), AddGenjinActivity.class);
                    intent.putExtra("cus_id",list.get(position).getUser_customization_id());
                    intent.putExtra("id",list.get(position).getId());
                    intent.putExtra("bean",list.get(position));
                    if (list.size()>1){
                        intent.putExtra("copy",list.get(1).getContent());
                    }

                    intent.putExtra("position",position);
                    softReference.get().startActivityForResult(intent,2);
                }
            });
            TextView tv_day=holder.itemView.findViewById(R.id.tv_day);
            TextView tv_date=holder.itemView.findViewById(R.id.tv_date);

            String s=list.get(position).getFollow_date();
            String day=s.substring(s.length()-2,s.length());
            String date=s.substring(0,7);
            tv_date.setText(date);
            tv_day.setText(day);
            TextView textView = holder.itemView.findViewById(R.id.tv_content);
            textView.setText(list.get(position).getContent());
            if (position == 0) {
                holder.itemView.findViewById(R.id.tv_title).setVisibility(View.VISIBLE);
            } else {
                holder.itemView.findViewById(R.id.tv_title).setVisibility(View.GONE);
            }
            ImageView del_iv=holder.itemView.findViewById(R.id.del_iv);
            del_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    softReference.get().setDetail(list.get(position).getId(),position);
                }
            });
        }else {
            TextView tv_day=holder.itemView.findViewById(R.id.tv_day);
            TextView tv_date=holder.itemView.findViewById(R.id.tv_date);
            String s=list.get(position).getSuccess_time();
            String day=s.substring(s.length()-2,s.length());
            String date=s.substring(0,7);
            tv_date.setText(date);
            tv_day.setText(day);
            TextView tv_cje = holder.itemView.findViewById(R.id.tv_cje);
            tv_cje.setText(list.get(position).getTransaction_amount()+"万");
            TextView tv_yj = holder.itemView.findViewById(R.id.tv_yj);
            tv_yj.setText(list.get(position).getCommission());
            TextView tv_cjfa = holder.itemView.findViewById(R.id.tv_cjfa);
            if (list.get(position).getTransaction_plan().equals("")){
                tv_cjfa.setText("成交方案：--");
            }else {
                tv_cjfa.setText("成交方案："+list.get(position).getTransaction_plan());
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(softReference.get(), YongjinTwoActivity.class);
                    intent.putExtra("id",list.get(position).getId());
                    softReference.get().startActivity(intent);
                }
            });
        }




    }


    @Override
    public int getItemViewType(int position) {
        if (type == 0) {//0-10是iv，10-20是tv，所以先除以10看看是不是奇偶数就行了
            return 0;
        } else {
            return 1;
        }
    }
    @Override
    public int getItemCount() {
        Log.i("getItemCount","getItemCount"+list.size());
        return list.size();
    }

    // 暴露接口，更新数据源，并修改hasMore的值，如果有增加数据，hasMore为true，否则为false
    public void updateList(List<GualiDetailBean> list1,int type) {
        // 在原有的数据之上增加新数据
        if (list1 != null) {
            list.addAll(list1);
        }
        notifyDataSetChanged();
        num=list.size();
        this.type = type;
    }

    public void remove() {
        // 在原有的数据之上增加新数据
        if (list != null) {
            list.clear();
        }
        notifyDataSetChanged();
        num=list.size();
    }
    public void removeData(int position) {
        list.remove(position);
        //删除动画
//        notifyItemRemoved(position);

        notifyDataSetChanged();
        num=list.size();
    }
    public void gengxin(GualiDetailBean b){
        list.get(p).setFollow_date(b.getFollow_date());
        list.get(p).setContent(b.getContent());
        list.get(p).setNext_follow_date(b.getNext_follow_date());
        list.get(p).setVisit_date(b.getVisit_date());
        notifyDataSetChanged();
    }
}
