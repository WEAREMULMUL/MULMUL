package com.excmul.chatting.domain;

import com.excmul.common.domain.DateEntity;
import com.excmul.user.domain.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CHATTING_ROOM")
public class ChattingRoomEntity extends DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHATTING_ROOM_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "CHATTING_ROOM_SENDER_ID")
    private UserEntity sender;

    @OneToOne
    @JoinColumn(name = "CHATTING_ROOM_RECIPIENT_ID")
    private UserEntity recipient;

    @OneToMany(mappedBy = "chattingRoomEntity")
    private List<MessageEntity> messages = new ArrayList<>();
}
