package io.cupokki.webmvcboilerplate.repository;

import io.cupokki.webmvcboilerplate.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findByEmail(String email);
}
