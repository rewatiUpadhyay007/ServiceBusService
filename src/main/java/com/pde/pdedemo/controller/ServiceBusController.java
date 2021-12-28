package com.pde.pdedemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pde.service.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/serviceBus")
public class ServiceBusController 
{
	@Autowired
	private AzureSBusService azureSBusService;
	
	
	@PostMapping("/Queue/{queueName}/{message}")
    public void AddMessageToQueue(@PathVariable("queueName") String queueName,@PathVariable("message") String message) throws IOException 
    {
		azureSBusService.AddMessageToQueue(queueName, message);
    }
		
    @GetMapping("/Queue/{queueName}")
    public List<String>  ReadMessageFromQueue(@PathVariable("queueName") String queueName) throws IOException ,InterruptedException
    {
    	return azureSBusService.ReadMessageFromQueue(queueName,2);
    }
    

	@PostMapping("/Topic/{topicName}/{message}")
    public void AddMessageToTopic(@PathVariable("topicName") String topicName, @PathVariable("message") String message) throws IOException 
    {
		azureSBusService.AddMessageToTopic(topicName, message);
    }
		
    @GetMapping("/Topic/{topicName}/{subscriptionName}")
    public String ReadMessageFromSubscription(@PathVariable("topicName") String topicName,@PathVariable("subscriptionName") String subscriptionName) throws IOException ,InterruptedException
    {
    	return azureSBusService.ReadMessageFromSubscription(topicName,subscriptionName);
    }
    

    @GetMapping("/Topic/{topicName}/{subscriptionName}/{batchSize}")
    public List<String> ReadMessageFromSubscription(@PathVariable("topicName") String topicName,@PathVariable("subscriptionName") String subscriptionName,@PathVariable("batchSize") int batchSize) throws IOException ,InterruptedException
    {
    	return azureSBusService.ReadMessageFromSubscription(topicName,subscriptionName,batchSize);
    }
    
    @PostMapping("/Topic/{topicName}")
    public void AddJSONMessageToTopic(@PathVariable("topicName") String topicName,@RequestBody String message) throws IOException 
    {
    	azureSBusService.AddJSONMessageToTopic(topicName, message);
    }
    
}
