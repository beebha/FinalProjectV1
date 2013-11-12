import edu.harvard.cscie56.finalproject.auth.Role
import edu.harvard.cscie56.finalproject.auth.User
import edu.harvard.cscie56.finalproject.auth.UserRole

class BootStrap {

    def init = { servletContext ->
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

        def testAdmin = new User(username: 'admin', password: 'password')
        testAdmin.save(flush: true)

        def testUser = new User(username: 'user', password: 'password')
        testUser.save(flush: true)

        UserRole.create testAdmin, adminRole, true
        UserRole.create testUser, userRole, true

        assert User.count() == 2
        assert Role.count() == 2
        assert UserRole.count() == 2
    }
    def destroy = {
    }
}
