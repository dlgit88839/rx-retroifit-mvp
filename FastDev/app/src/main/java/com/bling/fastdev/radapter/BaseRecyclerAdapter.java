package com.bling.fastdev.radapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装BaseRecycleraAdapter
 * created by dongliang
 * 2018/3/26  18:08
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    protected List<T> data = new ArrayList<>(); //数据源
    protected int TYPE_HEADER = 100000;  //HEADER 的type从100000开始 防止重复 可以调用set方法修改应该 数据源type特殊的情况
    protected int TYPE_FOOTER = 200000;
    protected Context context;
    protected int layoutId = -1;
    protected LayoutInflater mLayoutInflater;
    protected SparseArray<View> footerList;     //用于存放 footview
    protected SparseArray<View> headerList;
    protected int animId = -1;
    private ItemClickListener itemClickListener; //item 点击监听
    private ItemLongClickListener itemLongClickListener; //item 长按监听

    protected int lastPosition = -1;//记录最后一个进入的 item 用于动画的判断

    private BaseRecyclerAdapter(ArrayList<T> data, Context context) {
        this.data = data;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    /**
     * 用于只有单一布局时简单布局ID传递
     * created by dongliang
     * 2018/3/28  18:03
     */
    public BaseRecyclerAdapter(ArrayList<T> data, Context context, int layoutId) {
        this(data, context);
        this.layoutId = layoutId;
    }

    public int getTYPE_HEADER() {
        return TYPE_HEADER;
    }

    public void setTYPE_HEADER(int TYPE_HEADER) {
        this.TYPE_HEADER = TYPE_HEADER;
    }

    public int getTYPE_FOOTER() {
        return TYPE_FOOTER;
    }

    public void setTYPE_FOOTER(int TYPE_FOOTER) {
        this.TYPE_FOOTER = TYPE_FOOTER;
    }

    /**
     * 添加header
     * 用TYPE_HEADER+hearderList.size  用于type类型 找到对应的view
     * created by dongliang
     * 2018/3/27  10:45
     */

    public void addHeader(View view) {
        if (headerList == null) {
            headerList = new SparseArray<>();
        }
        headerList.append(headerList.size() + TYPE_HEADER, view);
    }

    /**
     * 添加footer
     * created by dongliang
     * 2018/3/27  10:45
     */

    public void addFooter(View view) {
        if (footerList == null) {
            footerList = new SparseArray<>();
        }
        footerList.append(footerList.size() + TYPE_FOOTER, view);
    }


    public SparseArray<View> getFooterList() {
        return footerList;
    }

    public SparseArray<View> getHeaderList() {
        return headerList;
    }

    public int getHeaderCount() {
        return headerList != null ? headerList.size() : 0;
    }

    public int getFooterCount() {
        return footerList != null ? footerList.size() : 0;
    }

    protected boolean isHeaderPos(int pos) {
        return pos < getHeaderCount() ? true : false;
    }

    protected boolean isFooterPos(int pos) {
        return pos >= getHeaderCount() + data.size() && pos < getHeaderCount() + data.size() + getFooterCount() ? true : false;
    }

    public List<T> getData() {
        return data;
    }

    /**
     * 添加item
     * created by dongliang
     * 2018/4/16  16:22
     */
    public void add(T item, int pos) {
        data.add(pos, item);
        notifyItemInserted(pos);
    }

    public void add(T item) {
        data.add(item);
        notifyItemInserted(data.size() - 1);

    }

    /**
     * created by dongliang
     * 2018/4/16  16:28
     */
    public void addMore(List<T> list) {
        int startPosition = list.size();
        data.addAll(list);
        notifyItemRangeInserted(startPosition, data.size());
    }

    /**
     * 删除item
     * created by dongliang
     * 2018/4/16  16:28
     */
    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 设置点击监听
     * created by dongliang
     * 2018/3/29  13:08
     */
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * 设置长按监听
     * created by dongliang
     * 2018/3/29  13:08
     */
    public void setItemLongClickListener(ItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderPos(position)) {

            return headerList.keyAt(position);
        }
        if (isFooterPos(position)) {
            return footerList.keyAt(position - getHeaderCount() - data.size());
        }
        return 0;

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //header
        if (viewType >= TYPE_HEADER && viewType < TYPE_FOOTER) {
            return new BaseViewHolder(headerList.get(viewType), viewType);
        }
        //footer
        if (viewType >= TYPE_FOOTER) {
            return new BaseViewHolder(footerList.get(viewType), viewType);
        }


        return new BaseViewHolder(mLayoutInflater.inflate(layoutId, parent, false));


    }


    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        holder.setPosition(position);
        if (itemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.itemClick(position, v);
                }
            });
        }
        if (itemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemLongClickListener.itemLongClick(position, v);
                    return false;
                }
            });
        }
        if (isHeaderPos(position) || isFooterPos(position)) {              //header
            return;
        }
        T item = data.get(position - getHeaderCount());
        viewConfig(holder, position, item);

    }

    @Override
    public int getItemCount() {
        return data.size() + getFooterCount() + getHeaderCount();

    }


    public void setItemAnim(int resId) {
        this.animId = resId;
    }

    /**
     * item 进入是添加动画效果
     * created by dongliang
     * 2018/3/29  12:50
     */
    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (holder.getCurPosition() < lastPosition) {
            return;
        }
        lastPosition = holder.getCurPosition();
        if (holder.getViewType() < TYPE_HEADER) {
            if (animId > 0) {
                Animation animation = android.view.animation.AnimationUtils.loadAnimation(context, animId);
                holder.itemView.startAnimation(animation);
            }
        }

    }

    /**
     * item detached时移除动画
     * created by dongliang
     * 2018/3/29  12:50
     */
    @Override
    public void onViewDetachedFromWindow(BaseViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        if (holder.getViewType() < TYPE_HEADER && animId > 0)

            holder.itemView.clearAnimation();

    }

    /**
     * 用于对布局进行数据填充
     * created by dongliang
     * 2018/3/29  13:28
     */
    public abstract void viewConfig(BaseViewHolder holder, int position, T item);

    /**
     * 点击事件
     * created by dongliang
     * 2018/4/16  16:20
     */
    public interface ItemClickListener {
        void itemClick(int pos, View view);
    }

    /**
     * 长按事件
     * created by dongliang
     * 2018/4/16  16:20
     */
    public interface ItemLongClickListener {
        void itemLongClick(int pos, View view);
    }
}
