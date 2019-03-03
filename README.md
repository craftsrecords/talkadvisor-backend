# TalkAdvisor
[![pipeline status](https://gitlab.com/crafts-records/talkadvisor/talkadvisor-back/badges/master/pipeline.svg)](https://gitlab.com/crafts-records/talkadvisor/talkadvisor-back/commits/master)

TalkAdvisor is a [hexagonal architecture](https://beyondxscratch.com/2017/08/19/decoupling-your-technical-code-from-your-business-logic-with-the-hexagonal-architecture-hexarch) demo application developed with Kotlin and SpringBoot.
This application recommends IT Talks recorded on YouTube given some criteria

## Testing Strategy
![Testing Strategy](testing-strategy.png)

### Unit Test and Test Composition:

For example in the resources, when testing the mapping of a Profile Domain to a Profile Resource,
we don't add a unit test inside resources.PreferencesTest to verify the mapping of a Preferences Resource 
since the Profile, which contains it, will test it by composition 
#### Custom Assertions
[TALK] talk about custom assert and factories

Custom asserts in the adapters: Mapping a domain object to an adapter one can be done in several places
Storing the mapping validation inside a custom assert will ensure no mapping tests will miss a new acceptance criteria.
TODO: GIVE AN EXAMPLE IN THE CODE

##Improvement
use Topics, Talks etc. classes instead of Iterable<Set>, Iterable<Talk>...