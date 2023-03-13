package GFO.Spring.domain.comment.service.impl;

import GFO.Spring.domain.comment.entity.Comment;
import GFO.Spring.domain.comment.exception.CommentNotFoundException;
import GFO.Spring.domain.comment.exception.CommentUserMismatchException;
import GFO.Spring.domain.comment.presentation.dto.request.ModifyCommentReqDto;
import GFO.Spring.domain.comment.repository.CommentRepository;
import GFO.Spring.domain.comment.service.ModifyCommentService;
import GFO.Spring.domain.user.entity.User;
import GFO.Spring.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifyCommentServiceImpl implements ModifyCommentService {
    private final CommentRepository commentRepository;
    private final UserUtil userUtil;

    @Override
    public void execute(Long id, ModifyCommentReqDto modifyCommentReqDto) {
        User user = userUtil.currentUser();
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("댓글을 찾을 수 없습니다"));

        if(!user.equals(comment.getUser()))
            throw new CommentUserMismatchException("댓글의 작성자가 아닙니다");

        commentRepository.save(comment.update(id, modifyCommentReqDto.getContent()));
    }
}
