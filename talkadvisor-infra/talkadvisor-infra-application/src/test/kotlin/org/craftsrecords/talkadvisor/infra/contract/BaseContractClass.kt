package org.craftsrecords.talkadvisor.infra.contract

import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.craftsrecords.talkadvisor.infra.controller.ProfileController
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMessageVerifier
internal class BaseContractClass {

    @Autowired
    private lateinit var profileController: ProfileController

    @Before
    fun setup() {
        val standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(profileController)
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder)
    }
}
