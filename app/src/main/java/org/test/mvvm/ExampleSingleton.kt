package org.test.mvvm

import org.test.mvvm.models.User

object ExampleSingleton {
    val SingletonUser:User by lazy {
        User("anujwebhost@gmail.com","anuj72","http://google.com/anuj.png")
    }
}