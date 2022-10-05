package com.example.hackathon2022.global.response;

import com.example.hackathon2022.global.response.result.CommonResultResponse;
import com.example.hackathon2022.global.response.result.SingleResultResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    @Getter
    @AllArgsConstructor
    public enum CommonResponse {
        SUCCESS(true, "성공", HttpStatus.OK);

        private boolean success;
        private String message;
        private HttpStatus httpStatus;
    }

    public CommonResultResponse getSuccessResult() {
        return getCommonResultResponse();
    }

    public <T> SingleResultResponse<T> getSingleResultResponse(T result) {
        return new SingleResultResponse<>(getCommonResultResponse(), result);
    }

    public CommonResultResponse getCommonResultResponse() {
        return new CommonResultResponse(CommonResponse.SUCCESS.isSuccess(), CommonResponse.SUCCESS.message, CommonResponse.SUCCESS.httpStatus);
    }
}
