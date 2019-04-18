package com.example.messaging.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.messaging.R;
import com.example.messaging.api.CustomViewModelFactory;
import com.example.messaging.api.model.MessageApiResponse;
import com.example.messaging.base.BaseFragment;
import com.example.messaging.databinding.FragmentMessagesBinding;
import com.example.messaging.ui.adapter.CustomMsgAdapter;
import com.example.messaging.ui.viewmodel.MainActivityViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import static android.widget.LinearLayout.HORIZONTAL;


public class MessagesFragment extends BaseFragment<FragmentMessagesBinding> {

    private CustomMsgAdapter adapter;
    private LinearLayoutManager layoutManager;
    private MainActivityViewModel viewModel;

    @Inject
    CustomViewModelFactory factory;

    @Override
    public int getLayout() {
        return R.layout.fragment_messages;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity(), factory).get(MainActivityViewModel.class);
        initMessageAdapter();
        observeDataChange();

    }

    private void initMessageAdapter() {
        adapter = new CustomMsgAdapter(new ArrayList<>());
        layoutManager = new LinearLayoutManager(getContext());
        binding.rvMessages.setLayoutManager(layoutManager);
        binding.rvMessages.addItemDecoration(new DividerItemDecoration(getContext(), HORIZONTAL));
        binding.rvMessages.setAdapter(adapter);
    }

    private void observeDataChange() {
        viewModel.getMessageData().observe(this, this::showMessageList);
    }

    private void showMessageList(MessageApiResponse data) {
        adapter.swapData(data.getMessages());
    }
}
