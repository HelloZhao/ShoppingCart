package com.bwie.shoppingcart.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.shoppingcart.R;
import com.bwie.shoppingcart.bean.Bean;

import java.util.List;

/**
 * Created by lenovo on 2017/2/21.
 */

public class MyAdapter extends BaseAdapter {

    private final Context context;
    private final List<Bean> mShopList;
   private CheckInterface mCheckInterface;
    public MyAdapter(Context context, List<Bean> mShopList) {
        this.context = context;
        this.mShopList = mShopList;
    }
    public void setOnMyCheckInterface(CheckInterface mCheckInterface) {
        this.mCheckInterface = mCheckInterface;
    }
    public BtnClickListener btnClickListener;

    public void setBtnClickListener(BtnClickListener btnClickListener) {
        this.btnClickListener = btnClickListener;
    }

    @Override
    public int getCount() {
        return mShopList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MyViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new MyViewHolder();
            convertView = View.inflate(context, R.layout.item2, null);
            viewHolder.shopName = (TextView) convertView.findViewById(R.id.tv_intro_);
            viewHolder.shopPrice = (TextView) convertView.findViewById(R.id.tv_price_);
            viewHolder.jianMoney = (TextView) convertView.findViewById(R.id.tv_reduce_);
            viewHolder.shopNum = (TextView) convertView.findViewById(R.id.tv_num_);
            viewHolder.zengMoney = (TextView) convertView.findViewById(R.id.tv_add_);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.check_box_);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }
        viewHolder.shopName.setText(mShopList.get(position).getShopName());
        viewHolder.shopPrice.setText("¥" + mShopList.get(position).getShopPrice());
        viewHolder.checkBox.setChecked(mShopList.get(position).isChecked());
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.checkBox.isChecked()) {
                    mShopList.get(position).setChecked(true);
                    mCheckInterface.checkGroup(position,mShopList.get(position).isChecked());
                } else {
                    mShopList.get(position).setChecked(false);
                    mCheckInterface.checkGroup(position,mShopList.get(position).isChecked());
                }
            }
        });
        viewHolder.checkBox.setChecked(mShopList.get(position).isChecked());
        viewHolder.jianMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int shopNum = mShopList.get(position).getShopNum();
                shopNum--;
                mShopList.get(position).setShopNum(shopNum);
                if(shopNum<1){
                    mShopList.get(position).setShopNum(1);
                }
                viewHolder.shopNum.setText(mShopList.get(position).getShopNum()+ "");
                btnClickListener.BtnClickListener(position);
                notifyDataSetChanged();
            }
        });
        viewHolder.zengMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int shopNum = mShopList.get(position).getShopNum();
                shopNum++;
                mShopList.get(position).setShopNum(shopNum);
                viewHolder.shopNum.setText(mShopList.get(position).getShopNum()+ "");
                btnClickListener.BtnClickListener(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    class MyViewHolder {
        CheckBox checkBox;
        TextView shopName;
        TextView shopPrice;
        TextView jianMoney;
        TextView zengMoney;
        TextView shopNum;
    }


    public interface BtnClickListener {
        void BtnClickListener(int pos);
    }
    /**
     * 创建接口
     */
    public interface CheckInterface {
        void checkGroup(int position, boolean isChecked);
    }

}
