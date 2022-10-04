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

    enum class Type {
        DEMO, FULL
    }

    private class IllegalAgeException(message: String) : Exception(message)

    class User(var id: Int, var name: String, var age: Byte, var type: Type) {
        val startTime: Long by lazy {
            val format = java.text.SimpleDateFormat("dd-MM-yyyy hh:mm:ss.SSS")
            format.parse(format.format(Date())).time
        }

        override fun toString(): String {
            return "$id $name $age $type"
        }
    }




    companion object {
        fun User.isAdult() {
            if (this.age > 18) return println(this)
            else throw IllegalAgeException("Недопустимый возраст")
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
            val firstUser = User(123, "David", 12, Type.DEMO)
            println(firstUser.startTime)

            Thread.sleep(1000)
            println("Второй пользователь")
            val secondUser = User(123, "David", 12, Type.FULL)
            println(secondUser.startTime)

            val userList = mutableListOf<User>(
                User(1213, "Stas", 54, Type.FULL)
            ).apply {
                add(User(123, "Semen", 0, Type.DEMO))
                add(User(13, "YarAsSlave", 18, Type.FULL))
                add(User(313, "Sasha", 23, Type.DEMO))
            }

            println("Пользователи с полным доступом")
            printList(getFullAccessList(userList))

            println("Имена пользователей")
            printList(getUserNameList(userList))
        }
    }

}


