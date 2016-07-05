package com.kyletung.simplebookmovie.view.recycler;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * All rights reserved by <a href="http://www.yanze.com">YanZe</a>
 * Created by DongYuhui on 2016/1/14.
 * Base Recycler View
 */
public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {

    protected int resource;
    protected ArrayList<T> mListData;

    protected Context mContext;
    protected Activity mActivity;
    protected Fragment mFragment;

    public BaseRecyclerAdapter(Context context, int resource) {
        this.mContext = context;
        this.resource = resource;
        mListData = new ArrayList<>();
    }

    public BaseRecyclerAdapter(Context context, int resource, Activity activity) {
        this(context, resource);
        this.mActivity = activity;
    }

    public BaseRecyclerAdapter(Context context, int resource, Fragment fragment) {
        this(context, resource);
        this.mFragment = fragment;
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
    public void putList(ArrayList<T> list) {
        mListData.clear();
        notifyDataSetChanged();
        for (int i = 0; i < list.size(); i++) {
            mListData.add(list.get(i));
            notifyItemInserted(mListData.size() - 1);
        }
    }

    /**
     * 添加列表内容
     *
     * @param list 列表内容
     */
    public void addList(ArrayList<T> list) {
        for (int i = 0; i < list.size(); i++) {
            mListData.add(list.get(i));
            notifyItemInserted(mListData.size() - 1);
        }
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
