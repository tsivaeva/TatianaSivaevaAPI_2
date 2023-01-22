package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespDataGetBoard  implements Serializable {
    public String idMember;
    public String memberType;
    public Boolean unconfirmed;
    public  Boolean deactivated;
    public String id;
}
