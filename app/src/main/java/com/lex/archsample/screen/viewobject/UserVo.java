package com.lex.archsample.screen.viewobject;

import java.io.Serializable;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;


@Value
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
public class UserVo implements Serializable {
    Integer id;
    String login;
    String avatarUrl;
}
