package com.doomole.portfolio.entity.freeNotice;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notice_recommend")
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class NoticeRecommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommend_idx")
    private long recommendIdx;

    @Column(name = "free_notice_idx")
    private long freeNoticeIdx;

    @Column(name = "account_idx")
    private long accountIdx;
}
