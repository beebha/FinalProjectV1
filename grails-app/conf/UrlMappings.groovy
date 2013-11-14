class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
        "/admin"(view:"/index")
        "/"(controller: "login")
        "/loginfail"(controller: "login", action: "loginfail")
		"500"(view:'/error')
	}
}
