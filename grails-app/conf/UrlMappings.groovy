class UrlMappings {
    static mappings = {
      "/changePassword/$id"(controller:"shiroUser", action:"changePassword")
      "/submitPassword/$id"(controller:"shiroUser", action:"submitPassword")

      "/$controller/$action?/$id?"{
	      constraints {
			 // apply constraints here
		  }
	  }
      "/"(controller: "dashboard", action:"index")
	  "500"(view:'/error')
	}
}
