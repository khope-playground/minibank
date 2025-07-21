package external.doamin.apply.presentation

import com.google.protobuf.Empty
import external.doamin.apply.application.LoanApplyUseCase
import external.doamin.user.application.UserUseCase
import external.proto.LoanApplyRequest
import external.proto.LoanApplyResponse
import external.proto.LoanApplyServiceGrpcKt
import external.proto.OriginateApplyRequest
import net.devh.boot.grpc.server.service.GrpcService
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

@GrpcService
class LoanApplyGrpcEndpoint(
    private val loanApplyUseCase: LoanApplyUseCase,
    private val userUseCase: UserUseCase
) : LoanApplyServiceGrpcKt.LoanApplyServiceCoroutineImplBase() {

    override suspend fun registerLoanApply(request: LoanApplyRequest): LoanApplyResponse {
        val applyUid = newSuspendedTransaction {
            loanApplyUseCase.registerLoanApply(
                amount = request.amount,
                rate = request.rate,
                period = request.period,
                borrowerUserUid = request.borrowerUserUid
            )
        }

        return LoanApplyResponse.newBuilder()
            .setApplyUid(applyUid)
            .build()
    }

    override suspend fun originateLoanApply(request: OriginateApplyRequest): Empty {
        newSuspendedTransaction {
            loanApplyUseCase.updateOriginateDateNow(request.applyUid)
        }

        return Empty.getDefaultInstance()
    }
}
