package external.doamin.apply.domain.model

import kotlinx.datetime.LocalDate

data class LoanApply(
    val applyUid: Long = 0,
    val amount: Long = 0L,
    val rate: Double = 0.0,
    val period: Int = 0,
    val originateDate: LocalDate? = null,
    val borrowerUserUid: Long = 0L
)
