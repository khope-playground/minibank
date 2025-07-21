package infra.grpc.client.apply

import application.dto.LoanApplyResponse
import domain.spi.ApplyPort
import external.proto.LoanApplyRequest
import external.proto.LoanApplyServiceGrpcKt

class ApplyGrpcAdapter(
    private val stub: LoanApplyServiceGrpcKt.LoanApplyServiceCoroutineStub
) : ApplyPort {


    override suspend fun registerLoanApply(
        applyUid: Long,
        amount: Long,
        rate: Double,
        period: Int,
        borrowerUserUid: Long
    ): LoanApplyResponse {
        val response = stub.registerLoanApply(
            LoanApplyRequest.newBuilder()
                .setApplyUid(applyUid)
                .setAmount(amount)
                .setRate(rate)
                .setPeriod(period)
                .setBorrowerUserUid(borrowerUserUid)
                .build()
        )

        return LoanApplyResponse(
            loanApplyUid = response.applyUid
        )
    }

    override suspend fun originateLoanApply(applyUid: Int) {
        TODO("Not yet implemented")
    }


}