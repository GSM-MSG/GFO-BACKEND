package GFO.Spring.domain.comment.presentation;

import GFO.Spring.domain.comment.presentation.dto.request.CreateCommentReqDto;
import GFO.Spring.domain.comment.presentation.dto.request.ModifyCommentReqDto;
import GFO.Spring.domain.comment.service.CreateCommentService;
import GFO.Spring.domain.comment.service.DeleteCommentService;
import GFO.Spring.domain.comment.service.ModifyCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CreateCommentService createCommentService;
    private final ModifyCommentService modifyCommentService;
    private final DeleteCommentService deleteCommentService;

    @PostMapping("/{postId}")
    public ResponseEntity<Void> createComment(@PathVariable Long postId, @RequestBody @Valid CreateCommentReqDto createCommentReqDto) {
        createCommentService.execute(postId, createCommentReqDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<Void> modifyComment(@PathVariable Long commentId, @RequestBody @Valid ModifyCommentReqDto modifyCommentReqDto) {
        modifyCommentService.execute(commentId, modifyCommentReqDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        deleteCommentService.execute(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
