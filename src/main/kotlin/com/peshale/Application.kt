package com.peshale

import com.peshale.domain.Post
import com.peshale.wall.Wall
import kotlin.random.Random

fun main() {
    println("Number of posts on the wall is ${Wall(Application.run(10)).posts.size}")
}


class Application{

    companion object {
        fun run(sizeOfArray: Int): Array<Post?> {
            var posts = arrayOfNulls<Post>(sizeOfArray)
            println("Post application is running....")
            for (i in posts.indices) {
                val id = Random.nextInt(i,  100_000)
                var post = Post.prepareData(id)
                posts[i] = post
            }
            println("Prepared array of ${posts.size}")
            return posts
        }
    }
}