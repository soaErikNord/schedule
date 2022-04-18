package com.eknord.schedule.routes.schedule_api;

import com.eknord.schedule.data.Summary;
import com.eknord.schedule.preprocessor.ScheduleRules;
import com.eknord.schedule.routes.RestRouteBuilder;

import org.springframework.stereotype.Component;

@Component
public class ScheduleApi extends RestRouteBuilder {

    @Override
    public void configure() throws Exception {
        final Summary summary = new Summary();
        final ScheduleRules sRules = new ScheduleRules();

        // Main Route
        from("direct:schedule_api").routeId("schedule_api")
            .streamCaching()
            .setHeader("graph", jsonpath("$.graph"))
            
            // .bean(Trips.class, "buildGraph(${header.graph})")
            
            .setHeader("Rule1", method(sRules, "getRule1(${header.graph})"))
            .bean(summary, "setAdditionalProperty('Output 1: ', ${header.Rule1})")
            
            .setHeader("Rule2", method(sRules, "getRule2(${header.graph})"))
            .bean(summary, "setAdditionalProperty('Output 2: ', ${header.Rule2})")
            
            .setHeader("Rule3", method(sRules, "getRule3(${header.graph})"))
            .bean(summary, "setAdditionalProperty('Output 3: ', ${header.Rule3})")
            
            .setHeader("Rule4", method(sRules, "getRule4(${header.graph})"))
            .bean(summary, "setAdditionalProperty('Output 4: ', ${header.Rule4})")
            
            .setHeader("Rule5", method(sRules, "getRule5(${header.graph})"))
            .bean(summary, "setAdditionalProperty('Output 5: ', ${header.Rule5})")
            
            .setHeader("Rule6", method(sRules, "getRule6(${header.graph})"))
            .bean(summary, "setAdditionalProperty('Output 6: ', ${header.Rule6})")
            
            .setHeader("Rule7", method(sRules, "getRule7(${header.graph})"))
            .bean(summary, "setAdditionalProperty('Output 7: ', ${header.Rule7})")
            
            .setHeader("Rule8", method(sRules, "getRule8(${header.graph})"))
            .bean(summary, "setAdditionalProperty('Output 8: ', ${header.Rule8})")
            
            .setHeader("Rule9", method(sRules, "getRule9(${header.graph})"))
            .bean(summary, "setAdditionalProperty('Output 9: ', ${header.Rule9})")
            
            .setHeader("Rule10", method(sRules, "getRule10(${header.graph})"))
            .bean(summary, "setAdditionalProperty('Output 10: ', ${header.Rule10})")
            // .bean(ScheduleRules.class, "getRule1(${header.graph}, summary)")
            .setBody().method(summary, "getAdditionalProperties")
            .to("direct:reply");
        
    }
    
}
