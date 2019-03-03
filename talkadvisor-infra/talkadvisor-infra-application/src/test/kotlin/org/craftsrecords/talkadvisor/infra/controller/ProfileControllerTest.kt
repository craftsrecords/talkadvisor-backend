package org.craftsrecords.talkadvisor.infra.controller

import org.craftsrecords.talkadvisor.infra.configurations.DomainConfiguration
import org.craftsrecords.talkadvisor.recommendation.criteria.createPreferences
import org.craftsrecords.talkadvisor.recommendation.profile.Profile
import org.craftsrecords.talkadvisor.recommendation.spi.Profiles
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.containsInAnyOrder
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.core.io.Resource
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.restdocs.headers.HeaderDocumentation.headerWithName
import org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler
import org.springframework.restdocs.operation.preprocess.Preprocessors.*
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.snippet.Attributes.key
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@ExtendWith(SpringExtension::class)
@WebMvcTest(value = [ProfileController::class])
@AutoConfigureRestDocs
@ActiveProfiles("searchTalksStub")
@Import(DomainConfiguration::class)
internal class ProfileControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var profiles: Profiles

    @Value("classpath:payloads/create-profile-request.json")
    private lateinit var createProfileRequest: Resource

    @Value("\${spring.test.restdocs.uri-port}")
    private var serverPort: Int = 0

    private val profilesUrl = "profiles"

    @Test
    fun `should create a profile`() {
        val requestContent = readProfileCreationRequest()
        val userId = "myself"
        this.mvc.perform(
                post("/$profilesUrl")
                        .accept(APPLICATION_JSON_UTF8)
                        .contentType(APPLICATION_JSON_UTF8)
                        .header("User-Id", userId)
                        .content(requestContent))
                .andExpect(status().isCreated)
                .andExpect(header().string("Location", "http://localhost:$serverPort/$profilesUrl/$userId"))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", `is`(userId)))
                .andExpect(jsonPath("$.preferences.topics[0].name", `is`("DDD")))
                .andExpect(jsonPath("$.preferences.topics[1].name", `is`("Hexagonal Architecture")))
                .andExpect(jsonPath("$.preferences.talksFormats[*]", containsInAnyOrder("CONFERENCE", "QUICKIE")))
                .andDo(documentCreateProfile())

    }

    private fun documentCreateProfile(): RestDocumentationResultHandler {
        return document("create-profile",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),

                requestHeaders(headerWithName("User-Id")
                        .description("The Id of the user who wants to create his profile")
                ),
                requestFields(criteriaDescriptor()),
                responseFields()
                        .and(fieldWithPath("id").description("the id of the profile"))
                        .and(fieldWithPath("preferences").description("the stored preferences of the user"))
                        .andWithPrefix("preferences.", criteriaDescriptor()))
    }

    private fun criteriaDescriptor(): List<FieldDescriptor> {
        return listOf(
                fieldWithPath("topics[]")
                        .description("list of the topics the user wants some recommendations for"),
                fieldWithPath("topics[].name")
                        .description("the name of the topic"),
                fieldWithPath("talksFormats[]")
                        .description("list of the talks formats the user only wants to watch")
                        .attributes(key("constraints").value("Possible values: ${TalkFormat.values().contentToString()}")))
    }

    @Test
    fun `should notifies that the profile already exists`() {
        val requestContent = readProfileCreationRequest()
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
                .andDo(document("profile-already-exists"))
    }

    private fun readProfileCreationRequest() = createProfileRequest.file.readBytes()

}