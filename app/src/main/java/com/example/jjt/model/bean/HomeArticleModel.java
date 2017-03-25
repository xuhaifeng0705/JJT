package com.example.jjt.model.bean;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
public class HomeArticleModel {

    /**
     * id : 275
     * title : 儿菜：水润润、嫩嘟嘟、甜丝丝的馋人菜
     * short_content : 你听说过儿菜吗？它可在川渝人民心中拥有不可替代的位置。这种长得有点像榨菜头的小东西，据说无比美味，今天我们来介绍儿菜。
     * image : /upload/news/20170324/692db91013f8af9f9e3226fd2e4718c6.jpg
     * create_time : 2017-03-24 14:54:14
     * view : 228
     */


        private String id;
        private String title;
        private String short_content;
        private String image;
        private String create_time;
        private int view;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShort_content() {
            return short_content;
        }

        public void setShort_content(String short_content) {
            this.short_content = short_content;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getView() {
            return view;
        }

        public void setView(int view) {
            this.view = view;
        }

}
