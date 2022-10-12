package org.khasanof.post_service.repository.post_comment;

import org.khasanof.post_service.dto.post_comment.PostCommentCount;
import org.khasanof.post_service.entity.post_comment.PostCommentEntity;
import org.khasanof.post_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostCommentRepository extends MongoRepository<PostCommentEntity, String>, BaseRepository {

    @Query("db.post_comment.find({ 'post_id' : ?0 })")
    Optional<PostCommentEntity> findByPostIdQuery(String id);
    @Aggregation(pipeline = {"{ '$match': { '_id' : ?0 } }", "{ '$project' : { 'count' : { '$size' : '$comments' } } }"})
    PostCommentCount findByIdCountQuery(String id);

    @Query(value = "db.post_comment.updateOne({ '_id' : ?0 }{ '$push' : { 'comments' : { '_id' : ?1 , 'user_id' : ?2, 'message' : ?3 , 'is_deleted' : false } } })")
    PostCommentEntity updateOneQuery(String postId, String id, String userId, String message);


}

