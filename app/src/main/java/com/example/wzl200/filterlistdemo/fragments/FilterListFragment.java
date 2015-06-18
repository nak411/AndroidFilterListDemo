package com.example.wzl200.filterlistdemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.wzl200.filterlistdemo.R;
import com.example.wzl200.filterlistdemo.adapters.ItemListAdapter;
import com.example.wzl200.filterlistdemo.data.ListData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Simple fragment that contains a filterable list of strings.
 * Could have also extended directly from {@link android.support.v4.app.ListFragment} but we want to keep the UI flexible.
 */
public class FilterListFragment extends Fragment implements SearchView.OnQueryTextListener {



    private ItemListAdapter mAdapter;

    public FilterListFragment() {
        //Required empty constructor
    }

    public static FilterListFragment newInstance() {
        return new FilterListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter_string_list, container, false);
        //Tell the system to call onCreateOptionsMenu
        setHasOptionsMenu(true);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        //Set up list view
        mAdapter = new ItemListAdapter(getData(ListData.items), getActivity());
        ListView lvItems = (ListView) view.findViewById(R.id.lv_items);
        lvItems.setAdapter(mAdapter);
    }

    private List<String> getData(String[] items) {
        List<String> list = new ArrayList<>();
        for (String str : items) {
            list.add(str);
        }
        return list;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Set up search view
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        mAdapter.getFilter().filter(s);
        return false;
    }
}
