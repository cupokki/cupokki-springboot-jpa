package io.cupokki.jpa.repository;

import io.cupokki.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findByEmail(String email);
}
