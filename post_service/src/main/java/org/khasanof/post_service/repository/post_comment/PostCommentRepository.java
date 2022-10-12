package org.khasanof.post_service.repository.post_comment;

import org.khasanof.post_service.dto.post_comment.PostCommentCount;
import org.khasanof.post_service.entity.post_comment.PostCommentEntity;
import org.khasanof.post_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostCommentRepository extends MongoRepository<PostCommentEntity, String>, BaseRepository {

    @Query("db.post_comment.find({ 'post_id' : ?0 })")
    Optional<PostCommentEntity> findByPostIdQuery(String id);

    @Query(value = "db.post_comment.find({ '_id' : ?0 }{ attachments : { $elemMatch: { _id: ?1 } } })")
    PostCommentEntity findByPostIdQuery(String postId, String commentId);

    @Query(value = """
            db.post_comment.aggregate([{$match: {_id: ObjectId(633f02bf5a10942796f87007)}}, {$project: {count: {$size: $comments}}}])
            """)
    PostCommentCount findByIdCountQuery(String id);

    @Query(value = """
            db.post_comment.updateOne(
                { _id: ObjectId(":#{#postId})"},
                {
                    $push: {
                        comments: {
                            "_id": ":#{#id}",
                            "user_id": ":#{#userId}",
                            "message": ":#{#message}",
                            "is_deleted": false
                        }
                    }
                })""")
    String updateOneQuery(String postId, String id, String userId, String message);


}

