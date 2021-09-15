package server.repository;

import org.springframework.stereotype.Repository;
import server.exception.NotFoundException;
import server.model.Post;
import server.model.PostContainer;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

// Stub
@Repository
public class PostRepository {

    private final AtomicLong idCounter;
    private final Map<Long, PostContainer> repository;

    public PostRepository() {
        repository = new ConcurrentHashMap<>();
        idCounter = new AtomicLong(0);
    }

    public List<Post> all() {
        return repository.values().stream()
                         .filter(o -> !o.isRemoved())
                         .map(o -> o.getPost())
                         .collect(Collectors.toList());
    }

    public Optional<Post> getById(long id) {

        return repository.containsKey(id) && !repository.get(id).isRemoved() ? Optional.of(repository.get(id).getPost())
                                                                             : Optional.empty();
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            var id = idCounter.incrementAndGet();
            post.setId(id);
            repository.put(id, new PostContainer(post));
        } else {
            if (repository.containsKey(post.getId()) && repository.get(post.getId()).isRemoved()) {
                repository.get(post.getId()).setPost(post);
                repository.get(post.getId()).setNotRemoved();
            } else {
                throw new NotFoundException();
            }
        }
        return post;
    }

    public void removeById(long id) {
        if (repository.containsKey(id) && !repository.get(id).isRemoved()) {
            repository.get(id).setRemoved();
        } else {
            throw new NotFoundException();
        }
    }
}
