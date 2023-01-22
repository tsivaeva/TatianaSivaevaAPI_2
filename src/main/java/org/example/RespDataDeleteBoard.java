package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespDataDeleteBoard implements Serializable {
    public String _value;
}
