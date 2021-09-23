package com.ivangrod.needlehackpost.infrastructure.post.persistence.model

import com.ivangrod.needlehackpost.domain.post.*
import java.time.LocalDateTime
import java.util.*
import java.util.stream.Collectors
import javax.persistence.Entity
import javax.persistence.Id


@Entity
class JpaPost(
    @Id val id: String? = null,
    val title: String? = null,
    val uri: String? = null,
    val creator: String? = null,
    val source: String? = null
) {

    fun toDomain(): Post? {
        return Post.create(
            PostTitle(title),
            PostUri(uri),
            Author(creator),
            Feed("", ""),
            PostContent.buildWithContentPlain(""),
            PostDate(LocalDateTime.now()),
            emptySet()
        )
    }

    companion object {
        fun fromDomain(post: Post): JpaPost {
            return JpaPost(
                post.id.value,
                post.title.value,
                post.uri.value,
                post.creator.value,
                post.origin.source
            )
        }
    }
}

/*
Any property defined within the class body {}, if present, will not be used in the generated equals(), hashCode(),
and toString() methods
*/

/*
The copy() function only performs a shallow copy of primitives and references. The objects referenced internally are
not deep copied by the method. This isn’t an issue if the entire hierarchy of nested objects is immutable
*/

/*
 Destructuring -> val (id: String, _, uri: String) = post1
 ALERT - Based on the order of properties passed to the primary constructor
 */
