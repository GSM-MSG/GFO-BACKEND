package GFO.Spring.domain.comment.exception;

import GFO.Spring.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class CommentUserMismatchException extends RuntimeException {
    private final ErrorCode errorCode;

    public CommentUserMismatchException(String message) {
        super(message);
        this.errorCode = ErrorCode.COMMENT_USER_MISMATCH;
    }
}
