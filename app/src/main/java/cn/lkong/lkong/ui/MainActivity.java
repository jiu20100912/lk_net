package cn.lkong.lkong.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import cn.lkong.lkong.R;
import cn.lkong.lkong.core.BaseActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tab_main)
    TabLayout mTabMain;
    @BindView(R.id.vp_main)
    ViewPager mVpMain;
    @BindView(R.id.drawer)
    DrawerLayout mDrawer;

    private Fragment[] mFragments;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        setTransStatusBar();
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.app_main, R.string.app_main);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerToggle.syncState();
        mDrawer.addDrawerListener(mDrawerToggle);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void stopWaiting() {

    }

    private void closeDrawer() {
        mDrawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    class MainPageAdapter extends FragmentStatePagerAdapter {

        public MainPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }

}
