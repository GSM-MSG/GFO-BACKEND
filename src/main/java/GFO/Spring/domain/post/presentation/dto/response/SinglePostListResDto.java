package GFO.Spring.domain.post.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SinglePostListResDto {
    private String title;
    private String email;
    private String name;
}
