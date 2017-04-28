package guozifeng.zshx.com.snmall.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import guozifeng.zshx.com.snmall.home.bean.ResultBeanData;
import guozifeng.zshx.com.snmall.utils.Constants;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/4/27.
 * 作用：
 */

public class ActViewPagerAdapter extends PagerAdapter{
    private Context mContext;
    private List<ResultBeanData.ResultBean.ActInfoBean> act_info;
    public ActViewPagerAdapter(Context mContext, List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
        this.mContext=mContext;
        this.act_info=act_info;
    }


    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //移除所有视图的父类
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView img=new ImageView(mContext);
        img.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        //无限轮播的重点，找到图片的下标
        int in=position%act_info.size();
        Glide.with(mContext)
                .load(Constants.BASE_URL_IMAGE+act_info.get(in).getIcon_url())
                .into(img);

        //给图片设置点击监听
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了"+position, Toast.LENGTH_SHORT).show();
            }
        });
        container.addView(img);
        //注意，返回图片对象
        return img;
    }
}
