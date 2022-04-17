package com.eknord.schedule.routes;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class ControlPanel extends RestRouteBuilder {

    @Override
    public void configure() throws Exception {
        // Main Route
        from("direct:control-panel").routeId("control-panel")
            .setHeader(Exchange.CONTENT_TYPE, simple("text/html"))
            .choice()
                .when(simple("${headers.task} == 'styles.css'"))
                    .to("language:constant:resource:classpath:/pages/styles.css")
                    .setHeader(Exchange.CONTENT_TYPE, simple("text/css"))
                .when(simple("${headers.task} == 'schedule'"))
                    .to("language:constant:resource:classpath:/pages/schedule.html")
                .otherwise()
                    .throwException(new IllegalAccessException("Bad Request"))
            .end();
        
    }
    
}
