package com.example.dell.readingpartner.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.dell.readingpartner.R;
import com.example.dell.readingpartner.adapter.VideoAdapter;
import com.example.dell.readingpartner.api.Api;
import com.example.dell.readingpartner.api.ApiConfig;
import com.example.dell.readingpartner.api.TtitCallback;
import com.example.dell.readingpartner.entity.VideoEntity;
import com.example.dell.readingpartner.entity.VideoListResponse;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class HomeFragment extends BaseFragment{

    private RecyclerView recyclerView;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        getVideoList();
    }

    private void getVideoList(){
        String token = getStringFromSp("token");
        HashMap<String, Object> params = new HashMap<>();
        params.put("token", token);
        Api.config(ApiConfig.VIDEO_LIST, params).getRequest(new TtitCallback() {
            @Override
            public void onSuccess(String res) {
                VideoListResponse response = new Gson().fromJson(res, VideoListResponse.class);
                if (response != null && response.getCode() == 0) {
                    List<VideoEntity> datas = response.getPage().getList();
                    VideoAdapter videoAdapter = new VideoAdapter(getActivity(), datas);
                    recyclerView.setAdapter(videoAdapter);
                }
                showToastSync(res);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
