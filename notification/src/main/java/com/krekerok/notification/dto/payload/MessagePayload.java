package com.krekerok.notification.dto.payload;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.krekerok.notification.dto.payload.user.UserGreetingPayload;


@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserGreetingPayload.class, name = "userGreetingPayload"),
})
public interface MessagePayload {
}
