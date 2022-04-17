package com.eknord.schedule.routes.schedule_api;

import com.eknord.schedule.data.Summary;
import com.eknord.schedule.preprocessor.Trips;
import com.eknord.schedule.routes.RestRouteBuilder;

import org.springframework.stereotype.Component;

@Component
public class ScheduleApi extends RestRouteBuilder {

    @Override
    public void configure() throws Exception {
        final Summary summary = new Summary();

        // Main Route
        from("direct:schedule_api").routeId("schedule_api")
            .streamCaching()
            .bean(Trips.class, "buildGraph(${header.graph})")
            .setBody().method(summary, "getAdditionalProperties")
            .to("direct:reply");
        
    }
    
}
