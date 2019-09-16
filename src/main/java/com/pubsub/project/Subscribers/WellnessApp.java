package com.pubsub.project.Subscribers;

import com.pubsub.project.Employee;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class WellnessApp {

    public static void main(String[] args) throws NamingException, JMSException {
        System.out.println("App started"); System.out.println();

        InitialContext initialContext=new InitialContext();
        Topic topic=(Topic)initialContext.lookup("topic/myTopic");

        try(ActiveMQConnectionFactory cf=new ActiveMQConnectionFactory();
            JMSContext jmsContext=cf.createContext())
        {
            JMSConsumer consumer1=jmsContext.createSharedConsumer(topic,"sharedConsumer");
            JMSConsumer consumer2=jmsContext.createSharedConsumer(topic,"sharedConsumer");

            for(int i=1;i<=10;i=i+2) {
                Message message = consumer1.receive();
                Employee employee = message.getBody(Employee.class);
                System.out.println("Consumer 1 "+employee.getFirstName());

                Message message2 = consumer2.receive();
                Employee employee2 = message2.getBody(Employee.class);
                System.out.println("Consumer 2 "+employee2.getFirstName());
            }




        }
    }
}
