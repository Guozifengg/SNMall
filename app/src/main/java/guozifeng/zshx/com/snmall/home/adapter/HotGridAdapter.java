package guozifeng.zshx.com.snmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import guozifeng.zshx.com.snmall.R;
import guozifeng.zshx.com.snmall.home.bean.ResultBeanData;
import guozifeng.zshx.com.snmall.utils.Constants;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/4/28.
 * 作用：
 */

public class HotGridAdapter extends BaseAdapter {

    private Context mContext;
    private List<ResultBeanData.ResultBean.HotInfoBean> hot_info;

    public HotGridAdapter(Context mContext, List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {
        this.mContext=mContext;
        this.hot_info=hot_info;
    }

    @Override
    public int getCount() {
        return hot_info.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView=View.inflate(mContext, R.layout.hot_grid_item,null);
            viewHolder.iv_hot= (ImageView) convertView.findViewById(R.id.iv_hot);
            viewHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price= (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        //获取对应位置Item的数据
        ResultBeanData.ResultBean.HotInfoBean hotInfoBean = hot_info.get(position);

        Glide.with(mContext)
                .load(Constants.BASE_URL_IMAGE+hotInfoBean.getFigure())
                .into(viewHolder.iv_hot);
        viewHolder.tv_name.setText(hotInfoBean.getName());
        viewHolder.tv_price.setText("$"+hotInfoBean.getCover_price());
        return convertView;
    }
    private static class ViewHolder{
        private ImageView iv_hot;
        private TextView tv_name;
        private TextView tv_price;
    }
}
