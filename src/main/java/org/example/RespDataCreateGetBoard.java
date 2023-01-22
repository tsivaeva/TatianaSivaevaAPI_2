package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespDataCreateGetBoard {
    private String id;
    private String name;
    private String desc;
    private boolean closed;
    private boolean pinned;
    private String url;
    private String shortUrl;

    public RespDataCreateGetBoard(String id, String name, String desc, boolean closed, boolean pinned, String url, String shortUrl) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.closed = closed;
        this.pinned = pinned;
        this.url = url;
        this.shortUrl = shortUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isClosed() {
        return closed;
    }

    public boolean isPinned() {
        return pinned;
    }

    public String getUrl() {
        return url;
    }

    public String getShortUrl() {
        return shortUrl;
    }
}
