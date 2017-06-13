package com.widget.dialog;

import android.app.Dialog;
import android.content.Context;

import android.util.DisplayMetrics;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import android.view.Window;
import android.view.WindowManager;

import android.widget.TextView;


public class PhotoPickDialog {
    private Context context;


    private TextView tv_takephoto;
    TextView tv_photo;

    TextView tv_cancle;
    TakePhotoListener mListener;
    View mView;
    private Dialog dialog;

    //private Display display;


    public PhotoPickDialog(Context context) {
        this.context = context;
//        WindowManager windowManager = (WindowManager) context.getSystemService(Context
//                .WINDOW_SERVICE);
        //display = windowManager.getDefaultDisplay();
    }

    public PhotoPickDialog builder() {
        if (dialog == null) {
            mView = LayoutInflater.from(context).inflate(R.layout.dialog_photo_picker,null);
            dialog = new Dialog(context, R.style.photo_dialog);
            dialog.setCancelable(true);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(mView);
            Window window = dialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            WindowManager.LayoutParams lp = window.getAttributes();
            WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(dm);
            int width = dm.widthPixels;
            lp.width = width;
            window.setAttributes(lp);
        }
        initView();
        return this;
    }
    private void initView() {
        tv_takephoto = (TextView) mView.findViewById(R.id.tv_takephoto);
        tv_cancle = (TextView) mView.findViewById(R.id.tv_cancel);
        tv_photo = (TextView) mView.findViewById(R.id.tv_photo);
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.takePhoto();
                }

                dialog.dismiss();
            }
        });
        tv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.pickPhoto();
                }
                dialog.dismiss();
            }
        });
    }


    public PhotoPickDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public void show() {
        dialog.show();
    }

    public PhotoPickDialog setTakePhotoListener(TakePhotoListener listener){
        mListener = listener;
        return this;
    }
    public interface TakePhotoListener{
        public void takePhoto();
        public void pickPhoto();
    }
}
