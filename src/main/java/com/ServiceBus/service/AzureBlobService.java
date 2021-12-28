package com.ServiceBus.service;

import java.io.IOException;

public interface AzureBlobService 
{
	public String readFromBlob(String containerName, String fileName) throws IOException;
	public void writeToBlob(String containerName, String fileName, String contect) throws IOException;
	//public String getSecret(String keyVaultName, String secretName);
	//public void setSecret(String keyVaultName, String secretName, String secretValue);
}
