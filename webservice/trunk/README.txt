Make sure to download beehive V1 distribution.  (For now you need to download
the beehive from subversion and build the distribution target).  See Beehive 
Documentation for building the distribution.


BEEHIVE_HOME must be set to the root of the distribution.


TO RUN DRT:  

You need to set up a context for "sctestServer" in the servlet that 
points to the following directory: 

		<ServiceControl-TRUNK>/drt/servers/webapp

After the context is set make sure the following URL doesn't 
generate an error.
http://localhost:8080/sctestServer/web/Service.jws?wsdl


