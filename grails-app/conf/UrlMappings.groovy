class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
        "/admin"(view:"/index")
        "/"(view:"/customLogin/login")
		"500"(view:'/error')
	}
}
