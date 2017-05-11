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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lw.sch.R;
import com.lw.sch.entity.LwOptLogin;
import com.lw.sch.entity.LwOptPersonnel;
import com.lw.sch.utils.StaticSource;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

public class RenyuanActivity extends AppCompatActivity {
    private String data;
    private LwOptLogin lwOptLogin;
    private EditText name,acount,ruzhiriqi,isuse;
    private Toolbar toolbar;
    private RadioButton nan,nv;
    private RadioGroup sex;
    private Switch isteacher;
    private Button chongzhi,guoqi,bianji,quxiao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renyuan);
        toolbar = (Toolbar) findViewById(R.id.mydata_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent=getIntent();
       data=intent.getStringExtra("data");
        Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd HH:mm:ss").create();
        lwOptLogin=gson.fromJson(data,LwOptLogin.class);
        name= (EditText) findViewById(R.id.peiname);
        acount= (EditText) findViewById(R.id.peraccount);
        ruzhiriqi= (EditText) findViewById(R.id.ruzhiriqi);
        isuse= (EditText) findViewById(R.id.isuse);
        nan= (RadioButton) findViewById(R.id.nan);
        nv= (RadioButton) findViewById(R.id.nv);
        isteacher= (Switch) findViewById(R.id.isteacher);
        chongzhi= (Button) findViewById(R.id.chongzhi);
        guoqi= (Button) findViewById(R.id.guoqi);
        bianji= (Button) findViewById(R.id.banji);
        quxiao= (Button) findViewById(R.id.quxiao);
        sex= (RadioGroup) findViewById(R.id.sex);
        name.setText(lwOptLogin.getLwOptPersonnel().getPersonnelName());
        if ("男".equals(lwOptLogin.getLwOptPersonnel().getPersonnelSex())){
            nan.setChecked(true);
        }else{
            nv.setChecked(true);
        }
        ruzhiriqi.setText(lwOptLogin.getCreateDate().toString());
        acount.setText(lwOptLogin.getLoginName());
        if ("1".equals(lwOptLogin.getIsUse())){
            isuse.setText("使用");
        }else{
            isuse.setText("未使用");
        }
        if ("1".equals(lwOptLogin.getLwOptPersonnel().getIsTeacher()==null?"2":lwOptLogin.getLwOptPersonnel().getIsTeacher().toString())){
            isteacher.setChecked(true);
        }else{
            isteacher.setChecked(false);
        }
        chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        guoqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params = new RequestParams(StaticSource.SERVICE+StaticSource.OVERDUE);
                params.addBodyParameter("loginId",lwOptLogin.getLoginId().toString());
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if("0".equals(result)){
                            Snackbar.make(guoqi,"操作失败",Snackbar.LENGTH_LONG).show();
                        }else{
                            Snackbar.make(guoqi,"操作成功",Snackbar.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Snackbar.make(guoqi,"操作失败",Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        Snackbar.make(guoqi,"操作取消",Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFinished() {
                    }
                });
            }
        });

        bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("编辑".equals(bianji.getText())){
                    acount.setEnabled(true);
                    name.setEnabled(true);
                    nan.setEnabled(true);
                    nv.setEnabled(true);
                    isteacher.setEnabled(true);
                    quxiao.setVisibility(View.VISIBLE);
                    bianji.setText("保存");
                }else{
                    if (TextUtils.isEmpty(name.getText().toString())) {
                        name.setError("请输入姓名");
                        name.setFocusable(true);
                        return;
                    }
                    if (name.getText().toString().length()>5 || name.getText().toString().length()<2){
                        name.setError("姓名长度必须在2个到5个之间");
                        name.setFocusable(true);
                        return;
                    }

                    if (TextUtils.isEmpty(acount.getText().toString())) {
                        acount.setError("请输入账户名");
                        acount.setFocusable(true);
                        return;
                    }

                    if (acount.getText().toString().length()>14){
                        acount.setError("账户名长度不得超过14位");
                        acount.setFocusable(true);
                        return;
                    }
                    acount.setEnabled(false);
                    name.setEnabled(false);
                    nan.setEnabled(false);
                    nv.setEnabled(false);
                    isteacher.setEnabled(false);
                    quxiao.setVisibility(View.GONE);
                    LwOptPersonnel lwOptPersonnel=lwOptLogin.getLwOptPersonnel();
                    lwOptPersonnel.setIsTeacher(isteacher.isChecked()?1:0);
                    lwOptPersonnel.setPersonnelName(name.getText().toString());
                    lwOptPersonnel.setPersonnelSex(sex.getCheckedRadioButtonId()==R.id.nan?"男":"女");
                    lwOptLogin.setLoginName(acount.getText().toString());
                    lwOptLogin.setLwOptPersonnel(lwOptPersonnel);
                    bianji.setText("编辑");
                }

            }
        });
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acount.setEnabled(false);
                name.setEnabled(false);
                nan.setEnabled(false);
                nv.setEnabled(false);
                isteacher.setEnabled(false);
                quxiao.setVisibility(View.GONE);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
