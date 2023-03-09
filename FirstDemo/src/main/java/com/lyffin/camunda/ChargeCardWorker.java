package com.lyffin.camunda;

import org.camunda.bpm.client.ExternalTaskClient;

import java.awt.*;
import java.net.URI;
import java.util.logging.Logger;

public class ChargeCardWorker {
    private final static Logger LOGGER = Logger.getLogger(ChargeCardWorker.class.getName());

    public static void main(String[] args) {
        ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl("http://127.0.0.1:8080/engine-rest")
                .asyncResponseTimeout(10000) //超时时间
                .build();

        // subscribe to an external task topic as specified in the process
        client.subscribe("charge-card")
                .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
                .handler((externalTask, externalTaskService) -> {
                    // Put your business logic here

                    // Get a process variable
                    String item = externalTask.getVariable("item");
                    Long amount = externalTask.getVariable("amount");

                    LOGGER.info("Charging credit card with an amount of '" + amount + "'€ for the item '" + item + "'...");
                    try {
                        Desktop.getDesktop().browse(new URI("https://docs.camunda.org/get-started/quick-start/complete"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Complete the task
                    externalTaskService.complete(externalTask);
                })
                .open();
    }
}
