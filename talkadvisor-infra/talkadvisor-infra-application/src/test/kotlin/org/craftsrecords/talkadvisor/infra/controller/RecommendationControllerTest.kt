package org.craftsrecords.talkadvisor.infra.controller

import org.craftsrecords.talkadvisor.infra.configurations.DomainConfiguration
import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.profile.Profile
import org.craftsrecords.talkadvisor.recommendation.spi.Profiles
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat.*
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.restdocs.headers.HeaderDocumentation.headerWithName
import org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.operation.preprocess.Preprocessors.*
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.snippet.Attributes.key
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultHandler
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@ExtendWith(SpringExtension::class)
@WebMvcTest(value = [RecommendationController::class])
@AutoConfigureRestDocs
@ActiveProfiles("searchTalksStub")
@Import(DomainConfiguration::class)
internal class RecommendationControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var profiles: Profiles

    @Value("\${spring.test.restdocs.uri-port}")
    private var serverPort: Int = 0

    private val recommendationsUrl = "recommendations"
    private lateinit var expectedRecommendationLocationUrl: String
    private lateinit var recommendationLocationPattern: String

    @BeforeEach
    fun setup() {
        expectedRecommendationLocationUrl = "http://localhost:$serverPort/$recommendationsUrl"
        recommendationLocationPattern = "$expectedRecommendationLocationUrl/[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}"
    }

    @Test
    fun `should recommend some talks`() {
        val userId = "myself"
        lateinit var recommendationId: String

        bootstrapProfileFor(userId)

        this.mvc.perform(
                post("/$recommendationsUrl")
                        .accept(APPLICATION_JSON_UTF8)
                        .contentType(APPLICATION_JSON_UTF8)
                        .header("User-Id", userId))
                .andExpect(status().isCreated)
                .andExpect(header().string("Location", matchesPattern(recommendationLocationPattern)))
                .andDo { recommendationId = it.extractIdFromLocationHeader() }
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", `is`(recommendationId)))
                .andExpect(jsonPath("$.talks[0].id", notNullValue()))
                .andExpect(jsonPath("$.talks[0].title", `is`("QUICKIE DDD")))
                .andExpect(jsonPath("$.talks[0].duration", `is`("PT10M30S")))
                .andExpect(jsonPath("$.talks[0].format", `is`("QUICKIE")))
                .andDo(documentRecommendation())
    }

    private fun documentRecommendation(): ResultHandler {
        return document("recommendations",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(headerWithName("User-Id")
                        .description("The Id of the user who wants some recommendation")),
                responseFields()
                        .and(fieldWithPath("id").description("the id of the recommendation"))
                        .and(fieldWithPath("talks").description("the list of the recommended talks according to the user profile"))
                        .and(fieldWithPath("talks[].id").description("the id of the talk"))
                        .and(fieldWithPath("talks[].title").description("the title of the talk"))
                        .and(fieldWithPath("talks[].duration")
                                .description("the duration of the talk")
                                .attributes(key("constraints").value("formatted in the ISO-8601 standard")))
                        .and(fieldWithPath("talks[].format")
                                .description("the format of the talk")
                                .attributes(key("constraints").value("Possible values: ${values().contentToString()}"))))
    }

    private fun MvcResult.extractIdFromLocationHeader(): String {
        return this.response.getHeader("Location")!!.substringAfter("$expectedRecommendationLocationUrl/")
    }

    private fun bootstrapProfileFor(userId: String) {
        profiles.save(createProfile(userId))
    }

    private fun createProfile(userId: String): Profile {
        return Profile(id = userId,
                preferences = Preferences(setOf(Topic("DDD"), Topic("Hexagonal Architecture")), setOf(CONFERENCE, QUICKIE)))
    }

    @Test
    fun `should notifies that no profile exists for the user when an unknown user ask for a recommendation`() {
        val userId = "noProfileUser"

        this.mvc.perform(
                post("/$recommendationsUrl")
                        .accept(APPLICATION_JSON_UTF8)
                        .contentType(APPLICATION_JSON_UTF8)
                        .header("User-Id", userId))
                .andExpect(status().isBadRequest)
                .andExpect(status().reason("No profile found for the user $userId"))
                .andDo(document("recommendations-without-profile"))
    }
}