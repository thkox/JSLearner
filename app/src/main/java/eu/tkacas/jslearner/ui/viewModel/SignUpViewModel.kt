package eu.tkacas.jslearner.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tkacas.jslearner.domain.use_case.ValidateEmail
import eu.tkacas.jslearner.domain.use_case.ValidateFirstName
import eu.tkacas.jslearner.domain.use_case.ValidateLastName
import eu.tkacas.jslearner.domain.use_case.ValidatePassword
import eu.tkacas.jslearner.domain.use_case.ValidateTerms
import eu.tkacas.jslearner.ui.events.SignUpFormEvent
import eu.tkacas.jslearner.ui.states.SignUpFormState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val validateFirstName: ValidateFirstName = ValidateFirstName(),
    private val validateLastName: ValidateLastName = ValidateLastName(),
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateTerms: ValidateTerms = ValidateTerms()
): ViewModel() {
    var state by mutableStateOf(SignUpFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: SignUpFormEvent) {
        when(event) {
            is SignUpFormEvent.FirstNameChanged -> {
                state = state.copy(firstName = event.firstName)
            }
            is SignUpFormEvent.LastNameChanged -> {
                state = state.copy(lastName = event.lastName)
            }
            is SignUpFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is SignUpFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is SignUpFormEvent.AcceptTerms -> {
                state = state.copy(acceptedTerms = event.isAccepted)
            }
            is SignUpFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val firstNameResult = validateFirstName.execute(state.firstName)
        val lastNameResult = validateLastName.execute(state.lastName)
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)
        val termsResult = validateTerms.execute(state.acceptedTerms)

        val hasError = listOf(
            firstNameResult,
            lastNameResult,
            emailResult,
            passwordResult,
            termsResult
        ).any { it.errorMessage != null}

        if(hasError) {
            state = state.copy(
                firstNameError = firstNameResult.errorMessage,
                lastNameError = lastNameResult.errorMessage,
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                termsError = termsResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }
}