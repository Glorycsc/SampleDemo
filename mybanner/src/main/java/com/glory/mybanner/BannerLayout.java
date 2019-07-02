package com.glory.mybanner;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Glory .
 */

public class BannerLayout extends LinearLayout {

    private ViewPager bannerViewPager;
    private LinearLayout bannerPointLayout;
    private int mPosition = 0;
    private int mBannerCount = 1;
    private Context context;
    private int bannerPointSize;
    private int bannerPointGravity;
    private int bannerPointDrawableSelected, bannerPointDrawableUnselected;
    private int bannerDelaySecond;
    private OnBannerClickListener onBannerClickListener;
    private Handler handler = new Handler();
    private Runnable runnable;
    private ViewGroup view;

    public BannerLayout(Context context) {
        this(context, null);

    }

    public BannerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public BannerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }


    private void initView(Context context, AttributeSet attrs) {
        this.context = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BannerLayout);
        bannerPointSize = typedArray.getDimensionPixelSize(R.styleable.BannerLayout_bannerPointSize, 10);
        bannerPointGravity = typedArray.getInt(R.styleable.BannerLayout_bannerPointGravity, Gravity.CENTER);
        bannerDelaySecond = typedArray.getInt(R.styleable.BannerLayout_bannerDelaySecond, 3);
        bannerPointDrawableSelected = typedArray.getResourceId(R.styleable.BannerLayout_bannerPointDrawableSelected, R.drawable.hightlight);
        bannerPointDrawableUnselected = typedArray.getResourceId(R.styleable.BannerLayout_bannerPointDrawableUnselected, R.drawable.normal);
        typedArray.recycle();
        view = (ViewGroup) View.inflate(context, R.layout.custom_banner_layout, null);
        addView(view);

        bannerViewPager = (ViewPager) view.findViewById(R.id.bannerViewPager);
        bannerPointLayout = (LinearLayout) view.findViewById(R.id.bannerPointLayout);
        //指示点的位置
        bannerPointLayout.setGravity(bannerPointGravity);
    }

    //接收 url 和 drawable类型的  或者R资源
    public void start(final List<Object> bannerList) {

        bannerShutdown();
//       view.removeView(bannerViewPager);
        mBannerCount = bannerList.size();
        BannerPagerAdapter bannerPagerAdapter = new BannerPagerAdapter(context, bannerList);
        bannerViewPager.setAdapter(bannerPagerAdapter);

        bannerViewPager.setCurrentItem(Integer.MAX_VALUE / 2 - bannerList.size());//设置为中间 , 默认为0 是不能向左滑动的
        bannerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addPointLayout(position % mBannerCount);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 1) {
                    //手动拖动，移除定时
                    bannerShutdown();
                } else {
                    bannerStart();
                }
            }
        });
//        view.addView(bannerViewPager);
        addPointLayout(0);
        bannerStart();
    }

    //添加指示点
    private void addPointLayout(int position) {
        if (mBannerCount == 1) {
            bannerPointLayout.setVisibility(GONE);
        } else {
            bannerPointLayout.setVisibility(VISIBLE);
            bannerPointLayout.removeAllViews();
            for (int i = 0; i < mBannerCount; i++) {
                ImageView imageView = new ImageView(context);
                LayoutParams layoutParams = new LayoutParams(bannerPointSize, bannerPointSize);
                layoutParams.setMargins(10, 0, 0, 0);
                imageView.setLayoutParams(layoutParams);

                if (position == i) {
                    imageView.setImageResource(bannerPointDrawableSelected);
                } else {
                    imageView.setImageResource(bannerPointDrawableUnselected);
                }
                bannerPointLayout.addView(imageView);
            }
        }

    }

    //开启轮播
    public void bannerStart() {
        if (runnable == null) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    mPosition = bannerViewPager.getCurrentItem();
//                    if (mPosition < mBannerCount - 1) {
//                        mPosition++;
//                    } else {
//                        mPosition = 0;
//                    }

                    bannerViewPager.setCurrentItem(++mPosition);
                    handler.postDelayed(this, bannerDelaySecond * 1000);//执行后
                }
            };
            handler.postDelayed(runnable, bannerDelaySecond * 1000);
        }
    }

    //移除定时任务
    public void bannerShutdown() {
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
            runnable = null;
        }
    }


    public interface OnBannerClickListener {
        void onBannerClick(View itemView, int position);

    }

    public int dp2px(float var0) {
        float var1 = context.getResources().getDisplayMetrics().density;
        return (int) (var0 * var1 + 0.5F);
    }

    public BannerLayout setBannerPointSize(int bannerPointSize) {
        this.bannerPointSize = dp2px(bannerPointSize);
        return this;
    }

    public BannerLayout setBannerPointGravity(int bannerPointGravity) {
        this.bannerPointGravity = bannerPointGravity;
        bannerPointLayout.setGravity(bannerPointGravity);
        return this;
    }

    public BannerLayout setBannerPointDrawableSelected(int bannerPointDrawableSelected) {
        this.bannerPointDrawableSelected = bannerPointDrawableSelected;
        return this;
    }

    public BannerLayout setBannerPointDrawableUnselected(int bannerPointDrawableUnselected) {
        this.bannerPointDrawableUnselected = bannerPointDrawableUnselected;
        return this;
    }

    //设置切换等待时间,
    public BannerLayout setBannerDelaySecond(int bannerDelaySecond) {
        this.bannerDelaySecond = bannerDelaySecond;
        return this;
    }

    //item的点击事件
    public BannerLayout setOnBannerClickListener(OnBannerClickListener onBannerClickListener) {
        this.onBannerClickListener = onBannerClickListener;
        return this;
    }


    /**
     * -----------------------------------------------------------------------------------------------
     * banner的适配器 继承自pagerAdapter
     * 实现无线循环
     * ①getCount 返回Integer最大值
     */
    private class BannerPagerAdapter extends PagerAdapter {
        private List<Object> bannerList = new ArrayList<>();
        private Context context;
        private List<View> viewList = new ArrayList<>();

        BannerPagerAdapter(Context context, List<Object> bannerList) {
            this.context = context;
            this.bannerList.clear();
            this.bannerList.addAll(bannerList);
            createViewList(bannerList);//把图片加载为ImageView
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //实例化item
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            Log.e("glz", position + "");
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            int realPosition = position % bannerList.size();
            final View view = viewList.get(realPosition);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onBannerClickListener != null) {
                        onBannerClickListener.onBannerClick(view, position);
                    }
                }
            });
            //如果该View已添加到Viewpager需先移除 否则会报错
            //java.lang.IllegalStateException: The specified child already has a parent. You must call removeView() on the child's parent first.
            if (((ViewPager) view.getParent()) != null) {
                ((ViewPager) view.getParent()).removeView(view);
            }
            container.addView(view, layoutParams);
            return view;
        }

        public List<View> createViewList(List<Object> bannerList) {

            for (int i = 0; i < bannerList.size(); i++) {
                //创建一个ViewList
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Object object = bannerList.get(i);
                Glide.with(context)
                        .load(object)
                        .apply(new RequestOptions().placeholder(R.drawable.default_bg))
                        .into(imageView);

                viewList.add(imageView);
            }
            return viewList;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
        }
    }
}
