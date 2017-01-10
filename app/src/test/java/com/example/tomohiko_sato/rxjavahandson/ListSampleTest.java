package com.example.tomohiko_sato.rxjavahandson;

import org.junit.Test;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ListSampleTest {
    @Test
    public void map() throws Exception {
        List<Integer> items = Observable.fromArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
                .map(i -> i * 2)
                .test()
                .values();
        System.out.print(items);
    }

    @Test
    public void concatMap() throws Exception {
        List<Integer> items = Observable.fromArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
                .concatMap(i -> Observable.range(i, 3)) // Observable<Observable<Integer>> を Observable<Integer> にする
                .test()
                .values();
        System.out.print(items);
    }

    @Test
    public void flatMap() throws Exception {
        List<Integer> items = Observable.fromArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
                .flatMap(i -> Observable.range(i, 3)) // Observable<Observable<Integer>> を Observable<Integer> にする
                .test()
                .values();
        System.out.print(items);
    }

    @Test
    public void window() throws Exception {
        List<Observable<Integer>> items = Observable.fromArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
                .window(3)
                .test()
                .values();

        for (Observable<Integer> item : items) {
            System.out.print(item.test().values());
        }

        System.out.print(items);
    }

    @Test
    public void scan() throws Exception {
        List<Integer> items = Observable.fromArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
                .scan((i, j) -> i + j)
                .test()
                .values();

        System.out.print(items);
    }

    @Test
    public void first() throws Exception {
        List<Integer> items = Observable.fromArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
                .firstElement()
                .test()
                .values();

        System.out.print(items);

    }

    @Test
    public void take() throws Exception {
        List<Integer> items = Observable.fromArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
                .take(3)
                .test()
                .values();

        System.out.print(items);
    }
}