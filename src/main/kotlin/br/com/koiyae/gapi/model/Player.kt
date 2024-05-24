package br.com.koiyae.gapi.model

import kotlin.random.Random

data class Player(var name: String, var email: String) {

    var birthDate: String? = null
    var user: String? = null
        set(value) {
            field = value
            if (internalId.isNullOrBlank()) {
                createInternalId()
            }
        }
    var internalId: String? = null
        private set

    constructor(
        name: String, email: String, birthDate: String, user: String
    ) : this(name, email) {
        this.birthDate = birthDate
        this.user = user
        createInternalId()
    }

    init {
        if(name.isNullOrBlank()) {
            throw IllegalArgumentException("O nome está em branco!")
        }
        this.email = validateEmail()
    }


    override fun toString(): String {
        return "Gamer(nome='$name', email='$email', data de nascimento='$birthDate', usuário='$user, id interno='$internalId')"
    }

    fun createInternalId() {
        val number = Random.nextInt(1000)
        val tag = String.format("%04d", number)

        "$user#$tag".also { this.internalId = it }
    }

    fun validateEmail(): String {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if(regex.matches(email)) {
            return email
        } else {
            throw IllegalArgumentException("Email inválido!")
        }
    }
}
