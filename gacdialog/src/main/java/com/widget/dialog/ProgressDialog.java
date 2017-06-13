package com.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ProgressDialog {
    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private TextView tv_msg;

    private Display display;
    ProgressWheel wheel;

    public ProgressDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context
                .WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public ProgressDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_gac_progress, null);
        wheel = (ProgressWheel)view.findViewById(R.id.progress);
        wheel.setBarColor(context.getResources().getColor(R.color.colorAccent));
        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        tv_msg = (TextView)view.findViewById(R.id.tv_msg);
        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
       /* lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth() * 0.8)
                , LayoutParams.WRAP_CONTENT));*/
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (display.getWidth() * 0.8);
        setListener();
        return this;
    }

    public ProgressDialog setProgressColor(int color){
        if(wheel != null){
            wheel.setBarColor(color);
        }
        return this;
    }
    private void startSpin(){
        if(wheel != null){
            wheel.spin();
        }
    }
    private void stopSpin(){
        if(wheel != null){
            wheel.stopSpinning();
        }
    }
    public ProgressDialog setMsg(String msg) {

        if ("".equals(msg)) {
            tv_msg.setText("正在加载...");
        } else {
            tv_msg.setText(msg);
        }
        return this;
    }


    private void setListener(){
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                stopSpin();
            }
        });
    }
    public ProgressDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public void show() {
       // setLayout();
        if(dialog != null){
            dialog.show();
            startSpin();
        }

    }
}
