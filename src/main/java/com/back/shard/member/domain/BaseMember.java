package com.back.shard.member.domain;

import com.back.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;


@MappedSuperclass
@Getter
@Setter(value = PROTECTED)
@NoArgsConstructor
public class BaseMember extends BaseEntity {
    @Column(unique = true)
    private String username;
    private String password;
    private String nickname;
    private int activityScore;

    public BaseMember(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    protected int setActivityScore(int score) {
        this.activityScore += score;
        return score;
    }
}


