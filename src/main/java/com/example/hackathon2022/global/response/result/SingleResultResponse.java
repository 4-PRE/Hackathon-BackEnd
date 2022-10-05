package com.example.hackathon2022.global.response.result;

import lombok.Getter;

@Getter
public class SingleResultResponse<T> extends CommonResultResponse{

    private final T result;

    public SingleResultResponse(CommonResultResponse commonResultResponse, T result) {
        super(commonResultResponse.isSuccess(), commonResultResponse.getMessage(), commonResultResponse.getHttpStatus());
        this.result = result;
    }
}
