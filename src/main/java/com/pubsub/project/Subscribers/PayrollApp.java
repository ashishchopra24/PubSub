package com.pubsub.project.Subscribers;

import com.pubsub.project.Employee;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PayrollApp {

    public static void main(String[] args) throws NamingException, JMSException {
        System.out.println("App started");
        InitialContext initialContext=new InitialContext();
        Topic topic=(Topic)initialContext.lookup("topic/myTopic");

        try(ActiveMQConnectionFactory cf=new ActiveMQConnectionFactory();
            JMSContext jmsContext=cf.createContext())
        {
            JMSConsumer consumer=jmsContext.createConsumer(topic);
            Message message=consumer.receive();
            Employee employee=message.getBody(Employee.class);
            System.out.println(employee.getFirstName());




        }
    }
}
