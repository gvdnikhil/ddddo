package com.hashedin.huSpark.model.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@NoArgsConstructor
public class MessageResponse {

    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

}
