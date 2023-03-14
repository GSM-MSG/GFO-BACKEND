package GFO.Spring.domain.post.presentation.dto.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostListResDto {
    private Long postId;
    private String title;
    private String email;
    private String name;
}
