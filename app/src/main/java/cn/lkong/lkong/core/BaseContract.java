package cn.lkong.lkong.core;

/**
 * Created by jiu20100912 on 2017/7/28.
 */

public interface BaseContract {

    interface IPresenter<T> {

        void attachView(T view);

        void detachView();
    }

    interface IView {

        void showError();

        void complete();

        void stopWaiting();
    }
}
