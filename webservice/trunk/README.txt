TO RUN DRT:  

You need to set up a context for "sctestServer" in the servlet that 
points to the following directory: 

		<ServiceControl-TRUNK>//drt/servers/webapp

After the context is set make sure the following URL doesn't 
generate an error.
http://localhost:8080/sctestServer/web/Service.jws?wsdl


TO RUN GOOGLE SAMPLE:

Make sure the 
@WSDL(path ="xxxxx"  in file: 

	<ServiceControl-TRUNK>/samples/google/src/model/GoogleClient.jcx
	
Should point to the Googlewsdl.  The wsdl is in the same dir as the .jcx.

