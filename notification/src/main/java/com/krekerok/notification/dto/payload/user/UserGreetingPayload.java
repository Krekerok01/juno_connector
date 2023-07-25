package com.krekerok.notification.dto.payload.user;

import com.krekerok.notification.dto.payload.MessagePayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserGreetingPayload implements MessagePayload {
    private String username;
    private String role;
}
