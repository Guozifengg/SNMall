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

public class RecommendGridAdapter extends BaseAdapter {

    private Context mContext;
    private List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info;

    public RecommendGridAdapter(Context mContext, List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
        this.mContext=mContext;
        this.recommend_info=recommend_info;
    }

    @Override
    public int getCount() {
        return recommend_info.size();
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
            convertView=View.inflate(mContext, R.layout.recommend_grid_item,null);
            //进行findViewById(R.id.);的查找,把查找对象存入ViewHolder中提高了效率
            viewHolder.iv_recommend= (ImageView) convertView.findViewById(R.id.iv_recommend);
            viewHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price= (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        //根据位置,得到所需的数据
        ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean = recommend_info.get(position);
        //使用图片处理的开源框架,根据图片网址展示图片
        Glide.with(mContext)
                .load(Constants.BASE_URL_IMAGE+recommendInfoBean.getFigure())
                .into(viewHolder.iv_recommend);
        viewHolder.tv_name.setText(recommendInfoBean.getName());
        viewHolder.tv_price.setText("人民币:"+recommendInfoBean.getCover_price());
        return convertView;
    }

    //定义ViewHolder
    private static class ViewHolder{
        ImageView iv_recommend;
        TextView tv_name;
        TextView tv_price;
    }
}
