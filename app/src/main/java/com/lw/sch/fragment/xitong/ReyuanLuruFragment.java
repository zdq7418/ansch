package com.lw.sch.fragment.xitong;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.hss01248.dialog.interfaces.MyItemDialogListener;
import com.lw.sch.R;
import com.lw.sch.activity.BanjiActivity;
import com.lw.sch.entity.LwOptClass;
import com.lw.sch.entity.LwOptLogin;
import com.lw.sch.utils.StaticSource;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class ReyuanLuruFragment extends Fragment {
    private EditText pername, peracount, perpwd, perpwdpwd;
    private Button save;
    private Switch isteacher;
    private RadioGroup sex;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_renyuanluru, container, false);
        pername = (EditText) view.findViewById(R.id.peiname);
        peracount = (EditText) view.findViewById(R.id.peraccount);
        perpwd = (EditText) view.findViewById(R.id.perpwd);
        perpwdpwd = (EditText) view.findViewById(R.id.perpwdpwd);
        save = (Button) view.findViewById(R.id.queren);
        isteacher = (Switch) view.findViewById(R.id.isteacher);
        sex = (RadioGroup) view.findViewById(R.id.sex);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pername.setError(null);
                peracount.setError(null);
                perpwd.setError(null);
                perpwdpwd.setError(null);
                boolean cancel = false;
                View focusView = null;

                if (TextUtils.isEmpty(pername.getText().toString())) {
                    pername.setError("请输入姓名");
                    focusView = pername;
                    cancel = true;
                }
                if (pername.getText().toString().length() > 5 || pername.getText().toString().length() < 2) {
                    pername.setError("姓名长度必须在2个到5个之间");
                    focusView = pername;
                    cancel = true;
                }

                if (TextUtils.isEmpty(peracount.getText().toString())) {
                    peracount.setError("请输入账户名");
                    focusView = peracount;
                    cancel = true;
                }

                if (peracount.getText().toString().length() > 14) {
                    peracount.setError("账户名长度不得超过14位");
                    focusView = peracount;
                    cancel = true;
                }

                if (TextUtils.isEmpty(perpwd.getText().toString())) {
                    perpwd.setError("请输入密码");
                    focusView = perpwd;
                    cancel = true;
                }

                if (!perpwdpwd.getText().toString().equals(perpwd.getText().toString())) {
                    perpwdpwd.setError("两次密码不一致");
                    focusView = perpwdpwd;
                    cancel = true;
                }
                if (cancel) {
                    focusView.requestFocus();
                } else {

                    RequestParams params = new RequestParams(StaticSource.SERVICE + StaticSource.SAVEPERSONNEL);
                    params.addBodyParameter("peopleName", pername.getText().toString());
                    params.addBodyParameter("loginName", peracount.getText().toString());
                    params.addBodyParameter("loginSex", sex.getCheckedRadioButtonId() == R.id.nan ? "男" : "女");
                    params.addBodyParameter("loginPassword", perpwd.getText().toString());
                    params.addBodyParameter("isTeacher", isteacher.isChecked() ? "1" : "0");
                    x.http().post(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            if ("0".equals(result)) {
                                peracount.setError("账户已存在");
                            } else {
                                Snackbar.make(save, "保存成功", Snackbar.LENGTH_LONG).show();
                                pername.setText("");
                                peracount.setText("");
                                perpwd.setText("");
                                perpwdpwd.setText("");

                            }
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            Snackbar.make(save, "保存失败", Snackbar.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancelled(CancelledException cex) {
                            Snackbar.make(save, "取消操作", Snackbar.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFinished() {
                        }
                    });

                }
            }
        });
        return view;
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String key = intent.getStringExtra("key");

        }
    }

}
