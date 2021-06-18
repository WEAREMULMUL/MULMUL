package com.excmul.domain.user.dto;

import com.excmul.domain.util.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String phoneNumber, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String phoneNumber, String picture) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}