package com.example.vertx_project.eventBus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

public class ConsumerVerticle extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.eventBus().consumer("consumer", message -> {
      System.out.println("I have recived a message: " + message.body());
      JsonObject reply = new JsonObject();
      reply.put("message","Recived the message");
      message.reply(reply);
    });
    //super.start(startPromise);
  }
}
