package com.peshale

import com.peshale.domain.Post
import com.peshale.wall.WallService
import kotlin.random.Random

fun main() {
    val allPosts = WallService(Application.run(10)).posts
    println("Number of posts on the wall is ${allPosts.size}")
    for (i in allPosts.indices) {
        println(allPosts.get(i).toString())
    }
}


class Application{

    companion object {
        fun run(sizeOfArray: Int): Array<Post?> {
            val posts = arrayOfNulls<Post>(sizeOfArray)
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