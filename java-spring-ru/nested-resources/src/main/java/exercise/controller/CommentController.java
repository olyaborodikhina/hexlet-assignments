package exercise.controller;

import exercise.ResourceNotFoundException;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // BEGIN
    @GetMapping(path = "{postId}/comments")
    public Iterable<Comment> getCommentByPostId(@PathVariable long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @GetMapping(path = "{postId}/comments/{commentId}")
    public Comment getCommentByIdAndPostId(@PathVariable long postId, @PathVariable long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
    }

    @PostMapping(path = "{postId}/comments")
    public Iterable<Comment> createComment(@PathVariable long postId, @RequestBody Comment comment) {
        comment.setPost(postRepository.findById(postId).get());
        commentRepository.save(comment);
        return commentRepository.findAllByPostId(postId);
    }

    @PatchMapping(path = "{postId}/comments/{commentId}")
    public void editComment(@PathVariable long postId, @PathVariable long commentId, @RequestBody Comment newComment) {
        commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        Comment comment = commentRepository.findByIdAndPostId(commentId, postId).get();
        comment.setContent(newComment.getContent());
        commentRepository.save(comment);

    }

    @DeleteMapping(path = "{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable long postId, @PathVariable long commentId) {
        commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        commentRepository.delete(commentRepository.findByIdAndPostId(commentId, postId).get());
    }

    // END
}
