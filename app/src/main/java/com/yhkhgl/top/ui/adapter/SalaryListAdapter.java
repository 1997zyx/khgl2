package com.yhkhgl.top.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yhkhgl.top.R;
import com.yhkhgl.top.bean.XinziBean;
import com.yhkhgl.top.recyclerview.base.ViewHolder;
import com.yhkhgl.top.ui.activity.SalaryActivity;


import java.util.List;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class SalaryListAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    List<XinziBean> list;
    public SalaryListAdapter(Context context,List<XinziBean> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.xinzi_item,null);
        return new ViewHolder(context,view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView tv_yongjin = holder.itemView.findViewById(R.id.tv_yongjin);
        tv_yongjin.setText("￥"+list.get(position).getTotal_salary());
        ImageView touxiang_iv = holder.itemView.findViewById(R.id.touxiang_iv);
        Glide.with(context).load(list.get(position).getAvatar()).into(touxiang_iv);
        TextView dixin_tv =holder.itemView.findViewById(R.id.dixin_tv);
        dixin_tv.setText(list.get(position).getBase_salary_money()+"元");
        TextView name = holder.itemView.findViewById(R.id.name);
        name.setText(list.get(position).getName());
        TextView tv_date = holder.itemView.findViewById(R.id.tv_date);
        tv_date.setText(list.get(position).getCalculation_date());
        TextView shebao_tv = holder.itemView.findViewById(R.id.shebao_tv);
        shebao_tv.setText(list.get(position).getSocial_insurance_late_money()+"元");
        TextView tc_tv = holder.itemView.findViewById(R.id.tc_tv);
        tc_tv.setText(list.get(position).getCommission_amount()+"元");
        TextView qita_tv = holder.itemView.findViewById(R.id.qita_tv);
        qita_tv.setText(list.get(position).getCorrection_value()+"元");
        TextView jj_tv = holder.itemView.findViewById(R.id.jj_tv);
        jj_tv.setText(list.get(position).getBonus()+"元");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SalaryActivity.class);
                intent.putExtra("service_user_id",list.get(position).getService_user_id());
                intent.putExtra("calculation_date",list.get(position).getCalculation_date());
                startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    // 暴露接口，更新数据源，并修改hasMore的值，如果有增加数据，hasMore为true，否则为false
    public void updateList(List<XinziBean> newDatas) {
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
