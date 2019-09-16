package com.pubsub.project.Publisher;

import com.pubsub.project.Employee;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class HRApp {
    public static void main(String[] args) throws NamingException {
        InitialContext initialContext=new InitialContext();
        Topic topic=(Topic)initialContext.lookup("topic/myTopic");

        try(ActiveMQConnectionFactory cf=new ActiveMQConnectionFactory();
            JMSContext jmsContext=cf.createContext())
        {
            Employee emp=new Employee();
            emp.setId(2343);
            emp.setFirstName("ashish");
            emp.setLastName("chopra");
            emp.setEmail("ashish.chopra@e2open.com");
            emp.setPhone("374991023");

            for(int i=1;i<=10;i++)
            jmsContext.createProducer().send(topic,emp);

            System.out.println("Message sent");


        }
    }
}
