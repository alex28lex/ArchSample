package com.lex.archsample.model.dataobject;

import android.support.annotation.NonNull;

import com.lex.archsample.model.dataobject.dto.UserDto;

import java.util.ArrayList;
import java.util.List;


public final class UserDtoMocker {

    public static UserDto getUserDto(@NonNull String login) {
        return UserDto.builder()
                .login(login)
                .build();
    }

    public static List<UserDto> getUserDtoList(int count) {
        final List<UserDto> dtoList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            dtoList.add(getUserDto("Login " + i));
        }
        return dtoList;
    }
}
