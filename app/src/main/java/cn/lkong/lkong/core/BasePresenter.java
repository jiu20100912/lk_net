package cn.lkong.lkong.core;

/**
 * Created by jiu20100912 on 2017/7/28.
 */

public class BasePresenter<T extends BaseContract.IView> implements BaseContract.IPresenter<T> {

    protected T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }
}
