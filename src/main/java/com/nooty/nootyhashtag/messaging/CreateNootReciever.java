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

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;
@Controller
public class CreateNootReciever {

    @Autowired
    private HashtagRepo hashtagRepo;

    @Autowired
    private HashtagNootRepo hashtagNootRepo;


    private final static String QUEUE_NAME = "newHashtag";
    private Gson gson;

    private CreateNootReciever() throws Exception {
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
            saveHashtag(message);
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});
    }

    private void saveHashtag(String data) {
        HashtagViewModel viewModel = gson.fromJson(data, HashtagViewModel.class);
        String hashtag = viewModel.getHashtag().toLowerCase();
        Optional<Hashtag> hashtagOptional = this.hashtagRepo.findByHashtag(hashtag);

        if (!hashtagOptional.isPresent()) {
            Hashtag h = new Hashtag();
            h.setHashtag(hashtag);
            h.setId(UUID.randomUUID().toString());
            this.hashtagRepo.save(h);
            hashtagOptional = this.hashtagRepo.findByHashtag(hashtag);
        }
        Hashtag h = hashtagOptional.get();

        HashtagNoot hn = new HashtagNoot();
        hn.setHashtagId(h.getId());
        hn.setUserId(viewModel.getUserId());
        hn.setNootId(viewModel.getNootId());
        hn.setDate();

        saveHashtagNoot(hn);
    }

    private void saveHashtagNoot(HashtagNoot hn) {
        if (!this.hashtagNootRepo.existsByHashtagIdAndNootId(hn.getHashtagId(), hn.getNootId())) {
            hn.setId(UUID.randomUUID().toString());
            this.hashtagNootRepo.save(hn);
        }
    }
}
