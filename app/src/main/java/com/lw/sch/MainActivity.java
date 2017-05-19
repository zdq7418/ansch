package com.lw.sch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lw.sch.fragment.BanjiGLFragment;
import com.lw.sch.fragment.ChengjiGLFragment;
import com.lw.sch.fragment.KechengGLFragment;
import com.lw.sch.fragment.SusheGLFragment;
import com.lw.sch.fragment.XitongGLFragment;
import com.lw.sch.utils.StaticSource;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    DrawerLayout drawer;
    private BanjiGLFragment banjiGLFragment;
    private XitongGLFragment xitongGLFragment;
    private Toolbar toolbar;
    private SharedPreferences preferences;
    private SusheGLFragment susheGLFragment;
    private KechengGLFragment kechengGLFragment;
    private ChengjiGLFragment chengjiGLFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("成绩管理");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        banjiGLFragment=new BanjiGLFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, banjiGLFragment)
                .commit();
        toolbar.setTitle("班级管理");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        // 当SearchView获得焦点时弹出软键盘的类型，就是设置输入类型
        searchView.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        // 设置回车键表示查询操作
        searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        // 为searchView添加事件
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            // 输入后点击回车改变文本
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent();
                intent.setAction(StaticSource.fragmentname);
                intent.putExtra("key",query);
                sendBroadcast(intent);
                return false;
            }

            // 随着输入改变文本
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

       if (id == R.id.banjiguanli) {
            banjiGLFragment=new BanjiGLFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, banjiGLFragment)
                    .commit();
            toolbar.setTitle("班级管理");
        } else if (id == R.id.kechengguanli) {
            kechengGLFragment=new KechengGLFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, kechengGLFragment)
                    .commit();
            toolbar.setTitle("课程管理");
        }else if (id == R.id.xitonguanli) {
            xitongGLFragment=new XitongGLFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, xitongGLFragment)
                    .commit();
            toolbar.setTitle("系统管理");
        } else if (id == R.id.tuichu) {
            StyledDialog.buildIosAlert( "提示", "确认退出吗？",  new MyDialogListener() {
                @Override
                public void onFirst() {
                    preferences = getSharedPreferences("student", MODE_PRIVATE);
                    if(preferences.getString("loginFlag", "").equals("1")){
                        Intent intent=new Intent();
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("loginFlag", "0");
                        editor.commit();
                        intent.setClass(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                        return;
                    }
                }

                @Override
                public void onSecond() {
                }
            }).setBtnText("确认","取消").show();
        }
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
