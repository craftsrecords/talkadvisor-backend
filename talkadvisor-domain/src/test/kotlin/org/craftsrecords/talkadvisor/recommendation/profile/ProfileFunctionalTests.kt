package org.craftsrecords.talkadvisor.recommendation.profile

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
        strict = true,
        plugin = ["pretty", "json:target/cucumber/profile.json"],
        features = ["classpath:features/creating-a-profile.feature"],
        glue = ["classpath:org.craftsrecords.talkadvisor.recommendation.stepdefs"])
class ProfileFunctionalTests