package com.example.recipebook_final

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.isVisible
import com.example.recipebook_final.databinding.ActivityMainBinding
import com.example.recipebook_final.ui.theme.Recipebook_finalTheme

class MainActivity : ComponentActivity() {

    lateinit var bindingClass: ActivityMainBinding

    private var login: String = "empty"
    private var password: String = "empty"
    private var name: String = "empty"
    private var surname: String = "empty"
    private var middlename: String = "empty"
    var imgID: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Const.REQUEST_CODE_SIGN_IN){
            var incomeLogin = data?.getStringExtra(Const.LOGIN)
            var incomePassword = data?.getStringExtra(Const.PASSWORD)
            var incomeImgID = data?.getIntExtra(Const.AVATAR_ID, 0)

            //Log.d("MyLog", "Login: $login IncomeLogin: $incomeLogin Password: $password IncomePassword: $incomePassword")

            if (incomeLogin == login && incomePassword == password){
                bindingClass.ivUserAvatar.setImageResource(imgID)
                bindingClass.ivUserAvatar.visibility = View.VISIBLE
                val textInformation = "Welcome, $surname $name $middlename!"
                bindingClass.tvInformation.text = textInformation

                bindingClass.btnSignIn.text = "Logout"
                bindingClass.btnSignUp.visibility = View.GONE
            }
            else{
                bindingClass.ivUserAvatar.setImageResource(R.drawable.error)
                bindingClass.ivUserAvatar.visibility = View.VISIBLE
                bindingClass.tvInformation.text = "Error. User doesn't exist!"
                bindingClass.tvInformation.visibility = View.VISIBLE
            }
        }
        else if (requestCode == Const.REQUEST_CODE_SIGN_UP){
            login = data?.getStringExtra(Const.LOGIN)!!
            password = data?.getStringExtra(Const.PASSWORD)!!
            surname = data?.getStringExtra(Const.SURNAME)!!
            name = data?.getStringExtra(Const.NAME)!!
            middlename = data?.getStringExtra(Const.MIDDLE_NAME)!!
            imgID = data?.getIntExtra(Const.AVATAR_ID, 0)!!

            bindingClass.ivUserAvatar.visibility = View.VISIBLE
            bindingClass.ivUserAvatar.setImageResource(imgID)

            val textInformation = "Welcome, $surname $name $middlename!"
            bindingClass.tvInformation.text = textInformation
            bindingClass.tvInformation.visibility = View.VISIBLE

            bindingClass.btnSignIn.text = "Logout"
            bindingClass.btnSignUp.visibility = View.GONE
        }
    }

    fun onClickSignIn(view: View){
        if (bindingClass.ivUserAvatar.isVisible && bindingClass.tvInformation.text.toString() != "Error. User doesn't exist!"){
            bindingClass.tvInformation.text = ""
            bindingClass.ivUserAvatar.visibility = View.INVISIBLE
            bindingClass.btnSignIn.text = getString(R.string.sign_in)
            bindingClass.btnSignUp.visibility = View.VISIBLE
        }
        else{
            val intent = Intent(this, SignInUpActivity::class.java)
            intent.putExtra(Const.SIGN_STATE, Const.SIGN_IN_STATE)
            startActivityForResult(intent, Const.REQUEST_CODE_SIGN_IN)
        }

    }

    fun onClickSignUp(view: View){
        val intent = Intent(this, SignInUpActivity::class.java)
        intent.putExtra(Const.SIGN_STATE, Const.SIGN_UP_STATE)
        startActivityForResult(intent, Const.REQUEST_CODE_SIGN_UP)
    }



}