package io.github.coreycao.emojiboard;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sycao on 26/01/2018.
 * 自定义表情面板的gridAdapter
 * 最后一个为删除按钮，做了单独处理
 */

public class EmojiGridAdapter extends BaseAdapter {

    private Context mContext;

    private List<CEmoji> mData = new ArrayList<>();

    public EmojiGridAdapter(Context context, List<CEmoji> data) {
        mContext = context;
        mData.clear();
        mData.addAll(data);
        mData.add(EmojiHelper.emojiDelete);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mData.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = null;
        //如果没有可以重用的控件
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            rootView = inflater.inflate(R.layout.item_emoji, parent, false); //加载布局，创建View
        } else {
            //控件己经被创建过，直接重用
            rootView = convertView;
        }
        //依据位置提取相应的数据源对象
        CEmoji item = mData.get(position);
        //获取用于显示内容的控件的引用
        ImageView iv = (ImageView) rootView.findViewById(R.id.iv_emoji);
        //设置显示内容
        if (!TextUtils.isEmpty(item.emojiPath)) {
            Glide.with(mContext).load(item.emojiPath).into(iv);
        }
        return rootView;
    }
}
