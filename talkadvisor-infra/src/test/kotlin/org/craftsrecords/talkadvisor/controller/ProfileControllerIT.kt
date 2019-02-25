package org.craftsrecords.talkadvisor.controller

import org.craftsrecords.talkadvisor.configurations.DomainConfiguration
import org.craftsrecords.talkadvisor.recommendation.criteria.createPreferences
import org.craftsrecords.talkadvisor.recommendation.profile.Profile
import org.craftsrecords.talkadvisor.recommendation.spi.Profiles
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.core.io.Resource
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@ExtendWith(SpringExtension::class)
@WebMvcTest(value = [ProfileController::class])
@Import(DomainConfiguration::class)
internal class ProfileControllerIT {

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var profiles: Profiles

    @Value("classpath:payloads/create-profile-request.json")
    private lateinit var createProfileRequest: Resource

    private val profilesUrl = "profiles"

    @Test
    fun `should create a profile`() {
        val requestContent = createProfileRequest.file.readBytes()

        val userId = "myself"
        this.mvc.perform(
                post("/$profilesUrl")
                        .accept(APPLICATION_JSON_UTF8)
                        .contentType(APPLICATION_JSON_UTF8)
                        .header("User-Id", userId)
                        .content(requestContent))
                .andExpect(status().isCreated)
                .andExpect(header().string("Location", "http://localhost/$profilesUrl/$userId"))
    }

    @Test
    fun `should notifies that the profile already exists`() {
        val requestContent = createProfileRequest.file.readBytes()
        val userId = "profileExist"

        profiles.save(Profile(userId, createPreferences()))

        this.mvc.perform(
                post("/$profilesUrl")
                        .accept(APPLICATION_JSON_UTF8)
                        .contentType(APPLICATION_JSON_UTF8)
                        .header("User-Id", userId)
                        .content(requestContent))
                .andExpect(status().isConflict)
                .andExpect(status().reason("A profile already exists for the user $userId"))
    }
}