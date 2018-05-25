package com.lex.archsample.model.dataobject.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;


@Value
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
public class UserDto {
    @SerializedName("id")
    Integer id;
    @SerializedName("login")
    String login;
    @SerializedName("avatar_url")
    String avatarUrl;
    @SerializedName("url")
    String url;
    @SerializedName("html_url")
    String htmlUrl;
    @SerializedName("repos_url")
    String reposUrl;
}
