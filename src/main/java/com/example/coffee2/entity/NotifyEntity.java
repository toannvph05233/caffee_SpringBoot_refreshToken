package com.example.coffee2.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "notify")
public class NotifyEntity {
    @Id
    @SequenceGenerator(name = "notify_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notify_seq")
    private Long id;

    @Column(name = "from_user")
    private String fromUser;

    @Column(name = "to_user")
    private String toUser;

    @Column(name = "is_notify")
    private Long isNotify;

    @Column(name = "name")
    private String name;

    @Column(name = "post_id")
    private String postId;

    @Column(name = "comment_id")
    private String commentId;
}
