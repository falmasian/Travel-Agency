//package com.flightagency.client;
//
//import java.io.*;
//import java.net.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class ClientManager{
//
//	private final int port;
//	private static final Logger logger = LoggerFactory.getLogger(ClientManager.class);
//
//	public ClientManager(int port) {
//        this.port = port;
//    }
//	public ClientManager() {
//        this(9876);
//    }
//
//	public void execute() {
//        try {
//            InetAddress host = InetAddress.getLocalHost();
//            Socket socket = new Socket(host.getHostName(), port);
//
//		    new ReadThread(socket).start();
//            new WriteThread(socket).start();
//
//        } catch (UnknownHostException ex) {
//            logger.error("Server not found.");
//        } catch (IOException ex) {
//           logger.error("I/O Error. ");
//        }
//		 catch (Exception ex) {
//            logger.error("Error in the server");
//        }
//    }
//}