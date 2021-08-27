package com.yhkhgl.top.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.yhkhgl.top.R;
import com.yhkhgl.top.bean.YongjinListBean;
import com.yhkhgl.top.recyclerview.base.ViewHolder;
import com.yhkhgl.top.ui.activity.GuanliDetailActivity;

import java.util.List;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class YongjinAdapter extends RecyclerView.Adapter<ViewHolder> {
    List<YongjinListBean> list;
    Context context;
    public YongjinAdapter(Context context, List<YongjinListBean> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.yongjin_item,null);
        return new ViewHolder(context,view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView tv_name = holder.itemView.findViewById(R.id.tv_name);
        tv_name.setText(list.get(position).getSurname());
        TextView tv_time = holder.itemView.findViewById(R.id.tv_time);
        TextView tv_cje = holder.itemView.findViewById(R.id.tv_cje);
        TextView tv_cjfa = holder.itemView.findViewById(R.id.tv_cjfa);
        TextView tv_yongjin =holder.itemView.findViewById(R.id.tv_yongjin);
        tv_time.setText("成交时间："+list.get(position).getSuccess_time());
        tv_cje.setText("成交额（万）："+list.get(position).getTransaction_amount());
        if (list.get(position).getTransaction_plan()==null||list.get(position).getTransaction_plan().equals("")){
            tv_cjfa.setText("成交方案：--");
        }else {
            tv_cjfa.setText("成交方案："+list.get(position).getTransaction_plan());
        }

        tv_yongjin.setText("￥" + list.get(position).getCommission());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GuanliDetailActivity.class);
                intent.putExtra("id", list.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    // 暴露接口，更新数据源，并修改hasMore的值，如果有增加数据，hasMore为true，否则为false
    public void updateList(List<YongjinListBean> newDatas) {
        // 在原有的数据之上增加新数据
        if (newDatas != null) {
            list.addAll(newDatas);
        }
        notifyDataSetChanged();
    }
    // 暴露接口，更新数据源，并修改hasMore的值，如果有增加数据，hasMore为true，否则为false
    public void remove() {
        if (list != null) {
            list.clear();
        }
        notifyDataSetChanged();
    }
}
