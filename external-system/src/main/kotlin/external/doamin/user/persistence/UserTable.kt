package external.doamin.user.persistence

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table


object UserTable : Table("u_user") {
    val userUid: Column<Long> =  long("user_uid").autoIncrement()
    val name: Column<String> = varchar("name", 50)
    val email: Column<String> = varchar("email", 100).uniqueIndex()
    val userType: Column<UserType> = customEnumeration(
        name = "user_type",
        fromDb = { value -> UserType.fromCode((value as Int)) },
        toDb = { it.code }
    )
}

enum class UserType(
    val code: Int
) {
    ADMIN(1), INVESTOR(2), BORROWER(3);

    companion object {
        fun fromCode(code: Int): UserType {
            return values().find { it.code == code } ?:
                throw IllegalArgumentException("Invalid UserType code: $code")
        }
    }
}