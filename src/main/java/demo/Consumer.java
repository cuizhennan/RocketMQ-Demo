package demo;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created by mx on 16/2/15.
 */
public class Consumer {
    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("PushConsumer");
        consumer.setNamesrvAddr("172.16.24.105:9876");
        try {
            consumer.subscribe("PushTopic", "push");
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    Message msg = list.get(0);
                    System.out.println(msg.toString());

                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });

            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
