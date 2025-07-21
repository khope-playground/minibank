package external.doamin.apply.presentation

class LoanApplyGrpcEndpoint(
    private val loanApplyUseCase: external.doamin.apply.application.LoanApplyUseCase,
    private val userUseCase: external.doamin.user.application.UserUseCase
) {


}