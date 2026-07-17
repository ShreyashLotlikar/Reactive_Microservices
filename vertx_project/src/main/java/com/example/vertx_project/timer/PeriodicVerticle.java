package com.example.vertx_project.timer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class PeriodicVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    System.out.println("PeriodicVerticle.start()");

    vertx.setTimer(2000, handler -> {
      System.out.println("I am delayed by 2 seconds");
    });

    vertx.setPeriodic(2000, handler -> {
      System.out.println("I am printing every 2 seconds");
    });
  }
}
