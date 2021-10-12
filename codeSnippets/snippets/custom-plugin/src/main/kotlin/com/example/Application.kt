package com.example

import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(SimplePlugin)
    install(RequestLoggingPlugin)
    install(ConfigurablePlugin) {
        showGreeting = true
        greeting = "Hello from ConfigurablePlugin!"
    }
    install(DataTransformationPlugin)
    routing {
        get("/index") {
            call.respondText("Index page")
        }

        get("/about") {
            val data = call.receive<String>()
            call.respondText("About page")
        }

        post("/receive") {
            val data = call.receive<String>()
            call.respond(data)
        }
    }
}








