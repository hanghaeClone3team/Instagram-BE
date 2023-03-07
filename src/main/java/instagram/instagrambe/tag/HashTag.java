//package instagram.instagrambe.tag;
//
//import instagram.instagrambe.common.Timestamped;
//import instagram.instagrambe.post.entity.Post;
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.LinkedHashSet;
//import java.util.Set;
//
//@Entity
//@Getter
//@ToString
//public class hashtag {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Setter
//    @Column(nullable = false)
//    private String hashtag;
//
//    @ToString.Exclude
//    @ManyToMany(mappedBy = "hashtags")
//    private Set<Article> articles = new LinkedHashSet<>();
//    @ToString.Exclude
//    @JoinTable(
//            name = "article_hashtag",
//            joinColumns = @JoinColumn(name = "articleId"),
//            inverseJoinColumns = @JoinColumn(name = "hashtagId")
//    )
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private Set<hashtag> hashtags = new LinkedHashSet<>();
//
//
////    @CreationTimestamp // 자동으로 만든시간이 들어감
////    private Timestamp createDate;
//}
//
