package cn.lkong.lkong.core;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.lkong.lkong.R;
import cn.lkong.lkong.di.component.AppComponent;

public abstract class BaseActivity<P extends BasePresenter> extends RxAppCompatActivity implements BaseContract.IView {

    protected static String TAG;
    protected Context mContext = null;
    protected Toolbar mToolbar;
    protected Unbinder mUnbinder;
    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        mContext = this;
        if (getLayoutResId() != 0) setContentView(getLayoutResId());
        mUnbinder = ButterKnife.bind(this);
        mToolbar = ButterKnife.findById(this, R.id.toolbar);
        if (mToolbar != null) initToolbar();
        setupActivityComponent(App.getAppComponent());
        if (mPresenter != null) mPresenter.attachView(this);
        initViews();
        initData();
    }

    protected void initToolbar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() == null) return;
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasParentActivity())
                    NavUtils.navigateUpFromSameTask(BaseActivity.this);
                else
                    onBackPressed();
            }
        });
    }

    /**
     * 设置本Activity关联的布局Id
     */
    protected abstract int getLayoutResId();

    /**
     * 依赖注入
     */
    protected void setupActivityComponent(AppComponent appComponent) {
    }

    /**
     * 初始化界面
     */
    protected abstract void initViews();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    public void setStatusBarColor(int color) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);
    }

    /**
     * 透明状态栏
     */
    public void setTransStatusBar() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY)
            mUnbinder.unbind();
        this.mUnbinder = null;
        if (mPresenter != null)
            mPresenter.detachView();//释放资源
        this.mPresenter = null;
    }

    //----返回父Activity---
    protected boolean hasParentActivity() {
        return false;
    }

    @Override
    public void onBackPressed() {
        if (hasParentActivity())
            NavUtils.navigateUpFromSameTask(this);
        else
            super.onBackPressed();
    }
}
