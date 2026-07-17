package com.example.vertx_project.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class BootstrapWebVerticle extends AbstractVerticle {
  public static void main(String[] args) {

    System.out.println("BootstrapWebVerticle ");
    Vertx vertx = Vertx.vertx();

    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);
    /*router.get("/getCustomer").handler(context -> {
      context.response().end("Get Customer called");
    });*/
    // if you want to send json
    router.get("/getCustomer").handler(context -> {
      JsonObject customer = new JsonObject();
      customer.put("name","nayab");
      context.response().end(customer.encodePrettily());
    });
    // put create
    // post update
    // patch partail update
    // delete

    router.put("/createCustomer").handler(BodyHandler.create()).handler(context -> {
      JsonObject customer = context.body().asJsonObject();
      System.out.println("Customer : " +customer.toString());

      JsonObject res = new JsonObject();
      res.put("message","customer created sucessfully");
      context.response().end(res.encodePrettily());
    });

    router.post("/updateCustomer").handler(BodyHandler.create()).handler(context -> {
      JsonObject customer = context.body().asJsonObject();
      System.out.println("Customer : " +customer.toString());

      JsonObject res = new JsonObject();
      res.put("message","customer updated sucessfully");
      context.response().end(res.encodePrettily());
    });

    router.patch("/patch Customer").handler(BodyHandler.create()).handler(context -> {
      JsonObject customer = context.body().asJsonObject();
      System.out.println("Customer : " +customer.toString());

      JsonObject res = new JsonObject();
      res.put("message","customer patched sucessfully");
      context.response().end(res.encodePrettily());
    });

    router.delete("/deleteCustomer").handler(BodyHandler.create()).handler(context -> {
      JsonObject customer = context.body().asJsonObject();
      System.out.println("Customer : " +customer.toString());

      JsonObject res = new JsonObject();
      res.put("message","customer deleted sucessfully");
      context.response().end(res.encodePrettily());
    });

    // path params
    router.get("/getCustomer/:id/:name").handler(context -> {
      String id = context.request().getParam("id");
      String name = context.request().getParam("name");

      System.out.println("ID is " +id);
      System.out.println("Name: " +name );
      JsonObject customer = new JsonObject();
      customer.put("name","nayab");
      context.response().end(customer.encodePrettily());
    });

    // Query Params
    router.get("/fetchCustomer").handler(context -> {
      MultiMap params = context.request().params();
      String name = params.get("name");
      String email = params.get("email");

      System.out.println("Name: " +name );
      System.out.println("Email: " +email );
      JsonObject customer = new JsonObject();
      customer.put("name","nayab");
      context.response().end(customer.encodePrettily());
    });


    server.requestHandler(router).listen(8080);
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    startPromise.complete();
  }
}
