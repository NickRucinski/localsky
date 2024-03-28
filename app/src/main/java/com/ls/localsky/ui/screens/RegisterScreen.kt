package com.ls.localsky.ui.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ls.localsky.DatabaseLS
import com.ls.localsky.R
import com.ls.localsky.ui.app.LocalSkyAppRouter
import com.ls.localsky.ui.app.Screen
import com.ls.localsky.ui.components.ButtonComponent
import com.ls.localsky.ui.components.ClickableLoginText
import com.ls.localsky.ui.components.DividerTextComponent
import com.ls.localsky.ui.components.NormalTextInput
import com.ls.localsky.ui.components.PasswordInput
import com.ls.localsky.ui.components.TitleText

@Composable
fun RegisterScreen(
    context: Context,
    database: DatabaseLS
) {

    val firstNameValue = remember {
        mutableStateOf("")
    }
    val lastNameValue = remember {
        mutableStateOf("")
    }
    val passwordValue = remember {
        mutableStateOf("")
    }
    val emailValue = remember {
        mutableStateOf("")
    }


    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ){
        Column(modifier = Modifier.fillMaxSize()){

            TitleText(value = "Register to " + stringResource(R.string.app_name))
            Spacer(modifier=Modifier.height(50.dp))
            // Text input fields
            // First Name
            NormalTextInput(labelValue = stringResource(id = R.string.first_name), firstNameValue, Icons.Filled.Person, KeyboardType.Text)
            // Last name
            NormalTextInput(labelValue = stringResource(id = R.string.last_name), lastNameValue, Icons.Filled.Person, KeyboardType.Text)
            // Email
            NormalTextInput(labelValue = stringResource(id = R.string.email), emailValue, Icons.Filled.Mail, KeyboardType.Email)
            PasswordInput(labelValue = stringResource(id = R.string.password), passwordValue)
            Spacer(modifier=Modifier.height(50.dp))
            //Buttons
            Spacer(modifier=Modifier.height(10.dp))
            ButtonComponent(value = stringResource(id = R.string.register)){
                if(checkInputFields(
                    context,
                    firstNameValue.value,
                    lastNameValue.value,
                    passwordValue.value,
                    emailValue.value
                )){
                    database.createUser(
                        firstNameValue.value,
                        lastNameValue.value,
                        emailValue.value,
                        passwordValue.value,
                        { FirebaseUser, User ->
                            Toast.makeText(
                                context,
                                "Debug: Created User",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        {
                            Log.d("Register Error",it.toString())
                            Toast.makeText(
                                context,
                                "There was an error creating your account try again soon.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            }
            Spacer(modifier=Modifier.height(10.dp))
            //Login divider
            DividerTextComponent()
            ClickableLoginText(onTextSelected = {
                LocalSkyAppRouter.navigateTo(Screen.LoginScreen)
            })

        }

    }

}

@Preview
@Composable
fun DefaultPreviewOfRegisterScreen() {
    RegisterScreen(LocalContext.current,  DatabaseLS())
}

/**
 * Checks all fields provided to see if they are valid using [checkIfFieldIsValid]
 * @param firstNameField - The string element from the first name text edit field
 * @param lastNameField - The string element from the last name text edit field
 * @param emailField - The string element from the email text edit field
 * @param passwordField - The string element from the password text edit field
 * @return Boolean
 *
 * TODO - Make this more generic and add support for better password checking
 */
private fun checkInputFields(
    context: Context,
    firstNameField: String,
    lastNameField: String,
    emailField: String,
    passwordField: String
): Boolean{
    return checkIfFieldIsValid(
        context,
        firstNameField,
        "Please enter a value for First Name"
    ) &&
    checkIfFieldIsValid(
        context,
        lastNameField,
        "Please enter a value for Last Name"
    ) &&
    checkIfFieldIsValid(
        context,
        emailField,
        "Please enter a value for Email"
    ) &&
    checkIfFieldIsValid(
        context,
        passwordField,
        "Please enter a value for Password"
    )
}


/**
 * Checks if the given text field is blank
 * and if not it displays a toast with the given error message
 * @param context - Application context used to display the toast
 * @param field - The string field that is to be checked
 * @param errorMessage - A error message that describes the problem to the user
 * @return Boolean
 */
private fun checkIfFieldIsValid(
    context: Context,
    field: String,
    errorMessage: String
): Boolean{
    if(field.isNotBlank()){
        return true
    }
    Toast.makeText(
        context,
        errorMessage,
        Toast.LENGTH_SHORT
    ).show()
    return false
}