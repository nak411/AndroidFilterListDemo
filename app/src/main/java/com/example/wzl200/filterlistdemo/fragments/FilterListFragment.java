package com.example.wzl200.filterlistdemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wzl200.filterlistdemo.R;

/**
 * Simple fragment that contains a filterable list of strings.
 * Could have also extended directly from {@link android.support.v4.app.ListFragment} but we want to keep the UI flexible.
 */
public class FilterListFragment extends Fragment implements SearchView.OnQueryTextListener {



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
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);

        // Associate search configuration with the SearchView
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        searchView.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
