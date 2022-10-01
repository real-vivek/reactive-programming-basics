package com.real.vivek.learn.reactive.programming;

import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class LearnToCreateMonoFlux {

	public static Flux<String> namesFlux() {
		// Flux represents 0 or N elements
		// Flux.fromIterable produces list of given string as stream
		// The log method is used to see how internally the calls are made from Publisher to Subscriber
		// The FluxIterable(Flux.fromIterable) can be considered as Publisher and the code in main method where we subscribe is the Subscriber
		// The code flow is as follows from the Publisher point of view: 
		//1.	First the Subscriber will call the subscribe method on the publisher so as to trigger the stream
		//2.	Then the Publisher will call the onSubscribe method on the Subscriber, passing the Subscription object to the Subscriber in method params. Eg: onSubscribe([Synchronous Fuseable] FluxIterable.IterableSubscription)
		//3.	Using this Subscription object's request method the Subscriber will call the Publisher and request for data it can be unbounded or of long type
		//4.	Thus using Subscription object, connection is established between the Publisher and Subscriber
		//5.	After the connection is established the Publisher will call the onNext() method on the Subscriber passing each data element as param to the onNext 
		//6.	Once the data is exhausted, the Publisher will call the onComplete method on the Subscriber telling that the amount data requested has been published to the subscriber
		//7.	This concept of Subscriber telling the Publisher how much data it wants is Back Pressure thus making reactive streams a push pull based model
		//8.	The Subscriber can at any point of time make a cancel request on the Subscription object so that the Subscriber is not overwhelmed with data
		return Flux.fromIterable(List.of("lenord", "lisa")).log();
	}

	public static Mono<String> namesMono() {
		// Mono is a reactive type which can contain 0 or 1 element
		// The control flow is the same for Mono as well as Flux Publishers
		return Mono.just("vivek").log();
	}
	
	
	public static void main(String[] args) {
		//This is the Subscriber to which we have provided consumer
		//If we don't subscribe the stream will not be triggered and we will have only Flux object
		namesFlux().subscribe(name->System.out.println(name));
		namesMono().subscribe(name->System.out.println(name));
	}

}
