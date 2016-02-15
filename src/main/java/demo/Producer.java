package demo;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * Created by mx on 16/2/15.
 */
public class Producer {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("Producer");
        producer.setNamesrvAddr("172.16.24.105:9876");

        try {
            producer.start();
            Message message = new Message("PushTopic", "push", "1", "Just for test.".getBytes());
            SendResult result = producer.send(message);
            System.out.println("id:" + result.getMsgId() + "\nresult:" + result.getSendStatus() + "\n");

            message = new Message("PushTopic", "push", "2", "Just for test.".getBytes());
            result = producer.send(message);
            System.out.println("id:" + result.getMsgId() + "\nresult:" + result.getSendStatus() + "\n");


            message = new Message("PullTopic", "pull", "1", "Just for test.".getBytes());
            result = producer.send(message);
            System.out.println("id:" + result.getMsgId() + "\nresult:" + result.getSendStatus() + "\n");


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }
}
