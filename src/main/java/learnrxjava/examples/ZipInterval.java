package learnrxjava.examples;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class ZipInterval {

  public static void main(String... args) {
    Observable<String> data = Observable.just("one", "two", "three", "four", "five");
    Observable.zip(data, Observable.interval(1, TimeUnit.SECONDS), (d, t) -> {
      return d + " " + t;
    }).toBlocking().forEach(System.out::println);

  }
}
