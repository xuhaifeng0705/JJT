package com.example.jjt.model.db;

import android.content.Context;


import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by codeest on 16/8/16.
 */

//http://www.jianshu.com/p/37af717761cc

//Builder.name : 指定数据库的名称。如不指定默认名为default。
//        Builder.schemaVersion : 指定数据库的版本号。
//        Builder.encryptionKey : 指定数据库的密钥。
//        Builder.migration : 指定迁移操作的迁移类。
//        Builder.deleteRealmIfMigrationNeeded : 声明版本冲突时自动删除原数据库。
//        Builder.inMemory : 声明数据库只在内存中持久化。
//        build : 完成配置构建。
public class RealmHelper {

    private static final String DB_NAME = "myRealm.realm";

    private Realm mRealm;

    public RealmHelper(Context mContext) {
        mRealm = Realm.getInstance(new RealmConfiguration.Builder(mContext)
                .deleteRealmIfMigrationNeeded()
                .name(DB_NAME)
                .build());
    }

    /**
     * 增加 阅读记录
     * @param id
     * 使用@PrimaryKey注解后copyToRealm需要替换为copyToRealmOrUpdate
     */
    public void insertNewsId(int id) {
//        ReadStateBean bean = new ReadStateBean();
//        bean.setId(id);
//        mRealm.beginTransaction();
//        mRealm.copyToRealmOrUpdate(bean);
//        mRealm.commitTransaction();
    }

    /**
     * 查询 阅读记录
     * @param id
     * @return
     */
    public boolean queryNewsId(int id) {
//        RealmResults<ReadStateBean> results = mRealm.where(ReadStateBean.class).findAll();
//        for(ReadStateBean item : results) {
//            if(item.getId() == id) {
//                return true;
//            }
//        }
        return false;
    }

    /**
     * 增加 收藏记录
     * @param bean
     */
//    public void insertLikeBean(RealmLikeBean bean) {
//        mRealm.beginTransaction();
//        mRealm.copyToRealmOrUpdate(bean);
//        mRealm.commitTransaction();
//    }

    /**
     * 删除 收藏记录
     * @param id
     */
//    public void deleteLikeBean(String id) {
//        RealmLikeBean data = mRealm.where(RealmLikeBean.class).equalTo("id",id).findFirst();
//        mRealm.beginTransaction();
//        if (data != null) {
//            data.deleteFromRealm();
//        }
//        mRealm.commitTransaction();
//    }

    /**
     * 查询 收藏记录
     * @param id
     * @return
     */
//    public boolean queryLikeId(String id) {
//        RealmResults<RealmLikeBean> results = mRealm.where(RealmLikeBean.class).findAll();
//        for(RealmLikeBean item : results) {
//            if(item.getId().equals(id)) {
//                return true;
//            }
//        }
//        return false;
//    }

//    public List<RealmLikeBean> getLikeList() {
//        //使用findAllSort ,先findAll再result.sort无效
//        RealmResults<RealmLikeBean> results = mRealm.where(RealmLikeBean.class).findAllSorted("time");
//        return mRealm.copyFromRealm(results);
//    }

    /**
     * 修改 收藏记录 时间戳以重新排序
     * @param id
     * @param time
     * @param isPlus
     */
//    public void changeLikeTime(String id ,long time, boolean isPlus) {
//        RealmLikeBean bean = mRealm.where(RealmLikeBean.class).equalTo("id", id).findFirst();
//        mRealm.beginTransaction();
//        if (isPlus) {
//            bean.setTime(++time);
//        } else {
//            bean.setTime(--time);
//        }
//        mRealm.commitTransaction();
//    }

    /**
     * 更新 掘金首页管理列表
     * @param bean
     */
//    public void updateGoldManagerList(GoldManagerBean bean) {
//        GoldManagerBean data = mRealm.where(GoldManagerBean.class).findFirst();
//        mRealm.beginTransaction();
//        if (data != null) {
//            data.deleteFromRealm();
//        }
//        mRealm.copyToRealm(bean);
//        mRealm.commitTransaction();
//    }

    /**
     * 获取 掘金首页管理列表
     * @return
     */
//    public GoldManagerBean getGoldManagerList() {
//        GoldManagerBean bean = mRealm.where(GoldManagerBean.class).findFirst();
//        if (bean == null)
//            return null;
//        return mRealm.copyFromRealm(bean);
//    }
}
