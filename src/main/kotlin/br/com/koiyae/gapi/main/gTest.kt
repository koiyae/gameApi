import br.com.koiyae.gapi.model.Player

fun main() {
    val gamerOne = Player(
        "Bruno", "bruno@email.com"
    )
    println(gamerOne)

    val gamerTwo = Player(
        "Joao", "joao@email.com", "20/07/2002", "joao"
    )
    println(gamerTwo)

    gamerOne.let {
        it.birthDate = "16/06/2005"
        it.user = "koiyae"
    }.also {
        println(gamerOne.internalId)
    }

    println(gamerOne)
    gamerOne.user = "bruno koiyae"
    println(gamerOne)
}
