package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class JsonExportVisitor implements Visitor {

    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @Override
    public void visit(User item) {
        System.out.println("user: " + gson.toJson(item));
    }

    @Override
    public void visit(Asset item) {
        System.out.println("asset: " + gson.toJson(item));
    }

    @Override
    public void visit(Group item) {
        System.out.println("group: " + gson.toJson(item));
    }
}
