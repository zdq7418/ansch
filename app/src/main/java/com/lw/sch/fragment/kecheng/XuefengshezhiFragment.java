package com.lw.sch.fragment.kecheng;

import android.content.Intent;
import android.os.Bundle;
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
import com.lw.sch.R;
import com.lw.sch.activity.RenyuanActivity;
import com.lw.sch.entity.LwOptCurriculumStudents;
import com.lw.sch.entity.LwOptLogin;
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

public class XuefengshezhiFragment extends Fragment{
    private RecyclerView recyclerView;
    private List<LwOptCurriculumStudents> lwOptDepartments=new ArrayList<>();
    private CommonAdapter<LwOptCurriculumStudents> mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_xuefengshezhi, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.renyuan_recycleview);
        initData();
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.renyuan_swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                mAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));//这里用线性显示 类似于listview
        mAdapter=new CommonAdapter<LwOptCurriculumStudents>(getActivity(),R.layout.xibu_items,lwOptDepartments) {
            @Override
            protected void convert(ViewHolder holder, LwOptCurriculumStudents lwOptClass, int position) {
                holder.setText(R.id.xibuname, lwOptClass.getLwOptPersonnel().getPersonnelName());
            }
        };
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent=new Intent(getActivity(),RenyuanActivity.class);
                intent.putExtra("data", JsonTools.createJsonString(lwOptDepartments.get(position)));
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                final List<String> strings = new ArrayList<>();
                strings.add("删除");


                StyledDialog.buildBottomItemDialog( strings, "取消",  new MyItemDialogListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        if ("删除".equals(text)){
                            StyledDialog.buildIosAlert( "提示", "确认删除吗？",  new MyDialogListener() {
                                @Override
                                public void onFirst() {

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
        RequestParams params = new RequestParams(StaticSource.SERVICE+StaticSource.FINDALLPER);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd HH:mm:ss").create();
                List<LwOptCurriculumStudents> lwOptDepartmentss=gson.fromJson(result,new TypeToken<List<LwOptCurriculumStudents>>(){}.getType());
                lwOptDepartments.clear();
                for (LwOptCurriculumStudents l:lwOptDepartmentss){
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



}
