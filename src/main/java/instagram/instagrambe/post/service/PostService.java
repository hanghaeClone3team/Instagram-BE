package instagram.instagrambe.post.service;

//import instagram.instagrambe.comment.dto.CommentReplyResponseDto;

import instagram.instagrambe.comment.dto.CommentResponseDto;
import instagram.instagrambe.comment.entity.Comment;
import instagram.instagrambe.comment.entity.CommentReply;
import instagram.instagrambe.comment.repository.CommentReplyRepository;
import instagram.instagrambe.comment.repository.CommentRepository;
import instagram.instagrambe.post.dto.PostRequestDto;
import instagram.instagrambe.post.dto.PostResponseDto;
import instagram.instagrambe.post.entity.Post;
import instagram.instagrambe.post.image.common.S3Uploader;
import instagram.instagrambe.post.repository.PostLikeRepository;
import instagram.instagrambe.post.repository.PostRepository;
import instagram.instagrambe.user.entity.User;
import instagram.instagrambe.util.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static instagram.instagrambe.util.ErrorCode.FORBIDDEN_DATA;
import static instagram.instagrambe.util.ErrorCode.NOT_FOUND_DATA;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final S3Uploader s3Uploader;
    private final PostLikeRepository postLikeRepository;
    private final CommentRepository commentRepository;
    private final CommentReplyRepository commentReplyRepository;



    // 게시글 작성
    @Transactional
    public ResponseEntity<PostResponseDto> createPost(MultipartFile image,
                                                      PostRequestDto postRequestDto,
                                                      User user) throws IOException {
//        System.out.println("postRepository.contents = " + postRepository.getContent());
        System.out.println("-------user = " + user.getUsername());
        String storedFileName = s3Uploader.upload(image);//, "images"); //s3에 업로드하기

        postRequestDto.setImageUrl(storedFileName);

        Post post = postRepository.saveAndFlush(new Post(postRequestDto, user));
        return ResponseEntity.ok().body(PostResponseDto.of(post));
    }

    //게시글 전체 조회
    @Transactional
    public ResponseEntity<List<PostResponseDto>> getPosts(User user) {
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostResponseDto> postResponseDtoList =new ArrayList<>();

        for (Post post : postList) {
            boolean heart = isHeart(user, post);
            List<CommentResponseDto> commentResponseDtoList = getComment(post);
            postResponseDtoList.add(new PostResponseDto(post, commentResponseDtoList, heart));
        }
        return ResponseEntity.ok().body(postResponseDtoList);
    }
//    @Transactional(readOnly = true)
//    public ResponseEntity<List<PostResponseDto>> getPosts() {
//        // 1) 객체선언하기!
//        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
//        List<PostResponseDto> postResponseList = new ArrayList<>();
//        // 2) 각각의 게시글 마다 댓글들을 전부 가져오기
//        for (Post post : postList) {
//            List<CommentResponseDto> commentList = new ArrayList<>();
//            for (Comment comment : post.getComments()) {
//                commentList.add(CommentResponseDto.from(comment, commentLikeRespositoy.countCommentLikesByCommentId(comment.getId())));
//            }
//            postResponseList.add(PostResponseDto.from(post, commentList, postLikeRepository.countByPostIdAndUserId(post.getId()));
//        }
//        // 3) 요청받은 DTO를 DB에 저장할 객체 만들기
//        return ResponseEntity.ok().body(postResponseList);
//    }

    // 게시글 상세 조회 (선택한 게시글 조회)
    @Transactional
    public ResponseEntity<PostResponseDto> getPost(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CustomException(NOT_FOUND_DATA));
        List<CommentResponseDto> commentResponseDtoList = getComment(post);
        boolean heart = isHeart(user, post);
        PostResponseDto postResponseDto = new PostResponseDto(post, commentResponseDtoList, heart);
        return ResponseEntity.ok().body(postResponseDto);
    }

    // 게시글 수정 (선택한 게시글 수정)
    @Transactional
    public ResponseEntity<PostResponseDto> updateBlog(Long postId, PostRequestDto postRequestDto, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CustomException(NOT_FOUND_DATA));
//        if(user.getRole() == USER) { //관리자 없다고 가정.
        if (user.getUsername().equals(post.getUser().getUsername()))
            post.update(postRequestDto);
        else throw new CustomException(FORBIDDEN_DATA);
        return ResponseEntity.ok().body(PostResponseDto.of(post));
    }

    // 게시글 삭제 (선택한 게시글 삭제)
    @Transactional
    public ResponseEntity deleteBlog(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CustomException(NOT_FOUND_DATA));
//        if(user.getRole()==USER) { //관리자 없다고 가정.
        if (user.getUsername().equals(post.getUser().getUsername()))
            postRepository.deleteById(postId);
        else throw new CustomException(FORBIDDEN_DATA);
        return ResponseEntity.ok().body("게시글 삭제 성공");
    }

    // 게시글에 있는 댓글 가져오기
//    private List<CommentResponseDto> getComment(Post post) {
//        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
//        List<Comment> commentList = commentRepository.findAllByPostOrderByCreatedAtDesc(post);
//        for (Comment c : commentList) {
//            commentResponseDtoList.add(new CommentResponseDto(c));
//        }
//        return commentResponseDtoList;
//    }
    private List<CommentResponseDto> getComment(Post post) {
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        List<Comment> commentList = commentRepository.findAllByPostOrderByCreatedAtDesc(post);
        for (Comment c : commentList) {
//            log.info("comment = {}", c.getComment());
            CommentResponseDto commentDto = new CommentResponseDto(c);
            List<CommentReply> commentReplyList = commentReplyRepository.findAllByCommentOrderByCreatedAtDesc(c);
            for (CommentReply commentReply : commentReplyList) {
//                log.info("commentReply = {}", commentReply.getCommentReply());
                commentDto.addCommentReplies(commentReply);
            }
            commentResponseDtoList.add(commentDto);
//            commentResponseDtoList.add(new CommentResponseDto(c));
//            for (CommentReply commentReply : commentReplyList) {
//                commentResponseDtoList.add(new CommentResponseDto());
//            }
        }
        return commentResponseDtoList;
    }


    // 게시글 하트 가져오기
    private boolean isHeart(User user, Post post) {
        boolean heart = false;
        Long likeCheck = postLikeRepository.countByPostId_AndUser_UserId(post.getId(), user.getUserId());
        if (likeCheck != 0)
            heart = true;
        return heart;
    }

    // 게시글 좋아요 누르기
    public void likePost(Post post, boolean heart) {
        Long likeId = post.getPostlikes();
        if (heart) post.Likes(likeId + 1);
        else post.Likes(likeId - 1);
    }
}


