package guozifeng.zshx.com.snmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import guozifeng.zshx.com.snmall.R;
import guozifeng.zshx.com.snmall.home.bean.ResultBeanData;
import guozifeng.zshx.com.snmall.utils.Constants;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/4/27.
 * 作用：
 */
public class SeckillRecycleViewAdapter extends RecyclerView.Adapter<SeckillRecycleViewAdapter.SeckillViewHolder>{

    private Context mContext;
    private List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> seckill_info;
    private OnSeckillRecyclerView onSeckillRecyclerView;
    public SeckillRecycleViewAdapter(Context mContext, List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> seckill_info) {
        this.mContext=mContext;
        this.seckill_info=seckill_info;
    }

    public void setOnSeckillRecyclerView(OnSeckillRecyclerView onSeckillRecyclerView){
        this.onSeckillRecyclerView=onSeckillRecyclerView;
    }
    @Override
    public SeckillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_seckill, null);

        return new SeckillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SeckillViewHolder holder, int position) {

        //更加位置得到对应的数据,再进行绑定
        ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = seckill_info.get(position);
        //使用Glide开源框架根据网址是ImageView加载图片
        Glide.with(mContext)
                .load(Constants.BASE_URL_IMAGE+listBean.getFigure())
                .into(holder.iv_figure);
        //设置价格
        holder.tv_cover_price.setText(listBean.getCover_price());
        //设置降价文本
        holder.tv_origin_price.setText(listBean.getOrigin_price());
    }

    @Override
    public int getItemCount() {
        return seckill_info.size();
    }


    class SeckillViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;
        public SeckillViewHolder(View itemView) {
            super(itemView);
            iv_figure= (ImageView) itemView.findViewById(R.id.iv_figure);
            tv_cover_price= (TextView) itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price= (TextView) itemView.findViewById(R.id.tv_origin_price);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注意要判空，否则会报空
                if(onSeckillRecyclerView!=null) {
                    onSeckillRecyclerView.OnItemClick(getLayoutPosition());
                }
            }
        });
        }
    }
    public interface OnSeckillRecyclerView{
        //定义监听的方法，可以传递下标
        public void OnItemClick(int position);
    }
}

