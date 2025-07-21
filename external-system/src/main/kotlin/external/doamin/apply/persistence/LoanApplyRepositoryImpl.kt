package external.doamin.apply.persistence

import external.doamin.apply.domain.model.LoanApply
import kotlinx.datetime.LocalDate
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.springframework.stereotype.Repository

@Repository
class LoanApplyRepositoryImpl : LoanApplyRepository {

    override suspend fun createLoanApply(loanApply: LoanApply): LoanApply {
        LoanApplyTable.insert {
            it[amount] = loanApply.amount
            it[rate] = loanApply.rate
            it[period] = loanApply.period
            it[borrowerUserUid] = loanApply.borrowerUserUid
        }.let {
            val applyUid = it[LoanApplyTable.applyUid]
            return loanApply.copy(applyUid = applyUid)
        }
    }

    override suspend fun getApplyByUid(applyUid: Long): LoanApply? {
        return LoanApplyTable.selectAll().where { LoanApplyTable.applyUid eq applyUid }
            .mapNotNull { row ->
                LoanApply(
                    applyUid = row[LoanApplyTable.applyUid],
                    amount = row[LoanApplyTable.amount],
                    rate = row[LoanApplyTable.rate],
                    originateDate = row[LoanApplyTable.originateDate],
                    period = row[LoanApplyTable.period],
                    borrowerUserUid = row[LoanApplyTable.borrowerUserUid]
                )
            }.singleOrNull()
    }

    override suspend fun deleteByUid(applyUid: Long): Boolean {
        return LoanApplyTable.deleteWhere { LoanApplyTable.applyUid eq applyUid } > 0
    }

    override suspend fun updateOriginateDateNow(applyUid: Long, originateDate: LocalDate): Boolean {
        return LoanApplyTable.update({ LoanApplyTable.applyUid eq applyUid }) {
            it[LoanApplyTable.originateDate] = originateDate
        } > 0
    }
}
