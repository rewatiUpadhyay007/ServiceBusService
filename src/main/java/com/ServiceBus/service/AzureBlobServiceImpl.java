package com.ServiceBus.service;

import java.io.IOException;
import java.nio.charset.Charset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import com.azure.spring.autoconfigure.storage.resource.AzureStorageResourcePatternResolver;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

@Service
public class AzureBlobServiceImpl implements AzureBlobService
{

	@Value("${azure.storage.custom.connection-string}")
	private  String connectionString;
	   
	@Value("${azure.storage.blob-endpoint}")
	private  String endpoint;
	
	@Override
	public String readFromBlob(String containerName, String fileName) throws IOException 
	{
		/* String searchLocation = "azure-blob://" + containerName + "/" + fileName;	
		 System.out.print("\n+++++++++++++++++++"+searchLocation+"\n+++++++++++++++++++");
		 BlobServiceClient client = new BlobServiceClientBuilder().connectionString(connectionString).endpoint(endpoint).buildClient();
         AzureStorageResourcePatternResolver storageResourcePatternResolver = new AzureStorageResourcePatternResolver(client);
         Resource resource = storageResourcePatternResolver.getResource(searchLocation);
         return StreamUtils.copyToString(resource.getInputStream(),Charset.defaultCharset());
         */
		
		//String searchLocation = "azure-blob://" + containerName + "/" + fileName;
		String searchLocation = "azure-blob://" + containerName + "/" + fileName;
		System.out.print("\n+++++++++++++++++++"+searchLocation+"\n+++++++++++++++++++");
        String connectionString = "DefaultEndpointsProtocol=https;AccountName=mystorsgeaccountpde;AccountKey=Y6mX9oa3Qa3MAnz2Q0zcG/wN8AEhZMGup5RQSCq34yZAtHgWWWYWJY/Jl9QU6xy8Xe+FWRMyXie/uTEAkKpZTA==;EndpointSuffix=core.windows.net";
        String endpoint = "https://mystorsgeaccountpde.blob.core.windows.net/";
	
		BlobServiceClient client = new BlobServiceClientBuilder().connectionString(connectionString).endpoint(endpoint).buildClient();
	    AzureStorageResourcePatternResolver storageResourcePatternResolver = new AzureStorageResourcePatternResolver(client);
	
	    Resource resource = storageResourcePatternResolver.getResource(searchLocation);

		return StreamUtils.copyToString(
				resource.getInputStream(),
		        Charset.defaultCharset());
	}

	@Override
	public void writeToBlob(String containerName, String fileName, String contect) throws IOException 
	{
		// TODO Auto-generated method stub
		
	}
/*
	@Override
	public String getSecret(String keyVaultName, String secretName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSecret(String keyVaultName, String secretName, String secretValue) {
		// TODO Auto-generated method stub
		
	}
*/
}
