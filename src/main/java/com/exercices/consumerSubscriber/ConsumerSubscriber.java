package com.exercices.consumerSubscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import com.exercices.publisherBread.Bread;

public class ConsumerSubscriber  implements Subscriber<Bread>{

	public Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		
	}

	@Override
	public void onNext(Bread bread) {
		System.out.println("Enjoy the bread. loafNumber = "+ bread.loafNumber);
		subscription.request(1);
	}

	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onComplete() {
		// TODO Auto-generated method stub
		
	}
	
	
	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

}
