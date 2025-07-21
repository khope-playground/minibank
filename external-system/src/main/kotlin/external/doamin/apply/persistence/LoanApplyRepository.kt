package external.doamin.apply.persistence

import external.doamin.apply.domain.model.LoanApply
import kotlinx.datetime.LocalDate

interface LoanApplyRepository {
    suspend fun createLoanApply(loanApply: LoanApply): LoanApply

    suspend fun getApplyByUid(applyUid: Long): LoanApply?

    suspend fun deleteByUid(applyUid: Long): Boolean

    suspend fun updateOriginateDateNow(applyUid: Long, originateDate: LocalDate): Boolean
}