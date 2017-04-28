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
 * Created by Administrator on 2017/4/26.
 * 作用：
 */

public class ChannelAdapter extends BaseAdapter {

    private Context mContext;
    private List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info;

    public ChannelAdapter(Context mContext, List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
        this.mContext = mContext;
        this.channel_info = channel_info;
    }

    @Override
    public int getCount() {
        return channel_info.size();
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
            //使用convertView来装载ViewGroup
            convertView = View.inflate(mContext, R.layout.channel_grid_item, null);
            viewHolder.iv_channel = (ImageView) convertView.findViewById(R.id.iv_channel);
            viewHolder.tv_channel = (TextView) convertView.findViewById(R.id.tv_channel);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        ResultBeanData.ResultBean.ChannelInfoBean channelInfoBean=channel_info.get(position);
        viewHolder.tv_channel.setText(channelInfoBean.getChannel_name());
        //Glide加载图片，不用加依赖因为Banner中自带
        Glide.with(mContext)
                .load(Constants.BASE_URL_IMAGE+channelInfoBean.getImage())
                .into(viewHolder.iv_channel);

        return convertView;
    }

    static class ViewHolder{

        public ImageView iv_channel;
        public TextView tv_channel;
    }
}
