package com.doomole.portfolio.entity.freeNotice;

import com.doomole.portfolio.entity.account.Account;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "free_notice")
@DynamicUpdate
public class FreeNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "free_notice_idx")
    private long freeNoticeIdx;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @CreatedDate
    @Column(name = "create_datetime", updatable = false)
    private String createDatetime;

    @Column(name = "update_datetime")
    private String updateDatetime;

    @Column(name = "view_count")
    private int viewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_idx")
    private Account account;
}
