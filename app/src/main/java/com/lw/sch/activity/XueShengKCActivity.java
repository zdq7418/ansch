package com.lw.sch.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hss01248.dialog.StyledDialog;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.lw.sch.R;
import com.lw.sch.entity.LwOptCurriculum;
import com.lw.sch.entity.LwOptCurriculumStudents;
import com.lw.sch.entity.LwOptDepartment;
import com.lw.sch.entity.LwOptPersonnel;
import com.lw.sch.utils.StaticSource;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class XueShengKCActivity extends AppCompatActivity {
    private MaterialSpinner xuesheng,kecheng;
    private Button xuesave;
    private List<LwOptCurriculum> ke=new ArrayList<>();
    private List<LwOptPersonnel> xue=new ArrayList<>();
    private LwOptPersonnel lp;
    private LwOptCurriculum lc;
    private LwOptCurriculumStudents lcs;
    private Toolbar toolbar;
    private TextView title;
    private String data;
    private Dialog styledDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xue_sheng_kc);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        xuesheng= (MaterialSpinner) findViewById(R.id.xuesheng_spinner);
        kecheng= (MaterialSpinner) findViewById(R.id.kechneg_spinner);
        xuesave= (Button) findViewById(R.id.xuesave);
        lcs=new LwOptCurriculumStudents();
        initKe();
        initXue();
        Intent intent=getIntent();
        if (intent!=null){
            data=intent.getStringExtra("data");
            if (data!=null){
                lcs=StaticSource.gson.fromJson(data,LwOptCurriculumStudents.class);
                xuesheng.setEnabled(false);
                kecheng.setEnabled(false);
                xuesave.setText("编辑");
            }
        }
        xuesheng.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                lp=xue.get(position);
                lcs.setLwOptPersonnel(lp);
            }
        });
        kecheng.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                lc=ke.get(position);
                lcs.setLwOptCurriculum(lc);
            }
        });
        xuesave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("编辑".equals(xuesave.getText().toString())){
                    xuesheng.setEnabled(true);
                    kecheng.setEnabled(true);
                    xuesave.setText("保存");
                    return;
                }
                if (lcs.getLwOptPersonnel().getPersonnelId()==0){
                    Snackbar.make(xuesave,"请选择学生",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (lcs.getLwOptCurriculum().getCurriculumId()==0){
                    Snackbar.make(xuesave,"请选择课程",Snackbar.LENGTH_LONG).show();
                    return;
                }
                String url="";
                RequestParams params =null;
                url=StaticSource.SERVICE+StaticSource.SAVECURRSTU;
                params = new RequestParams(url);
                if (lcs.getCurriculumPersonnelId()!=null){
                    url=StaticSource.SERVICE+StaticSource.SAVEORUPCURRSTU;
                    params = new RequestParams(url);
                    params.addBodyParameter("id",lcs.getCurriculumPersonnelId().toString());
                }
                params.addBodyParameter("personnel",StaticSource.gson.toJson(lcs.getLwOptPersonnel()));
                params.addBodyParameter("curriculum",StaticSource.gson.toJson(lcs.getLwOptCurriculum()));
                styledDialog= StyledDialog.buildLoading( "加载中...").setActivity(XueShengKCActivity.this).show();
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        styledDialog.dismiss();
                        if ("1".equals(result)){
                            Snackbar.make(xuesave,"保存成功",Snackbar.LENGTH_LONG).show();
                        }else{
                            Snackbar.make(xuesave,"该学生已存在该课程",Snackbar.LENGTH_LONG).show();
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
        });

    }

    private void initXue(){
        RequestParams params = new RequestParams(StaticSource.SERVICE + StaticSource.FINDALLPERTWO);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                xue = StaticSource.gson.fromJson(result, new TypeToken<List<LwOptPersonnel>>(){}.getType());
                LwOptPersonnel l=new LwOptPersonnel();
                l.setPersonnelId(0);
                l.setPersonnelName("请选择");
                xue.add(0,l);
                xuesheng.setItems(xue);
                for (int i=0;i<xue.size();i++){
                    if (lcs.getLwOptPersonnel().getPersonnelId().toString().equals(xue.get(i).getPersonnelId().toString())){
                        xuesheng.setSelectedIndex(i);
                        break;
                    }
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
    private void initKe(){
        RequestParams params = new RequestParams(StaticSource.SERVICE + StaticSource.FINDALLCURR);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ke = StaticSource.gson.fromJson(result, new TypeToken<List<LwOptCurriculum>>(){}.getType());
                LwOptCurriculum l=new LwOptCurriculum();
                l.setCurriculumId(0);
                l.setCurriculumName("请选择");
                ke.add(0,l);
                kecheng.setItems(ke);
                for (int i=0;i<ke.size();i++){
                    if (lcs.getLwOptCurriculum().getCurriculumId().toString().equals(ke.get(i).getCurriculumId().toString())){
                        kecheng.setSelectedIndex(i);
                        break;
                    }
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
