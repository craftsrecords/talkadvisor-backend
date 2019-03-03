package org.craftsrecords.talkadvisor.infra.controller

import org.craftsrecords.talkadvisor.infra.resources.Recommendation
import org.craftsrecords.talkadvisor.infra.resources.toResource
import org.craftsrecords.talkadvisor.recommendation.api.RecommendTalks
import org.craftsrecords.talkadvisor.recommendation.profile.ProfileNotFoundException
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping(path = ["/recommendations"])
class RecommendationController(private val recommendTalks: RecommendTalks) {

    @PostMapping
    fun createRecommendation(@RequestHeader("User-Id") user: String): ResponseEntity<Recommendation> {
        val domainRecommendation = recommendTalks to user
        val recommendation = domainRecommendation.toResource()
        val location = linkTo(this::class.java).slash(recommendation).toUri()

        return ResponseEntity.created(location).body(recommendation)
    }

    @ExceptionHandler(ProfileNotFoundException::class)
    fun handleProfileNotFoundException(response: HttpServletResponse, exception: ProfileNotFoundException) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.message)
    }
}
