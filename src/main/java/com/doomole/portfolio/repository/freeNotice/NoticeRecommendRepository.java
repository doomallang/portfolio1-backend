package com.doomole.portfolio.repository.freeNotice;

import com.doomole.portfolio.entity.freeNotice.NoticeRecommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticeRecommendRepository extends JpaRepository<NoticeRecommend, Long> {

    int countByFreeNoticeIdx(long freeNoticeIdx);

    Optional<NoticeRecommend> findByFreeNoticeIdxAndAccountIdx(long freeNoticeIdx, long accountIdx);
}
