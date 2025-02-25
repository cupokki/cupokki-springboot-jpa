package io.cupokki.webmvcboilerplate.repository;

import io.cupokki.webmvcboilerplate.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
