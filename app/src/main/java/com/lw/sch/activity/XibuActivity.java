package com.lw.sch.activity;

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

import com.lw.sch.R;
import com.lw.sch.entity.LwOptDepartment;
import com.lw.sch.utils.StaticSource;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class XibuActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText xibuname;
    private Button xibusave;
    private TextView title;
    private String data;
    private LwOptDepartment lwOptDepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xibu);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title= (TextView) findViewById(R.id.xubutitle);
        xibuname= (EditText) findViewById(R.id.xibuname);
        xibusave= (Button) findViewById(R.id.xibusave);
        lwOptDepartment=new LwOptDepartment();
        Intent intent=getIntent();
        if (intent!=null){
            data=intent.getStringExtra("data");
            if (data!=null){
                title.setText("查看详情");
                lwOptDepartment= StaticSource.gson.fromJson(data,LwOptDepartment.class);
                xibuname.setText(lwOptDepartment.getDepartmentName());
                xibuname.setEnabled(false);
                xibusave.setText("编辑");
            }
        }
        xibusave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("编辑".equals(xibusave.getText().toString())){
                   xibuname.setEnabled(true);
                    xibusave.setText("保存");
                    return;
                }
                String name=xibuname.getText().toString();
                xibuname.setError(null);
                if (TextUtils.isEmpty(name)){
                    xibuname.setError("请输入系部名称");
                    return;
                }
                if (name.length()>10){
                    xibuname.setError("系部名称长度大于10");
                    return;
                }
                xibuname.setEnabled(false);
                String url="";
                RequestParams params =null;
                    url=StaticSource.SERVICE+StaticSource.SAVEDE;
                    params = new RequestParams(url);
                    if (lwOptDepartment.getDepartmentId()!=null){
                        url=StaticSource.SERVICE+StaticSource.SAVEORUPDE;
                        params = new RequestParams(url);
                        params.addBodyParameter("id",lwOptDepartment.getDepartmentId().toString());
                    }


                params.addBodyParameter("name",name);
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if("0".equals(result)){
                            Snackbar.make(xibusave,"操作失败",Snackbar.LENGTH_LONG).show();
                        }else{
                            Snackbar.make(xibusave,"操作成功",Snackbar.LENGTH_LONG).show();
                        }
                        xibuname.setEnabled(true);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Snackbar.make(xibusave,"操作失败",Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        Snackbar.make(xibusave,"操作取消",Snackbar.LENGTH_LONG).show();
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
