package com.example.w7_miniproject_backend.repository;

import com.example.w7_miniproject_backend.domain.Liken;
import com.example.w7_miniproject_backend.domain.Post;
import com.example.w7_miniproject_backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Liken,Long> {
    Optional<Liken> findByUserAndPost(User user, Post post);
    void deleteByPostAndUser(Post post, User user);
    Long countAllByPostId(Long postId);
}
