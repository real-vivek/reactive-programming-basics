package com.real.vivek.learn.reactive.programming;

import java.time.Duration;
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
	
	public static Flux<String> namesFlux_filter() {
		return Flux.fromIterable(List.of("lenord", "lisa")).filter(name->name.length()>4).log();
	}
	
	public static Mono<String> namesMono_map() {
		//As we have used the log before using the map function we see the onNext call which the Publisher makes on the Subscriber has param value "vivek" in small case
		return Mono.just("vivek").log().map(name->name.toUpperCase());
	}
	
	// Flat map gets the flux and flattens the values just like we get it from a flux
	public static Flux<String> namesFlux_flat_map() {
		return Flux.fromIterable(List.of("lisa")).flatMap(name->splitString(name));
	}
	
	// Flat map gets the flux and flattens the values just like we get it from a flux
	// The flatMap waits for all the results and then sends the result 
	// The flatMap does more than just 1 to N transformation, it subscribes to Flux/Mono thats part of transformation and then flattens it 
	// Thus flatMap is used with Transformations that returns Publisher and are used for async transformation
	// How flatMap's subscription will work internally:
	// Flux takes elements(more than 1 element) assigns them a delay of 1 second, while subscribing, the flatMap gets the elements in any order
	public static Flux<String> namesFlux_async_flat_map() {
		return Flux.fromIterable(List.of("lenord","lisa")).flatMap(name->async_splitString(name)).log();
	}
	
	public static Flux<String> splitString(String name) {
		return Flux.fromArray(name.split(""));
	}
	
	// How concatMap's subscription will work internally:
	// Flux takes one element at a time assigns it a delay of 1 second, while subscribing, the flatMap gets the elements in the order given 
	public static Flux<String> namesFlux_concat_map() {
		return Flux.fromIterable(List.of("lenord","lisa")).concatMap(name->async_splitString(name)).log();
	}
	
	public static Mono<List<String>> namesMono_async_flat_map() {
		return Mono.just("lisa").flatMap(name->async_splitString_mono(name)).log();
	}
	
	public static Flux<String> namesMono_async_flat_map_many() {
		return Mono.just("lisa").flatMapMany(name->async_splitString(name)).log();
	}
	
	public static Mono<List<String>> async_splitString_mono(String name) {
		var listOfNames=List.of(name.split(""));
		return Mono.just(listOfNames).log();
	}
	
	// The elements will be emitted after 1 sec
	public static Flux<String> async_splitString(String name) {
		return Flux.fromArray(name.split("")).delayElements(Duration.ofMillis(1000));
	}

	public static void main(String[] args) {
		//This is the Subscriber to which we have provided consumer
		//If we don't subscribe the stream will not be triggered and we will have only Flux object
		namesFlux().subscribe(name->System.out.println(name));
		System.out.println("======================================");
		namesMono().subscribe(name->System.out.println(name));
		System.out.println("======================================");
		namesFlux_filter().subscribe(name->System.out.println(name));
		System.out.println("======================================");
		namesMono_map().subscribe(name->System.out.println(name));
		System.out.println("======================================");
		namesFlux_flat_map().subscribe(name->System.out.println(name));
		System.out.println("======================================");
	}

}
