package com.icebergsocialnetwork.repository.like;

import com.icebergsocialnetwork.model.like.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    @Query(nativeQuery = true,value ="select * from friend_request where (user_receiver_id=:id1 and user_sender_id=:id2 ) or(user_receiver_id=:id2 and user_sender_id=:id1 )")
    FriendRequest findAllByUserSender (Long id1,Long id2);




}