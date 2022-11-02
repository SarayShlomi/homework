package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        JsonExportVisitor exporter = new JsonExportVisitor();

        ArrayList<Entity> items = getItems();

        for (Entity e: items) {
            e.accept(exporter);
        }
    }

    private static ArrayList<Entity> getItems(){
        ArrayList<Entity> items = new ArrayList<>();
        User user = new User();
        user.setName("this" + " is a user");
        items.add(user);

        Asset asset = new Asset();
        asset.setOwner("the owner");
        items.add(asset);

        Group group = new Group();
        group.setSize(99);
        items.add(group);

        return items;
    }
}

