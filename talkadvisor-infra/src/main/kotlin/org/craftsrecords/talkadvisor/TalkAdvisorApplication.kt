package org.craftsrecords.talkadvisor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TalkAdvisorApplication

fun main(args: Array<String>) {
    runApplication<TalkAdvisorApplication>(*args)
}

