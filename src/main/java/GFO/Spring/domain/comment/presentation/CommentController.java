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

    @PostMapping("/{id}")
    public ResponseEntity<Void> createComment(@PathVariable Long id, @RequestBody @Valid CreateCommentReqDto createCommentReqDto) {
        createCommentService.execute(id, createCommentReqDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> modifyComment(@PathVariable Long id, @RequestBody @Valid ModifyCommentReqDto modifyCommentReqDto) {
        modifyCommentService.execute(id, modifyCommentReqDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        deleteCommentService.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
