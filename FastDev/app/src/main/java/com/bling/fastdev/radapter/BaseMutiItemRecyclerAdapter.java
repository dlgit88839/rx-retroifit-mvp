package com.bling.fastdev.radapter;

import android.content.Context;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 封装多种布局Adapter 继承自BaseRecyclerAdapter  数据源需要实现BaseRecycleViewAdapterModel 用于返回Itemtype
 * created by dongliang
 * 2018/3/29 18:08
 */
public abstract class BaseMutiItemRecyclerAdapter<T extends BaseRecycleViewAdapterModel> extends BaseRecyclerAdapter<T> {

    private SparseIntArray typeList;

    protected BaseMutiItemRecyclerAdapter(ArrayList<T> data, Context context) {
        super(data,context,-1);
    }

    /**
     * 添加多种type 布局
     * created by dongliang
     * 2018/3/27  10:37
     */
    public void addType(int type, int layoutId) {
        if (typeList == null) {
            typeList = new SparseIntArray();
        }
        typeList.put(type, layoutId);
    }

    /**
     * 直接传入 SparseArray 添加多种布局
     * created by dongliang
     * 2018/3/27  10:40
     */
    public void addType(SparseArray<Integer> map) {
        if (map != null) {
            for (int i = 0; i < map.size(); i++) {
                int type = map.keyAt(i);
                int layoutId = map.get(type);
                addType(type, layoutId);
            }
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (isHeaderPos(position)) {              //header
            return headerList.indexOfKey(position);
        }
        if (isFooterPos(position)) {
            return footerList.indexOfKey(position - getHeaderCount() - data.size());
        }
        return data.get(position - getHeaderCount()).dataItemType();         //如果不是header 或者footer 则根据pos获取相应的数据源对应的itemType
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType >= TYPE_HEADER && viewType < TYPE_FOOTER) {
            return new BaseViewHolder(headerList.get(viewType), viewType);
        }
        if (viewType >= TYPE_FOOTER) {
            return new BaseViewHolder(footerList.get(viewType), viewType);
        }
        if (typeList != null) {
            return new BaseViewHolder(mLayoutInflater.inflate(typeList.get(viewType), parent, false), viewType);
        }
        return null;
    }


}
