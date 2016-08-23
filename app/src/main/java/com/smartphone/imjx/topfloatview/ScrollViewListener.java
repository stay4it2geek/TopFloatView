package com.smartphone.imjx.topfloatview;

/**
 * Created by imjx on 2016/8/23.
 *
 * 暴露给外部的监听接口，监听scrollview 的滚动，返回滚动值
 */
public interface ScrollViewListener {
    void getScrollVerticalValue(int yValue);
}
