package com.bawei.zhujinru20200102;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作:a zhujinru
 * 时间:2020/1/2 9:23
 * 作用:适配器
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<GsonBean.ResultBean> result;

    public MyAdapter(List<GsonBean.ResultBean> result) {
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.child, null);
        return new MyViewHolder(inflate);
    }
//设置数据
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GsonBean.ResultBean resultBean = result.get(position);
        holder.cName.setText(resultBean.getTitle());
        holder.cPrice.setText(resultBean.getRank()+"");
        NetUtil.getInstance().getPho(resultBean.getImageUrl(),holder.cImage);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.c_image)
        ImageView cImage;
        @BindView(R.id.c_name)
        android.widget.TextView cName;
        @BindView(R.id.c_price)
        android.widget.TextView cPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
