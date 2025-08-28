package com.guanyiping.ticketing.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String userId;
    private String userName;
    private String passWord;
    private String email;

}
