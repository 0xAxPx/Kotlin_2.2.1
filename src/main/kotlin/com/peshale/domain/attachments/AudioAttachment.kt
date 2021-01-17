package com.peshale.domain.attachments

data class AudioAttachment (
    override val type: String,
    val audio: Audio): Attachment {
}