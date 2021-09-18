package com.ivangrod.needlehackpost.infrastructure.post.persistence.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class JpaPost(@Id val id: UUID, val title: String, val uri: String)

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
