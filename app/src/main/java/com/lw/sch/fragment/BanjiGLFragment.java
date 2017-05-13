package com.lw.sch.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.lw.sch.R;
import com.lw.sch.adapter.MFragmentPagerAdapter;
import com.lw.sch.fragment.banji.XibuFragment;
import com.lw.sch.utils.StaticSource;

import java.util.ArrayList;

public class BanjiGLFragment extends Fragment {


    //图片
    private TextView xibuguanli;
    //电影
    private TextView banjiguanli;
    //音乐

    //实现Tab滑动效果
    private ViewPager mViewPager;

    //动画图片
    private ImageView cursor;

    //动画图片偏移量
    private int offset = 0;
    private int position_one;
    private int position_two;

    //动画图片宽度
    private int bmpW;

    //当前页卡编号
    private int currIndex = 0;

    //存放Fragment
    private ArrayList<Fragment> fragmentArrayList;

    //管理Fragment
    private FragmentManager fragmentManager;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_banjigl, container, false);
        // Inflate the layout for this fragment
        xibuguanli = (TextView)view.findViewById(R.id.xibuguanli);
        //电影头标
        banjiguanli = (TextView) view.findViewById(R.id.banjiguanli);
        //音乐头标
        //初始化TextView
        cursor = (ImageView) view.findViewById(R.id.cursor);
        mViewPager = (ViewPager) view.findViewById(R.id.vPager);
        InitTextView();
        //初始化ImageView
        InitImageView();

        //初始化Fragment
        InitFragment();
        //初始化ViewPager
        InitViewPager();
        return view;
    }

    /**
     * 初始化头标
     */
    private void InitTextView(){

        //图片头标


        //添加点击事件
        xibuguanli.setOnClickListener(new MyOnClickListener(0));
        banjiguanli.setOnClickListener(new MyOnClickListener(1));
    }

    /**
     * 初始化页卡内容区
     */
    private void InitViewPager() {


        mViewPager.setAdapter(new MFragmentPagerAdapter(fragmentManager, fragmentArrayList));

        //让ViewPager缓存2个页面
        mViewPager.setOffscreenPageLimit(2);

        //设置默认打开第一页
        mViewPager.setCurrentItem(0);
        StaticSource.fragmentname="XibuFragment";
        //将顶部文字恢复默认值
        resetTextViewTextColor();
        xibuguanli.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));

        //设置viewpager页面滑动监听事件
        mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * 初始化动画
     */
    private void InitImageView() {

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        // 获取分辨率宽度
        int screenW = dm.widthPixels;

        bmpW = (screenW/2);

        //设置动画图片宽度
        setBmpW(cursor, bmpW);
        offset = 0;

        //动画图片偏移量赋值
        position_one = (int) (screenW / 2.0);
        position_two = position_one * 2;

    }

    /**
     * 初始化Fragment，并添加到ArrayList中
     */
    private void InitFragment(){
        fragmentArrayList = new ArrayList<Fragment>();
        fragmentArrayList.add(new XibuFragment());
        fragmentArrayList.add(new com.lw.sch.fragment.banji.BanjiFragment());

        fragmentManager =getChildFragmentManager();

    }



    /**
     * 头标点击监听
     * @author weizhi
     * @version 1.0
     */
    public class MyOnClickListener implements View.OnClickListener{
        private int index = 0 ;
        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mViewPager.setCurrentItem(index);
        }
    }

    /**
     * 页卡切换监听
     * @author weizhi
     * @version 1.0
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageSelected(int position) {
            Animation animation = null ;
            switch (position){

                //当前为页卡1
                case 0:
                    //从页卡1跳转转到页卡2
                    if(currIndex == 1){
                        animation = new TranslateAnimation(position_one, 0, 0, 0);
                        resetTextViewTextColor();
                        xibuguanli.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                    }else if(currIndex == 2){//从页卡1跳转转到页卡3
                        animation = new TranslateAnimation(position_two, 0, 0, 0);
                        resetTextViewTextColor();
                        xibuguanli.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                    }
                    StaticSource.fragmentname="XibuFragment";
                    break;

                //当前为页卡2
                case 1:
                    //从页卡1跳转转到页卡2
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_one, 0, 0);
                        resetTextViewTextColor();
                        banjiguanli.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                    } else if (currIndex == 2) { //从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_two, position_one, 0, 0);
                        resetTextViewTextColor();
                        banjiguanli.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                    }
                    StaticSource.fragmentname="BanjiFragment";
                    break;

            }
            currIndex = position;

            animation.setFillAfter(true);// true:图片停在动画结束位置
            animation.setDuration(300);
            cursor.startAnimation(animation);

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /**
     * 设置动画图片宽度
     * @param mWidth
     */
    private void setBmpW(ImageView imageView,int mWidth){
        ViewGroup.LayoutParams para;
        para = imageView.getLayoutParams();
        para.width = mWidth;
        imageView.setLayoutParams(para);
    }

    /**
     * 将顶部文字恢复默认值
     */
    private void resetTextViewTextColor(){

        xibuguanli.setTextColor(getResources().getColor(R.color.main_top_tab_color));
        banjiguanli.setTextColor(getResources().getColor(R.color.main_top_tab_color));
    }



}
