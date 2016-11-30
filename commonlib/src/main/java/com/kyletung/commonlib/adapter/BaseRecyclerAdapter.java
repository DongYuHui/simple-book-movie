package com.kyletung.commonlib.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DongYuHui on 2016/1/14.
 * Base Recycler View
 */
public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {

    protected int resource;
    protected List<T> mListData;

    protected Context mContext;

    public BaseRecyclerAdapter(Context context, int resource) {
        this.mContext = context;
        this.resource = resource;
        mListData = new ArrayList<>();
    }

    /**
     * 生成 View
     *
     * @param parent ViewGroup
     * @return 返回 View
     */
    protected View createView(ViewGroup parent) {
        return LayoutInflater.from(mContext).inflate(resource, parent, false);
    }

    /**
     * 替换列表内容
     *
     * @param list 列表内容
     */
    public void putList(List<T> list) {
        if (list == null) return;
        mListData.clear();
        notifyDataSetChanged();
        mListData.addAll(list);
        notifyItemRangeInserted(0, list.size() - 1);
//        for (int i = 0; i < list.size(); i++) {
//            mListData.add(list.get(i));
//            notifyItemInserted(mListData.size() - 1);
//        }
    }

    /**
     * 添加列表内容
     *
     * @param list 列表内容
     */
    public void addList(List<T> list) {
        int start = mListData.size();
        mListData.addAll(list);
        notifyItemRangeInserted(start, list.size() + start - 1);
//        for (int i = 0; i < list.size(); i++) {
//            mListData.add(list.get(i));
//            notifyItemInserted(mListData.size() - 1);
//        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateDataViewHolder(parent, viewType);
    }

    /**
     * 生成自定义 ViewHolder
     *
     * @param parent   父布局
     * @param viewType ViewType
     * @return 返回自定义 ViewHolder 实例
     */
    public abstract VH onCreateDataViewHolder(ViewGroup parent, int viewType);

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindDataViewHolder((VH) holder, position);
    }

    /**
     * 绑定数据
     *
     * @param holder   ViewHolder
     * @param position 位置
     */
    public abstract void onBindDataViewHolder(VH holder, int position);

    @Override
    public int getItemCount() {
        return mListData.size();
    }

}
