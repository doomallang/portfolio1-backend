package com.doomole.portfolio.repository.freeNotice;

import com.doomole.portfolio.entity.freeNotice.FreeNotice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreeNoticeRepository extends JpaRepository<FreeNotice, Long> {
    List<FreeNotice> findAllByTitleContains(String searchText, Pageable pageable);

    Long countAllByTitleContains(String searchText);
}
