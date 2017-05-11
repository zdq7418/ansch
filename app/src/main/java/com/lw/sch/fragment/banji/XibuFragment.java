package com.lw.sch.fragment.banji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.hss01248.dialog.interfaces.MyItemDialogListener;
import com.lw.sch.R;
import com.lw.sch.activity.BanjiActivity;
import com.lw.sch.entity.LwOptClass;
import com.lw.sch.entity.LwOptDepartment;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;


public class XibuFragment extends Fragment{
    private RecyclerView recyclerView;
    private List<LwOptDepartment> lwOptDepartments=new ArrayList<>();
    private CommonAdapter<LwOptDepartment> mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_xibu, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.xibu_recycleview);
        initData();
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.xibu_swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                for (int i=20;i<30;i++){
                    lwOptDepartments.add(new LwOptDepartment("软件"+i+"系"));
                }
                mAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));//这里用线性显示 类似于listview
        mAdapter=new CommonAdapter<LwOptDepartment>(getActivity(),R.layout.xibu_items,lwOptDepartments) {
            @Override
            protected void convert(ViewHolder holder, LwOptDepartment lwOptClass, int position) {
                holder.setText(R.id.xibuname, lwOptClass.getDepartmentName());
            }
        };
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent=new Intent(getActivity(),BanjiActivity.class);
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
        for (int i=0;i<20;i++){
            lwOptDepartments.add(new LwOptDepartment("软件"+i+"系"));
        }
    }
}
