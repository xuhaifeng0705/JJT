package com.example.jjt.ui.main.activity;

import android.animation.Animator;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jjt.Presenter.WelcomePresenter;
import com.example.jjt.Presenter.contract.WelcomeContract;
import com.example.jjt.R;
import com.example.jjt.base.BaseActivity;
import com.example.jjt.component.ImageLoader;
import com.example.jjt.model.bean.WelcomeBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/22 0022.
 */
public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContract.View {
    @BindView(R.id.iv_welcome_bg)
    ImageView ivWelcomeBg;
    @BindView(R.id.tv_welcome_author)
    TextView tvWelcomeAuthor;


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getWelcomeData();
//        mPresenter.getshop();

    }

//    @Override
//    public void showShop(TextModel homeShopModel) {
//        ImageLoader.load(this,homeShopModel.getImg(), ivWelcomeBg);
//        ivWelcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).setListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
//                jumpToMain();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//
//            }
//        }).start();
//        tvWelcomeAuthor.setText(homeShopModel.getTest2());
//    }

    @Override
    public void showContent(List<WelcomeBean> welcomeBean) {
        ImageLoader.load(this, welcomeBean.get(0).getUrl(), ivWelcomeBg);
        ivWelcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                jumpToMain();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        }).start();
        tvWelcomeAuthor.setText(welcomeBean.get(0).get_id());
    }

    @Override
    public void jumpToMain() {
        Intent intent = new Intent();
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

//        不过有时候overridePendingTransition 这个函数会不起作用，总结下，大概是以下三个方面的原因：
//
//        1、android系统版本2.0以下，这个没办法，想其他办法解决切换动画吧。
//        2、在ActivityGroup等的嵌入式Activity中，这个比较容易解决，用如下方法就可以了：
//        this.getParent().overridePendingTransition 就可以解决。
//        3、在一个Activity的内部类中，或者匿名类中，这时候只好用handler来解决了。
//        4、手机的显示动画效果被人为或者其他方式给关闭了 现在打开即可 设置->显示->显示动画效果
    }

    @Override
    protected void onDestroy() {
        Glide.clear(ivWelcomeBg);
        super.onDestroy();
    }

    @Override
    public void showError(String msg) {

    }
}
