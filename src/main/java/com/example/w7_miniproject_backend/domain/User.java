package com.example.w7_miniproject_backend.domain;

import com.example.w7_miniproject_backend.dto.userDto.SignupRequestDto;
import com.example.w7_miniproject_backend.validator.UserInfoValidator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String userimg;


    //게시물 조회 할때쓸거임
    @JsonIgnoreProperties({"user"})
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Post> posts;


//    @Column(unique = true)
//    private Long kakaoId;

//
//    public User(String nickname, String encodedPassword, Long kakaoId) {
//        this.username = nickname;
//        this.password = encodedPassword;
//        this.kakaoId = kakaoId;
//    }
}
