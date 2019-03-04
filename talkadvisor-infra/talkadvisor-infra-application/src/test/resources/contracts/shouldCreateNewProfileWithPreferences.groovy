package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return the new created profile"
    request {
        method 'POST'
        url '/profiles'
        headers {
            header 'Content-Type' : applicationJsonUtf8()
            header 'User-Id' : 'mySelf'
        }
        body """
            {
              "topics": [
                {
                  "name": "DDD"
                },
                {
                  "name": "Hexagonal Architecture"
                }
              ],
              "talksFormats": [
                "CONFERENCE",
                "QUICKIE"
              ]
            }
        """
    }
    response {
        status 201
        headers {
            header 'Content-Type' : applicationJsonUtf8()
        }
        body """
            {
                "id": "mySelf",
                "preferences": {
                  "topics": [
                    {
                      "name": "DDD"
                    },
                    {
                      "name": "Hexagonal Architecture"
                    }
                  ],
                  "talksFormats": [
                    "CONFERENCE",
                    "QUICKIE"
                  ]
                }
            }
        """
    }
}