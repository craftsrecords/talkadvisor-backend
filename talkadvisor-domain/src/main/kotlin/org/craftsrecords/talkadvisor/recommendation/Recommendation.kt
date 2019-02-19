package org.craftsrecords.talkadvisor.recommendation

import org.craftsrecords.talkadvisor.recommendation.talk.Talk
import java.util.*

class Recommendation(val id: UUID = UUID.randomUUID(), val talks: Set<Talk>)