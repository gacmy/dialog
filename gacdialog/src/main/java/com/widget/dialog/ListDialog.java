package com.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ListDialog {
    private Context context;
    private Dialog dialog;
    View mView;
    List<String> mList;
    ListView lv;
    ArrayAdapter<String> mAdapter;
    ListSelectListener mListener;

    private Display display;


    public ListDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context
                .WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public ListDialog builder() {
        mView = LayoutInflater.from(context).inflate(R.layout.dialog_gac_list,null);
        dialog = new Dialog(context, R.style.photo_dialog);
        dialog.setCancelable(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(mView);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        int width = display.getWidth();
        lp.width = width;
        window.setAttributes(lp);
        initView();

        return this;
    }
    private void initView() {
        lv = (ListView) mView.findViewById(R.id.lv);
        mList = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(context,R.layout.item_gac_textview,mList);
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mListener != null){
                    mListener.select(mList.get(position),position);
                }

                dialog.dismiss();
            }
        });
    }


    public ListDialog setContent(List<String> list) {
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
        return this;
    }

    public ListDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }







    public void show() {

        dialog.show();
    }


    public ListDialog setListSelectListener(ListSelectListener listener){
        mListener = listener;
        return this;
    }
    public interface ListSelectListener{
        public void select(String content,int pos);
    }
}
