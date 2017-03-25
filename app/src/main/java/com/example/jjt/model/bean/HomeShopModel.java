package com.example.jjt.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
public class HomeShopModel {

    /**
     * id : 45
     * type : 2
     * fromuser :
     * nickname : 松鹤楼小馆(圆融店)
     * username : shlxgyrd
     * password : 21218cca77804d2ba1922c33e0151105
     * cash_password : 21218cca77804d2ba1922c33e0151105
     * token :
     * headpic : ./Upload/admin/2017-03-03/58b8d8f21bf35.png
     * shopname : 松鹤楼小馆(圆融店)
     * shoppic : a:0:{}
     * province_id : 10
     * city_id : 78
     * district_id : 2873
     * address : 江苏省苏州市吴中区旺墩路269号圆融时代广场B1层B1050-1060
     * lng : 120.714539
     * lat : 31.317860
     * telephone : 0512-62607768
     * cmt_count : 0
     * taste_star : 4.0
     * envir_star : 4.0
     * service_star : 4.0
     * cmt_star : 4.0
     * view_count : 81
     * collect_count : 2
     * order_sum : 10516.00
     * cent_rate : 0.10
     * is_stick : 1
     * status : 1
     * dele_status : 1
     * create_time : 2017-03-03 10:44:04
     * distance : 12884.2km
     * cates : ["江浙菜"]
     * star_length : 80
     */

    private List<CustomersBean> customers;

    public List<CustomersBean> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomersBean> customers) {
        this.customers = customers;
    }

    public static class CustomersBean {
        private String id;
        private String type;
        private String fromuser;
        private String nickname;
        private String username;
        private String password;
        private String cash_password;
        private String token;
        private String headpic;
        private String shopname;
        private String shoppic;
        private String province_id;
        private String city_id;
        private String district_id;
        private String address;
        private String lng;
        private String lat;
        private String telephone;
        private String cmt_count;
        private String taste_star;
        private String envir_star;
        private String service_star;
        private String cmt_star;
        private String view_count;
        private String collect_count;
        private String order_sum;
        private String cent_rate;
        private String is_stick;
        private String status;
        private String dele_status;
        private String create_time;
        private String distance;
        private int star_length;
        private List<String> cates;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFromuser() {
            return fromuser;
        }

        public void setFromuser(String fromuser) {
            this.fromuser = fromuser;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getCash_password() {
            return cash_password;
        }

        public void setCash_password(String cash_password) {
            this.cash_password = cash_password;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getHeadpic() {
            return headpic;
        }

        public void setHeadpic(String headpic) {
            this.headpic = headpic;
        }

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public String getShoppic() {
            return shoppic;
        }

        public void setShoppic(String shoppic) {
            this.shoppic = shoppic;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getDistrict_id() {
            return district_id;
        }

        public void setDistrict_id(String district_id) {
            this.district_id = district_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getCmt_count() {
            return cmt_count;
        }

        public void setCmt_count(String cmt_count) {
            this.cmt_count = cmt_count;
        }

        public String getTaste_star() {
            return taste_star;
        }

        public void setTaste_star(String taste_star) {
            this.taste_star = taste_star;
        }

        public String getEnvir_star() {
            return envir_star;
        }

        public void setEnvir_star(String envir_star) {
            this.envir_star = envir_star;
        }

        public String getService_star() {
            return service_star;
        }

        public void setService_star(String service_star) {
            this.service_star = service_star;
        }

        public String getCmt_star() {
            return cmt_star;
        }

        public void setCmt_star(String cmt_star) {
            this.cmt_star = cmt_star;
        }

        public String getView_count() {
            return view_count;
        }

        public void setView_count(String view_count) {
            this.view_count = view_count;
        }

        public String getCollect_count() {
            return collect_count;
        }

        public void setCollect_count(String collect_count) {
            this.collect_count = collect_count;
        }

        public String getOrder_sum() {
            return order_sum;
        }

        public void setOrder_sum(String order_sum) {
            this.order_sum = order_sum;
        }

        public String getCent_rate() {
            return cent_rate;
        }

        public void setCent_rate(String cent_rate) {
            this.cent_rate = cent_rate;
        }

        public String getIs_stick() {
            return is_stick;
        }

        public void setIs_stick(String is_stick) {
            this.is_stick = is_stick;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDele_status() {
            return dele_status;
        }

        public void setDele_status(String dele_status) {
            this.dele_status = dele_status;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public int getStar_length() {
            return star_length;
        }

        public void setStar_length(int star_length) {
            this.star_length = star_length;
        }

        public List<String> getCates() {
            return cates;
        }

        public void setCates(List<String> cates) {
            this.cates = cates;
        }
    }
}
