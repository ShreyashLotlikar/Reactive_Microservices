package com.example.vertx_project.verticles;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.*;
import io.vertx.core.json.JsonObject;

public class BootstrapVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    ConfigStoreOptions fileStore = new ConfigStoreOptions()
      .setType("file")
      .setOptional(true)
      .setConfig(new JsonObject().put("path", "my-config.json"));
    ConfigStoreOptions sysPropsStore = new ConfigStoreOptions().setType("sys");

    ConfigRetrieverOptions options = new ConfigRetrieverOptions().addStore(fileStore).addStore(sysPropsStore);

    //ConfigRetriever retriever = ConfigRetriever.create(vertx, options);

    ConfigRetriever retriever = ConfigRetriever.create(vertx, options
      .addStore(new ConfigStoreOptions().setType("file").setConfig(new JsonObject().put("path", "my-config.json"))));

    retriever.getConfig().onComplete(json -> {
      if (json.failed()) {
        System.err.println("Config retrieval failed: " + json.cause().getMessage());
        json.cause().printStackTrace();
        return;
      }

      JsonObject configs = json.result();
      int numInstance = configs.getInteger("instances");

      DeploymentOptions dOptions =new DeploymentOptions();
      dOptions.setInstances(numInstance);
      dOptions.setThreadingModel(ThreadingModel.EVENT_LOOP); // false

      vertx.deployVerticle(FirstVerticle.class.getName(), dOptions);
      vertx.deployVerticle(new SecondVerticle());
    });




    //Three ways to deploy verticles
    //vertx.deployVerticle("com.example.vertx_project.verticles.FirstVerticle");
    //vertx.deployVerticle(FirstVerticle.class.getName(), dOptions);
    //vertx.deployVerticle(new FirstVerticle());
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    startPromise.complete();
  }
}
