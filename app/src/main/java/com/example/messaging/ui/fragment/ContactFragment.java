package com.example.messaging.ui.fragment;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.messaging.R;
import com.example.messaging.api.CustomViewModelFactory;
import com.example.messaging.api.model.Contact;
import com.example.messaging.api.model.ContactApiResponse;
import com.example.messaging.base.BaseFragment;
import com.example.messaging.databinding.FragmentContactBinding;
import com.example.messaging.ui.activity.ContactDetailActivity;
import com.example.messaging.ui.adapter.CustomListAdapter;
import com.example.messaging.ui.callback.IItemClick;
import com.example.messaging.ui.viewmodel.MainActivityViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import static android.widget.LinearLayout.HORIZONTAL;
import static com.example.messaging.api.ApiConstants.CONTACT_EMAIL;
import static com.example.messaging.api.ApiConstants.CONTACT_FIRST_NAME;
import static com.example.messaging.api.ApiConstants.CONTACT_LAST_NAME;
import static com.example.messaging.api.ApiConstants.CONTACT_NUMBER;

public class ContactFragment extends BaseFragment<FragmentContactBinding> implements IItemClick {

    private CustomListAdapter adapter;
    private LinearLayoutManager layoutManager;
    private MainActivityViewModel viewModel;

    @Inject
    CustomViewModelFactory factory;

    @Override
    public int getLayout() {
        return R.layout.fragment_contact;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity(), factory).get(MainActivityViewModel.class);
        initContactAdapter();
        observeDataChange();

    }

    private void initContactAdapter() {
        adapter = new CustomListAdapter(new ArrayList<>());
        layoutManager = new LinearLayoutManager(getContext());
        binding.rvContacts.setLayoutManager(layoutManager);
        binding.rvContacts.addItemDecoration(new DividerItemDecoration(getContext(), HORIZONTAL));
        binding.rvContacts.setAdapter(adapter);
        adapter.addListener(this);
    }

    private void observeDataChange() {
        viewModel.getUsersData().observe(this, this::loadUserList);
    }

    private void loadUserList(ContactApiResponse data) {
        adapter.swapData(data.getContact());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.removeListener();
            adapter = null;
        }
    }

    @Override
    public void onItemClick(Contact user, int position) {
        Intent intent = new Intent(getContext(), ContactDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(CONTACT_FIRST_NAME, user.getFname());
        bundle.putString(CONTACT_LAST_NAME, user.getLname());
        bundle.putString(CONTACT_NUMBER, user.getPhone());
        bundle.putString(CONTACT_EMAIL, user.getEmail());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
