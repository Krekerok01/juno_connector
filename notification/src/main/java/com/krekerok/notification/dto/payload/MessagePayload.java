package com.krekerok.notification.dto.payload;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.krekerok.notification.dto.payload.user.UserPayload;


@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserPayload.class, name = "userPayload")
})
public interface MessagePayload {
}
