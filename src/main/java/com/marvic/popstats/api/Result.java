package com.marvic.popstats.api;

import java.util.List;

public class Result<T> {
    private List<T> payload;

    public Result(List<T> payload) {
        if (payload == null) {
            payload = List.of();
        }
        this.payload = payload;
    }

    public List<T> getPayload() {
        return payload;
    }

    public int getItemsIncluded() {
        return payload.size();
    }
}
