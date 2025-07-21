package external.doamin.user.persistence

import external.doamin.user.domain.model.User
import org.jetbrains.exposed.sql.*

class UserRepositoryImpl: UserRepository {

    override fun createUser(name: String, email: String, userType: UserType): Long {
        return UserTable.insert {
            it[UserTable.name] = name
            it[UserTable.email] = email
            it[UserTable.userType] = userType
        } get UserTable.userUid
    }

    override fun getUserByUid(userUid: Long): User? {
       return UserTable.selectAll().where { UserTable.userUid eq userUid }
            .singleOrNull()?.let { row ->
               row.toUser(row)
           }
    }

    private fun ResultRow.toUser(row: ResultRow): User {
        return User(
            userUid = row[UserTable.userUid],
            name = row[UserTable.name],
            email = row[UserTable.email],
            userType = row[UserTable.userType]
        )
    }
}