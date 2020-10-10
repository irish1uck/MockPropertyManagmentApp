package com.example.mockpropertymanagmentapp.ui.auth.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.mockpropertymanagmentapp.R
import com.example.mockpropertymanagmentapp.ui.auth.activities.LoginActivity
import com.example.mockpropertymanagmentapp.data.network.MyApi
import com.example.mockpropertymanagmentapp.data.models.Landlord
import com.example.mockpropertymanagmentapp.data.models.RegisterResponse
import com.example.mockpropertymanagmentapp.ui.auth.AuthListener
import kotlinx.android.synthetic.main.fragment_register_landlord.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterLandlordFragment : Fragment(), AuthListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_register_landlord, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        var api = MyApi()
        view.button_register_landlord_submit.setOnClickListener {
            var name = view.edit_text_register_landlord_name.text.toString()
            var email = view.edit_text_register_landlord_email.text.toString()
            var password = view.edit_text_register_landlord_password.text.toString()
            var confirmPassword = view.edit_text_register_landlord_confirm_password.text.toString()
            var type = "landlord"
            if (password == confirmPassword && password.length > 5) {
                var landlord = Landlord(email, name, password, type)
                api.registerLandlord(landlord)
                    .enqueue(object: Callback<RegisterResponse>{
                        override fun onResponse(
                            call: Call<RegisterResponse>,
                            response: Response<RegisterResponse>
                        ) {
                            Toast.makeText(activity, "Registration Successful", Toast.LENGTH_SHORT).show()
                            context!!.startActivity(Intent(context, LoginActivity::class.java))
                        }
                        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                            Toast.makeText(activity, "Registration Failed", Toast.LENGTH_SHORT).show()
                        }

                    })
            } else Toast.makeText(
                activity,
                "Passwords don't match or less than 6 characters",
                Toast.LENGTH_SHORT
            ).show()
        }
        view.button_register_landlord_to_login.setOnClickListener{
            context!!.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    override fun onStarted() {
        TODO("Not yet implemented")
    }

    override fun onSuccess(response: LiveData<String>) {
        TODO("Not yet implemented")
    }

    override fun onFailure(message: String) {
        TODO("Not yet implemented")
    }

}