package com.example.wzl200.filterlistdemo.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.wzl200.filterlistdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Using this way just for demo purposes. Normally recycler view would be more efficient/customizable Created by Terran on 6/17/15.
 */
public class ItemListAdapter extends BaseAdapter implements Filterable {

    private List<String> mData;

    private List<String> mFilteredData;

    private LayoutInflater mInflater;

    private ItemFilter mFilter;

    public ItemListAdapter(List<String> data, Context context) {
        mData = data;
        mFilteredData = data;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mFilteredData.size();
    }

    @Override
    public String getItem(int position) {
        return mFilteredData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String strItem = mFilteredData.get(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_row, parent, false);

            holder = new ViewHolder();
            holder.mTvItem = (TextView) convertView.findViewById(R.id.tv_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTvItem.setText(strItem);

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ItemFilter();
        }
        return mFilter;
    }

    /**
     * View holder
     */
    static class ViewHolder {

        private TextView mTvItem;
    }

    /**
     * Filter for filtering list items
     */
    private class ItemFilter extends Filter {

        /**
         * Invoked on a background thread.  This is where all the filter logic should go
         *
         * @param constraint the constraint to filter on
         * @return the resulting list after applying the constraint
         */
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            if (TextUtils.isEmpty(constraint)) {
                results.count = mData.size();
                results.values = mData;
            } else {
                //Create a new list to filter on
                List<String> resultList = new ArrayList<>();
                for (String str : mData) {
                    if (str.toLowerCase().contains(constraint.toString().toLowerCase())) {
                        resultList.add(str);
                    }
                }
                results.count = resultList.size();
                results.values = resultList;
            }
            return results;
        }

        /**
         * Runs on ui thread
         *
         * @param constraint the constraint used for the result
         * @param results    the results to display
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mFilteredData = (ArrayList<String>) results.values;
            notifyDataSetChanged();
        }
    }
}
