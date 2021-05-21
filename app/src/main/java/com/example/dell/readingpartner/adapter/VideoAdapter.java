package com.example.dell.readingpartner.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.dueeeke.videocontroller.component.PrepareView;
import com.example.dell.readingpartner.R;
import com.example.dell.readingpartner.api.Api;
import com.example.dell.readingpartner.api.ApiConfig;
import com.example.dell.readingpartner.api.TtitCallback;
import com.example.dell.readingpartner.entity.BaseResponse;
import com.example.dell.readingpartner.entity.VideoEntity;
import com.example.dell.readingpartner.listener.OnItemChildClickListener;
import com.example.dell.readingpartner.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<VideoEntity> datas;

    private OnItemChildClickListener mOnItemChildClickListener;

    private OnItemClickListener mOnItemClickListener;

    public void setDatas(List<VideoEntity> datas) {
        this.datas = datas;
    }

    public VideoAdapter(Context context) {
        this.mContext = context;
    }

    public VideoAdapter(Context context, List<VideoEntity> datas) {
        this.mContext = context;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        VideoEntity videoEntity = datas.get(position);
        vh.tvTitle.setText(videoEntity.getVtitle());
        vh.tvAuthor.setText(videoEntity.getAuthor());
//        vh.tvDz.setText(String.valueOf(videoEntity.getLikeNum()));
//        vh.tvComment.setText(String.valueOf(videoEntity.getCommentNum()));
//        vh.tvCollect.setText(String.valueOf(videoEntity.getCollectNum()));

        if (videoEntity.getVideoSocialEntity() != null) {
            int likeNum = videoEntity.getVideoSocialEntity().getLikeNum();
            int commentNum = videoEntity.getVideoSocialEntity().getCommentNum();
            int collectNum = videoEntity.getVideoSocialEntity().getCollectNum();
            boolean flagLike = videoEntity.getVideoSocialEntity().isFlagLike();
            boolean flagCollect = videoEntity.getVideoSocialEntity().isFlagCollect();

            if (flagLike) {
                vh.tvDz.setTextColor(Color.parseColor("#E21918"));
                vh.imgDizan.setImageResource(R.mipmap.dianzan_select);
            }
            if (flagCollect) {
                vh.tvCollect.setTextColor(Color.parseColor("#E21918"));
                vh.imgCollect.setImageResource(R.mipmap.collect_select);
            }

            vh.tvDz.setText(String.valueOf(likeNum));
            vh.tvComment.setText(String.valueOf(commentNum));
            vh.tvCollect.setText(String.valueOf(collectNum));
            vh.flagLike = flagLike;
            vh.flagCollect = flagCollect;
        }

        Picasso.with(mContext).load(videoEntity.getHeadurl()).into(vh.imgHeader);
        Picasso.with(mContext).load(videoEntity.getCoverurl()).into(vh.mThumb);

        //记录每个item对应的下标
        vh.mPosition = position;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvTitle;
        private TextView tvAuthor;
        private TextView tvDz;
        private TextView tvComment;
        private TextView tvCollect;
        private ImageView imgHeader;
        private ImageView imgCollect;
        private ImageView imgDizan;
        public ImageView mThumb;
        public PrepareView mPrepareView;
        public FrameLayout mPlayerContainer;
        public int mPosition;
        private boolean flagCollect;
        private boolean flagLike;

        public ViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.title);
            tvAuthor = view.findViewById(R.id.author);
            tvDz = view.findViewById(R.id.dz);
            tvComment = view.findViewById(R.id.comment);
            tvCollect = view.findViewById(R.id.collect);
            imgHeader = view.findViewById(R.id.img_header);
            imgCollect = view.findViewById(R.id.img_collect);
            imgDizan = view.findViewById(R.id.img_like);
            mPlayerContainer = view.findViewById(R.id.player_container);
            mPrepareView = view.findViewById(R.id.prepare_view);
            mThumb = mPrepareView.findViewById(R.id.thumb);
            if (mOnItemChildClickListener != null) {
                mPlayerContainer.setOnClickListener(this);
            }
            if (mOnItemClickListener != null) {
                view.setOnClickListener(this);
            }

            imgCollect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int collectNum = Integer.parseInt(tvCollect.getText().toString());
                    if (flagCollect) { //已收藏
                        if (collectNum > 0) {
                            tvCollect.setText(String.valueOf(--collectNum));
                            tvCollect.setTextColor(Color.parseColor("#161616"));
                            imgCollect.setImageResource(R.mipmap.collect);
                            updateCount(datas.get(mPosition).getVid(), 1, !flagCollect, collectNum);
                        }
                    } else {//未收藏
                        tvCollect.setText(String.valueOf(++collectNum));
                        tvCollect.setTextColor(Color.parseColor("#E21918"));
                        imgCollect.setImageResource(R.mipmap.collect_select);
                        updateCount(datas.get(mPosition).getVid(), 1, !flagCollect, collectNum);
                    }
                    flagCollect = !flagCollect;
                }
            });
            imgDizan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int likeNum = Integer.parseInt(tvDz.getText().toString());
                    if (flagLike) { //已点赞
                        if (likeNum > 0) {
                            tvDz.setText(String.valueOf(--likeNum));
                            tvDz.setTextColor(Color.parseColor("#161616"));
                            imgDizan.setImageResource(R.mipmap.dianzan);
                            updateCount(datas.get(mPosition).getVid(), 2, !flagLike, likeNum);
                        }
                    } else {//未点赞
                        tvDz.setText(String.valueOf(++likeNum));
                        tvDz.setTextColor(Color.parseColor("#E21918"));
                        imgDizan.setImageResource(R.mipmap.dianzan_select);
                        updateCount(datas.get(mPosition).getVid(), 2, !flagLike, likeNum);
                    }
                    flagLike = !flagLike;
                }
            });

            //通过tag将ViewHolder和itemView绑定
            view.setTag(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.player_container) {
                if (mOnItemChildClickListener != null) {
                    mOnItemChildClickListener.onItemChildClick(mPosition);
                }
            } else {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(mPosition);
                }
            }
        }
    }

    private void updateCount(int vid, int type, boolean flag, int num) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("vid", vid);
        params.put("type", type);
        params.put("flag", flag);
        params.put("changeNum", num);
        Api.config(ApiConfig.VIDEO_UPDATE_COUNT, params).postRequest(new TtitCallback() {
            @Override
            public void onSuccess(final String res) {
                Log.e("onSuccess", res);
                Gson gson = new Gson();
                BaseResponse baseResponse = gson.fromJson(res, BaseResponse.class);
                if (baseResponse.getCode() == 0) {

                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public void setOnItemChildClickListener(OnItemChildClickListener onItemChildClickListener) {
        mOnItemChildClickListener = onItemChildClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
