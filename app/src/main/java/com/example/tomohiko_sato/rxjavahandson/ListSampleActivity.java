package com.example.tomohiko_sato.rxjavahandson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * リスト操作に慣れることを目的としたActivity
 */
public class ListSampleActivity extends AppCompatActivity {

    private static final String TAG = ListSampleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sample);
    }

    @Override
    protected void onResume() {
        super.onResume();
/*
        Observable.just("hogehoge")
                .map(s -> s.length())
                .subscribe(i -> Log.d(TAG, Integer.toString(i)));

        Observable.fromIterable(Arrays.asList("hoge", "fuga"))
                .subscribe(str -> Log.d(TAG, str));

        Observable.fromArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
                .buffer(3, 2)
                .subscribe(items -> {
                    Log.d(TAG, String.valueOf(items));
                });


        Observable.fromArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
                .reduce((i, j) -> i + j)
                .subscribe(k -> Log.d(TAG, String.valueOf(k)));


        Observable.fromArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
                .flatMap(i -> Observable.range(i, 3)) // Observable<Observable<Integer>> を Observable<Integer> にする
                .subscribe(i -> Log.d(TAG, String.valueOf(i)));

        Observable.fromArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
                .scan((i, j) -> i + j)
                .filter(i -> i % 2 == 0)
                .take(3)
                .first(1)
                .subscribe(i -> Log.d(TAG, String.valueOf(i)));
*/

        Observable.just(3)
                .doOnNext(i -> Log.d(TAG, "do on next: " + Thread.currentThread().getName()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(i -> Log.d(TAG, "subscribe: " + Thread.currentThread().getName()));
    }
}
