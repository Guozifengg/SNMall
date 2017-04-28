package guozifeng.zshx.com.snmall.home.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;
import com.youth.banner.transformer.RotateUpTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;

import guozifeng.zshx.com.snmall.R;
import guozifeng.zshx.com.snmall.home.bean.ResultBeanData;
import guozifeng.zshx.com.snmall.utils.Constants;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/4/26.
 * 作用：
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private ResultBeanData.ResultBean mResultBean;
    //加载布局的填充对象
    private final LayoutInflater layoutInflater;

    public HomeFragmentAdapter(Context mContext, ResultBeanData.ResultBean mResultBean) {
        this.mContext = mContext;
        this.mResultBean = mResultBean;

        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //判断返回类型，根据返回类型，决定创建什么样的Holder
        if(viewType==BANNER){
            //返回创建的BannerViewHolder
            return new BannerViewHodler(mContext,layoutInflater.inflate(R.layout.banner_viewpager,null));
        }else if(viewType==CHANNEL){
            return new ChannelViewHolder(mContext,layoutInflater.inflate(R.layout.channel_item,null));
        }else if(viewType==ACT){
            return new ActViewHolder(mContext,layoutInflater.inflate(R.layout.act_viewpager,null));
        }else if(viewType==SECKILL){
            return new SeckillViewHolder(mContext,layoutInflater.inflate(R.layout.seckill_layout,null));
        }else if(viewType==RECOMMEND){
            return new RecommendViewHolder(mContext,layoutInflater.inflate(R.layout.recommend_layout,null));
        }else if(viewType==HOT){
            return new HotViewHolder(mContext,layoutInflater.inflate(R.layout.hot_layout,null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position)==BANNER){
            BannerViewHodler bannerViewHodler= (BannerViewHodler) holder;
            bannerViewHodler.setData(mResultBean.getBanner_info());
        }else if(getItemViewType(position)==CHANNEL){
            ChannelViewHolder channelViewHolder= (ChannelViewHolder) holder;
            channelViewHolder.setData(mResultBean.getChannel_info());
        }else if(getItemViewType(position)==ACT){
            ActViewHolder actViewHolder= (ActViewHolder) holder;
            actViewHolder.setData(mResultBean.getAct_info());
        }else if(getItemViewType(position)==SECKILL){
            SeckillViewHolder seckillViewHolder= (SeckillViewHolder) holder;
            seckillViewHolder.setData(mResultBean.getSeckill_info());
        }else if(getItemViewType(position)==RECOMMEND){
            RecommendViewHolder recommendViewHolder= (RecommendViewHolder) holder;
            recommendViewHolder.setData(mResultBean.getRecommend_info());
        }else if(getItemViewType(position)==HOT){
            HotViewHolder hotViewHolder= (HotViewHolder) holder;
            hotViewHolder.setData(mResultBean.getHot_info());
        }
    }

    //增加条目，不要忘记修改条目数量
    @Override
    public int getItemCount() {
        return 6;
    }

    //广告条幅类型(int数从0开始,数组从0开始,程序界:万物从0开始)
    private static final int BANNER =0;
    //频道类型
    private static final int CHANNEL = 1;
    //活动类型
    private static final int ACT = 2;
    //秒杀类型
    private static final int SECKILL = 3;
    //推荐类型
    private static final int RECOMMEND = 4;
    //热卖类型
    private static final int HOT = 5;
    //定义当前的条目
    private int currentType;
    @Override
    public int getItemViewType(int position) {
        switch (position){
            case BANNER:
                currentType=BANNER;
                break;
            case CHANNEL:
                currentType=CHANNEL;
                break;
            case ACT:
                currentType=ACT;
                break;
            case SECKILL:
                currentType=SECKILL;
                break;
            case RECOMMEND:
                currentType= RECOMMEND;
                break;
            case HOT:
                currentType=HOT;
                break;
        }
        return currentType;
    }

    private class BannerViewHodler extends RecyclerView.ViewHolder {
        private final Banner mBanner;
        private Context mContext;
        public BannerViewHodler(Context context,View itemView) {
            super(itemView);
            mContext=context;
            mBanner= (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info){

            ArrayList<String> imagersUrl=new ArrayList<>();

            for(int i=0;i< banner_info.size(); i++){
                String imagerUrl=banner_info.get(i).getImage();
                imagersUrl.add(imagerUrl);
            }
            //添加小圆点
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //添加轮播切换效果
            mBanner.setBannerAnimation(RotateUpTransformer.class);
            mBanner.setDelayTime(500);
            //添加轮播图片
            mBanner.setImages(imagersUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    Glide.with(mContext)
                            .load(Constants.BASE_URL_IMAGE+url)
                            .into(view);
                }
            });
            mBanner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "点击了"+position, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


    private class ChannelViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private GridView gv_Channel;
        private ChannelAdapter channelAdapter;

        public ChannelViewHolder(Context mContext, View inflate) {
            super(inflate);
            this.mContext=mContext;
            //找到布局中的GridView
            gv_Channel = (GridView) inflate.findViewById(R.id.gv_channel);
        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            //实例适配类，创建适配器
            channelAdapter = new ChannelAdapter(mContext, channel_info);
            //设置适配器
            gv_Channel.setAdapter(channelAdapter);
            //GridView设置条目监听
            gv_Channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "GridView"+position, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private class ActViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private ViewPager act_vp;

        public ActViewHolder(Context mContext, View inflate) {
            super(inflate);
            this.mContext=mContext;
            act_vp = (ViewPager) inflate.findViewById(R.id.act_vp);

            act_vp.setPageMargin(35);//设置page间间距，自行根据需求设置
            act_vp.setOffscreenPageLimit(3);//>=3
            //setPageTransformer 决定动画效果
            act_vp.setPageTransformer(true, new ScaleInTransformer());
        }

        public void setData(List<ResultBeanData.ResultBean.ActInfoBean> act_info) {

            act_vp.setAdapter(new ActViewPagerAdapter(mContext,act_info));
        }
    }

    private class SeckillViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private TextView tv_time_seckill;
        private TextView tv_more_seckill;
        private RecyclerView rv_seckill;

        private long dt=0;
        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                dt=dt-1000;

                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm:ss");
                String time=simpleDateFormat.format(new Date(dt));

                tv_time_seckill.setText(time);

                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0,1000);

                if(dt<=0){
                    handler.removeCallbacksAndMessages(null);
                }
            }
        };
        public SeckillViewHolder(Context mContext, View inflate) {
            super(inflate);
            this.mContext=mContext;
            tv_time_seckill = (TextView) inflate.findViewById(R.id.tv_time_seckill);
            tv_more_seckill = (TextView) inflate.findViewById(R.id.tv_more_seckill);
            rv_seckill = (RecyclerView) inflate.findViewById(R.id.rv_seckill);

        }

        public void setData(ResultBeanData.ResultBean.SeckillInfoBean seckill_info) {

//            Log.d("zzz",seckill_info.getList().get(0).getFigure());
            //得到数据，给RecyclerView设置数据
            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            //适配器
            SeckillRecycleViewAdapter seckillRecyclerViewAdapter = new SeckillRecycleViewAdapter(mContext, seckill_info.getList());
            rv_seckill.setAdapter(seckillRecyclerViewAdapter);


            seckillRecyclerViewAdapter.setOnSeckillRecyclerView(new SeckillRecycleViewAdapter.OnSeckillRecyclerView() {
                @Override
                public void OnItemClick(int position) {
                    Toast.makeText(mContext, "点击秒杀的"+position, Toast.LENGTH_SHORT).show();
                }
            });

            dt=Integer.valueOf(seckill_info.getEnd_time())-Integer.valueOf(seckill_info.getStart_time());

            handler.sendEmptyMessageDelayed(0,1000);
        }
    }

    private class RecommendViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private TextView tv_more_recommed;
        private GridView gv_recommend;

        public RecommendViewHolder(Context mContext, View inflate) {
            super(inflate);
            this.mContext=mContext;
            tv_more_recommed = (TextView) inflate.findViewById(R.id.tv_more_recommend);
            gv_recommend = (GridView) itemView.findViewById(R.id.gv_recommend);

        }

        public void setData(List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {

            RecommendGridAdapter recommendGridAdapter=new RecommendGridAdapter(mContext,recommend_info);
            gv_recommend.setAdapter(recommendGridAdapter);
            //设置监听
            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private class HotViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private TextView tv_more_hot;
        private GridView gv_hot;
        public HotViewHolder(Context mContext, View inflate) {
            super(inflate);
            this.mContext=mContext;
            tv_more_hot= (TextView) itemView.findViewById(R.id.tv_more_hot);
            gv_hot= (GridView) itemView.findViewById(R.id.gv_hot);
        }

        public void setData(List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {
                HotGridAdapter hotGridAdapter=new HotGridAdapter(mContext,hot_info);
            gv_hot.setAdapter(hotGridAdapter);

            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "热门点击"+position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
