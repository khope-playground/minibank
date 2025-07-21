package external.doamin.apply.application

import external.doamin.apply.domain.service.LoanApplyService

class LoanApplyUseCase(
    private val loanApplyService: LoanApplyService
) {

    suspend fun registerLoanApply(
        amount: Long,
        rate: Double,
        period: Int,
        borrowerUserUid: Long
    ) = loanApplyService.registerLoanApply(
        amount = amount,
        rate = rate,
        period = period,
        borrowerUserUid = borrowerUserUid
    )

    suspend fun updateOriginateDateNow(applyUid: Long) = loanApplyService.updateOriginateDateNow(applyUid)
}