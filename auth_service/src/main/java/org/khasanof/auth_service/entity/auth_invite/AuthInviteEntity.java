package org.khasanof.auth_service.entity.auth_invite;

import lombok.*;
import org.khasanof.auth_service.entity.Auditable;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.entity.invite.InviteEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.LinkedList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "auth_invite")
public class AuthInviteEntity extends Auditable {
    @DocumentReference
    @Field(name = "post_id")
    private AuthUserEntity userId;
    private LinkedList<InviteEntity> invites;
}
