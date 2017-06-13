package com.gac.widget.gacdialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.widget.dialog.IosAlertDialog;
import com.widget.dialog.ListDialog;
import com.widget.dialog.PhotoPickDialog;
import com.widget.dialog.PickerSelect;
import com.widget.dialog.ProgressDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button bt_ios;
    Button bt_progress;
    Button bt_photo;
    Button bt_list;
    Button bt_select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_ios = (Button)findViewById(R.id.bt_ios);
        bt_progress = (Button)findViewById(R.id.bt_progress);
        bt_photo = (Button)findViewById(R.id.bt_photo);
        bt_list = (Button)findViewById(R.id.bt_list);
        bt_select= (Button)findViewById(R.id.bt_select);
        setListener();
    }

    private void setListener(){
        bt_ios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new IosAlertDialog(MainActivity.this).builder().setCancelable(true).setMsg("ios alert dialog").setTitle("alert title")
//                       .show();
                new IosAlertDialog(MainActivity.this).builder().setCancelable(true).setMsg("ios alert dialog").setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).setTitle("alert title")
                        .show();
            }
        });



        bt_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ProgressDialog(MainActivity.this).builder().setCancelable(true).setMsg("加载对话框")
                        .setProgressColor(getResources().getColor(R.color.colorPrimary)).show();

            }
        });

        bt_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PhotoPickDialog(MainActivity.this).builder().setCancelable(true).setTakePhotoListener(new PhotoPickDialog.TakePhotoListener() {
                    @Override
                    public void takePhoto() {

                    }

                    @Override
                    public void pickPhoto() {

                    }
                }).show();
            }
        });

        bt_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> list = new ArrayList<String>();
                for(int i = 0; i < 10; i++){
                    list.add("第"+i+"条项目");
                }
                new ListDialog(MainActivity.this).builder().setCancelable(true).setContent(list).setListSelectListener(new ListDialog.ListSelectListener() {
                    @Override
                    public void select(String content, int pos) {
                        Toast.makeText(MainActivity.this,"content:"+content+" pos:"+pos,Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });

        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> list = new ArrayList<String>();
                for(int i = 0; i < 10; i++){
                    list.add("第"+i+"条项目");
                }
                PickerSelect pickerSelect = new PickerSelect(MainActivity.this,list,"title");
                pickerSelect.setSelected(list.get(5));
                pickerSelect.setSelectListener(new PickerSelect.SelectListener() {
                    @Override
                    public void onSelect(String str) {
                        Toast.makeText(MainActivity.this,"content:"+str,Toast.LENGTH_SHORT).show();
                    }

                });
                pickerSelect.show();
            }
        });

    }


}
