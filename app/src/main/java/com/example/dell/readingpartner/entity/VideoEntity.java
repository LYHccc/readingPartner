package com.example.dell.readingpartner.entity;

import java.io.Serializable;

public class VideoEntity implements Serializable {
    private int vid;
    private String vtitle;
    private String author;
    private String coverurl;
    private String headurl;
    private String playurl;
    private VideoSocialEntity videoSocialEntity;

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getVtitle() {
        return vtitle;
    }

    public void setVtitle(String vtitle) {
        this.vtitle = vtitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoverurl() {
        return coverurl;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getPlayurl() {
        return playurl;
    }

    public void setPlayurl(String playurl) {
        this.playurl = playurl;
    }

    public VideoSocialEntity getVideoSocialEntity() {
        return videoSocialEntity;
    }

    public void setVideoSocialEntity(VideoSocialEntity videoSocialEntity) {
        this.videoSocialEntity = videoSocialEntity;
    }

    public static class VideoSocialEntity {
        private int commentNum;
        private int likeNum;
        private int collectNum;
        private boolean flagLike;
        private boolean flagCollect;

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentnum) {
            this.commentNum = commentnum;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likenum) {
            this.likeNum = likenum;
        }

        public int getCollectNum() {
            return collectNum;
        }

        public void setCollectNum(int collectnum) {
            this.collectNum = collectnum;
        }

        public boolean isFlagLike() {
            return flagLike;
        }

        public void setFlagLike(boolean flagLike) {
            this.flagLike = flagLike;
        }

        public boolean isFlagCollect() {
            return flagCollect;
        }

        public void setFlagCollect(boolean flagCollect) {
            this.flagCollect = flagCollect;
        }
    }
}
