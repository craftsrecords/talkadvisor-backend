package org.craftsrecords.talkadvisor.infra.resources

import java.time.Duration
import org.craftsrecords.talkadvisor.recommendation.talk.Talk as DomainTalk

class Talk(val id: String, val title: String, val duration: Duration, val format: String)

fun DomainTalk.toResource() = Talk(id = id, title = title, duration = duration, format = format.name)