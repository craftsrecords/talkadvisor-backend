package org.craftsrecords.talkadvisor

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(strict = true, plugin = ["pretty", "html:target/cucumber"], features = ["classpath:features/"])
class RunCucumberTest