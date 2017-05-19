package com.lw.sch.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hss01248.dialog.StyledDialog;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.lw.sch.R;
import com.lw.sch.entity.LwOptClass;
import com.lw.sch.entity.LwOptDepartment;
import com.lw.sch.utils.JsonTools;
import com.lw.sch.utils.StaticSource;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class BanjiActivity extends AppCompatActivity {
    private MaterialSpinner xibuspinner;
    private TextView banjititle;
    private EditText banjiname,banjinum;
    private Button banjisave;
    private Toolbar toolbar;
    private List<LwOptDepartment> lwOptDepartments=new ArrayList<>();
    private LwOptClass lwOptClass;
    private String data;
    private Dialog styledDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banji);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        xibuspinner= (MaterialSpinner) findViewById(R.id.xibu_spinner);
        banjititle= (TextView) findViewById(R.id.banjititle);
        banjiname= (EditText) findViewById(R.id.banjiname);
        banjinum= (EditText) findViewById(R.id.banjinum);
        banjisave= (Button) findViewById(R.id.banjisave);
        lwOptClass=new LwOptClass();
        initData();
        Intent intent=getIntent();
        if (intent!=null){
            data=intent.getStringExtra("data");
            if (data!=null){
                lwOptClass= StaticSource.gson.fromJson(data,LwOptClass.class);
                banjititle.setText("查看详情");
                banjiname.setText(lwOptClass.getClassName());
                banjinum.setText(lwOptClass.getClassNumber().toString());

                banjiname.setEnabled(false);
                banjinum.setEnabled(false);
                xibuspinner.setEnabled(false);
                banjisave.setText("编辑");
            }
        }
        banjisave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("编辑".equals(banjisave.getText().toString())){
                    banjiname.setEnabled(true);
                    banjinum.setEnabled(true);
                    xibuspinner.setEnabled(true);
                    banjisave.setText("保存");
                    return;
                }
                banjiname.setError(null);
                banjinum.setError(null);
                if (TextUtils.isEmpty(banjiname.getText().toString())){
                    banjiname.setError("请输入班级名称");
                    return;
                }
                if (banjiname.getText().toString().length()>20){
                    banjiname.setError("班级名称长度大于20");
                    return;
                }
                if (TextUtils.isEmpty(banjinum.getText().toString())){
                    banjinum.setError("请输入班级人数");
                    return;
                }
                if (!JsonTools.isInteger(banjinum.getText().toString())){
                    banjinum.setError("请输入整数");
                    return;
                }
                if ("0".equals(lwOptClass.getClassDepartment()) || "".equals(lwOptClass.getClassDepartment()) || null==lwOptClass.getClassDepartment()){
                    Snackbar.make(banjisave,"请选择系部",Snackbar.LENGTH_LONG).show();
                    return;
                }
                String url="";
                RequestParams params =null;
                url=StaticSource.SERVICE+StaticSource.SAVECLA;
                params = new RequestParams(url);
                if (lwOptClass.getClassId()!=null){
                    url=StaticSource.SERVICE+StaticSource.SAVEORUPCLA;
                    params = new RequestParams(url);
                    params.addBodyParameter("id",lwOptClass.getClassId().toString());
                }
                params.addBodyParameter("name",banjiname.getText().toString());
                params.addBodyParameter("number",banjinum.getText().toString());
                params.addBodyParameter("departmentId",lwOptClass.getClassDepartment());
                styledDialog= StyledDialog.buildLoading( "加载中...").setActivity(BanjiActivity.this).show();
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        styledDialog.dismiss();
                        if ("1".equals(result)){
                            Snackbar.make(banjisave,"保存成功",Snackbar.LENGTH_LONG).show();
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
        xibuspinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                lwOptClass.setClassDepartment(lwOptDepartments.get(position).getDepartmentId().toString());
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

    private void initData() {
        RequestParams params = new RequestParams(StaticSource.SERVICE + StaticSource.FINDALLDE);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                lwOptDepartments = StaticSource.gson.fromJson(result, new TypeToken<List<LwOptDepartment>>(){}.getType());
                LwOptDepartment l=new LwOptDepartment();
                l.setDepartmentId(0);
                l.setDepartmentName("请选择");
                lwOptDepartments.add(0,l);
                xibuspinner.setItems(lwOptDepartments);
                for (int i=0;i<lwOptDepartments.size();i++){
                    if (lwOptClass.getClassDepartment().equals(lwOptDepartments.get(i).getDepartmentId().toString())){
                        xibuspinner.setSelectedIndex(i);
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
}
