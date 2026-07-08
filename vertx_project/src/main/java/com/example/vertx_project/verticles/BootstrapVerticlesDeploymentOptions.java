package com.example.vertx_project.verticles;

import io.netty.bootstrap.AbstractBootstrap;
import io.vertx.core.*;

public class BootstrapVerticlesDeploymentOptions extends AbstractVerticle {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    DeploymentOptions dOptions =new DeploymentOptions();
    dOptions.setInstances(3);
    dOptions.setThreadingModel(ThreadingModel.EVENT_LOOP); // false || making worker thread false

    vertx.deployVerticle(FirstVerticle.class.getName(), dOptions);
    vertx.deployVerticle("com.example.vertx_project.verticles.CustomerVerticle",dOptions );
  }
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    startPromise.complete();
  }
}
