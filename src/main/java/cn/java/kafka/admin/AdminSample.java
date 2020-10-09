package cn.java.kafka.admin;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;

import java.util.*;


@Slf4j
public class AdminSample {

    private static final int NUM_PARTITIONS = 1;
    private static final short REPLICATION_FACTOR = 1;
    private static final String TOPIC_NAME = "lollipop98";

    private static final AdminClient adminClient = getAdminClient();

    public static void main(String[] args) throws Exception {
        log.info("createTopic");
        createTopic();

        log.info("topicLists");
        topicLists();
    }

    /**
     * 创建 topic 实例
     */
    public static void createTopic() {
        NewTopic newTopic = new NewTopic(TOPIC_NAME, NUM_PARTITIONS, REPLICATION_FACTOR);
        CreateTopicsResult result = adminClient.createTopics(Collections.singletonList(newTopic));
        log.info("createTopic result: {}", result);
    }


    /**
     * 设置 AdminClient
     *
     * @return
     */
    public static AdminClient getAdminClient() {
        Properties properties = new Properties();
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.161.100:9092");

        AdminClient adminClient = AdminClient.create(properties);
        log.info("adminClient: {}", adminClient);
        return adminClient;
    }

    public static void topicLists() throws Exception {
        ListTopicsOptions options = new ListTopicsOptions();
        options.listInternal(true);

        ListTopicsResult listTopicsResult = adminClient.listTopics(options);
        Set<String> names = listTopicsResult.names().get();
        Collection<TopicListing> topicListings = listTopicsResult.listings().get();
        Map<String, TopicListing> stringTopicListingMap = listTopicsResult.namesToListings().get();

        log.info("===============names===============");
        names.forEach(log::info);

        log.info("===============topicListings===============");
        topicListings.forEach(topicListing -> log.info("{}", topicListing));

        log.info("===============stringTopicListingMap===============");
        stringTopicListingMap.forEach((a, b) -> log.info("{} : {}", a, b));
    }

}
