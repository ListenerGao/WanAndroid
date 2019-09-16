package com.listenergao.wanandroid.http.test;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.listenergao.wanandroid.base.BaseActivity;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int[] a = new int[10];
        TextView[] textNum = new Button[10];

        // java多态
        TextView textView = new Button(this);

        TextView tv = new TextView(this);
        Button bt = new Button(this);

        List<Button> buttonList = new ArrayList<>();
//        报错，java的泛型具有不可变性
//        List<TextView> textViews = buttonList;
        //使用 上界通配符 ? extends,来使java泛型具有协变性
        List<? extends TextView> textViewList = buttonList;
        TextView textView1 = textViewList.get(0);

        List<? extends TextView> t1 = new ArrayList<>();
        List<? extends TextView> t2 = new ArrayList<Button>();
        List<? extends TextView> t3 = new ArrayList<RadioButton>();


    }
}
