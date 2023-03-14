package GFO.Spring.domain.post.service.serviceimpl;

import GFO.Spring.domain.post.entity.Post;
import GFO.Spring.domain.post.presentation.dto.response.SinglePostListResDto;
import GFO.Spring.domain.post.repository.PostRepository;
import GFO.Spring.domain.post.service.PostListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostListServiceImpl implements PostListService {
    private final PostRepository postRepository;

    @Override
    public List<SinglePostListResDto> execute() {
        return postRepository.findAll().stream()
            .map(post -> new SinglePostListResDto(
                    post.getPostId(),
                    post.getTitle(),
                    post.getUser().getEmail(),
                    post.getUser().getName()
            )).collect(Collectors.toList());
    }
}
