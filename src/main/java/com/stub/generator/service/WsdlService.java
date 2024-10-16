package com.stub.generator.service;

import com.stub.generator.model.WsdlEndpoint;
import com.stub.generator.model.WsdlSchema;
import com.stub.generator.repository.WsdlEndpointRepository;
import com.stub.generator.repository.WsdlSchemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.wsdl.Definition;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.WSDLException;
import java.io.File;
import javax.wsdl.PortType;
import javax.wsdl.Operation;

@Service
public class WsdlService {
    @Autowired
    private WsdlEndpointRepository endpointRepository;

    @Autowired
    private WsdlSchemaRepository schemaRepository;

    public void parseWsdl(File wsdlFile) throws WSDLException {
        WSDLFactory wsdlFactory = WSDLFactory.newInstance();
        Definition definition = wsdlFactory.newWSDLReader().readWSDL(wsdlFile.getAbsolutePath());

        for (Object service : definition.getAllServices().values()) {
            for (Object port : ((javax.wsdl.Service) service).getPorts().values()) {
                String endpoint = ((javax.wsdl.Port) port).getBinding().getPortType().getQName().getLocalPart();

                for (Object operationObj : ((PortType) ((javax.wsdl.Port) port).getBinding().getPortType()).getOperations()) {
                    Operation operation = (Operation) operationObj;

                    WsdlEndpoint wsdlEndpoint = new WsdlEndpoint();
                    wsdlEndpoint.setEndpointUrl(endpoint);
                    wsdlEndpoint.setOperationName(operation.getName());
                    endpointRepository.save(wsdlEndpoint);

                    WsdlSchema wsdlSchema = new WsdlSchema();
                    wsdlSchema.setEndpoint(wsdlEndpoint);
                    wsdlSchema.setInputSchema("<inputSchema>...</inputSchema>");  // Placeholder
                    wsdlSchema.setOutputSchema("<outputSchema>...</outputSchema>");  // Placeholder
                    schemaRepository.save(wsdlSchema);
                }
            }
        }
    }
}
