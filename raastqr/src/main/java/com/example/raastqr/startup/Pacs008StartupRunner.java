//package com.example.raastqr.startup;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import com.example.raastqr.service.Pacs008ClientService;
//
//@Component
//public class Pacs008StartupRunner implements CommandLineRunner {
//
//    private final Pacs008ClientService pacs008ClientService;
//    private final boolean sendOnStartup;
//
//    public Pacs008StartupRunner(Pacs008ClientService pacs008ClientService,
//                                @Value("${app.send-on-startup:true}") boolean sendOnStartup) {
//        this.pacs008ClientService = pacs008ClientService;
//        this.sendOnStartup = sendOnStartup;
//    }
//
//    @Override
//    public void run(String... args) {
//        if (sendOnStartup) {
//           // pacs008ClientService.sendPacs008();
//        }
//    }
//}
