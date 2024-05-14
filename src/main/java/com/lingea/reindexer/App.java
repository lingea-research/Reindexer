package com.lingea.reindexer;

public class App {
    public static void main(String[] args) {
        System.out.println("Running reindexer...");
        String indexingServiceUrl = "localhost:8080/index";
        if (args.length > 3) {
            indexingServiceUrl = args[3];
        }
        Reindexer reindexer = new Reindexer(args[0], args[1], args[2], indexingServiceUrl);
        reindexer.reindexAll();
        System.out.println("End peacefully.");
    }
}
