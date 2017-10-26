package com.liang.victor.myssrlib;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.shadowsocks.constant.State;
import com.github.shadowsocks.utils.SS_SDK;
import com.github.shadowsocks.utils.VpnCallback;


public class MainActivity extends Activity implements View.OnClickListener, VpnCallback {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        button = (Button) findViewById(R.id.switchButton);
        button.setOnClickListener(this);
        button.setText("未连接");
        SS_SDK.getInstance().setStateCallback(this);
    }

    @Override
    public void onClick(View v) {
        //这里仅提供调用示例，具体请按照自己的配置去设置ip    远程端口    密码         协议类型
        SS_SDK.getInstance().setProfile("45.76.111.76",9998,"53542468awdz","origin");
        SS_SDK.getInstance().switchVpn(this);
    }

    @Override
    public void callback(int state) {
        switch (state) {
            case State.CONNECTING:
                button.setText("连接中");
                break;
            case State.CONNECTED:
               button.setText("已连接");
                break;
            case State.STOPPED:
               button.setText("已停止");
                break;
            case State.STOPPING:
                button.setText("正在停止");
                break;
        }
    }

}
