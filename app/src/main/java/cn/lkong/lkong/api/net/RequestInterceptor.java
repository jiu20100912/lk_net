package cn.lkong.lkong.api.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
