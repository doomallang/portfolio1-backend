package com.doomole.portfolio.repository.freeNotice;

import com.doomole.portfolio.entity.freeNotice.FreeNoticeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FreeNoticeCommentRepository extends JpaRepository<FreeNoticeComment, Long> {

    Optional<List<FreeNoticeComment>> findByFreeNoticeIdx(long freeNoticeIdx);

    Optional<FreeNoticeComment> findByCommentIdx(long commentIdx);

}
