package com.ivangrod.needlehackpost.infrastructure.post.persistence.model

import com.ivangrod.needlehackpost.domain.post.Post
import com.ivangrod.needlehackpost.domain.post.Posts
import com.ivangrod.needlehackpost.infrastructure.post.persistence.repository.JpaPostRepository

class JpaPosts(private val jpaRepository: JpaPostRepository) : Posts {

    override fun save(post: Post) {
        jpaRepository.save(JpaPost.fromDomain(post))
    }
}
