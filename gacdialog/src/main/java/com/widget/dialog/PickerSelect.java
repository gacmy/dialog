package com.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import java.util.List;

/**
 * Created by gacmy on 2017/4/18.
 */

public class PickerSelect {
    private Dialog seletorDialog;
    private PickerView pickerView;
    Context context;
    List<String> mList;
    TextView tv_cancle;
    TextView tv_select;
    TextView tv_title;
    SelectListener listener;
    String title;
    String selected="";
    public PickerSelect(Context context, List<String> list, String title) {
        this.context = context;
        mList = list;
        this.title = title;
        initDialog();
        initView();
        pickerView.setData(list);
    }
    public interface SelectListener{
        void onSelect(String str);
    }

    public void setSelectListener(SelectListener listener){
        this.listener = listener;
    }
    private void initView() {
        pickerView = (PickerView) seletorDialog.findViewById(R.id.pickerView);
        tv_cancle = (TextView) seletorDialog.findViewById(R.id.tv_cancle);
        tv_select = (TextView) seletorDialog.findViewById(R.id.tv_select);
        tv_title = (TextView) seletorDialog.findViewById(R.id.tv_title);
        tv_title.setText(title);

        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seletorDialog.dismiss();
            }
        });
        tv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSelect(selected);
                seletorDialog.dismiss();
            }
        });

    }
    private void initDialog() {
        if (seletorDialog == null) {
            seletorDialog = new Dialog(context, R.style.photo_dialog);
            seletorDialog.setCancelable(false);
            seletorDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            seletorDialog.setContentView(R.layout.dialog_gac_picker);
            Window window = seletorDialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            WindowManager.LayoutParams lp = window.getAttributes();
            int width = ScreenUtil.getInstance(context).getScreenWidth();
            lp.width = width;
            window.setAttributes(lp);
        }
    }
    public void show() {
        addListener();
        seletorDialog.show();
    }
    public void setSelected(String name){
        pickerView.setSelected(mList.indexOf(name));
        selected = name;
    }
    private void addListener() {

        pickerView.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text, int pos) {
             selected = text;
               // Log.d("PickerSelect","text:"+text+" pos:"+pos);
            }
        });
    }

}
