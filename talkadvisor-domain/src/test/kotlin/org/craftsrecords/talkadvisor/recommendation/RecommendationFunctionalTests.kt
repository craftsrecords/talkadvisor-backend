package org.craftsrecords.talkadvisor.recommendation

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(strict = true, plugin = ["pretty", "json:target/cucumber/recommendation.json"], features = ["classpath:features/recommending-talks.feature"])
class RecommendationFunctionalTests