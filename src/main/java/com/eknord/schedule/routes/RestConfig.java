package com.eknord.schedule.routes;

import com.eknord.schedule.data.Api;
import com.eknord.schedule.data.Summary;

import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class RestConfig extends RestRouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration()
            .component("servlet").port(8080)
            .bindingMode(RestBindingMode.json)
            .dataFormatProperty("prettyPrint", "true");

        rest("/").consumes("application/json")
            // public methods
            .put("/apis/schedule").type(Api.class).outType(Summary.class).to("direct:schedule_api")
            // internal only
            .get("/control-panel/{task}").to("direct:control-panel");
        
    }
    
}
