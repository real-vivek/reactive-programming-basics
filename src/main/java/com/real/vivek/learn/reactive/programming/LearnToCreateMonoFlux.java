package com.real.vivek.learn.reactive.programming;

import java.util.List;

import reactor.core.publisher.Flux;

public class LearnToCreateMonoFlux {

	public static Flux<String> namesFlux() {
		// Flux represents 0 or N elements
		// Flux.fromIterable produces list of given string as stream.
		return Flux.fromIterable(List.of("lenord", "lisa"));
	}

	public static void main(String[] args) {
		namesFlux().subscribe(name -> System.out.println(name));
	}

}
