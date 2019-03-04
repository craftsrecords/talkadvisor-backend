function fn(){
    var baseUrl = "http://localhost:@local.server.port@"
    var config = {
       talkAdvisorUrl: baseUrl,
       profilesUrl: baseUrl + '/profiles/',
       recommendationsUrl : baseUrl + '/recommendations/'
    };
    return config
}