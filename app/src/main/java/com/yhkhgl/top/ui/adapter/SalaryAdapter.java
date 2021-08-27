package com.yhkhgl.top.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.yhkhgl.top.R;
import com.yhkhgl.top.bean.YongjinListBean;
import com.yhkhgl.top.recyclerview.base.ViewHolder;

import java.util.List;

public class SalaryAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    List<YongjinListBean> list;
    public SalaryAdapter(Context context ,List<YongjinListBean> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.salary_item,null);
        return new ViewHolder(context,view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView name = holder.itemView.findViewById(R.id.name);
        TextView tv_date = holder.itemView.findViewById(R.id.tv_date);
        TextView tv_cje = holder.itemView.findViewById(R.id.tv_cje);
        TextView tv_yongjin = holder.itemView.findViewById(R.id.tv_yongjin);
        TextView tv_type = holder.itemView.findViewById(R.id.tv_type);
        name.setText(list.get(position).getSurname());
        tv_date.setText(list.get(position).getSuccess_time());
        tv_cje.setText(list.get(position).getTransaction_amount());
        tv_yongjin.setText(list.get(position).getCommission());
        if (list.get(position).getState().equals("1")){
            tv_type.setText("通过");
            tv_type.setTextColor(context.getResources().getColor(R.color.ff4dccc0));
        }else {
            tv_type.setText("拒绝");
            tv_type.setTextColor(context.getResources().getColor(R.color.fe60012));
        }
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
