package com.peshale.domain

import com.peshale.domain.attachments.Attachment

data class Comment(
    val commentId: Long,
    val postId: Int,
    val fromId: Long,
    val date: Long,
    val text: String,
    val donut: Donut?,
    val replyToUser: Long,
    val replyToComment: Long,
    val attachment: Attachment?,
    val parentStack: Array<Comment>,
    val thread: Any
) {

}
