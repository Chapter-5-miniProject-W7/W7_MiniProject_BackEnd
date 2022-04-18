package com.example.w7_miniproject_backend.service;

import com.example.w7_miniproject_backend.domain.Post;
import com.example.w7_miniproject_backend.domain.User;
import com.example.w7_miniproject_backend.dto.postDto.PostRequestDto;
import com.example.w7_miniproject_backend.dto.postDto.PostResponseDto;
import com.example.w7_miniproject_backend.repository.PostRepository;
import com.example.w7_miniproject_backend.repository.UserRepository;
import com.example.w7_miniproject_backend.security.jwt.JwtDecoder;
import com.fasterxml.classmate.AnnotationOverrides;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {
    private final AwsS3Service awsS3Service;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtDecoder jwtDecoder;

    @Transactional
    public ResponseEntity save(MultipartFile multipartFile, PostRequestDto.SaveRequest postDto, String user) {
        String username = jwtDecoder.decodeUsername(user);
//        PostValidator.validatePostSaveRegister(postDto, multipartFile, username);
        Map<String, String> map = awsS3Service.uploadFile(multipartFile);
        User joinUser = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("유효한 회원을 찾을 수 없습니다."));
        Post post = Post.builder()
                .roomimg(map.get("fileName"))
                .roomUrl(map.get("url"))
                .des(postDto.getDes())
                .category(postDto.getCategory())
                .user(joinUser)
                .build();
        // 추가로 카테고리 저장.
        return ResponseEntity.ok().body("등록 완료");
    }

    public ResponseEntity getPostDeatils(Long postId) {
        return ResponseEntity.ok().body("출력");
    }

    public ResponseEntity<PostResponseDto> getAllPost() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto.MainResponse> postReponse = new ArrayList<>();
        return new ResponseEntity(HttpStatus.OK);
    }
}
