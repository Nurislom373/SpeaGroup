package org.khasanof.post_service.repository.post_comment;

import org.khasanof.post_service.entity.post_comment.PostCommentEntity;
import org.khasanof.post_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentRepository extends MongoRepository<PostCommentEntity, String>, BaseRepository {
}

