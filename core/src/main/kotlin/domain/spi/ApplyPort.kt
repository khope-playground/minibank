package domain.spi

import application.dto.LoanApplyResponse

interface ApplyPort {
    suspend fun registerLoanApply(
        applyUid: Long,
        amount: Long,
        rate: Double,
        period: Int,
        borrowerUserUid: Long
    ): LoanApplyResponse

    suspend fun originateLoanApply(applyUid: Long)
}
