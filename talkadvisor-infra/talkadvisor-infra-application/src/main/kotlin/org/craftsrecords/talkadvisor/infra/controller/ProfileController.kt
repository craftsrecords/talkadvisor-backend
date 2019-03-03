package org.craftsrecords.talkadvisor.infra.controller

import org.craftsrecords.talkadvisor.infra.resources.Preferences
import org.craftsrecords.talkadvisor.infra.resources.Profile
import org.craftsrecords.talkadvisor.infra.resources.toResource
import org.craftsrecords.talkadvisor.recommendation.api.CreateProfile
import org.craftsrecords.talkadvisor.recommendation.profile.ProfileAlreadyExistsException
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletResponse.SC_CONFLICT

@RestController
@RequestMapping(path = ["/profiles"])
class ProfileController(private val createProfile: CreateProfile) {

    /**
     * simulating an authentication context by passing the userId in a header,
     *  no need to set up all the security frameworks for this demo, but never do that in production !!!
     */
    @PostMapping
    fun createProfile(@RequestHeader("User-Id") userId: String, @RequestBody preferences: Preferences): ResponseEntity<Profile> {
        val profile = createProfile.forUserWithPreferences(userId, preferences.toDomainObject()).toResource()
        val location = linkTo(this::class.java).slash(profile).toUri()
        return ResponseEntity.created(location).body(profile)
    }

    @ExceptionHandler(ProfileAlreadyExistsException::class)
    fun handleProfileAlreadyExistsException(response: HttpServletResponse, exception: ProfileAlreadyExistsException) {
        response.sendError(SC_CONFLICT, exception.message)
    }
}

