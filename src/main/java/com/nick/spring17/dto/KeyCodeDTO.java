package com.nick.spring17.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KeyCodeDTO {
    String id;

    String keycode;

    String token;

}
