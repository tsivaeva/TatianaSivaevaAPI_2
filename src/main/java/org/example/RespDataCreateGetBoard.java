package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespDataCreateGetBoard  implements Serializable {
    public String id;
    public String name;
    public String desc;
    public boolean closed;
    public boolean pinned;
    public String url;
    public String shortUrl;


}
