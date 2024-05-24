package br.com.koiyae.gapi.model

import java.util.Scanner
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

    val searchedGames = mutableListOf<Game?>()

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
        return "Nome: $name\nE-mail: $email\nData de Nascimento: $birthDate\nUsuário: $user\nId interno: $internalId"
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

    companion object {
        fun createPlayer(read: Scanner): Player {
            print("Boas-vindas ao GameApi! Vamos fazer seu cadastro. Digite o seu nome: ")
            val name = read.nextLine()
            print("Digite seu e-mail: ")
            val email = read.nextLine()
            print("Deseja completar seu cadastro com usuário e data de nascimento? s/N ")
            val option = read.nextLine()

            if(option.equals("s", true)) {
                print("Digite sua data de nascimento (DD/MM/AAAA): ")
                val birth = read.nextLine()
                print("Digite seu nome de usuário: ")
                val user = read.nextLine()

                return Player(name, email, birth, user)
            } else {
                return Player(name, email)
            }
        }
    }
}
