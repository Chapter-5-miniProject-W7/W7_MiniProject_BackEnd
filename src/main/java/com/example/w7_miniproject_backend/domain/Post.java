package com.example.w7_miniproject_backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"post"})
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //columndefinition ="TEXT"로 해야하는가?
    @Column(columnDefinition = "TEXT")
    private String roomimg;
    private String roomurl;

    @JsonManagedReference
    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @JsonManagedReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Liken> liken;

    @JsonManagedReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Scrapbook> scrap;

    @ManyToOne
    private Category category;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;
    //     포스트 정보

    @Column
    private String des;
}
