package GFO.Spring.domain.comment.service.impl;

import GFO.Spring.domain.comment.entity.Comment;
import GFO.Spring.domain.comment.exception.CommentNotFoundException;
import GFO.Spring.domain.comment.exception.CommentUserMismatchException;
import GFO.Spring.domain.comment.repository.CommentRepository;
import GFO.Spring.domain.comment.service.DeleteCommentService;
import GFO.Spring.domain.user.entity.User;
import GFO.Spring.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteCommentServiceImpl implements DeleteCommentService {
    private final CommentRepository commentRepository;
    private final UserUtil userUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(Long id) {
        User user = userUtil.currentUser();
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("댓글을 찾을 수 없습니다"));

        if(!comment.getUser().equals(user))
            throw new CommentUserMismatchException("댓글의 작성자가 아닙니다");

        commentRepository.delete(comment);
    }
}
