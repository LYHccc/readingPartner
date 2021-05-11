package com.example.dell.readingpartner.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.dell.readingpartner.R;
import com.example.dell.readingpartner.adapter.VideoAdapter;
import com.example.dell.readingpartner.entity.VideoEntity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

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
//        TextView tv = v.findViewById(R.id.title);
//        tv.setText("视频页面");
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<VideoEntity> datas = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            VideoEntity ve = new VideoEntity();
            ve.setTitle("韭菜盒子");
            ve.setName("大胃王");
            ve.setDzCount(i * 2);
            ve.setCollectCount(i * 4);
            ve.setCommentCount(i * 8);
            datas.add(ve);
        }
        VideoAdapter videoAdapter = new VideoAdapter(getActivity(), datas);
        recyclerView.setAdapter(videoAdapter);
        return v;
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
