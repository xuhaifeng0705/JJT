package com.example.jjt.utils;

import com.example.jjt.model.http.exception.ApiException;
import com.example.jjt.model.http.response.GanhuoHttpResponse;
import com.example.jjt.model.http.response.JJTHttpResponse;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/3/22 0022.
 */
public class RxUtils {


    /**
     * 统一线程处理
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一返回结果处理
     * 干货集中营
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<GanhuoHttpResponse<T>, T> handleResult() {   //compose判断结果
        return new Observable.Transformer<GanhuoHttpResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<GanhuoHttpResponse<T>> httpResponseObservable) {
                return httpResponseObservable.flatMap(new Func1<GanhuoHttpResponse<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(GanhuoHttpResponse<T> tGanhuoHttpResponse) {
                        if(!tGanhuoHttpResponse.getError()) {
                            return createData(tGanhuoHttpResponse.getResults());
                        } else {
                            return Observable.error(new ApiException("服务器返回error"));
                        }
                    }
                });
            }
        };
    }

    /**
     * 统一返回结果处理
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<JJTHttpResponse<T>, T> handleJJTResult() {   //compose判断结果
        return new Observable.Transformer<JJTHttpResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<JJTHttpResponse<T>> httpResponseObservable) {
                return httpResponseObservable.flatMap(new Func1<JJTHttpResponse<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(JJTHttpResponse<T> jjtHttpResponse) {
                        if(jjtHttpResponse.getCode() == 200) {
                            return createData(jjtHttpResponse.getResult());
                        } else {
                            return Observable.error(new ApiException("服务器返回error"));
//                            return Observable.error(new ApiException(jjtHttpResponse.getMessage()));
                        }
                    }
                });
            }
        };
    }


    /**
     * 生成Observable
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createData(final T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
