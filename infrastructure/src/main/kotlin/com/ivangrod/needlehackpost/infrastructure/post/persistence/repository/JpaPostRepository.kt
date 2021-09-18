package com.ivangrod.needlehackpost.infrastructure.post.persistence.repository

import com.ivangrod.needlehackpost.infrastructure.post.persistence.model.JpaPost
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface JpaPostRepository : JpaRepository<JpaPost, UUID>
