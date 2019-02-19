package org.craftsrecords.talkadvisor.recommendation.criteria

import org.apache.commons.lang3.Validate.notBlank

class Topic(name:String){
        val name:String = notBlank(name, "Cannot create a topic with a blank name")
}
