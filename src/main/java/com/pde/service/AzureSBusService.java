package com.pde.service;

import java.io.IOException;
import java.util.List;

public interface AzureSBusService 
{
	public void AddMessageToQueue(String queueName, String mssage) throws IOException;
	public void AddJSONMessageToTopic(String topicName, String message) throws IOException ;
	public List<String>  ReadMessageFromQueue(String queueName, int number) throws IOException,InterruptedException;
	public void AddMessageToTopic(String topicName, String message) throws IOException;
	public String ReadMessageFromSubscription(String topicName, String subsciptionName) throws IOException,InterruptedException;
	public List<String> ReadMessageFromSubscription(String topicName, String subsciptionName,int size) throws IOException,InterruptedException;
}
