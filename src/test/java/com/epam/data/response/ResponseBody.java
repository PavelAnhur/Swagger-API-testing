package com.epam.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ResponseBody {
    private final int code;
    private final String type;
    private final String message;
}
