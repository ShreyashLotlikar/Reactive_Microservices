package com.example.vertx_project;


import com.example.vertx_project.verticles.FirstVerticle;
import io.vertx.core.Vertx;

public class MainVerticle  {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    FirstVerticle fv = new FirstVerticle();
    vertx.deployVerticle(fv);
  }
}
