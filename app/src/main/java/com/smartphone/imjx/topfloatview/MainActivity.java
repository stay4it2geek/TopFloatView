package com.smartphone.imjx.topfloatview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * scrollview 顶部悬浮
 */
public class MainActivity extends AppCompatActivity implements ScrollViewListener, ImageViewpagerView.OnItemClickListener {

    //屏幕顶部悬浮相对性布局
    private RelativeLayout topFloatRelativelayout;
    //tab的线性布局
    private LinearLayout tabLinearLayout;
    //有banner的tab布局与其父类布局的顶部距离
    private int tabBannerLayoutTop;
    //banner
    private ImageViewpagerView bannerview;
    private MyScrollview mScrollView;
    private HorizontalScrollView horizontalScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLinearLayout = (LinearLayout) findViewById(R.id.tabLinearLayout);
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);

        bannerview = (ImageViewpagerView) findViewById(R.id.bannerview);
        topFloatRelativelayout = (RelativeLayout) findViewById(R.id.topfloatLayout);
        List<String> imgUrls = new ArrayList<>();
        List<String> urls = new ArrayList<>();
        imgUrls.add("http://pic2.ooopic.com/11/65/54/78bOOOPICca_1024.jpg");
        imgUrls.add(" http://pic.58pic.com/58pic/17/47/54/86k58PICcip_1024.jpg");
        urls.add("http://pic2.ooopic.com/11/65/54/78bOOOPICca_1024.jpg");
        urls.add(" http://pic.58pic.com/58pic/17/47/54/86k58PICcip_1024.jpg");
        ((ImageViewpagerView) findViewById(R.id.bannerview)).setImagesUrl(imgUrls,urls);
        ((ImageViewpagerView) findViewById(R.id.bannerview)).setOnItemClickListener(this);
        mScrollView = (MyScrollview) findViewById(R.id.scrollview);
        mScrollView.setScrollViewListener(this);
    }

    @Override
    public void getScrollVerticalValue(int yValue) {
        if (yValue > tabBannerLayoutTop) {
            if (horizontalScrollView.getParent() != topFloatRelativelayout) {
                tabLinearLayout.removeView(horizontalScrollView);
                topFloatRelativelayout.setVisibility(View.VISIBLE);
                topFloatRelativelayout.addView(horizontalScrollView);

            }

        } else if (yValue < tabBannerLayoutTop || yValue == tabBannerLayoutTop) {

            if (horizontalScrollView.getParent() != tabLinearLayout) {
                topFloatRelativelayout.removeView(horizontalScrollView);
                topFloatRelativelayout.setVisibility(View.GONE);
                tabLinearLayout.addView(horizontalScrollView);
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            tabBannerLayoutTop = bannerview.getBottom();//获取bannerlayout的底部位置

        }
    }

    @Override
    public void onItemClick(int position,String url) {
        Toast.makeText(this,"你点击了第"+position+"个图片"+"\r\n+ 跳转地址是"+url,Toast.LENGTH_SHORT).show();
    }
}
