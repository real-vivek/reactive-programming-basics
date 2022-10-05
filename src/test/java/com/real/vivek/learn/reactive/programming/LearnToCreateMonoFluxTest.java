package com.real.vivek.learn.reactive.programming;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


class LearnToCreateMonoFluxTest {

    @Test
    void namesFlux() {
        Flux<String> stringFlux = LearnToCreateMonoFlux.namesFlux();
      //StepVerifier is a type used to do reactive testing
        StepVerifier.create(stringFlux).
        			expectNext("lenord","lisa").//Here we assert values using expectNext where we tell what values we expect when subscription is invoked
        			verifyComplete();// Here we verify that we received a completion signal
    }

    @Test
    void namesMono() {
    	var stringMono = LearnToCreateMonoFlux.namesMono();
    	// Here we assert the count of values that we get is 1 and then we get the completion signal
         StepVerifier.create(stringMono).expectNextCount(1).verifyComplete();
    }
    
    @Test
    void namesMono_map() {
    	var stringMono = LearnToCreateMonoFlux.namesMono_map();
         StepVerifier.create(stringMono).expectNext("VIVEK").verifyComplete();
    }
    
    @Test
    void namesFlux_filter() {
        Flux<String> stringFlux = LearnToCreateMonoFlux.namesFlux_filter();
        StepVerifier.create(stringFlux).
        			expectNext("lenord").
        			verifyComplete();
    }
    
    @Test
    void namesFlux_flat_map() {
        Flux<String> stringFlux = LearnToCreateMonoFlux.namesFlux_flat_map();
        StepVerifier.create(stringFlux).
        			expectNext("l","i","s","a").
        			verifyComplete();
    }
    
    //Compare the timings between flatMap and concatMap operations 
    //The concatMap operations will require more time as order of elements is preserved
    @Test
    void namesFlux_async_flat_map() {
    	Flux<String> stringFlux = LearnToCreateMonoFlux.namesFlux_async_flat_map();
    	StepVerifier.create(stringFlux).
    	expectNextCount(10).
    	verifyComplete();
    }
    
    @Test
    void namesFlux_concat_map() {
    	Flux<String> stringFlux = LearnToCreateMonoFlux.namesFlux_concat_map();
    	StepVerifier.create(stringFlux).
    	expectNextCount(10).
    	verifyComplete();
    }
}