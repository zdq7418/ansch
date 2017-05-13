package com.lw.sch.fragment.banji;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.hss01248.dialog.interfaces.MyItemDialogListener;
import com.lw.sch.LoginActivity;
import com.lw.sch.MainActivity;
import com.lw.sch.R;
import com.lw.sch.activity.BanjiActivity;
import com.lw.sch.activity.XibuActivity;
import com.lw.sch.entity.LwOptClass;
import com.lw.sch.entity.LwOptDepartment;
import com.lw.sch.utils.JsonTools;
import com.lw.sch.utils.StaticSource;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cc.trity.floatingactionbutton.FloatingActionButton;

public class BanjiFragment extends Fragment{
    private RecyclerView recyclerView;
    private List<LwOptClass> lwOptDepartments=new ArrayList<>();
    private CommonAdapter<LwOptClass> mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MyReceiver myReceiver;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        myReceiver = new BanjiFragment.MyReceiver();
        getActivity().registerReceiver(myReceiver, new IntentFilter("BanjiFragment"));
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_banji, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.banji_recycleview);
        initData();
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.banji_swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        final FloatingActionButton fab= (FloatingActionButton) view.findViewById(R.id.fab_action_a);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BanjiActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));//这里用线性显示 类似于listview
        mAdapter=new CommonAdapter<LwOptClass>(getActivity(),R.layout.xibu_items,lwOptDepartments) {
            @Override
            protected void convert(ViewHolder holder, LwOptClass lwOptClass, int position) {
                holder.setText(R.id.xibuname, lwOptClass.getClassName());
            }
        };
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent=new Intent(getActivity(),BanjiActivity.class);
                intent.putExtra("data",JsonTools.createJsonString(lwOptDepartments.get(position)));
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, final int position) {
                final List<String> strings = new ArrayList<>();
                strings.add("删除");


                StyledDialog.buildBottomItemDialog( strings, "取消",  new MyItemDialogListener() {
                    @Override
                    public void onItemClick(CharSequence text, int poion) {
                        if ("删除".equals(text)){
                            StyledDialog.buildIosAlert( "提示", "确认删除吗？",  new MyDialogListener() {
                                @Override
                                public void onFirst() {
                                    RequestParams params = new RequestParams(StaticSource.SERVICE+StaticSource.DELETECLA);
                                    params.addBodyParameter("id",lwOptDepartments.get(position).getClassId().toString());
                                    x.http().post(params, new Callback.CommonCallback<String>() {
                                        @Override
                                        public void onSuccess(String result) {
                                            if ("1".equals(result)){
                                                Snackbar.make(recyclerView,"删除成功",Snackbar.LENGTH_LONG).show();
                                                initData();
                                            }

                                        }

                                        @Override
                                        public void onError(Throwable ex, boolean isOnCallback) {
                                        }

                                        @Override
                                        public void onCancelled(CancelledException cex) {
                                        }

                                        @Override
                                        public void onFinished() {
                                        }
                                    });
                                }

                                @Override
                                public void onSecond() {
                                }
                            }).setBtnText("确认","取消").show();
                        }
                    }

                    @Override
                    public void onBottomBtnClick() {
                    }
                }).show();
                return false;
            }
        });
        recyclerView.setAdapter(mAdapter);
        return view;
    }
    private void initData(){
        RequestParams params = new RequestParams(StaticSource.SERVICE+StaticSource.FINDALLCLA);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                List<LwOptClass> lwOptDep=gson.fromJson(result,new TypeToken<List<LwOptClass>>(){}.getType());
                lwOptDepartments.clear();
                for (LwOptClass l:lwOptDep){
                    lwOptDepartments.add(l);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void initData(String  key){
        RequestParams params = new RequestParams(StaticSource.SERVICE+StaticSource.FINDCLABYKEY);
        params.addBodyParameter("key",key);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                List<LwOptClass> lwOptDep=gson.fromJson(result,new TypeToken<List<LwOptClass>>(){}.getType());
                lwOptDepartments.clear();
                for (LwOptClass l:lwOptDep){
                    lwOptDepartments.add(l);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private class MyReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            String key=intent.getStringExtra("key");
            initData(key);
        }
    }


}
