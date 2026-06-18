package com.nsel.testcompose.presentation.viewModel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginState(
    val uName: String = "",
    val password : String = "",
    val isLoading: Boolean = false,
    val showSnackbar: Boolean = false,
    val snackbarMessage: String = ""
){
    val isUnameError: Boolean get() = uName.length>6
}

class MainActivityViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    fun onUsernameChange(newName: String){
        _uiState.value = _uiState.value.copy(uName = newName)
    }

    fun onPasswordChange(newPassword: String){
        _uiState.value = _uiState.value.copy(password = newPassword)
    }

    fun onLoginClick(){
        val currentState = _uiState.value

        if(currentState.uName.isEmpty() || currentState.password.isEmpty() || currentState.isUnameError){
            _uiState.update {
                it.copy(
                    showSnackbar = true,
                    snackbarMessage = "Ingrese usuario y contraseña válidos"
                )
            }
            return
        }

        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            delay(2000)
            if(currentState.uName == "error"){
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        showSnackbar = true,
                        snackbarMessage = "Credenciales incorrectas"
                    )
                }
            }else{
                _uiState.update{it.copy(isLoading = false)}
            }
        }
    }

    fun onSnackbarDismiss(){
        _uiState.update{it.copy(showSnackbar = false)}
    }

}