package com.example.basegit.publisher;

import java.util.*;
import java.util.stream.Collectors;

public class PublisherSubscriber {

    private static final String TOPIC_INFO = "info";
    private static final String TOPIC_WARNING = "warning";
    private static final String TOPIC_ERROR = "error";

    public static void main(String[] args) {
        Subscriber subscriberInfo = new SubscriberInfoImpl();
        Subscriber subscriberWarning = new SubscriberWarningImpl();
        Subscriber subscriberError = new SubscriberErrorImpl();

        Publisher publisher = new PublisherImpl();
        publisher.subscribe(TOPIC_INFO, subscriberInfo);
        publisher.subscribe(TOPIC_WARNING, subscriberWarning);
        publisher.subscribe(TOPIC_ERROR, subscriberError, subscriberWarning);
        publisher.publish();


        Publisher publisher2 = new PublisherImpl();
        publisher2.subscribe(TOPIC_INFO, subscriberInfo);
        publisher2.subscribe(TOPIC_WARNING, subscriberWarning);
        publisher2.subscribe(TOPIC_ERROR, subscriberError, subscriberWarning);
        publisher2.unsubscribe(TOPIC_ERROR, subscriberWarning);
        publisher2.publish();

    }

    public interface Publisher {
        void subscribe(String topic, Subscriber... subscribers);

        void unsubscribe(String topic, Subscriber subscriber);

        void publish();
    }

    public static class PublisherImpl implements Publisher {
        private Map<String, List<Subscriber>> subscriberList;

        public PublisherImpl() {
            this.subscriberList = new HashMap<>();
            subscriberList.put(TOPIC_INFO, new ArrayList<>());
            subscriberList.put(TOPIC_WARNING, new ArrayList<>());
            subscriberList.put(TOPIC_ERROR, new ArrayList<>());
        }

        @Override
        public void subscribe(String topic, Subscriber... subscribers) {
            subscriberList.get(topic).addAll(Arrays.stream(subscribers).collect(Collectors.toList()));
        }

        @Override
        public void unsubscribe(String topic, Subscriber subscriber) {
            subscriberList.get(topic).remove(subscriber);
        }

        @Override
        public void publish() {
            List<Subscriber> infoSubscriber = subscriberList.get(TOPIC_INFO);
            publish(infoSubscriber, "Info message");
            List<Subscriber> warningSubscriber = subscriberList.get(TOPIC_WARNING);
            publish(warningSubscriber, "Warning message");
            List<Subscriber> errorSubscriber = subscriberList.get(TOPIC_ERROR);
            publish(errorSubscriber, "Error message");
        }

        public void publish(List<Subscriber> subscribers, String message) {
            subscribers.forEach(subscriber -> subscriber.receiveMessage(message));
        }
    }


    public interface Subscriber {
        void receiveMessage(String message);

        String getId();
    }

    public static class SubscriberInfoImpl implements Subscriber {

        @Override
        public void receiveMessage(String message) {
            System.out.println("Reading message = " + message + " by publisher = " + this.getId());
        }

        @Override
        public String getId() {
            return "Subscriber - 1";
        }
    }

    public static class SubscriberWarningImpl implements Subscriber {

        @Override
        public void receiveMessage(String message) {
            System.out.println("Reading message = " + message + " by publisher = " + this.getId());
        }

        @Override
        public String getId() {
            return "Subscriber - 2";
        }
    }

    public static class SubscriberErrorImpl implements Subscriber {

        @Override
        public void receiveMessage(String message) {
            System.out.println("Reading message = " + message + " by publisher = " + this.getId());
        }

        @Override
        public String getId() {
            return "Subscriber - 3";
        }
    }
}
