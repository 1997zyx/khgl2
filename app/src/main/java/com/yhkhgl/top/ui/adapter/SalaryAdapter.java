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
            tv_type.setText("ιθΏ");
            tv_type.setTextColor(context.getResources().getColor(R.color.ff4dccc0));
        }else {
            tv_type.setText("ζη»");
            tv_type.setTextColor(context.getResources().getColor(R.color.fe60012));
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    // ζ΄ι²ζ₯ε£οΌζ΄ζ°ζ°ζ?ζΊοΌεΉΆδΏ?ζΉhasMoreηεΌοΌε¦ζζε’ε ζ°ζ?οΌhasMoreδΈΊtrueοΌε¦εδΈΊfalse
    public void updateList(List<YongjinListBean> newDatas) {
        // ε¨εζηζ°ζ?δΉδΈε’ε ζ°ζ°ζ?
        if (newDatas != null) {
            list.addAll(newDatas);
        }
        notifyDataSetChanged();
    }
    // ζ΄ι²ζ₯ε£οΌζ΄ζ°ζ°ζ?ζΊοΌεΉΆδΏ?ζΉhasMoreηεΌοΌε¦ζζε’ε ζ°ζ?οΌhasMoreδΈΊtrueοΌε¦εδΈΊfalse
    public void remove() {
        if (list != null) {
            list.clear();
        }
        notifyDataSetChanged();
    }
}
