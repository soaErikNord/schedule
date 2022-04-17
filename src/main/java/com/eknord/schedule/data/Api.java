package com.eknord.schedule.data;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "graph"
})
@Generated("jsonschema2pojo")
public class Api {
    @JsonProperty("graph")
    @JsonPropertyDescription("Graph of all cities")
    private String graph;

    public Api() {

    }

    public Api(String graph) {
        super();
        this.graph = graph;
    }

    @JsonProperty("graph")
    public void setGraph(String graph) {
        this.graph = graph;
    }

    public Api withGraph(String graph) {
        this.graph = graph;
        return this;
    }
}
