package org.craftsrecords.talkadvisor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TalkadvisorApplication

fun main(args: Array<String>) {
	runApplication<TalkadvisorApplication>(*args)
}

