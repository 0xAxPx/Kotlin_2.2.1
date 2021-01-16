package com.peshale.wall

import com.peshale.PostNotFoundException
import com.peshale.domain.Comment
import com.peshale.domain.Post
import kotlin.random.Random

class WallService() {

    companion object {
        var ids = ArrayList<Int>()
        var posts = ArrayList<Post>()
        val comments = ArrayList<Comment>()

        fun getNumberOfPosts(): Int {
            return posts.size
        }

        fun getPost(id: Int): Post {
            var index = 0
            for (i in posts.indices) if (posts[i].postId == id) {
                index = i
            }
            return posts[index]
        }
    }

    fun add(post: Post): Post {
        val newPost = setUniquePostId(post)
        posts.add(newPost)
        ids.add(newPost.postId)
        return newPost
    }

    fun update(post: Post): Boolean {
        var update = false
        for (i in posts.indices) {
            val currentId = post.postId
            val currentDateCreated = post.date
            if (posts[i].postId == post.postId) {
                update = true
                val newPost = post.copy(postId = currentId, date = currentDateCreated)
                posts[i] = newPost
            } else {
                update = false
            }
        }
        return update
    }

    fun createComment(comment: Comment) {
        for (p in posts) {
            if (p.postId == comment.postId) {
                comments.add(comment)
            } else {
                throw PostNotFoundException("Post ${comment.postId} does not exist")
            }
        }
    }

    private fun setUniquePostId(post: Post): Post {
        //check if id exists in array of id
        val currentId = post.postId
        for (i in ids.indices) {
            if (ids[i] == currentId) {
                post.postId = Random.nextInt()
            }
        }
        return post
    }

    fun getLastComment(commentId: Long): Comment {
        return comments.last()
    }
}