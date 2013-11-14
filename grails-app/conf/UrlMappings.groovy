class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
        "/admin"(view:"/index")
        "/"(view:"/login/login")
        "/loginfail"(controller: "login", action: "loginfail")
		"500"(view:'/error')
        name forgotPassword: "/forgotPassword"(view:"/forgotPassword/forgotPassword")
	}
}
