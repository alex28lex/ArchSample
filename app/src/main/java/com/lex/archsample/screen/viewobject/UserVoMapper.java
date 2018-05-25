package com.lex.archsample.screen.viewobject;

import com.lex.archsample.model.dataobject.dto.UserDto;
import com.lex.archsample.model.dataobject.dto.UserDto;

import java.util.ArrayList;
import java.util.List;


public final class UserVoMapper {

    public static UserVo fromDto(UserDto userDto) {
        return UserVo.builder()
                .id(userDto.getId())
                .login(userDto.getLogin())
                .avatarUrl(userDto.getAvatarUrl())
                .build();
    }

    public static List<UserVo> fromDto(List<UserDto> userDtos) {
        final List<UserVo> userVos = new ArrayList<>();
        for (UserDto userDto : userDtos) {
            userVos.add(fromDto(userDto));
        }
        return userVos;
    }

    public static UserDto toDto(UserVo userVo) {
        return UserDto.builder()
                .id(userVo.getId())
                .login(userVo.getLogin())
                .avatarUrl(userVo.getAvatarUrl())
                .build();
    }

    public static List<UserDto> toDto(List<UserVo> userVos) {
        final List<UserDto> userDtos = new ArrayList<>();
        for (UserVo userVo : userVos) {
            userDtos.add(toDto(userVo));
        }
        return userDtos;
    }
}
