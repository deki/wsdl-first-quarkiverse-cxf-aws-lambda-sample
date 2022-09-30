# Modernize APIs to run serverless using Apache CXF

This example combines 
[the WSDL-first CustomerService sample from Apache CXF](https://github.com/apache/cxf/blob/master/distribution/src/main/release/samples/wsdl_first/src/main/resources/CustomerService.wsdl) 
with [Quarkus](http://www.quarkus.io) to deploy it in a serverless fashion using [AWS Lambda](https://aws.amazon.com/lambda/).

## Prerequisites
Please follow the [prerequisites in the Quarkus Guide](https://quarkus.io/guides/amazon-lambda-http#prerequisites).

## Java runtime

### Build

    mvn clean package

### Deploy

    sam deploy -t target/sam.jvm.yaml -g --stack-name cxf-wsdl-first-sample-app
    
Confirm (with y) that it's ok do not have authorization for testing.
    
### Test

1. Copy the URL (similar to `http://XXXXX.execute-api.REGION.amazonaws.com/`) from the Outputs and append `services/customerService`.
2. Run

    curl -X POST -d '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:cus="http://customerservice.example.com/"><soapenv:Body><cus:getCustomersByName><name>My test customername</name></cus:getCustomersByName></soapenv:Body></soapenv:Envelope>' http://XXXXX.execute-api.REGION.amazonaws.com/services/customerService | xmllint --format -

### Logs

Check the logs for Init Duration:

   sam logs --stack-name cxf-wsdl-first-sample-app --start-time '1min ago' 

Likely it is a high value (>5000ms).


## Custom runtime (GraalVM)

### Build

    mvn clean package -Dnative

Check the [Quarkus guide](https://quarkus.io/guides/amazon-lambda-http#build-and-deploy) for more detailed instructions (e.g. how to build on Windows).

### Deploy

    sam deploy -t target/sam.native.yaml -g --stack-name cxf-wsdl-first-sample-native-app
    
Confirm (with y) that it's ok do not have authorization for testing.

### Test

See above (but make sure to use the different URL).

### Logs

   sam logs --stack-name cxf-wsdl-first-sample-native-app --start-time '1min ago' 

The duration compared to the Java runtime above is much lower.