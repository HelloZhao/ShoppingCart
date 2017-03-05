package com.bwie.shoppingcart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwie.shoppingcart.adapter.MyAdapter;
import com.bwie.shoppingcart.bean.Bean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,MyAdapter.CheckInterface{

    private TextView tv_fragment_shopcar_reset;
    private ListView lv_fragment_shopcar_cart;
    private CheckBox total_check_fragment_shopcar;
    private TextView tv_fragment_shopcar_total_sum;
    private Button btn_fragment_shopcar_pushsum;
    private List<Bean> mShopList;
    private RelativeLayout relative_jiesuan, relative_delteView;
    private MyAdapter adapter;
    int totalCount = 0;
    double totalPrice = 0;
    private Button btn_fragment_shopcar_delet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
        addAdapter();

    }

    //添加适配器
    private void addAdapter() {
        adapter = new MyAdapter(this, mShopList);
        lv_fragment_shopcar_cart.setAdapter(adapter);
        total_check_fragment_shopcar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    for (int i = 0; i < mShopList.size(); i++) {
                        mShopList.get(i).setChecked(true);
                    }
                } else {
                    for (int i = 0; i < mShopList.size(); i++) {
                        mShopList.get(i).setChecked(false);
                    }
                }
                calculate();
                adapter.notifyDataSetChanged();
            }
        });
        adapter.setBtnClickListener(new MyAdapter.BtnClickListener() {
            @Override
            public void BtnClickListener(int pos) {
                calculate();
            }
        });
        adapter.setOnMyCheckInterface(this);
    }

    //初始化数据
    private void initData() {
        mShopList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mShopList.add(new Bean("荆泽阳" + i, 1, 10));
        }
    }

    //初始化控件
    private void initViews() {
        tv_fragment_shopcar_reset = (TextView) findViewById(R.id.tv_fragment_shopcar_reset);
        lv_fragment_shopcar_cart = (ListView) findViewById(R.id.lv_fragment_shopcar_cart);
        total_check_fragment_shopcar = (CheckBox) findViewById(R.id.total_check_fragment_shopcar);
        tv_fragment_shopcar_total_sum = (TextView) findViewById(R.id.tv_fragment_shopcar_total_sum);
        btn_fragment_shopcar_pushsum = (Button) findViewById(R.id.btn_fragment_shopcar_pushsum);
        relative_jiesuan = (RelativeLayout) findViewById(R.id.relative_jiesuan);
        relative_delteView = (RelativeLayout) findViewById(R.id.relative_delteView);
        btn_fragment_shopcar_delet = (Button) findViewById(R.id.btn_fragment_shopcar_delet);
        tv_fragment_shopcar_reset.setOnClickListener(this);
        btn_fragment_shopcar_delet.setOnClickListener(this);
    }

    boolean flag = true;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_fragment_shopcar_reset:
                if (flag) {
                    tv_fragment_shopcar_reset.setText("编辑");
                    relative_delteView.setVisibility(View.VISIBLE);
                    relative_jiesuan.setVisibility(View.GONE);
                    flag = false;
                } else {
                    tv_fragment_shopcar_reset.setText("完成");
                    relative_jiesuan.setVisibility(View.VISIBLE);
                    relative_delteView.setVisibility(View.GONE);
                    flag = true;
                }
                break;
            case R.id.btn_fragment_shopcar_delet:
                for (int i = 0; i < mShopList.size(); i++) {
                    if(total_check_fragment_shopcar.isChecked()){
                        mShopList.clear();
                    }else{
                        mShopList.remove(i);
                    }
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void checkGroup(int position, boolean isChecked) {
        calculate();
    }
    /**
     * 统计操作<br>
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
     * 3.给底部的textView进行数据填充
     */
    private void calculate() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < mShopList.size(); i++) {
            Bean bean = mShopList.get(i);
            if (bean.isChecked()) {
                int count = bean.getShopNum();
                totalCount += count;
                totalPrice += bean.getShopPrice() * bean.getShopNum();
            }
        }
        tv_fragment_shopcar_total_sum.setText("￥" + totalPrice);
        btn_fragment_shopcar_pushsum.setText("去支付(" + totalCount + ")");
    }


}
