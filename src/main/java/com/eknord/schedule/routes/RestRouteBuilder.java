package com.eknord.schedule.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:reply").routeId("reply")
            .removeHeaders("*", Exchange.HTTP_RESPONSE_CODE);
        
    }
    
}
