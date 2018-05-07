package cn.lkong.lkong.core;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jiu20100912 on 2018/5/7.
 */
public class SPUtils {
    public static final String SP_NAME = "lkong_sp";

    private SharedPreferences mSP;

    public SPUtils(Context context) {
        mSP = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }
}
