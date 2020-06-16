package com.nooty.nootyhashtag.messaging;

import com.google.gson.Gson;
import com.nooty.nootyhashtag.HashtagNootRepo;
import com.nooty.nootyhashtag.HashtagRepo;
import com.nooty.nootyhashtag.models.Hashtag;
import com.nooty.nootyhashtag.models.HashtagNoot;
import com.nooty.nootyhashtag.models.viewmodels.HashtagViewModel;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.UUID;
@Controller
public class DeleteNootReciever {

    @Autowired
    private HashtagRepo hashtagRepo;

    @Autowired
    private HashtagNootRepo hashtagNootRepo;


    private final static String QUEUE_NAME = "deleteHashtag";
    private Gson gson;

    private DeleteNootReciever() throws Exception {
        gson = new Gson();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        //factory.setHost("172.18.0.20");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            deleteHashtagNoot(message);
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});
    }

    private void deleteHashtagNoot(String data) {
        HashtagViewModel viewModel = gson.fromJson(data, HashtagViewModel.class);

        if (!this.hashtagNootRepo.existsByNootId(viewModel.getNootId())) {
            this.hashtagNootRepo.deleteByNootId(viewModel.getNootId());
        }
    }
}
