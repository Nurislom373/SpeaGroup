package org.khasanof.post_service.repository.post_comment;

import org.khasanof.post_service.entity.post_comment.PostCommentEntity;
import org.khasanof.post_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface PostCommentRepository extends MongoRepository<PostCommentEntity, String>, BaseRepository {

    @Query("db.post_comment.find({ 'post_id' : ?0 })")
    PostCommentEntity findByQuery(String id);

    @Query(value = "db.post_comment.find({ '_id' : ?0 }{ attachments : { $elemMatch: { _id: ?1 } } })")
    LinkedList<PostCommentEntity> findByQuery(String postId, String commentId);


}

