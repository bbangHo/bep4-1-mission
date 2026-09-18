package com.back.post.out;

import com.back.post.domain.Post;
import com.back.post.domain.PostMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostMemberRepository extends JpaRepository<PostMember, Integer> {

}
