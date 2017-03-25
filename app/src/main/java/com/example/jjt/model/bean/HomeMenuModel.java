package com.example.jjt.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
public class HomeMenuModel {


    /**
     * id : 9
     * name : 吾悦广场
     * logo : ./Upload/admin/2017-01-05/586dde623a67c.png
     * sort_index : 1
     * dele_status : 1
     * add_time : 2016-09-02 16:26:37
     */

    private List<CatesBean> cates;

    public List<CatesBean> getCates() {
        return cates;
    }

    public void setCates(List<CatesBean> cates) {
        this.cates = cates;
    }

    public static class CatesBean {
        private String id;
        private String name;
        private String logo;
        private String sort_index;
        private String dele_status;
        private String add_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getSort_index() {
            return sort_index;
        }

        public void setSort_index(String sort_index) {
            this.sort_index = sort_index;
        }

        public String getDele_status() {
            return dele_status;
        }

        public void setDele_status(String dele_status) {
            this.dele_status = dele_status;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }
}
