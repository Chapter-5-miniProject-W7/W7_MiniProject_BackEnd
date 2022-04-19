package com.example.w7_miniproject_backend.service;

import com.example.w7_miniproject_backend.domain.Liken;
import com.example.w7_miniproject_backend.domain.Post;
import com.example.w7_miniproject_backend.domain.User;
import com.example.w7_miniproject_backend.dto.LikeDto;
import com.example.w7_miniproject_backend.repository.LikeRepository;
import com.example.w7_miniproject_backend.repository.PostRepository;
import com.example.w7_miniproject_backend.repository.UserRepository;
import com.example.w7_miniproject_backend.security.jwt.JwtDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtDecoder jwtDecoder;

    @Transactional
    public LikeDto registerLike(Long postId, String usernameDecode){
        String username = jwtDecoder.decodeUsername(usernameDecode);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("유저가 없습니다."));
        Liken liken = Liken.builder()
                .post(post)
                .user(user)
                .build();


        //있어? 지우고 0줘
        if (likeRepository.findByUserAndPost(user, post).orElse(null) == null){
            likeRepository.save(liken);
            Long cnt = likeRepository.countAllByPostId(postId);
            return LikeDto.builder()
                    .likebool(true)
                    .likecnt(cnt)
                    .build();
        }
        //없어? 만들고 1줘
        else {
            likeRepository.deleteByPostAndUser(post, user);
            Long cnt = likeRepository.countAllByPostId(postId);
            return LikeDto.builder()
                    .likebool(false)
                    .likecnt(cnt)
                    .build();
        }

    }
}

like 테이블에는 user정보, post정보,
post에는 like 갯수와, user에 따른 boolean값이 들어가야함
        like갯수와 boolean값이 들어가야함
        post
그렇다면
long likecnt = 포스트아이디로 찾은 countby결과값
boolean은 유저와 포스트아이디를 찾아서 있으면 1 없으면 0


