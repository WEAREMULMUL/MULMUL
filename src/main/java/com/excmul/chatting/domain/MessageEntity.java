package com.excmul.chatting.domain;

import com.excmul.common.domain.DateEntity;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGE")
public class MessageEntity extends DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MESSAGE_ID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHATTING_ROOM_ID")
    private ChattingRoomEntity chattingRoomEntity;

    @Column(name = "MESSAGE_CONTENT")
    private String content;
}
