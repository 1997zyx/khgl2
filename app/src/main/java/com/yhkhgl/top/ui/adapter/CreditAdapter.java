package com.yhkhgl.top.ui.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yhkhgl.top.R;
import com.yhkhgl.top.bean.CreditBean;
import com.yhkhgl.top.recyclerview.base.ViewHolder;
import com.yhkhgl.top.ui.activity.CreditActivity;
import com.yhkhgl.top.utils.BottomDialogUtil;

import java.lang.ref.SoftReference;
import java.util.List;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class CreditAdapter extends RecyclerView.Adapter<ViewHolder> {
    List<CreditBean> mlist;
    Context context;
    SoftReference<CreditActivity> softReference;
    public CreditAdapter(Context context,List<CreditBean> mlist,CreditActivity activity){
        this.context=context;
        this.mlist=mlist;
        softReference=new SoftReference<>(activity);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.zhengxin_item,parent,false);
        return new ViewHolder(context,view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView zuijin_tv=holder.itemView.findViewById(R.id.zuijin_tv);
        if (position == 0){
            zuijin_tv.setVisibility(View.VISIBLE);
        }else {
            zuijin_tv.setVisibility(View.INVISIBLE);
        }

        TextView add_tv=holder.itemView.findViewById(R.id.add_tv);

        add_tv.setText(mlist.get(position).getDistance());
        TextView tv_name=holder.itemView.findViewById(R.id.tv_name);
        tv_name.setText(mlist.get(position).getName());
        TextView tv_address=holder.itemView.findViewById(R.id.tv_address);
        tv_address.setText(mlist.get(position).getAddress());
        TextView tv_time=holder.itemView.findViewById(R.id.tv_time);
        tv_time.setText(mlist.get(position).getWork_time());
        LinearLayout ll_phone=holder.itemView.findViewById(R.id.ll_phone);
        ll_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mlist.get(position).getMobile().equals("")){
                    Toast.makeText(context,"暂无电话",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + mlist.get(position).getMobile());
                intent.setData(data);
                startActivity(intent);
            }
        });
        LinearLayout address_ll=holder.itemView.findViewById(R.id.address_ll);
        address_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(context, R.style.BottomDialogStyle);
                BottomDialogUtil.Diamap(dialog,context,mlist.get(position).getLatitude(),mlist.get(position).getLongitude(),mlist.get(position).getAddress());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
    // 暴露接口，更新数据源，并修改hasMore的值，如果有增加数据，hasMore为true，否则为false
    public void updateList(List<CreditBean> newDatas) {
        // 在原有的数据之上增加新数据
        if (newDatas != null) {
            mlist.addAll(newDatas);
        }
        notifyDataSetChanged();
    }

    // 暴露接口，更新数据源，并修改hasMore的值，如果有增加数据，hasMore为true，否则为false
    public void remove() {
        if (mlist != null) {
            mlist.clear();

        }
        notifyDataSetChanged();
    }
}
