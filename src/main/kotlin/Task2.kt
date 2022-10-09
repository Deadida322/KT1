import java.util.*

class Task2 {

    var a: AuthCallback = object : AuthCallback {
        override fun authSuccess() {
            println("Авторизация выполнена успешно")
        }

        override fun authFailed() {
            println("Ошибка авторизации")
        }
    }

    public fun doAction(action: Action) {
        when (action) {
            is Action.Registration -> println("Регистрация началась")
            is Action.Logout -> println("Вы вышли из аккаунта")
            is Action.Login -> auth(action.user, a) { println("Кэш обновлён") }
        }
    }

    private class IllegalAgeException(message: String) : Exception(message)

    companion object {
        fun User.isAdult() {
            if (this.age > 18)
                return println(this)
            else
                throw IllegalAgeException("Недопустимый возраст")
        }
        inline fun auth(user: User, callback: AuthCallback, updateCache: () -> Unit) {
            try {
                user.isAdult()
                callback.authSuccess()
                updateCache()
            }
            catch (e: IllegalAgeException){
                callback.authFailed()
            }
        }
        private fun getUserNameList(list: List<User>): List<String> {
            val userList = mutableListOf<String>()
            for (i in list) {
                userList.add(i.name)
            }
            return userList
        }

        private fun <T> printList(list: List<T>) {
            for (i in list) {
                println(i)
            }
        }

        private fun getFullAccessList(userList: List<User>): List<User> {

            val resultList = mutableListOf<User>()

            for (i in userList) {
                if (i.type == Type.FULL)
                    resultList.add(i)
            }
            return resultList
        }

        fun main() {
            println("Первый пользователь")
            val firstUser = User.UserBuilder("Serg", 1).setAge(12).build()
            println(firstUser.startTime)

            Thread.sleep(1000)
            println("Второй пользователь")
            val secondUser = User.UserBuilder("David", 2).setAge(15).build()
            println(secondUser.startTime)

            val userList = mutableListOf<User>(
                User.UserBuilder("Dona", 8).setAge(19).setType(Type.FULL).build()
            ).apply {
                add(User.UserBuilder("Andrew", 32).setAge(18).build())
                add(User.UserBuilder("Mora", 21).setAge(120).setType(Type.FULL).build())
                add(User.UserBuilder("Lorem", 22).setAge(15).build())
            }

            println("Пользователи с полным доступом")
            printList(getFullAccessList(userList))

            println("Имена пользователей")
            printList(getUserNameList(userList))
        }
    }

}


