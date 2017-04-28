package guozifeng.zshx.com.snmall.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import guozifeng.zshx.com.snmall.R;
import guozifeng.zshx.com.snmall.base.BaseFragment;
import guozifeng.zshx.com.snmall.community.fragment.CommunityFragment;
import guozifeng.zshx.com.snmall.home.fragment.HomeFragment;
import guozifeng.zshx.com.snmall.shoppingcart.fragment.ShoppingcartFragment;
import guozifeng.zshx.com.snmall.type.fargment.TypeFragment;
import guozifeng.zshx.com.snmall.user.fragment.UserFragment;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.rg_group)
    RadioGroup rgGroup;
    private List<BaseFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //初始化Fragment
        initFragment();
        //默认选中第一个
        rgGroup.check(R.id.rb_home);
        //默认加载第一个Fragment
        initFragment(mContent,fragments.get(0));
    }

    //舒适和各个模块的Fragment,并装进集合中
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingcartFragment());
        fragments.add(new UserFragment());
    }
    //给Fragment设置编号（程序员的世界，万物从0开始）
    private int position;
    @OnClick({R.id.rb_home, R.id.rb_type, R.id.rb_community, R.id.rb_cart, R.id.rb_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
                rgGroup.check(R.id.rb_home);
                position=0;
                break;
            case R.id.rb_type:
                rgGroup.check(R.id.rb_type);
                position=1;
                break;
            case R.id.rb_community:
                rgGroup.check(R.id.rb_community);
                position=2;
                break;
            case R.id.rb_cart:
                rgGroup.check(R.id.rb_cart);
                position=3;
                break;
            case R.id.rb_user:
                rgGroup.check(R.id.rb_user);
                position=4;
                break;
        }
        BaseFragment baseFragment=fragments.get(position);
        initFragment(mContent,baseFragment);
    }

    //上次切换的Fragment
    private Fragment mContent;

        public void initFragment(Fragment from,Fragment to) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            if (from != to) {
                mContent = to;
                if (!to.isAdded()) {
                    if (from != null) {
                        transaction.hide(from);
                    }
                    if (to != null) {
                        transaction.add(R.id.frameLayout, to);
                        transaction.commit();
                    }
                } else {
                    if (from != null) {
                        transaction.hide(from);
                    }
                    if (to != null) {
                        transaction.show(to);
                        transaction.commit();
                    }
                }
            }
        }
}
