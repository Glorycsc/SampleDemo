package com.glory.mybanner;

import java.util.List;

/**
 * Create by glorizz on 2018/7/17
 * Describe:
 */
public class BannerModel {
    public static final String NEWS = "zixunNotice";
    public static final String BRAND = "pinpaiNotice";
    public static final String FIND = "faxianNotice";
    public static final String HOME = "mainNotice";
    public static final String READ = "yueduNotice";

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * link_type : 1
         * img_url : http://124.205.9.251:8082/static//image/201807/04b973cf-eaeb-41cf-bd49-29c044ee3257.png
         * pid : 13
         * id : 42
         * sort : 0
         * is_release : 1
         * jump_type : 3
         * link_value : http://124.205.9.251:8082/pinpai/brand_order_detail?id=13
         * notice_type : pinpaiNotice
         */

        private String link_type;
        private String img_url;
        private String pid;
        private int id;
        private int sort;
        private String is_release;
        private String jump_type;
        private String link_value;
        private String notice_type;

        public String getLink_type() {
            return link_type;
        }

        public void setLink_type(String link_type) {
            this.link_type = link_type;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getIs_release() {
            return is_release;
        }

        public void setIs_release(String is_release) {
            this.is_release = is_release;
        }

        public String getJump_type() {
            return jump_type;
        }

        public void setJump_type(String jump_type) {
            this.jump_type = jump_type;
        }

        public String getLink_value() {
            return link_value;
        }

        public void setLink_value(String link_value) {
            this.link_value = link_value;
        }

        public String getNotice_type() {
            return notice_type;
        }

        public void setNotice_type(String notice_type) {
            this.notice_type = notice_type;
        }
    }
}

