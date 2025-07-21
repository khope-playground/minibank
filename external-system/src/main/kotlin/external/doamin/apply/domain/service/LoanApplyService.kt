package external.doamin.apply.domain.service

import external.doamin.apply.domain.model.LoanApply
import external.doamin.apply.persistence.LoanApplyRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.slf4j.LoggerFactory.*
import org.springframework.stereotype.Service

@Service
class LoanApplyService(
    private val loanApplyRepository: LoanApplyRepository
) {

    private val log = getLogger(LoanApplyService::class.java)

    suspend fun registerLoanApply(
        amount: Long,
        rate: Double,
        period: Int,
        borrowerUserUid: Long
    ): Long {
        log.info("Registering loan apply with amount: $amount, rate: $rate, period: $period, borrowerUserUid: $borrowerUserUid")
        val applyUid = newSuspendedTransaction {
            val loanApply = LoanApply(
                amount = amount,
                rate = rate,
                period = period,
                borrowerUserUid = borrowerUserUid
            )

            val createdLoanApply = loanApplyRepository.createLoanApply(loanApply)
            log.info(
                "Loan apply created with uid: " +
                        "${createdLoanApply.applyUid}, " +
                        "amount: ${createdLoanApply.amount}, " +
                        "rate: ${createdLoanApply.rate}, " +
                        "period: ${createdLoanApply.period}, " +
                        "borrowerUserUid: ${createdLoanApply.borrowerUserUid}"
            )
            createdLoanApply.applyUid
        }

        return applyUid
    }

    suspend fun updateOriginateDateNow(applyUid: Long) {
        val now = Clock.System.todayIn(TimeZone.UTC)
        log.info("Updating originate date to now for applyUid: $applyUid, date: $now")

        val apply = loanApplyRepository.getApplyByUid(applyUid)
            ?: throw IllegalArgumentException("Loan apply not found with uid: $applyUid")

        loanApplyRepository.updateOriginateDateNow(apply.applyUid, now)
    }
}