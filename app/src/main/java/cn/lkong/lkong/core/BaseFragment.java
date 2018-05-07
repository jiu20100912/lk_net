package cn.lkong.lkong.core;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.lkong.lkong.di.component.AppComponent;

public abstract class BaseFragment<P extends BasePresenter> extends RxFragment implements BaseContract.IView {

    protected static String TAG;
    protected ViewGroup parentView;
    protected FragmentActivity activity;
    protected LayoutInflater inflater;
    protected Context mContext = null;
    private Unbinder mUnbinder;
    @Inject
    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = (ViewGroup) inflater.inflate(getLayoutResId(), container, false);
        activity = super.getActivity();
        mContext = activity;
        this.inflater = inflater;
        mUnbinder = ButterKnife.bind(this, parentView);
        return parentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TAG = getClass().getSimpleName();
        setupActivityComponent(App.getAppComponent());
        if (mPresenter != null) mPresenter.attachView(this);
        initViews();
        initData();
    }

    /**
     * 设置本Fragment关联的布局Id
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY)
            mUnbinder.unbind();
        this.mUnbinder = null;
        if (mPresenter != null)
            mPresenter.detachView();//释放资源
        this.mPresenter = null;
    }
}
