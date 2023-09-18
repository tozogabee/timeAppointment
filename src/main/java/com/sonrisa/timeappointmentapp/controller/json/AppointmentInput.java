package com.sonrisa.timeappointmentapp.controller.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class AppointmentInput {

    @JsonProperty("name")
    @JsonPropertyDescription("client name")
    private String name;

    @JsonProperty("from")
    @JsonFormat(pattern="yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime from;

    @JsonProperty("to")
    @JsonFormat(pattern="yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime to;

}
