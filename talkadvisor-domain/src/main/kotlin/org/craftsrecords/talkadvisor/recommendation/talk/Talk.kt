package org.craftsrecords.talkadvisor.recommendation.talk

import org.apache.commons.lang3.Validate
import org.apache.commons.lang3.Validate.notBlank
import org.craftsrecords.talkadvisor.recommendation.duration.isPositive
import java.time.Duration

class Talk private constructor(id: String,
                               title: String,
                               duration: Duration) {

    val id = notBlank(id, "Cannot create a Talk is a blank id")!!
    val title = notBlank(title, "Cannot create a Talk is a blank title")!!
    val duration = notNegative(duration)
    val format = TalkFormat.ofDuration(duration)


    private fun notNegative(duration: Duration): Duration {
        Validate.isTrue(duration.isPositive(), "Talk duration must be strictly positive")
        return duration
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Talk

        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Talk(id='$id', title='$title', duration=$duration, format=$format)"
    }


    companion object {
        inline fun with(content: Builder.() -> Unit) = Builder().apply(content)
    }

    class Builder {
        lateinit var id: String
        lateinit var title: String
        lateinit var duration: Duration
        fun build() = Talk(id, title, duration)
    }
}
