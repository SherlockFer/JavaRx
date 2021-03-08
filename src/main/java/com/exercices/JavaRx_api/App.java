package com.exercices.JavaRx_api;

import com.exercices.consumerSubscriber.ConsumerSubscriber;
import com.exercices.publisher.BackerPublisher;
import com.exercices.publisherBread.Bread;
import com.exercices.subscription.BackerConsumerSubscription;

import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.flowables.ConnectableFlowable;

public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
//    	Flowable.just("Hello world").subscribe(System.out::println);
    	
    	//1.
    	//Create a publisher for a stream and transgress through its contents
//    	Flowable<String> flowOfStrings=Flowable.just("Hi "," My "," Name "," is "," Fermin");
    		//go through the elements of the stream
//    	flowOfStrings.blockingSubscribe(System.out::print);
    	
    	//2.
    	//Create a publisher for a stream of number and perform a calculation
//    	Flowable<Integer> streamNumbers=Flowable.range(1,100);
    		//Sequencial sum
//    	Single<Integer> sum=streamNumbers.reduce(0, Integer::sum);
    		//Subscribe to the current single and calls given on success callback on the current thread when its completes normally
//    	sum.blockingSubscribe(System.out::print);
    		
    	//3.
        // Producing and consuming example
    	BackerPublisher backerPublisher = new BackerPublisher();
    		//you can create flowables from Observables and Publishers
    	Flowable<Bread> flowable = Flowable.fromPublisher(backerPublisher);
    		//replays items/events to late subscribers
    	ConnectableFlowable connectableFlowable=flowable.replay(1,true);
    		//create 2 instances of consumerSubscriber
    	ConsumerSubscriber consumerSubscriber1 = new ConsumerSubscriber();
    	ConsumerSubscriber consumerSubscriber2 = new ConsumerSubscriber();
    		//use flowable from backerPublisher, to subscribe to the publisher
    	flowable.subscribe(consumerSubscriber1);
    	flowable.subscribe(consumerSubscriber2);
    		//backersubscription for each consumer
    	BackerConsumerSubscription backerConsumerSubscription1 = new BackerConsumerSubscription(backerPublisher, consumerSubscriber1);
    	consumerSubscriber1.setSubscription(backerConsumerSubscription1);
    	BackerConsumerSubscription backerConsumerSubscription2 = new BackerConsumerSubscription(backerPublisher, consumerSubscriber2);
    	consumerSubscriber2.setSubscription(backerConsumerSubscription2);
    	
    	new Thread(() -> {
    		connectableFlowable.connect();
    		}).start();
    	
    	consumerSubscriber1.subscription.request(1);
    	
    }
}
