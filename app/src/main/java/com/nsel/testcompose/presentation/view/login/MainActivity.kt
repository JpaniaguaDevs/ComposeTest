package com.nsel.testcompose.presentation.view.login

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nsel.testcompose.R
import com.nsel.testcompose.presentation.viewModel.login.MainActivityViewModel
import com.nsel.testcompose.ui.theme.TestComposeTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.testTag
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nsel.testcompose.presentation.navigation.AppRoute
import com.nsel.testcompose.presentation.view.main.MainHostScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainActivityViewModel = viewModel()
            val globalNavController = rememberNavController()

            TestComposeTheme {
                NavHost(
                    navController = globalNavController,
                    startDestination = AppRoute.Login.route
                ){
                    composable(AppRoute.Login.route){
                        LoginScreen(
                            viewModel = viewModel,
                            onLoginSuccess = {
                                globalNavController.navigate(AppRoute.MainContent.route){
                                    popUpTo(AppRoute.Login.route){
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }

                    composable(AppRoute.MainContent.route){
                        MainHostScreen()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun LoginScreen(viewModel: MainActivityViewModel = viewModel(), onLoginSuccess: () -> Unit = {}) {
    val state by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.isLoginSuccess) {
        if(state.isLoginSuccess){
            onLoginSuccess()
        }
    }

    LaunchedEffect(state.showSnackbar){
        if(state.showSnackbar){
            snackbarHostState.showSnackbar(message = state.snackbarMessage)
            viewModel.onSnackbarDismiss()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier =  Modifier.padding(bottom = 16.dp),
                snackbar = { snackbarData ->
                    Snackbar(
                        snackbarData = snackbarData,
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.onErrorContainer,
                        shape = RoundedCornerShape(12.dp),
                        actionColor =  MaterialTheme.colorScheme.primary
                    )
                }
            )
        }
    ) { innerPadding ->

        val scrollState = rememberScrollState()

        Box(
            modifier = Modifier.fillMaxSize()
        ){

            Image(
                painter = painterResource(id = R.drawable.fondo_ala),
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(innerPadding)
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth()
                    .alpha(0.8f)
                    .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(16.dp))
                    .padding(vertical = 30.dp)
                    .imePadding()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id= R.drawable.logo_isa),
                    contentDescription = "Logo",
                    modifier = Modifier.fillMaxWidth()
                        .height(100.dp)
                        .padding(bottom = 20.dp)
                )

                TextField(
                    value = state.uName,
                    onValueChange = { viewModel.onUsernameChange(it) },
                    isError = state.isUnameError,
                    supportingText = {
                        if(state.isUnameError){
                            Text(text = "El nombre de usuario no puede exceder los 6 caracteres", color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
                        }
                    },
                    placeholder = { Text("Usuario", color = MaterialTheme.colorScheme.onSurface, fontSize = 15.sp) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.testTag("username_input")
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                        .heightIn(min = 56.dp)
                )

                Spacer(modifier = Modifier.height(22.dp))

                TextField(
                    value = state.password,
                    onValueChange = {viewModel.onPasswordChange(it)},
                    placeholder = { Text("Contraseña", color= MaterialTheme.colorScheme.onSurface, fontSize = 15.sp)},
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.testTag("password_input")
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                        .heightIn(min = 56.dp)
                )

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = {viewModel.onLoginClick()},
                    modifier = Modifier.testTag("login_button")
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                        .heightIn(min = 48.dp),
                    enabled = !state.isLoading,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    if(state.isLoading){
                        CircularWavyProgressIndicator(
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(36.dp)
                        )
                    }else{
                        Text(text = "Iniciar Sesión", color = MaterialTheme.colorScheme.onPrimary, fontSize = 17.sp)
                    }
                }
            }
        }
    }

}


@Preview(
    name = "Modo Claro",
    showBackground = true,
    device = Devices.PHONE
)
@Preview(
    name = "Modo Oscuro",
    showBackground = true,
    device = Devices.PHONE,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun FullScreenPreview(){
    TestComposeTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                LoginScreen()
            }
        }
    }
}