package com.xtremelabs.droidsugar.view;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import com.xtremelabs.droidsugar.ProxyDelegatingHandler;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"UnusedDeclaration"})
public class FakeView {
    private View realView;

    private int id;
    private List<View> children = new ArrayList<View>();
    private FakeView parent;
    private Context context;
    private int visibility;
    public boolean selected;
    private View.OnClickListener onClickListener;
    private Object tag;
    private boolean enabled = true;
    public int height;
    public int width;
    public int paddingLeft;
    public int paddingTop;
    public int paddingRight;
    public int paddingBottom;
    public ViewGroup.LayoutParams layoutParams;

    public FakeView(View view) {
        this.realView = view;
    }

    public void __constructor__(Context context) {
        this.context = context;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public View findViewById(int id) {
        if (id == this.id) {
            return realView;
        }

        for (View child : children) {
            View found = child.findViewById(id);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    public View getRootView() {
        FakeView root = this;
        while(root.parent != null) {
            root = root.parent;
        }
        return root.realView;
    }

    public void addView(View child) {
        children.add(child);
        childProxy(child).parent = this;
    }

    private FakeView childProxy(View child) {
        return (FakeView) ProxyDelegatingHandler.getInstance().proxyFor(child);
    }

    public int getChildCount() {
        return children.size();
    }

    public ViewGroup.LayoutParams getLayoutParams() {
        return layoutParams;
    }

    public View getChildAt(int index) {
        return children.get(index);
    }

    public void removeAllViews() {
        for (View child : children) {
            childProxy(child).parent = null;
        }
        children.clear();
    }

    public final Context getContext() {
        return context;
    }

    public Resources getResources() {
        return context.getResources();
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public boolean performClick() {
        if (onClickListener != null) {
            onClickListener.onClick(realView);
            return true;
        } else {
            return false;
        }
    }

    public Object getTag() {
        return this.tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public final int getHeight() {
        return height;
    }

    public final int getWidth() {
        return width;
    }

    public void setPadding(int left, int top, int right, int bottom) {
        paddingLeft = left;
        paddingTop = top;
        paddingRight = right;
        paddingBottom = bottom;
    }

    public int getPaddingTop() {
        return paddingTop;
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public int getPaddingRight() {
        return paddingRight;
    }

    public int getPaddingBottom() {
        return paddingBottom;
    }
}
