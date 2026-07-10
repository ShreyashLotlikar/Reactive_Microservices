package com.example.vertx_project.eventBus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

public class ProducerVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    JsonObject message = new JsonObject();
    message.put("name", "Shreyash");

    vertx.eventBus().<JsonObject>request("consumer", message)
      .onSuccess(reply -> System.out.println("Got the ACK: " + reply.body()))
      .onFailure(err -> System.out.println("Failed to deliver: " + err.getMessage()));

    startPromise.complete();
  }
}
