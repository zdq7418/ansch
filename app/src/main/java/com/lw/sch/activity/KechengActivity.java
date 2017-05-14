package com.lw.sch.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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
import com.lw.sch.entity.LwOptCurriculum;
import com.lw.sch.entity.LwOptDepartment;
import com.lw.sch.utils.JsonTools;
import com.lw.sch.utils.StaticSource;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class KechengActivity extends AppCompatActivity {
    private TextView banjititle;
    private EditText banjiname,banjinum;
    private Button banjisave;
    private Toolbar toolbar;
    private LwOptCurriculum lwOptClass;
    private String data;
    private Dialog styledDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kecheng);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        banjititle= (TextView) findViewById(R.id.kechengtitle);
        banjiname= (EditText) findViewById(R.id.kechengname);
        banjinum= (EditText) findViewById(R.id.kechengxuefeng);
        banjisave= (Button) findViewById(R.id.kechengsave);
        lwOptClass=new LwOptCurriculum();
        Intent intent=getIntent();
        if (intent!=null){
            data=intent.getStringExtra("data");
            if (data!=null){
                lwOptClass= StaticSource.gson.fromJson(data,LwOptCurriculum.class);
                banjititle.setText("查看详情");
                banjiname.setText(lwOptClass.getCurriculumName());
                banjinum.setText(lwOptClass.getCurriculumCredit().toString());

                banjiname.setEnabled(false);
                banjinum.setEnabled(false);
                banjisave.setText("编辑");
            }
        }
        banjisave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("编辑".equals(banjisave.getText().toString())){
                    banjiname.setEnabled(true);
                    banjinum.setEnabled(true);
                    banjisave.setText("保存");
                    return;
                }
                banjiname.setError(null);
                banjinum.setError(null);
                if (TextUtils.isEmpty(banjiname.getText().toString())){
                    banjiname.setError("请输入课程名称");
                    return;
                }
                if (banjiname.getText().toString().length()>20){
                    banjiname.setError("班级名称长度大于20");
                    return;
                }
                if (TextUtils.isEmpty(banjinum.getText().toString())){
                    banjinum.setError("请输入课程学分值");
                    return;
                }
                if (!JsonTools.isDouble(banjinum.getText().toString())){
                    banjinum.setError("请输入数字");
                    return;
                }

                String url="";
                RequestParams params =null;
                url=StaticSource.SERVICE+StaticSource.SAVECURR;
                params = new RequestParams(url);
                if (lwOptClass.getCurriculumId()!=null){
                    url=StaticSource.SERVICE+StaticSource.SAVEORUPCURR;
                    params = new RequestParams(url);
                    params.addBodyParameter("id",lwOptClass.getCurriculumId().toString());
                }
                params.addBodyParameter("name",banjiname.getText().toString());
                params.addBodyParameter("credit",banjinum.getText().toString());
                styledDialog= StyledDialog.buildLoading( "加载中...").setActivity(KechengActivity.this).show();
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


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
