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
    	var stringFlux = LearnToCreateMonoFlux.namesMono();
    	// Here we assert the count of values that we get is 1 and then we get the completion signal
         StepVerifier.create(stringFlux).expectNextCount(1).verifyComplete();
    }
}