package external.doamin.apply.persistence

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date

object LoanApplyTable : Table("lo_apply"){
    val applyUid = long("apply_uid").autoIncrement()
    val amount = long("amount")
    val rate = double("rate")
    val period = integer("period")
    val originateDate = date("originate_date").nullable().default(null)
    val borrowerUserUid = long("borrower_user_uid")

    override val primaryKey = PrimaryKey(applyUid)
}