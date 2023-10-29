package com.flightagency.admin;

//public class CreateDbApp {
//
//    public static void main(String[] args) {
//        Logger logger = LoggerFactory.getLogger(CreateDbApp.class);
//        ApplicationContext ctx =
//                new AnnotationConfigApplicationContext(Config.class);
//        CreateTables ct = ctx.getBean(CreateTables.class);
//        if (ct.getConn() != null) {
//            ct.createAllTables();
//        } else {
//            logger.error("could not connect to database!");
//        }
//    }
//}