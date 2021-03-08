package com.exercices.publisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import com.exercices.publisherBread.Bread;
import com.exercices.subscription.BackerConsumerSubscription;

public class BackerPublisher implements Publisher<Bread>{

	@Override
	public void subscribe(Subscriber<? super Bread> subscriber) {
		//when a new subscriber arrives, it will create a new backer consumer subscription
		//with this a new comsumer(susbscriber) is created and when the publisher give when is neccesary an event to the comsumer (susbcriber)
		subscriber.onSubscribe(
				new BackerConsumerSubscription(this, subscriber));	
		
	}
	
	public Bread makeBread() {
		return new Bread();
	}

}
