class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
        "/admin"(view:"/index")
        "/"(view:"/customLogin/login")
        "/loginFailure"(view:"/customLogin/loginFailure")
		"500"(view:'/error')
        name forgotPassword: "/forgotPassword"(view:"/forgotPassword/forgotPassword")
	}
}
