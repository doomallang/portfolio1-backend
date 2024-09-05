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
@Table(name = "comment_recommend")
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class CommentRecommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommend_idx")
    private long recommendIdx;

    @Column(name = "comment_idx")
    private long commentIdx;

    @Column(name = "account_idx")
    private long accountIdx;
}
