'''
Created on 2012-9-20


'''
import httplib


body='''<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:cxf="http://cxf.demo/">
   <soapenv:Header>
   <id>123</id>
   </soapenv:Header>
   <soapenv:Body>
      <cxf:sayHi>
         <!--Optional:-->
         <arg0>ddd</arg0>
      </cxf:sayHi>
   </soapenv:Body>
</soapenv:Envelope>'''

conn = httplib.HTTPConnection("localhost", "8080")
## conn = httplib.HTTPSConnection("10.112.177.233", "1314")

###
# Note: don't reuse the conn object
###
##conn.request("GET", "/mebusw/HelloWorld?WSDL")

conn.request("POST", "/mebusw/HelloWorld", body=body)


r2 = conn.getresponse()
print r2, dir(r2)
print r2.read()