package com.example.recipebook_final

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import com.example.recipebook_final.databinding.LayoutSigninupActivityBinding

class SignInUpActivity : ComponentActivity() {

    private lateinit var bindingClass: LayoutSigninupActivityBinding

    private var incomeStat = "empty"

    var imgid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = LayoutSigninupActivityBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        incomeStat = intent.getStringExtra(Const.SIGN_STATE)!!

        if (incomeStat == Const.SIGN_IN_STATE){
            showSignIn()
        }
        else if (incomeStat == Const.SIGN_UP_STATE){
            showSignUp()
        }
    }


    private fun showSignIn(){
        bindingClass.edSurname.visibility = View.GONE
        bindingClass.edName.visibility = View.GONE
        bindingClass.edMiddleName.visibility = View.GONE
        bindingClass.ivUserAvatarSign.visibility = View.INVISIBLE
        bindingClass.btnShowAvatar.visibility = View.INVISIBLE
    }

    private fun showSignUp(){
        bindingClass.edSurname.visibility = View.VISIBLE
        bindingClass.edName.visibility = View.VISIBLE
        bindingClass.edMiddleName.visibility = View.VISIBLE
        bindingClass.btnShowAvatar.visibility = View.VISIBLE
        bindingClass.ivUserAvatarSign.visibility = View.INVISIBLE
    }

    fun onClickDone(view: View){
        if (incomeStat == Const.SIGN_UP_STATE){
            val intent = Intent()
            intent.putExtra(Const.LOGIN, bindingClass.edLogin.text.toString())
            intent.putExtra(Const.PASSWORD, bindingClass.edPassword.text.toString())
            intent.putExtra(Const.SURNAME, bindingClass.edSurname.text.toString())
            intent.putExtra(Const.NAME, bindingClass.edName.text.toString())
            intent.putExtra(Const.MIDDLE_NAME, bindingClass.edMiddleName.text.toString())
            intent.putExtra(Const.AVATAR_ID, imgid)
            setResult(RESULT_OK, intent)
            finish()
        }
        else if (incomeStat == Const.SIGN_IN_STATE){
            val intent = Intent()
            intent.putExtra(Const.LOGIN, bindingClass.edLogin.text.toString())
            intent.putExtra(Const.PASSWORD, bindingClass.edPassword.text.toString())
            intent.putExtra(Const.AVATAR_ID, imgid)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    fun onClickShowAvatar(view: View){
        bindingClass.ivUserAvatarSign.setImageResource(R.drawable.money)
        imgid = resources.getIdentifier("money", "drawable", this.packageName)
        bindingClass.ivUserAvatarSign.visibility = View.VISIBLE
    }
}