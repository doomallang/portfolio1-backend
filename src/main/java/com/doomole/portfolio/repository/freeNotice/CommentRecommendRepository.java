package com.doomole.portfolio.repository.freeNotice;

import com.doomole.portfolio.entity.freeNotice.CommentRecommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRecommendRepository extends JpaRepository<CommentRecommend, Long> {

    Optional<CommentRecommend> findByCommentIdxAndAccountIdx(long commentIdx, long accountIdx);

    int countByCommentIdx(long commentIdx);
}
