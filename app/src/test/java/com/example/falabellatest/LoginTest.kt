package com.example.falabellatest

import com.example.falabellatest.fragments.LoginFragment
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class LoginTest {
    private lateinit var loginFragment: LoginFragment

    @Before
    fun setup() {
        loginFragment = Mockito.mock(LoginFragment::class.java)
    }

    @Test
    fun whenLogingIn() {
        loginFragment.login("prueba","123123")
        Mockito.verify(loginFragment).login("prueba","123123")
    }
}