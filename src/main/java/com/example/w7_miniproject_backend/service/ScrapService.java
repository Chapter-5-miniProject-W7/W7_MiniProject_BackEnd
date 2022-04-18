package com.example.w7_miniproject_backend.service;

import com.example.w7_miniproject_backend.domain.Post;
import com.example.w7_miniproject_backend.domain.Scrapbook;
import com.example.w7_miniproject_backend.domain.User;
import com.example.w7_miniproject_backend.dto.ScrapDto;
import com.example.w7_miniproject_backend.repository.PostRepository;
import com.example.w7_miniproject_backend.repository.ScrapRepository;
import com.example.w7_miniproject_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScrapService {
    private final ScrapRepository scrapRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public ScrapDto createScrap(Long postId, String username) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("유저가 없습니다."));
        Scrapbook scrap = Scrapbook.builder()
                .post(post)
                .user(user)
                .build();
        //있어? 지우고 0줘
        if (scrapRepository.findByPostIdAndUsername(scrap)) {
            scrapRepository.deleteByPostIdAndUsername(scrap);
            Long cnt = scrapRepository.countAllByPostId(postId);
            return ScrapDto.builder()
                    .scrapbool(false)
                    .scrapcnt(cnt)
                    .build();
        }
        //없어? 만들고 1줘
        else {
            scrapRepository.save(scrap);
            Long cnt = scrapRepository.countAllByPostId(postId);
            return ScrapDto.builder()
                    .scrapbool(false)
                    .scrapcnt(cnt)
                    .build();
        }

    }

    public List<Post> readScrap(String username) {
        List<Scrapbook> scrapbook = scrapRepository.findAllByUsername(username);
        List<Post> list = new ArrayList<>();
        for (Scrapbook scrapbooks : scrapbook) {
            list.add(scrapbooks.getPost());
        }
        return list;
    }
}