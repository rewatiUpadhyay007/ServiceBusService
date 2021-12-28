package com.pde.pdedemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pde.service.*;
import java.io.IOException;

@RestController
@RequestMapping("/blob")
public class BlobController 
{
	@Autowired
	private AzureBlobService azureService;
		
    @GetMapping("/readBlobFile/{container}/{fileName}")
    public String readBlobFile(@PathVariable("container") String container, @PathVariable("fileName") String fileName) throws IOException 
    {
        return azureService.readFromBlob(container, fileName);
    }
     
/*
    @PostMapping("/writeBlobFile")
    public String writeBlobFile(@RequestBody String data) throws IOException 
    {
        try (OutputStream os = ((WritableResource) this.blobFile).getOutputStream()) {
            os.write(data.getBytes());
        }
        return "file was updated";
    }
    */
    
}
