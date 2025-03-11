package io.cupokki.webmvcboilerplate.repository;

import io.cupokki.webmvcboilerplate.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    public Optional<Member> findByEmail(String email);

    public Boolean existsByEmail(String Email);

    public Boolean existsByUsername(String username);
}
