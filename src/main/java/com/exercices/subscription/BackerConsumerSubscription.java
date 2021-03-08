package com.exercices.subscription;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import com.exercices.publisher.BackerPublisher;
import com.exercices.publisherBread.Bread;

public class BackerConsumerSubscription implements Subscription{
	
	public static BackerPublisher publisher;
	public static Subscriber<? super Bread> subscriber;
	
	
	//Add constructor to set variables
	public BackerConsumerSubscription(BackerPublisher backerPublisher, Subscriber<? super Bread> suscriber) {
		BackerConsumerSubscription.setPublisher(backerPublisher);
		BackerConsumerSubscription.setSuscriber(suscriber);
	}
	
	@Override
	public void request(long n) {
		System.out.println("Loaves requested= "+n);
		Bread loaf;
		if(publisher != null && subscriber != null && (loaf = publisher.makeBread()) != null) {
			for (int i = 0; i < n; i++) {
				subscriber.onNext(loaf);
			}
		}
		
	}

	@Override
	public void cancel() {
		System.out.println("cancel subscription request");
		
	}

	public static BackerPublisher getPublisher() {
		return BackerConsumerSubscription.publisher;
	}

	public static void setPublisher(BackerPublisher publisher) {
		BackerConsumerSubscription.publisher = publisher;
	}
	public static Subscriber getSuscriber() {
		return BackerConsumerSubscription.subscriber;
	}

	public static void setSuscriber(Subscriber<? super Bread> subscriber) {
		BackerConsumerSubscription.subscriber = subscriber;
	}


	

}
