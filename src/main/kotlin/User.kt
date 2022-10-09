import java.util.*

class User() {
    var id: Int = 0
    var name: String = ""
    var age: Byte = 0
    var type: Type = Type.DEMO

    class UserBuilder(var name: String, var id: Int) {
        var age: Byte = 0
        var type: Type = Type.DEMO

        fun setAge(age: Byte): UserBuilder {
            this.age = age
            return this
        }

        fun setType(type: Type): UserBuilder {
            this.type = type
            return this
        }

        fun build(): User {
            return User(this)
        }

    }

    val startTime: Long by lazy {
        val format = java.text.SimpleDateFormat("dd-MM-yyyy hh:mm:ss.SSS")
        format.parse(format.format(Date())).time
    }

    constructor(userBuilder: UserBuilder) : this() {
        this.id = userBuilder.id
        this.name = userBuilder.name
        this.age = userBuilder.age
        this.type = userBuilder.type
    }


    override fun toString(): String {
        return "$id $name $age $type"
    }
}