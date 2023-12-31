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
public class UserPayload implements MessagePayload {
    private String firstName;
    private String lastName;
    private String role;
}
