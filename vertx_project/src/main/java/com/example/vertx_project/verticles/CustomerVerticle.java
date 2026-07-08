package com.example.vertx_project.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class CustomerVerticle extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    System.out.println("CustomerVerticle.start()");
  }
}
