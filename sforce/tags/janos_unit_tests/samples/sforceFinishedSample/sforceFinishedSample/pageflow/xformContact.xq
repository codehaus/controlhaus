{-- pageflow/xformation.dtf#xformContact --}

declare namespace ns0 = "urn:enterprise.soap.sforce.com"
declare namespace ns1 = "urn:sobject.enterprise.soap.sforce.com"

<ns0:create>
    <ns0:sObjects>{ $_XmlObjectDoc/@* , $_XmlObjectDoc/node() }</ns0:sObjects>
</ns0:create>
