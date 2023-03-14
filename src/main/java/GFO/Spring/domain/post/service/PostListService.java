package GFO.Spring.domain.post.service;

import GFO.Spring.domain.post.presentation.dto.response.SinglePostListResDto;

import java.util.List;

public interface PostListService {
    List<SinglePostListResDto> execute();
}
