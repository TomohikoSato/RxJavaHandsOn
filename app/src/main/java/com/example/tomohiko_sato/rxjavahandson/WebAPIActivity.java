package com.example.tomohiko_sato.rxjavahandson;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;

import com.example.tomohiko_sato.rxjavahandson.github.data.User;
import com.example.tomohiko_sato.rxjavahandson.github.normal.GithubWebClient;
import com.example.tomohiko_sato.rxjavahandson.github.rx.GithubRxWebClient;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Webリクエストなどの非同期処理に慣れるためのActivity
 */
public class WebAPIActivity extends AppCompatActivity {
    private static final String TAG = WebAPIActivity.class.getSimpleName();
    private CompositeDisposable disposables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        disposables = new CompositeDisposable();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        requestNormally();
        requestRx();
//        requestSerialAsync();
//        requestParallelAsync();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.dispose();
    }

    void requestNormally() {
        Handler handler = new Handler();
        new Thread(() -> {
            try {
                User user = new GithubWebClient().requestUser("square");
                handler.post(() -> Log.d(TAG + "normal", user.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    void requestRx() {
        Disposable disposable = new GithubRxWebClient().requestUser("square")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> Log.d(TAG + "rx", user.toString()));
        disposables.add(disposable);
    }

    void requestSerialAsync() {
        HaikuAPI haiku = new HaikuAPI();
        Disposable disposable = haiku.requestFirstVerse()
                .flatMap(first -> haiku.requestSecondVerse(first).map(second -> first + second))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> Log.d(TAG, s),
                        Throwable::printStackTrace);
        disposables.add(disposable);
    }

    void requestParallelAsync() {
        final HaikuAPI haiku = new HaikuAPI();
        Observable.zip(haiku.requestFirstVerse().subscribeOn(Schedulers.io()), haiku.requestSecondVerse("柿食えば").subscribeOn(Schedulers.io()), Pair::create)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pair -> Log.d(TAG, pair.first + pair.second), Throwable::printStackTrace);
    }

    static class HaikuAPI {
        Observable<String> requestFirstVerse() {
            return Observable.create(emitter -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    emitter.onError(e);
                }

                int random = (int) (Math.random() * 3);
                switch (random) {
                    case 0:
                        emitter.onNext("柿食えば");
                        break;
                    case 1:
                        emitter.onNext("古池や");
                        break;
                    case 2:
                        emitter.onNext("風呂敷を");
                        break;
                    default:
                        emitter.onError(new IllegalArgumentException("illegal number"));
                }
            });
        }

        Observable<String> requestSecondVerse(String firstVerse) {
            return Observable.create(emitter -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    emitter.onError(e);
                }

                switch (firstVerse) {
                    case "柿食えば":
                        emitter.onNext("鐘が鳴るなり法隆寺");
                        break;
                    case "古池や":
                        emitter.onNext("蛙飛び込む水の音");
                        break;
                    case "風呂敷を":
                        emitter.onNext("ほどけば柿のころげけり");
                        break;
                    default:
                        emitter.onError(new IllegalArgumentException("illegal first verse"));
                }
            });
        }
    }
}
