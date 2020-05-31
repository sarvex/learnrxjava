package learnrxjava.examples;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;


public class FlowControlSampleExample {

  public static void main(String args[]) {
    hotStream().sample(500, TimeUnit.MILLISECONDS).toBlocking().forEach(System.out::println);
  }

  /**
   * This is an artificial source to demonstrate an infinite stream that emits randomly
   */
  public static Observable<Integer> hotStream() {
    return Observable.create((Subscriber<? super Integer> s) -> {
      int i = 0;
      while (!s.isUnsubscribed()) {
        s.onNext(i++);
        try {
          // sleep for a random amount of time
          // NOTE: Only using Thread.sleep here as an artificial demo.
          Thread.sleep((long) (Math.random() * 100));
        } catch (Exception e) {
          // do nothing
        }
      }
    }).subscribeOn(Schedulers.newThread());
  }

}
