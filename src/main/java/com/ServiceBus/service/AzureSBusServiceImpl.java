package com.ServiceBus.service;

import java.io.IOException;
import java.time.Duration;

import com.azure.messaging.servicebus.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class AzureSBusServiceImpl implements AzureSBusService
{

	
	@Value("${serviceBusConnectionString}")
	private  String connectionString;
		
	private static String CurrentMessage;
	
	// Create receiver and sender which will share the connection.
			
	@Override
	public void AddMessageToQueue(String queueName, String mssage) throws IOException 
	{
		// TODO Auto-generated method stub
		 // create a Service Bus Sender client for the queue 
	    ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
	            .connectionString(connectionString)
	            .sender()
	            .queueName(queueName)
	            .buildClient();

	    // send one message to the queue
	    senderClient.sendMessage(new ServiceBusMessage(mssage));
	    System.out.println("Sent a single message to the queue: " + queueName); 
	}

	@Override
	public List<String> ReadMessageFromQueue(String queueName, int number) throws IOException, InterruptedException 
	{
		/* // TODO Auto-generated method stub
		CountDownLatch countdownLatch = new CountDownLatch(1);

	    // Create an instance of the processor through the ServiceBusClientBuilder
	    ServiceBusProcessorClient processorClient = new ServiceBusClientBuilder()
	        .connectionString(connectionString)
	        .processor()
	        .queueName(queueName)
	        .processMessage(AzureSBusServiceImpl::processMessage)
	        .processError(context -> processError(context, countdownLatch))
	        .buildProcessorClient();

	    System.out.println("Starting the processor");
	    processorClient.start();
	    TimeUnit.SECONDS.sleep(10);
	    System.out.println("Stopping and closing the processor");
	    
	    processorClient.close(); 
	    
	     */
		 ServiceBusReceiverClient receiver = new ServiceBusClientBuilder()
		            .connectionString(connectionString)
		            .receiver()
		            .maxAutoLockRenewDuration(Duration.ofMinutes(1))
		            .queueName(queueName)
		            .buildClient();
		List<String> messages= new ArrayList<String>();
		 receiver.receiveMessages(number).stream().forEach(message -> {
             // Process message. The message lock is renewed for up to 1 minute.
             System.out.printf("Sequence #: %s. Contents: %s%n", message.getSequenceNumber(), message.getBody());
             messages.add(message.getBody().toString());
             // Messages from the sync receiver MUST be settled explicitly.
             receiver.complete(message);
         });
		return messages;
	}

	@Override
	public void AddMessageToTopic(String topicName, String message) throws IOException 
	{
		// TODO Auto-generated method stub
		 // create a Service Bus Sender client for the queue 
	    ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
	            .connectionString(connectionString)
	            .sender()
	            .topicName(topicName)
	            .buildClient();

	    // send one message to the topic
	    senderClient.sendMessage(new ServiceBusMessage(message));
	    System.out.println("Sent a single message to the topic: " + topicName);
	}
	
	@Override
	public void AddJSONMessageToTopic(String topicName, String message) throws IOException {
		// TODO Auto-generated method stub
		 // create a Service Bus Sender client for the queue 
		
	    ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
	            .connectionString(connectionString)
	            .sender()
	            .topicName(topicName)
	            .buildClient();
	    ServiceBusMessage sbMessage= new ServiceBusMessage(message);
	    sbMessage.setContentType("application/json")	;
	        
	    // send one message to the topic
	    senderClient.sendMessage(sbMessage);
	    System.out.println("Sent a single message to the topic: " + topicName);
	}

	@Override
	public String ReadMessageFromSubscription(String topicName, String subsciptionName) throws IOException,InterruptedException
	{
		// TODO Auto-generated method stub
	    // Create an instance of the processor through the ServiceBusClientBuilder
	    /*ServiceBusProcessorClient processorClient = new ServiceBusClientBuilder()
	        .connectionString(connectionString)
	        .processor()
	        .topicName(topicName)
	        .subscriptionName(subsciptionName)
	        .processMessage(AzureSBusServiceImpl::processMessage)
	        .processError(context -> processError(context, countdownLatch))
	        .buildProcessorClient();

	    System.out.println("Starting the processor");
	    processorClient.start();
	    TimeUnit.SECONDS.sleep(1);

	    System.out.println("Stopping and closing the processor");
	    processorClient.close();  
		return CurrentMessage;
		*/
		
		 ServiceBusReceiverClient receiver = new ServiceBusClientBuilder()
		            .connectionString(connectionString)
		            .receiver()
		            .maxAutoLockRenewDuration(Duration.ofMinutes(1))
		            .topicName(topicName).subscriptionName(subsciptionName)
		            .buildClient();
		List<String> messages= new ArrayList<String>();
		 receiver.receiveMessages(1).stream().forEach(message -> {
          // Process message. The message lock is renewed for up to 1 minute.
          System.out.printf("Sequence #: %s. Contents: %s%n", message.getSequenceNumber(), message.getBody());
          messages.add(message.getBody().toString());
          // Messages from the sync receiver MUST be settled explicitly.
          receiver.complete(message);
      });
		return messages.get(0);
	}
	
	@Override
	public List<String> ReadMessageFromSubscription(String topicName, String subsciptionName,int size) throws IOException,InterruptedException
	{
		// TODO Auto-generated method stub
	    // Create an instance of the processor through the ServiceBusClientBuilder
	    /*ServiceBusProcessorClient processorClient = new ServiceBusClientBuilder()
	        .connectionString(connectionString)
	        .processor()
	        .topicName(topicName)
	        .subscriptionName(subsciptionName)
	        .processMessage(AzureSBusServiceImpl::processMessage)
	        .processError(context -> processError(context, countdownLatch))
	        .buildProcessorClient();

	    System.out.println("Starting the processor");
	    processorClient.start();
	    TimeUnit.SECONDS.sleep(1);

	    System.out.println("Stopping and closing the processor");
	    processorClient.close();  
		return CurrentMessage;
		*/
		
		 ServiceBusReceiverClient receiver = new ServiceBusClientBuilder()
		            .connectionString(connectionString)
		            .receiver()
		            .maxAutoLockRenewDuration(Duration.ofMinutes(1))
		            .topicName(topicName).subscriptionName(subsciptionName)
		            .buildClient();
		List<String> messages= new ArrayList<String>();
		 receiver.receiveMessages(size).stream().forEach(message -> {
          // Process message. The message lock is renewed for up to 1 minute.
          System.out.printf("Sequence #: %s. Contents: %s%n", message.getSequenceNumber(), message.getBody());
          messages.add(message.getBody().toString());
          // Messages from the sync receiver MUST be settled explicitly.
          receiver.complete(message);
      });
		return messages;
	}
	
	private static void processMessage(ServiceBusReceivedMessageContext context) 
	{
	    ServiceBusReceivedMessage message = context.getMessage();
	    
	    System.out.printf("Processing message. Session: %s, Sequence #: %s. Contents: %s%n", message.getMessageId(),
	        message.getSequenceNumber(), message.getBody());
	    CurrentMessage = message.getBody().toString();
	} 
	private static void processError(ServiceBusErrorContext context, CountDownLatch countdownLatch) 
	{
	    System.out.printf("Error when receiving messages from namespace: '%s'. Entity: '%s'%n",
	        context.getFullyQualifiedNamespace(), context.getEntityPath());

	    if (!(context.getException() instanceof ServiceBusException)) {
	        System.out.printf("Non-ServiceBusException occurred: %s%n", context.getException());
	        return;
	    }

	    ServiceBusException exception = (ServiceBusException) context.getException();
	    ServiceBusFailureReason reason = exception.getReason();

	    if (reason == ServiceBusFailureReason.MESSAGING_ENTITY_DISABLED
	        || reason == ServiceBusFailureReason.MESSAGING_ENTITY_NOT_FOUND
	        || reason == ServiceBusFailureReason.UNAUTHORIZED) {
	        System.out.printf("An unrecoverable error occurred. Stopping processing with reason %s: %s%n",
	            reason, exception.getMessage());

	        countdownLatch.countDown();
	    } else if (reason == ServiceBusFailureReason.MESSAGE_LOCK_LOST) {
	        System.out.printf("Message lock lost for message: %s%n", context.getException());
	    } else if (reason == ServiceBusFailureReason.SERVICE_BUSY) {
	        try {
	            // Choosing an arbitrary amount of time to wait until trying again.
	            TimeUnit.SECONDS.sleep(1);
	        } catch (InterruptedException e) {
	            System.err.println("Unable to sleep for period of time");
	        }
	    } else {
	        System.out.printf("Error source %s, reason %s, message: %s%n", context.getErrorSource(),
	            reason, context.getException());
	    }
	}

	
	
}
